package cloud.lemonslice.barter.common.container;

import cloud.lemonslice.barter.common.tileentity.TradeStationBlockTileEntity;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.List;

import static cloud.lemonslice.barter.common.container.ContainerTypesRegistry.TRADE_STATION_PURCHASE_CONTAINER;

public class TradeStationPurchaseContainer extends Container
{
    private TradeStationBlockTileEntity tileEntity;
    public final ItemStackHandler showing = new ItemStackHandler(5);
    public final ItemStackHandler inputs = new ItemStackHandler(4);

    private int purchaseCount = 0;
    private int remainCount = 0;

    public TradeStationPurchaseContainer(int windowId, PlayerInventory inv, BlockPos pos, World world)
    {
        super(TRADE_STATION_PURCHASE_CONTAINER, windowId);
        this.tileEntity = (TradeStationBlockTileEntity) world.getTileEntity(pos);

        for (int i = 0; i < 4; i++)
        {
            addSlot(new SlotItemHandler(showing, i, 36 + 18 * i, 9)
            {
                @Override
                public boolean isItemValid(@Nonnull ItemStack stack)
                {
                    return false;
                }

                @Override
                public boolean canTakeStack(PlayerEntity playerIn)
                {
                    return false;
                }
            });
        }
        addSlot(new SlotItemHandler(showing, 4, 124, 9)
        {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack)
            {
                return false;
            }

            @Override
            public boolean canTakeStack(PlayerEntity playerIn)
            {
                return false;
            }
        });

        for (int i = 0; i < 4; i++)
        {
            addSlot(new SlotItemHandler(inputs, i, 36 + 18 * i, 39)
            {
                @Override
                public void onSlotChanged()
                {
                    super.onSlotChanged();
                    TradeStationPurchaseContainer.this.onInputChanged();
                }
            });
        }

        for (int i = 0; i < 3; ++i)
        {

            for (int j = 0; j < 9; ++j)
            {
                addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 66 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            addSlot(new Slot(inv, i, 8 + i * 18, 124));
        }

        refreshRemain();
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index)
    {
        Slot slot = this.inventorySlots.get(index);

        if (slot == null || !slot.getHasStack())
        {
            return ItemStack.EMPTY;
        }

        ItemStack newStack = slot.getStack(), oldStack = newStack.copy();

        boolean isMerged;

        // 0~5: Showing; 5~9: Input; 9~36: Player Backpack; 36~45: Hot Bar.

        if (index < 5)
        {
            isMerged = false;
        }
        else if (index < 9)
        {
            isMerged = mergeItemStack(newStack, 36, 45, true)
                    || mergeItemStack(newStack, 9, 36, false);
        }
        else if (index < 36)
        {
            isMerged = mergeItemStack(newStack, 5, 9, false)
                    || mergeItemStack(newStack, 36, 45, true);
        }
        else
        {
            isMerged = mergeItemStack(newStack, 5, 9, false)
                    || mergeItemStack(newStack, 9, 36, false);
        }

        if (!isMerged)
        {
            return ItemStack.EMPTY;
        }

        if (newStack.getCount() == 0)
        {
            slot.putStack(ItemStack.EMPTY);
        }
        else
        {
            slot.onSlotChanged();
        }

        slot.onTake(playerIn, newStack);

        return oldStack;
    }

    public void onContainerClosed(PlayerEntity playerIn)
    {
        super.onContainerClosed(playerIn);
        if (!playerIn.isAlive() || playerIn instanceof ServerPlayerEntity && ((ServerPlayerEntity) playerIn).hasDisconnected())
        {
            for (int j = 0; j < 4; ++j)
            {
                playerIn.dropItem(inputs.getStackInSlot(j), false);
            }

        }
        else
        {
            for (int i = 0; i < 4; ++i)
            {
                playerIn.inventory.placeItemBackInInventory(playerIn.getEntityWorld(), inputs.getStackInSlot(i));
            }
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn)
    {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerIn, tileEntity.getBlockState().getBlock());
    }

    public void refreshRemain()
    {
        getTileEntity().getContainerInventory().ifPresent(h ->
        {
            if (getTileEntity().isSpecialMode())
            {
                remainCount = tileEntity.getStockCount() > 0 ? 1 : 0;
            }
            else if (h.getStackInSlot(0).getCount() != 0)
                remainCount = tileEntity.getStockCount() / h.getStackInSlot(0).getCount();
        });
    }

    public void onInputChanged()
    {
        getTileEntity().getContainerInventory().ifPresent(h ->
        {
            refreshRemain();
            if (!h.getStackInSlot(0).isEmpty())
            {
                purchaseCount = remainCount;
                List<Pair<Ingredient, Integer>> list = getTileEntity().getInputIngredients();
                if (!list.isEmpty())
                {
                    for (int i = 0; i < list.size(); i++)
                    {
                        Ingredient ingredient = list.get(i).getFirst();
                        int count = list.get(i).getSecond();
                        ItemStack input = this.inputs.getStackInSlot(i);
                        if (ingredient.test(input))
                        {
                            purchaseCount = Math.min(purchaseCount, input.getCount() / count);
                        }
                        else
                        {
                            purchaseCount = 0;
                            return;
                        }
                    }
                }
                else
                {
                    purchaseCount = 0;
                }
            }
            else
            {
                purchaseCount = 0;
            }
        });
    }

    public int getPurchaseCount()
    {
        return purchaseCount;
    }

    public int getRemainCount()
    {
        return remainCount;
    }

    public TradeStationBlockTileEntity getTileEntity()
    {
        return tileEntity;
    }
}

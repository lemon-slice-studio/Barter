package cloud.lemonslice.barter.common.container;

import cloud.lemonslice.barter.common.block.NormalTradeStationBlock;
import cloud.lemonslice.barter.common.tileentity.TradeStationBlockTileEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

import static cloud.lemonslice.barter.common.container.ContainerTypesRegistry.TRADE_STATION_SALE_CONTAINER;

public class TradeStationSaleContainer extends Container
{
    private TradeStationBlockTileEntity tileEntity;

    public TradeStationSaleContainer(int windowId, PlayerInventory inv, BlockPos pos, World world)
    {
        super(TRADE_STATION_SALE_CONTAINER, windowId);
        this.tileEntity = (TradeStationBlockTileEntity) world.getTileEntity(pos);
        tileEntity.getContainerInventory().ifPresent(h ->
        {
            addSlot(new SlotItemHandler(h, 0, 8, 124)
            {
                @Override
                public boolean isItemValid(@Nonnull ItemStack stack)
                {
                    return !(Block.getBlockFromItem(stack.getItem()) instanceof NormalTradeStationBlock);
                }
            });
            for (int i = 2; i < 4; ++i)
            {
                for (int j = 0; j < 9; ++j)
                {
                    addSlot(new SlotItemHandler(h, j + i * 9 - 17, 8 + j * 18, 16 + i * 18 + 3)
                    {
                        @Override
                        public boolean isItemValid(@Nonnull ItemStack stack)
                        {
                            return !(Block.getBlockFromItem(stack.getItem()) instanceof NormalTradeStationBlock);
                        }
                    });
                }
            }
        });
        tileEntity.getPurchaseInventory().ifPresent(h ->
        {
            for (int i = 0; i < 2; ++i)
            {
                for (int j = 0; j < 9; ++j)
                {
                    addSlot(new SlotItemHandler(h, j + i * 9, 8 + j * 18, 16 + i * 18)
                    {
                        @Override
                        public boolean isItemValid(@Nonnull ItemStack stack)
                        {
                            return !(Block.getBlockFromItem(stack.getItem()) instanceof NormalTradeStationBlock);
                        }
                    });
                }
            }
        });
        for (int i = 0; i < 3; ++i)
        {

            for (int j = 0; j < 9; ++j)
            {
                addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 151 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            addSlot(new Slot(inv, i, 8 + i * 18, 209));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn)
    {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerIn, tileEntity.getBlockState().getBlock());
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

        // 0: Showing;
        // 1~19: For Sale;
        // 19~37: Input;
        // 37~64: Player Backpack;
        // 64~73: Hot Bar.

        if (index == 0)
        {
            isMerged = mergeItemStack(newStack, 1, 19, false)
                    || mergeItemStack(newStack, 64, 73, true)
                    || mergeItemStack(newStack, 37, 64, false);
        }
        else if (index < 19)
        {
            isMerged = mergeItemStack(newStack, 64, 73, true)
                    || mergeItemStack(newStack, 37, 64, false);
        }
        else if (index < 37)
        {
            isMerged = mergeItemStack(newStack, 64, 73, true)
                    || mergeItemStack(newStack, 37, 64, false);
        }
        else if (index < 64)
        {
            isMerged = mergeItemStack(newStack, 1, 19, false)
                    || mergeItemStack(newStack, 64, 73, true);
        }
        else
        {
            isMerged = mergeItemStack(newStack, 1, 19, false)
                    || mergeItemStack(newStack, 37, 64, false);
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

    public TradeStationBlockTileEntity getTileEntity()
    {
        return tileEntity;
    }
}

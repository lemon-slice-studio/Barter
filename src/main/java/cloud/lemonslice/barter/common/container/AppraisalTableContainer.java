package cloud.lemonslice.barter.common.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import static cloud.lemonslice.barter.common.container.ContainerTypesRegistry.APPRAISAL_TABLE_CONTAINER;

public class AppraisalTableContainer extends Container
{
    public final ItemStackHandler inputs = new ItemStackHandler(4);
    private final BlockPos pos;
    private final World world;

    public AppraisalTableContainer(int windowId, PlayerInventory inv, BlockPos pos, World world)
    {
        super(APPRAISAL_TABLE_CONTAINER, windowId);
        this.pos = pos;
        this.world = world;

        for (int i = 0; i < 4; i++)
        {
            addSlot(new SlotItemHandler(inputs, i, 53 + 18 * i, 27));
        }

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 103 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            addSlot(new Slot(inv, i, 8 + i * 18, 161));
        }
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

        // 0~4: Showing; 4~31: Player Backpack; 31~40: Hot Bar.

        if (index < 4)
        {
            isMerged = mergeItemStack(newStack, 31, 40, true)
                    || mergeItemStack(newStack, 4, 31, false);
        }
        else if (index < 31)
        {
            isMerged = mergeItemStack(newStack, 5, 9, false)
                    || mergeItemStack(newStack, 31, 40, true);
        }
        else
        {
            isMerged = mergeItemStack(newStack, 0, 31, false);
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
        return isWithinUsableDistance(IWorldPosCallable.of(world, pos), playerIn, world.getBlockState(pos).getBlock());
    }
}

package cloud.lemonslice.barter.common.block;

import cloud.lemonslice.barter.common.tileentity.TradeStationBlockTileEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Collections;
import java.util.List;

public class WoodenTradeStationBlock extends TradeStationBlock
{
    public WoodenTradeStationBlock(String name)
    {
        super(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD), name);
    }

    private void dropItems(World worldIn, BlockPos pos)
    {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof TradeStationBlockTileEntity)
        {
            ((TradeStationBlockTileEntity) te).getContainerInventory().ifPresent(h ->
            {
                for (int i = 0; i < 19; i++)
                {
                    ItemStack stack = h.getStackInSlot(i);
                    if (stack != ItemStack.EMPTY)
                    {
                        Block.spawnAsEntity(worldIn, pos, stack);
                    }
                }
            });

            ((TradeStationBlockTileEntity) te).getPurchaseInventory().ifPresent(h ->
            {
                for (int i = 0; i < 18; i++)
                {
                    ItemStack stack = h.getStackInSlot(i);
                    if (stack != ItemStack.EMPTY)
                    {
                        Block.spawnAsEntity(worldIn, pos, stack);
                    }
                }
            });


            ItemStackHandler h = ((TradeStationBlockTileEntity) te).getInputInventory();
            for (int i = 0; i < 4; i++)
            {
                ItemStack stack = h.getStackInSlot(i);
                if (stack != ItemStack.EMPTY)
                {
                    Block.spawnAsEntity(worldIn, pos, stack);
                }
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
    {
        return Collections.singletonList(new ItemStack(Items.CHEST));
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (!worldIn.isRemote && !(newState.getBlock() instanceof TradeStationBlock))
        {
            dropItems(worldIn, pos);
        }
        if (!(newState.getBlock() instanceof TradeStationBlock) || !newState.hasTileEntity())
        {
            worldIn.removeTileEntity(pos);
        }
    }
}

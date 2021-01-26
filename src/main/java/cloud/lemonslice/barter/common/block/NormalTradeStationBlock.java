package cloud.lemonslice.barter.common.block;

import cloud.lemonslice.barter.common.item.ItemsRegistry;
import cloud.lemonslice.barter.common.item.KeyItem;
import cloud.lemonslice.barter.common.tileentity.TileEntityTypesRegistry;
import cloud.lemonslice.barter.common.tileentity.TradeStationBlockTileEntity;
import cloud.lemonslice.silveroak.common.block.NormalHorizontalBlock;
import cloud.lemonslice.silveroak.helper.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.network.NetworkHooks;

public class NormalTradeStationBlock extends NormalHorizontalBlock
{
    public static final EnumProperty<EdgeType> EDGE_TYPE = EnumProperty.create("edge", EdgeType.class);
    protected static final VoxelShape SHAPE = VoxelShapeHelper.createVoxelShape(0, 0, 0, 16, 8, 16);

    public NormalTradeStationBlock(Properties properties, String name)
    {
        super(properties, name);
        this.setDefaultState(this.getStateContainer().getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(EDGE_TYPE, EdgeType.NONE));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE;
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return TileEntityTypesRegistry.NORMAL_TRADE_STATION.create();
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (worldIn.isRemote)
        {
            return ActionResultType.SUCCESS;
        }
        ItemStack held = player.getHeldItem(handIn);
        if (player.isSneaking() && !held.isEmpty())
        {
            Item heldItem = held.getItem();
            if (Tags.Items.INGOTS_IRON.contains(heldItem))
            {
                worldIn.setBlockState(pos, state.with(EDGE_TYPE, EdgeType.IRON));
                return ActionResultType.SUCCESS;
            }
            else if (Tags.Items.INGOTS_GOLD.contains(heldItem))
            {
                worldIn.setBlockState(pos, state.with(EDGE_TYPE, EdgeType.GOLD));
                return ActionResultType.SUCCESS;
            }
            else if (Tags.Items.GEMS_LAPIS.contains(heldItem))
            {
                worldIn.setBlockState(pos, state.with(EDGE_TYPE, EdgeType.LAPIS));
                return ActionResultType.SUCCESS;
            }
            else if (Tags.Items.GEMS_EMERALD.contains(heldItem))
            {
                worldIn.setBlockState(pos, state.with(EDGE_TYPE, EdgeType.EMERALD));
                return ActionResultType.SUCCESS;
            }
            else if (Tags.Items.GEMS_DIAMOND.contains(heldItem))
            {
                worldIn.setBlockState(pos, state.with(EDGE_TYPE, EdgeType.DIAMOND));
                return ActionResultType.SUCCESS;
            }
            else if (Tags.Items.DUSTS_REDSTONE.contains(heldItem))
            {
                worldIn.setBlockState(pos, state.with(EDGE_TYPE, EdgeType.REDSTONE));
                return ActionResultType.SUCCESS;
            }
            else if (Tags.Items.INGOTS_NETHERITE.contains(heldItem))
            {
                worldIn.setBlockState(pos, state.with(EDGE_TYPE, EdgeType.NETHERITE));
                return ActionResultType.SUCCESS;
            }
            else if (Tags.Items.GEMS_QUARTZ.contains(heldItem))
            {
                worldIn.setBlockState(pos, state.with(EDGE_TYPE, EdgeType.QUARTZ));
                return ActionResultType.SUCCESS;
            }
        }
        else
        {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof TradeStationBlockTileEntity)
            {
                if (held.getItem() == ItemsRegistry.TRADE_STATION_ADMIN_KEY)
                {
                    NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) te, te.getPos());
                    return ActionResultType.SUCCESS;
                }

                if (held.getItem() instanceof KeyItem)
                {
                    String owner = held.getOrCreateTag().getString("Owner");
                    String staff = held.getOrCreateTag().getString("Staff");
                    if ((owner.equals(player.getUniqueID().toString()) || staff.equals(player.getUniqueID().toString())) && ((TradeStationBlockTileEntity) te).checkOwner(owner))
                    {
                        NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) te, te.getPos());
                        return ActionResultType.SUCCESS;
                    }
                    else
                    {
                        player.sendStatusMessage(new TranslationTextComponent("info.barter.trade_station.key_wrong"), true);
                    }
                }
                else
                {
                    NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) te, te.getPos());
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return ActionResultType.PASS;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(HORIZONTAL_FACING, EDGE_TYPE);
    }
}
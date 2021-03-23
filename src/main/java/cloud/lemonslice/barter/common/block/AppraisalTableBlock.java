package cloud.lemonslice.barter.common.block;

import cloud.lemonslice.barter.common.container.AppraisalTableContainer;
import cloud.lemonslice.silveroak.common.block.NormalHorizontalBlock;
import cloud.lemonslice.silveroak.helper.VoxelShapeHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class AppraisalTableBlock extends NormalHorizontalBlock
{
    protected static final VoxelShape SHAPE;
    private static final ITextComponent CONTAINER_NAME = new TranslationTextComponent("container.barter.appraisal");

    public AppraisalTableBlock(String name)
    {
        super(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(5.0F).sound(SoundType.STONE), name);
        this.setDefaultState(this.getStateContainer().getBaseState().with(HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE;
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (worldIn.isRemote)
        {
            return ActionResultType.SUCCESS;
        }
        else
        {
            NetworkHooks.openGui((ServerPlayerEntity) player, getContainer(state, worldIn, pos), pos);
            return ActionResultType.CONSUME;
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos)
    {
        return new SimpleNamedContainerProvider((id, inventory, player) -> new AppraisalTableContainer(id, inventory, pos, worldIn), CONTAINER_NAME);
    }

    static
    {
        VoxelShape top = VoxelShapeHelper.createVoxelShape(0.5, 8, 0.5, 15, 2, 15);
        VoxelShape leg1 = VoxelShapeHelper.createVoxelShape(1, 0, 1, 2, 8, 2);
        VoxelShape leg2 = VoxelShapeHelper.createVoxelShape(13, 0, 1, 2, 8, 2);
        VoxelShape leg3 = VoxelShapeHelper.createVoxelShape(1, 0, 13, 2, 8, 2);
        VoxelShape leg4 = VoxelShapeHelper.createVoxelShape(13, 0, 13, 2, 8, 2);
        SHAPE = VoxelShapes.or(top, leg1, leg2, leg3, leg4);
    }
}

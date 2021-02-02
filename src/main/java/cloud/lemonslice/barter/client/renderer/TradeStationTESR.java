package cloud.lemonslice.barter.client.renderer;

import cloud.lemonslice.barter.Barter;
import cloud.lemonslice.barter.common.tileentity.TradeStationBlockTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class TradeStationTESR extends TileEntityRenderer<TradeStationBlockTileEntity>
{
    public TradeStationTESR(TileEntityRendererDispatcher rendererDispatcherIn)
    {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TradeStationBlockTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn)
    {
        Minecraft mc = Minecraft.getInstance();
        tileEntityIn.getContainerInventory().ifPresent(handler ->
        {
            ItemStack itemForShow = ItemStack.EMPTY;
            if (!handler.getStackInSlot(0).isEmpty())
            {
                itemForShow = handler.getStackInSlot(0);
            }

            ItemRenderer renderItem = mc.getItemRenderer();
            Direction direction = tileEntityIn.getBlockState().get(HORIZONTAL_FACING);

            if (!itemForShow.isEmpty())
            {
                matrixStackIn.push();
                RenderHelper.disableStandardItemLighting();

                matrixStackIn.translate(0, 0.65, 0);

                // Item for show.
                if (!tileEntityIn.isLocked())
                {
                    for (int i = 0; i < 4; i++)
                    {
                        matrixStackIn.push();
                        switch (direction)
                        {
                            case NORTH:
                                matrixStackIn.translate(i * 0.16D + 0.26D, i * 0.001D, 0.6D);
                                matrixStackIn.rotate(new Quaternion(Vector3f.YP, 45, true));
                                break;
                            case SOUTH:
                                matrixStackIn.translate(i * 0.16D + 0.26D, i * 0.001D, 0.4D);
                                matrixStackIn.rotate(new Quaternion(Vector3f.YP, 225, true));
                                break;
                            case EAST:
                                matrixStackIn.translate(0.4D, i * 0.001D, i * 0.16D + 0.26D);
                                matrixStackIn.rotate(new Quaternion(Vector3f.YP, 225, true));
                                break;
                            case WEST:
                                matrixStackIn.translate(0.6D, i * 0.001D, i * 0.16D + 0.26D);
                                matrixStackIn.rotate(new Quaternion(Vector3f.YP, 45, true));
                        }
                        matrixStackIn.scale(0.5F, 0.5F, 0.5F);

                        RenderHelper.enableStandardItemLighting();
                        renderItem.renderItem(itemForShow, ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
                        RenderHelper.disableStandardItemLighting();
                        matrixStackIn.pop();
                    }
                }
                matrixStackIn.pop();
            }

            matrixStackIn.push();
            RenderHelper.disableStandardItemLighting();
            int second = Math.toIntExact(Barter.PROXY.getClientWorld().getGameTime() / 20);

            int inputCount = tileEntityIn.getInputIngredients().size();
            switch (inputCount)
            {
                case 1:
                    matrixStackIn.push();
                    switch (direction)
                    {
                        case NORTH:
                            matrixStackIn.translate(0.5, 0.2, 0);
                            matrixStackIn.rotate(new Quaternion(Vector3f.YP, 0, true));
                            break;
                        case SOUTH:
                            matrixStackIn.translate(0.5, 0.2, 1);
                            matrixStackIn.rotate(new Quaternion(Vector3f.YP, 180, true));
                            break;
                        case EAST:
                            matrixStackIn.translate(1, 0.2, 0.5);
                            matrixStackIn.rotate(new Quaternion(Vector3f.YP, 270, true));
                            break;
                        case WEST:
                            matrixStackIn.translate(0, 0.2, 0.5);
                            matrixStackIn.rotate(new Quaternion(Vector3f.YP, 90, true));
                    }
                    matrixStackIn.scale(0.4F, 0.4F, 0.4F);
                    int index_1 = second % tileEntityIn.getInputIngredients().get(0).getFirst().getMatchingStacks().length;
                    RenderHelper.enableStandardItemLighting();
                    renderItem.renderItem(tileEntityIn.getInputIngredients().get(0).getFirst().getMatchingStacks()[index_1], ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
                    RenderHelper.disableStandardItemLighting();
                    matrixStackIn.pop();
                    break;
                case 2:
                    for (int i = 0; i < 2; i++)
                    {
                        matrixStackIn.push();
                        switch (direction)
                        {
                            case NORTH:
                                matrixStackIn.translate(0.375 + i * 0.25, 0.2, 0);
                                matrixStackIn.rotate(new Quaternion(Vector3f.YP, 0, true));
                                break;
                            case SOUTH:
                                matrixStackIn.translate(0.375 + i * 0.25, 0.2, 1);
                                matrixStackIn.rotate(new Quaternion(Vector3f.YP, 180, true));
                                break;
                            case EAST:
                                matrixStackIn.translate(1, 0.2, 0.375 + i * 0.25);
                                matrixStackIn.rotate(new Quaternion(Vector3f.YP, 270, true));
                                break;
                            case WEST:
                                matrixStackIn.translate(0, 0.2, 0.375 + i * 0.25);
                                matrixStackIn.rotate(new Quaternion(Vector3f.YP, 90, true));
                        }
                        matrixStackIn.scale(0.25F, 0.25F, 0.25F);
                        int index_2 = second % tileEntityIn.getInputIngredients().get(i).getFirst().getMatchingStacks().length;
                        RenderHelper.enableStandardItemLighting();
                        renderItem.renderItem(tileEntityIn.getInputIngredients().get(i).getFirst().getMatchingStacks()[index_2], ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
                        RenderHelper.disableStandardItemLighting();
                        matrixStackIn.pop();
                    }
                    break;
                case 3:
                    matrixStackIn.push();
                    switch (direction)
                    {
                        case NORTH:
                            matrixStackIn.translate(0.5, 0.3, 0);
                            matrixStackIn.rotate(new Quaternion(Vector3f.YP, 0, true));
                            break;
                        case SOUTH:
                            matrixStackIn.translate(0.5, 0.3, 1);
                            matrixStackIn.rotate(new Quaternion(Vector3f.YP, 180, true));
                            break;
                        case EAST:
                            matrixStackIn.translate(1, 0.3, 0.5);
                            matrixStackIn.rotate(new Quaternion(Vector3f.YP, 270, true));
                            break;
                        case WEST:
                            matrixStackIn.translate(0, 0.3, 0.5);
                            matrixStackIn.rotate(new Quaternion(Vector3f.YP, 90, true));
                    }
                    matrixStackIn.scale(0.25F, 0.25F, 0.25F);
                    int index_3 = second % tileEntityIn.getInputIngredients().get(0).getFirst().getMatchingStacks().length;
                    RenderHelper.enableStandardItemLighting();
                    renderItem.renderItem(tileEntityIn.getInputIngredients().get(0).getFirst().getMatchingStacks()[index_3], ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
                    RenderHelper.disableStandardItemLighting();
                    matrixStackIn.pop();

                    for (int i = 0; i < 2; i++)
                    {
                        matrixStackIn.push();
                        switch (direction)
                        {
                            case NORTH:
                                matrixStackIn.translate(0.375 + i * 0.25, 0.05, 0);
                                matrixStackIn.rotate(new Quaternion(Vector3f.YP, 0, true));
                                break;
                            case SOUTH:
                                matrixStackIn.translate(0.375 + i * 0.25, 0.05, 1);
                                matrixStackIn.rotate(new Quaternion(Vector3f.YP, 180, true));
                                break;
                            case EAST:
                                matrixStackIn.translate(1, 0.05, 0.375 + i * 0.25);
                                matrixStackIn.rotate(new Quaternion(Vector3f.YP, 270, true));
                                break;
                            case WEST:
                                matrixStackIn.translate(0, 0.05, 0.375 + i * 0.25);
                                matrixStackIn.rotate(new Quaternion(Vector3f.YP, 90, true));
                        }
                        matrixStackIn.scale(0.25F, 0.25F, 0.25F);
                        index_3 = second % tileEntityIn.getInputIngredients().get(i + 1).getFirst().getMatchingStacks().length;
                        RenderHelper.enableStandardItemLighting();
                        renderItem.renderItem(tileEntityIn.getInputIngredients().get(i + 1).getFirst().getMatchingStacks()[index_3], ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
                        RenderHelper.disableStandardItemLighting();
                        matrixStackIn.pop();
                    }
                    break;
                case 4:
                    for (int i = 0; i < 2; i++)
                    {
                        matrixStackIn.push();
                        switch (direction)
                        {
                            case NORTH:
                                matrixStackIn.translate(0.375 + i * 0.25, 0.3, 0);
                                matrixStackIn.rotate(new Quaternion(Vector3f.YP, 0, true));
                                break;
                            case SOUTH:
                                matrixStackIn.translate(0.375 + i * 0.25, 0.3, 1);
                                matrixStackIn.rotate(new Quaternion(Vector3f.YP, 180, true));
                                break;
                            case EAST:
                                matrixStackIn.translate(1, 0.3, 0.375 + i * 0.25);
                                matrixStackIn.rotate(new Quaternion(Vector3f.YP, 270, true));
                                break;
                            case WEST:
                                matrixStackIn.translate(0, 0.3, 0.375 + i * 0.25);
                                matrixStackIn.rotate(new Quaternion(Vector3f.YP, 90, true));
                        }
                        matrixStackIn.scale(0.25F, 0.25F, 0.25F);
                        int index_4 = second % tileEntityIn.getInputIngredients().get(i).getFirst().getMatchingStacks().length;
                        RenderHelper.enableStandardItemLighting();
                        renderItem.renderItem(tileEntityIn.getInputIngredients().get(i).getFirst().getMatchingStacks()[index_4], ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
                        RenderHelper.disableStandardItemLighting();
                        matrixStackIn.pop();
                    }
                    for (int i = 0; i < 2; i++)
                    {
                        matrixStackIn.push();
                        switch (direction)
                        {
                            case NORTH:
                                matrixStackIn.translate(0.375 + i * 0.25, 0.05, 0);
                                matrixStackIn.rotate(new Quaternion(Vector3f.YP, 0, true));
                                break;
                            case SOUTH:
                                matrixStackIn.translate(0.375 + i * 0.25, 0.05, 1);
                                matrixStackIn.rotate(new Quaternion(Vector3f.YP, 180, true));
                                break;
                            case EAST:
                                matrixStackIn.translate(1, 0.05, 0.375 + i * 0.25);
                                matrixStackIn.rotate(new Quaternion(Vector3f.YP, 270, true));
                                break;
                            case WEST:
                                matrixStackIn.translate(0, 0.05, 0.375 + i * 0.25);
                                matrixStackIn.rotate(new Quaternion(Vector3f.YP, 90, true));
                        }
                        matrixStackIn.scale(0.25F, 0.25F, 0.25F);
                        int index_4 = second % tileEntityIn.getInputIngredients().get(i + 2).getFirst().getMatchingStacks().length;
                        RenderHelper.enableStandardItemLighting();
                        renderItem.renderItem(tileEntityIn.getInputIngredients().get(i + 2).getFirst().getMatchingStacks()[index_4], ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
                        RenderHelper.disableStandardItemLighting();
                        matrixStackIn.pop();
                    }
            }
            matrixStackIn.pop();
        });
    }
}

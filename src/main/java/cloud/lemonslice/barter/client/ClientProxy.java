package cloud.lemonslice.barter.client;

import cloud.lemonslice.barter.client.renderer.TradeStationTESR;
import cloud.lemonslice.barter.common.CommonProxy;
import cloud.lemonslice.barter.common.tileentity.TileEntityTypesRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import java.util.Arrays;

import static cloud.lemonslice.barter.common.block.BlocksRegistry.*;

public class ClientProxy extends CommonProxy
{

    @Override
    public World getClientWorld()
    {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer()
    {
        return Minecraft.getInstance().player;
    }

    public static void registerRenderType()
    {
        registerCutoutType(WHITE_APPRAISAL_TABLE, ORANGE_APPRAISAL_TABLE, MAGENTA_APPRAISAL_TABLE, LIGHT_BLUE_APPRAISAL_TABLE,
                YELLOW_APPRAISAL_TABLE, LIME_APPRAISAL_TABLE, PINK_APPRAISAL_TABLE, GRAY_APPRAISAL_TABLE,
                LIGHT_GRAY_APPRAISAL_TABLE, CYAN_APPRAISAL_TABLE, PURPLE_APPRAISAL_TABLE, BLUE_APPRAISAL_TABLE,
                BROWN_APPRAISAL_TABLE, GREEN_APPRAISAL_TABLE, RED_APPRAISAL_TABLE, BLACK_APPRAISAL_TABLE);
    }

    public static void bindTileEntityRenderer()
    {
        ClientRegistry.bindTileEntityRenderer(TileEntityTypesRegistry.NORMAL_TRADE_STATION, TradeStationTESR::new);
    }

    private static void registerCutoutType(Block... blocks)
    {
        Arrays.asList(blocks).forEach(block -> RenderTypeLookup.setRenderLayer(block, RenderType.getCutout()));
    }
}

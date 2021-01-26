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

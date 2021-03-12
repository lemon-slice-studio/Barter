package cloud.lemonslice.barter;

import cloud.lemonslice.barter.client.ClientProxy;
import cloud.lemonslice.barter.common.CommonProxy;
import cloud.lemonslice.barter.common.block.BlocksRegistry;
import cloud.lemonslice.barter.common.container.ContainerTypesRegistry;
import cloud.lemonslice.barter.common.group.BarterGroup;
import cloud.lemonslice.barter.common.item.ItemsRegistry;
import cloud.lemonslice.barter.common.tileentity.TileEntityTypesRegistry;
import cloud.lemonslice.barter.network.SimpleNetworkHandler;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("barter")
public final class Barter
{
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "barter";
    public static final CommonProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public Barter()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);
        new BlocksRegistry();
        new ItemsRegistry();
        new TileEntityTypesRegistry();
        new ContainerTypesRegistry();
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        CommonProxy.registerCompostable();
        CommonProxy.registerFireInfo();
        SimpleNetworkHandler.init();
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {
        ClientProxy.registerRenderType();
        ContainerTypesRegistry.clientInit();
        ClientProxy.bindTileEntityRenderer();
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event)
    {

    }

    public static void error(String format, Object... data)
    {
        Barter.LOGGER.log(Level.ERROR, String.format(format, data));
    }

    public static void warn(String format, Object... data)
    {
        Barter.LOGGER.log(Level.WARN, String.format(format, data));
    }

    public static void info(String format, Object... data)
    {
        Barter.LOGGER.log(Level.INFO, String.format(format, data));
    }

    public static final ItemGroup ITEM_GROUP = new BarterGroup();
}

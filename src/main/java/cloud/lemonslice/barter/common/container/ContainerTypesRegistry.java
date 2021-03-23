package cloud.lemonslice.barter.common.container;

import cloud.lemonslice.barter.Barter;
import cloud.lemonslice.barter.client.gui.AppraisalTableGuiContainer;
import cloud.lemonslice.barter.client.gui.TradeStationPurchaseGuiContainer;
import cloud.lemonslice.barter.client.gui.TradeStationSaleGuiContainer;
import cloud.lemonslice.barter.registry.RegistryModule;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;

public final class ContainerTypesRegistry extends RegistryModule
{
    public final static ContainerType<?> TRADE_STATION_SALE_CONTAINER = IForgeContainerType.create(((windowId, inv, data) -> new TradeStationSaleContainer(windowId, inv, data.readBlockPos(), Barter.PROXY.getClientWorld()))).setRegistryName("trade_station_sale");
    public final static ContainerType<?> TRADE_STATION_PURCHASE_CONTAINER = IForgeContainerType.create(((windowId, inv, data) -> new TradeStationPurchaseContainer(windowId, inv, data.readBlockPos(), Barter.PROXY.getClientWorld()))).setRegistryName("trade_station_purchase");
    public final static ContainerType<?> APPRAISAL_TABLE_CONTAINER = IForgeContainerType.create(((windowId, inv, data) -> new AppraisalTableContainer(windowId, inv, data.readBlockPos(), Barter.PROXY.getClientWorld()))).setRegistryName("appraisal_table");

    public static void clientInit()
    {
        ScreenManager.registerFactory((ContainerType<TradeStationSaleContainer>) TRADE_STATION_SALE_CONTAINER, TradeStationSaleGuiContainer::new);
        ScreenManager.registerFactory((ContainerType<TradeStationPurchaseContainer>) TRADE_STATION_PURCHASE_CONTAINER, TradeStationPurchaseGuiContainer::new);
        ScreenManager.registerFactory((ContainerType<AppraisalTableContainer>) APPRAISAL_TABLE_CONTAINER, AppraisalTableGuiContainer::new);
    }
}

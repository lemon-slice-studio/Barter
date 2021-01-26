package cloud.lemonslice.barter.common.tileentity;

import cloud.lemonslice.barter.registry.RegistryModule;
import net.minecraft.tileentity.TileEntityType;

import static cloud.lemonslice.barter.common.block.BlocksRegistry.*;

public final class TileEntityTypesRegistry extends RegistryModule
{
    public static final TileEntityType<TradeStationBlockTileEntity> NORMAL_TRADE_STATION =
            (TileEntityType<TradeStationBlockTileEntity>) TileEntityType.Builder.create(TradeStationBlockTileEntity::new,
                    WHITE_TRADE_STATION, ORANGE_TRADE_STATION, MAGENTA_TRADE_STATION, LIGHT_BLUE_TRADE_STATION,
                    YELLOW_TRADE_STATION, LIME_TRADE_STATION, PINK_TRADE_STATION, GRAY_TRADE_STATION,
                    LIGHT_GRAY_TRADE_STATION, CYAN_TRADE_STATION, PURPLE_TRADE_STATION, BLUE_TRADE_STATION,
                    BROWN_TRADE_STATION, GREEN_TRADE_STATION, RED_TRADE_STATION, BLACK_TRADE_STATION)
                    .build(null).setRegistryName("normal_trade_station");
}

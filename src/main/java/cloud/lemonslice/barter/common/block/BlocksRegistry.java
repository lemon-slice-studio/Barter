package cloud.lemonslice.barter.common.block;

import cloud.lemonslice.barter.registry.RegistryModule;
import cloud.lemonslice.silveroak.common.item.NormalBlockItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;

import static cloud.lemonslice.barter.Barter.ITEM_GROUP;

public final class BlocksRegistry extends RegistryModule
{
    public static final Block WHITE_TRADE_STATION = new NormalTradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "white_trade_station");
    public static final Block ORANGE_TRADE_STATION = new NormalTradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "orange_trade_station");
    public static final Block MAGENTA_TRADE_STATION = new NormalTradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "magenta_trade_station");
    public static final Block LIGHT_BLUE_TRADE_STATION = new NormalTradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "light_blue_trade_station");
    public static final Block YELLOW_TRADE_STATION = new NormalTradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "yellow_trade_station");
    public static final Block LIME_TRADE_STATION = new NormalTradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "lime_trade_station");
    public static final Block PINK_TRADE_STATION = new NormalTradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "pink_trade_station");
    public static final Block GRAY_TRADE_STATION = new NormalTradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "gray_trade_station");
    public static final Block LIGHT_GRAY_TRADE_STATION = new NormalTradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "light_gray_trade_station");
    public static final Block CYAN_TRADE_STATION = new NormalTradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "cyan_trade_station");
    public static final Block PURPLE_TRADE_STATION = new NormalTradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "purple_trade_station");
    public static final Block BLUE_TRADE_STATION = new NormalTradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "blue_trade_station");
    public static final Block BROWN_TRADE_STATION = new NormalTradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "brown_trade_station");
    public static final Block GREEN_TRADE_STATION = new NormalTradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "green_trade_station");
    public static final Block RED_TRADE_STATION = new NormalTradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "red_trade_station");
    public static final Block BLACK_TRADE_STATION = new NormalTradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "black_trade_station");

    public static final BlockItem WHITE_TRADE_STATION_ITEM = new NormalBlockItem(WHITE_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem ORANGE_TRADE_STATION_ITEM = new NormalBlockItem(ORANGE_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem MAGENTA_TRADE_STATION_ITEM = new NormalBlockItem(MAGENTA_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem LIGHT_BLUE_TRADE_STATION_ITEM = new NormalBlockItem(LIGHT_BLUE_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem YELLOW_TRADE_STATION_ITEM = new NormalBlockItem(YELLOW_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem LIME_TRADE_STATION_ITEM = new NormalBlockItem(LIME_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem PINK_TRADE_STATION_ITEM = new NormalBlockItem(PINK_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem GRAY_TRADE_STATION_ITEM = new NormalBlockItem(GRAY_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem LIGHT_GRAY_TRADE_STATION_ITEM = new NormalBlockItem(LIGHT_GRAY_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem CYAN_TRADE_STATION_ITEM = new NormalBlockItem(CYAN_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem PURPLE_TRADE_STATION_ITEM = new NormalBlockItem(PURPLE_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem BLUE_TRADE_STATION_ITEM = new NormalBlockItem(BLUE_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem BROWN_TRADE_STATION_ITEM = new NormalBlockItem(BROWN_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem GREEN_TRADE_STATION_ITEM = new NormalBlockItem(GREEN_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem RED_TRADE_STATION_ITEM = new NormalBlockItem(RED_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem BLACK_TRADE_STATION_ITEM = new NormalBlockItem(BLACK_TRADE_STATION, ITEM_GROUP);
}

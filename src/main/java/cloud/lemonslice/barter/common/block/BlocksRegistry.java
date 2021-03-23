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
    public static final Block WHITE_APPRAISAL_TABLE = new AppraisalTableBlock("white_appraisal_table");
    public static final Block ORANGE_APPRAISAL_TABLE = new AppraisalTableBlock("orange_appraisal_table");
    public static final Block MAGENTA_APPRAISAL_TABLE = new AppraisalTableBlock("magenta_appraisal_table");
    public static final Block LIGHT_BLUE_APPRAISAL_TABLE = new AppraisalTableBlock("light_blue_appraisal_table");
    public static final Block YELLOW_APPRAISAL_TABLE = new AppraisalTableBlock("yellow_appraisal_table");
    public static final Block LIME_APPRAISAL_TABLE = new AppraisalTableBlock("lime_appraisal_table");
    public static final Block PINK_APPRAISAL_TABLE = new AppraisalTableBlock("pink_appraisal_table");
    public static final Block GRAY_APPRAISAL_TABLE = new AppraisalTableBlock("gray_appraisal_table");
    public static final Block LIGHT_GRAY_APPRAISAL_TABLE = new AppraisalTableBlock("light_gray_appraisal_table");
    public static final Block CYAN_APPRAISAL_TABLE = new AppraisalTableBlock("cyan_appraisal_table");
    public static final Block PURPLE_APPRAISAL_TABLE = new AppraisalTableBlock("purple_appraisal_table");
    public static final Block BLUE_APPRAISAL_TABLE = new AppraisalTableBlock("blue_appraisal_table");
    public static final Block BROWN_APPRAISAL_TABLE = new AppraisalTableBlock("brown_appraisal_table");
    public static final Block GREEN_APPRAISAL_TABLE = new AppraisalTableBlock("green_appraisal_table");
    public static final Block RED_APPRAISAL_TABLE = new AppraisalTableBlock("red_appraisal_table");
    public static final Block BLACK_APPRAISAL_TABLE = new AppraisalTableBlock("black_appraisal_table");

    public static final BlockItem WHITE_APPRAISAL_TABLE_ITEM = new NormalBlockItem(WHITE_APPRAISAL_TABLE, ITEM_GROUP);
    public static final BlockItem ORANGE_APPRAISAL_TABLE_ITEM = new NormalBlockItem(ORANGE_APPRAISAL_TABLE, ITEM_GROUP);
    public static final BlockItem MAGENTA_APPRAISAL_TABLE_ITEM = new NormalBlockItem(MAGENTA_APPRAISAL_TABLE, ITEM_GROUP);
    public static final BlockItem LIGHT_BLUE_APPRAISAL_TABLE_ITEM = new NormalBlockItem(LIGHT_BLUE_APPRAISAL_TABLE, ITEM_GROUP);
    public static final BlockItem YELLOW_APPRAISAL_TABLE_ITEM = new NormalBlockItem(YELLOW_APPRAISAL_TABLE, ITEM_GROUP);
    public static final BlockItem LIME_APPRAISAL_TABLE_ITEM = new NormalBlockItem(LIME_APPRAISAL_TABLE, ITEM_GROUP);
    public static final BlockItem PINK_APPRAISAL_TABLE_ITEM = new NormalBlockItem(PINK_APPRAISAL_TABLE, ITEM_GROUP);
    public static final BlockItem GRAY_APPRAISAL_TABLE_ITEM = new NormalBlockItem(GRAY_APPRAISAL_TABLE, ITEM_GROUP);
    public static final BlockItem LIGHT_GRAY_APPRAISAL_TABLE_ITEM = new NormalBlockItem(LIGHT_GRAY_APPRAISAL_TABLE, ITEM_GROUP);
    public static final BlockItem CYAN_APPRAISAL_TABLE_ITEM = new NormalBlockItem(CYAN_APPRAISAL_TABLE, ITEM_GROUP);
    public static final BlockItem PURPLE_APPRAISAL_TABLE_ITEM = new NormalBlockItem(PURPLE_APPRAISAL_TABLE, ITEM_GROUP);
    public static final BlockItem BLUE_APPRAISAL_TABLE_ITEM = new NormalBlockItem(BLUE_APPRAISAL_TABLE, ITEM_GROUP);
    public static final BlockItem BROWN_APPRAISAL_TABLE_ITEM = new NormalBlockItem(BROWN_APPRAISAL_TABLE, ITEM_GROUP);
    public static final BlockItem GREEN_APPRAISAL_TABLE_ITEM = new NormalBlockItem(GREEN_APPRAISAL_TABLE, ITEM_GROUP);
    public static final BlockItem RED_APPRAISAL_TABLE_ITEM = new NormalBlockItem(RED_APPRAISAL_TABLE, ITEM_GROUP);
    public static final BlockItem BLACK_APPRAISAL_TABLE_ITEM = new NormalBlockItem(BLACK_APPRAISAL_TABLE, ITEM_GROUP);

    public static final Block ACACIA_TRADE_STATION = new WoodenTradeStationBlock("acacia_trade_station");
    public static final Block BIRCH_TRADE_STATION = new WoodenTradeStationBlock("birch_trade_station");
    public static final Block CRIMSON_TRADE_STATION = new WoodenTradeStationBlock("crimson_trade_station");
    public static final Block DARK_OAK_TRADE_STATION = new WoodenTradeStationBlock("dark_oak_trade_station");
    public static final Block JUNGLE_TRADE_STATION = new WoodenTradeStationBlock("jungle_trade_station");
    public static final Block OAK_TRADE_STATION = new WoodenTradeStationBlock("oak_trade_station");
    public static final Block SPRUCE_TRADE_STATION = new WoodenTradeStationBlock("spruce_trade_station");
    public static final Block WARPED_TRADE_STATION = new WoodenTradeStationBlock("warped_trade_station");

    public static final BlockItem ACACIA_TRADE_STATION_ITEM = new NormalBlockItem(ACACIA_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem BIRCH_TRADE_STATION_ITEM = new NormalBlockItem(BIRCH_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem CRIMSON_TRADE_STATION_ITEM = new NormalBlockItem(CRIMSON_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem DARK_OAK_TRADE_STATION_ITEM = new NormalBlockItem(DARK_OAK_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem JUNGLE_TRADE_STATION_ITEM = new NormalBlockItem(JUNGLE_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem OAK_TRADE_STATION_ITEM = new NormalBlockItem(OAK_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem SPRUCE_TRADE_STATION_ITEM = new NormalBlockItem(SPRUCE_TRADE_STATION, ITEM_GROUP);
    public static final BlockItem WARPED_TRADE_STATION_ITEM = new NormalBlockItem(WARPED_TRADE_STATION, ITEM_GROUP);

    public static final Block WHITE_TRADE_STATION = new TradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "white_trade_station");
    public static final Block ORANGE_TRADE_STATION = new TradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "orange_trade_station");
    public static final Block MAGENTA_TRADE_STATION = new TradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "magenta_trade_station");
    public static final Block LIGHT_BLUE_TRADE_STATION = new TradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "light_blue_trade_station");
    public static final Block YELLOW_TRADE_STATION = new TradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "yellow_trade_station");
    public static final Block LIME_TRADE_STATION = new TradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "lime_trade_station");
    public static final Block PINK_TRADE_STATION = new TradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "pink_trade_station");
    public static final Block GRAY_TRADE_STATION = new TradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "gray_trade_station");
    public static final Block LIGHT_GRAY_TRADE_STATION = new TradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "light_gray_trade_station");
    public static final Block CYAN_TRADE_STATION = new TradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "cyan_trade_station");
    public static final Block PURPLE_TRADE_STATION = new TradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "purple_trade_station");
    public static final Block BLUE_TRADE_STATION = new TradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "blue_trade_station");
    public static final Block BROWN_TRADE_STATION = new TradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "brown_trade_station");
    public static final Block GREEN_TRADE_STATION = new TradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "green_trade_station");
    public static final Block RED_TRADE_STATION = new TradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "red_trade_station");
    public static final Block BLACK_TRADE_STATION = new TradeStationBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F), "black_trade_station");

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

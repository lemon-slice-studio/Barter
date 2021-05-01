package cloud.lemonslice.barter.data.provider;

import cloud.lemonslice.barter.common.block.BlocksRegistry;
import cloud.lemonslice.barter.common.item.ItemsRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public final class RecipesProvider extends RecipeProvider
{
    public RecipesProvider(DataGenerator generatorIn)
    {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shapedRecipe(ItemsRegistry.WOODEN_KEY)
                .key('x', Tags.Items.RODS_WOODEN).key('+', ItemTags.PLANKS)
                .patternLine("+  ").patternLine(" xx").patternLine("  x")
                .setGroup("wooden_trade_station_shopkeeper_key")
                .addCriterion("has_planks", hasItem(ItemTags.PLANKS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemsRegistry.STONE_KEY)
                .key('*', Tags.Items.COBBLESTONE).key('+', Tags.Items.STONE)
                .patternLine("+  ").patternLine(" **").patternLine("  *")
                .setGroup("stone_trade_station_shopkeeper_key")
                .addCriterion("has_cobblestone", hasItem(Tags.Items.COBBLESTONE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemsRegistry.GOLDEN_KEY)
                .key('*', Tags.Items.INGOTS_GOLD).key('x', Tags.Items.NUGGETS_GOLD).key('+', Tags.Items.STORAGE_BLOCKS_GOLD)
                .patternLine("+  ").patternLine(" *x").patternLine("  *")
                .setGroup("trade_station_shopkeeper_key")
                .addCriterion("has_gold", hasItem(Tags.Items.STORAGE_BLOCKS_GOLD)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemsRegistry.STAFF_KEY)
                .key('*', Tags.Items.INGOTS_IRON).key('x', Tags.Items.NUGGETS_IRON).key('+', Tags.Items.STORAGE_BLOCKS_IRON)
                .patternLine("+  ").patternLine(" *x").patternLine("  *")
                .setGroup("trade_station_staff_key")
                .addCriterion("has_iron", hasItem(Tags.Items.INGOTS_IRON)).build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.ACACIA_TRADE_STATION_ITEM)
                .key('*', Blocks.ACACIA_WOOD).key('x', Blocks.ACACIA_PLANKS)
                .patternLine("***").patternLine("x x").patternLine("xxx")
                .setGroup("wooden_trade_station")
                .addCriterion("has_planks", hasItem(Blocks.ACACIA_PLANKS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.BIRCH_TRADE_STATION_ITEM)
                .key('*', Blocks.BIRCH_WOOD).key('x', Blocks.BIRCH_PLANKS)
                .patternLine("***").patternLine("x x").patternLine("xxx")
                .setGroup("wooden_trade_station")
                .addCriterion("has_planks", hasItem(Blocks.BIRCH_PLANKS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.CRIMSON_TRADE_STATION_ITEM)
                .key('*', Blocks.CRIMSON_HYPHAE).key('x', Blocks.CRIMSON_PLANKS)
                .patternLine("***").patternLine("x x").patternLine("xxx")
                .setGroup("wooden_trade_station")
                .addCriterion("has_planks", hasItem(Blocks.CRIMSON_PLANKS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.DARK_OAK_TRADE_STATION_ITEM)
                .key('*', Blocks.DARK_OAK_WOOD).key('x', Blocks.DARK_OAK_PLANKS)
                .patternLine("***").patternLine("x x").patternLine("xxx")
                .setGroup("wooden_trade_station")
                .addCriterion("has_planks", hasItem(Blocks.DARK_OAK_PLANKS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.JUNGLE_TRADE_STATION_ITEM)
                .key('*', Blocks.JUNGLE_WOOD).key('x', Blocks.JUNGLE_PLANKS)
                .patternLine("***").patternLine("x x").patternLine("xxx")
                .setGroup("wooden_trade_station")
                .addCriterion("has_planks", hasItem(Blocks.JUNGLE_PLANKS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.OAK_TRADE_STATION_ITEM)
                .key('*', Blocks.OAK_WOOD).key('x', Blocks.OAK_PLANKS)
                .patternLine("***").patternLine("x x").patternLine("xxx")
                .setGroup("wooden_trade_station")
                .addCriterion("has_planks", hasItem(Blocks.OAK_PLANKS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.SPRUCE_TRADE_STATION_ITEM)
                .key('*', Blocks.SPRUCE_WOOD).key('x', Blocks.SPRUCE_PLANKS)
                .patternLine("***").patternLine("x x").patternLine("xxx")
                .setGroup("wooden_trade_station")
                .addCriterion("has_planks", hasItem(Blocks.SPRUCE_PLANKS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.WARPED_TRADE_STATION_ITEM)
                .key('*', Blocks.WARPED_HYPHAE).key('x', Blocks.WARPED_PLANKS)
                .patternLine("***").patternLine("x x").patternLine("xxx")
                .setGroup("wooden_trade_station")
                .addCriterion("has_planks", hasItem(Blocks.WARPED_PLANKS)).build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.WHITE_TRADE_STATION_ITEM)
                .key('*', Blocks.WHITE_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.SHULKER_BOX).key('x', Blocks.SMOOTH_STONE_SLAB).key('#', Tags.Items.DUSTS_REDSTONE)
                .patternLine("***").patternLine("-+-").patternLine("x#x")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.ORANGE_TRADE_STATION_ITEM)
                .key('*', Blocks.ORANGE_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.SHULKER_BOX).key('x', Blocks.SMOOTH_STONE_SLAB).key('#', Tags.Items.DUSTS_REDSTONE)
                .patternLine("***").patternLine("-+-").patternLine("x#x")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.MAGENTA_TRADE_STATION_ITEM)
                .key('*', Blocks.MAGENTA_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.SHULKER_BOX).key('x', Blocks.SMOOTH_STONE_SLAB).key('#', Tags.Items.DUSTS_REDSTONE)
                .patternLine("***").patternLine("-+-").patternLine("x#x")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.LIGHT_BLUE_TRADE_STATION_ITEM)
                .key('*', Blocks.LIGHT_BLUE_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.SHULKER_BOX).key('x', Blocks.SMOOTH_STONE_SLAB).key('#', Tags.Items.DUSTS_REDSTONE)
                .patternLine("***").patternLine("-+-").patternLine("x#x")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.YELLOW_TRADE_STATION_ITEM)
                .key('*', Blocks.YELLOW_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.SHULKER_BOX).key('x', Blocks.SMOOTH_STONE_SLAB).key('#', Tags.Items.DUSTS_REDSTONE)
                .patternLine("***").patternLine("-+-").patternLine("x#x")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.LIME_TRADE_STATION_ITEM)
                .key('*', Blocks.LIME_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.SHULKER_BOX).key('x', Blocks.SMOOTH_STONE_SLAB).key('#', Tags.Items.DUSTS_REDSTONE)
                .patternLine("***").patternLine("-+-").patternLine("x#x")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.PINK_TRADE_STATION_ITEM)
                .key('*', Blocks.PINK_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.SHULKER_BOX).key('x', Blocks.SMOOTH_STONE_SLAB).key('#', Tags.Items.DUSTS_REDSTONE)
                .patternLine("***").patternLine("-+-").patternLine("x#x")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.GRAY_TRADE_STATION_ITEM)
                .key('*', Blocks.GRAY_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.SHULKER_BOX).key('x', Blocks.SMOOTH_STONE_SLAB).key('#', Tags.Items.DUSTS_REDSTONE)
                .patternLine("***").patternLine("-+-").patternLine("x#x")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.LIGHT_GRAY_TRADE_STATION_ITEM)
                .key('*', Blocks.LIGHT_GRAY_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.SHULKER_BOX).key('x', Blocks.SMOOTH_STONE_SLAB).key('#', Tags.Items.DUSTS_REDSTONE)
                .patternLine("***").patternLine("-+-").patternLine("x#x")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.CYAN_TRADE_STATION_ITEM)
                .key('*', Blocks.CYAN_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.SHULKER_BOX).key('x', Blocks.SMOOTH_STONE_SLAB).key('#', Tags.Items.DUSTS_REDSTONE)
                .patternLine("***").patternLine("-+-").patternLine("x#x")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.PURPLE_TRADE_STATION_ITEM)
                .key('*', Blocks.PURPLE_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.SHULKER_BOX).key('x', Blocks.SMOOTH_STONE_SLAB).key('#', Tags.Items.DUSTS_REDSTONE)
                .patternLine("***").patternLine("-+-").patternLine("x#x")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.BLUE_TRADE_STATION_ITEM)
                .key('*', Blocks.BLUE_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.SHULKER_BOX).key('x', Blocks.SMOOTH_STONE_SLAB).key('#', Tags.Items.DUSTS_REDSTONE)
                .patternLine("***").patternLine("-+-").patternLine("x#x")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.BROWN_TRADE_STATION_ITEM)
                .key('*', Blocks.BROWN_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.SHULKER_BOX).key('x', Blocks.SMOOTH_STONE_SLAB).key('#', Tags.Items.DUSTS_REDSTONE)
                .patternLine("***").patternLine("-+-").patternLine("x#x")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.GREEN_TRADE_STATION_ITEM)
                .key('*', Blocks.GREEN_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.SHULKER_BOX).key('x', Blocks.SMOOTH_STONE_SLAB).key('#', Tags.Items.DUSTS_REDSTONE)
                .patternLine("***").patternLine("-+-").patternLine("x#x")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.RED_TRADE_STATION_ITEM)
                .key('*', Blocks.RED_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.SHULKER_BOX).key('x', Blocks.SMOOTH_STONE_SLAB).key('#', Tags.Items.DUSTS_REDSTONE)
                .patternLine("***").patternLine("-+-").patternLine("x#x")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.BLACK_TRADE_STATION_ITEM)
                .key('*', Blocks.BLACK_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.SHULKER_BOX).key('x', Blocks.SMOOTH_STONE_SLAB).key('#', Tags.Items.DUSTS_REDSTONE)
                .patternLine("***").patternLine("-+-").patternLine("x#x")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.WHITE_APPRAISAL_TABLE_ITEM)
                .key('*', Blocks.WHITE_CARPET).key('+', Tags.Items.GLASS_PANES_COLORLESS).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine(" + ").patternLine("***").patternLine("xxx")
                .setGroup("appraisal_table")
                .addCriterion("has_pane", hasItem(Tags.Items.GLASS_PANES_COLORLESS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.ORANGE_APPRAISAL_TABLE_ITEM)
                .key('*', Blocks.ORANGE_CARPET).key('+', Tags.Items.GLASS_PANES_COLORLESS).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine(" + ").patternLine("***").patternLine("xxx")
                .setGroup("appraisal_table")
                .addCriterion("has_pane", hasItem(Tags.Items.GLASS_PANES_COLORLESS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.MAGENTA_APPRAISAL_TABLE_ITEM)
                .key('*', Blocks.MAGENTA_CARPET).key('+', Tags.Items.GLASS_PANES_COLORLESS).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine(" + ").patternLine("***").patternLine("xxx")
                .setGroup("appraisal_table")
                .addCriterion("has_pane", hasItem(Tags.Items.GLASS_PANES_COLORLESS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.LIGHT_BLUE_APPRAISAL_TABLE_ITEM)
                .key('*', Blocks.LIGHT_BLUE_CARPET).key('+', Tags.Items.GLASS_PANES_COLORLESS).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine(" + ").patternLine("***").patternLine("xxx")
                .setGroup("appraisal_table")
                .addCriterion("has_pane", hasItem(Tags.Items.GLASS_PANES_COLORLESS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.YELLOW_APPRAISAL_TABLE_ITEM)
                .key('*', Blocks.YELLOW_CARPET).key('+', Tags.Items.GLASS_PANES_COLORLESS).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine(" + ").patternLine("***").patternLine("xxx")
                .setGroup("appraisal_table")
                .addCriterion("has_pane", hasItem(Tags.Items.GLASS_PANES_COLORLESS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.LIME_APPRAISAL_TABLE_ITEM)
                .key('*', Blocks.LIME_CARPET).key('+', Tags.Items.GLASS_PANES_COLORLESS).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine(" + ").patternLine("***").patternLine("xxx")
                .setGroup("appraisal_table")
                .addCriterion("has_pane", hasItem(Tags.Items.GLASS_PANES_COLORLESS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.PINK_APPRAISAL_TABLE_ITEM)
                .key('*', Blocks.PINK_CARPET).key('+', Tags.Items.GLASS_PANES_COLORLESS).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine(" + ").patternLine("***").patternLine("xxx")
                .setGroup("appraisal_table")
                .addCriterion("has_pane", hasItem(Tags.Items.GLASS_PANES_COLORLESS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.GRAY_APPRAISAL_TABLE_ITEM)
                .key('*', Blocks.GRAY_CARPET).key('+', Tags.Items.GLASS_PANES_COLORLESS).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine(" + ").patternLine("***").patternLine("xxx")
                .setGroup("appraisal_table")
                .addCriterion("has_pane", hasItem(Tags.Items.GLASS_PANES_COLORLESS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.LIGHT_GRAY_APPRAISAL_TABLE_ITEM)
                .key('*', Blocks.LIGHT_GRAY_CARPET).key('+', Tags.Items.GLASS_PANES_COLORLESS).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine(" + ").patternLine("***").patternLine("xxx")
                .setGroup("appraisal_table")
                .addCriterion("has_pane", hasItem(Tags.Items.GLASS_PANES_COLORLESS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.CYAN_APPRAISAL_TABLE_ITEM)
                .key('*', Blocks.CYAN_CARPET).key('+', Tags.Items.GLASS_PANES_COLORLESS).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine(" + ").patternLine("***").patternLine("xxx")
                .setGroup("appraisal_table")
                .addCriterion("has_pane", hasItem(Tags.Items.GLASS_PANES_COLORLESS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.PURPLE_APPRAISAL_TABLE_ITEM)
                .key('*', Blocks.PURPLE_CARPET).key('+', Tags.Items.GLASS_PANES_COLORLESS).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine(" + ").patternLine("***").patternLine("xxx")
                .setGroup("appraisal_table")
                .addCriterion("has_pane", hasItem(Tags.Items.GLASS_PANES_COLORLESS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.BLUE_APPRAISAL_TABLE_ITEM)
                .key('*', Blocks.BLUE_CARPET).key('+', Tags.Items.GLASS_PANES_COLORLESS).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine(" + ").patternLine("***").patternLine("xxx")
                .setGroup("appraisal_table")
                .addCriterion("has_pane", hasItem(Tags.Items.GLASS_PANES_COLORLESS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.BROWN_APPRAISAL_TABLE_ITEM)
                .key('*', Blocks.BROWN_CARPET).key('+', Tags.Items.GLASS_PANES_COLORLESS).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine(" + ").patternLine("***").patternLine("xxx")
                .setGroup("appraisal_table")
                .addCriterion("has_pane", hasItem(Tags.Items.GLASS_PANES_COLORLESS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.GREEN_APPRAISAL_TABLE_ITEM)
                .key('*', Blocks.GREEN_CARPET).key('+', Tags.Items.GLASS_PANES_COLORLESS).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine(" + ").patternLine("***").patternLine("xxx")
                .setGroup("appraisal_table")
                .addCriterion("has_pane", hasItem(Tags.Items.GLASS_PANES_COLORLESS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.RED_APPRAISAL_TABLE_ITEM)
                .key('*', Blocks.RED_CARPET).key('+', Tags.Items.GLASS_PANES_COLORLESS).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine(" + ").patternLine("***").patternLine("xxx")
                .setGroup("appraisal_table")
                .addCriterion("has_pane", hasItem(Tags.Items.GLASS_PANES_COLORLESS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.BLACK_APPRAISAL_TABLE_ITEM)
                .key('*', Blocks.BLACK_CARPET).key('+', Tags.Items.GLASS_PANES_COLORLESS).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine(" + ").patternLine("***").patternLine("xxx")
                .setGroup("appraisal_table")
                .addCriterion("has_pane", hasItem(Tags.Items.GLASS_PANES_COLORLESS)).build(consumer);
    }

    @Override
    public String getName()
    {
        return "Barter Recipes";
    }
}

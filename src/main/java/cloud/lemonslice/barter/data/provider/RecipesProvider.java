package cloud.lemonslice.barter.data.provider;

import cloud.lemonslice.barter.common.block.BlocksRegistry;
import cloud.lemonslice.barter.common.item.ItemsRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
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
        ShapedRecipeBuilder.shapedRecipe(ItemsRegistry.TRADE_STATION_SHOPKEEPER_KEY)
                .key('*', Tags.Items.INGOTS_GOLD).key('x', Tags.Items.NUGGETS_GOLD).key('+', Tags.Items.STORAGE_BLOCKS_GOLD)
                .patternLine("+  ").patternLine(" *x").patternLine("  *")
                .setGroup("trade_station_shopkeeper_key")
                .addCriterion("has_gold", hasItem(Tags.Items.STORAGE_BLOCKS_GOLD)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemsRegistry.TRADE_STATION_STAFF_KEY)
                .key('*', Tags.Items.INGOTS_IRON).key('x', Tags.Items.NUGGETS_IRON).key('+', Tags.Items.STORAGE_BLOCKS_IRON)
                .patternLine("+  ").patternLine(" *x").patternLine("  *")
                .setGroup("trade_station_staff_key")
                .addCriterion("has_iron", hasItem(Tags.Items.INGOTS_IRON)).build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.WHITE_TRADE_STATION_ITEM)
                .key('*', Blocks.WHITE_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.TRAPPED_CHEST).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine("***").patternLine("-+-").patternLine("xxx")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.ORANGE_TRADE_STATION_ITEM)
                .key('*', Blocks.ORANGE_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.TRAPPED_CHEST).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine("***").patternLine("-+-").patternLine("xxx")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.MAGENTA_TRADE_STATION_ITEM)
                .key('*', Blocks.MAGENTA_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.TRAPPED_CHEST).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine("***").patternLine("-+-").patternLine("xxx")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.LIGHT_BLUE_TRADE_STATION_ITEM)
                .key('*', Blocks.LIGHT_BLUE_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.TRAPPED_CHEST).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine("***").patternLine("-+-").patternLine("xxx")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.YELLOW_TRADE_STATION_ITEM)
                .key('*', Blocks.YELLOW_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.TRAPPED_CHEST).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine("***").patternLine("-+-").patternLine("xxx")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.LIME_TRADE_STATION_ITEM)
                .key('*', Blocks.LIME_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.TRAPPED_CHEST).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine("***").patternLine("-+-").patternLine("xxx")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.PINK_TRADE_STATION_ITEM)
                .key('*', Blocks.PINK_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.TRAPPED_CHEST).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine("***").patternLine("-+-").patternLine("xxx")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.GRAY_TRADE_STATION_ITEM)
                .key('*', Blocks.GRAY_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.TRAPPED_CHEST).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine("***").patternLine("-+-").patternLine("xxx")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.LIGHT_GRAY_TRADE_STATION_ITEM)
                .key('*', Blocks.LIGHT_GRAY_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.TRAPPED_CHEST).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine("***").patternLine("-+-").patternLine("xxx")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.CYAN_TRADE_STATION_ITEM)
                .key('*', Blocks.CYAN_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.TRAPPED_CHEST).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine("***").patternLine("-+-").patternLine("xxx")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.PURPLE_TRADE_STATION_ITEM)
                .key('*', Blocks.PURPLE_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.TRAPPED_CHEST).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine("***").patternLine("-+-").patternLine("xxx")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.BLUE_TRADE_STATION_ITEM)
                .key('*', Blocks.BLUE_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.TRAPPED_CHEST).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine("***").patternLine("-+-").patternLine("xxx")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.BROWN_TRADE_STATION_ITEM)
                .key('*', Blocks.BROWN_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.TRAPPED_CHEST).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine("***").patternLine("-+-").patternLine("xxx")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.GREEN_TRADE_STATION_ITEM)
                .key('*', Blocks.GREEN_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.TRAPPED_CHEST).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine("***").patternLine("-+-").patternLine("xxx")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.RED_TRADE_STATION_ITEM)
                .key('*', Blocks.RED_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.TRAPPED_CHEST).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine("***").patternLine("-+-").patternLine("xxx")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.BLACK_TRADE_STATION_ITEM)
                .key('*', Blocks.BLACK_CARPET).key('-', Blocks.OBSIDIAN).key('+', Blocks.TRAPPED_CHEST).key('x', Blocks.SMOOTH_STONE_SLAB)
                .patternLine("***").patternLine("-+-").patternLine("xxx")
                .setGroup("trade_station")
                .addCriterion("has_obsidian", hasItem(Blocks.OBSIDIAN)).build(consumer);
    }

    @Override
    public String getName()
    {
        return "Barter Recipes";
    }
}

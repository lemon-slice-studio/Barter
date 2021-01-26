package cloud.lemonslice.barter.data.provider;

import cloud.lemonslice.barter.common.item.ItemsRegistry;
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
        ShapedRecipeBuilder.shapedRecipe(ItemsRegistry.TRADE_STATION_SHOPKEEPER_KEY).key('*', Tags.Items.INGOTS_GOLD).key('x', Tags.Items.NUGGETS_GOLD).patternLine("*  ").patternLine(" x ").patternLine("  x").setGroup("trade_station_shopkeeper_key").addCriterion("has_gold", hasItem(Tags.Items.INGOTS_GOLD)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemsRegistry.TRADE_STATION_STAFF_KEY).key('*', Tags.Items.INGOTS_IRON).key('x', Tags.Items.NUGGETS_IRON).patternLine("*  ").patternLine(" x ").patternLine("  x").setGroup("trade_station_staff_key").addCriterion("has_iron", hasItem(Tags.Items.INGOTS_IRON)).build(consumer);
    }

    @Override
    public String getName()
    {
        return "Barter Recipes";
    }
}

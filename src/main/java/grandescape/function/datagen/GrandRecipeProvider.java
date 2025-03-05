package grandescape.function.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import grandescape.world_items.item.GrandItems;

import java.util.concurrent.CompletableFuture;

public class GrandRecipeProvider extends RecipeProvider {

    public GrandRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        smeltingResultFromBase(recipeOutput, Items.DIAMOND, GrandItems.FROZEN_DIAMOND.get());
    }
}

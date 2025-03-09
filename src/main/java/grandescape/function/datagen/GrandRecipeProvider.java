package grandescape.function.datagen;

import grandescape.world_items.block.GrandBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import grandescape.world_items.item.GrandItems;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class GrandRecipeProvider extends RecipeProvider {

    public GrandRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        smeltingResultFromBase(recipeOutput, Items.DIAMOND, GrandItems.FROZEN_DIAMOND.get());
        smeltingResultFromBase(recipeOutput, Blocks.RAW_IRON_BLOCK, GrandBlocks.CANNON_BALL_PILE.get());
    }
}

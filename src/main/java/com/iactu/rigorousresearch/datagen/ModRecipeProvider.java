package com.iactu.rigorousresearch.datagen;

import com.iactu.rigorousresearch.RigorousResearchMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
//        ItemLike ingredient = Items.PAINTING;
//        ItemLike result = Items.PAINTING;
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result)
//                .requires(ingredient)
//                .unlockedBy("criteria", has(ingredient))
//                .save(
//                        pRecipeOutput,
//                        recipeId("clear_painting_variant")
//                );
    }

    private ResourceLocation recipeId(String path) {
        return ResourceLocation.fromNamespaceAndPath(RigorousResearchMod.MOD_ID, path);
    }
}

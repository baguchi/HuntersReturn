package baguchan.hunters_return.data;

import baguchan.hunters_return.init.HunterItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class CraftingGenerator extends RecipeProvider {
    public CraftingGenerator(PackOutput generator, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(generator, completableFuture);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HunterItems.BOOMERANG, 1)
                .pattern(" B")
                .pattern("BL")
                .pattern(" B")
                .define('B', ItemTags.PLANKS)
                .define('L', Tags.Items.LEATHERS)
                .unlockedBy("has_item", has(Tags.Items.LEATHERS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HunterItems.MINI_CROSSBOW, 1)
                .pattern("RIR")
                .pattern("SIS")
                .pattern(" R ")
                .define('I', Items.IRON_NUGGET)
                .define('R', Tags.Items.RODS_WOODEN)
                .define('S', Tags.Items.STRINGS)
                .unlockedBy("has_item", has(Items.IRON_NUGGET)).save(consumer);

    }
}

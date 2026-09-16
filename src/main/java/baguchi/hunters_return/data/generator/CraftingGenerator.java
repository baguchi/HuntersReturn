package baguchi.hunters_return.data.generator;

import baguchi.hunters_return.init.HunterItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.common.Tags;

public class CraftingGenerator extends RecipeProvider {
    public CraftingGenerator(final BootstrapContext<Recipe<?>> recipeOutput, final BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }
    @Override
    protected void buildRecipes() {

        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.COMBAT, HunterItems.BOOMERANG, 1)
                .pattern(" B")
                .pattern("BL")
                .pattern(" B")
                .define('B', ItemTags.PLANKS)
                .define('L', Tags.Items.LEATHERS)
                .unlockedBy("has_item", has(Tags.Items.LEATHERS)).save(this.output);
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.COMBAT, HunterItems.MINI_CROSSBOW, 1)
                .pattern("RIR")
                .pattern("SIS")
                .pattern(" R ")
                .define('I', Items.IRON_NUGGET)
                .define('R', Tags.Items.RODS_WOODEN)
                .define('S', Tags.Items.STRINGS)
                .unlockedBy("has_item", has(Items.IRON_NUGGET)).save(this.output);

    }
}

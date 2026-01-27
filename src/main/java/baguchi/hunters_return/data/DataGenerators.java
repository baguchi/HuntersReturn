package baguchi.hunters_return.data;

import baguchi.hunters_return.HuntersReturn;
import baguchi.hunters_return.data.generator.CraftingGenerator;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = HuntersReturn.MODID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        RegistryDataGenerator registryDataGenerator = new RegistryDataGenerator(packOutput, event.getLookupProvider());

        event.getGenerator().addProvider(true, registryDataGenerator);
        event.getGenerator().addProvider(true, new EnchantmentTagProvider(packOutput, registryDataGenerator.getRegistryProvider()));
        BlockTagsProvider blocktags = new BlockTagGenerator(packOutput, registryDataGenerator.getRegistryProvider());
        event.getGenerator().addProvider(true, blocktags);
        event.getGenerator().addProvider(true, new ItemTagGenerator(packOutput, registryDataGenerator.getRegistryProvider()));
        event.getGenerator().addProvider(true, new EntityTagGenerator(packOutput, registryDataGenerator.getRegistryProvider()));
        event.getGenerator().addProvider(true, new BiomeTagGenerator(packOutput, registryDataGenerator.getRegistryProvider()));
        event.getGenerator().addProvider(true, new Runner(packOutput, registryDataGenerator.getRegistryProvider()));
    }

    public static final class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider lookupProvider, RecipeOutput output) {
            return new CraftingGenerator(lookupProvider, output);
        }

        @Override
        public String getName() {
            return HuntersReturn.MODID + "recipes";
        }
    }
}
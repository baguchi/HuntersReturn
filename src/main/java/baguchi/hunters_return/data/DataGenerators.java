package baguchi.hunters_return.data;

import baguchi.hunters_return.HuntersReturn;
import baguchi.hunters_return.data.generator.CraftingGenerator;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = HuntersReturn.MODID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        event.createWorldRegistryObjects(RegistryDataGenerator.BUILDER);
        CompletableFuture<HolderLookup.Provider> completableFuture = event.getWorldLookupProvider();

        event.getGenerator().addProvider(true, new EnchantmentTagProvider(packOutput, completableFuture));
        BlockTagsProvider blocktags = new BlockTagGenerator(packOutput, completableFuture);
        event.getGenerator().addProvider(true, blocktags);
        event.getGenerator().addProvider(true, new ItemTagGenerator(packOutput, completableFuture));
        event.getGenerator().addProvider(true, new EntityTagGenerator(packOutput, completableFuture));
        event.getGenerator().addProvider(true, new BiomeTagGenerator(packOutput, completableFuture));
        event.createReloadableRegistryObjects(
                new RegistrySetBuilder()
                        .add(RecipeProvider.asBootstrap(CraftingGenerator::new)),
                Set.of("minecraft", HuntersReturn.MODID));
    }
}
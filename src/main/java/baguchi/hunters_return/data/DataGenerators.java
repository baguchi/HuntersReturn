package baguchi.hunters_return.data;

import baguchi.hunters_return.HuntersReturn;
import baguchi.hunters_return.data.generator.CraftingGenerator;
import baguchi.hunters_return.data.resources.registries.HunterVariants;
import baguchi.hunters_return.init.HunterEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.data.event.GatherDataRegistryEntriesEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = HuntersReturn.MODID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> completableFuture = event.getWorldLookupProvider();

        event.getGenerator().addProvider(true, new EnchantmentTagProvider(packOutput, completableFuture));
        BlockTagsProvider blocktags = new BlockTagGenerator(packOutput, completableFuture);
        event.getGenerator().addProvider(true, blocktags);
        event.getGenerator().addProvider(true, new ItemTagGenerator(packOutput, completableFuture));
        event.getGenerator().addProvider(true, new EntityTagGenerator(packOutput, completableFuture));
        event.getGenerator().addProvider(true, new BiomeTagGenerator(packOutput, completableFuture));
    }

    @SubscribeEvent
    public static void gatherRegistry(GatherDataRegistryEntriesEvent event) {
        event.add(Registries.ENCHANTMENT, HunterEnchantments::bootstrap)
                .add(HunterVariants.HUNTER_VARIANT_KEY, HunterVariants::bootstrap)
                .add(RecipeProvider.asBootstrap(CraftingGenerator::new));
    }
}
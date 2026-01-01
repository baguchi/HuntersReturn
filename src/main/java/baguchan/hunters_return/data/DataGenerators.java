package baguchan.hunters_return.data;

import baguchan.hunters_return.HuntersReturn;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = HuntersReturn.MODID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        event.getGenerator().addProvider(true, new RegistryDataGenerator(packOutput, lookupProvider));
        //event.getGenerator().addProvider(true, new EnchantTagGenerator(packOutput, lookupProvider, event.getExistingFileHelper()));
        BlockTagsProvider blocktags = new BlockTagGenerator(packOutput, lookupProvider, existingFileHelper);
        event.getGenerator().addProvider(true, blocktags);
        event.getGenerator().addProvider(true, new ItemTagGenerator(packOutput, lookupProvider, blocktags.contentsGetter(), existingFileHelper));
        event.getGenerator().addProvider(true, new EntityTagGenerator(packOutput, lookupProvider, existingFileHelper));
        event.getGenerator().addProvider(true, new BiomeTagGenerator(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new CraftingGenerator(packOutput, lookupProvider));
    }

}
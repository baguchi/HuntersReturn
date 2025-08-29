package baguchi.hunters_return.data;

import baguchi.hunters_return.HuntersReturn;
import baguchi.hunters_return.data.resources.registries.HunterVariants;
import baguchi.hunters_return.init.HunterEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class RegistryDataGenerator extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, HunterEnchantments::bootstrap)
            .add(HunterVariants.HUNTER_VARIANT_KEY, HunterVariants::bootstrap);


    public RegistryDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of("minecraft", HuntersReturn.MODID));
    }
}
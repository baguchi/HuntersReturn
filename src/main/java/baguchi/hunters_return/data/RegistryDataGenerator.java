package baguchi.hunters_return.data;

import baguchi.hunters_return.data.resources.registries.HunterVariants;
import baguchi.hunters_return.init.HunterEnchantments;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class RegistryDataGenerator {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, HunterEnchantments::bootstrap)
            .add(HunterVariants.HUNTER_VARIANT_KEY, HunterVariants::bootstrap);

}
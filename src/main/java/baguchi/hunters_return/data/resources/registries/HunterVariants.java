package baguchi.hunters_return.data.resources.registries;

import baguchi.hunters_return.HuntersReturn;
import baguchi.hunters_return.api.HunterVariant;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.Tags;

import java.util.Optional;

public class HunterVariants {
    public static final ResourceKey<Registry<HunterVariant>> HUNTER_VARIANT_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(HuntersReturn.MODID, "hunter_variant"));

    public static final ResourceKey<HunterVariant> NORMAL = createKey("normal");
    public static final ResourceKey<HunterVariant> COLD = createKey("cold");
    public static final ResourceKey<HunterVariant> DEFAULT = NORMAL;

    private static ResourceKey<HunterVariant> createKey(String name) {
        return ResourceKey.create(HUNTER_VARIANT_KEY, ResourceLocation.fromNamespaceAndPath(HuntersReturn.MODID, name));
    }

    static void register(BootstrapContext<HunterVariant> context, ResourceKey<HunterVariant> key, String name, ResourceKey<Biome> biomeResourceKey) {
        register(context, key, name, HolderSet.direct(context.lookup(Registries.BIOME).getOrThrow(biomeResourceKey)));
    }

    static void register(BootstrapContext<HunterVariant> context, ResourceKey<HunterVariant> key, String name, TagKey<Biome> biomeTag) {
        register(context, key, name, context.lookup(Registries.BIOME).getOrThrow(biomeTag));
    }

    static void register(BootstrapContext<HunterVariant> context, ResourceKey<HunterVariant> key, String name, HolderSet<Biome> biomeHolderSet) {
        ResourceLocation resourcelocation = HuntersReturn.locate("entity/hunter/" + name);
        ResourceLocation resourcelocation2 = HuntersReturn.locate("entity/hunter/" + name + "_old");
        context.register(key, new HunterVariant(resourcelocation, Optional.of(resourcelocation2), biomeHolderSet));
    }

    static void registerWithoutOld(BootstrapContext<HunterVariant> context, ResourceKey<HunterVariant> key, String name, HolderSet<Biome> biomeHolderSet) {
        ResourceLocation resourcelocation = HuntersReturn.locate("entity/hunter/" + name);
        context.register(key, new HunterVariant(resourcelocation, Optional.empty(), biomeHolderSet));
    }

    public static Holder<HunterVariant> getSpawnVariant(RegistryAccess p_332694_, Holder<Biome> p_332773_) {
        Registry<HunterVariant> registry = p_332694_.lookupOrThrow(HUNTER_VARIANT_KEY);
        return registry.listElements()
                .filter(p_332674_ -> p_332674_.value().biomes().contains(p_332773_))
                .findFirst()
                .or(() -> registry.get(DEFAULT))
                .or(registry::getAny)
                .orElseThrow();
    }

    public static void bootstrap(BootstrapContext<HunterVariant> context) {
        register(context, NORMAL, "normal", HolderSet.empty());
        register(context, COLD, "cold", Tags.Biomes.IS_COLD_OVERWORLD);
    }
}
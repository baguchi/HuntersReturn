package baguchan.hunterillager.event;

import baguchan.hunterillager.HunterIllager;
import baguchan.hunterillager.init.HunterStructureRegister;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = HunterIllager.MODID)
public class WorldLoadEvent {
	// ============================================================================================================================================================================
	// it comes from TelepathicGrunt's Structure Tutorial
	// https://github.com/TelepathicGrunt/StructureTutorialMod/tree/1.18.x-Forge-Jigsaw/
	// ============================================================================================================================================================================


	private static Method GETCODEC_METHOD;

	@SubscribeEvent
	public static void addDimensionalSpacing(final WorldEvent.Load event) {
		if (event.getWorld() instanceof ServerLevel serverLevel) {
			ChunkGenerator chunkGenerator = serverLevel.getChunkSource().getGenerator();
			// Skip superflat to prevent issues with it. Plus, users don't want structures clogging up their superflat worlds.
			if (chunkGenerator instanceof FlatLevelSource && serverLevel.dimension().equals(Level.OVERWORLD)) {
				return;
			}

			StructureSettings worldStructureConfig = chunkGenerator.getSettings();

			// Create a mutable map we will use for easier adding to biomes
			HashMap<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> structureToMultiMap = new HashMap<>();

			// Add the resourcekey of all biomes that this Configured Structure can spawn in.
			for (Map.Entry<ResourceKey<Biome>, Biome> biomeEntry : serverLevel.registryAccess().ownedRegistryOrThrow(Registry.BIOME_REGISTRY).entrySet()) {
				// This space is add structure in biome
				// You can do checks for other traits that the biome has.
				Biome.BiomeCategory biomeCategory = biomeEntry.getValue().getBiomeCategory();
				if (BiomeDictionary.hasType(biomeEntry.getKey(), BiomeDictionary.Type.OVERWORLD) && (BiomeDictionary.hasType(biomeEntry.getKey(), BiomeDictionary.Type.FOREST) || BiomeDictionary.hasType(biomeEntry.getKey(), BiomeDictionary.Type.PLAINS)) && !BiomeDictionary.hasType(biomeEntry.getKey(), BiomeDictionary.Type.UNDERGROUND) && !BiomeDictionary.hasType(biomeEntry.getKey(), BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(biomeEntry.getKey(), BiomeDictionary.Type.RIVER) && !BiomeDictionary.hasType(biomeEntry.getKey(), BiomeDictionary.Type.MOUNTAIN) && !BiomeDictionary.hasType(biomeEntry.getKey(), BiomeDictionary.Type.HILLS) && biomeCategory != Biome.BiomeCategory.THEEND && biomeCategory != Biome.BiomeCategory.NETHER && biomeCategory != Biome.BiomeCategory.NONE) {
					associateBiomeToConfiguredStructure(structureToMultiMap, HunterStructureRegister.HUNTER_HOUSE_FEATURE, biomeEntry.getKey());
				}
			}
			// Requires AccessTransformer  (see resources/META-INF/accesstransformer.cfg)
			ImmutableMap.Builder<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> tempStructureToMultiMap = ImmutableMap.builder();
			worldStructureConfig.configuredStructures.entrySet().stream().filter(entry -> !structureToMultiMap.containsKey(entry.getKey())).forEach(tempStructureToMultiMap::put);

			// Add our structures to the structure map/multimap and set the world to use this combined map/multimap.
			structureToMultiMap.forEach((key, value) -> tempStructureToMultiMap.put(key, ImmutableMultimap.copyOf(value)));

			// Requires AccessTransformer  (see resources/META-INF/accesstransformer.cfg)
			worldStructureConfig.configuredStructures = tempStructureToMultiMap.build();


			try {
				if (GETCODEC_METHOD == null)
					GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "codec");
				ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(chunkGenerator));
				if (cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
			} catch (Exception e) {
				HunterIllager.LOGGER.error("Was unable to check if " + serverLevel.dimension().location() + " is using Terraforged's ChunkGenerator.");
			}

			/*
			 * Prevent spawning our structure in Vanilla's superflat world as
			 * people seem to want their superflat worlds free of modded structures.
			 * Also that vanilla superflat is really tricky and buggy to work with in my experience.
			 */
			if (chunkGenerator instanceof FlatLevelSource &&
					serverLevel.dimension().equals(Level.OVERWORLD)) {
				return;
			}

			Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(worldStructureConfig.structureConfig());
			tempMap.putIfAbsent(HunterStructureRegister.HUNTER_HOUSE, StructureSettings.DEFAULTS.get(HunterStructureRegister.HUNTER_HOUSE));
			worldStructureConfig.structureConfig = tempMap;
		}
	}

	/**
	 * Helper method that handles setting up the map to multimap relationship to help prevent issues.
	 */
	private static void associateBiomeToConfiguredStructure(Map<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> structureToMultiMap, ConfiguredStructureFeature<?, ?> configuredStructureFeature, ResourceKey<Biome> biomeRegistryKey) {
		structureToMultiMap.putIfAbsent(configuredStructureFeature.feature, HashMultimap.create());
		HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> configuredStructureToBiomeMultiMap = structureToMultiMap.get(configuredStructureFeature.feature);
		if (configuredStructureToBiomeMultiMap.containsValue(biomeRegistryKey)) {
			HunterIllager.LOGGER.error("""
							    Detected 2 ConfiguredStructureFeatures that share the same base StructureFeature trying to be added to same biome. One will be prevented from spawning.
							    This issue happens with vanilla too and is why a Snowy Village and Plains Village cannot spawn in the same biome because they both use the Village base structure.
							    The two conflicting ConfiguredStructures are: {}, {}
							    The biome that is attempting to be shared: {}
							""",
					BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureFeature),
					BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureToBiomeMultiMap.entries().stream().filter(e -> e.getValue() == biomeRegistryKey).findFirst().get().getKey()),
					biomeRegistryKey
			);
		} else {
			configuredStructureToBiomeMultiMap.put(configuredStructureFeature, biomeRegistryKey);
		}
	}
}

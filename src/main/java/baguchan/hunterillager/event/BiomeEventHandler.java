package baguchan.hunterillager.event;

import baguchan.hunterillager.HunterIllager;
import baguchan.hunterillager.structure.StructureRegister;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HunterIllager.MODID)
public class BiomeEventHandler {
	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void addSpawn(BiomeLoadingEvent event) {
		BiomeGenerationSettingsBuilder generation = event.getGeneration();
		if (event.getName() != null) {
			RegistryKey<Biome> biome = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());

			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD) && (
					BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST)) || BiomeDictionary.hasType(biome, BiomeDictionary.Type.PLAINS)) {
				generation.addStructureStart(StructureRegister.HUNTER_HOUSE);
			}
		}
	}
}
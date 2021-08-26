package baguchan.hunterillager.event;

import baguchan.hunterillager.HunterIllager;
import baguchan.hunterillager.init.HunterStructureRegister;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
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
			ResourceKey<Biome> biome = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());

			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD) && (
					BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST)) || BiomeDictionary.hasType(biome, BiomeDictionary.Type.PLAINS)) {
				generation.addStructureStart(HunterStructureRegister.HUNTER_HOUSE_FEATURE);
			}
		}
	}
}
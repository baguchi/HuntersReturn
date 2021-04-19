package baguchan.hunterillager.event;

import baguchan.hunterillager.HunterIllager;
import baguchan.hunterillager.structure.StructureRegister;
import net.minecraft.world.biome.Biome;
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
		if ((event.getName().getNamespace().contains("minecraft") || event.getName().getNamespace().contains("biomesoplenty")) && (
				event.getCategory() == Biome.Category.FOREST || event.getCategory() == Biome.Category.PLAINS)) {
			generation.addStructureStart(StructureRegister.HUNTER_HOUSE);
		}
	}
}
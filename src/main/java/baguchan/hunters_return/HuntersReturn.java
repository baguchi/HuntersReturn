package baguchan.hunters_return;

import baguchan.hunters_return.init.HunterEnchantments;
import baguchan.hunters_return.init.HunterEntityRegistry;
import baguchan.hunters_return.init.HunterItems;
import baguchan.hunters_return.init.HunterSounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(baguchan.hunters_return.HuntersReturn.MODID)
public class HuntersReturn {
	public static final String MODID = "hunters_return";
	public static final Logger LOGGER = LogManager.getLogger();

	public HuntersReturn(IEventBus modEventBus) {
		// Register the setup method for modloading
		modEventBus.addListener(this::setup);
		HunterEntityRegistry.ENTITIES_REGISTRY.register(modEventBus);
		HunterItems.ITEM_REGISTRY.register(modEventBus);
		HunterSounds.SOUND_EVENTS.register(modEventBus);
		HunterEnchantments.DEFERRED_REGISTRY_ENCHANTMET.register(modEventBus);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HunterConfig.COMMON_SPEC);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, HunterConfig.CLIENT_SPEC);

		// Register ourselves for server and other game events we are interested in
		NeoForge.EVENT_BUS.addListener(this::serverStart);
	}

	private void setup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			Raid.RaiderType.create("hunters_return", HunterEntityRegistry.HUNTERILLAGER.get(), new int[]{0, 1, 2, 3, 2, 2, 2, 3});
			SpawnPlacements.register(HunterEntityRegistry.HUNTERILLAGER.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		});
	}

	public static ResourceLocation locate(String path) {
		return new ResourceLocation(baguchan.hunters_return.HuntersReturn.MODID, path);
	}


	private void serverStart(final ServerAboutToStartEvent event) {
	}
}

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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(baguchan.hunters_return.HuntersReturn.MODID)
public class HuntersReturn {
	public static final String MODID = "hunters_return";
	public static final Logger LOGGER = LogManager.getLogger();

	public HuntersReturn() {
		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		// Register the enqueueIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		// Register the processIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
		// Register the doClientStuff method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		HunterEntityRegistry.ENTITIES_REGISTRY.register(bus);
		HunterItems.ITEM_REGISTRY.register(bus);
		HunterSounds.SOUND_EVENTS.register(bus);
		HunterEnchantments.DEFERRED_REGISTRY_ENCHANTMET.register(bus);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HunterConfig.COMMON_SPEC);
		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.addListener(this::serverStart);
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			Raid.RaiderType.create("hunters_return", HunterEntityRegistry.HUNTERILLAGER.get(), new int[]{0, 1, 2, 3, 2, 2, 2, 3});
			SpawnPlacements.register(HunterEntityRegistry.HUNTERILLAGER.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		});
	}

	private void doClientStuff(FMLClientSetupEvent event) {
	}

	private void enqueueIMC(final InterModEnqueueEvent event) {
	}

	private void processIMC(final InterModProcessEvent event) {
	}

	public static ResourceLocation locate(String path) {
		return new ResourceLocation(baguchan.hunters_return.HuntersReturn.MODID, path);
	}


	private void serverStart(final ServerAboutToStartEvent event) {
	}
}

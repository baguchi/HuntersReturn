package baguchan.hunterillager.init;

import baguchan.hunterillager.HunterIllager;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = HunterIllager.MODID)
public class ModCreativeTabEvents {
	@SubscribeEvent
	public static void registerCreativeTab(CreativeModeTabEvent.BuildContents event) {
		event.registerSimple(CreativeModeTabs.COMBAT, HunterItems.BOOMERANG.get());
		event.registerSimple(CreativeModeTabs.SPAWN_EGGS, HunterItems.SPAWNEGG_HUNTERILLAGER.get());
	}
}

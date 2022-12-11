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
		if (event.getTab() == CreativeModeTabs.COMBAT) {
			event.accept(HunterItems.BOOMERANG.get());
		}
		if (event.getTab() == CreativeModeTabs.SPAWN_EGGS) {
			event.accept(HunterItems.SPAWNEGG_HUNTERILLAGER.get());
		}
	}
}

package baguchan.hunters_return.init;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = baguchan.hunters_return.HuntersReturn.MODID)
public class ModCreativeTabEvents {
	@SubscribeEvent
	public static void registerCreativeTab(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.COMBAT) {
			event.accept(HunterItems.BOOMERANG.get());
		}
		if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			event.accept(HunterItems.SPAWNEGG_HUNTER.get());
		}
	}
}
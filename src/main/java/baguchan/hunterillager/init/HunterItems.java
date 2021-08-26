package baguchan.hunterillager.init;

import baguchan.hunterillager.HunterIllager;
import baguchan.hunterillager.item.BoomerangItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = HunterIllager.MODID, bus = EventBusSubscriber.Bus.MOD)
public class HunterItems {
	public static final Item SPAWNEGG_HUNTERILLAGER = new SpawnEggItem(HunterEntityRegistry.HUNTERILLAGER, 9804699, 5777447, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC));

	public static final Item BOOMERANG = new BoomerangItem((new Item.Properties()).tab(CreativeModeTab.TAB_COMBAT).durability(384));

	public static void register(IForgeRegistry<Item> registry, Item item, String id) {
		item.setRegistryName(new ResourceLocation("hunterillager", id));
		registry.register(item);
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> registry) {
		register(registry.getRegistry(), SPAWNEGG_HUNTERILLAGER, "spawnegg_hunterillager");
		register(registry.getRegistry(), BOOMERANG, "boomerang");
	}
}

package baguchan.hunterillager.init;

import baguchan.hunterillager.item.BoomerangItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = "hunterillager", bus = EventBusSubscriber.Bus.MOD)
public class HunterItems {
	public static final Item SPAWNEGG_HUNTERILLAGER = new SpawnEggItem(HunterEntityRegistry.HUNTERILLAGER, 9804699, 5777447, (new Item.Properties()).tab(ItemGroup.TAB_MISC));

	public static final Item BOOMERANG = new BoomerangItem((new Item.Properties()).tab(ItemGroup.TAB_COMBAT).durability(384));

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

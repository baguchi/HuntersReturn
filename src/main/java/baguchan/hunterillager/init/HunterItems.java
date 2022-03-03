package baguchan.hunterillager.init;

import baguchan.hunterillager.HunterIllager;
import baguchan.hunterillager.item.BoomerangItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = HunterIllager.MODID, bus = EventBusSubscriber.Bus.MOD)
public class HunterItems {
	public static final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, HunterIllager.MODID);

	public static final RegistryObject<Item> SPAWNEGG_HUNTERILLAGER = ITEM_REGISTRY.register("spawnegg_hunterillager", () -> new ForgeSpawnEggItem(HunterEntityRegistry.HUNTERILLAGER, 9804699, 5777447, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));

	public static final RegistryObject<Item> BOOMERANG = ITEM_REGISTRY.register("boomerang", () -> new BoomerangItem((new Item.Properties()).tab(CreativeModeTab.TAB_COMBAT).durability(384)));
}

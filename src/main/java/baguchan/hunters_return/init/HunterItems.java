package baguchan.hunters_return.init;

import baguchan.hunters_return.item.BoomerangItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.neoforge.registries.RegistryObject;

public class HunterItems {
	public static final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, baguchan.hunters_return.HuntersReturn.MODID);

	public static final RegistryObject<Item> SPAWNEGG_HUNTER = ITEM_REGISTRY.register("spawnegg_hunter", () -> new DeferredSpawnEggItem(HunterEntityRegistry.HUNTERILLAGER, 9804699, 5777447, (new Item.Properties())));
	public static final RegistryObject<Item> BOOMERANG = ITEM_REGISTRY.register("boomerang", () -> new BoomerangItem((new Item.Properties()).durability(384)));

}

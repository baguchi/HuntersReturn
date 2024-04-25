package baguchan.hunters_return.init;

import baguchan.hunters_return.item.BoomerangItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class HunterItems {
	public static final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(BuiltInRegistries.ITEM, baguchan.hunters_return.HuntersReturn.MODID);

	public static final Supplier<DeferredSpawnEggItem> SPAWNEGG_HUNTER = ITEM_REGISTRY.register("spawnegg_hunter", () -> new DeferredSpawnEggItem(HunterEntityRegistry.HUNTERILLAGER, 9804699, 5777447, (new Item.Properties())));
	public static final Supplier<Item> BOOMERANG = ITEM_REGISTRY.register("boomerang", () -> new BoomerangItem((new Item.Properties()).durability(384)));

}

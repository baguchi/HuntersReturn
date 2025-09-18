package baguchi.hunters_return.init;

import baguchi.hunters_return.HuntersReturn;
import baguchi.hunters_return.item.BoomerangItem;
import baguchi.hunters_return.item.MiniCrossbowItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HunterItems {
	public static final DeferredRegister.Items ITEM_REGISTRY = DeferredRegister.createItems(baguchi.hunters_return.HuntersReturn.MODID);

    public static final DeferredItem<SpawnEggItem> SPAWNEGG_HUNTER = ITEM_REGISTRY.register("hunter_spawn_egg", () -> new SpawnEggItem((new Item.Properties().spawnEgg(HunterEntityRegistry.HUNTERILLAGER.get()).setId(prefix("hunter_spawn_egg")))));
	public static final DeferredItem<Item> BOOMERANG = ITEM_REGISTRY.register("boomerang", () -> new BoomerangItem((new Item.Properties().enchantable(2).setId(prefix("boomerang"))).durability(384)));
	public static final DeferredItem<Item> MINI_CROSSBOW = ITEM_REGISTRY.register("mini_crossbow", () -> new MiniCrossbowItem((new Item.Properties().setId(prefix("mini_crossbow"))).durability(412).component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY).enchantable(2).stacksTo(1)));

	private static ResourceKey<Item> prefix(String path) {
		return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(HuntersReturn.MODID, path));
	}
}

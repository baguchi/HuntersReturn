package baguchi.hunters_return.init;

import baguchi.hunters_return.item.BoomerangItem;
import baguchi.hunters_return.item.MiniCrossbowItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.component.UseEffects;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HunterItems {
	public static final DeferredRegister.Items ITEM_REGISTRY = DeferredRegister.createItems(baguchi.hunters_return.HuntersReturn.MODID);

    public static final DeferredItem<SpawnEggItem> HUNTER_SPAWN_EGG = ITEM_REGISTRY.registerItem("hunter_spawn_egg", (properties) -> new SpawnEggItem((properties.spawnEgg(HunterEntityRegistry.HUNTERILLAGER.get()))));
    public static final DeferredItem<Item> BOOMERANG = ITEM_REGISTRY.registerItem("boomerang", (properties) -> new BoomerangItem((properties.enchantable(2).component(DataComponents.USE_EFFECTS, new UseEffects(true, false, 0.8F))).durability(384)));
    public static final DeferredItem<Item> MINI_CROSSBOW = ITEM_REGISTRY.registerItem("mini_crossbow", (properties) -> new MiniCrossbowItem(properties.durability(412).component(DataComponents.USE_EFFECTS, new UseEffects(true, true, 1.0F)).component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY).enchantable(2).stacksTo(1)));

}

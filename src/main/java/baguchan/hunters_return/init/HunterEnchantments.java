package baguchan.hunters_return.init;

import baguchan.hunters_return.enchantment.BounceEnchantment;
import baguchan.hunters_return.item.BoomerangItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class HunterEnchantments {
	public static final DeferredRegister<Enchantment> DEFERRED_REGISTRY_ENCHANTMET = DeferredRegister.create(Registries.ENCHANTMENT, baguchan.hunters_return.HuntersReturn.MODID);


	public static final EnchantmentCategory BOOMERANG = EnchantmentCategory.create("boomerang", (item) -> {
		return item instanceof BoomerangItem;
	});

	public static final Supplier<Enchantment> BOUNCE = DEFERRED_REGISTRY_ENCHANTMET.register("bounce", () -> new BounceEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.MAINHAND));

}

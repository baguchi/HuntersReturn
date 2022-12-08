package baguchan.hunterillager.init;

import baguchan.hunterillager.HunterIllager;
import baguchan.hunterillager.enchantment.BounceEnchantment;
import baguchan.hunterillager.item.BoomerangItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class HunterEnchantments {
	public static final DeferredRegister<Enchantment> DEFERRED_REGISTRY_ENCHANTMET = DeferredRegister.create(Registries.ENCHANTMENT, HunterIllager.MODID);


	public static final EnchantmentCategory BOOMERANG = EnchantmentCategory.create("boomerang", (item) -> {
		return item instanceof BoomerangItem;
	});

	public static final RegistryObject<Enchantment> BOUNCE = DEFERRED_REGISTRY_ENCHANTMET.register("bounce", () -> new BounceEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.MAINHAND));

}

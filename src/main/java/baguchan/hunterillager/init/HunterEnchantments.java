package baguchan.hunterillager.init;

import baguchan.hunterillager.HunterIllager;
import baguchan.hunterillager.enchantment.BounceEnchantment;
import baguchan.hunterillager.item.BoomerangItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HunterIllager.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HunterEnchantments {
	public static final EnchantmentType BOOMERANG = EnchantmentType.create("boomerang", (item) -> {
		return item instanceof BoomerangItem;
	});

	public static final Enchantment BOUNCE = new BounceEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlotType.MAINHAND);

	@SubscribeEvent
	public static void registerEntity(RegistryEvent.Register<Enchantment> event) {
		event.getRegistry().register(BOUNCE.setRegistryName("bounce"));
	}
}

package baguchan.hunters_return.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class HunterEnchantments {
	public static final DeferredRegister<Enchantment> DEFERRED_REGISTRY_ENCHANTMET = DeferredRegister.create(Registries.ENCHANTMENT, baguchan.hunters_return.HuntersReturn.MODID);


    public static final Supplier<Enchantment> BOUNCE = DEFERRED_REGISTRY_ENCHANTMET.register("bounce", () -> new Enchantment(
            Enchantment.definition(
                    ModItemTags.BOOMERANG_ENCHANTABLE, 5, 3, Enchantment.dynamicCost(1, 10), Enchantment.dynamicCost(51, 10), 1, EquipmentSlot.MAINHAND
            )
    ));

}

package baguchan.hunterillager.enchantment;

import baguchan.hunterillager.init.HunterEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class BounceEnchantment extends Enchantment {
	public BounceEnchantment(Enchantment.Rarity p_i50019_1_, EquipmentSlot... p_i50019_2_) {
		super(p_i50019_1_, HunterEnchantments.BOOMERANG, p_i50019_2_);
	}

	public int getMinCost(int p_77321_1_) {
		return 1 + (p_77321_1_ - 1) * 10;
	}

	public int getMaxCost(int p_223551_1_) {
		return 50;
	}

	public int getMaxLevel() {
		return 3;
	}

	/*public boolean checkCompatibility(Enchantment p_77326_1_) {
		return super.checkCompatibility(p_77326_1_) && p_77326_1_ != Enchantments.MULTISHOT;
	}*/
}
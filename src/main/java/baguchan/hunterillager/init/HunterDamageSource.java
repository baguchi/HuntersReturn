package baguchan.hunterillager.init;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

public class HunterDamageSource {
	public static DamageSource boomerangAttack(Entity p_19362_, @Nullable Entity p_19363_) {
		return (new IndirectEntityDamageSource("hunterillager.boomerang", p_19362_, p_19363_)).setProjectile();
	}
}

package baguchan.hunterillager.init;

import baguchan.hunterillager.HunterIllager;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public interface HunterDamageSource {
	ResourceKey<DamageType> BOOMERANG = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(HunterIllager.MODID, "boomerang"));

}

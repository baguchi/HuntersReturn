package baguchi.hunters_return.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public interface HunterDamageSource {
    ResourceKey<DamageType> BOOMERANG = ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath(baguchi.hunters_return.HuntersReturn.MODID, "boomerang"));
}

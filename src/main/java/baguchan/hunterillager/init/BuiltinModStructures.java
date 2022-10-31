package baguchan.hunterillager.init;

import baguchan.hunterillager.HunterIllager;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;

public class BuiltinModStructures {
	public static final ResourceKey<Structure> HUNTER_HOUSE = createKey(HunterIllager.locate("hunter_house"));

	private static ResourceKey<Structure> createKey(ResourceLocation key) {
		return ResourceKey.create(Registry.STRUCTURE_REGISTRY, key);
	}
}

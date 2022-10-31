package baguchan.hunterillager.init;

import baguchan.hunterillager.HunterIllager;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.StructureSet;

public class BuiltinModStructureSets {

	public static final ResourceKey<StructureSet> HUNTER_HOUSE = register(HunterIllager.locate("hunter_house"));

	private static ResourceKey<StructureSet> register(ResourceLocation key) {
		return ResourceKey.create(Registry.STRUCTURE_SET_REGISTRY, key);
	}
}

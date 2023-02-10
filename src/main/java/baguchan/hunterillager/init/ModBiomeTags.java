package baguchan.hunterillager.init;

import baguchan.hunterillager.HunterIllager;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ModBiomeTags {

	public static final TagKey<Biome> HAS_HUNTER_HOUSE = create("has_structure/hunter_house");

	private static TagKey<Biome> create(String path) {
		return TagKey.create(Registry.BIOME_REGISTRY, HunterIllager.locate(path));
	}
}

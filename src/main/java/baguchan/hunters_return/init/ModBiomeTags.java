package baguchan.hunters_return.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ModBiomeTags {

	public static final TagKey<Biome> HAS_HUNTER_HOUSE = create("has_structure/hunter_house");

	private static TagKey<Biome> create(String path) {
		return TagKey.create(Registries.BIOME, baguchan.hunters_return.HuntersReturn.locate(path));
	}
}

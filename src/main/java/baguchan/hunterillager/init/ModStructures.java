package baguchan.hunterillager.init;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;

import java.util.Map;

public class ModStructures {

	protected static Holder<Structure> HUNTER_HOUSE;

	public static final Holder<StructureTemplatePool> START = Pools.register(new StructureTemplatePool(new ResourceLocation("hunterillager:hunter_house/hunter_illager_house"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.single("hunterillager:hunter_house/hunter_illager_house", ProcessorLists.MOSSIFY_20_PERCENT), 50)), StructureTemplatePool.Projection.RIGID));


	public static void register() {
		TerrainAdjustment dungeonTerrainAdjustment = TerrainAdjustment.BEARD_THIN;
		HUNTER_HOUSE = register(BuiltinModStructures.HUNTER_HOUSE, new JigsawStructure(structure(ModBiomeTags.HAS_HUNTER_HOUSE, dungeonTerrainAdjustment), START, 6, ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG));
	}

	private static Structure.StructureSettings structure(TagKey<Biome> p_236546_, Map<MobCategory, StructureSpawnOverride> p_236547_, GenerationStep.Decoration p_236548_, TerrainAdjustment p_236549_) {
		return new Structure.StructureSettings(biomes(p_236546_), p_236547_, p_236548_, p_236549_);
	}

	private static Structure.StructureSettings structure(TagKey<Biome> p_236539_, GenerationStep.Decoration p_236540_, TerrainAdjustment p_236541_) {
		return structure(p_236539_, Map.of(), p_236540_, p_236541_);
	}

	private static Structure.StructureSettings structure(TagKey<Biome> p_236543_, TerrainAdjustment p_236544_) {
		return structure(p_236543_, Map.of(), GenerationStep.Decoration.SURFACE_STRUCTURES, p_236544_);
	}

	private static HolderSet<Biome> biomes(TagKey<Biome> p_236537_) {
		return BuiltinRegistries.BIOME.getOrCreateTag(p_236537_);
	}

	private static Holder<Structure> register(ResourceKey<Structure> p_236534_, Structure p_236535_) {
		return BuiltinRegistries.register(BuiltinRegistries.STRUCTURES, p_236534_, p_236535_);
	}

}
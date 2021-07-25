package baguchan.hunterillager.structure;


import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

public class HunterHouseStructure extends StructureFeature<NoneFeatureConfiguration> {
	public HunterHouseStructure(Codec<NoneFeatureConfiguration> p_i51440_1_) {
		super(p_i51440_1_);
	}

	@Override
	public String getFeatureName() {
		return "hunterillager:hunter_house";
	}

	protected boolean isFeatureChunk(ChunkGenerator p_160197_, BiomeSource p_160198_, long p_160199_, WorldgenRandom p_160200_, ChunkPos p_160201_, Biome p_160202_, ChunkPos p_160203_, NoneFeatureConfiguration p_160204_, LevelHeightAccessor p_160205_) {
		int i = p_160201_.x >> 4;
		int j = p_160201_.z >> 4;
		p_160200_.setSeed((long) (i ^ j << 4) ^ p_160199_);
		p_160200_.nextInt();

		return !this.isNearVillage(p_160197_, p_160199_, p_160200_, p_160201_);
	}

	private boolean isNearVillage(ChunkGenerator p_160182_, long p_160183_, WorldgenRandom p_160184_, ChunkPos p_160185_) {
		StructureFeatureConfiguration structurefeatureconfiguration = p_160182_.getSettings().getConfig(StructureFeature.VILLAGE);
		if (structurefeatureconfiguration == null) {
			return false;
		} else {
			int i = p_160185_.x;
			int j = p_160185_.z;

			for (int k = i - 10; k <= i + 10; ++k) {
				for (int l = j - 10; l <= j + 10; ++l) {
					ChunkPos chunkpos = StructureFeature.VILLAGE.getPotentialFeatureChunk(structurefeatureconfiguration, p_160183_, p_160184_, k, l);
					if (k == chunkpos.x && l == chunkpos.z) {
						return true;
					}
				}
			}

			return false;
		}
	}

	@Override
	public GenerationStep.Decoration step() {
		return GenerationStep.Decoration.SURFACE_STRUCTURES;
	}

	public StructureFeature.StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
		return FeatureStart::new;
	}

	public static class FeatureStart extends StructureStart<NoneFeatureConfiguration> {
		public FeatureStart(StructureFeature<NoneFeatureConfiguration> p_159888_, ChunkPos p_159889_, int p_159890_, long p_159891_) {
			super(p_159888_, p_159889_, p_159890_, p_159891_);
		}

		public void generatePieces(RegistryAccess p_159901_, ChunkGenerator p_159902_, StructureManager p_159903_, ChunkPos p_159904_, Biome p_159905_, NoneFeatureConfiguration p_159906_, LevelHeightAccessor p_159907_) {
			BlockPos blockpos = new BlockPos(p_159904_.getMinBlockX(), 90, p_159904_.getMinBlockZ());
			Rotation rotation = Rotation.getRandom(this.random);
			HunterHousePieces.addStructure(p_159903_, blockpos, rotation, this, this.random);
		}
	}
}

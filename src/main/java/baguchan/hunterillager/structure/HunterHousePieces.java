package baguchan.hunterillager.structure;

import baguchan.hunterillager.HunterIllager;
import baguchan.hunterillager.entity.HunterIllagerEntity;
import baguchan.hunterillager.init.HunterEntityRegistry;
import baguchan.hunterillager.init.HunterStructureRegister;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.Map;

public class HunterHousePieces {
	private static final ResourceLocation hunterbase_Template = new ResourceLocation(HunterIllager.MODID, "illager_woodhut");

	private static final Map<ResourceLocation, BlockPos> structurePos = ImmutableMap.of(hunterbase_Template, BlockPos.ZERO);

	static final BlockPos PIVOT = new BlockPos(9, 0, 9);

	public static void addPieces(StructureTemplateManager p_227549_, BlockPos p_227550_, Rotation p_227551_, StructurePieceAccessor p_227552_, RandomSource p_227553_) {
		p_227552_.addPiece(new Piece(p_227549_, hunterbase_Template, p_227550_, p_227551_, 0));
	}

	public static class Piece extends TemplateStructurePiece {
		public Piece(StructureTemplateManager p_227555_, ResourceLocation p_227556_, BlockPos p_227557_, Rotation p_227558_, int p_227559_) {
			super(HunterStructureRegister.HUNTER_HOUSE_STRUCTURE_PIECE, 0, p_227555_, p_227556_, p_227556_.toString(), makeSettings(p_227558_, p_227556_), makePosition(p_227556_, p_227557_, p_227559_));
		}

		public Piece(StructureTemplateManager p_227561_, CompoundTag p_227562_) {
			super(HunterStructureRegister.HUNTER_HOUSE_STRUCTURE_PIECE, p_227562_, p_227561_, (p_227589_) -> {
				return makeSettings(Rotation.valueOf(p_227562_.getString("Rot")), p_227589_);
			});
		}

		private static StructurePlaceSettings makeSettings(Rotation p_227576_, ResourceLocation p_227577_) {
			return (new StructurePlaceSettings()).setRotation(p_227576_).setMirror(Mirror.NONE).setRotationPivot(HunterHousePieces.PIVOT).addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
		}

		private static BlockPos makePosition(ResourceLocation p_227564_, BlockPos p_227565_, int p_227566_) {
			return p_227565_.offset(HunterHousePieces.structurePos.get(p_227564_)).below(p_227566_);
		}

		protected void addAdditionalSaveData(StructurePieceSerializationContext p_227579_, CompoundTag p_227580_) {
			super.addAdditionalSaveData(p_227579_, p_227580_);
			p_227580_.putString("Rot", this.placeSettings.getRotation().name());
		}

		protected void handleDataMarker(String function, BlockPos pos, ServerLevelAccessor worldIn, RandomSource p_227585_, BoundingBox p_227586_) {
			if ("hunter".equals(function)) {
				worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
				HunterIllagerEntity hunterIllager = HunterEntityRegistry.HUNTERILLAGER.get().create(worldIn.getLevel());
				hunterIllager.setPersistenceRequired();
				hunterIllager.setPos(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
				hunterIllager.setHomeTarget(pos);
				hunterIllager.finalizeSpawn(worldIn, worldIn.getCurrentDifficultyAt(hunterIllager.blockPosition()), MobSpawnType.STRUCTURE, null, null);
				worldIn.addFreshEntity(hunterIllager);
			}
		}

		public void postProcess(WorldGenLevel p_227568_, StructureManager p_227569_, ChunkGenerator p_227570_, RandomSource p_227571_, BoundingBox p_227572_, ChunkPos p_227573_, BlockPos p_227574_) {
			ResourceLocation resourcelocation = new ResourceLocation(this.templateName);
			StructurePlaceSettings structureplacesettings = makeSettings(this.placeSettings.getRotation(), resourcelocation);
			BlockPos blockpos = HunterHousePieces.structurePos.get(resourcelocation);
			BlockPos blockpos1 = this.templatePosition.offset(StructureTemplate.calculateRelativePosition(structureplacesettings, new BlockPos(3 - blockpos.getX(), 0, -blockpos.getZ())));
			int i = p_227568_.getHeight(Heightmap.Types.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
			BlockPos blockpos2 = this.templatePosition;
			this.templatePosition = this.templatePosition.offset(0, i - 90 - 1, 0);
			super.postProcess(p_227568_, p_227569_, p_227570_, p_227571_, p_227572_, p_227573_, p_227574_);

			this.templatePosition = blockpos2;
		}
	}
}
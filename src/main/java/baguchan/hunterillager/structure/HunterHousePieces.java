package baguchan.hunterillager.structure;

import baguchan.hunterillager.HunterIllager;
import baguchan.hunterillager.entity.HunterIllagerEntity;
import baguchan.hunterillager.init.HunterEntityRegistry;
import baguchan.hunterillager.init.HunterStructureRegister;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import java.util.Map;
import java.util.Random;

public class HunterHousePieces {
	private static final ResourceLocation hunterbase_Template = new ResourceLocation(HunterIllager.MODID, "illager_woodhut");

	private static final Map<ResourceLocation, BlockPos> structurePos = ImmutableMap.of(hunterbase_Template, BlockPos.ZERO);

	public static void addStructure(StructureManager p_162435_, BlockPos p_162436_, Rotation p_162437_, StructurePieceAccessor p_162438_, Random p_162439_) {
		p_162438_.addPiece(new Piece(p_162435_, hunterbase_Template, p_162436_, p_162437_, 0));
	}

	public static class Piece extends TemplateStructurePiece {
		public Piece(StructureManager p_71244_, ResourceLocation p_71245_, BlockPos p_71246_, Rotation p_71247_, int p_71248_) {
			super(HunterStructureRegister.HUNTER_HOUSE_STRUCTURE_PIECE, 0, p_71244_, p_71245_, p_71245_.toString(), makeSettings(p_71247_, p_71246_), makePosition(p_71245_, p_71246_, p_71248_));
		}

		public Piece(ServerLevel p_162441_, CompoundTag p_162442_) {
			super(HunterStructureRegister.HUNTER_HOUSE_STRUCTURE_PIECE, p_162442_, p_162441_, (p_162451_) -> {
				return makeSettings(p_162441_, Rotation.valueOf(p_162442_.getString("Rot")), p_162451_);
			});
		}

		private static StructurePlaceSettings makeSettings(ServerLevel serverLevel, Rotation p_162447_, ResourceLocation resourceLocation) {
			StructureTemplate structuretemplate = serverLevel.getStructureManager().getOrCreate(resourceLocation);
			BlockPos blockpos = new BlockPos(structuretemplate.getSize().getX() / 2, 0, structuretemplate.getSize().getZ() / 2);
			return (new StructurePlaceSettings()).setRotation(p_162447_).setMirror(Mirror.NONE).setRotationPivot(blockpos).addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK).addProcessor(new ProtectedBlockProcessor(BlockTags.FEATURES_CANNOT_REPLACE.getName()));
		}

		private static StructurePlaceSettings makeSettings(Rotation p_163156_, BlockPos p_163158_) {
			BlockIgnoreProcessor blockignoreprocessor = BlockIgnoreProcessor.STRUCTURE_BLOCK;

			StructurePlaceSettings structureplacesettings = (new StructurePlaceSettings()).setRotation(p_163156_).setMirror(Mirror.NONE).setRotationPivot(p_163158_).addProcessor(blockignoreprocessor).addProcessor(new ProtectedBlockProcessor(BlockTags.FEATURES_CANNOT_REPLACE.getName()));


			return structureplacesettings;
		}

		private static BlockPos makePosition(ResourceLocation p_162453_, BlockPos p_162454_, int p_162455_) {
			return p_162454_.offset(structurePos.get(p_162453_)).below(p_162455_);
		}

		protected void addAdditionalSaveData(ServerLevel p_162444_, CompoundTag p_162445_) {
			super.addAdditionalSaveData(p_162444_, p_162445_);
			p_162445_.putString("Rot", this.placeSettings.getRotation().name());
		}

		public boolean postProcess(WorldGenLevel worldIn, StructureFeatureManager p_230383_2_, ChunkGenerator p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, ChunkPos p_230383_6_, BlockPos p_230383_7_) {
			//ResourceLocation var8 = new ResourceLocation(this.templateName);
			//BlockPos blockPos = (BlockPos) structurePos.get(var8);
			BlockPos blockpos1 = this.templatePosition;
			int i = worldIn.getHeight(Heightmap.Types.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
			BlockPos blockpos2 = this.templatePosition;
			this.templatePosition = this.templatePosition.offset(0, i - 90 - 2, 0);
			boolean flag = super.postProcess(worldIn, p_230383_2_, p_230383_3_, p_230383_4_, p_230383_5_, p_230383_6_, p_230383_7_);
			this.templatePosition = blockpos2;
			return flag;
		}

		protected void handleDataMarker(String function, BlockPos pos, ServerLevelAccessor worldIn, Random p_71263_, BoundingBox p_71264_) {
			if ("hunter".equals(function)) {
				worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
				HunterIllagerEntity hunterIllager = HunterEntityRegistry.HUNTERILLAGER.create(worldIn.getLevel());
				hunterIllager.setPersistenceRequired();
				hunterIllager.setPos(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
				hunterIllager.setHomeTarget(pos);
				hunterIllager.finalizeSpawn(worldIn, worldIn.getCurrentDifficultyAt(hunterIllager.blockPosition()), MobSpawnType.STRUCTURE, null, null);
				worldIn.addFreshEntity(hunterIllager);
			}
			if ("sniper".equals(function)) {
				worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
				HunterIllagerEntity hunterIllager = HunterEntityRegistry.HUNTERILLAGER.create(worldIn.getLevel());
				hunterIllager.setPersistenceRequired();
				hunterIllager.setPos(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
				hunterIllager.setHomeTarget(pos);
				hunterIllager.setItemInHand(InteractionHand.MAIN_HAND, Items.BOW.getDefaultInstance());
				worldIn.addFreshEntity(hunterIllager);
			}
		}
	}
}

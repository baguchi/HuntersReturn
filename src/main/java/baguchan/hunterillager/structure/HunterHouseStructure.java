package baguchan.hunterillager.structure;


import baguchan.hunterillager.init.HunterStructureRegister;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;

import java.util.Optional;

public class HunterHouseStructure extends Structure {
	public static final Codec<HunterHouseStructure> CODEC = simpleCodec(HunterHouseStructure::new);

	public HunterHouseStructure(Structure.StructureSettings p_227593_) {
		super(p_227593_);
	}

	public Optional<GenerationStub> findGenerationPoint(Structure.GenerationContext p_227595_) {
		return onTopOfChunkCenter(p_227595_, Heightmap.Types.WORLD_SURFACE_WG, (p_227598_) -> {
			this.generatePieces(p_227598_, p_227595_);
		});
	}

	private void generatePieces(StructurePiecesBuilder p_227600_, Structure.GenerationContext p_227601_) {
		ChunkPos chunkpos = p_227601_.chunkPos();
		WorldgenRandom worldgenrandom = p_227601_.random();
		BlockPos blockpos = new BlockPos(chunkpos.getMinBlockX(), 90, chunkpos.getMinBlockZ());
		Rotation rotation = Rotation.getRandom(worldgenrandom);
		HunterHousePieces.addPieces(p_227601_.structureTemplateManager(), blockpos, rotation, p_227600_, worldgenrandom);
	}

	public StructureType<?> type() {
		return HunterStructureRegister.HUNTER_HOUSE.get();
	}
}
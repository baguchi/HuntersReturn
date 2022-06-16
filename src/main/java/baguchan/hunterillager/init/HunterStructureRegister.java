package baguchan.hunterillager.init;

import baguchan.hunterillager.HunterIllager;
import baguchan.hunterillager.structure.HunterHousePieces;
import baguchan.hunterillager.structure.HunterHouseStructure;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;

public class HunterStructureRegister {
	public static final DeferredRegister<StructureType<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, HunterIllager.MODID);


	public static final RegistryObject<StructureType<HunterHouseStructure>> HUNTER_HOUSE = DEFERRED_REGISTRY_STRUCTURE.register("hunter_house", () -> () -> HunterHouseStructure.CODEC);
	public static StructurePieceType HUNTER_HOUSE_STRUCTURE_PIECE;

	static StructurePieceType setPieceId(StructurePieceType.StructureTemplateType p_67164_, String p_67165_) {
		return Registry.register(Registry.STRUCTURE_PIECE, p_67165_.toLowerCase(Locale.ROOT), p_67164_);
	}

	public static void init() {
		HUNTER_HOUSE_STRUCTURE_PIECE = setPieceId(HunterHousePieces.Piece::new, "HHSP");
	}

	private static String prefix(String path) {
		return "hunterillager:" + path;
	}
}

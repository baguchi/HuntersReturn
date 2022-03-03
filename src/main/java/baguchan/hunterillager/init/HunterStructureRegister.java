package baguchan.hunterillager.init;

import baguchan.hunterillager.HunterIllager;
import baguchan.hunterillager.structure.HunterHousePieces;
import baguchan.hunterillager.structure.HunterHouseStructure;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Locale;

@Mod.EventBusSubscriber(modid = HunterIllager.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HunterStructureRegister {
	public static final StructureFeature<NoneFeatureConfiguration> HUNTER_HOUSE = new HunterHouseStructure(NoneFeatureConfiguration.CODEC);
	public static StructurePieceType HUNTER_HOUSE_STRUCTURE_PIECE;

	static StructurePieceType setPieceId(StructurePieceType.StructureTemplateType p_67164_, String p_67165_) {
		return Registry.register(Registry.STRUCTURE_PIECE, p_67165_.toLowerCase(Locale.ROOT), p_67164_);
	}

	@SubscribeEvent
	public static void registerFeature(RegistryEvent.Register<StructureFeature<?>> registry) {
		registry.getRegistry().register(HUNTER_HOUSE.setRegistryName("hunter_house"));
	}

	public static void init() {
		HUNTER_HOUSE_STRUCTURE_PIECE = setPieceId(HunterHousePieces.Piece::new, "HHSP");
	}

	private static String prefix(String path) {
		return "hunterillager:" + path;
	}
}

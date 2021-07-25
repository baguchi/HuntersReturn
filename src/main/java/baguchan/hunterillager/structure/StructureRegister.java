package baguchan.hunterillager.structure;

import baguchan.hunterillager.HunterIllager;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.Locale;

@EventBusSubscriber(modid = HunterIllager.MODID, bus = EventBusSubscriber.Bus.MOD)
public class StructureRegister {
	public static final StructureFeature<NoneFeatureConfiguration> HUNTER_HOUSE = new HunterHouseStructure(NoneFeatureConfiguration.CODEC);
	public static final ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> HUNTER_HOUSE_FEATURE = configFeatureRegister(prefix("hunter_house"), HUNTER_HOUSE.configured(NoneFeatureConfiguration.INSTANCE));


	public static final StructurePieceType HUNTER_HOUSE_STRUCTURE_PIECE = setPieceId(HunterHousePieces.Piece::new, "HHSP");

	static StructurePieceType setPieceId(StructurePieceType p_67164_, String p_67165_) {
		return Registry.register(Registry.STRUCTURE_PIECE, p_67165_.toLowerCase(Locale.ROOT), p_67164_);
	}

	@SubscribeEvent
	public static void registerfeature(RegistryEvent.Register<StructureFeature<?>> registry) {
		NoiseGeneratorSettings.bootstrap().structureSettings().structureConfig().put(HUNTER_HOUSE, new StructureFeatureConfiguration(28, 6, 15437620));
		registry.getRegistry().register(HUNTER_HOUSE.setRegistryName("hunter_house"));
	}

	private static <FC extends FeatureConfiguration, F extends StructureFeature<FC>> ConfiguredStructureFeature<FC, F> configFeatureRegister(String p_127268_, ConfiguredStructureFeature<FC, F> p_127269_) {
		return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, p_127268_, p_127269_);
	}

	private static String prefix(String path) {
		return "hunterillager:" + path;
	}
}

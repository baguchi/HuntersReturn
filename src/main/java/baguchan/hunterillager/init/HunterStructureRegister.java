package baguchan.hunterillager.init;

import baguchan.hunterillager.HunterConfig;
import baguchan.hunterillager.HunterIllager;
import baguchan.hunterillager.structure.HunterHousePieces;
import baguchan.hunterillager.structure.HunterHouseStructure;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Mod.EventBusSubscriber(modid = HunterIllager.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HunterStructureRegister {
	public static final StructureFeature<NoneFeatureConfiguration> HUNTER_HOUSE = new HunterHouseStructure(NoneFeatureConfiguration.CODEC);
	public static final ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> HUNTER_HOUSE_FEATURE = configFeatureRegister(prefix("hunter_house"), HUNTER_HOUSE.configured(NoneFeatureConfiguration.INSTANCE));


	public static final StructurePieceType HUNTER_HOUSE_STRUCTURE_PIECE = setPieceId(HunterHousePieces.Piece::new, "HHSP");

	static StructurePieceType setPieceId(StructurePieceType.StructureTemplateType p_67164_, String p_67165_) {
		return Registry.register(Registry.STRUCTURE_PIECE, p_67165_.toLowerCase(Locale.ROOT), p_67164_);
	}

	@SubscribeEvent
	public static void registerfeature(RegistryEvent.Register<StructureFeature<?>> registry) {
		StructureFeature.STRUCTURES_REGISTRY.put("hunterillager:hunter_house", HUNTER_HOUSE);

		setupMapSpacingAndLand(HUNTER_HOUSE, new StructureFeatureConfiguration(HunterConfig.COMMON.structureSpacing.get(), 6, 15437620), false);

		registry.getRegistry().register(HUNTER_HOUSE.setRegistryName("hunter_house"));
	}

	// ============================================================================================================================================================================
	// it comes from TelepathicGrunt's Structure Tutorial
	// https://github.com/TelepathicGrunt/StructureTutorialMod/tree/1.18.x-Forge-Jigsaw/
	// ============================================================================================================================================================================

	public static <F extends StructureFeature<?>> void setupMapSpacingAndLand(
			F structure,
			StructureFeatureConfiguration structureFeatureConfiguration,
			boolean transformSurroundingLand) {
		if (transformSurroundingLand) {
			StructureFeature.NOISE_AFFECTING_FEATURES =
					ImmutableList.<StructureFeature<?>>builder()
							.addAll(StructureFeature.NOISE_AFFECTING_FEATURES)
							.add(structure)
							.build();
		}
		StructureSettings.DEFAULTS =
				ImmutableMap.<StructureFeature<?>, StructureFeatureConfiguration>builder()
						.putAll(StructureSettings.DEFAULTS)
						.put(structure, structureFeatureConfiguration)
						.build();

		BuiltinRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
			Map<StructureFeature<?>, StructureFeatureConfiguration> structureMap = settings.getValue().structureSettings().structureConfig();

			/*
			 * Pre-caution in case a mod makes the structure map immutable like datapacks do.
			 * I take no chances myself. You never know what another mods does...
			 *
			 * structureConfig requires AccessTransformer (See resources/META-INF/accesstransformer.cfg)
			 */
			if (structureMap instanceof ImmutableMap) {
				Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(structureMap);
				tempMap.put(structure, structureFeatureConfiguration);
				settings.getValue().structureSettings().structureConfig = tempMap;
			} else {
				structureMap.put(structure, structureFeatureConfiguration);
			}
		});
	}

	private static <FC extends FeatureConfiguration, F extends StructureFeature<FC>> ConfiguredStructureFeature<FC, F> configFeatureRegister(String p_127268_, ConfiguredStructureFeature<FC, F> p_127269_) {
		return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, p_127268_, p_127269_);
	}

	private static String prefix(String path) {
		return "hunterillager:" + path;
	}
}

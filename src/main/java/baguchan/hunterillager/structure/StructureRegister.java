package baguchan.hunterillager.structure;

import baguchan.hunterillager.HunterIllager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = HunterIllager.MODID, bus = EventBusSubscriber.Bus.MOD)
public class StructureRegister {
	public static final Structure<NoFeatureConfig> HUNTER_HOUSE_STRUCTURE = new HunterHouseStructure(NoFeatureConfig.CODEC);

	public static final StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> HUNTER_HOUSE = func_244162_a(prefix("hunter_house"), HUNTER_HOUSE_STRUCTURE.configured(NoFeatureConfig.INSTANCE));

	public static final IStructurePieceType HUNTER_HOUSE_STRUCTURE_PIECE = registerStructurePiece(new ResourceLocation(HunterIllager.MODID, "hunter_house"), HunterHousePieces.Piece::new);

	public static <C extends IFeatureConfig> IStructurePieceType registerStructurePiece(ResourceLocation key, IStructurePieceType pieceType) {
		return Registry.register(Registry.STRUCTURE_PIECE, key, pieceType);
	}

	@SubscribeEvent
	public static void registerStructure(RegistryEvent.Register<Structure<?>> registry) {
		DimensionSettings.bootstrap().structureSettings().structureConfig().put(HUNTER_HOUSE_STRUCTURE, new StructureSeparationSettings(28, 6, 15437620));
		registry.getRegistry().register(HUNTER_HOUSE_STRUCTURE.setRegistryName("hunterhouse"));
		Structure.STRUCTURES_REGISTRY.put(prefix("hunterhouse"), HUNTER_HOUSE_STRUCTURE);
	}

	private static <FC extends IFeatureConfig, F extends Structure<FC>> StructureFeature<FC, F> func_244162_a(String p_244162_0_, StructureFeature<FC, F> p_244162_1_) {
		return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, p_244162_0_, p_244162_1_);
	}

	private static String prefix(String path) {
		return "hunterillager:" + path;
	}
}

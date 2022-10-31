package baguchan.hunterillager.init;

import baguchan.hunterillager.HunterConfig;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;

public class ModStructureSets {

	protected static Holder<StructureSet> HUNTER_HOUSE;

	public static void register() {
		HUNTER_HOUSE = register(BuiltinModStructureSets.HUNTER_HOUSE, ModStructures.HUNTER_HOUSE, new RandomSpreadStructurePlacement(HunterConfig.COMMON.SPACING.get(), HunterConfig.COMMON.SEPARATION.get(), RandomSpreadType.LINEAR, 15437620));
	}


	private static Holder<StructureSet> register(ResourceKey<StructureSet> p_211129_, StructureSet p_211130_) {
		return BuiltinRegistries.register(BuiltinRegistries.STRUCTURE_SETS, p_211129_, p_211130_);
	}

	private static Holder<StructureSet> register(ResourceKey<StructureSet> p_211132_, Holder<Structure> p_211133_, StructurePlacement p_211134_) {
		return register(p_211132_, new StructureSet(p_211133_, p_211134_));
	}

}
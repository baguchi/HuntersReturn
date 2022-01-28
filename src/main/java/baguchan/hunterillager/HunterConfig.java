package baguchan.hunterillager;

import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.function.Predicate;

public class HunterConfig {
	public static final Common COMMON;
	public static final ForgeConfigSpec COMMON_SPEC;

	static {
		Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	public static void init() {
	}

	public static class Common {
		public final ForgeConfigSpec.IntValue structureSpacing;

		public final ForgeConfigSpec.ConfigValue<List<? extends String>> foodWhitelist;
		public final ForgeConfigSpec.ConfigValue<List<? extends String>> attackableWhitelist;

		public Common(ForgeConfigSpec.Builder builder) {
			Predicate<Object> validator = o -> o instanceof String;

			structureSpacing = builder
					.translation(HunterIllager.MODID + ".config.structureSpacing")
					.comment("Changed HunterHouse Generate Spacing. [24 ~ 100]")
					.defineInRange("HunterHouse Generate Spacing", 26, 24, 100);

			foodWhitelist = builder
					.translation(HunterIllager.MODID + ".config.foodWhitelist")
					.comment("Add Item for What Hunter Illager can eatable [example: minecraft:apple]")
					.defineList("HunterIllager's Food Whitelist"
							, Lists.newArrayList("minecraft:apple"
									, "minecraft:cooked_beef", "minecraft:cooked_chicken", "minecraft:cooked_mutton", "minecraft:cooked_porkchop"
									, "minecraft:beef", "minecraft:chicken", "minecraft:mutton", "minecraft:porkchop")
							, validator);
			attackableWhitelist = builder
					.translation(HunterIllager.MODID + ".config.attackableWhitelist")
					.comment("Add Entity for What Hunter Illager can hunt [example: minecraft:chicken]")
					.defineList("HunterIllager's Hunt Entity Whitelist"
							, Lists.newArrayList("minecraft:chicken"
									, "minecraft:rabbit", "minecraft:pig", "minecraft:cow", "minecraft:sheep"
									, "earthmobsmod:wooly_cow", "earthmobsmod:horned_sheep")
							, validator);
		}
	}

}
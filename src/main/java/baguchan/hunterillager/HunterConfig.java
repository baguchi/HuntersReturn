package baguchan.hunterillager;

import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.function.Predicate;

@Mod.EventBusSubscriber(modid = HunterIllager.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HunterConfig {
	public static final Common COMMON;
	public static final ForgeConfigSpec COMMON_SPEC;

	static {
		Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	public static class Common {
		public final ForgeConfigSpec.IntValue structureSpacing;

		public final ForgeConfigSpec.ConfigValue<List<? extends String>> foodWhitelist;

		public Common(ForgeConfigSpec.Builder builder) {
			Predicate<Object> validator = o -> o instanceof String && ((String) o).contains(":");

			structureSpacing = builder
					.translation(HunterIllager.MODID + ".config.structureSpacing")
					.comment("Changed HunterHouse Generate Spacing. [24 ~ 36]")
					.defineInRange("HunterIllager's Food Whitelist", 26, 24, 36);

			foodWhitelist = builder
					.translation(HunterIllager.MODID + ".config.foodWhitelist")
					.comment("Add Item for What Hunter Illager can eatable")
					.define("HunterIllager's Food Whitelist"
							, Lists.newArrayList("minecraft:apple"
									, "minecraft:cooked_beef", "minecraft:cooked_chicken", "minectaft:cooked_mutton", "minecraft:cooked_porkchop"
									, "minecraft:beef", "minecraft:chicken", "minectaft:mutton", "minecraft:porkchop")
							, validator);
		}
	}

}
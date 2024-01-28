package baguchan.hunters_return;

import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.function.Predicate;

public class HunterConfig {
	public static final Common COMMON;
	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Client CLIENT;
	public static final ForgeConfigSpec CLIENT_SPEC;


	static {
		Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
		Pair<Client, ForgeConfigSpec> specPair2 = new ForgeConfigSpec.Builder().configure(Client::new);

		CLIENT_SPEC = specPair2.getRight();
		CLIENT = specPair2.getLeft();
	}

	public static class Common {
		public final ForgeConfigSpec.ConfigValue<List<? extends String>> foodWhitelist;
		public final ForgeConfigSpec.ConfigValue<List<? extends String>> foodInInventoryWhitelist;
		public final ForgeConfigSpec.ConfigValue<List<? extends String>> attackableWhitelist;
		public Common(ForgeConfigSpec.Builder builder) {
			Predicate<Object> validator = o -> o instanceof String;

			foodWhitelist = builder
					.translation(HuntersReturn.MODID + ".config.foodWhitelist")
					.comment("Add Item for What Hunter Illager can eatable [example: minecraft:apple]")
					.defineList("Hunter's Food Whitelist"
							, Lists.newArrayList("minecraft:apple"
									, "minecraft:cooked_beef", "minecraft:cooked_chicken", "minecraft:cooked_mutton", "minecraft:cooked_porkchop"
									, "minecraft:beef", "minecraft:chicken", "minecraft:mutton", "minecraft:porkchop")
							, validator);
			foodInInventoryWhitelist = builder
					.comment("Add Item for What Hunter Illager has in own Inventory [example: minecraft:apple]")
					.defineList("Hunter's Food In Inventory Whitelist"
							, Lists.newArrayList("minecraft:apple"
									, "minecraft:cooked_beef", "minecraft:cooked_chicken", "minecraft:cooked_mutton", "minecraft:cooked_porkchop"
									, "minecraft:beef", "minecraft:chicken", "minecraft:mutton", "minecraft:porkchop")
							, validator);
			attackableWhitelist = builder
					.translation(HuntersReturn.MODID + ".config.attackableWhitelist")
					.comment("Add Entity for What Hunter Illager can hunt [example: minecraft:chicken]")
					.defineList("Hunter's Hunt Entity Whitelist"
							, Lists.newArrayList("minecraft:chicken"
									, "minecraft:rabbit", "minecraft:pig", "minecraft:cow", "minecraft:sheep"
									, "earthmobsmod:wooly_cow", "earthmobsmod:horned_sheep")
							, validator);
		}
	}

	public static class Client {
		public final ForgeConfigSpec.BooleanValue oldAnimation;

		public Client(ForgeConfigSpec.Builder builder) {
			oldAnimation = builder.comment("Enable The Old animations")
					.define("Enable Old Animations", false);
		}
	}
}
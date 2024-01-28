package baguchan.hunters_return;

import com.google.common.collect.Lists;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.function.Predicate;

public class HunterConfig {
	public static final Common COMMON;
    public static final ModConfigSpec COMMON_SPEC;
	public static final Client CLIENT;
	public static final ModConfigSpec CLIENT_SPEC;

	static {
        Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
		Pair<Client, ModConfigSpec> specPair2 = new ModConfigSpec.Builder().configure(Client::new);

		CLIENT_SPEC = specPair2.getRight();
		CLIENT = specPair2.getLeft();
	}

	public static class Common {
        public final ModConfigSpec.ConfigValue<List<? extends String>> foodWhitelist;
        public final ModConfigSpec.ConfigValue<List<? extends String>> foodInInventoryWhitelist;
        public final ModConfigSpec.ConfigValue<List<? extends String>> attackableWhitelist;

        public Common(ModConfigSpec.Builder builder) {
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
		public final ModConfigSpec.BooleanValue oldAnimation;

		public Client(ModConfigSpec.Builder builder) {
			oldAnimation = builder.comment("Enable The Old animations")
					.define("Enable Old Animations", false);
		}
	}
}
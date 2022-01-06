package baguchan.hunterillager;

import com.google.common.collect.Lists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.function.Predicate;

@Mod.EventBusSubscriber(modid = HunterIllager.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HunterConfig {
	public static final Common COMMON;
	public static final ForgeConfigSpec COMMON_SPEC;

	public static List<Item> foodWhitelist = Lists.newArrayList();

	static {
		Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	public static void bakeConfig() {
		foodWhitelist.clear();
		COMMON.foodWhitelist.get().forEach(item -> {
			Item type = ForgeRegistries.ITEMS.getValue(new ResourceLocation(item));

			if (type != null) {
				foodWhitelist.add(type);
			}
		});
	}


	@SubscribeEvent
	public static void onModConfigEvent(final ModConfigEvent.Loading configEvent) {
		if (configEvent.getConfig().getSpec() == HunterConfig.COMMON_SPEC) {
			bakeConfig();
		}
	}

	@SubscribeEvent
	public static void onModConfigEvent(final ModConfigEvent.Reloading configEvent) {
		if (configEvent.getConfig().getSpec() == HunterConfig.COMMON_SPEC) {
			bakeConfig();
		}
	}

	public static class Common {
		public final ForgeConfigSpec.ConfigValue<List<? extends String>> foodWhitelist;

		public Common(ForgeConfigSpec.Builder builder) {
			Predicate<Object> validator = o -> o instanceof String && ((String) o).contains(":");

			foodWhitelist = builder
					.translation(HunterIllager.MODID + ".config.foodWhitelist")
					.define("HunterIllager's Food Whitelist"
							, Lists.newArrayList("minecraft:apple"
									, "minecraft:cooked_beef", "minecraft:cooked_chicken", "minectaft:cooked_mutton", "minecraft:cooked_porkchop"
									, "minecraft:beef", "minecraft:chicken", "minectaft:mutton", "minecraft:porkchop")
							, validator);
		}
	}

}
package baguchi.hunters_return.utils;

import baguchi.hunters_return.HunterConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class HunterConfigUtils {
	public static boolean isWhitelistedItem(Item item) {
		return HunterConfig.COMMON.foodWhitelist.get().contains(BuiltInRegistries.ITEM.getKey(item).toString());
	}

	public static boolean isWhitelistedEntity(EntityType<?> entityType) {
		return HunterConfig.COMMON.attackableWhitelist.get().contains(BuiltInRegistries.ENTITY_TYPE.getKey(entityType).toString());
	}
}

package baguchan.hunterillager.utils;

import baguchan.hunterillager.HunterConfig;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class HunterConfigUtils {
	public static boolean isWhitelistedItem(Item item) {
		return HunterConfig.COMMON.foodWhitelist.get().contains(item.getRegistryName().toString());
	}

	public static boolean isWhitelistedEntity(EntityType<?> entityType) {
		return HunterConfig.COMMON.attackableWhitelist.get().contains(entityType.getRegistryName().toString());
	}
}
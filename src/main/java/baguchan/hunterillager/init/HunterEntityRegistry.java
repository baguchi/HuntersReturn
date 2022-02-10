package baguchan.hunterillager.init;

import baguchan.hunterillager.HunterIllager;
import baguchan.hunterillager.entity.HunterIllagerEntity;
import baguchan.hunterillager.entity.projectile.BoomerangEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = HunterIllager.MODID, bus = EventBusSubscriber.Bus.MOD)
public class HunterEntityRegistry {
	public static final EntityType<HunterIllagerEntity> HUNTERILLAGER = EntityType.Builder.of(HunterIllagerEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build(prefix("hunterillager"));

	public static final EntityType<BoomerangEntity> BOOMERANG = EntityType.Builder.<BoomerangEntity>of(BoomerangEntity::new, MobCategory.MISC).sized(0.3F, 0.3F).clientTrackingRange(4).updateInterval(20).build(prefix("boomerang"));

	@SubscribeEvent
	public static void registerEntity(RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().register(HUNTERILLAGER.setRegistryName("hunterillager"));
		event.getRegistry().register(BOOMERANG.setRegistryName("boomerang"));
		Raid.RaiderType.create("hunterillager", HUNTERILLAGER, new int[]{0, 0, 1, 2, 2, 1, 2, 3});
		SpawnPlacements.register(HUNTERILLAGER, SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
	}

	@SubscribeEvent
	public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
		event.put(HUNTERILLAGER, HunterIllagerEntity.createAttributes().build());
	}

	private static String prefix(String path) {
		return "hunterillager." + path;
	}
}

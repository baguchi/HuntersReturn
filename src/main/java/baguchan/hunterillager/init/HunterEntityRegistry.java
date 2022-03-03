package baguchan.hunterillager.init;

import baguchan.hunterillager.HunterIllager;
import baguchan.hunterillager.entity.HunterIllagerEntity;
import baguchan.hunterillager.entity.projectile.BoomerangEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = HunterIllager.MODID, bus = EventBusSubscriber.Bus.MOD)
public class HunterEntityRegistry {
	public static final DeferredRegister<EntityType<?>> ENTITIES_REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITIES, HunterIllager.MODID);


	public static final RegistryObject<EntityType<HunterIllagerEntity>> HUNTERILLAGER = ENTITIES_REGISTRY.register("hunterillager", () -> EntityType.Builder.of(HunterIllagerEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build(prefix("hunterillager")));

	public static final RegistryObject<EntityType<BoomerangEntity>> BOOMERANG = ENTITIES_REGISTRY.register("boomerang", () -> EntityType.Builder.<BoomerangEntity>of(BoomerangEntity::new, MobCategory.MISC).sized(0.3F, 0.3F).clientTrackingRange(4).updateInterval(20).build(prefix("boomerang")));


	@SubscribeEvent
	public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
		event.put(HUNTERILLAGER.get(), HunterIllagerEntity.createAttributes().build());
	}

	private static String prefix(String path) {
		return "hunterillager." + path;
	}
}

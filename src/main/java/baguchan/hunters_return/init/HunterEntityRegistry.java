package baguchan.hunters_return.init;

import baguchan.hunters_return.HuntersReturn;
import baguchan.hunters_return.entity.Hunter;
import baguchan.hunters_return.entity.HunterBoar;
import baguchan.hunters_return.entity.RudeHog;
import baguchan.hunters_return.entity.projectile.BoomerangEntity;
import baguchan.hunters_return.entity.projectile.SpinBlade;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = baguchan.hunters_return.HuntersReturn.MODID, bus = EventBusSubscriber.Bus.MOD)
public class HunterEntityRegistry {
	public static final DeferredRegister<EntityType<?>> ENTITIES_REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, HuntersReturn.MODID);


	public static final RegistryObject<EntityType<Hunter>> HUNTERILLAGER = ENTITIES_REGISTRY.register("hunter", () -> EntityType.Builder.of(Hunter::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build(prefix("hunter")));
    public static final RegistryObject<EntityType<RudeHog>> RUDEHOG = ENTITIES_REGISTRY.register("rudehog", () -> EntityType.Builder.of(RudeHog::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build(prefix("rudehog")));

    public static final RegistryObject<EntityType<HunterBoar>> HUNTER_BOAR = ENTITIES_REGISTRY.register("hunter_boar", () -> EntityType.Builder.of(HunterBoar::new, MobCategory.MONSTER).sized(1.3964844F, 1.4F).clientTrackingRange(8).build(prefix("hunter_boar")));
	public static final RegistryObject<EntityType<BoomerangEntity>> BOOMERANG = ENTITIES_REGISTRY.register("boomerang", () -> EntityType.Builder.<BoomerangEntity>of(BoomerangEntity::new, MobCategory.MISC).sized(0.3F, 0.3F).clientTrackingRange(4).updateInterval(20).build(prefix("boomerang")));
    public static final RegistryObject<EntityType<SpinBlade>> SPIN_BLADE = ENTITIES_REGISTRY.register("spin_blade", () -> EntityType.Builder.<SpinBlade>of(SpinBlade::new, MobCategory.MISC).sized(0.4F, 0.4F).clientTrackingRange(6).updateInterval(20).build(prefix("spin_blade")));


	@SubscribeEvent
	public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
		event.put(HUNTERILLAGER.get(), Hunter.createAttributes().build());
        event.put(RUDEHOG.get(), RudeHog.createAttributes().build());
        event.put(HUNTER_BOAR.get(), HunterBoar.createAttributes().build());
	}

	private static String prefix(String path) {
		return "hunters_return." + path;
	}
}

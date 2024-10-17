package baguchi.hunters_return.init;

import baguchi.hunters_return.HuntersReturn;
import baguchi.hunters_return.entity.Hunter;
import baguchi.hunters_return.entity.projectile.BoomerangEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@EventBusSubscriber(modid = baguchi.hunters_return.HuntersReturn.MODID, bus = EventBusSubscriber.Bus.MOD)
public class HunterEntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITIES_REGISTRY = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, HuntersReturn.MODID);


    public static final Supplier<EntityType<Hunter>> HUNTERILLAGER = ENTITIES_REGISTRY.register("hunter", () -> EntityType.Builder.of(Hunter::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build(prefix("hunter")));
    public static final Supplier<EntityType<BoomerangEntity>> BOOMERANG = ENTITIES_REGISTRY.register("boomerang", () -> EntityType.Builder.<BoomerangEntity>of(BoomerangEntity::new, MobCategory.MISC).sized(0.4F, 0.4F).clientTrackingRange(4).updateInterval(20).build(prefix("boomerang")));


	@SubscribeEvent
	public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
		event.put(HUNTERILLAGER.get(), Hunter.createAttributes().build());
	}

    @SubscribeEvent
    public static void registerEntitySpawn(RegisterSpawnPlacementsEvent event) {
        event.register(HunterEntityRegistry.HUNTERILLAGER.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    private static ResourceKey<EntityType<?>> prefix(String path) {
        return ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(HuntersReturn.MODID, path));
    }

}

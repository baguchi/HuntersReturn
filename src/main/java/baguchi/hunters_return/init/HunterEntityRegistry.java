package baguchi.hunters_return.init;

import baguchi.hunters_return.HuntersReturn;
import baguchi.hunters_return.entity.Hunter;
import baguchi.hunters_return.entity.projectile.BoomerangEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = baguchi.hunters_return.HuntersReturn.MODID)
public class HunterEntityRegistry {
    public static final DeferredRegister.Entities ENTITIES_REGISTRY = DeferredRegister.createEntities(HuntersReturn.MODID);


    public static final DeferredHolder<EntityType<?>, EntityType<Hunter>> HUNTERILLAGER = ENTITIES_REGISTRY.registerEntityType("hunter", Hunter::new, MobCategory.MONSTER, (builder) -> builder.sized(0.6F, 1.95F).passengerAttachments(2.0F).ridingOffset(-0.6F).clientTrackingRange(8).notInPeaceful());
    public static final DeferredHolder<EntityType<?>, EntityType<BoomerangEntity>> BOOMERANG = ENTITIES_REGISTRY.registerEntityType("boomerang", BoomerangEntity::new, MobCategory.MISC, (builder) -> builder.sized(0.4F, 0.4F).clientTrackingRange(4).updateInterval(20));


	@SubscribeEvent
	public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
		event.put(HUNTERILLAGER.get(), Hunter.createAttributes().build());
	}

    @SubscribeEvent
    public static void registerEntitySpawn(RegisterSpawnPlacementsEvent event) {
        event.register(HunterEntityRegistry.HUNTERILLAGER.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    private static ResourceKey<EntityType<?>> prefix(String path) {
        return ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(HuntersReturn.MODID, path));
    }

}

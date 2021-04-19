package baguchan.hunterillager.event;

import baguchan.hunterillager.HunterIllager;
import baguchan.hunterillager.entity.HunterIllagerEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.world.spawner.WanderingTraderSpawner;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HunterIllager.MODID)
public class EntityEvent {

	@SubscribeEvent()
	public static void addSpawn(EntityJoinWorldEvent event) {
		if(event.getEntity() instanceof VillagerEntity){
			VillagerEntity abstractVillagerEntity = (VillagerEntity) event.getEntity();

			abstractVillagerEntity.goalSelector.addGoal(1, new AvoidEntityGoal((CreatureEntity)abstractVillagerEntity, HunterIllagerEntity.class, 16.0F ,0.8F, 0.85F));
		}

		if(event.getEntity() instanceof WanderingTraderEntity){
			WanderingTraderEntity wanderingTraderEntity = (WanderingTraderEntity) event.getEntity();

			wanderingTraderEntity.goalSelector.addGoal(1, new AvoidEntityGoal((CreatureEntity)wanderingTraderEntity, HunterIllagerEntity.class, 16.0F ,0.8F, 0.85F));
		}
	}
}

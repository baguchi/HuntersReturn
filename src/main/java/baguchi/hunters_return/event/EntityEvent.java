package baguchi.hunters_return.event;

import baguchi.hunters_return.entity.Hunter;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@EventBusSubscriber(modid = baguchi.hunters_return.HuntersReturn.MODID)
public class EntityEvent {

	@SubscribeEvent()
	public static void addSpawn(EntityJoinLevelEvent event) {
		if (event.getEntity() instanceof Villager) {
			Villager abstractVillager = (Villager) event.getEntity();

			abstractVillager.goalSelector.addGoal(1, new AvoidEntityGoal((PathfinderMob) abstractVillager, Hunter.class, 16.0F, 0.8F, 0.85F));
		}

		if (event.getEntity() instanceof WanderingTrader) {
			WanderingTrader wanderingTraderEntity = (WanderingTrader) event.getEntity();

			wanderingTraderEntity.goalSelector.addGoal(1, new AvoidEntityGoal((PathfinderMob) wanderingTraderEntity, Hunter.class, 16.0F, 0.8F, 0.85F));
		}
	}
}

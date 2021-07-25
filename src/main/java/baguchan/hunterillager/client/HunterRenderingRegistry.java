package baguchan.hunterillager.client;

import baguchan.hunterillager.HunterIllager;
import baguchan.hunterillager.client.model.HunterIllagerModel;
import baguchan.hunterillager.client.render.BoomerangRender;
import baguchan.hunterillager.client.render.HunterIllagerRender;
import baguchan.hunterillager.init.HunterEntityRegistry;
import baguchan.hunterillager.init.ModModelLayers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = HunterIllager.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HunterRenderingRegistry {
	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(HunterEntityRegistry.HUNTERILLAGER, HunterIllagerRender::new);
		event.registerEntityRenderer(HunterEntityRegistry.BOOMERANG, BoomerangRender::new);
	}

	@SubscribeEvent
	public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModModelLayers.HUNTERILLAGER, HunterIllagerModel::createBodyLayer);
	}
}

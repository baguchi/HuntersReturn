package baguchan.hunters_return.client;

import baguchan.hunters_return.client.model.HunterModel;
import baguchan.hunters_return.client.model.OldHunterModel;
import baguchan.hunters_return.client.render.BoomerangRender;
import baguchan.hunters_return.client.render.HunterRender;
import baguchan.hunters_return.init.HunterEntityRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = baguchan.hunters_return.HuntersReturn.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class HunterRenderingRegistry {
    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(HunterEntityRegistry.HUNTERILLAGER.get(), HunterRender::new);
        event.registerEntityRenderer(HunterEntityRegistry.BOOMERANG.get(), BoomerangRender::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.HUNTER, HunterModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.HUNTER_OLD, OldHunterModel::createBodyLayer);
    }
}

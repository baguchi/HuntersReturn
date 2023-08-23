package baguchan.hunters_return.client;

import baguchan.hunters_return.client.model.HunterModel;
import baguchan.hunters_return.client.model.RudeHogModel;
import baguchan.hunters_return.client.model.SpinBladeModel;
import baguchan.hunters_return.client.render.*;
import baguchan.hunters_return.init.HunterEntityRegistry;
import baguchan.hunters_return.init.HunterItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = baguchan.hunters_return.HuntersReturn.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HunterRenderingRegistry {
    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(HunterEntityRegistry.HUNTERILLAGER.get(), HunterRender::new);
        event.registerEntityRenderer(HunterEntityRegistry.RUDEHOG.get(), RudeHogRenderer::new);
        event.registerEntityRenderer(HunterEntityRegistry.BOOMERANG.get(), BoomerangRender::new);
        event.registerEntityRenderer(HunterEntityRegistry.HUNTER_BOAR.get(), HunterBoarRenderer::new);
        event.registerEntityRenderer(HunterEntityRegistry.SPIN_BLADE.get(), SpinBladeRenderer::new);

    }

    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.HUNTER, HunterModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.RUDEHOG, RudeHogModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SPIN_BLADE, SpinBladeModel::createBodyLayer);

    }

    @SubscribeEvent
    public static void modelBake(ModelEvent.ModifyBakingResult event) {
        HunterItems.addItemModelProperties();
    }
}

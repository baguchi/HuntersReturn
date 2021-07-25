package baguchan.hunterillager.client;

import baguchan.hunterillager.client.model.HunterIllagerModel;
import baguchan.hunterillager.init.HunterEntityRegistry;
import baguchan.hunterillager.init.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fmlclient.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class HunterRenderingRegistry {
	public static void registerRenderers() {
		EntityRenderers.register(HunterEntityRegistry.HUNTERILLAGER, baguchan.hunterillager.client.render.HunterIllagerRender::new);
		EntityRenderers.register(HunterEntityRegistry.BOOMERANG, baguchan.hunterillager.client.render.BoomerangRender::new);
		RenderingRegistry.registerLayerDefinition(ModModelLayers.HUNTERILLAGER, HunterIllagerModel::createBodyLayer);
	}
}

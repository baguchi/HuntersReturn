package baguchan.hunterillager.client;

import baguchan.hunterillager.init.HunterEntityRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class HunterRenderingRegistry {
	public static void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(HunterEntityRegistry.HUNTERILLAGER, baguchan.hunterillager.client.render.HunterIllagerRender::new);
		RenderingRegistry.registerEntityRenderingHandler(HunterEntityRegistry.BOOMERANG, baguchan.hunterillager.client.render.BoomerangRender::new);
	}
}

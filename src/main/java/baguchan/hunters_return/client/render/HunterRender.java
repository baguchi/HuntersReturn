package baguchan.hunters_return.client.render;

import bagu_chan.bagus_lib.client.layer.CustomArmorLayer;
import baguchan.hunters_return.client.ModModelLayers;
import baguchan.hunters_return.client.model.HunterModel;
import baguchan.hunters_return.entity.Hunter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HunterRender<T extends Hunter> extends MobRenderer<T, HunterModel<T>> {
	private static final ResourceLocation ILLAGER = new ResourceLocation(baguchan.hunters_return.HuntersReturn.MODID, "textures/entity/hunter/hunter.png");
	private static final ResourceLocation ILLAGER_SLEEP = new ResourceLocation(baguchan.hunters_return.HuntersReturn.MODID, "textures/entity/hunter/hunter_sleep.png");

	public HunterRender(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new HunterModel<>(renderManagerIn.bakeLayer(ModModelLayers.HUNTER)), 0.5F);
		this.addLayer(new CustomArmorLayer<>(this, renderManagerIn));
		this.addLayer(new ItemInHandLayer<>(this, Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer()));
	}

	@Override
	public ResourceLocation getTextureLocation(T p_110775_1_) {
		if (p_110775_1_.isSleeping()) {
			return ILLAGER_SLEEP;
		}
		return ILLAGER;
	}
}

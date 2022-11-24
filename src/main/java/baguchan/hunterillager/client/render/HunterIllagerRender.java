package baguchan.hunterillager.client.render;

import baguchan.hunterillager.HunterIllager;
import baguchan.hunterillager.client.model.HunterModel;
import baguchan.hunterillager.entity.Hunter;
import baguchan.hunterillager.init.ModModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HunterIllagerRender<T extends Hunter> extends MobRenderer<T, HunterModel<T>> {
	private static final ResourceLocation ILLAGER = new ResourceLocation(HunterIllager.MODID, "textures/entity/hunter.png");

	public HunterIllagerRender(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new HunterModel<>(renderManagerIn.bakeLayer(ModModelLayers.HUNTERILLAGER)), 0.5F);
		this.addLayer(new ItemInHandLayer<>(this, Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer()) {
			public void render(PoseStack p_114989_, MultiBufferSource p_114990_, int p_114991_, T p_114992_, float p_114993_, float p_114994_, float p_114995_, float p_114996_, float p_114997_, float p_114998_) {
				if (p_114992_.isAggressive()) {
					super.render(p_114989_, p_114990_, p_114991_, p_114992_, p_114993_, p_114994_, p_114995_, p_114996_, p_114997_, p_114998_);
				}

			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(T p_110775_1_) {
		return ILLAGER;
	}
}

package baguchan.hunterillager.client.render;

import baguchan.hunterillager.client.model.HunterIllagerModel;
import baguchan.hunterillager.client.render.layer.CrossArmHeldItemLayer;
import baguchan.hunterillager.entity.HunterIllagerEntity;
import baguchan.hunterillager.init.ModModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HunterIllagerRender extends IllagerRenderer<HunterIllagerEntity> {
	private static final ResourceLocation ILLAGER = new ResourceLocation("hunterillager", "textures/entity/hunter_illager/hunter_illager.png");

	public HunterIllagerRender(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new HunterIllagerModel<>(renderManagerIn.bakeLayer(ModModelLayers.HUNTERILLAGER)), 0.5F);
		addLayer(new CrossArmHeldItemLayer<>(this));
		this.addLayer(new ItemInHandLayer<>(this) {
			public void render(PoseStack p_114989_, MultiBufferSource p_114990_, int p_114991_, HunterIllagerEntity p_114992_, float p_114993_, float p_114994_, float p_114995_, float p_114996_, float p_114997_, float p_114998_) {
				if (p_114992_.isAggressive()) {
					super.render(p_114989_, p_114990_, p_114991_, p_114992_, p_114993_, p_114994_, p_114995_, p_114996_, p_114997_, p_114998_);
				}

			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(HunterIllagerEntity p_110775_1_) {
		return ILLAGER;
	}
}

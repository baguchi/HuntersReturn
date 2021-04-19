package baguchan.hunterillager.client.render;

import baguchan.hunterillager.client.model.HunterIllagerModel;
import baguchan.hunterillager.client.render.layer.CrossArmHeldItemLayer;
import baguchan.hunterillager.entity.HunterIllagerEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.IllagerModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HunterIllagerRender extends IllagerRenderer<HunterIllagerEntity> {
	private static final ResourceLocation ILLAGER = new ResourceLocation("hunterillager", "textures/entity/hunter_illager/hunter_illager.png");

	public HunterIllagerRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new HunterIllagerModel<>(0.0F, 0.0F, 64, 128), 0.5F);
		addLayer(new HeadLayer<>(this));
		addLayer(new CrossArmHeldItemLayer<>(this));
		addLayer(new HeldItemLayer<HunterIllagerEntity, IllagerModel<HunterIllagerEntity>>(this) {
			public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, HunterIllagerEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				if (entitylivingbaseIn.isAggressive())
					super.render(matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(HunterIllagerEntity p_110775_1_) {
		return ILLAGER;
	}
}

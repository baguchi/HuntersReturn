package baguchan.hunters_return.client.render;

import baguchan.hunters_return.HuntersReturn;
import baguchan.hunters_return.client.ModModelLayers;
import baguchan.hunters_return.client.model.SpinBladeModel;
import baguchan.hunters_return.entity.projectile.SpinBlade;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpinBladeRenderer extends EntityRenderer<SpinBlade> {
	private static final ResourceLocation LOCATION = new ResourceLocation(HuntersReturn.MODID, "textures/entity/spin_blade.png");
	private final SpinBladeModel<SpinBlade> model;

	public SpinBladeRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager);
		this.model = new SpinBladeModel<>(renderManager.bakeLayer(ModModelLayers.SPIN_BLADE));
	}

	@Override
	public void render(SpinBlade entityIn, float entityYaw, float partialTicks, PoseStack stackIn, MultiBufferSource bufferIn, int packedLightIn) {
		stackIn.pushPose();
		stackIn.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot())));
		stackIn.mulPose(Axis.XP.rotationDegrees(Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
		stackIn.mulPose(Axis.YP.rotationDegrees(((entityIn.tickCount + partialTicks) * 60F)));

		stackIn.translate(0.0F, -1.501F, 0.0F);
		VertexConsumer vertexconsumer = bufferIn.getBuffer(this.model.renderType(LOCATION));
		this.model.renderToBuffer(stackIn, vertexconsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

		stackIn.popPose();
		super.render(entityIn, entityYaw, partialTicks, stackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(SpinBlade p_115034_) {
		return LOCATION;
	}
}

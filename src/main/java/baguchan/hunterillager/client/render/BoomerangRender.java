package baguchan.hunterillager.client.render;

import baguchan.hunterillager.entity.projectile.BoomerangEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoomerangRender extends EntityRenderer<BoomerangEntity> {
	private ItemRenderer itemRenderer;

	public BoomerangRender(EntityRendererManager renderManager) {
		super(renderManager);
		this.itemRenderer = Minecraft.getInstance().getItemRenderer();
	}

	public void render(BoomerangEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.pushPose();
		matrixStackIn.translate(0.0F, entityIn.getEyeHeight() / 2, 0.0F);
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.yRotO, entityIn.yRot)));
		matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(90.0F));
		matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-MathHelper.lerp(partialTicks, entityIn.xRotO, entityIn.xRot)));

		matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees((entityIn.tickCount + partialTicks + entityIn.getPiercingLevel() * 0.85F) * ((float) entityIn.getSpeed() * 80.0F)));

		this.itemRenderer.renderStatic(entityIn.getBoomerang(), ItemCameraTransforms.TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
		matrixStackIn.popPose();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(BoomerangEntity p_110775_1_) {
		return AtlasTexture.LOCATION_BLOCKS;
	}
}

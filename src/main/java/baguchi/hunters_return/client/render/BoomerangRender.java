package baguchi.hunters_return.client.render;

import baguchi.hunters_return.client.render.state.BoomerangRenderState;
import baguchi.hunters_return.entity.projectile.BoomerangEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoomerangRender extends EntityRenderer<BoomerangEntity, BoomerangRenderState> {
	private ItemRenderer itemRenderer;

	public BoomerangRender(EntityRendererProvider.Context renderManager) {
		super(renderManager);
		this.itemRenderer = Minecraft.getInstance().getItemRenderer();
	}

	@Override
	public void render(BoomerangRenderState renderState, PoseStack stackIn, MultiBufferSource bufferIn, int packedLightIn) {
		stackIn.pushPose();

		stackIn.translate(-0.0F, 0, 0.15F);
		stackIn.mulPose(Axis.YP.rotationDegrees(renderState.yRot));
		if (!renderState.inGround) {
			stackIn.mulPose(Axis.XP.rotationDegrees(renderState.xRot));
		}
		stackIn.mulPose(Axis.XP.rotationDegrees(90.0F));
		if (!renderState.inGround) {
			stackIn.mulPose(Axis.ZP.rotationDegrees((renderState.ageInTicks) * (((float) renderState.speed * 80.0F))));
		}
		stackIn.scale(1.25F, 1.25F, 1.25F);
		BakedModel bakedmodel = this.itemRenderer.getModel(renderState.boomerang, null, (LivingEntity) null, 0);

		this.itemRenderer.render(renderState.boomerang, ItemDisplayContext.GROUND, false, stackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY, bakedmodel);
		stackIn.popPose();
		super.render(renderState, stackIn, bufferIn, packedLightIn);

	}
	@Override
	public BoomerangRenderState createRenderState() {
		return new BoomerangRenderState();
	}

	public void extractRenderState(BoomerangEntity p_361771_, BoomerangRenderState p_364204_, float p_360538_) {
		super.extractRenderState(p_361771_, p_364204_, p_360538_);
		p_364204_.xRot = p_361771_.getXRot(p_360538_);
		p_364204_.yRot = p_361771_.getYRot(p_360538_);
		p_364204_.inGround = p_361771_.inGround;
		p_364204_.speed = (float) p_361771_.getSpeed();
		p_364204_.boomerang = p_361771_.getBoomerang();
	}
}

package baguchi.hunters_return.client.render;

import baguchi.hunters_return.client.render.state.BoomerangRenderState;
import baguchi.hunters_return.entity.projectile.BoomerangEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;


public class BoomerangRender extends EntityRenderer<BoomerangEntity, BoomerangRenderState> {
	private final ItemModelResolver itemModelResolver;


	public BoomerangRender(EntityRendererProvider.Context renderManager) {
		super(renderManager);
		this.itemModelResolver = renderManager.getItemModelResolver();
	}

    @Override
    public void submit(BoomerangRenderState renderState, PoseStack stackIn, SubmitNodeCollector bufferIn, CameraRenderState p_451076_) {
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
        stackIn.scale(1.4F, 1.4F, 1.4F);
        renderState.boomerang.submit(stackIn, bufferIn, renderState.lightCoords, OverlayTexture.NO_OVERLAY, renderState.outlineColor);
        stackIn.popPose();
        super.submit(renderState, stackIn, bufferIn, p_451076_);
    }

	@Override
	public BoomerangRenderState createRenderState() {
		return new BoomerangRenderState();
	}

	@Override
	public void extractRenderState(BoomerangEntity p_361771_, BoomerangRenderState p_364204_, float p_360538_) {
		super.extractRenderState(p_361771_, p_364204_, p_360538_);
		p_364204_.xRot = p_361771_.getXRot(p_360538_);
		p_364204_.yRot = p_361771_.getYRot(p_360538_);
		p_364204_.inGround = p_361771_.inGround;
		p_364204_.speed = (float) p_361771_.getSpeed();
		this.itemModelResolver.updateForNonLiving(p_364204_.boomerang, p_361771_.getBoomerang(), ItemDisplayContext.GROUND, p_361771_);
	}
}

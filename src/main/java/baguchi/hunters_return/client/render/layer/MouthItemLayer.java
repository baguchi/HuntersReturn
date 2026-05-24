package baguchi.hunters_return.client.render.layer;

import baguchi.hunters_return.client.model.HunterModel;
import baguchi.hunters_return.client.render.state.HunterRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class MouthItemLayer<T extends HunterRenderState, M extends HunterModel<T>> extends RenderLayer<T, M> {
    public MouthItemLayer(RenderLayerParent<T, M> p_116686_) {
        super(p_116686_);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, T state, float yRot, float xRot) {
        if (!state.mouthItem.isEmpty()) {
            poseStack.pushPose();

            getParentModel().everything.translateAndRotate(poseStack);
            getParentModel().body.translateAndRotate(poseStack);
            getParentModel().head.translateAndRotate(poseStack);
            poseStack.translate(0.0D, (double) -(1F / 16), (double) -0.3F);
            poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
            state.mouthItem.submit(poseStack, submitNodeCollector, lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor);

            poseStack.popPose();
        }
    }
}
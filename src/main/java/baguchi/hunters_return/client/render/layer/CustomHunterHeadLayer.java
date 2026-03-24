package baguchi.hunters_return.client.render.layer;

import baguchi.hunters_return.client.model.HunterModel;
import baguchi.hunters_return.client.render.state.HunterRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.object.skull.SkullModelBase;
import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Util;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.block.SkullBlock;

import java.util.function.Function;

public class CustomHunterHeadLayer<S extends HunterRenderState, M extends HunterModel<S> & HeadedModel> extends RenderLayer<S, M> {
    private static final float ITEM_SCALE = 0.625F;
    private static final float SKULL_SCALE = 1.1875F;
    private final CustomHeadLayer.Transforms transforms;
    private final Function<SkullBlock.Type, SkullModelBase> skullModels;
    private final PlayerSkinRenderCache playerSkinRenderCache;

    public CustomHunterHeadLayer(RenderLayerParent<S, M> renderer, EntityModelSet modelSet, PlayerSkinRenderCache playerSkinRenderCache) {
        this(renderer, modelSet, playerSkinRenderCache, CustomHeadLayer.Transforms.DEFAULT);
    }

    public CustomHunterHeadLayer(
            RenderLayerParent<S, M> renderer, EntityModelSet modelSet, PlayerSkinRenderCache playerSkinRenderCache, CustomHeadLayer.Transforms transforms
    ) {
        super(renderer);
        this.transforms = transforms;
        this.skullModels = Util.memoize(type -> SkullBlockRenderer.createModel(modelSet, type));
        this.playerSkinRenderCache = playerSkinRenderCache;
    }

    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, S state, float yRot, float xRot) {
        if (!state.headItem.isEmpty() || state.wornHeadType != null) {
            poseStack.pushPose();
            poseStack.scale(this.transforms.horizontalScale(), 1.0F, this.transforms.horizontalScale());
            M parentModel = this.getParentModel();
            parentModel.translateToHead(state, parentModel.head, poseStack);
            if (state.wornHeadType != null) {
                poseStack.translate(0.0F, this.transforms.skullYOffset(), 0.0F);
                poseStack.scale(1.1875F, -1.1875F, -1.1875F);
                poseStack.translate(-0.5, 0.0, -0.5);
                SkullBlock.Type type = state.wornHeadType;
                SkullModelBase skullModel = this.skullModels.apply(type);
                RenderType renderType = this.resolveSkullRenderType(state, type);
                SkullBlockRenderer.submitSkull(
                        0.0F, poseStack, submitNodeCollector, lightCoords, skullModel, renderType, state.outlineColor, null
                );
            } else {
                translateToHead(poseStack, this.transforms);
                state.headItem.submit(poseStack, submitNodeCollector, lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor);
            }

            poseStack.popPose();
        }
    }

    private RenderType resolveSkullRenderType(LivingEntityRenderState state, SkullBlock.Type type) {
        if (type == SkullBlock.Types.PLAYER) {
            ResolvableProfile profile = state.wornHeadProfile;
            if (profile != null) {
                return this.playerSkinRenderCache.getOrDefault(profile).renderType();
            }
        }

        return SkullBlockRenderer.getSkullRenderType(type, null);
    }

    public static void translateToHead(PoseStack poseStack, CustomHeadLayer.Transforms transforms) {
        poseStack.translate(0.0F, -0.25F + transforms.yOffset(), 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        poseStack.scale(0.625F, -0.625F, -0.625F);
    }
}

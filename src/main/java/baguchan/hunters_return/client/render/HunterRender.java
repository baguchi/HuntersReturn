package baguchan.hunters_return.client.render;

import bagu_chan.bagus_lib.client.layer.CustomArmorLayer;
import baguchan.hunters_return.HunterConfig;
import baguchan.hunters_return.HuntersReturn;
import baguchan.hunters_return.client.ModModelLayers;
import baguchan.hunters_return.client.model.HunterModel;
import baguchan.hunters_return.client.model.NewHunterModel;
import baguchan.hunters_return.client.model.OldHunterModel;
import baguchan.hunters_return.entity.Hunter;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HunterRender<T extends Hunter> extends MobRenderer<T, HunterModel<T>> {
    private static final ResourceLocation ILLAGER = ResourceLocation.fromNamespaceAndPath(baguchan.hunters_return.HuntersReturn.MODID, "textures/entity/hunter/hunter.png");
    private static final ResourceLocation ILLAGER_OLD = ResourceLocation.fromNamespaceAndPath(baguchan.hunters_return.HuntersReturn.MODID, "textures/entity/hunter/hunter_old.png");
	private static final RenderType SLEEP_EYE = RenderType.entityCutout(ResourceLocation.fromNamespaceAndPath(HuntersReturn.MODID, "textures/entity/hunter/sleep_eye.png"));
	private static final RenderType SLEEP_EYE_OLD = RenderType.entityCutout(ResourceLocation.fromNamespaceAndPath(HuntersReturn.MODID, "textures/entity/hunter/sleep_eye_old.png"));
	private static final RenderType EYE = RenderType.entityCutout(ResourceLocation.fromNamespaceAndPath(HuntersReturn.MODID, "textures/entity/hunter/eye.png"));

	private final HunterModel<T> old;
	private final HunterModel<T> normal = this.getModel();

	public HunterRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new NewHunterModel<>(renderManagerIn.bakeLayer(ModModelLayers.HUNTER)), 0.5F);
		this.addLayer(new CustomArmorLayer<>(this, renderManagerIn));
		this.addLayer(new ItemInHandLayer<>(this, Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer()));
		this.addLayer(new EyesLayer<T, HunterModel<T>>(this) {

			@Override
			public void render(PoseStack p_116983_, MultiBufferSource p_116984_, int p_116985_, T entity, float p_116987_, float p_116988_, float particalTick, float p_116990_, float p_116991_, float p_116992_) {
				float f3 = (float) entity.tickCount + particalTick;


				if (!entity.isInvisible() && (0 > Math.sin(f3 * 0.05F) + Math.sin(f3 * 0.13F) + Math.sin(f3 * 0.7F) + 2.55F || entity.isSleeping())) {
					super.render(p_116983_, p_116984_, p_116985_, entity, p_116987_, p_116988_, particalTick, p_116990_, p_116991_, p_116992_);
				} else if (!HunterConfig.CLIENT.eyeMoving.getAsBoolean()) {
					VertexConsumer vertexconsumer = p_116984_.getBuffer(EYE);
					this.getParentModel().renderToBuffer(p_116983_, vertexconsumer, 15728640, OverlayTexture.NO_OVERLAY);
				}
			}

			@Override
			public RenderType renderType() {
				if (HunterConfig.CLIENT.oldModel.get()) {
					return SLEEP_EYE_OLD;
				}
				return SLEEP_EYE;
			}
		});
		this.old = new OldHunterModel<>(renderManagerIn.bakeLayer(ModModelLayers.HUNTER_OLD));
	}

	@Override
	public void render(T p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_) {
		super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
		if (HunterConfig.CLIENT.oldModel.get()) {
			this.model = this.old;
		} else {
			this.model = this.normal;
		}
	}

	@Override
	public ResourceLocation getTextureLocation(T p_110775_1_) {
		if (HunterConfig.CLIENT.oldModel.get()) {
            if (p_110775_1_.getTextureOld() != null) {
                return p_110775_1_.getTextureOld();
			} else {
				return ILLAGER_OLD;
			}
		}
        if (p_110775_1_.getTexture() != null) {
            return p_110775_1_.getTexture();
		}
		return ILLAGER;
	}
}

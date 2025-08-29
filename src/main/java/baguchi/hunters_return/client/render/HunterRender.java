package baguchi.hunters_return.client.render;

import baguchi.bagus_lib.client.layer.CustomArmorLayer;
import baguchi.hunters_return.HunterConfig;
import baguchi.hunters_return.HuntersReturn;
import baguchi.hunters_return.client.ModModelLayers;
import baguchi.hunters_return.client.model.HunterModel;
import baguchi.hunters_return.client.model.NewHunterModel;
import baguchi.hunters_return.client.model.OldHunterModel;
import baguchi.hunters_return.client.render.layer.MouthItemLayer;
import baguchi.hunters_return.client.render.state.HunterRenderState;
import baguchi.hunters_return.entity.Hunter;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractIllager;


public class HunterRender extends MobRenderer<Hunter, HunterRenderState, HunterModel<HunterRenderState>> {
    private static final ResourceLocation ILLAGER = ResourceLocation.fromNamespaceAndPath(HuntersReturn.MODID, "textures/entity/hunter/hunter.png");
    private static final ResourceLocation ILLAGER_OLD = ResourceLocation.fromNamespaceAndPath(HuntersReturn.MODID, "textures/entity/hunter/normal_old.png");

    private static final RenderType SLEEP_EYE = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(HuntersReturn.MODID, "textures/entity/hunter/sleep_eye.png"));
    private static final RenderType SLEEP_EYE_OLD = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(HuntersReturn.MODID, "textures/entity/hunter/sleep_eye_old.png"));

	private final HunterModel<HunterRenderState> old;
	private final HunterModel<HunterRenderState> normal;

	public HunterRender(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new NewHunterModel<>(renderManagerIn.bakeLayer(ModModelLayers.HUNTER)), 0.5F);
		this.addLayer(new CustomArmorLayer<>(this, renderManagerIn));
		this.addLayer(new ItemInHandLayer<>(this));
		this.addLayer(new MouthItemLayer<>(this));
        this.addLayer(new EyesLayer<>(this) {
            @Override
            public void render(PoseStack p_116983_, MultiBufferSource p_116984_, int p_116985_, HunterRenderState p_363277_, float p_116987_, float p_116988_) {
                float f3 = (p_363277_.ageInTicks + p_363277_.id);


                if (!p_363277_.isInvisible && (0 > Math.sin(f3 * 0.05F) + Math.sin(f3 * 0.13F) + Math.sin(f3 * 0.7F) + 2.55F || p_363277_.sleep)) {
                    super.render(p_116983_, p_116984_, p_116985_, p_363277_, p_116987_, p_116988_);
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
		this.old = new OldHunterModel(renderManagerIn.bakeLayer(ModModelLayers.HUNTER_OLD));
		this.normal = new NewHunterModel<>(renderManagerIn.bakeLayer(ModModelLayers.HUNTER));
	}

	@Override
	public void render(HunterRenderState p_361886_, PoseStack p_115311_, MultiBufferSource p_115312_, int p_115313_) {
		super.render(p_361886_, p_115311_, p_115312_, p_115313_);
		if (HunterConfig.CLIENT.oldModel.get()) {
			this.model = this.old;
		} else {
			this.model = this.normal;
		}
	}

	@Override
	public HunterRenderState createRenderState() {
		return new HunterRenderState();
	}

	@Override
	public void extractRenderState(Hunter hunter, HunterRenderState hunterState, float p_361157_) {
		super.extractRenderState(hunter, hunterState, p_361157_);
		ArmedEntityRenderState.extractArmedEntityRenderState(hunter, hunterState, this.itemModelResolver);
		HunterRenderState.extractMouthEntityRenderState(hunter, hunterState, this.itemModelResolver);

		hunterState.isRiding = hunter.isPassenger();
		hunterState.mainArm = hunter.getMainArm();
		hunterState.armPose = hunter.getArmPose();
		hunterState.maxCrossbowChargeDuration = hunterState.armPose == AbstractIllager.IllagerArmPose.CROSSBOW_CHARGE
				? hunter.getUseItem().getUseDuration(hunter)
				: 0;
		hunterState.ticksUsingItem = hunter.getTicksUsingItem();
		hunterState.attackAnim = hunter.getAttackAnim(p_361157_);
		hunterState.isAggressive = hunter.isAggressive();

		hunterState.attackAnimationState.copyFrom(hunter.attackAnimationState);
		hunterState.chargeAnimationState.copyFrom(hunter.chargeAnimationState);
		hunterState.shootAnimationState.copyFrom(hunter.shootAnimationState);
		hunterState.thrownAnimationState.copyFrom(hunter.thrownAnimationState);
		hunterState.dodghRightAnimationState.copyFrom(hunter.dodghRightAnimationState);
		hunterState.dodghLeftAnimationState.copyFrom(hunter.dodghLeftAnimationState);
        hunterState.texture = hunter.getTexture();
        hunterState.textureOld = hunter.getTextureOld();
        hunterState.eyeRot = (hunter.getViewYRot(p_361157_) - hunter.getPreciseBodyRotation(p_361157_) + 180);
		hunterState.sleep = hunter.isSleeping();
        hunterState.id = hunter.getId();
	}

	@Override
    public ResourceLocation getTextureLocation(HunterRenderState hunterRenderState) {
		if (HunterConfig.CLIENT.oldModel.get()) {
            if (hunterRenderState.textureOld != null) {
                return hunterRenderState.textureOld;
            }
            return ILLAGER_OLD;
        }
        if (hunterRenderState.texture != null) {
            return hunterRenderState.texture;
        }
		return ILLAGER;
	}
}

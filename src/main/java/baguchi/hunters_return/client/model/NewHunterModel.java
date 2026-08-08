package baguchi.hunters_return.client.model;


import baguchi.hunters_return.client.animation.HunterAnimations;
import baguchi.hunters_return.client.render.state.HunterRenderState;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.monster.illager.AbstractIllager;

public class NewHunterModel<T extends HunterRenderState> extends HunterModel<T> {
    private final KeyframeAnimation walkAnimation;
    private final KeyframeAnimation walkRightAttackAnimation;
    private final KeyframeAnimation walkLeftAttackAnimation;
    private final KeyframeAnimation shootRightAnimation;
    private final KeyframeAnimation shootLeftAnimation;
    private final KeyframeAnimation chargeRightAnimation;
    private final KeyframeAnimation chargeLeftAnimation;
    private final KeyframeAnimation thrownRightAnimation;
    private final KeyframeAnimation thrownLeftAnimation;
    private final KeyframeAnimation attackRightAnimation;
    private final KeyframeAnimation attackLeftAnimation;
    public final ModelPart body;
    public final ModelPart everything;
    private final ModelPart LeftLeg;
    private final ModelPart RightLeg;
    private final ModelPart RightArm;
    private final ModelPart LeftArm;
    private final ModelPart root;
    public final ModelPart head;
    private final ModelPart nose;
    private final ModelPart cape;
    private final ModelPart rightEye;
    private final ModelPart leftEye;

    public NewHunterModel(ModelPart root) {
        super(root);
        this.root = root;
        this.everything = root.getChild("everything");
        this.body = this.everything.getChild("body");
        this.cape = this.body.getChild("cape");
        this.LeftLeg = this.everything.getChild("left_leg");
        this.RightLeg = this.everything.getChild("right_leg");
        this.RightArm = this.body.getChild("right_arm");
        this.LeftArm = this.body.getChild("left_arm");
        this.head = this.body.getChild("head");
        this.rightEye = this.head.getChild("rightEye");
        this.leftEye = this.head.getChild("leftEye");
        this.nose = this.head.getChild("nose");
        this.walkAnimation = HunterAnimations.walk.bake(root);
        this.walkRightAttackAnimation = HunterAnimations.right_walk_attack.bake(root);
        this.walkLeftAttackAnimation = HunterAnimations.left_walk_attack.bake(root);
        this.shootRightAnimation = HunterAnimations.right_shot.bake(root);
        this.shootLeftAnimation = HunterAnimations.left_shot.bake(root);
        this.chargeRightAnimation = HunterAnimations.right_attack_range_charge.bake(root);
        this.chargeLeftAnimation = HunterAnimations.left_attack_range_charge.bake(root);
        this.thrownRightAnimation = HunterAnimations.right_attack_melee.bake(root);
        this.thrownLeftAnimation = HunterAnimations.left_attack_melee.bake(root);
        this.attackRightAnimation = HunterAnimations.right_attack_melee.bake(root);
        this.attackLeftAnimation = HunterAnimations.left_attack_melee.bake(root);
    }

    @Override
    public void setupAnim(T entityIn) {
        this.rightArmPose = HumanoidModel.ArmPose.EMPTY;
        this.leftArmPose = HumanoidModel.ArmPose.EMPTY;
        AbstractIllager.IllagerArmPose abstractillager$illagerarmpose = entityIn.armPose;

        super.setupAnim(entityIn);
        this.head.yRot = entityIn.yRot * ((float) Math.PI / 180F);
        this.head.xRot = entityIn.xRot * ((float) Math.PI / 180F);
        if (entityIn.isRiding) {
            this.RightArm.xRot = (-(float) Math.PI / 5F);
            this.RightArm.yRot = 0.0F;
            this.RightArm.zRot = 0.0F;
            this.LeftArm.xRot = (-(float) Math.PI / 5F);
            this.LeftArm.yRot = 0.0F;
            this.LeftArm.zRot = 0.0F;
            this.RightLeg.xRot = -1.4137167F;
            this.RightLeg.yRot = ((float) Math.PI / 10F);
            this.RightLeg.zRot = 0.07853982F;
            this.LeftLeg.xRot = -1.4137167F;
            this.LeftLeg.yRot = (-(float) Math.PI / 10F);
            this.LeftLeg.zRot = -0.07853982F;
        } else {
            if (!(entityIn.dodghRightAnimationState.isStarted() || entityIn.dodghLeftAnimationState.isStarted())) {
                if (entityIn.chargeAnimationState.isStarted()) {
                    if (entityIn.mainArm == HumanoidArm.RIGHT) {
                        this.walkRightAttackAnimation.applyWalk(entityIn.walkAnimationPos, entityIn.walkAnimationSpeed, 1, 1.5F);

                    } else {
                        this.walkLeftAttackAnimation.applyWalk(entityIn.walkAnimationPos, entityIn.walkAnimationSpeed, 1, 1.5F);
                    }
                } else if (!entityIn.sleep) {
                    this.walkAnimation.applyWalk(entityIn.walkAnimationPos, entityIn.walkAnimationSpeed, 1, 1.5F);
                }
            } else {
                dodghRightAnimation.apply(entityIn.dodghRightAnimationState, entityIn.ageInTicks);
                dodghLeftAnimation.apply(entityIn.dodghLeftAnimationState, entityIn.ageInTicks);
            }
        }
        if (abstractillager$illagerarmpose == AbstractIllager.IllagerArmPose.CROSSBOW_HOLD) {
            AnimationUtils.animateCrossbowHold(this.RightArm, this.LeftArm, this.head, true);
        } else if (abstractillager$illagerarmpose == AbstractIllager.IllagerArmPose.CROSSBOW_CHARGE) {
            AnimationUtils.animateCrossbowCharge(this.RightArm, this.LeftArm, entityIn.ageInTicks, entityIn.maxCrossbowChargeDuration, true);
        } else {
            if (entityIn.boomerangUsing) {
                if (entityIn.mainArm == HumanoidArm.RIGHT) {
                    this.shootLeftAnimation.apply(entityIn.shootAnimationState, entityIn.ageInTicks);
                    this.chargeLeftAnimation.apply(entityIn.chargeAnimationState, entityIn.ageInTicks);
                    this.attackLeftAnimation.apply(entityIn.attackAnimationState, entityIn.ageInTicks, 1.5F);
                    this.thrownLeftAnimation.apply(entityIn.thrownAnimationState, entityIn.ageInTicks);
                } else {
                    this.shootRightAnimation.apply(entityIn.shootAnimationState, entityIn.ageInTicks);
                    this.chargeRightAnimation.apply(entityIn.chargeAnimationState, entityIn.ageInTicks);
                    this.attackRightAnimation.apply(entityIn.attackAnimationState, entityIn.ageInTicks, 1.5F);
                    this.thrownRightAnimation.apply(entityIn.thrownAnimationState, entityIn.ageInTicks);
                }
            } else {
                if (entityIn.mainArm == HumanoidArm.RIGHT) {
                    this.shootRightAnimation.apply(entityIn.shootAnimationState, entityIn.ageInTicks);
                    this.chargeRightAnimation.apply(entityIn.chargeAnimationState, entityIn.ageInTicks);
                    this.attackRightAnimation.apply(entityIn.attackAnimationState, entityIn.ageInTicks, 1.5F);
                    this.thrownRightAnimation.apply(entityIn.thrownAnimationState, entityIn.ageInTicks);

                } else {
                    this.shootLeftAnimation.apply(entityIn.shootAnimationState, entityIn.ageInTicks);
                    this.chargeLeftAnimation.apply(entityIn.chargeAnimationState, entityIn.ageInTicks);
                    this.attackLeftAnimation.apply(entityIn.attackAnimationState, entityIn.ageInTicks, 1.5F);
                    this.thrownLeftAnimation.apply(entityIn.thrownAnimationState, entityIn.ageInTicks);
                }
            }
        }

        float f3 = (entityIn.ageInTicks + entityIn.id);

        this.rightEye.visible = !entityIn.sleep && !(0 > Math.sin(f3 * 0.05F) + Math.sin(f3 * 0.13F) + Math.sin(f3 * 0.7F) + 2.55F);
        this.leftEye.visible = !entityIn.sleep && !(0 > Math.sin(f3 * 0.05F) + Math.sin(f3 * 0.13F) + Math.sin(f3 * 0.7F) + 2.55F);
        this.rightEye.x -= (Mth.clamp((entityIn.eyeRot % 360 - 180) / 90F, 0.0F, 0.5F));
        this.leftEye.x -= (Mth.clamp((entityIn.eyeRot % 360 - 180) / 90F, -0.5F, 0.0F));
        if (!entityIn.mouthItem.isEmpty()) {
            this.nose.xRot = -0.5F;
        }
    }
}
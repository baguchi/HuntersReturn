package baguchi.hunters_return.client.model;


import baguchi.hunters_return.client.render.state.HunterRenderState;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.illager.AbstractIllager;

public class OldHunterModel<T extends HunterRenderState> extends HunterModel<T> {
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

    public OldHunterModel(ModelPart root) {
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
        this.nose = this.head.getChild("nose");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition everything = partdefinition.addOrReplaceChild("everything", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition left_leg = everything.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -12.0F, 0.0F));

        PartDefinition right_leg = everything.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -12.0F, 0.0F));

        PartDefinition body = everything.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, -12.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(1, 38).addBox(-4.0F, -11.75F, -2.5F, 8.0F, 14.0F, 5.0F, new CubeDeformation(0.75F)), PartPose.offset(0.0F, -12.0F, 0.0F));

        PartDefinition cape = body.addOrReplaceChild("cape", CubeListBuilder.create().texOffs(0, 64).addBox(-4.5F, 0.0F, 0.0F, 9.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 76).mirror().addBox(-4.5F, 11.0F, 0.0F, 9.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -12.0F, 3.0F, 0.1309F, 0.0F, 0.0F));

        PartDefinition quiver = cape.addOrReplaceChild("quiver", CubeListBuilder.create().texOffs(20, 64).addBox(-2.5F, 0.0F, 1.0F, 5.0F, 13.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

        PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -3.0F));

        PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.15F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 46).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -10.0F, 0.0F));

        PartDefinition leftHand = left_arm.addOrReplaceChild("leftHand", CubeListBuilder.create(), PartPose.offset(1.0F, 9.5F, 0.0F));

        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 46).mirror().addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, -10.0F, 0.0F));

        PartDefinition rightHand = right_arm.addOrReplaceChild("rightHand", CubeListBuilder.create(), PartPose.offset(-1.0F, 11.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 128);
    }


    @Override
    public void setupAnim(T entityIn) {
        this.rightArmPose = HumanoidModel.ArmPose.EMPTY;
        this.leftArmPose = HumanoidModel.ArmPose.EMPTY;
        AbstractIllager.IllagerArmPose abstractillager$illagerarmpose = entityIn.armPose;

        super.setupAnim(entityIn);


        if (!entityIn.dodghRightAnimationState.isStarted() && !entityIn.dodghLeftAnimationState.isStarted()) {
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
                this.RightArm.xRot = Mth.cos(entityIn.walkAnimationPos * 0.6662F + 3.1415927F) * 2.0F * entityIn.walkAnimationSpeed * 0.5F;
                this.RightArm.yRot = 0.0F;
                this.RightArm.zRot = 0.0F;
                this.LeftArm.xRot = Mth.cos(entityIn.walkAnimationPos * 0.6662F) * 2.0F * entityIn.walkAnimationSpeed * 0.5F;
                this.LeftArm.yRot = 0.0F;
                this.LeftArm.zRot = 0.0F;
                this.RightLeg.xRot = Mth.cos(entityIn.walkAnimationPos * 0.6662F) * 1.4F * entityIn.walkAnimationSpeed * 0.5F;
                this.RightLeg.yRot = 0.0F;
                this.RightLeg.zRot = 0.0F;
                this.LeftLeg.xRot = Mth.cos(entityIn.walkAnimationPos * 0.6662F + 3.1415927F) * 1.4F * entityIn.walkAnimationSpeed * 0.5F;
                this.LeftLeg.yRot = 0.0F;
                this.LeftLeg.zRot = 0.0F;
            }

            if (abstractillager$illagerarmpose == AbstractIllager.IllagerArmPose.ATTACKING) {
                if (entityIn.getMainHandItemStack().isEmpty()) {
                    AnimationUtils.animateZombieArms(this.LeftArm, this.RightArm, true, entityIn);
                } else {
                    AnimationUtils.swingWeaponDown(this.RightArm, this.LeftArm, entityIn.mainArm, entityIn.attackAnim, entityIn.ageInTicks);
                }
            } else if (abstractillager$illagerarmpose == AbstractIllager.IllagerArmPose.SPELLCASTING) {
                this.RightArm.z = 0.0F;
                this.RightArm.x = -5.0F;
                this.LeftArm.z = 0.0F;
                this.LeftArm.x = 5.0F;
                this.RightArm.xRot = Mth.cos(entityIn.ageInTicks * 0.6662F) * 0.25F;
                this.LeftArm.xRot = Mth.cos(entityIn.ageInTicks * 0.6662F) * 0.25F;
                this.RightArm.zRot = 2.3561945F;
                this.LeftArm.zRot = -2.3561945F;
                this.RightArm.yRot = 0.0F;
                this.LeftArm.yRot = 0.0F;
            } else if (abstractillager$illagerarmpose == AbstractIllager.IllagerArmPose.BOW_AND_ARROW) {
                this.RightArm.yRot = -0.1F + this.head.yRot;
                this.RightArm.xRot = -1.5707964F + this.head.xRot;
                this.LeftArm.xRot = -0.9424779F + this.head.xRot;
                this.LeftArm.yRot = this.head.yRot - 0.4F;
                this.LeftArm.zRot = 1.5707964F;
            } else if (abstractillager$illagerarmpose == AbstractIllager.IllagerArmPose.CROSSBOW_HOLD) {
                AnimationUtils.animateCrossbowHold(this.RightArm, this.LeftArm, this.head, true);
            } else if (abstractillager$illagerarmpose == AbstractIllager.IllagerArmPose.CROSSBOW_CHARGE) {
                AnimationUtils.animateCrossbowCharge(this.RightArm, this.LeftArm, entityIn.ageInTicks, entityIn.maxCrossbowChargeDuration, true);
            } else if (abstractillager$illagerarmpose == AbstractIllager.IllagerArmPose.CELEBRATING) {
                this.RightArm.z = 0.0F;
                this.RightArm.x = -5.0F;
                this.RightArm.xRot = Mth.cos(entityIn.ageInTicks * 0.6662F) * 0.05F;
                this.RightArm.zRot = 2.670354F;
                this.RightArm.yRot = 0.0F;
                this.LeftArm.z = 0.0F;
                this.LeftArm.x = 5.0F;
                this.LeftArm.xRot = Mth.cos(entityIn.ageInTicks * 0.6662F) * 0.05F;
                this.LeftArm.zRot = -2.3561945F;
                this.LeftArm.yRot = 0.0F;
            }
        } else {
            dodghRightAnimation.apply(entityIn.dodghRightAnimationState, entityIn.ageInTicks);
            dodghLeftAnimation.apply(entityIn.dodghLeftAnimationState, entityIn.ageInTicks);
        }
        if (!entityIn.mouthItem.isEmpty()) {
            this.nose.xRot = -0.5F;
        }
    }
}
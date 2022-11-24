package baguchan.hunterillager.client.model;
// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports

import baguchan.hunterillager.entity.Hunter;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.monster.AbstractIllager;

public class HunterModel<T extends Hunter> extends HierarchicalModel<T> implements ArmedModel, HeadedModel {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart waist;
	private final ModelPart LeftLeg;
	private final ModelPart RightLeg;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart bone;
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart cape;

	public HunterModel(ModelPart root) {
		this.root = root;
		this.waist = root.getChild("waist");
		this.cape = this.waist.getChild("Body").getChild("cape");
		this.LeftLeg = root.getChild("LeftLeg");
		this.RightLeg = root.getChild("RightLeg");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
		this.bone = root.getChild("bone");
		this.head = this.bone.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition waist = partdefinition.addOrReplaceChild("waist", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 0.0F));

		PartDefinition Body = waist.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 18).addBox(-4.0F, -24.25F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(28, 13).addBox(-4.0F, -24.0F, -2.75F, 8.0F, 14.0F, 5.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 12.0F, 0.0F));

		PartDefinition cape = Body.addOrReplaceChild("cape", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition cube_r1 = cape.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(28, 32).addBox(0.5F, -1.0F, 3.0F, 9.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(49, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(44, 44).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(16, 44).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition shoulderplate_r1 = RightArm.addOrReplaceChild("shoulderplate_r1", CubeListBuilder.create().texOffs(48, 32).addBox(-4.0F, -2.75F, -2.5F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(19, 0).addBox(-6.0F, 0.25F, -2.5F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(0, 36).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition shoulderplate_r2 = LeftArm.addOrReplaceChild("shoulderplate_r2", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -2.75F, -2.5F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(17, 18).addBox(4.0F, 0.25F, -2.5F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = bone.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head2 = head.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -34.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(48, 40).addBox(-5.0F, -30.0F, -4.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.head.xRot = headPitch * ((float) Math.PI / 180F);
		if (this.riding) {
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
			this.RightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
			this.RightArm.yRot = 0.0F;
			this.RightArm.zRot = 0.0F;
			this.LeftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
			this.LeftArm.yRot = 0.0F;
			this.LeftArm.zRot = 0.0F;
			this.RightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
			this.RightLeg.yRot = 0.0F;
			this.RightLeg.zRot = 0.0F;
			this.LeftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount * 0.5F;
			this.LeftLeg.yRot = 0.0F;
			this.LeftLeg.zRot = 0.0F;
		}

		AbstractIllager.IllagerArmPose abstractillager$illagerarmpose = entityIn.getArmPose();
		if (abstractillager$illagerarmpose == AbstractIllager.IllagerArmPose.ATTACKING) {
			AnimationUtils.swingWeaponDown(this.RightArm, this.LeftArm, entityIn, this.attackTime, ageInTicks);
		} else if (abstractillager$illagerarmpose == AbstractIllager.IllagerArmPose.BOW_AND_ARROW) {
			this.RightArm.yRot = -0.1F + this.head.yRot;
			this.RightArm.xRot = (-(float) Math.PI / 2F) + this.head.xRot;
			this.LeftArm.xRot = -0.9424779F + this.head.xRot;
			this.LeftArm.yRot = this.head.yRot - 0.4F;
			this.LeftArm.zRot = ((float) Math.PI / 2F);
		} else if (abstractillager$illagerarmpose == AbstractIllager.IllagerArmPose.CROSSBOW_HOLD) {
			AnimationUtils.animateCrossbowHold(this.RightArm, this.LeftArm, this.head, true);
		} else if (abstractillager$illagerarmpose == AbstractIllager.IllagerArmPose.CROSSBOW_CHARGE) {
			AnimationUtils.animateCrossbowCharge(this.RightArm, this.LeftArm, entityIn, true);
		} else if (abstractillager$illagerarmpose == AbstractIllager.IllagerArmPose.CELEBRATING) {
			this.RightArm.z = 0.0F;
			this.RightArm.x = -5.0F;
			this.RightArm.xRot = Mth.cos(ageInTicks * 0.6662F) * 0.05F;
			this.RightArm.zRot = 2.670354F;
			this.RightArm.yRot = 0.0F;
			this.LeftArm.z = 0.0F;
			this.LeftArm.x = 5.0F;
			this.LeftArm.xRot = Mth.cos(ageInTicks * 0.6662F) * 0.05F;
			this.LeftArm.zRot = -2.3561945F;
			this.LeftArm.yRot = 0.0F;
		}
		this.cape.xRot = 0.17453294F * limbSwingAmount * 1.75F;
	}

	private ModelPart getArm(HumanoidArm p_102923_) {
		return p_102923_ == HumanoidArm.LEFT ? this.LeftArm : this.RightArm;
	}

	public ModelPart getHead() {
		return this.head;
	}

	public void translateToHand(HumanoidArm p_102925_, PoseStack p_102926_) {
		this.getArm(p_102925_).translateAndRotate(p_102926_);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}
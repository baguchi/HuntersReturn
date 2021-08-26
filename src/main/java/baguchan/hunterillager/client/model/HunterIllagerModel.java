package baguchan.hunterillager.client.model;

import baguchan.hunterillager.entity.HunterIllagerEntity;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;


public class HunterIllagerModel<T extends HunterIllagerEntity> extends IllagerModel<T> {
	private ModelPart body;

	private final ModelPart quiver;

	private final ModelPart cape;

	private final ModelPart capeLower;

	public HunterIllagerModel(ModelPart p_170688_) {
		super(p_170688_);
		this.body = p_170688_.getChild("body");
		this.cape = this.body.getChild("cape");
		this.quiver = this.cape.getChild("quiver");
		this.capeLower = this.cape.getChild("cape_lower");
		this.getHead().visible = true;
		/*getHat().addBox("hood_fixed", -4.0F, -10.0F, -4.0F, 8, 10, 8, scaleFactor + 0.45F, 32, 0);
		this.cape = new ModelPart((Model) this, 0, 0);
		this.cape.setTexSize(textureWidthIn, textureHeightIn);
		this.cape.setPos(0.0F, 0.5F, 3.0F);
		this.cape.texOffs(0, 64).addBox(-4.5F, 0.0F, -0.5F, 9.0F, 11.0F, 1.0F, 0.1F + scaleFactor);
		this._body.addChild(this.cape);
		this.quiver = new ModelPart((Model) this);
		this.quiver.setTexSize(textureWidthIn, textureHeightIn);
		this.quiver.setPos(3.0F, 0.0F, 2.5F);
		this.quiver.texOffs(20, 64).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 13.0F, 3.0F, -0.5F + scaleFactor);
		this.quiver.texOffs(36, 64).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 13.0F, 3.0F, scaleFactor);
		this.cape.addChild(this.quiver);
		this.capeLower = new ModelPart((Model) this, 0, 0);
		this.capeLower.setTexSize(textureWidthIn, textureHeightIn);
		this.capeLower.setPos(0.0F, 11.0F, 0.0F);
		this.capeLower.texOffs(0, 76).addBox(-4.5F, 0.0F, -0.5F, 9.0F, 8.0F, 1.0F, 0.1F + scaleFactor);
		this.cape.addChild(this.capeLower);*/
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F), PartPose.offset(0.0F, 0.0F, 0.0F));
		partdefinition1.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.45F)), PartPose.ZERO);
		partdefinition1.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F), PartPose.offset(0.0F, -2.0F, 0.0F));
		PartDefinition partdefinition4 = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F).texOffs(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition partdefinition2 = partdefinition.addOrReplaceChild("arms", CubeListBuilder.create().texOffs(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F).texOffs(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 3.0F, -1.0F, -0.75F, 0.0F, 0.0F));
		partdefinition2.addOrReplaceChild("left_shoulder", CubeListBuilder.create().texOffs(44, 22).mirror().addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F), PartPose.ZERO);
		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-2.0F, 12.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(2.0F, 12.0F, 0.0F));
		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 46).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-5.0F, 2.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 46).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition partdefinition5 = partdefinition4.addOrReplaceChild("cape", CubeListBuilder.create().texOffs(0, 64).addBox(-4.5F, 0.0F, -0.5F, 9.0F, 11.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 0.5F, 3.0F));
		partdefinition5.addOrReplaceChild("quiver", CubeListBuilder.create().texOffs(20, 64).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 13.0F, 3.0F, new CubeDeformation(-0.5F)).texOffs(36, 64).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 13.0F, 3.0F), PartPose.offset(3.0F, 0.0F, 2.5F));
		partdefinition5.addOrReplaceChild("cape_lower", CubeListBuilder.create().texOffs(0, 76).addBox(-4.5F, 0.0F, -0.5F, 9.0F, 8.0F, 1.0F), PartPose.offset(0.0F, 11.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 128);
	}

	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.quiver.zRot = 0.5235988F;
		this.cape.xRot = 0.17453294F * limbSwingAmount * 1.75F;
		this.capeLower.xRot = 0.09817477F * limbSwingAmount * 1.75F;
		this.getHat().visible = true;
	}
}

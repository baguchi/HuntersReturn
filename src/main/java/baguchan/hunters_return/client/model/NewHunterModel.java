package baguchan.hunters_return.client.model;


import bagu_chan.bagus_lib.client.layer.IArmor;
import baguchan.hunters_return.entity.Hunter;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class NewHunterModel<T extends Hunter> extends HunterModel<T> implements ArmedModel, HeadedModel, IArmor {
    private final ModelPart rightEye;
    private final ModelPart leftEye;

    public NewHunterModel(ModelPart p_170688_) {
        super(p_170688_);

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
        this.rightEye = this.head.getChild("rightEye");
        this.leftEye = this.head.getChild("leftEye");
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float f3 = (ageInTicks + entityIn.getId());

        float eyeRot = (entityIn.getViewYRot(ageInTicks - entityIn.tickCount) - entityIn.getPreciseBodyRotation(ageInTicks - entityIn.tickCount) + 180);

        this.rightEye.visible = !entityIn.isSleeping() && !(0 > Math.sin(f3 * 0.05F) + Math.sin(f3 * 0.13F) + Math.sin(f3 * 0.7F) + 2.55F);
        this.leftEye.visible = !entityIn.isSleeping() && !(0 > Math.sin(f3 * 0.05F) + Math.sin(f3 * 0.13F) + Math.sin(f3 * 0.7F) + 2.55F);
        this.rightEye.x -= (Mth.clamp((eyeRot % 360 - 180) / 90F, 0.0F, 0.5F));
        this.leftEye.x -= (Mth.clamp((eyeRot % 360 - 180) / 90F, -0.5F, 0.0F));

    }
}
package baguchan.hunterillager.client.model;

import baguchan.hunterillager.entity.HunterIllagerEntity;
import net.minecraft.client.renderer.entity.model.IllagerModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

import java.util.Iterator;

public class HunterIllagerModel<T extends HunterIllagerEntity> extends IllagerModel<T> {
	private ModelRenderer _body;

	private final ModelRenderer quiver;

	private final ModelRenderer cape;

	private final ModelRenderer capeLower;

	private void setBodyRenderer() {
		Iterator<ModelRenderer> pieces = parts().iterator();
		pieces.next();
		this._body = pieces.next();
	}

	public HunterIllagerModel(float scaleFactor, float p_i47227_2_, int textureWidthIn, int textureHeightIn) {
		super(scaleFactor, p_i47227_2_, textureWidthIn, textureHeightIn);
		setBodyRenderer();
		getHat().addBox("hood_fixed", -4.0F, -10.0F, -4.0F, 8, 10, 8, scaleFactor + 0.45F, 32, 0);
		this.cape = new ModelRenderer((Model) this, 0, 0);
		this.cape.setTexSize(textureWidthIn, textureHeightIn);
		this.cape.setPos(0.0F, 0.5F, 3.0F);
		this.cape.texOffs(0, 64).addBox(-4.5F, 0.0F, -0.5F, 9.0F, 11.0F, 1.0F, 0.1F + scaleFactor);
		this._body.addChild(this.cape);
		this.quiver = new ModelRenderer((Model) this);
		this.quiver.setTexSize(textureWidthIn, textureHeightIn);
		this.quiver.setPos(3.0F, 0.0F, 2.5F);
		this.quiver.texOffs(20, 64).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 13.0F, 3.0F, -0.5F + scaleFactor);
		this.quiver.texOffs(36, 64).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 13.0F, 3.0F, scaleFactor);
		this.cape.addChild(this.quiver);
		this.capeLower = new ModelRenderer((Model) this, 0, 0);
		this.capeLower.setTexSize(textureWidthIn, textureHeightIn);
		this.capeLower.setPos(0.0F, 11.0F, 0.0F);
		this.capeLower.texOffs(0, 76).addBox(-4.5F, 0.0F, -0.5F, 9.0F, 8.0F, 1.0F, 0.1F + scaleFactor);
		this.cape.addChild(this.capeLower);
	}

	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.quiver.zRot = 0.5235988F;
		this.cape.xRot = 0.17453294F * limbSwingAmount * 1.75F;
		this.capeLower.xRot = 0.09817477F * limbSwingAmount * 1.75F;
		this.getHat().visible = true;
	}
}

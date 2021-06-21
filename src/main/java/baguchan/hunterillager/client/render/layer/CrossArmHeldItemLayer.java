package baguchan.hunterillager.client.render.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.IllagerModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;

public class CrossArmHeldItemLayer<T extends AbstractIllagerEntity, M extends IllagerModel<T> & IHasArm> extends LayerRenderer<T, M> {
	public CrossArmHeldItemLayer(IEntityRenderer<T, M> p_i50916_1_) {
		super(p_i50916_1_);
	}

	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (!entitylivingbaseIn.isInvisible() && !entitylivingbaseIn.isAggressive()) {
			matrixStackIn.pushPose();
			matrixStackIn.translate(0.0D, (double) 0.4F, (double) -0.4F);
			matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			ItemStack itemstack = entitylivingbaseIn.getItemBySlot(EquipmentSlotType.OFFHAND);
			Minecraft.getInstance().getItemInHandRenderer().renderItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.GROUND, false, matrixStackIn, bufferIn, packedLightIn);
			matrixStackIn.popPose();
		}
	}
}

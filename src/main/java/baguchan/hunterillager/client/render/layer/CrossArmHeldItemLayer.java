package baguchan.hunterillager.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.item.ItemStack;

public class CrossArmHeldItemLayer<T extends AbstractIllager, M extends EntityModel<T>> extends RenderLayer<T, M> {
	public CrossArmHeldItemLayer(RenderLayerParent<T, M> p_116686_) {
		super(p_116686_);
	}

	public void render(PoseStack p_116699_, MultiBufferSource p_116700_, int p_116701_, T p_116702_, float p_116703_, float p_116704_, float p_116705_, float p_116706_, float p_116707_, float p_116708_) {
		if (!p_116702_.isAggressive()) {
			p_116699_.pushPose();
			p_116699_.translate(0.0D, (double) 0.4F, (double) -0.4F);
			p_116699_.mulPose(Axis.XP.rotationDegrees(180.0F));
			ItemStack itemstack = p_116702_.getItemBySlot(EquipmentSlot.OFFHAND);
			Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer().renderItem(p_116702_, itemstack, ItemTransforms.TransformType.GROUND, false, p_116699_, p_116700_, p_116701_);
			p_116699_.popPose();
		}
	}
}
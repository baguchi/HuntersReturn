package baguchan.hunters_return.client.render;

import baguchan.hunters_return.entity.projectile.Boomerang;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoomerangRender extends EntityRenderer<Boomerang> {
	private final ItemRenderer itemRenderer;

	public BoomerangRender(EntityRendererProvider.Context renderManager) {
		super(renderManager);
		this.itemRenderer = Minecraft.getInstance().getItemRenderer();
	}

	@Override
	public void render(Boomerang entityIn, float entityYaw, float partialTicks, PoseStack stackIn, MultiBufferSource bufferIn, int packedLightIn) {
		stackIn.pushPose();

		stackIn.translate(-0.0F, 0, 0.15F);
		stackIn.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot())));
		stackIn.mulPose(Axis.XP.rotationDegrees(Mth.lerp(partialTicks, -entityIn.xRotO, -entityIn.getXRot())));

		stackIn.mulPose(Axis.XP.rotationDegrees(90.0F));
		stackIn.mulPose(Axis.ZP.rotationDegrees((entityIn.tickCount + partialTicks) * (((float) entityIn.getSpeed() * 40.0F) + 40F)));

		stackIn.scale(1.25F, 1.25F, 1.25F);
		BakedModel bakedmodel = this.itemRenderer.getModel(entityIn.getBoomerang(), entityIn.level(), null, entityIn.getId());

		this.itemRenderer.render(entityIn.getBoomerang(), ItemDisplayContext.GROUND, false, stackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY, bakedmodel);
		stackIn.popPose();
		super.render(entityIn, entityYaw, partialTicks, stackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(Boomerang p_115034_) {
		return TextureAtlas.LOCATION_BLOCKS;
	}
}

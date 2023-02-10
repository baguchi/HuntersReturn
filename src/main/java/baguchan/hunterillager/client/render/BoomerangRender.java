package baguchan.hunterillager.client.render;

import baguchan.hunterillager.entity.projectile.BoomerangEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoomerangRender extends EntityRenderer<BoomerangEntity> {
	private ItemRenderer itemRenderer;

	public BoomerangRender(EntityRendererProvider.Context renderManager) {
		super(renderManager);
		this.itemRenderer = Minecraft.getInstance().getItemRenderer();
	}

	@Override
	public void render(BoomerangEntity entityIn, float entityYaw, float partialTicks, PoseStack stackIn, MultiBufferSource bufferIn, int packedLightIn) {
		stackIn.pushPose();

		stackIn.mulPose(Vector3f.YP.rotationDegrees(-Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot())));
		stackIn.mulPose(Vector3f.XP.rotationDegrees(90.0F));
		stackIn.mulPose(Vector3f.XP.rotationDegrees(Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));

		stackIn.mulPose(Vector3f.ZP.rotationDegrees((entityIn.tickCount + partialTicks + entityIn.getPiercingLevel() * 0.85F) * (((float) entityIn.getSpeed() * 80.0F) + 1F)));
		stackIn.translate(0.0F, 0.0F, -entityIn.getBbHeight() / 2);

		BakedModel bakedmodel = this.itemRenderer.getModel(entityIn.getBoomerang(), entityIn.level, (LivingEntity) null, entityIn.getId());

		this.itemRenderer.render(entityIn.getBoomerang(), ItemTransforms.TransformType.GROUND, false, stackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY, bakedmodel);
		stackIn.popPose();
		super.render(entityIn, entityYaw, partialTicks, stackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(BoomerangEntity p_115034_) {
		return TextureAtlas.LOCATION_BLOCKS;
	}
}

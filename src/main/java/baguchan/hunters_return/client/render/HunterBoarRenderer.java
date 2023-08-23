package baguchan.hunters_return.client.render;

import baguchan.hunters_return.HuntersReturn;
import baguchan.hunters_return.entity.HunterBoar;
import net.minecraft.client.model.HoglinModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HunterBoarRenderer extends MobRenderer<HunterBoar, HoglinModel<HunterBoar>> {
    private static final ResourceLocation HOGLIN_LOCATION = new ResourceLocation(HuntersReturn.MODID, "textures/entity/hoglin.png");

    public HunterBoarRenderer(EntityRendererProvider.Context p_174165_) {
        super(p_174165_, new HoglinModel<>(p_174165_.bakeLayer(ModelLayers.HOGLIN)), 0.7F);
    }

    public ResourceLocation getTextureLocation(HunterBoar p_114862_) {
        return HOGLIN_LOCATION;
    }

    protected boolean isShaking(HunterBoar p_114864_) {
        return super.isShaking(p_114864_) || p_114864_.isConverting();
    }
}
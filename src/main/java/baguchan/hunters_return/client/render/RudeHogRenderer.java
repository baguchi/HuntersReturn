package baguchan.hunters_return.client.render;

import bagu_chan.bagus_lib.client.layer.CustomArmorLayer;
import baguchan.hunters_return.HuntersReturn;
import baguchan.hunters_return.client.ModModelLayers;
import baguchan.hunters_return.client.model.RudeHogModel;
import baguchan.hunters_return.entity.RudeHog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RudeHogRenderer<T extends RudeHog> extends MobRenderer<T, RudeHogModel<T>> {
    private static final ResourceLocation RUDEHOG = new ResourceLocation(HuntersReturn.MODID, "textures/entity/piglin/rudehog.png");

    public RudeHogRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new RudeHogModel<>(renderManagerIn.bakeLayer(ModModelLayers.RUDEHOG)), 0.5F);
        this.addLayer(new CustomArmorLayer<>(this, renderManagerIn));
        this.addLayer(new ItemInHandLayer<>(this, Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(T p_110775_1_) {
        return RUDEHOG;
    }
}

package baguchi.hunters_return.client;

import baguchi.hunters_return.HuntersReturn;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static ModelLayerLocation HUNTER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(HuntersReturn.MODID, "hunter"), "main");
    public static ModelLayerLocation HUNTER_OLD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(HuntersReturn.MODID, "hunter_old"), "main");
}

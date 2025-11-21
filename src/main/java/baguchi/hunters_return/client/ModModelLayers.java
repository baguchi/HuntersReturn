package baguchi.hunters_return.client;

import baguchi.hunters_return.HuntersReturn;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public class ModModelLayers {
    public static ModelLayerLocation HUNTER = new ModelLayerLocation(Identifier.fromNamespaceAndPath(HuntersReturn.MODID, "hunter"), "main");
    public static ModelLayerLocation HUNTER_OLD = new ModelLayerLocation(Identifier.fromNamespaceAndPath(HuntersReturn.MODID, "hunter_old"), "main");
}

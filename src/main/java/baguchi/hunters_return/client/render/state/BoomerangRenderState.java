package baguchi.hunters_return.client.render.state;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.item.ItemStack;

public class BoomerangRenderState extends EntityRenderState {
    public float xRot;
    public float yRot;
    public float speed;
    public boolean inGround;
    public ItemStack boomerang;
}

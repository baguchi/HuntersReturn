package baguchi.hunters_return.client.render.state;

import baguchi.hunters_return.entity.Hunter;
import net.minecraft.client.renderer.entity.state.IllagerRenderState;
import net.minecraft.world.entity.AnimationState;

public class HunterRenderState extends IllagerRenderState {
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState shootAnimationState = new AnimationState();
    public final AnimationState chargeAnimationState = new AnimationState();
    public final AnimationState thrownAnimationState = new AnimationState();
    public Hunter.HunterType hunterType;
    public boolean sleep;
}

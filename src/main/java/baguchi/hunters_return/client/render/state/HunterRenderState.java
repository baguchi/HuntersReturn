package baguchi.hunters_return.client.render.state;

import baguchi.hunters_return.entity.Hunter;
import net.minecraft.client.renderer.entity.state.IllagerRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.item.ItemDisplayContext;

import javax.annotation.Nullable;

public class HunterRenderState extends IllagerRenderState {
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState shootAnimationState = new AnimationState();
    public final AnimationState chargeAnimationState = new AnimationState();
    public final AnimationState thrownAnimationState = new AnimationState();
    public final AnimationState dodghRightAnimationState = new AnimationState();
    public final AnimationState dodghLeftAnimationState = new AnimationState();

    @Nullable
    public Identifier texture;
    @Nullable
    public Identifier textureOld;
    public boolean sleep;
    public final ItemStackRenderState mouthItem = new ItemStackRenderState();
    public int id;
    public float eyeRot;

    public static void extractMouthEntityRenderState(Hunter p_387833_, HunterRenderState p_387185_, ItemModelResolver p_386820_) {
        p_386820_.updateForLiving(p_387185_.mouthItem, p_387833_.getMouthItem(), ItemDisplayContext.GROUND, p_387833_);
    }
}

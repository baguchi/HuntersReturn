package baguchan.hunters_return.mixin.client;

import baguchan.hunters_return.init.HunterItems;
import baguchan.hunters_return.item.MiniCrossBowItem;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {

    @Inject(method = "isChargedCrossbow", at = @At("HEAD"), cancellable = true)
    private static void isChargedCrossbow(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.is(HunterItems.MINI_CROSSBOW)) {
            cir.setReturnValue(MiniCrossBowItem.isCharged(stack));
        }
    }
}
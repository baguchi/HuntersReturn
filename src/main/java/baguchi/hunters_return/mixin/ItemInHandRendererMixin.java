package baguchi.hunters_return.mixin;

import baguchi.hunters_return.init.HunterItems;
import baguchi.hunters_return.item.MiniCrossbowItem;
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
            cir.setReturnValue(MiniCrossbowItem.isCharged(stack));
        }
    }
}
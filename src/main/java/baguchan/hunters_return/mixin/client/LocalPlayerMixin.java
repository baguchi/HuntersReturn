package baguchan.hunters_return.mixin.client;

import baguchan.hunters_return.init.HunterItems;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends AbstractClientPlayer {

    @Shadow
    public Input input;

    @Shadow
    protected abstract boolean vehicleCanSprint(Entity p_265184_);

    @Shadow
    public abstract boolean isMovingSlowly();

    @Shadow
    protected abstract boolean hasEnoughImpulseToStartSprinting();

    @Shadow
    protected abstract boolean hasEnoughFoodToStartSprinting();

    public LocalPlayerMixin(ClientLevel p_250460_, GameProfile p_249912_) {
        super(p_250460_, p_249912_);
    }

    @Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z", shift = At.Shift.AFTER, ordinal = 0))
    private void injectSlowdown(CallbackInfo ci) {
        ItemStack itemStack = this.getUseItem();
        if (itemStack.is(HunterItems.MINI_CROSSBOW) || itemStack.is(HunterItems.BOOMERANG)) {
            if (this.isUsingItem() && !this.isPassenger()) {
                this.input.leftImpulse /= 0.2F;
                this.input.forwardImpulse /= 0.2F;
                if (itemStack.is(HunterItems.MINI_CROSSBOW)) {
                    this.input.leftImpulse *= 0.775F;
                    this.input.forwardImpulse *= 0.775F;
                }
                if (itemStack.is(HunterItems.BOOMERANG)) {
                    this.input.leftImpulse *= 0.98F;
                    this.input.forwardImpulse *= 0.98F;
                }

            }
        }
    }

    @Inject(
            method = "canStartSprinting",
            at = @At(value = "HEAD")
            , cancellable = true
    )
    private void canStartSprinting(CallbackInfoReturnable<Boolean> cir) {
        if (this.isUsingItem()) {
            ItemStack itemStack = this.getItemInHand(this.getUsedItemHand());
            if (itemStack.is(HunterItems.MINI_CROSSBOW) || itemStack.is(HunterItems.BOOMERANG)) {
                cir.setReturnValue(!this.isSprinting() && this.hasEnoughImpulseToStartSprinting()
                        && this.hasEnoughFoodToStartSprinting() && !this.hasEffect(MobEffects.BLINDNESS) && (!this.isPassenger() || this.vehicleCanSprint(this.getVehicle()))
                        && !this.isFallFlying());
            }
        }
    }
}

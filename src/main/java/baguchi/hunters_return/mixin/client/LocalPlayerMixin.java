package baguchi.hunters_return.mixin.client;

import baguchi.hunters_return.init.HunterItems;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.ClientInput;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends AbstractClientPlayer {

    @Shadow
    public ClientInput input;

    @Shadow
    protected abstract boolean hasEnoughFoodToSprint();

    @Shadow
    protected abstract boolean hasBlindness();

    @Shadow
    protected abstract boolean vehicleCanSprint(Entity p_265184_);

    @Shadow
    public abstract boolean isMovingSlowly();

    public LocalPlayerMixin(ClientLevel p_250460_, GameProfile p_249912_) {
        super(p_250460_, p_249912_);
    }

    @Inject(
            method = "modifyInput",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/Vec2;scale(F)Lnet/minecraft/world/phys/Vec2;", ordinal = 1, shift = At.Shift.BEFORE)
            , cancellable = true
    )
    private void modifyInput(Vec2 p_400209_, CallbackInfoReturnable<Vec2> cir) {
        if (p_400209_.lengthSquared() != 0.0F) {
            Vec2 vec2 = p_400209_.scale(0.98F);
            if (this.isUsingItem() && !this.isPassenger()) {
                ItemStack itemStack = this.getItemInHand(this.getUsedItemHand());

                boolean flag = false;

                if (itemStack.is(HunterItems.MINI_CROSSBOW)) {
                    vec2 = vec2.scale(0.775F);
                    flag = true;
                }

                if (itemStack.is(HunterItems.BOOMERANG)) {
                    vec2 = vec2.scale(0.98F);
                    flag = true;
                }

                if (this.isMovingSlowly()) {
                    float f = (float) this.getAttributeValue(Attributes.SNEAKING_SPEED);
                    vec2 = vec2.scale(f);
                }

                if (flag) {
                    cir.setReturnValue(modifyInputSpeedForSquareMovement(vec2));
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
                cir.setReturnValue(!this.isSprinting() && this.input.hasForwardImpulse() && this.hasEnoughFoodToSprint() && !this.hasBlindness() && (!this.isPassenger() || this.vehicleCanSprint(this.getVehicle())) && (!this.isFallFlying() || this.isUnderWater()) && (!this.isMovingSlowly() || this.isUnderWater()) && (!this.isInWater() || this.isUnderWater() || this.isInFluidType((fluidType, height) -> this.canSwimInFluidType(fluidType)) && this.canStartSwimming()));
            }
        }
    }

    @Shadow
    private static Vec2 modifyInputSpeedForSquareMovement(Vec2 p_400216_) {
        return null;
    }

}

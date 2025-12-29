package baguchi.hunters_return.mixin;

import baguchi.hunters_return.item.MiniCrossbowItem;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin extends ProjectileWeaponItem {
    public CrossbowItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "getChargeDuration", at = @At("HEAD"), cancellable = true)
    private static void getChargeDuration(ItemStack stack, LivingEntity shooter, CallbackInfoReturnable<Integer> cir) {
        if (stack.getItem() instanceof MiniCrossbowItem) {
            float f = EnchantmentHelper.modifyCrossbowChargingTime(stack, shooter, 0.65F);
            cir.setReturnValue(Mth.floor(f * 20.0F));
        }
    }

    @Inject(method = "tryLoadProjectiles", at = @At("RETURN"))
    private static void tryLoadProjectiles(LivingEntity shooter, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof MiniCrossbowItem && cir.getReturnValue()) {
            InteractionHand hand2 = shooter.getUsedItemHand() == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
            ItemStack itemstack2 = shooter.getItemInHand(hand2);
            if (itemstack2.getItem() instanceof MiniCrossbowItem && !CrossbowItem.isCharged(itemstack2)) {
                MiniCrossbowItem.tryLoadProjectiles(shooter, itemstack2);
            }
        }
    }
}

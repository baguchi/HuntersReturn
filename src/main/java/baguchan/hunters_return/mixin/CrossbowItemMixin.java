package baguchan.hunters_return.mixin;

import baguchan.hunters_return.item.MiniCrossBowItem;
import net.minecraft.util.Mth;
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
        if (stack.getItem() instanceof MiniCrossBowItem) {
            float f = EnchantmentHelper.modifyCrossbowChargingTime(stack, shooter, 0.65F);
            cir.setReturnValue(Mth.floor(f * 20.0F));
        }
    }
}

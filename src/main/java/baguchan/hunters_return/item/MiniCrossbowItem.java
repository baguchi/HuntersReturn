package baguchan.hunters_return.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;


public class MiniCrossbowItem extends CrossbowItem {
    private static final ChargingSounds DEFAULT_SOUNDS = new ChargingSounds(
            Optional.of(SoundEvents.CROSSBOW_LOADING_START), Optional.of(SoundEvents.CROSSBOW_LOADING_MIDDLE), Optional.of(SoundEvents.CROSSBOW_LOADING_END)
    );

    public MiniCrossbowItem(Properties miniCrossbow) {
        super(miniCrossbow);
    }

    @Override
    public InteractionResultHolder use(Level p_40920_, Player p_40921_, InteractionHand p_40922_) {
        ItemStack itemstack = p_40921_.getItemInHand(p_40922_);
        InteractionHand hand2 = p_40922_ == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
        ItemStack itemstack2 = p_40921_.getItemInHand(hand2);
        ChargedProjectiles chargedprojectiles = itemstack.get(DataComponents.CHARGED_PROJECTILES);
        ChargedProjectiles chargedprojectiles2 = itemstack2.get(DataComponents.CHARGED_PROJECTILES);

        if (chargedprojectiles != null && !chargedprojectiles.isEmpty()) {
            this.performShooting(p_40920_, p_40921_, p_40922_, itemstack, getShootingPower(chargedprojectiles), 1.0F, null);
            return InteractionResultHolder.consume(itemstack);
        } else if (chargedprojectiles2 != null && !chargedprojectiles2.isEmpty()) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            return super.use(p_40920_, p_40921_, p_40922_);
        }
    }

    public static boolean tryLoadProjectiles(LivingEntity p_40860_, ItemStack p_40861_) {
        List<ItemStack> list = draw(p_40861_, p_40860_.getProjectile(p_40861_), p_40860_);
        if (!list.isEmpty()) {
            p_40861_.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.of(list));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean useOnRelease(ItemStack p_41464_) {
        return true;
    }

    private static float getShootingPower(ChargedProjectiles p_330249_) {
        return p_330249_.contains(Items.FIREWORK_ROCKET) ? 1.6F * 0.85F : 2.25F;
    }

    @Override
    protected int getDurabilityUse(ItemStack p_331489_) {
        return p_331489_.is(Items.FIREWORK_ROCKET) ? 4 : 1;
    }


    @Override
    public int getEnchantmentValue() {
        return 2;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 8;
    }
}

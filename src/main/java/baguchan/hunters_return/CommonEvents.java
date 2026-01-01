package baguchan.hunters_return;

import baguchan.hunters_return.item.MiniCrossBowItem;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = HuntersReturn.MODID)
public class CommonEvents {

    @SubscribeEvent
    public static void afterPostDamage(LivingDamageEvent.Post event) {
        DamageSource damageSource = event.getSource();
        Entity entity = damageSource.getEntity();

        if (entity instanceof Projectile projectile) {
            if (projectile.getOwner() != null && projectile.getWeaponItem() != null) {
                if (projectile.getWeaponItem().getItem() instanceof MiniCrossBowItem) {
                    event.getEntity().invulnerableTime = 5;
                    event.getEntity().hurtDuration = 5;
                    event.getEntity().hurtTime = 5;
                }
            }
        }

    }
}

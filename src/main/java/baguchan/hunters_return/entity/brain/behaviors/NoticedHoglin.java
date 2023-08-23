package baguchan.hunters_return.entity.brain.behaviors;

import baguchan.hunters_return.entity.HunterBoar;
import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

public class NoticedHoglin<E extends PathfinderMob> extends Behavior<E> {

    private int noticedTick;

    public NoticedHoglin() {
        super(ImmutableMap.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT));
    }

    protected boolean checkExtraStartConditions(ServerLevel level, E mob) {
        LivingEntity livingentity = this.getAttackTarget(mob);
        if (noticedTick <= 0) {
            this.noticedTick = 30 + mob.getRandom().nextInt(30);
        } else {
            --this.noticedTick;
        }
        return this.noticedTick <= 0 && livingentity != null && mob.hasLineOfSight(livingentity) && mob.getVehicle() instanceof HunterBoar && this.getAttackTarget((LivingEntity) mob.getVehicle()) != this.getAttackTarget(mob);
    }

    protected void start(ServerLevel p_23524_, E mob, long p_23526_) {
        if (mob.getVehicle() instanceof HunterBoar) {
            mob.playSound(SoundEvents.PIGLIN_BRUTE_AMBIENT);
            ((HunterBoar) mob.getVehicle()).getBrain().setMemory(MemoryModuleType.ATTACK_TARGET, this.getAttackTarget(mob));
        }
    }

    protected boolean canStillUse(ServerLevel p_22545_, E p_22546_, long p_22547_) {
        return false;
    }

    private LivingEntity getAttackTarget(LivingEntity p_23533_) {
        return p_23533_.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).isPresent() ? (LivingEntity) p_23533_.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).get() : null;
    }
}

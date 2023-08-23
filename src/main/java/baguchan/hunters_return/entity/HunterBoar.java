package baguchan.hunters_return.entity;

import bagu_chan.bagus_lib.util.BrainUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.NearestVisibleLivingEntities;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.monster.hoglin.HoglinBase;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class HunterBoar extends Monster implements Enemy, HoglinBase {
    private static final EntityDataAccessor<Boolean> DATA_IMMUNE_TO_ZOMBIFICATION = SynchedEntityData.defineId(HunterBoar.class, EntityDataSerializers.BOOLEAN);

    protected static final ImmutableList<? extends SensorType<? extends Sensor<? super HunterBoar>>> SENSOR_TYPES = ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.NEAREST_PLAYERS);
    protected static final ImmutableList<? extends MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(MemoryModuleType.NEAREST_LIVING_ENTITIES, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES, MemoryModuleType.NEAREST_VISIBLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER, MemoryModuleType.LOOK_TARGET, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.PATH, MemoryModuleType.ATTACK_TARGET, MemoryModuleType.ATTACK_COOLING_DOWN);
    private int timeInOverworld;
    private int attackAnimationRemainingTicks;

    public HunterBoar(EntityType<? extends HunterBoar> p_34488_, Level p_34489_) {
        super(p_34488_, p_34489_);
        this.xpReward = 8;
    }

    protected Brain.Provider<HunterBoar> brainProvider() {
        return Brain.provider(MEMORY_TYPES, SENSOR_TYPES);
    }

    protected Brain<?> makeBrain(Dynamic<?> p_34221_) {
        Brain<HunterBoar> brain = this.brainProvider().makeBrain(p_34221_);
        initCoreActivity(brain);
        initIdleActivity(brain);
        initFightActivity(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.useDefaultActivity();
        return brain;
    }

    private static void initCoreActivity(Brain<HunterBoar> p_34217_) {
        p_34217_.addActivity(Activity.CORE, 0, ImmutableList.of(new LookAtTargetSink(45, 90), new MoveToTargetSink()));
    }

    private static void initIdleActivity(Brain<HunterBoar> p_34229_) {
        p_34229_.addActivity(Activity.IDLE, 10, ImmutableList.of(StartAttacking.create(HunterBoar::findNearestValidAttackTarget), SetEntityLookTargetSometimes.create(8.0F, UniformInt.of(30, 60)), new RunOne<>(ImmutableList.of(Pair.of(RandomStroll.stroll(0.4F), 2), Pair.of(SetWalkTargetFromLookTarget.create(0.4F, 3), 2), Pair.of(new DoNothing(30, 60), 1)))));
    }

    private static void initFightActivity(Brain<HunterBoar> p_34237_) {
        p_34237_.addActivityAndRemoveMemoryWhenStopped(Activity.FIGHT, 10, ImmutableList.of(SetWalkTargetFromAttackTargetIfTargetOutOfReach.create(1.0F), MeleeAttack.create(40), StopAttackingIfTargetInvalid.create()), MemoryModuleType.ATTACK_TARGET);
    }

    private Optional<? extends LivingEntity> findNearestValidAttackTarget() {
        return this.getBrain().getMemory(MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES).orElse(NearestVisibleLivingEntities.empty()).findClosest(this::isTargetable);
    }

    private boolean isTargetable(LivingEntity p_34253_) {
        EntityType<?> entitytype = p_34253_.getType();
        return entitytype == EntityType.PLAYER && BrainUtils.isEntityAttackable(this, p_34253_, 16);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 40.0D).add(Attributes.MOVEMENT_SPEED, (double) 0.3F).add(Attributes.KNOCKBACK_RESISTANCE, (double) 0.65F).add(Attributes.ATTACK_KNOCKBACK, 1.0D).add(Attributes.ATTACK_DAMAGE, 6.0D).add(Attributes.ARMOR, 4.0D);
    }


    private void setAttackTarget(LivingEntity p_34255_) {
        this.brain.eraseMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);
        this.brain.setMemoryWithExpiry(MemoryModuleType.ATTACK_TARGET, p_34255_, 200L);
    }

    public Brain<HunterBoar> getBrain() {
        return (Brain<HunterBoar>) super.getBrain();
    }

    protected void updateActivity() {
        Activity activity = this.brain.getActiveNonCoreActivity().orElse((Activity) null);
        this.brain.setActiveActivityToFirstValid(ImmutableList.of(Activity.FIGHT, Activity.IDLE));
        Activity activity1 = this.brain.getActiveNonCoreActivity().orElse((Activity) null);
        if (activity1 == Activity.FIGHT && activity != Activity.FIGHT) {
            this.playAngrySound();
        }

        this.setAggressive(this.brain.hasMemoryValue(MemoryModuleType.ATTACK_TARGET));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_IMMUNE_TO_ZOMBIFICATION, false);
    }

    public void addAdditionalSaveData(CompoundTag p_34529_) {
        super.addAdditionalSaveData(p_34529_);
        if (this.isImmuneToZombification()) {
            p_34529_.putBoolean("IsImmuneToZombification", true);
        }

        p_34529_.putInt("TimeInOverworld", this.timeInOverworld);
    }

    public void readAdditionalSaveData(CompoundTag p_34519_) {
        super.readAdditionalSaveData(p_34519_);
        this.setImmuneToZombification(p_34519_.getBoolean("IsImmuneToZombification"));
        this.timeInOverworld = p_34519_.getInt("TimeInOverworld");
    }

    protected void customServerAiStep() {
        this.level().getProfiler().push("boarBrain");
        this.getBrain().tick((ServerLevel) this.level(), this);
        this.level().getProfiler().pop();
        this.updateActivity();
        if (this.isConverting()) {
            ++this.timeInOverworld;
            if (this.timeInOverworld > 300 && net.minecraftforge.event.ForgeEventFactory.canLivingConvert(this, EntityType.ZOGLIN, (timer) -> this.timeInOverworld = timer)) {
                this.playSoundEvent(SoundEvents.HOGLIN_CONVERTED_TO_ZOMBIFIED);
                this.finishConversion((ServerLevel) this.level());
            }
        } else {
            this.timeInOverworld = 0;
        }

    }


    private void finishConversion(ServerLevel p_34532_) {
        Zoglin zoglin = this.convertTo(EntityType.ZOGLIN, true);
        if (zoglin != null) {
            zoglin.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0));
            net.minecraftforge.event.ForgeEventFactory.onLivingConvert(this, zoglin);
        }

    }

    protected void playSoundEvent(SoundEvent p_219180_) {
        this.playSound(p_219180_, this.getSoundVolume(), this.getVoicePitch());
    }

    public void setImmuneToZombification(boolean p_34565_) {
        this.getEntityData().set(DATA_IMMUNE_TO_ZOMBIFICATION, p_34565_);
    }

    private boolean isImmuneToZombification() {
        return this.getEntityData().get(DATA_IMMUNE_TO_ZOMBIFICATION);
    }

    public boolean isConverting() {
        return !this.level().dimensionType().piglinSafe() && !this.isImmuneToZombification() && !this.isNoAi();
    }

    public void aiStep() {
        if (this.attackAnimationRemainingTicks > 0) {
            --this.attackAnimationRemainingTicks;
        }

        super.aiStep();
    }

    public void handleEntityEvent(byte p_34212_) {
        if (p_34212_ == 4) {
            this.attackAnimationRemainingTicks = 10;
            this.playSound(SoundEvents.HOGLIN_ATTACK, 1.0F, this.getVoicePitch());
        } else {
            super.handleEntityEvent(p_34212_);
        }

    }

    public boolean doHurtTarget(Entity p_34207_) {
        if (!(p_34207_ instanceof LivingEntity)) {
            return false;
        } else {
            this.attackAnimationRemainingTicks = 10;
            this.level().broadcastEntityEvent(this, (byte) 4);
            this.playSound(SoundEvents.HOGLIN_ATTACK, 1.0F, this.getVoicePitch());
            return HoglinBase.hurtAndThrowTarget(this, (LivingEntity) p_34207_);
        }
    }

    @Override
    public int getAttackAnimationRemainingTicks() {
        return this.attackAnimationRemainingTicks;
    }

    protected SoundEvent getAmbientSound() {
        if (this.level().isClientSide) {
            return null;
        } else {
            return this.brain.hasMemoryValue(MemoryModuleType.ATTACK_TARGET) ? SoundEvents.HOGLIN_ANGRY : SoundEvents.HOGLIN_AMBIENT;
        }
    }

    protected SoundEvent getHurtSound(DamageSource p_34244_) {
        return SoundEvents.HOGLIN_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.HOGLIN_DEATH;
    }

    protected void playStepSound(BlockPos p_34231_, BlockState p_34232_) {
        this.playSound(SoundEvents.HOGLIN_STEP, 0.15F, 1.0F);
    }

    protected void playAngrySound() {
        this.playSound(SoundEvents.HOGLIN_ANGRY, 1.0F, this.getVoicePitch());
    }

    protected void blockedByShield(LivingEntity p_34246_) {
        if (!this.isBaby()) {
            HoglinBase.throwTarget(this, p_34246_);
        }

    }

    public double getPassengersRidingOffset() {
        return (double) this.getBbHeight() - (this.isBaby() ? 0.2D : 0.15D);
    }

    public boolean hurt(DamageSource p_34214_, float p_34215_) {
        boolean flag = super.hurt(p_34214_, p_34215_);
        if (this.level().isClientSide) {
            return false;
        } else if (flag && p_34214_.getEntity() instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity) p_34214_.getEntity();
            if (this.canAttack(livingentity) && !BehaviorUtils.isOtherTargetMuchFurtherAwayThanCurrentAttackTarget(this, livingentity, 4.0D)) {
                this.setAttackTarget(livingentity);
                if (this.getControllingPassenger() != null) {
                    this.getControllingPassenger().getBrain().setMemory(MemoryModuleType.ATTACK_TARGET, livingentity);
                }
            }

            return flag;
        } else {
            return flag;
        }
    }
}

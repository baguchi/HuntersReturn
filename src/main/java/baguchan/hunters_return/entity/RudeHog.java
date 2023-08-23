package baguchan.hunters_return.entity;

import bagu_chan.bagus_lib.register.ModSensors;
import baguchan.hunters_return.entity.brain.RudeHogAi;
import baguchan.hunters_return.init.HunterItems;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class RudeHog extends Piglin {

	public final int attackAnimationLength = (int) (40);
	public final int attackAnimationActionPoint = (int) (10);
	private int attackAnimationTick;
	public final AnimationState attackAnimationState = new AnimationState();
	public final AnimationState danceAnimationState = new AnimationState();

	protected static final ImmutableList<SensorType<? extends Sensor<? super Piglin>>> SENSOR_TYPES = ImmutableList.of(ModSensors.SMART_NEAREST_LIVING_ENTITY_SENSOR.get(), SensorType.NEAREST_PLAYERS, SensorType.NEAREST_ITEMS, SensorType.HURT_BY, SensorType.PIGLIN_SPECIFIC_SENSOR);
	protected static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(MemoryModuleType.LOOK_TARGET, MemoryModuleType.DOORS_TO_CLOSE, MemoryModuleType.NEAREST_LIVING_ENTITIES, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES, MemoryModuleType.NEAREST_VISIBLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_ADULT_PIGLINS, MemoryModuleType.NEARBY_ADULT_PIGLINS, MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM, MemoryModuleType.ITEM_PICKUP_COOLDOWN_TICKS, MemoryModuleType.HURT_BY, MemoryModuleType.HURT_BY_ENTITY, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.ATTACK_TARGET, MemoryModuleType.ATTACK_COOLING_DOWN, MemoryModuleType.INTERACTION_TARGET, MemoryModuleType.PATH, MemoryModuleType.ANGRY_AT, MemoryModuleType.UNIVERSAL_ANGER, MemoryModuleType.AVOID_TARGET, MemoryModuleType.ADMIRING_ITEM, MemoryModuleType.TIME_TRYING_TO_REACH_ADMIRE_ITEM, MemoryModuleType.ADMIRING_DISABLED, MemoryModuleType.DISABLE_WALK_TO_ADMIRE_ITEM, MemoryModuleType.CELEBRATE_LOCATION, MemoryModuleType.DANCING, MemoryModuleType.HUNTED_RECENTLY, MemoryModuleType.NEAREST_VISIBLE_BABY_HOGLIN, MemoryModuleType.NEAREST_VISIBLE_NEMESIS, MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED, MemoryModuleType.RIDE_TARGET, MemoryModuleType.VISIBLE_ADULT_PIGLIN_COUNT, MemoryModuleType.VISIBLE_ADULT_HOGLIN_COUNT, MemoryModuleType.NEAREST_VISIBLE_HUNTABLE_HOGLIN, MemoryModuleType.NEAREST_TARGETABLE_PLAYER_NOT_WEARING_GOLD, MemoryModuleType.NEAREST_PLAYER_HOLDING_WANTED_ITEM, MemoryModuleType.ATE_RECENTLY, MemoryModuleType.NEAREST_REPELLENT);


	public RudeHog(EntityType<? extends RudeHog> p_i48556_1_, Level p_i48556_2_) {
		super(p_i48556_1_, p_i48556_2_);
		this.xpReward = 30;
		this.setCanPickUpLoot(false);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 40.0D).add(Attributes.MOVEMENT_SPEED, (double) 0.35F).add(Attributes.ATTACK_DAMAGE, 5.0D);
	}

	protected Brain.Provider<RudeHog> revampedBrainProvider() {
		return Brain.provider(MEMORY_TYPES, SENSOR_TYPES);
	}


	@Override
	protected Brain<?> makeBrain(Dynamic<?> p_34723_) {
		return RudeHogAi.makeBrain(this, this.revampedBrainProvider().makeBrain(p_34723_));
	}

	@Override
	public InteractionResult mobInteract(Player p_34745_, InteractionHand p_34746_) {
		return InteractionResult.PASS;
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource p_219189_, DifficultyInstance p_219190_) {
		this.setItemInHand(InteractionHand.MAIN_HAND, HunterItems.BEAST_CUDGEL.get().getDefaultInstance());
		this.setGuaranteedDrop(EquipmentSlot.MAINHAND);
	}

	@Override
	public double getMeleeAttackRangeSqr(LivingEntity p_147273_) {
		return this.getMainHandItem().is(HunterItems.BEAST_CUDGEL.get()) ? super.getMeleeAttackRangeSqr(p_147273_) + 3.0F : super.getMeleeAttackRangeSqr(p_147273_);
	}

	@Override
	public void baseTick() {
		super.baseTick();
		if (this.level().isClientSide()) {
			if (this.attackAnimationTick < this.attackAnimationLength) {
				this.attackAnimationTick++;
			}

			if (this.attackAnimationTick >= this.attackAnimationLength) {
				this.attackAnimationState.stop();
			}

			if (this.level().isClientSide()) {
				if (this.isDancing()) {
					this.danceAnimationState.startIfStopped(this.tickCount);
				} else {
					this.danceAnimationState.stop();
				}
			}
		}
	}

	@Override
	public void handleEntityEvent(byte p_219360_) {
		if (p_219360_ == 4) {
			this.attackAnimationTick = 0;
			this.attackAnimationState.start(this.tickCount);
		} else {
			super.handleEntityEvent(p_219360_);
		}
	}

	public void holdInMainHand(ItemStack p_34784_) {
		super.holdInMainHand(p_34784_);
	}

	@Override
	public boolean canHunt() {
		return super.canHunt();
	}

	public void holdInOffHand(ItemStack p_34786_) {
		super.holdInOffHand(p_34786_);
	}

	public ItemStack addToInventory(ItemStack p_34779_) {
		return super.addToInventory(p_34779_);
	}

	public boolean canAddToInventory(ItemStack p_34781_) {
		return super.canAddToInventory(p_34781_);
	}

	public boolean canReplaceCurrentItem(ItemStack p_34788_) {
		EquipmentSlot equipmentslot = Mob.getEquipmentSlotForItem(p_34788_);
		ItemStack itemstack = this.getItemBySlot(equipmentslot);
		return this.canReplaceCurrentItem(p_34788_, itemstack);
	}

	public boolean canReplaceCurrentItem(ItemStack p_34712_, ItemStack p_34713_) {
		if (EnchantmentHelper.hasBindingCurse(p_34713_)) {
			return false;
		} else {
			boolean flag = RudeHogAi.isLovedItem(p_34712_) || p_34712_.is(Items.CROSSBOW);
			boolean flag1 = RudeHogAi.isLovedItem(p_34713_) || p_34713_.is(Items.CROSSBOW);
			if (flag && !flag1) {
				return true;
			} else if (!flag && flag1) {
				return false;
			} else {
				return this.isAdult() && !p_34712_.is(Items.CROSSBOW) && p_34713_.is(Items.CROSSBOW) ? false : super.canReplaceCurrentItem(p_34712_, p_34713_);
			}
		}
	}

	@Override
	public boolean isBaby() {
		return false;
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.PIGLIN_BRUTE_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource p_35072_) {
		return SoundEvents.PIGLIN_BRUTE_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.PIGLIN_BRUTE_DEATH;
	}

	protected void playStepSound(BlockPos p_35066_, BlockState p_35067_) {
		this.playSound(SoundEvents.PIGLIN_BRUTE_STEP, 0.15F, 1.0F);
	}

	protected void playConvertedSound() {
		this.playSound(SoundEvents.PIGLIN_BRUTE_CONVERTED_TO_ZOMBIFIED, 1.0F, this.getVoicePitch());
	}
}

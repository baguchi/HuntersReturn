package baguchan.hunterillager.entity;

import baguchan.hunterillager.HunterSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import baguchan.hunterillager.entity.HunterIllagerEntity;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class HunterIllagerEntity extends AbstractIllagerEntity {
	@Nullable
	private BlockPos homeTarget;
	public HunterIllagerEntity(EntityType<? extends HunterIllagerEntity> p_i48556_1_, World p_i48556_2_) {
		super(p_i48556_1_, p_i48556_2_);
		((GroundPathNavigator) this.getNavigation()).setCanOpenDoors(true);
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new OpenDoorGoal(this, true));
		this.goalSelector.addGoal(2, new AbstractIllagerEntity.RaidOpenDoorGoal(this));
		this.goalSelector.addGoal(3, new AbstractRaiderEntity.FindTargetGoal(this, 10.0F));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 0.95F, true));
		this.goalSelector.addGoal(5, new MoveToGoal(this, 26.0D, 1.0D));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, AbstractRaiderEntity.class)).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
		this.goalSelector.addGoal(8, new RandomWalkingGoal(this, 0.6D));
		this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.35F).add(Attributes.FOLLOW_RANGE, 20.0D).add(Attributes.MAX_HEALTH, 26.0D).add(Attributes.ATTACK_DAMAGE, 3.0D);
	}

	public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
		super.addAdditionalSaveData(p_213281_1_);
		if (this.homeTarget != null) {
			p_213281_1_.put("HomeTarget", NBTUtil.writeBlockPos(this.homeTarget));
		}

	}

	public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
		super.readAdditionalSaveData(p_70037_1_);
		if (p_70037_1_.contains("HomeTarget")) {
			this.homeTarget = NBTUtil.readBlockPos(p_70037_1_.getCompound("HomeTarget"));
		}
	}
	@OnlyIn(Dist.CLIENT)
	public AbstractIllagerEntity.ArmPose getArmPose() {
		if (this.isAggressive()) {
			return AbstractIllagerEntity.ArmPose.ATTACKING;
		} else {
			return this.isCelebrating() ? AbstractIllagerEntity.ArmPose.CELEBRATING : AbstractIllagerEntity.ArmPose.CROSSED;
		}
	}

	@Override
	public void applyRaidBuffs(int p_213660_1_, boolean p_213660_2_) {

	}

	@Nullable
	public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
		ILivingEntityData ilivingentitydata = super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
		((GroundPathNavigator) this.getNavigation()).setCanOpenDoors(true);
		this.populateDefaultEquipmentSlots(p_213386_2_);
		this.populateDefaultEquipmentEnchantments(p_213386_2_);
		return ilivingentitydata;
	}

	protected void populateDefaultEquipmentSlots(DifficultyInstance p_180481_1_) {
		if (this.getCurrentRaid() == null) {
			this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.WOODEN_SWORD));
		}
	}

	public boolean isAlliedTo(Entity p_184191_1_) {
		if (super.isAlliedTo(p_184191_1_)) {
			return true;
		} else if (p_184191_1_ instanceof LivingEntity && ((LivingEntity) p_184191_1_).getMobType() == CreatureAttribute.ILLAGER) {
			return this.getTeam() == null && p_184191_1_.getTeam() == null;
		} else {
			return false;
		}
	}

	@Override
	public SoundEvent getCelebrateSound() {
		return HunterSounds.HUNTER_ILLAGER_LAUGH;
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.VINDICATOR_AMBIENT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.VINDICATOR_DEATH;
	}

	protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
		return SoundEvents.VINDICATOR_HURT;
	}

	@Override
	public void killed(ServerWorld p_241847_1_, LivingEntity p_241847_2_) {
		super.killed(p_241847_1_, p_241847_2_);
		this.playSound(HunterSounds.HUNTER_ILLAGER_LAUGH, this.getSoundVolume(), this.getVoicePitch());
	}

	public void setHomeTarget(@Nullable BlockPos p_213726_1_) {
		this.homeTarget = p_213726_1_;
	}

	@Nullable
	private BlockPos getHomeTarget() {
		return this.homeTarget;
	}

	class MoveToGoal extends Goal {
		final HunterIllagerEntity trader;
		final double stopDistance;
		final double speedModifier;

		MoveToGoal(HunterIllagerEntity p_i50459_2_, double p_i50459_3_, double p_i50459_5_) {
			this.trader = p_i50459_2_;
			this.stopDistance = p_i50459_3_;
			this.speedModifier = p_i50459_5_;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		public void stop() {
			this.trader.setHomeTarget((BlockPos)null);
			HunterIllagerEntity.this.navigation.stop();
		}

		public boolean canUse() {
			BlockPos blockpos = this.trader.getHomeTarget();
			return blockpos != null && this.isTooFarAway(blockpos, this.stopDistance);
		}

		public void tick() {
			BlockPos blockpos = this.trader.getHomeTarget();
			if (blockpos != null && HunterIllagerEntity.this.navigation.isDone()) {
				if (this.isTooFarAway(blockpos, 10.0D)) {
					Vector3d vector3d = (new Vector3d((double)blockpos.getX() - this.trader.getX(), (double)blockpos.getY() - this.trader.getY(), (double)blockpos.getZ() - this.trader.getZ())).normalize();
					Vector3d vector3d1 = vector3d.scale(10.0D).add(this.trader.getX(), this.trader.getY(), this.trader.getZ());
					HunterIllagerEntity.this.navigation.moveTo(vector3d1.x, vector3d1.y, vector3d1.z, this.speedModifier);
				} else {
					HunterIllagerEntity.this.navigation.moveTo((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), this.speedModifier);
				}
			}

		}

		private boolean isTooFarAway(BlockPos p_220846_1_, double p_220846_2_) {
			return !p_220846_1_.closerThan(this.trader.position(), p_220846_2_);
		}
	}
}

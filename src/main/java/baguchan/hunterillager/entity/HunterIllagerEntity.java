package baguchan.hunterillager.entity;

import baguchan.hunterillager.HunterSounds;
import com.google.common.collect.Maps;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.raid.Raid;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class HunterIllagerEntity extends AbstractIllagerEntity implements IRangedAttackMob {
	public static final Predicate<LivingEntity> TARGET_FOOD_SELECTOR = (p_213616_0_) -> {
		return !p_213616_0_.isBaby() && p_213616_0_.getType() != EntityType.CAT && p_213616_0_.getType() != EntityType.PARROT && p_213616_0_.getType() != EntityType.WOLF && p_213616_0_.getType() != EntityType.PANDA;
	};
	private static final Predicate<? super ItemEntity> ALLOWED_ITEMS = (p_213616_0_) -> {
		return p_213616_0_.getItem().getItem().getFoodProperties() != null && p_213616_0_.getItem().getItem().getFoodProperties().isMeat();
	};

	private final Inventory inventory = new Inventory(5);

	@Nullable
	private BlockPos homeTarget;
	private int cooldown;

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
		this.goalSelector.addGoal(4, new RangedBowAttackGoal<>(this, 1.0F, 50, 16.0F));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 0.95F, true) {
			@Override
			public boolean canUse() {
				return !(mob.getMainHandItem().getItem() instanceof BowItem) && super.canUse();
			}

			@Override
			public boolean canContinueToUse() {
				return !(mob.getMainHandItem().getItem() instanceof BowItem) && super.canContinueToUse();
			}
		});
		this.goalSelector.addGoal(5, new MoveToGoal(this, 26.0D, 1.0D));
		this.goalSelector.addGoal(6, new GetFoodGoal<>(this));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, AbstractRaiderEntity.class)).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, AnimalEntity.class, 10, true, false, TARGET_FOOD_SELECTOR) {
			@Override
			public boolean canUse() {
				return cooldown <= 0 && super.canUse();
			}
		});
		this.goalSelector.addGoal(8, new RandomWalkingGoal(this, 0.6D));
		this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
	}

	protected void completeUsingItem() {
		Hand hand = this.getUsedItemHand();
		if (this.useItem.equals(this.getItemInHand(hand))) {
			if (!this.useItem.isEmpty() && this.isUsingItem()) {
				ItemStack copy = this.useItem.copy();

				if (copy.getItem().getFoodProperties() != null) {
					this.heal(copy.getItem().getFoodProperties().getNutrition());
				}
			}
		}
		super.completeUsingItem();
	}

	public void aiStep() {
		if (!this.level.isClientSide && this.isAlive()) {
			if (!this.isUsingItem() && this.getItemInHand(Hand.OFF_HAND).isEmpty()) {
				ItemStack food = ItemStack.EMPTY;

				if (this.getHealth() < this.getMaxHealth() && this.random.nextFloat() < 0.0025F) {
					food = this.findFood();
				}

				if (!food.isEmpty()) {
					this.setItemSlot(EquipmentSlotType.OFFHAND, food);
					this.startUsingItem(Hand.OFF_HAND);
				}
			}
		}

		super.aiStep();
	}

	private ItemStack findFood() {
		for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
			ItemStack itemstack = this.inventory.getItem(i);
			if (!itemstack.isEmpty() && itemstack.getItem().getFoodProperties() != null && itemstack.getItem().getFoodProperties().isMeat()) {
				return itemstack.split(1);
			}
		}
		return ItemStack.EMPTY;
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.35F).add(Attributes.FOLLOW_RANGE, 20.0D).add(Attributes.MAX_HEALTH, 26.0D).add(Attributes.ATTACK_DAMAGE, 3.0D);
	}

	public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
		super.addAdditionalSaveData(p_213281_1_);
		if (this.homeTarget != null) {
			p_213281_1_.put("HomeTarget", NBTUtil.writeBlockPos(this.homeTarget));
		}
		ListNBT listnbt = new ListNBT();

		for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
			ItemStack itemstack = this.inventory.getItem(i);
			if (!itemstack.isEmpty()) {
				listnbt.add(itemstack.save(new CompoundNBT()));
			}
		}

		p_213281_1_.put("Inventory", listnbt);

		p_213281_1_.putInt("HuntingCooldown", this.cooldown);
	}

	public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
		super.readAdditionalSaveData(p_70037_1_);
		if (p_70037_1_.contains("HomeTarget")) {
			this.homeTarget = NBTUtil.readBlockPos(p_70037_1_.getCompound("HomeTarget"));
		}
		ListNBT listnbt = p_70037_1_.getList("Inventory", 10);

		for (int i = 0; i < listnbt.size(); ++i) {
			ItemStack itemstack = ItemStack.of(listnbt.getCompound(i));
			if (!itemstack.isEmpty()) {
				this.inventory.addItem(itemstack);
			}
		}

		this.cooldown = p_70037_1_.getInt("HuntingCooldown");
		this.setCanPickUpLoot(true);
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
		ItemStack itemstack = new ItemStack(Items.STONE_SWORD);

		Raid raid = this.getCurrentRaid();
		int i = 1;
		if (p_213660_1_ > raid.getNumGroups(Difficulty.NORMAL)) {
			i = 2;
		}

		inventory.addItem(new ItemStack(Items.PORKCHOP, 6));

		boolean flag = this.random.nextFloat() <= raid.getEnchantOdds();
		if (flag) {
			Map<Enchantment, Integer> map = Maps.newHashMap();
			map.put(Enchantments.SHARPNESS, i);
			EnchantmentHelper.setEnchantments(map, itemstack);

			inventory.addItem(new ItemStack(Items.GOLDEN_APPLE));
		}

		this.setItemInHand(Hand.MAIN_HAND, itemstack);
	}

	protected void pickUpItem(ItemEntity p_175445_1_) {
		ItemStack itemstack = p_175445_1_.getItem();
		if (itemstack.getItem() instanceof BannerItem) {
			super.pickUpItem(p_175445_1_);
		} else {
			Item item = itemstack.getItem();
			if (this.wantsFood(itemstack)) {
				this.onItemPickup(p_175445_1_);
				this.take(p_175445_1_, itemstack.getCount());
				ItemStack itemstack1 = this.inventory.addItem(itemstack);
				if (itemstack1.isEmpty()) {
					p_175445_1_.remove();
				} else {
					itemstack.setCount(itemstack1.getCount());
				}
			}
		}

	}

	public boolean setSlot(int p_174820_1_, ItemStack p_174820_2_) {
		if (super.setSlot(p_174820_1_, p_174820_2_)) {
			return true;
		} else {
			int i = p_174820_1_ - 300;
			if (i >= 0 && i < this.inventory.getContainerSize()) {
				this.inventory.setItem(i, p_174820_2_);
				return true;
			} else {
				return false;
			}
		}
	}

	private boolean wantsFood(ItemStack p_213672_1_) {
		return p_213672_1_.getItem().getFoodProperties() != null && p_213672_1_.getItem().getFoodProperties().isMeat();
	}

	@Nullable
	public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
		ILivingEntityData ilivingentitydata = super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
		((GroundPathNavigator) this.getNavigation()).setCanOpenDoors(true);
		this.populateDefaultEquipmentSlots(p_213386_2_);
		this.populateDefaultEquipmentEnchantments(p_213386_2_);
		return ilivingentitydata;
	}

	protected void dropEquipment() {
		super.dropEquipment();
		if (this.inventory != null) {
			for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
				ItemStack itemstack = this.inventory.getItem(i);
				if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
					this.spawnAtLocation(itemstack);
				}
			}
		}
	}

	protected void populateDefaultEquipmentSlots(DifficultyInstance p_180481_1_) {
		if (this.getCurrentRaid() == null) {
			inventory.addItem(new ItemStack(Items.PORKCHOP, 4));
			if (this.random.nextFloat() < 0.5F) {
				this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.BOW));
			} else {
				this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.WOODEN_SWORD));
			}
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
		this.cooldown = 300;
	}

	public void setHomeTarget(@Nullable BlockPos p_213726_1_) {
		this.homeTarget = p_213726_1_;
	}

	@Nullable
	private BlockPos getHomeTarget() {
		return this.homeTarget;
	}

	@Override
	public void performRangedAttack(LivingEntity p_82196_1_, float p_82196_2_) {
		ItemStack itemstack = this.getProjectile(this.getItemInHand(ProjectileHelper.getWeaponHoldingHand(this, Items.BOW)));
		AbstractArrowEntity abstractarrowentity = ProjectileHelper.getMobArrow(this, itemstack, p_82196_2_);
		if (this.getMainHandItem().getItem() instanceof net.minecraft.item.BowItem)
			abstractarrowentity = ((net.minecraft.item.BowItem) this.getMainHandItem().getItem()).customArrow(abstractarrowentity);
		double d0 = p_82196_1_.getX() - this.getX();
		double d1 = p_82196_1_.getY(0.3333333333333333D) - abstractarrowentity.getY();
		double d2 = p_82196_1_.getZ() - this.getZ();
		double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
		abstractarrowentity.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F, (float) (14 - this.level.getDifficulty().getId() * 4));
		this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
		this.level.addFreshEntity(abstractarrowentity);
	}

	class MoveToGoal extends Goal {
		final HunterIllagerEntity hunter;
		final double stopDistance;
		final double speedModifier;

		MoveToGoal(HunterIllagerEntity p_i50459_2_, double p_i50459_3_, double p_i50459_5_) {
			this.hunter = p_i50459_2_;
			this.stopDistance = p_i50459_3_;
			this.speedModifier = p_i50459_5_;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		public void stop() {
			this.hunter.setHomeTarget((BlockPos) null);
			HunterIllagerEntity.this.navigation.stop();
		}

		public boolean canUse() {
			BlockPos blockpos = this.hunter.getHomeTarget();

			double distance = this.hunter.level.isDay() ? this.stopDistance : this.stopDistance / 3.0F;

			return blockpos != null && this.isTooFarAway(blockpos, distance);
		}

		public void tick() {
			BlockPos blockpos = this.hunter.getHomeTarget();
			if (blockpos != null && HunterIllagerEntity.this.navigation.isDone()) {
				if (this.isTooFarAway(blockpos, 10.0D)) {
					Vector3d vector3d = (new Vector3d((double) blockpos.getX() - this.hunter.getX(), (double) blockpos.getY() - this.hunter.getY(), (double) blockpos.getZ() - this.hunter.getZ())).normalize();
					Vector3d vector3d1 = vector3d.scale(10.0D).add(this.hunter.getX(), this.hunter.getY(), this.hunter.getZ());
					HunterIllagerEntity.this.navigation.moveTo(vector3d1.x, vector3d1.y, vector3d1.z, this.speedModifier);
				} else {
					HunterIllagerEntity.this.navigation.moveTo((double) blockpos.getX(), (double) blockpos.getY(), (double) blockpos.getZ(), this.speedModifier);
				}
			}

		}

		private boolean isTooFarAway(BlockPos p_220846_1_, double p_220846_2_) {
			return !p_220846_1_.closerThan(this.hunter.position(), p_220846_2_);
		}
	}

	public class GetFoodGoal<T extends HunterIllagerEntity> extends Goal {
		private final T mob;

		public GetFoodGoal(T p_i50572_2_) {
			this.mob = p_i50572_2_;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		public boolean canUse() {
			if (!this.mob.hasActiveRaid()) {

				List<ItemEntity> list = this.mob.level.getEntitiesOfClass(ItemEntity.class, this.mob.getBoundingBox().inflate(16.0D, 8.0D, 16.0D), HunterIllagerEntity.ALLOWED_ITEMS);
				if (!list.isEmpty()) {
					return this.mob.getNavigation().moveTo(list.get(0), (double) 1.15F);
				}


				return false;
			} else {
				return false;
			}
		}

		public void tick() {
			if (this.mob.getNavigation().getTargetPos().closerThan(this.mob.position(), 1.414D)) {
				List<ItemEntity> list = this.mob.level.getEntitiesOfClass(ItemEntity.class, this.mob.getBoundingBox().inflate(4.0D, 4.0D, 4.0D), HunterIllagerEntity.ALLOWED_ITEMS);
				if (!list.isEmpty()) {
					this.mob.pickUpItem(list.get(0));
				}
			}

		}
	}
}

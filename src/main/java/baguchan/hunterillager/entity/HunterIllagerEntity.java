package baguchan.hunterillager.entity;

import baguchan.hunterillager.entity.ai.BoomeranAttackGoal;
import baguchan.hunterillager.entity.ai.DoSleepingGoal;
import baguchan.hunterillager.entity.ai.SleepOnBedGoal;
import baguchan.hunterillager.entity.ai.WakeUpGoal;
import baguchan.hunterillager.entity.projectile.BoomerangEntity;
import baguchan.hunterillager.init.HunterItems;
import baguchan.hunterillager.init.HunterSounds;
import baguchan.hunterillager.utils.HunterConfigUtils;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class HunterIllagerEntity extends AbstractIllager implements RangedAttackMob {
	public static final Predicate<LivingEntity> TARGET_ENTITY_SELECTOR = (p_213616_0_) -> {
		return !p_213616_0_.isBaby() && HunterConfigUtils.isWhitelistedEntity(p_213616_0_.getType());
	};
	private static final Predicate<? super ItemEntity> ALLOWED_ITEMS = (p_213616_0_) -> {
		return p_213616_0_.getItem().getItem().getFoodProperties() != null && HunterConfigUtils.isWhitelistedItem(p_213616_0_.getItem().getItem());
	};

	private final SimpleContainer inventory = new SimpleContainer(5);

	@Nullable
	private BlockPos homeTarget;
	private int cooldown;

	public HunterIllagerEntity(EntityType<? extends HunterIllagerEntity> p_i48556_1_, Level p_i48556_2_) {
		super(p_i48556_1_, p_i48556_2_);
		((GroundPathNavigation) this.getNavigation()).setCanOpenDoors(true);
		this.setCanPickUpLoot(true);
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new WakeUpGoal(this));
		this.goalSelector.addGoal(0, new DoSleepingGoal(this));
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new OpenDoorGoal(this, true));
		this.goalSelector.addGoal(2, new AbstractIllager.RaiderOpenDoorGoal(this));
		this.goalSelector.addGoal(3, new Raider.HoldGroundAttackGoal(this, 10.0F));
		this.goalSelector.addGoal(4, new RangedBowAttackGoal<>(this, 1.1F, 50, 16.0F));
		this.goalSelector.addGoal(4, new BoomeranAttackGoal(this, 50, 16.0F));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.15F, true) {
			@Override
			public boolean canUse() {
				return !(mob.getMainHandItem().getItem() instanceof BowItem) && super.canUse();
			}

			@Override
			public boolean canContinueToUse() {
				return !(mob.getMainHandItem().getItem() instanceof BowItem) && super.canContinueToUse();
			}
		});
		this.goalSelector.addGoal(5, new SleepOnBedGoal(this, 1.0F, 8));
		this.goalSelector.addGoal(6, new MoveToGoal(this, 26.0D, 1.2D));
		this.goalSelector.addGoal(7, new GetFoodGoal<>(this));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Goat.class, true));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, Animal.class, 10, true, false, TARGET_ENTITY_SELECTOR) {
			@Override
			public boolean canUse() {
				return cooldown <= 0 && super.canUse();
			}
		});
		this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.8D));
		this.goalSelector.addGoal(9, new InteractGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
	}

	protected void completeUsingItem() {
		InteractionHand hand = this.getUsedItemHand();
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
			ItemStack mainhand = this.getItemInHand(InteractionHand.MAIN_HAND);

			if (!this.isUsingItem() && this.getItemInHand(InteractionHand.OFF_HAND).isEmpty() && (mainhand.getItem() == Items.BOW && this.getTarget() == null || mainhand.getItem() != Items.BOW)) {
				ItemStack food = ItemStack.EMPTY;

				if (this.getHealth() < this.getMaxHealth() && this.random.nextFloat() < 0.0025F) {
					food = this.findFood();
				}

				if (!food.isEmpty()) {
					this.setItemSlot(EquipmentSlot.OFFHAND, food);
					this.startUsingItem(InteractionHand.OFF_HAND);
				}
			}
		}

		super.aiStep();
	}

	private ItemStack findFood() {
		for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
			ItemStack itemstack = this.inventory.getItem(i);
			if (!itemstack.isEmpty() && itemstack.getItem().getFoodProperties() != null && HunterConfigUtils.isWhitelistedItem(itemstack.getItem())) {
				return itemstack.split(1);
			}
		}
		return ItemStack.EMPTY;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.3F).add(Attributes.FOLLOW_RANGE, 20.0D).add(Attributes.MAX_HEALTH, 26.0D).add(Attributes.ARMOR, 1.0D).add(Attributes.ATTACK_DAMAGE, 3.0D);
	}

	public void addAdditionalSaveData(CompoundTag p_213281_1_) {
		super.addAdditionalSaveData(p_213281_1_);
		if (this.homeTarget != null) {
			p_213281_1_.put("HomeTarget", NbtUtils.writeBlockPos(this.homeTarget));
		}
		ListTag listnbt = new ListTag();

		for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
			ItemStack itemstack = this.inventory.getItem(i);
			if (!itemstack.isEmpty()) {
				listnbt.add(itemstack.save(new CompoundTag()));
			}
		}

		p_213281_1_.put("Inventory", listnbt);

		p_213281_1_.putInt("HuntingCooldown", this.cooldown);
	}

	public void readAdditionalSaveData(CompoundTag p_70037_1_) {
		super.readAdditionalSaveData(p_70037_1_);
		if (p_70037_1_.contains("HomeTarget")) {
			this.homeTarget = NbtUtils.readBlockPos(p_70037_1_.getCompound("HomeTarget"));
		}
		ListTag listnbt = p_70037_1_.getList("Inventory", 10);

		for (int i = 0; i < listnbt.size(); ++i) {
			ItemStack itemstack = ItemStack.of(listnbt.getCompound(i));
			if (!itemstack.isEmpty()) {
				this.inventory.addItem(itemstack);
			}
		}

		this.cooldown = p_70037_1_.getInt("HuntingCooldown");
		this.setCanPickUpLoot(true);
	}

	@Override
	public void applyRaidBuffs(int p_213660_1_, boolean p_213660_2_) {
		ItemStack itemstack;
		ItemStack offHandStack = new ItemStack(HunterItems.BOOMERANG);


		Raid raid = this.getCurrentRaid();


		int i = 1;
		if (p_213660_1_ > raid.getNumGroups(Difficulty.NORMAL)) {
			i = 2;
		}

		if (raid.getBadOmenLevel() < 2 || p_213660_1_ <= raid.getNumGroups(Difficulty.NORMAL)) {
			itemstack = this.random.nextBoolean() ? new ItemStack(Items.BOW) : new ItemStack(Items.STONE_SWORD);
		} else {
			itemstack = this.random.nextBoolean() ? new ItemStack(Items.BOW) : new ItemStack(Items.IRON_SWORD);
		}

		inventory.addItem(new ItemStack(Items.PORKCHOP, 4));

		boolean flag = this.random.nextFloat() <= raid.getEnchantOdds();
		if (flag) {
			if (itemstack.getItem() == Items.BOW) {
				Map<Enchantment, Integer> map = Maps.newHashMap();
				map.put(Enchantments.POWER_ARROWS, i);
				EnchantmentHelper.setEnchantments(map, itemstack);
			} else {
				Map<Enchantment, Integer> map = Maps.newHashMap();
				map.put(Enchantments.SHARPNESS, i);
				EnchantmentHelper.setEnchantments(map, itemstack);
			}

			inventory.addItem(new ItemStack(Items.COOKED_BEEF, 2));


			Map<Enchantment, Integer> map2 = Maps.newHashMap();
			map2.put(Enchantments.SHARPNESS, i);
			EnchantmentHelper.setEnchantments(map2, offHandStack);
		}

		if (this.random.nextFloat() < 0.25F) {
			Map<Enchantment, Integer> map3 = Maps.newHashMap();
			map3.put(Enchantments.LOYALTY, i);
			EnchantmentHelper.setEnchantments(map3, offHandStack);

			this.setItemInHand(InteractionHand.OFF_HAND, offHandStack);
		}
		this.setItemInHand(InteractionHand.MAIN_HAND, itemstack);
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
					p_175445_1_.discard();
				} else {
					itemstack.setCount(itemstack1.getCount());
				}
			} else if (item == HunterItems.BOOMERANG && this.getOffhandItem().isEmpty()) {
				this.onItemPickup(p_175445_1_);
				this.take(p_175445_1_, itemstack.getCount());
				this.setItemInHand(InteractionHand.OFF_HAND, itemstack);
			}
		}

	}

	private boolean wantsFood(ItemStack p_213672_1_) {
		return p_213672_1_.getItem().getFoodProperties() != null && HunterConfigUtils.isWhitelistedItem(p_213672_1_.getItem());
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_37856_, DifficultyInstance p_37857_, MobSpawnType p_37858_, @Nullable SpawnGroupData p_37859_, @Nullable CompoundTag p_37860_) {
		SpawnGroupData ilivingentitydata = super.finalizeSpawn(p_37856_, p_37857_, p_37858_, p_37859_, p_37860_);
		((GroundPathNavigation) this.getNavigation()).setCanOpenDoors(true);
		this.setCanPickUpLoot(true);
		this.populateDefaultEquipmentSlots(p_37857_);
		this.populateDefaultEquipmentEnchantments(p_37857_);
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
			if (this.random.nextFloat() < 0.5F) {
				inventory.addItem(new ItemStack(Items.PORKCHOP, 2 + this.random.nextInt(2)));
			} else {
				inventory.addItem(new ItemStack(Items.BEEF, 2 + this.random.nextInt(2)));
			}
			if (this.random.nextFloat() < 0.5F) {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
			} else {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.WOODEN_SWORD));
				if (this.random.nextBoolean()) {
					ItemStack offHandStack = new ItemStack(HunterItems.BOOMERANG);

					Map<Enchantment, Integer> map3 = Maps.newHashMap();
					map3.put(Enchantments.LOYALTY, 1);
					EnchantmentHelper.setEnchantments(map3, offHandStack);

					this.setItemInHand(InteractionHand.OFF_HAND, offHandStack);
				}
			}
		}
	}

	public boolean isAlliedTo(Entity p_184191_1_) {
		if (super.isAlliedTo(p_184191_1_)) {
			return true;
		} else if (p_184191_1_ instanceof LivingEntity && ((LivingEntity) p_184191_1_).getMobType() == MobType.ILLAGER) {
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


	@OnlyIn(Dist.CLIENT)
	public AbstractIllager.IllagerArmPose getArmPose() {
		if (this.isAggressive()) {
			return this.isHolding(Items.BOW) || this.isHolding(HunterItems.BOOMERANG) ? AbstractIllager.IllagerArmPose.BOW_AND_ARROW : AbstractIllager.IllagerArmPose.ATTACKING;
		} else {
			return this.isCelebrating() ? AbstractIllager.IllagerArmPose.CELEBRATING : AbstractIllager.IllagerArmPose.CROSSED;
		}
	}

	@Override
	public void killed(ServerLevel p_241847_1_, LivingEntity p_241847_2_) {
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
	public void performRangedAttack(LivingEntity p_32141_, float p_32142_) {
		ItemStack itemstack = this.getProjectile(this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof net.minecraft.world.item.BowItem)));
		AbstractArrow abstractarrow = this.getArrow(itemstack, p_32142_);
		if (this.getMainHandItem().getItem() instanceof net.minecraft.world.item.BowItem)
			abstractarrow = ((net.minecraft.world.item.BowItem) this.getMainHandItem().getItem()).customArrow(abstractarrow);
		double d0 = p_32141_.getX() - this.getX();
		double d1 = p_32141_.getY(0.3333333333333333D) - abstractarrow.getY();
		double d2 = p_32141_.getZ() - this.getZ();
		double d3 = Math.sqrt(d0 * d0 + d2 * d2);
		abstractarrow.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F, (float) (14 - this.level.getDifficulty().getId() * 4));
		this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
		this.level.addFreshEntity(abstractarrow);
	}

	protected AbstractArrow getArrow(ItemStack p_32156_, float p_32157_) {
		return ProjectileUtil.getMobArrow(this, p_32156_, p_32157_);
	}

	public void performBoomeranAttack(LivingEntity p_82196_1_, float p_82196_2_) {
		BoomerangEntity boomerang = new BoomerangEntity(this.level, this, this.getOffhandItem());
		double d0 = p_82196_1_.getX() - this.getX();
		double d1 = p_82196_1_.getY(0.3333333333333333D) - boomerang.getY();
		double d2 = p_82196_1_.getZ() - this.getZ();
		double d3 = (double) Mth.sqrt((float) (d0 * d0 + d2 * d2));
		boomerang.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F, (float) (14 - this.level.getDifficulty().getId() * 4));
		this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
		this.level.addFreshEntity(boomerang);
		this.getOffhandItem().shrink(1);
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
					Vec3 vector3d = (new Vec3((double) blockpos.getX() - this.hunter.getX(), (double) blockpos.getY() - this.hunter.getY(), (double) blockpos.getZ() - this.hunter.getZ())).normalize();
					Vec3 vector3d1 = vector3d.scale(10.0D).add(this.hunter.getX(), this.hunter.getY(), this.hunter.getZ());
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
				if (!list.isEmpty() && this.mob.hasLineOfSight(list.get(0))) {
					return this.mob.getNavigation().moveTo(list.get(0), (double) 1.1F);
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

package baguchan.hunterillager.entity.projectile;

import baguchan.hunterillager.entity.Hunter;
import baguchan.hunterillager.init.HunterDamageSource;
import baguchan.hunterillager.init.HunterEnchantments;
import baguchan.hunterillager.init.HunterEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class BoomerangEntity extends ThrowableItemProjectile {
	private static final EntityDataAccessor<Byte> LOYALTY_LEVEL = SynchedEntityData.defineId(BoomerangEntity.class, EntityDataSerializers.BYTE);

	private static final EntityDataAccessor<Byte> PIERCING_LEVEL = SynchedEntityData.defineId(BoomerangEntity.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Byte> BOUNCE_LEVEL = SynchedEntityData.defineId(BoomerangEntity.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Boolean> RETURNING = SynchedEntityData.defineId(BoomerangEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<ItemStack> BOOMERANG = SynchedEntityData.defineId(BoomerangEntity.class, EntityDataSerializers.ITEM_STACK);

	private int totalHits;

	private int flyTick;

	public BoomerangEntity(EntityType<? extends BoomerangEntity> entityEntityType, Level world) {
		super(entityEntityType, world);
	}

	public BoomerangEntity(EntityType<? extends BoomerangEntity> type, Level world, LivingEntity shootingEntity, ItemStack boomerang) {
		super(type, shootingEntity, world);
		setOwner(shootingEntity);
		setBoomerang(boomerang);
		this.entityData.set(LOYALTY_LEVEL, (byte) EnchantmentHelper.getLoyalty(boomerang));
		this.entityData.set(PIERCING_LEVEL, (byte) EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PIERCING, boomerang));
		this.entityData.set(BOUNCE_LEVEL, (byte) EnchantmentHelper.getItemEnchantmentLevel(HunterEnchantments.BOUNCE.get(), boomerang));
		this.totalHits = 0;
	}

	public BoomerangEntity(Level world, LivingEntity entity, ItemStack boomerang) {
		this(HunterEntityRegistry.BOOMERANG.get(), world, entity, boomerang);
	}


	private void onHitFluid(BlockHitResult result) {
		double velocity = getVelocity();
		double horizontal = getDeltaMovement().y * getDeltaMovement().y;
		if (result.getType() == HitResult.Type.BLOCK && result.isInside() &&
				velocity >= 0.6499999761581421D && horizontal < 0.17499999701976776D)
			if (!this.level.getBlockState(result.getBlockPos()).isAir() && this.level.getFluidState(result.getBlockPos()).is(FluidTags.WATER)) {
				setDeltaMovement(getDeltaMovement().x, Mth.clamp(getDeltaMovement().y + 0.10000000149011612D, -0.10000000149011612D, 0.30000001192092896D), getDeltaMovement().z);
				this.hasImpulse = true;
			}
	}

	public DamageSource boomerangAttack(@Nullable Entity p_270857_) {
		return this.damageSources().source(HunterDamageSource.BOOMERANG, this, p_270857_);
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		boolean returnToOwner = false;
		int loyaltyLevel = (this.entityData.get(LOYALTY_LEVEL)).byteValue();
		int piercingLevel = (this.entityData.get(PIERCING_LEVEL)).byteValue();
		Entity shooter = getOwner();
		if (result.getEntity() != getOwner())
			if (!isReturning() || loyaltyLevel <= 0) {

				int sharpness = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, getBoomerang());
				int damage = (int) ((3.0D * Math.sqrt(getDeltaMovement().x * getDeltaMovement().x + getDeltaMovement().y * getDeltaMovement().y * 0.5D + getDeltaMovement().z * getDeltaMovement().z) + Math.min(1, sharpness) + Math.max(0, sharpness - 1) * 0.5D) + 0.5F * piercingLevel);

				if (damage != 0) {
					result.getEntity().hurt(this.boomerangAttack(shooter), damage);
				}
				if (shooter instanceof LivingEntity) {
					getBoomerang().hurtAndBreak(1, (LivingEntity) shooter, p_222182_1_ -> {
					});
				}

				double speed = getSpeed();
				if (piercingLevel < 1 && this.totalHits >= this.getBounceLevel() || this.totalHits >= piercingLevel + this.getBounceLevel() && speed > 0.4000000059604645D) {
					returnToOwner = true;

				} else if (piercingLevel < 1 && this.totalHits < this.getBounceLevel() || this.totalHits < piercingLevel + this.getBounceLevel() && speed <= 0.4000000059604645D) {
					Vec3 motion = getDeltaMovement();
					double motionX = motion.x;
					double motionY = motion.y;
					double motionZ = motion.z;
					motionX = -motionX;
					motionZ = -motionZ;
					setDeltaMovement(motionX, motionY, motionZ);
				}
				this.totalHits++;
			}
		if (returnToOwner && !isReturning())
			if (getOwner() != null && shouldReturnToThrower() && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.LOYALTY, getBoomerang()) > 0) {
				this.level.playSound(null, shooter.blockPosition(), SoundEvents.TRIDENT_RETURN, SoundSource.PLAYERS, 1.0F, 1.0F);
				Vec3 motion = getDeltaMovement();
				double motionX = motion.x;
				double motionY = motion.y;
				double motionZ = motion.z;
				motionX = -motionX;
				motionZ = -motionZ;
				setDeltaMovement(motionX, motionY, motionZ);
				//func_70018_K();
				if (loyaltyLevel > 0 && !isReturning() &&
						shooter != null) {
					this.level.playSound(null, shooter.blockPosition(), SoundEvents.TRIDENT_RETURN, SoundSource.PLAYERS, 1.0F, 1.0F);
					setReturning(true);
				}
			} else {
				Vec3 motion = getDeltaMovement();
				double motionX = motion.x;
				double motionY = motion.y;
				double motionZ = motion.z;
				motionX = -motionX;
				motionZ = -motionZ;
				setDeltaMovement(motionX, motionY, motionZ);
				if (loyaltyLevel > 0 && !isReturning() &&
						shooter != null) {
					this.level.playSound(null, shooter.blockPosition(), SoundEvents.TRIDENT_RETURN, SoundSource.PLAYERS, 1.0F, 1.0F);
					setReturning(true);
				}
			}
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		BlockPos pos = result.getBlockPos();
		BlockState state = this.level.getBlockState(pos);
		SoundType soundType = state.getSoundType(this.level, pos, this);
		if (!isReturning())
			this.level.playSound(null, getX(), getY(), getZ(), soundType.getHitSound(), SoundSource.BLOCKS, soundType.getVolume(), soundType.getPitch());
		this.totalHits++;
		BlockState blockstate = this.level.getBlockState(result.getBlockPos());
		int loyaltyLevel = this.entityData.get(LOYALTY_LEVEL).byteValue();
		int piercingLevel = this.entityData.get(PIERCING_LEVEL).byteValue();
		Entity entity = getOwner();
		if (!isReturning() && !blockstate.getCollisionShape(this.level, result.getBlockPos()).isEmpty()) {
			Vec3i direction = result.getDirection().getNormal();
			switch (result.getDirection()) {
				case UP, SOUTH, EAST -> direction = direction.multiply(-1);
				default -> {
				}
			}
			direction = new Vec3i(direction.getX() == 0 ? 1 : direction.getX(), direction.getY() == 0 ? 1 : direction.getY(), direction.getZ() == 0 ? 1 : direction.getZ());
			this.setDeltaMovement(this.getDeltaMovement().multiply(new Vec3(direction.getX(), direction.getY(), direction.getZ())));
			this.playSound(SoundEvents.WOOD_STEP, 0.5F, 1.0F);

			if (loyaltyLevel > 0 && !isReturning() && this.totalHits >= getBounceLevel() &&
					entity != null) {
				this.level.playSound(null, entity.blockPosition(), SoundEvents.TRIDENT_RETURN, SoundSource.PLAYERS, 1.0F, 1.0F);
				setReturning(true);
			}
		}
		checkInsideBlocks();
	}

	@Override
	protected void onHit(HitResult result) {
		super.onHit(result);
		int loyaltyLevel = (this.entityData.get(LOYALTY_LEVEL)).byteValue();
		if (loyaltyLevel < 1 && this.totalHits >= getBounceLevel()) {
			drop(getX(), getY(), getZ());
		}
	}


	public int getFlyTick() {
		return flyTick;
	}

	private boolean shouldReturnToThrower() {
		Entity entity = getOwner();
		if (entity != null && entity.isAlive())
			return (!(entity instanceof Player) || !entity.isSpectator() || entity instanceof Hunter);
		return false;
	}

	@Override
	public void playerTouch(Player entityIn) {
		super.playerTouch(entityIn);
		if (this.flyTick >= 10 && entityIn == getOwner()) {
			drop(getOwner().getX(), getOwner().getY(), getOwner().getZ());
		}
	}

	public void drop(double x, double y, double z) {
		if ((getOwner() instanceof Hunter) || (getOwner() instanceof Player && !((Player) getOwner()).isCreative())) {
			this.level.addFreshEntity(new ItemEntity(this.level, x, y, z, getBoomerang().split(1)));
			this.discard();
		} else {
			this.discard();
		}
	}

	@Override
	public void tick() {
		super.tick();

		Vec3 vec3 = this.getDeltaMovement();
		if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
			double d0 = vec3.horizontalDistance();
			this.setYRot((float) (Mth.atan2(vec3.x, vec3.z) * (double) (180F / (float) Math.PI)));
			this.setXRot((float) (Mth.atan2(vec3.y, d0) * (double) (180F / (float) Math.PI)));
			this.yRotO = this.getYRot();
			this.xRotO = this.getXRot();
		}

		this.flyTick++;
		Vec3 vec3d1 = this.position();
		Vec3 vec3d2 = new Vec3(getX() + getDeltaMovement().x, getY() + getDeltaMovement().y, getZ() + getDeltaMovement().z);
		BlockHitResult fluidHitResult = this.level.clip(new ClipContext(vec3d1, vec3d2, ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, this));
		onHitFluid(fluidHitResult);

		int loyaltyLevel = (this.entityData.get(LOYALTY_LEVEL)).byteValue();
		Entity entity = getOwner();
		if (loyaltyLevel > 0 && !isReturning()) {
			if (this.flyTick >= 80 && entity != null) {
				this.level.playSound(null, entity.blockPosition(), SoundEvents.TRIDENT_RETURN, SoundSource.PLAYERS, 1.0F, 1.0F);
				setReturning(true);
			}
			if (this.flyTick < 80) {
				//recover movement if loyalty enchant has
				this.setDeltaMovement(vec3.scale(1.01F));
			}
		}
		if (loyaltyLevel > 0 && entity != null && !shouldReturnToThrower() && isReturning()) {
			drop(getX(), getY(), getZ());
		} else if (loyaltyLevel > 0 && entity != null && isReturning()) {
			this.noPhysics = true;
			Vec3 vec3d3 = new Vec3(entity.getX() - getX(), entity.getEyeY() - getY(), entity.getZ() - getZ());
			double d0 = 0.05D * loyaltyLevel;
			this.setDeltaMovement(getDeltaMovement().scale(0.95D).add(vec3d3.normalize().scale(d0)));
		}
		this.checkInsideBlocks();
	}

	@Override
	protected float getGravity() {
		if (getLoyaltyLevel() > 0 && !this.isReturning()) {
			if (this.isInWater()) {
				return 0.01F;
			}

			return 0.0F;
		}

		return getLoyaltyLevel() > 0 && this.isReturning() ? 0.0F : 0.03F;
	}

	@Override
	public boolean shouldRenderAtSqrDistance(double p_70112_1_) {
		double d0 = this.getBoundingBox().getSize() * 5.0F;
		if (Double.isNaN(d0)) {
			d0 = 1.0D;
		}

		d0 = d0 * 64.0D;
		return p_70112_1_ < d0 * d0;
	}

	@Override
	protected Item getDefaultItem() {
		return this.getBoomerang().getItem();
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(LOYALTY_LEVEL, Byte.valueOf((byte) 0));
		this.entityData.define(PIERCING_LEVEL, Byte.valueOf((byte) 0));
		this.entityData.define(BOUNCE_LEVEL, Byte.valueOf((byte) 0));
		this.entityData.define(RETURNING, Boolean.valueOf(false));
		this.entityData.define(BOOMERANG, ItemStack.EMPTY);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.put("boomerang", getBoomerang().save(new CompoundTag()));
		nbt.putInt("totalHits", this.totalHits);
		nbt.putBoolean("returning", isReturning());
	}


	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		setBoomerang(ItemStack.of(nbt.getCompound("boomerang")));
		this.totalHits = nbt.getInt("totalHits");
		setReturning(nbt.getBoolean("returning"));
		this.entityData.set(LOYALTY_LEVEL, (byte) EnchantmentHelper.getLoyalty(getBoomerang()));
		this.entityData.set(PIERCING_LEVEL, (byte) EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PIERCING, getBoomerang()));
		this.entityData.set(BOUNCE_LEVEL, (byte) EnchantmentHelper.getItemEnchantmentLevel(HunterEnchantments.BOUNCE.get(), getBoomerang()));
	}

	private int getLoyaltyLevel() {
		return (this.entityData.get(LOYALTY_LEVEL)).byteValue();
	}

	private int getBounceLevel() {
		int loyaltyLevel = (this.entityData.get(LOYALTY_LEVEL)).byteValue();
		int bounceLevel = (this.entityData.get(BOUNCE_LEVEL)).byteValue();
		if (loyaltyLevel > 0)
			return 0 + bounceLevel * 8;
		return 8 + bounceLevel * 8;
	}

	public boolean isReturning() {
		return ((Boolean) this.entityData.get(RETURNING)).booleanValue();
	}

	public ItemStack getBoomerang() {
		return (ItemStack) this.entityData.get(BOOMERANG);
	}

	public double getSpeed() {
		return Math.sqrt(getDeltaMovement().x * getDeltaMovement().x + getDeltaMovement().y * getDeltaMovement().y + getDeltaMovement().z * getDeltaMovement().z);
	}

	public double getVelocity() {
		return Math.sqrt(getDeltaMovement().x * getDeltaMovement().x + getDeltaMovement().z * getDeltaMovement().z);
	}

	public int getPiercingLevel() {
		return (this.entityData.get(PIERCING_LEVEL)).byteValue();
	}

	public void setReturning(boolean returning) {
		this.entityData.set(RETURNING, Boolean.valueOf(returning));
	}

	public void setBoomerang(ItemStack stack) {
		this.entityData.set(BOOMERANG, stack);
	}

}

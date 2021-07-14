package baguchan.hunterillager.entity.projectile;

import baguchan.hunterillager.init.HunterEnchantments;
import baguchan.hunterillager.init.HunterEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.List;

public class BoomerangEntity extends ThrowableEntity {
	private static final DataParameter<Byte> LOYALTY_LEVEL = EntityDataManager.defineId(BoomerangEntity.class, DataSerializers.BYTE);

	private static final DataParameter<Byte> PIERCING_LEVEL = EntityDataManager.defineId(BoomerangEntity.class, DataSerializers.BYTE);
	private static final DataParameter<Byte> BOUNCE_LEVEL = EntityDataManager.defineId(BoomerangEntity.class, DataSerializers.BYTE);
	private static final DataParameter<Boolean> RETURNING = EntityDataManager.defineId(BoomerangEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<ItemStack> BOOMERANG = EntityDataManager.defineId(BoomerangEntity.class, DataSerializers.ITEM_STACK);

	private int totalHits;

	private int flyTick;

	public BoomerangEntity(EntityType<? extends BoomerangEntity> entityEntityType, World world) {
		super(entityEntityType, world);
	}

	public BoomerangEntity(EntityType<? extends BoomerangEntity> type, World world, LivingEntity shootingEntity, ItemStack boomerang) {
		super(type, shootingEntity, world);
		setOwner(shootingEntity);
		setBoomerang(boomerang);
		this.entityData.set(LOYALTY_LEVEL, (byte) EnchantmentHelper.getLoyalty(boomerang));
		this.entityData.set(PIERCING_LEVEL, (byte) EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PIERCING, boomerang));
		this.entityData.set(BOUNCE_LEVEL, (byte) EnchantmentHelper.getItemEnchantmentLevel(HunterEnchantments.BOUNCE, boomerang));
		this.totalHits = 0;
	}

	public BoomerangEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
		this(HunterEntityRegistry.BOOMERANG, world);
	}

	public BoomerangEntity(World world, LivingEntity entity, ItemStack boomerang) {
		this(HunterEntityRegistry.BOOMERANG, world, entity, boomerang);
	}


	private void onHitFluid(BlockRayTraceResult result) {
		double velocity = getVelocity();
		double horizontal = getDeltaMovement().y * getDeltaMovement().y;
		if (!this.level.isClientSide &&
				result.getType() == RayTraceResult.Type.BLOCK && result.isInside() &&
				velocity >= 0.6499999761581421D && horizontal < 0.17499999701976776D)
			if (!this.level.getBlockState(result.getBlockPos()).isAir() && this.level.getFluidState(result.getBlockPos()).is(FluidTags.WATER)) {
				setDeltaMovement(getDeltaMovement().x, MathHelper.clamp(getDeltaMovement().y + 0.10000000149011612D, -0.10000000149011612D, 0.30000001192092896D), getDeltaMovement().z);
				this.hasImpulse = true;
			}
	}

	protected void onHitEntity(EntityRayTraceResult result) {
		super.onHitEntity(result);
		boolean returnToOwner = false;
		int loyaltyLevel = (this.entityData.get(LOYALTY_LEVEL)).byteValue();
		int piercingLevel = (this.entityData.get(PIERCING_LEVEL)).byteValue();
		Entity entity = getOwner();
		if (result.getEntity() != getOwner())
			if (!isReturning() || loyaltyLevel <= 0) {
				Entity shooter = getOwner();
				int sharpness = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, getBoomerang());
				result.getEntity().hurt(DamageSource.thrown(this, shooter), (float) (3.0D * Math.sqrt(getDeltaMovement().x * getDeltaMovement().x + getDeltaMovement().y * getDeltaMovement().y * 0.5D + getDeltaMovement().z * getDeltaMovement().z) + Math.min(1, sharpness) + Math.max(0, sharpness - 1) * 0.5D) + 0.5F * piercingLevel);
				if (shooter instanceof LivingEntity)
					getBoomerang().hurtAndBreak(1, (LivingEntity) shooter, p_222182_1_ -> {

					});
				double speed = getSpeed();
				if (piercingLevel < 1 && this.totalHits >= this.getBounceLevel() || this.totalHits >= piercingLevel + this.getBounceLevel() && speed > 0.4000000059604645D) {
					returnToOwner = true;

				} else if (piercingLevel < 1 && this.totalHits < this.getBounceLevel() || this.totalHits < piercingLevel + this.getBounceLevel() && speed <= 0.4000000059604645D) {
					Vector3d motion = getDeltaMovement();
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
				Entity shooter = getOwner();
				this.level.playSound(null, shooter.blockPosition(), SoundEvents.TRIDENT_RETURN, SoundCategory.PLAYERS, 1.0F, 1.0F);
				Vector3d motion = getDeltaMovement();
				double motionX = motion.x;
				double motionY = motion.y;
				double motionZ = motion.z;
				motionX = -motionX;
				motionZ = -motionZ;
				setDeltaMovement(motionX, motionY, motionZ);
				//func_70018_K();
				if (loyaltyLevel > 0 && !isReturning() &&
						entity != null) {
					this.level.playSound(null, entity.blockPosition(), SoundEvents.TRIDENT_RETURN, SoundCategory.PLAYERS, 1.0F, 1.0F);
					setReturning(true);
				}
			} else {
				Vector3d motion = getDeltaMovement();
				double motionX = motion.x;
				double motionY = motion.y;
				double motionZ = motion.z;
				motionX = -motionX;
				motionZ = -motionZ;
				setDeltaMovement(motionX, motionY, motionZ);
				if (loyaltyLevel > 0 && !isReturning() &&
						entity != null) {
					this.level.playSound(null, entity.blockPosition(), SoundEvents.TRIDENT_RETURN, SoundCategory.PLAYERS, 1.0F, 1.0F);
					setReturning(true);
				}
			}
	}

	@Override
	protected void onHitBlock(BlockRayTraceResult result) {
		super.onHitBlock(result);
		BlockPos pos = result.getBlockPos();
		BlockState state = this.level.getBlockState(pos);
		SoundType soundType = state.getSoundType((IWorldReader) this.level, pos, this);
		if (!isReturning())
			this.level.playSound(null, getX(), getY(), getZ(), soundType.getHitSound(), SoundCategory.BLOCKS, soundType.getVolume() * 0.45F, soundType.getPitch());
		this.totalHits++;
		BlockState blockstate = this.level.getBlockState(result.getBlockPos());
		int loyaltyLevel = this.entityData.get(LOYALTY_LEVEL).byteValue();
		int piercingLevel = this.entityData.get(PIERCING_LEVEL).byteValue();
		Entity entity = getOwner();
		if (!isReturning() && !blockstate.getCollisionShape((IBlockReader) this.level, result.getBlockPos()).isEmpty()) {
			Direction face = result.getDirection();
			Vector3d motion = getDeltaMovement();
			double motionX = motion.x;
			double motionY = motion.y;
			double motionZ = motion.z;
			if (face == Direction.EAST) {
				motionX = -motionX;
			} else if (face == Direction.SOUTH) {
				motionZ = -motionZ;
			} else if (face == Direction.WEST) {
				motionX = -motionX;
			} else if (face == Direction.NORTH) {
				motionZ = -motionZ;
			} else if (face == Direction.UP) {
				motionY = -motionY;
			} else if (face == Direction.DOWN) {
				motionY = -motionY;
			}
			setDeltaMovement(motionX, motionY, motionZ);
			if (loyaltyLevel > 0 && !isReturning() && this.totalHits >= getBounceLevel() &&
					entity != null) {
				this.level.playSound(null, entity.blockPosition(), SoundEvents.TRIDENT_RETURN, SoundCategory.PLAYERS, 1.0F, 1.0F);
				setReturning(true);
			}
		}
		checkInsideBlocks();
	}

	protected void onHit(RayTraceResult result) {
		super.onHit(result);
		int loyaltyLevel = (this.entityData.get(LOYALTY_LEVEL)).byteValue();
		if (loyaltyLevel < 1 && this.totalHits >= getBounceLevel() &&
				!this.level.isClientSide())
			drop(getX(), getY(), getZ());
	}

	private boolean shouldReturnToThrower() {
		Entity entity = getOwner();
		if (entity != null && entity.isAlive())
			return (!(entity instanceof net.minecraft.entity.player.ServerPlayerEntity) || !entity.isSpectator());
		return false;
	}

	public void playerTouch(PlayerEntity entityIn) {
		super.playerTouch(entityIn);
		if (!this.level.isClientSide && this.flyTick >= 6 && entityIn == getOwner())
			drop(getOwner().getX(), getOwner().getY(), getOwner().getZ());
	}

	public void push(Entity entityIn) {
		super.push(entityIn);
		if (!this.level.isClientSide && this.flyTick >= 6 && entityIn == getOwner())
			drop(getOwner().getX(), getOwner().getY(), getOwner().getZ());
	}

	public void drop(double x, double y, double z) {
		if (!(getOwner() instanceof PlayerEntity) || (getOwner() instanceof PlayerEntity && !((PlayerEntity) getOwner()).isCreative()))
			this.level.addFreshEntity(new ItemEntity(this.level, x, y, z, getBoomerang().copy()));
		remove();
	}

	public void tick() {
		super.tick();
		this.flyTick++;
		Vector3d vec3d = getDeltaMovement();
		Vector3d vec3d1 = this.position();
		Vector3d vec3d2 = new Vector3d(getX() + getDeltaMovement().x, getY() + getDeltaMovement().y, getZ() + getDeltaMovement().z);
		BlockRayTraceResult fluidRaytraceResult = this.level.clip(new RayTraceContext(vec3d1, vec3d2, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, this));
		onHitFluid(fluidRaytraceResult);
		float f = MathHelper.sqrt(getDeltaMovement().x * getDeltaMovement().x + getDeltaMovement().z * getDeltaMovement().z);
		this.yRot = (float) (MathHelper.atan2(getDeltaMovement().x, getDeltaMovement().z) * 57.2957763671875D);
		for (this.xRot = (float) (MathHelper.atan2(getDeltaMovement().y, f) * 57.2957763671875D); this.xRot - this.xRotO < -180.0F; this.xRotO -= 360.0F)
			;
		while (this.xRot - this.xRotO >= 180.0F)
			this.xRotO += 360.0F;
		while (this.yRot - this.yRotO < -180.0F)
			this.yRotO -= 360.0F;
		while (this.yRot - this.yRotO >= 180.0F)
			this.yRotO += 360.0F;
		this.xRot = this.xRotO + (this.xRot - this.xRotO) * 0.2F;
		this.yRot = this.yRotO + (this.yRot - this.yRotO) * 0.2F;
		int loyaltyLevel = (this.entityData.get(LOYALTY_LEVEL)).byteValue();
		Entity entity = getOwner();
		if (loyaltyLevel > 0 && !isReturning() && this.flyTick >= 100 &&
				entity != null) {
			this.level.playSound(null, entity.blockPosition(), SoundEvents.TRIDENT_RETURN, SoundCategory.PLAYERS, 1.0F, 1.0F);
			setReturning(true);
		}
		if (loyaltyLevel > 0 && entity != null && !shouldReturnToThrower() && isReturning()) {
			if (!this.level.isClientSide)
				drop(getX(), getY(), getZ());
			remove();
		} else if (loyaltyLevel > 0 && entity != null && isReturning()) {
			this.noPhysics = true;
			Vector3d vec3d3 = new Vector3d(entity.getX() - getX(), entity.getEyeY() - getY(), entity.getZ() - getZ());
			if (this.level.isClientSide)
				this.yOld = getY();
			double d0 = 0.05D * loyaltyLevel;
			setDeltaMovement(getDeltaMovement().scale(0.95D).add(vec3d3.normalize().scale(d0)));
		}
		collideWithNearbyEntities();
	}

	protected float getGravity() {
		return isReturning() ? 0.0F : 0.03F;
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
	protected void defineSynchedData() {
		this.entityData.define(LOYALTY_LEVEL, Byte.valueOf((byte) 0));
		this.entityData.define(PIERCING_LEVEL, Byte.valueOf((byte) 0));
		this.entityData.define(BOUNCE_LEVEL, Byte.valueOf((byte) 0));
		this.entityData.define(RETURNING, Boolean.valueOf(false));
		this.entityData.define(BOOMERANG, ItemStack.EMPTY);
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.put("boomerang", getBoomerang().save(new CompoundNBT()));
		nbt.putInt("totalHits", this.totalHits);
		nbt.putBoolean("returning", isReturning());
	}


	@Override
	protected void readAdditionalSaveData(CompoundNBT nbt) {
		super.readAdditionalSaveData(nbt);
		setBoomerang(ItemStack.of(nbt.getCompound("boomerang")));
		this.totalHits = nbt.getInt("totalHits");
		setReturning(nbt.getBoolean("returning"));
		this.entityData.set(LOYALTY_LEVEL, (byte) EnchantmentHelper.getLoyalty(getBoomerang()));
		this.entityData.set(PIERCING_LEVEL, (byte) EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PIERCING, getBoomerang()));
		this.entityData.set(BOUNCE_LEVEL, (byte) EnchantmentHelper.getItemEnchantmentLevel(HunterEnchantments.BOUNCE, getBoomerang()));
	}


	protected void collideWithNearbyEntities() {
		if (isReturning()) {
			List<Entity> list = this.level.getEntities(this, getBoundingBox(), EntityPredicates.pushableBy(this));
			if (!list.isEmpty())
				for (int l = 0; l < list.size(); l++) {
					Entity entity = list.get(l);
					if (entity == getOwner())
						drop(entity.getX(), entity.getY(), entity.getZ());
				}
		}
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


	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}

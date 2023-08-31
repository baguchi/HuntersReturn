package baguchan.hunters_return.entity.projectile;

import baguchan.hunters_return.init.HunterDamageSource;
import baguchan.hunters_return.init.HunterEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class SpinBlade extends Projectile {
    private static final EntityDataAccessor<Byte> PIERCING_LEVEL = SynchedEntityData.defineId(SpinBlade.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Boolean> RETURNING = SynchedEntityData.defineId(SpinBlade.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<ItemStack> ITEMSTACK = SynchedEntityData.defineId(SpinBlade.class, EntityDataSerializers.ITEM_STACK);

    private static final EntityDataAccessor<Boolean> IN_GROUND = SynchedEntityData.defineId(SpinBlade.class, EntityDataSerializers.BOOLEAN);


    public SpinBlade(EntityType<? extends SpinBlade> entityEntityType, Level world) {
        super(entityEntityType, world);
    }

    public SpinBlade(EntityType<? extends SpinBlade> type, Level world, LivingEntity shootingEntity, ItemStack boomerang) {
        super(type, world);
        this.setPos(shootingEntity.getX(), shootingEntity.getEyeY() - 0.1F, shootingEntity.getZ());
        setOwner(shootingEntity);
        setItemStack(boomerang);

        this.entityData.set(PIERCING_LEVEL, (byte) EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PIERCING, boomerang));
    }

    public SpinBlade(Level world, LivingEntity entity, ItemStack boomerang) {
        this(HunterEntityRegistry.SPIN_BLADE.get(), world, entity, boomerang);
    }

    public DamageSource spinBladeAttack(@Nullable Entity p_270857_) {
        return this.damageSources().source(HunterDamageSource.SPIN_BLADE, this, p_270857_);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity shooter = getOwner();
        if (result.getEntity() != shooter) {
            int power = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, getItemStack());
            int damage = (int) ((5.0D + Math.min(1, power) + Math.max(0, power - 1) * 0.5D));

            if (this.isOnFire()) {
                result.getEntity().setSecondsOnFire(5);
            }

            if (damage > 0) {
                if (result.getEntity().hurt(this.spinBladeAttack(shooter), damage)) {
                    if (shooter instanceof LivingEntity) {
                        getItemStack().hurtAndBreak(1, (LivingEntity) shooter, p_222182_1_ -> {
                        });
                    }
                }
            }

        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        BlockPos pos = result.getBlockPos();
        BlockState state = this.level().getBlockState(pos);
        SoundType soundType = state.getSoundType(this.level(), pos, this);

        Entity entity = getOwner();
        Vec3 movement = this.getDeltaMovement();
        if (!isReturning()) {
            if (movement.length() < 0.2F && movement.y < 0) {
                if (!isReturning() &&
                        entity != null) {
                    this.level().playSound(null, entity.blockPosition(), SoundEvents.TRIDENT_RETURN, SoundSource.PLAYERS, 1.0F, 1.0F);
                    setReturning(true);
                }
            } else {
                Vec3i direction = result.getDirection().getNormal();
                switch (result.getDirection()) {
                    case UP, SOUTH, EAST -> direction = direction.multiply(-1);
                    default -> {
                    }
                }
                direction = new Vec3i(direction.getX() == 0 ? 1 : direction.getX(), direction.getY() == 0 ? 1 : direction.getY(), direction.getZ() == 0 ? 1 : direction.getZ());
                this.setDeltaMovement(movement.multiply(new Vec3(direction.getX(), direction.getY(), direction.getZ())).scale(0.91F));
                this.playSound(SoundEvents.WOOD_STEP, 0.5F, 1.0F);
                if (!isReturning()) {
                    this.level().playSound(null, getX(), getY(), getZ(), soundType.getHitSound(), SoundSource.BLOCKS, soundType.getVolume(), soundType.getPitch());
                }
            }
        }
    }

    private boolean shouldReturnToThrower() {
        Entity entity = getOwner();
        if (entity != null && entity.isAlive())
            return (!entity.isSpectator());
        return false;
    }

    private boolean shouldDropToThrower() {
        Entity entity = getOwner();
        if (entity != null && entity.isAlive())
            return !entity.isSpectator() && !(entity instanceof Player) && (this.distanceToSqr(entity) < 3);
        return false;
    }

    @Override
    public void playerTouch(Player entityIn) {
        super.playerTouch(entityIn);
        if (entityIn == getOwner() && this.tickCount > 20) {
            if (!this.level().isClientSide) {
                this.playSound(SoundEvents.ITEM_PICKUP, 0.6F, 1.0F);
                entityIn.take(this, 1);
                this.discard();
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        BlockPos blockpos2 = this.blockPosition();
        BlockState blockstate2 = this.level().getBlockState(blockpos2);
        if (this.isInWaterOrRain() || blockstate2.is(Blocks.POWDER_SNOW) || this.isInFluidType((fluidType, height) -> this.canFluidExtinguish(fluidType))) {
            this.clearFire();
        }
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        boolean flag = false;
        if (hitresult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockpos = ((BlockHitResult) hitresult).getBlockPos();
            BlockState blockstate = this.level().getBlockState(blockpos);
            if (blockstate.is(Blocks.NETHER_PORTAL)) {
                this.handleInsidePortal(blockpos);
                flag = true;
            } else if (blockstate.is(Blocks.END_GATEWAY)) {
                BlockEntity blockentity = this.level().getBlockEntity(blockpos);
                if (blockentity instanceof TheEndGatewayBlockEntity && TheEndGatewayBlockEntity.canEntityTeleport(this)) {
                    TheEndGatewayBlockEntity.teleportEntity(this.level(), blockpos, blockstate, this, (TheEndGatewayBlockEntity) blockentity);
                }

                flag = true;
            }
        }

        if (hitresult.getType() != HitResult.Type.MISS && !flag && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
            this.onHit(hitresult);
        }
        this.updateRotation();

        Vec3 vec3 = this.getDeltaMovement();
        Entity entity = getOwner();
        if (!isReturning()) {
            if (this.tickCount >= 20 && entity != null) {
                setReturning(true);
            }
        }
        if (entity != null && !this.level().isClientSide() && !shouldReturnToThrower() && isReturning()) {
            this.discard();
        } else if (entity != null && isReturning()) {
            this.noPhysics = true;
            Vec3 vec3d3 = new Vec3(entity.getX() - getX(), entity.getEyeY() - getY(), entity.getZ() - getZ());
            double d0 = 0.1D * 3;
            this.setDeltaMovement(getDeltaMovement().scale(0.95D).add(vec3d3.normalize().scale(d0)));
        }

        Vec3 vec33 = this.getDeltaMovement();
        double d2 = this.getX() + vec33.x;
        double d0 = this.getY() + vec33.y;
        double d1 = this.getZ() + vec33.z;
        this.setDeltaMovement(vec33.scale(0.85F));
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.checkInsideBlocks();

        if (!this.level().isClientSide() && this.

                shouldDropToThrower()) {
            this.discard();
        }

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
        this.entityData.define(PIERCING_LEVEL, Byte.valueOf((byte) 0));
        this.entityData.define(RETURNING, Boolean.valueOf(false));
        this.entityData.define(ITEMSTACK, ItemStack.EMPTY);
        this.entityData.define(IN_GROUND, Boolean.valueOf(false));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("returning", isReturning());
    }


    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);

        setReturning(nbt.getBoolean("returning"));
        this.entityData.set(PIERCING_LEVEL, (byte) EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PIERCING, getItemStack()));
    }

    public boolean isReturning() {
        return ((Boolean) this.entityData.get(RETURNING)).booleanValue();
    }

    public int getPiercingLevel() {
        return (this.entityData.get(PIERCING_LEVEL)).byteValue();
    }

    public void setReturning(boolean returning) {
        this.entityData.set(RETURNING, Boolean.valueOf(returning));
    }

    public ItemStack getItemStack() {
        return (ItemStack) this.entityData.get(ITEMSTACK);
    }

    public void setItemStack(ItemStack stack) {
        this.entityData.set(ITEMSTACK, stack);
    }
}

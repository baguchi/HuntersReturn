package baguchi.hunters_return.entity.ai;

import baguchi.hunters_return.entity.Hunter;
import baguchi.hunters_return.item.BoomerangItem;
import baguchi.hunters_return.item.MiniCrossbowItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BowItem;

import java.util.EnumSet;

public class BoomeranAttackGoal extends Goal {
	private final Hunter mob;
	private int attackIntervalMin;
	private final float attackRadiusSqr;
	private int attackTime = -1;
	private int seeTime;

    public BoomeranAttackGoal(Hunter hunter, int attackIntervalMin, float attackRadiusSqr) {
        this.mob = hunter;
		this.attackIntervalMin = attackIntervalMin;
		this.attackRadiusSqr = attackRadiusSqr * attackRadiusSqr;
		this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		LivingEntity entity = mob.getTarget();
		return mob.isHolding((item) -> item.getItem() instanceof BoomerangItem) && !mob.isHolding((item) -> item.getItem() instanceof BowItem || item.getItem() instanceof MiniCrossbowItem) && entity != null && entity.isAlive() && entity.distanceTo(mob) > 6D;
	}

	@Override
	public void start() {
		super.start();
		this.mob.setAggressive(true);
		this.mob.startUsingItem(ProjectileUtil.getWeaponHoldingHand(this.mob, item -> item instanceof BoomerangItem));
	}

	@Override
	public void stop() {
		super.stop();
		this.mob.setAggressive(false);
		this.seeTime = 0;
		this.attackTime = -1;
		this.mob.stopUsingItem();
	}

	public void tick() {
		LivingEntity livingentity = this.mob.getTarget();
		if (livingentity != null) {
			double d0 = this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
			boolean flag = this.mob.getSensing().hasLineOfSight(livingentity);
			boolean flag1 = this.seeTime > 0;
			if (flag != flag1) {
				this.seeTime = 0;
			}

			if (flag) {
				++this.seeTime;
			} else {
				--this.seeTime;
			}

			if (mob.isHolding((item) -> item.getItem() instanceof BoomerangItem)) {
				if (this.attackTime > 0 && this.seeTime >= -60) {
					--this.attackTime;
				} else if (this.attackTime <= 0) {
					if (!flag && this.seeTime < -60) {
						this.attackTime = this.attackIntervalMin;
					} else if (flag && this.seeTime >= 40) {
						this.mob.level().broadcastEntityEvent(this.mob, (byte) 4);
						this.mob.performBoomerangAttack(livingentity);
						this.attackTime = this.attackIntervalMin;
					}
				}
			} else {
				this.attackTime = this.attackIntervalMin;
			}

			if (this.mob.distanceTo(livingentity) > 8) {
				this.mob.getNavigation().moveTo(livingentity, 0.8F);
			} else {
				this.mob.getNavigation().stop();
				this.mob.getMoveControl().strafe(-0.1F, 0);
			}
			this.mob.lookAt(livingentity, 10F, 10F);
		}
	}
}

package baguchi.hunters_return.entity.ai;

import baguchi.hunters_return.entity.Hunter;
import baguchi.hunters_return.item.BoomerangItem;
import baguchi.hunters_return.item.MiniCrossbowItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.BowItem;

public class BoomeranAttackGoal extends Goal {
	private final Hunter mob;
	private int attackIntervalMin;
	private final float attackRadiusSqr;
	private int attackTime = -1;
	private int seeTime;
    private int cannotMoveTime;

    public BoomeranAttackGoal(Hunter hunter, int attackIntervalMin, float attackRadiusSqr) {
        this.mob = hunter;
		this.attackIntervalMin = attackIntervalMin;
		this.attackRadiusSqr = attackRadiusSqr * attackRadiusSqr;
	}

	@Override
	public boolean canUse() {
		LivingEntity entity = mob.getTarget();
		return !mob.isHolding((item) -> item.getItem() instanceof BowItem || item.getItem() instanceof MiniCrossbowItem) && entity != null && entity.isAlive() && entity.distanceToSqr(mob) > 16D;
	}

	@Override
	public void start() {
		super.start();
	}

	@Override
	public void stop() {
		super.stop();
		this.seeTime = 0;
		this.attackTime = -1;
        this.cannotMoveTime = 0;
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

            if (!this.mob.isPathFinding()) {
                ++this.cannotMoveTime;
            } else if (this.cannotMoveTime > 0) {
                --this.cannotMoveTime;
            }

			if (mob.isHolding((item) -> item.getItem() instanceof BoomerangItem)) {
				if (this.attackTime > 0 && this.seeTime >= -60) {
					--this.attackTime;
				} else if (this.attackTime <= 0) {
					if (!flag && this.seeTime < -60) {
						this.attackTime = this.attackIntervalMin;
                    } else if (flag && this.seeTime >= 40 && this.cannotMoveTime >= 80) {
						this.mob.performBoomerangAttack(livingentity);
						this.attackTime = this.attackIntervalMin;
                        this.cannotMoveTime = -80;
					}
				}
			} else {
				this.attackTime = this.attackIntervalMin;
			}
		}
	}
}

package baguchan.hunterillager.entity.ai;

import baguchan.hunterillager.entity.Hunter;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BowItem;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class DodgeGoal extends Goal {
	public Hunter hunter;

	@Nullable
	protected List<? extends Projectile> toAvoid;
	protected final Class<? extends Projectile> dodgeAtType;
	protected final TargetingConditions lookAtContext;

	private int cooldownTime;
	private int dodgeTime;
	private boolean dodgeDirection;

	public DodgeGoal(Hunter hunterEntity, Class<? extends Projectile> dodgeAtType) {
		this.hunter = hunterEntity;
		this.dodgeAtType = dodgeAtType;
		this.lookAtContext = TargetingConditions.forNonCombat().range((double) 10.0F);
		this.setFlags(EnumSet.of(Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		if (!this.hunter.isHolding((item) -> item.getItem() instanceof BowItem)) {
			return false;
		}

		if (--this.cooldownTime < 0) {
			if (this.hunter.getTarget() != null) {
				this.toAvoid = this.hunter.level().getEntitiesOfClass(this.dodgeAtType, this.hunter.getBoundingBox().inflate((double) 10.0F, 5.0D, (double) 10.0F), (p_148124_) -> {
					return (!(p_148124_ instanceof AbstractArrow) || !(((AbstractArrow) p_148124_).inGround) && p_148124_.getOwner() != this.hunter && (p_148124_.getOwner() == null || !this.hunter.isAlliedTo(p_148124_.getOwner())));
				});
				return !toAvoid.isEmpty();
			}
			this.cooldownTime = 10;
		}
		return false;
	}

	@Override
	public boolean canContinueToUse() {
		return this.dodgeTime > 0;
	}

	@Override
	public void start() {
		super.start();
		this.dodgeTime = 20;
		this.dodgeDirection = this.hunter.getRandom().nextBoolean();
	}

	@Override
	public void tick() {
		super.tick();
		--this.dodgeTime;
		if (this.hunter.getMoveControl() instanceof DodgeMoveControl) {
			((DodgeMoveControl) this.hunter.getMoveControl()).dodge(0.0F, this.dodgeDirection ? 0.8F : -0.8F);
		}
	}

	@Override
	public boolean requiresUpdateEveryTick() {
		return true;
	}

	@Override
	public void stop() {
		super.stop();
		this.cooldownTime = 40;
		this.hunter.getMoveControl().strafe(0.0F, 0F);
	}
}

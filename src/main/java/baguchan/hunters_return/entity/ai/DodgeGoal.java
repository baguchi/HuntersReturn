package baguchan.hunters_return.entity.ai;

import baguchan.hunters_return.entity.Hunter;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class DodgeGoal extends Goal {
	public final Hunter hunter;

	@Nullable
	protected List<? extends Projectile> toAvoid;
	protected final Class<? extends Projectile> dodgeAtType;
	protected final TargetingConditions lookAtContext;

	private int cooldownTime;
	private int dodgeTime;
	private boolean dodgeDirection;

	public DodgeGoal(Hunter hunterEntity) {
		this.hunter = hunterEntity;
		this.dodgeAtType = Projectile.class;
		this.lookAtContext = TargetingConditions.forNonCombat().range((double) 10.0F);
		this.setFlags(EnumSet.of(Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		if (--this.cooldownTime < 0) {
			if (this.hunter.getTarget() != null) {
				this.toAvoid = this.hunter.level().getEntitiesOfClass(this.dodgeAtType, this.hunter.getBoundingBox().inflate((double) 10.0F, 5.0D, (double) 10.0F), (p_148124_) -> {
					boolean flag = p_148124_ instanceof AbstractArrow abstractArrow && !abstractArrow.inGround;
					boolean flag2 = p_148124_ instanceof AbstractArrow;

					return (p_148124_.getDeltaMovement().length() >= 0.35F && !flag2 || flag) && (p_148124_.getOwner() == this.hunter.getTarget() && (p_148124_.getOwner() == null || !this.hunter.isAlliedTo(p_148124_.getOwner())));
				});
				if (!toAvoid.isEmpty()) {
					this.cooldownTime = 20 + this.hunter.getRandom().nextInt(2) * 10;
					return true;
				}
			}
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
		this.dodgeTime = 8;
		this.dodgeDirection = this.hunter.getRandom().nextBoolean();
		if (this.dodgeDirection) {
			this.hunter.level().broadcastEntityEvent(this.hunter, (byte) 64);
		} else {
			this.hunter.level().broadcastEntityEvent(this.hunter, (byte) 65);
		}
	}

	@Override
	public void tick() {
		super.tick();
		--this.dodgeTime;
		this.hunter.setXxa(this.dodgeDirection ? 1F : -1F);

		if (this.hunter.getTarget() != null) {
			this.hunter.getLookControl().setLookAt(this.hunter.getTarget(), 30.0F, 30.0F);
		}
	}

	@Override
	public boolean requiresUpdateEveryTick() {
		return true;
	}

	@Override
	public void stop() {
		super.stop();
		this.hunter.setXxa(0F);
		this.cooldownTime = 40 + this.hunter.getRandom().nextInt(2) * 20;
	}
}
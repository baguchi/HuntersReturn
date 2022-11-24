package baguchan.hunterillager.entity.ai;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;

public class DodgeMoveControl extends MoveControl {
	public DodgeMoveControl(Mob p_24983_) {
		super(p_24983_);
	}

	public void dodge(float p_24989_, float p_24990_) {
		this.operation = MoveControl.Operation.STRAFE;
		this.strafeForwards = p_24989_;
		this.strafeRight = p_24990_;
		this.speedModifier = 1.1D;
	}
}

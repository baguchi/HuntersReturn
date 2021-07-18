package baguchan.hunterillager.entity.ai;

import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.state.Property;
import net.minecraft.state.properties.BedPart;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class SleepOnBedGoal extends MoveToBlockGoal {
	private final CreatureEntity creature;

	public SleepOnBedGoal(CreatureEntity creature, double speedIn, int length) {
		super(creature, speedIn, length);
		this.creature = creature;
	}

	public boolean canUse() {
		return this.creature.getTarget() == null && this.creature.level.isNight() && !this.creature.isSleeping() && super.canUse();
	}

	public void tick() {
		super.tick();
		if (isReachedTarget()) {
			this.creature.startSleeping(this.blockPos);
		}
	}

	protected boolean isValidTarget(IWorldReader worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos);
		Block block = blockstate.getBlock();
		return blockstate.hasProperty((Property) BedBlock.PART) && blockstate.is(BlockTags.BEDS) && blockstate.getValue((Property) BedBlock.PART) == BedPart.HEAD;
	}

	protected boolean findNearestBlock() {
		return super.findNearestBlock();
	}

	public double acceptedDistance() {
		return 2.0D;
	}
}
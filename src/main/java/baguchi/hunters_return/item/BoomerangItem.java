package baguchi.hunters_return.item;

import baguchi.hunters_return.entity.projectile.BoomerangEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class BoomerangItem extends Item {
	public BoomerangItem(Properties properties) {
		super(properties);
	}


	@Override
	public InteractionResult useOn(UseOnContext p_41427_) {
		return super.useOn(p_41427_);
	}

	@Override
	public InteractionResult use(Level p_43099_, Player player, InteractionHand hand) {
		player.startUsingItem(hand);
		return InteractionResult.CONSUME;
	}

	public boolean releaseUsing(ItemStack stack, Level world, LivingEntity entity, int timeLeft) {
		int i = this.getUseDuration(stack, entity) - timeLeft;
		if (i >= 10) {
			if (!world.isClientSide) {
				stack.hurtAndBreak(1, entity, LivingEntity.getSlotForHand(entity.getUsedItemHand()));

				int heldTime = getUseDuration(stack, entity) - timeLeft;
				float velocity = 1.5F * BowItem.getPowerForTime(heldTime);
				BoomerangEntity projectile = new BoomerangEntity(world, entity, stack.copy());
				projectile.shootFromRotation((Entity) entity, entity.getXRot(), entity.getYRot(), 0.0F, velocity, 1.0F);
				world.addFreshEntity((Entity) projectile);
				world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
				if (!(entity instanceof Player) || !((Player) entity).isCreative()) {

					stack.shrink(1);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public int getUseDuration(ItemStack p_41454_, LivingEntity p_344979_) {
		return 72000;
	}

	@Override
	public ItemUseAnimation getUseAnimation(ItemStack p_41452_) {
		return ItemUseAnimation.BOW;
	}
}

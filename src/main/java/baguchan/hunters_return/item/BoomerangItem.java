package baguchan.hunters_return.item;

import baguchan.hunters_return.entity.projectile.BoomerangEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class BoomerangItem extends Item {
	public BoomerangItem(Properties properties) {
		super(properties);
	}


	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		player.startUsingItem(hand);
		return new InteractionResultHolder(InteractionResult.SUCCESS, player.getItemInHand(hand));
	}

	public void releaseUsing(ItemStack stack, Level world, LivingEntity entity, int timeLeft) {
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
	}

	@Override
	public int getUseDuration(ItemStack p_41454_, LivingEntity p_344979_) {
		return 72000;
	}

	public UseAnim getUseAnimation(ItemStack p_43417_) {
		return UseAnim.BOW;
	}

	@Override
	public int getEnchantmentValue(ItemStack stack) {
		return 2;
	}
}

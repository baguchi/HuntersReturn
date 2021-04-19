package baguchan.hunterillager.item;

import baguchan.hunterillager.entity.projectile.BoomerangEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class BoomerangItem extends Item {
	public BoomerangItem(Properties properties) {
		super(properties);
	}

	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return (super.canApplyAtEnchantingTable(stack, enchantment) || enchantment == Enchantments.SHARPNESS || enchantment == Enchantments.LOYALTY || enchantment == Enchantments.PIERCING);
	}


	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		player.startUsingItem(hand);
		return new ActionResult(ActionResultType.SUCCESS, player.getItemInHand(hand));
	}

	public void releaseUsing(ItemStack stack, World world, LivingEntity entity, int timeLeft) {
		if (!world.isClientSide) {
			int heldTime = getUseDuration(stack) - timeLeft;
			float velocity = 1.5F * BowItem.getPowerForTime(heldTime);
			BoomerangEntity projectile = new BoomerangEntity(world, entity, stack.copy());
			projectile.shootFromRotation((Entity) entity, entity.xRot, entity.yRot, 0.0F, velocity, 1.0F);
			world.addFreshEntity((Entity) projectile);
			world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.EGG_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);
			if (!(entity instanceof PlayerEntity) || !((PlayerEntity) entity).isCreative())
				stack.shrink(1);
		}
	}

	public int getUseDuration(ItemStack p_77626_1_) {
		return 72000;
	}

	public UseAction getUseAnimation(ItemStack p_77661_1_) {
		return UseAction.BOW;
	}

	public int getItemEnchantability(ItemStack stack) {
		return 1;
	}
}

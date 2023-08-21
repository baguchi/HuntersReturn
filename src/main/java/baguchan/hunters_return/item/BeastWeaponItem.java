package baguchan.hunters_return.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;

import java.util.UUID;

public class BeastWeaponItem extends Item implements Vanishable {
	public static final int THROW_THRESHOLD_TIME = 10;
	public static final float BASE_DAMAGE = 8.0F;
	public static final float SHOOT_POWER = 2.5F;
	protected static final UUID BASE_ATTACK_KNOCKBACK_UUID = UUID.fromString("a152560e-2264-9f1c-cd41-c452cdd3c788");
	protected static final UUID BASE_BLOCK_REACH_UUID = UUID.fromString("ac5cacef-1cf7-e43f-f929-5617aaeab139");
	protected static final UUID BASE_ENTITY_REACH_UUID = UUID.fromString("defdea4e-4ea9-871a-c760-db5fd962a99e");

	private final Multimap<Attribute, AttributeModifier> defaultModifiers;

	public BeastWeaponItem(Item.Properties p_43381_) {
		super(p_43381_);
		ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 10.0D, AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", (double) -2.95F, AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(BASE_ATTACK_KNOCKBACK_UUID, "Tool modifier", (double) 1.2F, AttributeModifier.Operation.ADDITION));
		builder.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(BASE_BLOCK_REACH_UUID, "Tool modifier", (double) 1.0F, AttributeModifier.Operation.ADDITION));
		builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(BASE_ENTITY_REACH_UUID, "Tool modifier", (double) 1.0F, AttributeModifier.Operation.ADDITION));
		this.defaultModifiers = builder.build();
	}

	public boolean canAttackBlock(BlockState p_43409_, Level p_43410_, BlockPos p_43411_, Player p_43412_) {
		return !p_43412_.isCreative();
	}

    @Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return (super.canApplyAtEnchantingTable(stack, enchantment) || enchantment == Enchantments.SHARPNESS || enchantment == Enchantments.KNOCKBACK);
	}

	public boolean hurtEnemy(ItemStack p_43390_, LivingEntity p_43391_, LivingEntity p_43392_) {
		p_43390_.hurtAndBreak(1, p_43392_, (p_43414_) -> {
			p_43414_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
		});
		return true;
	}

	public boolean mineBlock(ItemStack p_43399_, Level p_43400_, BlockState p_43401_, BlockPos p_43402_, LivingEntity p_43403_) {
		if ((double) p_43401_.getDestroySpeed(p_43400_, p_43402_) != 0.0D) {
			p_43399_.hurtAndBreak(2, p_43403_, (p_43385_) -> {
				p_43385_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
			});
		}

		return true;
	}

	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot p_43383_) {
		return p_43383_ == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_43383_);
	}

	public int getEnchantmentValue() {
		return 1;
	}
}

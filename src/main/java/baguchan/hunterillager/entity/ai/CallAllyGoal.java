package baguchan.hunterillager.entity.ai;

import baguchan.hunterillager.entity.Hunter;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public class CallAllyGoal extends Goal {
	public Hunter hunter;

	@Nullable
	protected List<? extends AbstractIllager> toCall;
	protected final TargetingConditions lookAtContext;

	private int cooldownTime;
	private int callTime;

	public CallAllyGoal(Hunter hunterEntity) {
		this.hunter = hunterEntity;
		this.lookAtContext = TargetingConditions.forNonCombat().ignoreLineOfSight().range((double) 30.0F);
		this.setFlags(EnumSet.of(Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		if (!this.hunter.isHolding((item) -> item.getItem() == Items.GOAT_HORN)) {
			return false;
		}

		if (--this.cooldownTime < 0) {
			if (this.hunter.getTarget() != null) {
				this.toCall = this.hunter.level.getEntitiesOfClass(AbstractIllager.class, this.hunter.getBoundingBox().inflate((double) 30.0F, 30.0D, (double) 30.0F), (p_148124_) -> {
					return this.hunter != p_148124_ && this.hunter.isAlliedTo(p_148124_) && p_148124_.getTarget() == null;
				});
				this.cooldownTime = 600;
				return !toCall.isEmpty();
			}
		}
		return false;
	}

	@Override
	public boolean canContinueToUse() {
		return this.callTime > 0;
	}

	@Override
	public void tick() {
		super.tick();
		--this.callTime;
	}

	@Override
	public void start() {
		super.start();
		this.callTime = 20;
		if (this.hunter.getOffhandItem().is(Items.GOAT_HORN)) {
			this.hunter.startUsingItem(InteractionHand.OFF_HAND);
		} else if (this.hunter.getMainHandItem().is(Items.GOAT_HORN)) {
			this.hunter.startUsingItem(InteractionHand.MAIN_HAND);
		}
		if (!this.toCall.isEmpty()) {
			this.toCall.forEach(abstractIllager -> {
				abstractIllager.setTarget(this.hunter.getTarget());
			});
		}
		Optional<? extends Holder<Instrument>> optional = this.getInstrument(this.hunter.getUseItem());
		if (optional.isPresent()) {
			Instrument instrument = optional.get().value();
			SoundEvent soundevent = instrument.soundEvent();
			float f = instrument.range() / 16.0F;
			this.hunter.playSound(soundevent, f, 1.0F);
			this.hunter.gameEvent(GameEvent.INSTRUMENT_PLAY, this.hunter);
		}
	}

	private Optional<? extends Holder<Instrument>> getInstrument(ItemStack p_220135_) {
		CompoundTag compoundtag = p_220135_.getTag();
		if (compoundtag != null) {
			ResourceLocation resourcelocation = ResourceLocation.tryParse(compoundtag.getString("instrument"));
			if (resourcelocation != null) {
				return Registry.INSTRUMENT.getHolder(ResourceKey.create(Registry.INSTRUMENT_REGISTRY, resourcelocation));
			}
		}
		return Optional.empty();
	}

	@Override
	public void stop() {
		super.stop();
		this.hunter.stopUsingItem();
	}
}

package baguchi.hunters_return.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class HunterSounds {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, baguchi.hunters_return.HuntersReturn.MODID);

	public static final Supplier<SoundEvent> HUNTER_ILLAGER_IDLE = createEvent("mob.hunterillager.idle");
	public static final Supplier<SoundEvent> HUNTER_ILLAGER_HURT = createEvent("mob.hunterillager.hurt");
	public static final Supplier<SoundEvent> HUNTER_ILLAGER_DEATH = createEvent("mob.hunterillager.death");
	public static final Supplier<SoundEvent> HUNTER_ILLAGER_CHEER = createEvent("mob.hunterillager.cheer");
	public static final Supplier<SoundEvent> HUNTER_ILLAGER_LAUGH = createEvent("mob.hunterillager.laugh");

	private static Supplier<SoundEvent> createEvent(String sound) {
		ResourceLocation name = ResourceLocation.fromNamespaceAndPath(baguchi.hunters_return.HuntersReturn.MODID, sound);
		return SOUND_EVENTS.register(sound, () -> SoundEvent.createVariableRangeEvent(name));
	}
}
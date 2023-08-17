package baguchan.hunters_return.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class HunterSounds {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, baguchan.hunters_return.HuntersReturn.MODID);

	public static final RegistryObject<SoundEvent> HUNTER_ILLAGER_IDLE = createEvent("mob.hunterillager.idle");
	public static final RegistryObject<SoundEvent> HUNTER_ILLAGER_HURT = createEvent("mob.hunterillager.hurt");
	public static final RegistryObject<SoundEvent> HUNTER_ILLAGER_DEATH = createEvent("mob.hunterillager.death");
	public static final RegistryObject<SoundEvent> HUNTER_ILLAGER_CHEER = createEvent("mob.hunterillager.cheer");
	public static final RegistryObject<SoundEvent> HUNTER_ILLAGER_LAUGH = createEvent("mob.hunterillager.laugh");

	private static RegistryObject<SoundEvent> createEvent(String sound) {
		ResourceLocation name = new ResourceLocation(baguchan.hunters_return.HuntersReturn.MODID, sound);
		return SOUND_EVENTS.register(sound, () -> SoundEvent.createVariableRangeEvent(name));
	}
}
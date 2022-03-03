package baguchan.hunterillager.init;

import baguchan.hunterillager.HunterIllager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HunterIllager.MODID)
public final class HunterSounds {
	public static final SoundEvent HUNTER_ILLAGER_IDLE = createEvent("mob.hunterillager.idle");
	public static final SoundEvent HUNTER_ILLAGER_HURT = createEvent("mob.hunterillager.hurt");
	public static final SoundEvent HUNTER_ILLAGER_DEATH = createEvent("mob.hunterillager.death");
	public static final SoundEvent HUNTER_ILLAGER_CHEER = createEvent("mob.hunterillager.cheer");
	public static final SoundEvent HUNTER_ILLAGER_LAUGH = createEvent("mob.hunterillager.laugh");

	private static SoundEvent createEvent(String sound) {

		ResourceLocation name = new ResourceLocation(HunterIllager.MODID, sound);

		return new SoundEvent(name).setRegistryName(name);
	}

	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> evt) {
		evt.getRegistry().register(HUNTER_ILLAGER_LAUGH);
	}

	private HunterSounds() {

	}
}
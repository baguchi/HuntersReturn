package baguchan.hunters_return.init;

import baguchan.hunters_return.HuntersReturn;
import baguchan.hunters_return.entity.brain.RudeHogSensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HunterSensors {
    public static final DeferredRegister<SensorType<?>> SENSOR_TYPES = DeferredRegister.create(ForgeRegistries.SENSOR_TYPES, HuntersReturn.MODID);

    public static final RegistryObject<SensorType<RudeHogSensor>> RUDEHOG_SENSOR = SENSOR_TYPES.register("rudehog_sensor", () -> new SensorType<>(RudeHogSensor::new));

}

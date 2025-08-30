package baguchan.hunters_return.init;

import baguchan.hunters_return.HuntersReturn;
import baguchan.hunters_return.api.HunterVariant;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class HunterEntityDatas {
    public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_DATAS = DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, HuntersReturn.MODID);


    public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<Holder<HunterVariant>>> HUNTER_VARIANT = ENTITY_DATAS.register("hunter_variant", () -> EntityDataSerializer.forValueType(HunterVariant.STREAM_CODEC));
}

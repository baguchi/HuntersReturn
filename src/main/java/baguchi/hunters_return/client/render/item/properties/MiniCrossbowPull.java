package baguchi.hunters_return.client.render.item.properties;

import baguchi.hunters_return.item.MiniCrossbowItem;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.client.renderer.item.properties.numeric.UseDuration;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;


public class MiniCrossbowPull implements RangeSelectItemModelProperty {
    public static final MapCodec<MiniCrossbowPull> MAP_CODEC = MapCodec.unit(new MiniCrossbowPull());

    @Override
    public float get(ItemStack itemStack, @org.jetbrains.annotations.Nullable ClientLevel clientLevel, @org.jetbrains.annotations.Nullable ItemOwner itemOwner, int i) {
        if (itemOwner == null || itemOwner.asLivingEntity() == null) {
            return 0.0F;
        } else if (CrossbowItem.isCharged(itemStack)) {
            return 0.0F;
        } else {
            int i2 = MiniCrossbowItem.getChargeDuration(itemStack, itemOwner.asLivingEntity());
            return (float) UseDuration.useDuration(itemStack, itemOwner.asLivingEntity()) / (float) i2;
        }
    }

    @Override
    public MapCodec<MiniCrossbowPull> type() {
        return MAP_CODEC;
    }
}

package baguchan.hunters_return.data;

import baguchan.hunters_return.HuntersReturn;
import baguchan.hunters_return.init.HunterEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EnchantmentTagProvider extends EnchantmentTagsProvider {
    public EnchantmentTagProvider(PackOutput p_255941_, CompletableFuture<HolderLookup.Provider> p_256600_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_255941_, p_256600_, HuntersReturn.MODID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(EnchantmentTags.TOOLTIP_ORDER).add(
                HunterEnchantments.CUTTING,
                HunterEnchantments.BOUNCE,
                HunterEnchantments.RETURN
        );
        this.tag(EnchantmentTags.NON_TREASURE)
                .add(
                        HunterEnchantments.CUTTING,
                        HunterEnchantments.BOUNCE,
                        HunterEnchantments.RETURN
                );
    }
}

package baguchi.hunters_return.data;

import baguchi.hunters_return.HuntersReturn;
import baguchi.hunters_return.init.HunterItems;
import baguchi.hunters_return.init.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends ItemTagsProvider {
    public ItemTagGenerator(PackOutput p_255871_, CompletableFuture<HolderLookup.Provider> p_256035_) {
        super(p_255871_, p_256035_, HuntersReturn.MODID);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.CROSSBOW_ENCHANTABLE).add(HunterItems.MINI_CROSSBOW.getKey());
        tag(ModItemTags.BOOMERANG_ENCHANTABLE).add(HunterItems.BOOMERANG.getKey());
        tag(ItemTags.DURABILITY_ENCHANTABLE).add(HunterItems.BOOMERANG.getKey()).add(HunterItems.MINI_CROSSBOW.getKey());
        tag(ItemTags.VANISHING_ENCHANTABLE).add(HunterItems.BOOMERANG.getKey()).add(HunterItems.MINI_CROSSBOW.getKey());
        tag(Tags.Items.TOOLS_CROSSBOW).add(HunterItems.MINI_CROSSBOW.getKey());
        tag(Tags.Items.PIGLIN_USABLE_CROSSBOWS).add(HunterItems.MINI_CROSSBOW.getKey());
        tag(Tags.Items.PILLAGER_USABLE_CROSSBOWS).add(HunterItems.MINI_CROSSBOW.getKey());
    }
}

package baguchan.hunters_return.data;

import baguchan.hunters_return.HuntersReturn;
import baguchan.hunters_return.init.HunterItems;
import baguchan.hunters_return.init.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends ItemTagsProvider {
    public ItemTagGenerator(PackOutput p_255871_, CompletableFuture<HolderLookup.Provider> p_256035_, CompletableFuture<TagsProvider.TagLookup<Block>> p_275322_, @org.jetbrains.annotations.Nullable net.neoforged.neoforge.common.data.ExistingFileHelper existingFileHelper) {
        super(p_255871_, p_256035_, p_275322_, HuntersReturn.MODID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.CROSSBOW_ENCHANTABLE).add(HunterItems.MINI_CROSSBOW.asItem());
        tag(ModItemTags.BOOMERANG_ENCHANTABLE).add(HunterItems.BOOMERANG.asItem());
        tag(ItemTags.DURABILITY_ENCHANTABLE).add(HunterItems.BOOMERANG.asItem()).add(HunterItems.MINI_CROSSBOW.asItem());
        tag(ItemTags.VANISHING_ENCHANTABLE).add(HunterItems.BOOMERANG.asItem()).add(HunterItems.MINI_CROSSBOW.asItem());
        tag(Tags.Items.TOOLS_CROSSBOW).add(HunterItems.MINI_CROSSBOW.asItem());
    }
}

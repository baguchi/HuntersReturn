package baguchi.hunters_return.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {

    public static final TagKey<Item> BOOMERANG_ENCHANTABLE = create("boomerang_enchantable");

    private static TagKey<Item> create(String path) {
        return TagKey.create(Registries.ITEM, baguchi.hunters_return.HuntersReturn.locate(path));
    }
}

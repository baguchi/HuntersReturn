package baguchi.hunters_return.data;

import baguchi.hunters_return.HuntersReturn;
import baguchi.hunters_return.init.HunterEntityRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class EntityTagGenerator extends EntityTypeTagsProvider {
    public EntityTagGenerator(PackOutput p_255941_, CompletableFuture<HolderLookup.Provider> p_256600_) {
        super(p_255941_, p_256600_, HuntersReturn.MODID);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(EntityTypeTags.ILLAGER).add(HunterEntityRegistry.HUNTERILLAGER.getKey());
        this.tag(EntityTypeTags.RAIDERS).add(HunterEntityRegistry.HUNTERILLAGER.getKey());
        this.tag(EntityTypeTags.IMPACT_PROJECTILES).add(HunterEntityRegistry.BOOMERANG.getKey());
        this.tag(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS).add(HunterEntityRegistry.HUNTERILLAGER.getKey());
        this.tag(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(HunterEntityRegistry.HUNTERILLAGER.getKey());
    }
}
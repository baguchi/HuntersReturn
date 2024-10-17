package baguchi.hunters_return.init;

import baguchi.hunters_return.HuntersReturn;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;

public class HunterEnchantments {
    public static final ResourceKey<Enchantment> BOUNCE = key("bounce");
    public static final ResourceKey<Enchantment> RETURN = key("return");
    public static final ResourceKey<Enchantment> CUTTING = key("cutting");

    private static ResourceKey<Enchantment> key(String p_345314_) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(HuntersReturn.MODID, p_345314_));
    }

    public static void bootstrap(BootstrapContext<Enchantment> p_345935_) {
        HolderGetter<DamageType> holdergetter = p_345935_.lookup(Registries.DAMAGE_TYPE);
        HolderGetter<Enchantment> holdergetter1 = p_345935_.lookup(Registries.ENCHANTMENT);
        HolderGetter<Item> holdergetter2 = p_345935_.lookup(Registries.ITEM);
        HolderGetter<Block> holdergetter3 = p_345935_.lookup(Registries.BLOCK);
        register(
                p_345935_,
                BOUNCE,
                Enchantment.enchantment(
                        Enchantment.definition(
                                holdergetter2.getOrThrow(ModItemTags.BOOMERANG_ENCHANTABLE),
                                5,
                                3, Enchantment.dynamicCost(1, 10), Enchantment.dynamicCost(51, 10), 1,
                                EquipmentSlotGroup.MAINHAND
                        )
                )
        );
        register(
                p_345935_,
                RETURN,
                Enchantment.enchantment(
                        Enchantment.definition(
                                holdergetter2.getOrThrow(ModItemTags.BOOMERANG_ENCHANTABLE),
                                5,
                                3, Enchantment.dynamicCost(5, 10), Enchantment.dynamicCost(30, 10), 1,
                                EquipmentSlotGroup.MAINHAND
                        )
                )
        );
        register(
                p_345935_,
                CUTTING,
                Enchantment.enchantment(
                                Enchantment.definition(
                                        holdergetter2.getOrThrow(ModItemTags.BOOMERANG_ENCHANTABLE),
                                        10,
                                        5,
                                        Enchantment.dynamicCost(1, 10),
                                        Enchantment.dynamicCost(16, 10),
                                        1,
                                        EquipmentSlotGroup.MAINHAND
                                )
                        )
                        .withEffect(
                                EnchantmentEffectComponents.DAMAGE,
                                new AddValue(LevelBasedValue.perLevel(0.5F)),
                                LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.DIRECT_ATTACKER, EntityPredicate.Builder.entity().of(p_345935_.lookup(Registries.ENTITY_TYPE), HunterEntityRegistry.BOOMERANG.get()).build()
                                )
                        )
        );
    }

    private static void register(BootstrapContext<Enchantment> p_346019_, ResourceKey<Enchantment> p_345703_, Enchantment.Builder p_345607_) {
        p_346019_.register(p_345703_, p_345607_.build(p_345703_.location()));
    }


}

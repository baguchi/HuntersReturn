package baguchan.hunters_return.init;

import baguchan.hunters_return.item.BeastWeaponItem;
import baguchan.hunters_return.item.BoomerangItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = baguchan.hunters_return.HuntersReturn.MODID, bus = EventBusSubscriber.Bus.MOD)
public class HunterItems {
	public static final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, baguchan.hunters_return.HuntersReturn.MODID);

	public static final RegistryObject<Item> SPAWNEGG_HUNTER = ITEM_REGISTRY.register("spawnegg_hunter", () -> new ForgeSpawnEggItem(HunterEntityRegistry.HUNTERILLAGER, 9804699, 5777447, (new Item.Properties())));
	public static final RegistryObject<Item> SPAWNEGG_RUDEHOG = ITEM_REGISTRY.register("spawnegg_rudehog", () -> new ForgeSpawnEggItem(HunterEntityRegistry.RUDEHOG, 0x85424C, 0x361D12, (new Item.Properties())));

	public static final RegistryObject<Item> BOOMERANG = ITEM_REGISTRY.register("boomerang", () -> new BoomerangItem((new Item.Properties()).durability(384)));
	public static final RegistryObject<Item> BEAST_CUDGEL = ITEM_REGISTRY.register("beast_cudgel", () -> new BeastWeaponItem((new Item.Properties()).durability(520).rarity(Rarity.UNCOMMON)));

}

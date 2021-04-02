package cloud.lemonslice.barter.common.item;

import cloud.lemonslice.barter.registry.RegistryModule;
import cloud.lemonslice.silveroak.common.item.NormalItem;
import net.minecraft.item.Item;

import static cloud.lemonslice.barter.Barter.ITEM_GROUP;

public final class ItemsRegistry extends RegistryModule
{
    public static final Item WOODEN_KEY = new KeyItem("wooden_key", new Item.Properties().maxDamage(16));
    public static final Item STONE_KEY = new KeyItem("stone_key", new Item.Properties().maxDamage(64));
    public static final Item GOLDEN_KEY = new KeyItem("golden_key", new Item.Properties().maxDamage(256));
    public static final Item STAFF_KEY = new StaffKeyItem("iron_key", new Item.Properties().maxDamage(256));
    public static final Item TRADE_STATION_ADMIN_KEY = new NormalItem("admin_key", ITEM_GROUP);
}

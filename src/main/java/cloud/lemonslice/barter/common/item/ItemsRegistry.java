package cloud.lemonslice.barter.common.item;

import cloud.lemonslice.barter.registry.RegistryModule;
import cloud.lemonslice.silveroak.common.item.NormalItem;
import net.minecraft.item.Item;

import static cloud.lemonslice.barter.Barter.ITEM_GROUP;

public final class ItemsRegistry extends RegistryModule
{
    public static final Item WOODEN_TRADE_STATION_SHOPKEEPER_KEY = new KeyItem("wooden_trade_station_shopkeeper_key", new Item.Properties().maxDamage(16));
    public static final Item STONE_TRADE_STATION_SHOPKEEPER_KEY = new KeyItem("stone_trade_station_shopkeeper_key", new Item.Properties().maxDamage(64));
    public static final Item TRADE_STATION_SHOPKEEPER_KEY = new KeyItem("trade_station_shopkeeper_key", new Item.Properties().maxDamage(256));
    public static final Item TRADE_STATION_STAFF_KEY = new StaffKeyItem("trade_station_staff_key", new Item.Properties().maxDamage(256));
    public static final Item TRADE_STATION_ADMIN_KEY = new NormalItem("trade_station_admin_key", ITEM_GROUP);
}

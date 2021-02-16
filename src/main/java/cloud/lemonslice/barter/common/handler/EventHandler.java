package cloud.lemonslice.barter.common.handler;

import cloud.lemonslice.barter.common.block.NormalTradeStationBlock;
import cloud.lemonslice.barter.common.item.ItemsRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public final class EventHandler
{
    @SubscribeEvent
    public static void onBlockRightClick(PlayerInteractEvent.RightClickBlock event)
    {
        if (event.getWorld().getBlockState(event.getPos()).getBlock() instanceof NormalTradeStationBlock)
        {
            event.setUseBlock(Event.Result.ALLOW);
        }
    }

    @SubscribeEvent
    public static void onBlockLeftClick(PlayerInteractEvent.LeftClickBlock event)
    {
        BlockState state = event.getWorld().getBlockState(event.getPos());
        if (state.getBlock() instanceof NormalTradeStationBlock)
        {
            ItemStack held = event.getPlayer().getHeldItem(event.getHand());
            if (held.getItem() == ItemsRegistry.TRADE_STATION_ADMIN_KEY)
            {
                event.getWorld().setBlockState(event.getPos(), Blocks.AIR.getDefaultState());
            }
        }
    }
}

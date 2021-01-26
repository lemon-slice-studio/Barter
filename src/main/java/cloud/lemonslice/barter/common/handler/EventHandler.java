package cloud.lemonslice.barter.common.handler;

import cloud.lemonslice.barter.common.block.NormalTradeStationBlock;
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
}

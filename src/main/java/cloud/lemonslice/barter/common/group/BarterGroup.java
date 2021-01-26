package cloud.lemonslice.barter.common.group;

import cloud.lemonslice.barter.common.block.BlocksRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import static cloud.lemonslice.barter.Barter.MODID;

public class BarterGroup extends ItemGroup
{
    public BarterGroup()
    {
        super(MODID + ".barter");
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(BlocksRegistry.WHITE_TRADE_STATION_ITEM);
    }
}

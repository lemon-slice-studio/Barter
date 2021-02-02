package cloud.lemonslice.barter.common.handler;

import net.minecraft.item.ItemStack;
import net.minecraft.item.SkullItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.tileentity.TileEntity;

public class ItemStackHelper
{
    public static ItemStack storeTEInStack(ItemStack stack, TileEntity te)
    {
        CompoundNBT nbt = te.write(new CompoundNBT());
        if (stack.getItem() instanceof SkullItem && nbt.contains("SkullOwner"))
        {
            CompoundNBT nbt2 = nbt.getCompound("SkullOwner");
            stack.getOrCreateTag().put("SkullOwner", nbt2);
            return stack;
        }
        else
        {
            stack.setTagInfo("BlockEntityTag", nbt);
            CompoundNBT nbt1 = new CompoundNBT();
            ListNBT listnbt = new ListNBT();
            listnbt.add(StringNBT.valueOf("\"(+NBT)\""));
            nbt1.put("Lore", listnbt);
            stack.setTagInfo("display", nbt1);
            return stack;
        }
    }
}

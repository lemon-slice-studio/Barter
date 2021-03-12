package cloud.lemonslice.barter.common.item;

import cloud.lemonslice.barter.common.tileentity.TradeStationBlockTileEntity;
import cloud.lemonslice.silveroak.common.item.NormalItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static cloud.lemonslice.barter.Barter.ITEM_GROUP;

public class KeyItem extends NormalItem
{
    public KeyItem(String name)
    {
        super(name, ITEM_GROUP);
    }

    public KeyItem(String name, Properties properties)
    {
        super(name, properties.group(ITEM_GROUP));
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn)
    {
        super.onCreated(stack, worldIn, playerIn);
        stack.getOrCreateTag().putString("Owner", playerIn.getUniqueID().toString());
        stack.getOrCreateTag().putString("OwnerName", playerIn.getName().getString());
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        CompoundNBT nbt = stack.getOrCreateTag();
        if (nbt.get("Owner") == null)
        {
            nbt.putString("Owner", entityIn.getUniqueID().toString());
            nbt.putString("OwnerName", entityIn.getName().getString());
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        CompoundNBT nbt = stack.getOrCreateTag();
        if (!nbt.getString("OwnerName").isEmpty())
        {
            tooltip.add(new TranslationTextComponent("tooltip.barter.key.owner", nbt.getString("OwnerName")).mergeStyle(TextFormatting.GRAY));
        }
    }

    public static boolean checkKey(ItemStack held, PlayerEntity player, TradeStationBlockTileEntity te)
    {
        String owner = held.getOrCreateTag().getString("Owner");
        String staff = held.getOrCreateTag().getString("Staff");
        return ((owner.equals(player.getUniqueID().toString()) || staff.equals(player.getUniqueID().toString())) && te.checkOwner(owner));
    }
}

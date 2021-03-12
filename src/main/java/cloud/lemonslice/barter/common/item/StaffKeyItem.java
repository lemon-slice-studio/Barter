package cloud.lemonslice.barter.common.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static cloud.lemonslice.barter.Barter.ITEM_GROUP;

public class StaffKeyItem extends KeyItem
{
    public StaffKeyItem(String name)
    {
        super(name);
    }

    public StaffKeyItem(String name, Properties properties)
    {
        super(name, properties.group(ITEM_GROUP));
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
        CompoundNBT nbt = stack.getOrCreateTag();
        if (!nbt.get("Owner").getString().equals(entityIn.getUniqueID().toString()) && nbt.get("Staff") == null)
        {
            nbt.putString("Staff", entityIn.getUniqueID().toString());
            nbt.putString("StaffName", entityIn.getName().getString());
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        CompoundNBT nbt = stack.getOrCreateTag();
        if (!nbt.getString("StaffName").isEmpty())
        {
            tooltip.add(new TranslationTextComponent("tooltip.barter.key.staff", nbt.getString("StaffName")).mergeStyle(TextFormatting.GRAY));
        }
    }
}

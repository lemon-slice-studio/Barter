package cloud.lemonslice.barter.common.tileentity;

import cloud.lemonslice.barter.common.container.TradeStationPurchaseContainer;
import cloud.lemonslice.barter.common.container.TradeStationSaleContainer;
import cloud.lemonslice.barter.common.item.ItemsRegistry;
import cloud.lemonslice.barter.common.item.KeyItem;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.List;

import static cloud.lemonslice.barter.common.tileentity.TileEntityTypesRegistry.NORMAL_TRADE_STATION;

public class TradeStationBlockTileEntity extends NormalContainerTileEntity
{
    private final LazyOptional<ItemStackHandler> containerInventory = LazyOptional.of(() -> this.createContainerItemHandler(19));
    private final LazyOptional<ItemStackHandler> purchaseInventory = LazyOptional.of(() -> this.createItemHandler(18));
    private final ItemStackHandler inputInventory = this.createItemHandler(4);
    private final List<Pair<Ingredient, Integer>> inputIngredients = Lists.newArrayList();

    private String uuid = "";
    private String input = "";
    private int check = 0;
    private boolean isSpecialMode = false;

    public TradeStationBlockTileEntity()
    {
        super(NORMAL_TRADE_STATION);
    }

    @Override
    public void read(BlockState state, CompoundNBT tag)
    {
        super.read(state, tag);
        this.containerInventory.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(tag.getCompound("Container")));
        this.purchaseInventory.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(tag.getCompound("Purchase")));
        ((INBTSerializable<CompoundNBT>) inputInventory).deserializeNBT(tag.getCompound("InputContainer"));
        setInput(tag.getString("Input"));
        uuid = tag.getString("UUID");
        isSpecialMode = tag.getBoolean("SpecialMode");
    }

    @Override
    public CompoundNBT write(CompoundNBT tag)
    {
        containerInventory.ifPresent(h -> tag.put("Container", ((INBTSerializable<CompoundNBT>) h).serializeNBT()));
        purchaseInventory.ifPresent(h -> tag.put("Purchase", ((INBTSerializable<CompoundNBT>) h).serializeNBT()));
        tag.put("InputContainer", ((INBTSerializable<CompoundNBT>) inputInventory).serializeNBT());
        tag.putString("Input", input);
        tag.putString("UUID", uuid);
        tag.putBoolean("SpecialMode", isSpecialMode);
        return super.write(tag);
    }

    public LazyOptional<ItemStackHandler> getContainerInventory()
    {
        return containerInventory;
    }

    private ItemStackHandler createItemHandler(int size)
    {
        return new ItemStackHandler(size)
        {
            @Override
            protected void onContentsChanged(int slot)
            {
                TradeStationBlockTileEntity.this.refresh();
                TradeStationBlockTileEntity.this.markDirty();
                super.onContentsChanged(slot);
            }
        };
    }

    private ItemStackHandler createContainerItemHandler(int size)
    {
        return new ItemStackHandler(size)
        {
            @Override
            protected void onContentsChanged(int slot)
            {
                TradeStationBlockTileEntity.this.refresh();
                TradeStationBlockTileEntity.this.markDirty();
                if (slot == 0)
                {
                    if (this.getStackInSlot(0).getMaxStackSize() == 1 && this.getStackInSlot(0).hasTag())
                    {
                        TradeStationBlockTileEntity.this.isSpecialMode = true;
                    }
                    else TradeStationBlockTileEntity.this.isSpecialMode = false;
                }
                super.onContentsChanged(slot);
            }
        };
    }

    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity)
    {
        ItemStack held = playerEntity.getHeldItemMainhand();
        if (held.getItem() == ItemsRegistry.TRADE_STATION_ADMIN_KEY || held.getItem() instanceof KeyItem && checkOwner(held.getOrCreateTag().getString("Owner")))
        {
            return new TradeStationSaleContainer(i, playerInventory, pos, world);
        }
        else
        {
            return new TradeStationPurchaseContainer(i, playerInventory, pos, world);
        }
    }

    public boolean checkOwner(String owner)
    {
        if (uuid.isEmpty())
        {
            uuid = owner;
            this.markDirty();
            return true;
        }
        else return uuid.equals(owner);
    }

    public String getInput()
    {
        return input;
    }

    public void setInput(String input)
    {
        this.input = input;
        inputIngredients.clear();
        try
        {
            JsonObject json = getJsonFromString(input);
            for (int i = 1; i <= 4; i++)
            {
                JsonElement element = getInputElement(json, i);
                if (element == null)
                {
                    continue;
                }
                int count = 1;
                if (element.isJsonObject() && ((JsonObject) element).get("count") != null)
                {
                    count = ((JsonObject) element).get("count").getAsInt();
                    if (count <= 0)
                    {
                        count = 1;
                    }
                }
                inputIngredients.add(new Pair<>(CraftingHelper.getIngredient(element), count));
            }
            check = 1;
        }
        catch (JsonSyntaxException | UnsupportedOperationException e)
        {
            check = -1;
            inputIngredients.clear();
        }
    }

    @Nullable
    public JsonElement getInputElement(JsonObject obj, int index)
    {
        if (obj == null || obj.isJsonNull())
        {
            throw new JsonSyntaxException("Json cannot be null");
        }
        return obj.get("input" + index);
    }

    public JsonObject getJsonFromString(String text)
    {
        Gson g = new Gson();
        return g.fromJson(text, JsonObject.class);
    }

    public void setCheck(int check)
    {
        this.check = check;
    }

    public int getCheck()
    {
        return check;
    }

    public List<Pair<Ingredient, Integer>> getInputIngredients()
    {
        return inputIngredients;
    }

    public void specialPurchase(PlayerEntity playerEntity, int index)
    {
        containerInventory.ifPresent(h ->
        {
            int purchaseCount = h.getStackInSlot(index).getCount();
            if (purchaseCount == 0)
            {
                return;
            }
            if (!inputIngredients.isEmpty())
            {
                for (int i = 0; i < inputIngredients.size(); i++)
                {
                    Ingredient ingredient = inputIngredients.get(i).getFirst();
                    int count = inputIngredients.get(i).getSecond();
                    ItemStack input = this.inputInventory.getStackInSlot(i);
                    if (ingredient.test(input))
                    {
                        purchaseCount = Math.min(purchaseCount, input.getCount() / count);
                    }
                    else
                    {
                        return;
                    }
                }
            }
            else
            {
                return;
            }

            purchaseCount = canPurchaseMuch(purchaseCount);

            if (purchaseCount == 0)
            {
                return;
            }

            for (int i = 0; i < inputIngredients.size(); i++)
            {
                ItemStack get = inputInventory.extractItem(i, purchaseCount, false);
                purchaseInventory.ifPresent(inv -> ItemHandlerHelper.insertItemStacked(inv, get, false));
            }
            ItemHandlerHelper.giveItemToPlayer(playerEntity, h.getStackInSlot(index));

            // To translate other goods to the showing slot.
            if (index == 0)
            {
                for (int i = 1; i < 19; i++)
                {
                    if (h.getStackInSlot(0).isItemEqual(h.getStackInSlot(i)))
                    {
                        h.setStackInSlot(0, h.getStackInSlot(i));
                        h.setStackInSlot(i, ItemStack.EMPTY);
                        return;
                    }
                }
            }
            h.setStackInSlot(index, ItemStack.EMPTY);
        });
    }

    public void purchase(PlayerEntity playerEntity)
    {
        containerInventory.ifPresent(h ->
        {
            if (h.getStackInSlot(0).getCount() == 0)
            {
                return;
            }
            int purchaseCount = getStockCount() / h.getStackInSlot(0).getCount();
            if (purchaseCount == 0)
            {
                return;
            }
            if (!inputIngredients.isEmpty())
            {
                for (int i = 0; i < inputIngredients.size(); i++)
                {
                    Ingredient ingredient = inputIngredients.get(i).getFirst();
                    int count = inputIngredients.get(i).getSecond();
                    ItemStack input = this.inputInventory.getStackInSlot(i);
                    if (ingredient.test(input))
                    {
                        purchaseCount = Math.min(purchaseCount, input.getCount() / count);
                    }
                    else
                    {
                        return;
                    }
                }
            }
            else
            {
                return;
            }

            purchaseCount = canPurchaseMuch(purchaseCount);

            if (purchaseCount == 0)
            {
                return;
            }

            for (int i = 0; i < inputIngredients.size(); i++)
            {
                ItemStack get = inputInventory.extractItem(i, purchaseCount * inputIngredients.get(i).getSecond(), false);
                purchaseInventory.ifPresent(inv -> ItemHandlerHelper.insertItemStacked(inv, get, false));
            }
            purchaseItem(playerEntity, purchaseCount);
        });
    }

    // To judge how much the chest can take in.
    public int canPurchaseMuch(int purchase)
    {
        return purchaseInventory.map(h ->
        {
            int accept = purchase;
            for (int i = 0; i < inputIngredients.size(); i++)
            {
                for (int j = accept; j > 0; j--)
                {
                    ItemStack get = inputInventory.getStackInSlot(i).copy();
                    get.setCount(accept * inputIngredients.get(i).getSecond());
                    if (!ItemHandlerHelper.insertItemStacked(h, get, true).isEmpty())
                    {
                        accept--;
                    }
                    else break;
                }
            }
            return accept;
        }).orElse(0);
    }

    public void purchaseItem(PlayerEntity playerEntity, int purchaseCount)
    {
        containerInventory.ifPresent(h ->
        {
            if (hasExtraEnough())
            {
                int p = purchaseCount * h.getStackInSlot(0).getCount();
                for (int i = 1; i < 19; i++)
                {
                    ItemStack itemStack = h.getStackInSlot(i).copy();
                    if (!h.getStackInSlot(0).isItemEqual(itemStack))
                    {
                        continue;
                    }
                    int count = itemStack.getCount();
                    if (count < p)
                    {
                        ItemHandlerHelper.giveItemToPlayer(playerEntity, itemStack);
                        h.setStackInSlot(i, ItemStack.EMPTY);
                        p -= count;
                    }
                    else if (count == p)
                    {
                        ItemHandlerHelper.giveItemToPlayer(playerEntity, itemStack);
                        h.setStackInSlot(i, ItemStack.EMPTY);
                        this.refresh();
                        return;
                    }
                    else
                    {
                        itemStack.setCount(p);
                        ItemHandlerHelper.giveItemToPlayer(playerEntity, itemStack);
                        h.extractItem(i, p, false);
                        this.refresh();
                        return;
                    }
                }
            }
        });
    }

    public boolean hasEnough()
    {
        return containerInventory.map(h ->
        {
            ItemStack price = h.getStackInSlot(0);
            if (price.isEmpty())
            {
                return false;
            }
            else return true;
        }).orElse(false);
    }

    public boolean hasExtraEnough()
    {
        return containerInventory.map(h ->
        {
            ItemStack price = h.getStackInSlot(0);
            if (price.isEmpty())
            {
                return false;
            }

            int count = price.getCount();

            for (int i = 1; i < h.getSlots(); i++)
            {
                if (ItemStack.areItemsEqual(price, h.getStackInSlot(i)))
                {
                    count -= h.getStackInSlot(i).getCount();
                }
                if (count <= 0)
                {
                    return true;
                }
            }
            return false;

        }).orElse(false);
    }

    public int getStockCount()
    {
        return containerInventory.map(h ->
        {
            int count = 0;
            ItemStack price = h.getStackInSlot(0);
            if (price.isEmpty())
            {
                return 0;
            }
            else if (isSpecialMode)
            {
                count += price.getCount();
            }

            for (int i = 1; i < h.getSlots(); i++)
            {
                if (ItemStack.areItemsEqual(price, h.getStackInSlot(i)))
                {
                    count += h.getStackInSlot(i).getCount();
                }
            }
            return count;
        }).orElse(0);
    }

    public boolean isSpecialMode()
    {
        return isSpecialMode;
    }

    public ItemStackHandler getInputInventory()
    {
        return inputInventory;
    }

    public LazyOptional<ItemStackHandler> getPurchaseInventory()
    {
        return purchaseInventory;
    }
}

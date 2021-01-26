package cloud.lemonslice.barter.client.gui;

import cloud.lemonslice.barter.Barter;
import cloud.lemonslice.barter.client.widget.IconButton;
import cloud.lemonslice.barter.common.container.TradeStationPurchaseContainer;
import cloud.lemonslice.barter.network.SimpleNetworkHandler;
import cloud.lemonslice.barter.network.client.TradeStationPurchaseMessage;
import cloud.lemonslice.barter.network.client.TradeStationSpecialPurchaseMessage;
import cloud.lemonslice.silveroak.client.texture.TexturePos;
import cloud.lemonslice.silveroak.helper.GuiHelper;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

import static cloud.lemonslice.barter.Barter.MODID;

public class TradeStationPurchaseGuiContainer extends ContainerScreen<TradeStationPurchaseContainer>
{
    private static final String TEXTURE_PATH = "textures/gui/trade_purchase_gui.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(MODID, TEXTURE_PATH);
    private TradeStationPurchaseContainer container;
    private int indexForShow = 0;
    private int indexToBuy = 0;
    private IconButton buttonCheck;
    private IconButton buttonUp;
    private IconButton buttonDown;

    public TradeStationPurchaseGuiContainer(TradeStationPurchaseContainer container, PlayerInventory inv, ITextComponent name)
    {
        super(container, inv, name);
        this.container = container;
        this.ySize = 148;
    }

    protected void initWidgets()
    {
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;

        this.buttonCheck = new IconButton(i + 119, j + 36, 22, 22, new TranslationTextComponent("container.barter.trade_station.purchase_check"), button -> purchase(), this::buttonCheckTooltip);
        this.children.add(this.buttonCheck);

        this.buttonUp = new IconButton(i + 144, j + 6, 10, 10, new TranslationTextComponent("container.barter.trade_station.choose_up"), button -> chooseNext(), this::buttonUpTooltip);
        this.children.add(this.buttonUp);
        this.buttonDown = new IconButton(i + 144, j + 18, 10, 10, new TranslationTextComponent("container.barter.trade_station.choose_down"), button -> choosePrevious(), this::buttonDownTooltip);
        this.children.add(this.buttonDown);
    }

    @Override
    protected void init()
    {
        super.init();
        this.initWidgets();
    }

    @Override
    public void tick()
    {
        super.tick();
        tickShowing();
        if (!getContainer().getTileEntity().isSpecialMode())
        {
            this.buttonUp.active = false;
            this.buttonDown.active = false;
        }
        else
        {
            this.buttonUp.active = true;
            this.buttonDown.active = true;
        }
    }

    private void choosePrevious()
    {
        container.getTileEntity().getContainerInventory().ifPresent(h ->
        {
            indexToBuy = indexToBuy + 18;
            indexToBuy %= 19;
            while (h.getStackInSlot(indexToBuy).isEmpty() || h.getStackInSlot(indexToBuy).getItem() != h.getStackInSlot(0).getItem())
            {
                indexToBuy = indexToBuy + 18;
                indexToBuy %= 19;
            }
        });
    }

    private void chooseNext()
    {
        container.getTileEntity().getContainerInventory().ifPresent(h ->
        {
            indexToBuy++;
            indexToBuy %= 19;
            while (h.getStackInSlot(indexToBuy).isEmpty() || h.getStackInSlot(indexToBuy).getItem() != h.getStackInSlot(0).getItem())
            {
                indexToBuy++;
                indexToBuy %= 19;
            }
        });
    }

    private void tickShowing()
    {
        List<Pair<Ingredient, Integer>> list = container.getTileEntity().getInputIngredients();
        if (Barter.PROXY.getClientWorld().getGameTime() % 20 == 0 || (list.size() > 0 && container.showing.getStackInSlot(0).isEmpty()))
        {
            for (int i = 0; i < list.size(); i++)
            {
                Pair<Ingredient, Integer> pair = list.get(i);
                ItemStack forShow = pair.getFirst().getMatchingStacks()[indexForShow % pair.getFirst().getMatchingStacks().length].copy();
                forShow.setCount(pair.getSecond());
                container.showing.setStackInSlot(i, forShow);
            }
            if (container.getTileEntity().isSpecialMode())
                container.getTileEntity().getContainerInventory().ifPresent(h -> container.showing.setStackInSlot(4, h.getStackInSlot(indexToBuy)));
            else
                container.getTileEntity().getContainerInventory().ifPresent(h -> container.showing.setStackInSlot(4, h.getStackInSlot(0)));
            indexForShow++;
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    public void buttonCheckTooltip(Button button, MatrixStack matrixStack, int mouseX, int mouseY)
    {
        if (button.isHovered())
        {
            container.refreshRemain();
            int canPurchase = container.getTileEntity().canPurchaseMuch(container.getPurchaseCount());
            if (canPurchase != container.getPurchaseCount())
            {
                GuiHelper.drawTooltip(this, matrixStack, mouseX, mouseY, (this.width - this.xSize) / 2 + 119, (this.height - this.ySize) / 2 + 36, 22, 22, Lists.newArrayList(button.getMessage(), new TranslationTextComponent("container.barter.trade_station.purchase_count", canPurchase), new TranslationTextComponent("container.barter.trade_station.remaining_count", container.getRemainCount()), new TranslationTextComponent("container.barter.trade_station.storage_full").mergeStyle(TextFormatting.RED)));
                return;
            }
            if (container.getTileEntity().isSpecialMode() && container.getTileEntity().hasEnough() || container.getTileEntity().hasExtraEnough())
            {
                GuiHelper.drawTooltip(this, matrixStack, mouseX, mouseY, (this.width - this.xSize) / 2 + 119, (this.height - this.ySize) / 2 + 36, 22, 22, Lists.newArrayList(button.getMessage(), new TranslationTextComponent("container.barter.trade_station.purchase_count", container.getPurchaseCount()), new TranslationTextComponent("container.barter.trade_station.remaining_count", container.getRemainCount())));
            }
            else
                GuiHelper.drawTooltip(this, matrixStack, mouseX, mouseY, (this.width - this.xSize) / 2 + 119, (this.height - this.ySize) / 2 + 36, 22, 22, Lists.newArrayList(button.getMessage(), new TranslationTextComponent("container.barter.trade_station.not_enough").mergeStyle(TextFormatting.RED)));
        }
    }

    public void buttonUpTooltip(Button button, MatrixStack matrixStack, int mouseX, int mouseY)
    {
        if (button.active && button.isHovered())
        {
            GuiHelper.drawTooltip(this, matrixStack, mouseX, mouseY, (this.width - this.xSize) / 2 + 144, (this.height - this.ySize) / 2 + 6, 10, 10, Lists.newArrayList(button.getMessage()));
        }
    }

    public void buttonDownTooltip(Button button, MatrixStack matrixStack, int mouseX, int mouseY)
    {
        if (button.active && button.isHovered())
        {
            GuiHelper.drawTooltip(this, matrixStack, mouseX, mouseY, (this.width - this.xSize) / 2 + 144, (this.height - this.ySize) / 2 + 18, 10, 10, Lists.newArrayList(button.getMessage()));
        }
    }

    private void purchase()
    {
        if (getContainer().getTileEntity().isSpecialMode())
        {
            SimpleNetworkHandler.CHANNEL.sendToServer(new TradeStationSpecialPurchaseMessage(getContainer().getTileEntity().getPos(), indexToBuy));
            if (indexToBuy != 0)
            {
                chooseNext();
            }
        }
        else
            SimpleNetworkHandler.CHANNEL.sendToServer(new TradeStationPurchaseMessage(getContainer().getTileEntity().getPos()));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY)
    {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        int offsetX = (width - xSize) / 2, offsetY = (height - ySize) / 2;
        GuiHelper.drawLayer(matrixStack, offsetX, offsetY, TEXTURE, new TexturePos(0, 0, xSize, ySize));

        buttonCheck.render(matrixStack, mouseX, mouseY, partialTicks);
        GuiHelper.drawLayer(matrixStack, offsetX + 119, offsetY + 36, TEXTURE, new TexturePos(186, 0, 22, 22));
        if (buttonCheck.isPressed())
        {
            GuiHelper.drawLayer(matrixStack, offsetX + 119, offsetY + 36, TEXTURE, new TexturePos(186, 44, 22, 22));
        }
        else if (buttonCheck.isHovered())
        {
            GuiHelper.drawLayer(matrixStack, offsetX + 119, offsetY + 36, TEXTURE, new TexturePos(186, 22, 22, 22));
        }

        if (buttonUp.active)
        {
            buttonUp.render(matrixStack, mouseX, mouseY, partialTicks);
            GuiHelper.drawLayer(matrixStack, offsetX + 144, offsetY + 6, TEXTURE, new TexturePos(176, 0, 10, 10));
            if (buttonUp.isHovered())
            {
                GuiHelper.drawLayer(matrixStack, offsetX + 144, offsetY + 6, TEXTURE, new TexturePos(176, 10, 10, 10));
            }
        }

        if (buttonDown.active)
        {
            buttonDown.render(matrixStack, mouseX, mouseY, partialTicks);
            GuiHelper.drawLayer(matrixStack, offsetX + 144, offsetY + 18, TEXTURE, new TexturePos(176, 20, 10, 10));
            if (buttonDown.isHovered())
            {
                GuiHelper.drawLayer(matrixStack, offsetX + 144, offsetY + 18, TEXTURE, new TexturePos(176, 30, 10, 10));
            }
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY)
    {

    }
}

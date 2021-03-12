package cloud.lemonslice.barter.client.gui;

import cloud.lemonslice.barter.Barter;
import cloud.lemonslice.barter.common.block.WoodenTradeStationBlock;
import cloud.lemonslice.barter.common.container.TradeStationSaleContainer;
import cloud.lemonslice.barter.network.SimpleNetworkHandler;
import cloud.lemonslice.barter.network.client.TradeStationLockMessage;
import cloud.lemonslice.barter.network.client.TradeStationSwitchRedstoneMessage;
import cloud.lemonslice.barter.network.client.TradeStationTextChangeMessage;
import cloud.lemonslice.silveroak.client.texture.TexturePos;
import cloud.lemonslice.silveroak.client.widget.IconButton;
import cloud.lemonslice.silveroak.helper.GuiHelper;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Collections;
import java.util.List;

import static cloud.lemonslice.barter.Barter.MODID;

public class TradeStationSaleGuiContainer extends ContainerScreen<TradeStationSaleContainer>
{
    private static final String TEXTURE_PATH = "textures/gui/trade_sale_gui.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(MODID, TEXTURE_PATH);

    private TradeStationSaleContainer container;
    private int offsetX;
    private int offsetY;

    private TextFieldWidget nameField;
    private IconButton buttonCheck;
    private IconButton buttonRedstone;
    private IconButton buttonLock;

    public TradeStationSaleGuiContainer(TradeStationSaleContainer container, PlayerInventory inv, ITextComponent name)
    {
        super(container, inv, name);
        this.container = container;
        this.ySize = 233;
    }

    protected void initWidgets()
    {
        this.minecraft.keyboardListener.enableRepeatEvents(true);

        this.nameField = new TextFieldWidget(this.font, offsetX + 62, offsetY + 101, 104, 12, new TranslationTextComponent("container.barter.trade_station.sale"));
        this.nameField.setCanLoseFocus(true);
        this.nameField.setTextColor(-1);
        this.nameField.setDisabledTextColour(-1);
        this.nameField.setResponder(this::whileTyping);
        this.nameField.setEnableBackgroundDrawing(false);
        this.nameField.setMaxStringLength(32767);
        this.nameField.setText(container.getTileEntity().getInput());
        this.children.add(this.nameField);

        this.buttonCheck = new IconButton(offsetX + 59, offsetY + 121, 22, 22, new TranslationTextComponent("tooltip.barter.trade_station.check"), button -> applyChange(nameField.getText()), this::buttonCheckTooltip);
        this.children.add(this.buttonCheck);

        this.buttonRedstone = new IconButton(offsetX + 147, offsetY + 121, 22, 22, new TranslationTextComponent("tooltip.barter.trade_station.redstone"), button -> switchRedstoneMode(), this::buttonRedstoneTooltip);
        this.children.add(this.buttonRedstone);
        if (container.getTileEntity().getBlockState().getBlock() instanceof WoodenTradeStationBlock)
        {
            buttonRedstone.active = false;
        }

        this.buttonLock = new IconButton(offsetX + 123, offsetY + 121, 22, 22, new TranslationTextComponent("tooltip.barter.trade_station.lock"), button -> switchLock(), this::buttonLockTooltip);
        this.children.add(this.buttonLock);
    }

    @Override
    protected void init()
    {
        super.init();
        this.offsetX = (this.width - this.xSize) / 2;
        this.offsetY = (this.height - this.ySize) / 2;
        this.initWidgets();
    }

    @Override
    public void tick()
    {
        super.tick();
        this.nameField.tick();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderNameField(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    public void buttonRedstoneTooltip(Button button, MatrixStack matrixStack, int mouseX, int mouseY)
    {
        if (button.isHovered())
        {
            List<ITextComponent> list = Lists.newArrayList(button.getMessage());
            switch (getContainer().getTileEntity().getRedstoneMode())
            {
                case 0:
                    list.add(new TranslationTextComponent("tooltip.barter.trade_station.redstone.short_pulse"));
                    break;
                case 1:
                    list.add(new TranslationTextComponent("tooltip.barter.trade_station.redstone.long_pulse"));
                    break;
                default:
                    list.add(new TranslationTextComponent("tooltip.barter.trade_station.redstone.none"));
            }
            GuiHelper.drawTooltip(this, matrixStack, mouseX, mouseY, button.x, button.y, 22, 22, list);
        }
    }

    public void buttonLockTooltip(Button button, MatrixStack matrixStack, int mouseX, int mouseY)
    {
        if (button.isHovered())
        {
            List<ITextComponent> list = Lists.newArrayList(button.getMessage());
            if (getContainer().getTileEntity().isLocked())
            {
                list.add(new TranslationTextComponent("tooltip.barter.trade_station.lock.locked"));
            }
            else
            {
                list.add(new TranslationTextComponent("tooltip.barter.trade_station.lock.unlocked"));
            }
            GuiHelper.drawTooltip(this, matrixStack, mouseX, mouseY, button.x, button.y, 22, 22, list);
        }
    }

    public void buttonCheckTooltip(Button button, MatrixStack matrixStack, int mouseX, int mouseY)
    {
        if (button.isHovered())
        {
            GuiHelper.drawTooltip(this, matrixStack, mouseX, mouseY, button.x, button.y, 22, 22, Collections.singletonList(button.getMessage()));
        }
    }

    public void whileTyping(String input)
    {
        this.nameField.setTextColor(-1);
        this.nameField.setDisabledTextColour(-1);
    }

    private void switchRedstoneMode()
    {
        SimpleNetworkHandler.CHANNEL.sendToServer(new TradeStationSwitchRedstoneMessage(getContainer().getTileEntity().getPos()));
        getContainer().getTileEntity().switchRedstoneMode();
    }

    private void switchLock()
    {
        Barter.PROXY.getClientPlayer().getUniqueID();
        SimpleNetworkHandler.CHANNEL.sendToServer(new TradeStationLockMessage(getContainer().getTileEntity().getPos()));
        getContainer().getTileEntity().lockOrUnlock();
    }

    private void applyChange(String input)
    {
        this.container.getTileEntity().setInput(input);
        this.container.getTileEntity().markDirty();
        SimpleNetworkHandler.CHANNEL.sendToServer(new TradeStationTextChangeMessage(input, getContainer().getTileEntity().getPos()));

        int check = this.getContainer().getTileEntity().getCheck();
        if (check == 1)
        {
            this.nameField.setTextColor(0x55FF55);
            this.nameField.setDisabledTextColour(0x55FF55);
        }
        else if (check == -1)
        {
            this.nameField.setTextColor(0xFF5555);
            this.nameField.setDisabledTextColour(0xFF5555);
        }
    }

    protected void renderNameField(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        this.nameField.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY)
    {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        GuiHelper.drawLayer(matrixStack, offsetX, offsetY, TEXTURE, new TexturePos(0, 0, xSize, ySize));

        GuiHelper.drawLayer(matrixStack, offsetX + 59, offsetY + 97, new TexturePos(110, 233, 110, 16));
        if (this.nameField.isFocused())
        {
            GuiHelper.drawLayer(matrixStack, offsetX + 59, offsetY + 97, new TexturePos(0, 233, 110, 16));
        }

        GuiHelper.renderIconButton(matrixStack, partialTicks, mouseX, mouseY, TEXTURE, buttonCheck,
                new TexturePos(198, 0, 22, 22),
                new TexturePos(198, 22, 22, 22),
                new TexturePos(198, 44, 22, 22));

        GuiHelper.renderIconButton(matrixStack, partialTicks, mouseX, mouseY, TEXTURE, buttonRedstone,
                new TexturePos(198, 66, 22, 22),
                new TexturePos(198, 88, 22, 22),
                new TexturePos(198, 110, 22, 22));

        GuiHelper.renderIconButton(matrixStack, partialTicks, mouseX, mouseY, TEXTURE, buttonLock,
                new TexturePos(198, 66, 22, 22),
                new TexturePos(198, 88, 22, 22),
                new TexturePos(198, 110, 22, 22));

        RenderSystem.enableAlphaTest();
        switch (getContainer().getTileEntity().getRedstoneMode())
        {
            case 0:
                GuiHelper.drawLayer(matrixStack, buttonRedstone.x, buttonRedstone.y, TEXTURE, new TexturePos(176, 0, 22, 22));
                break;
            case 1:
                GuiHelper.drawLayer(matrixStack, buttonRedstone.x, buttonRedstone.y, TEXTURE, new TexturePos(176, 22, 22, 22));
                break;
            default:
                GuiHelper.drawLayer(matrixStack, buttonRedstone.x, buttonRedstone.y, TEXTURE, new TexturePos(176, 44, 22, 22));
        }

        if (getContainer().getTileEntity().isLocked())
        {
            GuiHelper.drawLayer(matrixStack, buttonLock.x, buttonLock.y, TEXTURE, new TexturePos(176, 88, 22, 22));
        }
        else
        {
            GuiHelper.drawLayer(matrixStack, buttonLock.x, buttonLock.y, TEXTURE, new TexturePos(176, 66, 22, 22));
        }
    }

    @Override
    public void onClose()
    {
        super.onClose();
        this.minecraft.keyboardListener.enableRepeatEvents(false);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY)
    {

    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers)
    {
        if (keyCode == 256)
        {
            this.minecraft.player.closeScreen();
        }

        return this.nameField.keyPressed(keyCode, scanCode, modifiers) || this.nameField.canWrite() || super.keyPressed(keyCode, scanCode, modifiers);
    }
}

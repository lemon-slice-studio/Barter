package cloud.lemonslice.barter.client.gui;

import cloud.lemonslice.barter.client.widget.IconButton;
import cloud.lemonslice.barter.common.container.TradeStationSaleContainer;
import cloud.lemonslice.barter.network.SimpleNetworkHandler;
import cloud.lemonslice.barter.network.client.TradeStationTextChangeMessage;
import cloud.lemonslice.silveroak.client.texture.TexturePos;
import cloud.lemonslice.silveroak.helper.GuiHelper;
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

import static cloud.lemonslice.barter.Barter.MODID;

public class TradeStationSaleGuiContainer extends ContainerScreen<TradeStationSaleContainer>
{
    private static final String TEXTURE_PATH = "textures/gui/trade_sale_gui.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(MODID, TEXTURE_PATH);
    private TradeStationSaleContainer container;
    private TextFieldWidget nameField;
    private IconButton buttonCheck;

    public TradeStationSaleGuiContainer(TradeStationSaleContainer container, PlayerInventory inv, ITextComponent name)
    {
        super(container, inv, name);
        this.container = container;
        this.ySize = 233;
    }

    protected void initWidgets()
    {
        this.minecraft.keyboardListener.enableRepeatEvents(true);

        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;

        this.nameField = new TextFieldWidget(this.font, i + 62, j + 101, 104, 12, new TranslationTextComponent("container.barter.trade_station.sale"));
        this.nameField.setCanLoseFocus(true);
        this.nameField.setTextColor(-1);
        this.nameField.setDisabledTextColour(-1);
        this.nameField.setResponder(this::whileTyping);
        this.nameField.setEnableBackgroundDrawing(false);
        this.nameField.setMaxStringLength(32767);
        this.nameField.setText(container.getTileEntity().getInput());
        this.children.add(this.nameField);

        this.buttonCheck = new IconButton(i + 59, j + 121, 22, 22, new TranslationTextComponent("container.barter.trade_station.check"), button -> applyChange(nameField.getText()), this::buttonTooltip);
        this.children.add(this.buttonCheck);
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

    public void buttonTooltip(Button button, MatrixStack matrixStack, int mouseX, int mouseY)
    {
        if (button.isHovered())
        {
            GuiHelper.drawTooltip(this, matrixStack, mouseX, mouseY, (this.width - this.xSize) / 2 + 59, (this.height - this.ySize) / 2 + 121, 22, 22, Collections.singletonList(button.getMessage()));
        }
    }

    public void whileTyping(String input)
    {
        this.nameField.setTextColor(-1);
        this.nameField.setDisabledTextColour(-1);
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

        int offsetX = (width - xSize) / 2, offsetY = (height - ySize) / 2;
        GuiHelper.drawLayer(matrixStack, offsetX, offsetY, TEXTURE, new TexturePos(0, 0, xSize, ySize));

        GuiHelper.drawLayer(matrixStack, offsetX + 59, offsetY + 97, TEXTURE, new TexturePos(110, 233, 110, 16));
        if (this.nameField.isFocused())
        {
            GuiHelper.drawLayer(matrixStack, offsetX + 59, offsetY + 97, TEXTURE, new TexturePos(0, 233, 110, 16));
        }

        buttonCheck.render(matrixStack, mouseX, mouseY, partialTicks);
        GuiHelper.drawLayer(matrixStack, offsetX + 59, offsetY + 121, TEXTURE, new TexturePos(198, 0, 22, 22));
        if (buttonCheck.isPressed())
        {
            GuiHelper.drawLayer(matrixStack, offsetX + 59, offsetY + 121, TEXTURE, new TexturePos(198, 44, 22, 22));
        }
        else if (buttonCheck.isHovered())
        {
            GuiHelper.drawLayer(matrixStack, offsetX + 59, offsetY + 121, TEXTURE, new TexturePos(198, 22, 22, 22));
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

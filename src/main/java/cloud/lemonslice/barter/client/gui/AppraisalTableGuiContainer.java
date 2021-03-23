package cloud.lemonslice.barter.client.gui;

import cloud.lemonslice.barter.common.container.AppraisalTableContainer;
import cloud.lemonslice.silveroak.client.texture.TexturePos;
import cloud.lemonslice.silveroak.helper.GuiHelper;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.items.ItemStackHandler;

import static cloud.lemonslice.barter.Barter.MODID;

public class AppraisalTableGuiContainer extends ContainerScreen<AppraisalTableContainer>
{
    private static final String TEXTURE_PATH = "textures/gui/appraisal_gui.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(MODID, TEXTURE_PATH);

    private AppraisalTableContainer container;
    private int offsetX;
    private int offsetY;

    private ItemStackHandler items = new ItemStackHandler(4);
    private TextFieldWidget nameField;
    private Button buttonPaste;

    public AppraisalTableGuiContainer(AppraisalTableContainer container, PlayerInventory inv, ITextComponent name)
    {
        super(container, inv, name);
        this.container = container;
        this.ySize = 185;
    }

    protected void initWidgets()
    {
        this.nameField = new TextFieldWidget(this.font, offsetX + 36, offsetY + 59, 100, 12, new TranslationTextComponent("container.barter.appraisal_table.text"));
        this.nameField.setEnabled(false);
        this.nameField.setTextColor(-1);
        this.nameField.setDisabledTextColour(-1);
        this.nameField.setResponder(string ->
        {
        });
        this.nameField.setEnableBackgroundDrawing(false);
        this.nameField.setMaxStringLength(32767);
        this.children.add(this.nameField);

        TranslationTextComponent translation = new TranslationTextComponent("chat.copy");
        int length = this.font.getStringWidth(translation.getString());
        this.buttonPaste = new Button(offsetX + 84 - length / 2, offsetY + 76, length + 8, 20, translation, this::pasteClip, Button.field_238486_s_);
        this.children.add(this.buttonPaste);
    }

    public void pasteClip(Button button)
    {
        this.minecraft.keyboardListener.setClipboardString(nameField.getText());
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
        boolean needUpdate = false;
        for (int i = 0; i < 4; i++)
        {
            if (!ItemStack.areItemStacksEqual(items.getStackInSlot(i), container.inputs.getStackInSlot(i)))
            {
                items.setStackInSlot(i, container.inputs.getStackInSlot(i));
                needUpdate = true;
            }
        }
        if (needUpdate)
        {
            JsonObject jsonobject = new JsonObject();
            for (int i = 0; i < 4; i++)
            {
                ItemStack item = container.inputs.getStackInSlot(i);
                if (!item.isEmpty())
                {
                    JsonObject object = new JsonObject();
                    object.addProperty("item", item.getItem().getRegistryName().toString());
                    int count = container.inputs.getStackInSlot(i).getCount();
                    if (count > 1)
                    {
                        object.addProperty("count", count);
                    }
                    jsonobject.add("input" + (i + 1), object);
                }
            }
            if (jsonobject.size() != 0)
            {
                nameField.setText(jsonobject.toString());
            }
            else
            {
                nameField.setText("");
            }
        }
    }

    protected void renderNameField(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        this.nameField.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderNameField(matrixStack, mouseX, mouseY, partialTicks);
        this.buttonPaste.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY)
    {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        GuiHelper.drawLayer(matrixStack, offsetX, offsetY, TEXTURE, new TexturePos(0, 0, xSize, ySize));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY)
    {
        this.font.drawString(matrixStack, this.title.getString(), (float) (this.xSize / 2 - this.font.getStringWidth(this.title.getString()) / 2), 9.0F, 4210752);
    }
}

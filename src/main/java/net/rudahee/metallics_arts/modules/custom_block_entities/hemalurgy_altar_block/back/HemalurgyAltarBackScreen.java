package net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.back;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.data.player.data.model.PlayerEntity;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.gui.Square;

import java.awt.*;


public class HemalurgyAltarBackScreen extends AbstractContainerScreen<HemalurgyAltarBackMenu> {

    private static final ResourceLocation TEXTURE_FRONT =
            new ResourceLocation(MetallicsArts.MOD_ID,"textures/gui/hemalurgy_table_back.png");

    private static final Square SIZE_GUI = new Square(new Point(0,0), new Point(0, 231), new Point(236, 0), new Point(236, 231));
    private static final Square BUTTON_CHANGE_GUI = new Square(new Point(99,235), new Point(119, 235), new Point(99, 254), new Point(119, 254));
    private static final Square BUTTON_PUSHED_CHANGE_GUI = new Square(new Point(80,237), new Point(98, 237), new Point(80, 254), new Point(98, 254));
    private static final Square BUTTON_SAVE_GUI = new Square(new Point(217,194), new Point(254, 194), new Point(217, 211), new Point(254, 211));
    private static final Square BUTTON_PUSHED_SAVE_GUI = new Square(new Point(217,174), new Point(254, 174), new Point(217, 191), new Point(254, 191));

    private static int tick = 0;

    public HemalurgyAltarBackScreen(HemalurgyAltarBackMenu menu, Inventory inv, Component component) {
        super(menu, inv, component);
    }

    @Override
    protected void init() {
        super.init();

        IInvestedPlayerData data = CapabilityUtils.getCapability(Minecraft.getInstance().player);

        if (data == null) {
            return;
        }

        PlayerEntity entity = data.getPlayerData();


    }

    @Override
    protected void renderBg(PoseStack stack, float pPartialTick, int pMouseX, int pMouseY) {

        tick++;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE_FRONT);
        int x = (width - SIZE_GUI.getBottomRight().x) / 2;
        int y = (height - SIZE_GUI.getBottomRight().y) / 2;

        int offsetY = 14;

        blit(stack, x - 1, y + offsetY, SIZE_GUI.getTopLeft().x, SIZE_GUI.getTopLeft().y, SIZE_GUI.getBottomRight().x, SIZE_GUI.getBottomRight().y);

        //drawCenteredString(stack, Minecraft.getInstance().font, String.valueOf(super.mouseClicked(pMouseX, pMouseY, InputConstants.MOUSE_BUTTON_LEFT)), x + 10, y + 10, Color.RED.getRGB());

        //blit(stack, x + 5, y + 30, BUTTON_PUSHED_CHANGE_GUI.getTopLeft().x, BUTTON_PUSHED_CHANGE_GUI.getTopLeft().y, BUTTON_PUSHED_CHANGE_GUI.getBottomRight().x - BUTTON_PUSHED_CHANGE_GUI.getTopLeft().x, BUTTON_PUSHED_CHANGE_GUI.getBottomRight().y - BUTTON_PUSHED_CHANGE_GUI.getTopLeft().y);
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float delta) {
        this.renderBg(stack, delta, mouseX, mouseY);
        super.render(stack, mouseX, mouseY, delta);
        super.renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
        this.font.draw(stack, this.title, -27.0F, -15.0F, 4210752);

        this.font.draw(stack, this.playerInventoryTitle, 2.0F, (float) (this.imageHeight - 48), 4210752);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}

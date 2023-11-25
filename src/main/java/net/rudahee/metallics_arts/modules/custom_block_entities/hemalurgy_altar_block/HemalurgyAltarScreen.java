package net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block;

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
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.gui.Square;

import java.awt.*;


public class HemalurgyAltarScreen extends AbstractContainerScreen<HemalurgyAltarMenu> {

    private static final ResourceLocation TEXTURE_FRONT =
            new ResourceLocation(MetallicsArts.MOD_ID,"textures/gui/hemalurgy_table_front.png");

    private static final Square SIZE_GUI = new Square(new Point(0,0), new Point(0, 273), new Point(191, 0), new Point(253, 243));
    private static final Square BUTTON_CHANGE_GUI = new Square(new Point(217,152), new Point(235, 152), new Point(217, 169), new Point(235, 169));
    private static final Square BUTTON_PUSHED_CHANGE_GUI = new Square(new Point(237,152), new Point(255, 152), new Point(235, 169), new Point(255, 169));
    private static final Square BUTTON_SAVE_GUI = new Square(new Point(217,194), new Point(254, 194), new Point(217, 211), new Point(254, 211));
    private static final Square BUTTON_PUSHED_SAVE_GUI = new Square(new Point(217,174), new Point(254, 174), new Point(217, 191), new Point(254, 191));

    private static int tick = 0;

    public HemalurgyAltarScreen(HemalurgyAltarMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();

        try {
            IInvestedPlayerData data = CapabilityUtils.getCapability(Minecraft.getInstance().player);
            PlayerEntity entity = data.getPlayerData();
            System.out.println(entity.toString());
        } catch (PlayerException e) {
            System.out.println("Hey, el data o el player se fueron a la puta.");
        }

    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE_FRONT);
        int x = (width - SIZE_GUI.getBottomRight().x) / 2;
        int y = (height - SIZE_GUI.getBottomRight().y) / 2;

        blit(pPoseStack, x - 1, y + 14, SIZE_GUI.getTopLeft().x, SIZE_GUI.getTopLeft().y, SIZE_GUI.getBottomRight().x, SIZE_GUI.getBottomRight().y);
    }


    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
     // renderButtons(pPoseStack);
    }

    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
        this.font.draw(stack, this.title, -30.0F, -19.0F, 4210752);
        this.font.draw(stack, this.playerInventoryTitle, 8.0F, (float) (this.imageHeight - 39), 4210752);
    }



    protected void renderButtons(PoseStack stack) {
        int xWinSave = (width / 2) + 125;
        int yWinSave = (height / 2) + 70;
        int xWinChange = (width / 2) - 125;
        int yWinChange = (height / 2) + 70;

        blit(stack, xWinSave, yWinSave, BUTTON_CHANGE_GUI.getTopLeft().x, BUTTON_CHANGE_GUI.getTopLeft().y, BUTTON_CHANGE_GUI.getBottomRight().x, BUTTON_CHANGE_GUI.getBottomRight().y);
        blit(stack, xWinChange, yWinChange, BUTTON_SAVE_GUI.getTopLeft().x, BUTTON_SAVE_GUI.getTopLeft().y, BUTTON_SAVE_GUI.getBottomRight().x, BUTTON_SAVE_GUI.getBottomRight().y);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}

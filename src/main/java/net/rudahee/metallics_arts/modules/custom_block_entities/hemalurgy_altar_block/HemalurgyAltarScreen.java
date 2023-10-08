package net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.utils.gui.Square;

import java.awt.*;


public class HemalurgyAltarScreen extends AbstractContainerScreen<HemalurgyAltarMenu> {

    private static final ResourceLocation TEXTURE_FRONT =
            new ResourceLocation(MetallicsArts.MOD_ID,"textures/gui/hemalurgy_table_front.png");

    private static final Square SIZE_GUI = new Square(new Point(0,0), new Point(0, 273), new Point(191, 0), new Point(273, 191));

    private static int tick = 0;



    public HemalurgyAltarScreen(HemalurgyAltarMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE_FRONT);
        int x = (width - SIZE_GUI.getBottomRight().x) / 2;
        int y = (height - SIZE_GUI.getBottomRight().y) / 2;



        blit(pPoseStack, x - 1, y - 56, SIZE_GUI.getTopLeft().x, SIZE_GUI.getTopLeft().y, SIZE_GUI.getBottomRight().x, SIZE_GUI.getBottomRight().y + 100, SIZE_GUI.getBottomRight().x + 130, SIZE_GUI.getBottomRight().y + 120);

    }


    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
        this.font.draw(stack, this.title, 8.0F, 6.0F, 4210752);

        this.font.draw(stack, this.playerInventoryTitle, 8.0F, (float) (this.imageHeight - 64), 4210752);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}

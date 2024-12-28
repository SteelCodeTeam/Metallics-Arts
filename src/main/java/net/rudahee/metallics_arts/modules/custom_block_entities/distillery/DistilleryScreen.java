package net.rudahee.metallics_arts.modules.custom_block_entities.distillery;

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
import java.util.ArrayList;


public class DistilleryScreen extends AbstractContainerScreen<DistilleryMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(MetallicsArts.MOD_ID,"textures/gui/distillery_gui.png");

    private static int tick = 0;

    private static final Square SIZE_GUI = new Square(new Point(0,0), new Point(175, 0), new Point(0, 193), new Point(175, 193));

    public DistilleryScreen(DistilleryMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {

        tick++;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = ((width - SIZE_GUI.getBottomRight().x) / 2) - 1;
        int y = ((height - SIZE_GUI.getBottomRight().y) / 2) + 1;

        int offsetY = 14;

        this.blit(pPoseStack, x, y + offsetY, SIZE_GUI.getTopLeft().x, SIZE_GUI.getTopLeft().y, SIZE_GUI.getBottomRight().x, SIZE_GUI.getBottomRight().y);


        renderLavaAnimation(pPoseStack, x , y);
        renderLavaHeight(pPoseStack, x , y);
        renderProgress(pPoseStack, x, y);

        if (tick > 480) {
            tick = 0;
        }
    }

    private void renderHotGui(PoseStack stack, int x, int y) {
    }

    protected void renderLavaHeight(PoseStack stack, int x, int y) {
    }

    protected void renderLavaAnimation(PoseStack stack, int x, int y) {


    }

    private void renderProgress(PoseStack stack, int x, int y) {
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

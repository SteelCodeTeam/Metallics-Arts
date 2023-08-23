package net.rudahee.metallics_arts.modules.custom_block_entities.crucible_furnace;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.phys.Vec3;
import net.rudahee.metallics_arts.MetallicsArts;
import net.minecraft.network.chat.Component;
import net.rudahee.metallics_arts.utils.gui.Square;

import java.awt.*;
import java.util.ArrayList;


public class CrucibleFurnaceScreen extends AbstractContainerScreen<CrucibleFurnaceMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(MetallicsArts.MOD_ID,"textures/gui/crucible_furnace_gui.png");

    private static int tick = 0;

    private static final Square SIZE_GUI = new Square(new Point(0,0), new Point(175, 0), new Point(0, 193), new Point(175, 193));
    private static final Square SIZE_LAVA_GUI = new Square(new Point(8, 42), new Point(23, 42), new Point(256, 256), new Point(23, 93));
    private static final ArrayList<Square> SIZE_LAVA_BARS = new ArrayList<>() {{
        add(new Square(new Point(183, 104), new Point(198, 104), new Point(183, 155), new Point(198, 155)));
        add(new Square(new Point(211, 106), new Point(227, 106), new Point(211, 158), new Point(227, 158)));
        add(new Square(new Point(227, 106), new Point(243, 106), new Point(227, 158), new Point(243, 158)));
        add(new Square(new Point(211, 89), new Point(247, 89), new Point(211, 141), new Point(247, 141)));
        add(new Square(new Point(227, 89), new Point(263, 89), new Point(227, 141), new Point(263, 141)));
    }};



    public CrucibleFurnaceScreen(CrucibleFurnaceMenu menu, Inventory inventory, Component component) {
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
        int x = (width - SIZE_GUI.getBottomRight().x) / 2;
        int y = (height - SIZE_GUI.getBottomRight().y) / 2;

        int offsetY = 14;

        this.blit(pPoseStack, x - 1, y + offsetY, SIZE_GUI.getTopLeft().x, SIZE_GUI.getTopLeft().y, SIZE_GUI.getBottomRight().x, SIZE_GUI.getBottomRight().y);
        renderLavaBar(pPoseStack, x , y);
        renderProgressArrow(pPoseStack, width / 2, height / 2);

        if (tick > 120) {
            tick = 0;
        }
    }

    protected void renderLavaBar(PoseStack stack, int x, int y) {

        CrucibleFurnaceMenu menu = getMenu();

        int width = 16;

        if (tick >= 0 && tick < 25) {
            this.blit(stack, x + SIZE_LAVA_GUI.getTopLeft().x - 1, y + SIZE_LAVA_GUI.getTopLeft().y + 14, SIZE_LAVA_BARS.get(0).getTopLeft().x, SIZE_LAVA_BARS.get(0).getTopLeft().y, width, menu.getFuelQty());
        } else if (tick >= 25 && tick < 50) {
            this.blit(stack, x + SIZE_LAVA_GUI.getTopLeft().x - 1, y + SIZE_LAVA_GUI.getTopLeft().y + 14, SIZE_LAVA_BARS.get(1).getTopLeft().x, SIZE_LAVA_BARS.get(1).getTopLeft().y, width, menu.getFuelQty());
        } else if (tick >= 50 && tick < 75) {
            this.blit(stack, x + SIZE_LAVA_GUI.getTopLeft().x - 1, y + SIZE_LAVA_GUI.getTopLeft().y + 14, SIZE_LAVA_BARS.get(2).getTopLeft().x, SIZE_LAVA_BARS.get(2).getTopLeft().y, width, menu.getFuelQty());
        } else if (tick >= 75 && tick < 100) {
            this.blit(stack, x + SIZE_LAVA_GUI.getTopLeft().x - 1, y + SIZE_LAVA_GUI.getTopLeft().y + 14, SIZE_LAVA_BARS.get(3).getTopLeft().x, SIZE_LAVA_BARS.get(3).getTopLeft().y, width, menu.getFuelQty());
        } else if (tick >= 100) {
            this.blit(stack, x + SIZE_LAVA_GUI.getTopLeft().x - 1, y + SIZE_LAVA_GUI.getTopLeft().y + 14, SIZE_LAVA_BARS.get(4).getTopLeft().x, SIZE_LAVA_BARS.get(4).getTopLeft().y, width, menu.getFuelQty());
        }

    }

    private void renderProgressArrow(PoseStack pPoseStack, int x, int y) {

        blit(pPoseStack, x + 105, y + 33, 176, 0, 8, 1);

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

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

    private static final ArrayList<Square> FRAME_ANIMATION_FUEL = new ArrayList<>() {{
        add(new Square(new Point(213,3), new Point(254, 3), new Point(213,45), new Point(245,45)));
        add(new Square(new Point(213,49), new Point(254, 49), new Point(213,91), new Point(245,91)));
        add(new Square(new Point(213,96), new Point(254, 96), new Point(213,138), new Point(245,138)));
        add(new Square(new Point(213,142), new Point(254, 142), new Point(213,184), new Point(245,184)));
    }};
    private static final Square FRAME_FUEL = new Square(new Point(25,50), new Point(68, 50),new Point(25,93), new Point(68, 93));

    private static final Square BUBBLES_ANIMATION = new Square(new Point(193,18), new Point(205, 18),new Point(193,47), new Point(205, 47));
    private static final Square BUBBLES = new Square(new Point(29,19), new Point(40, 19),new Point(29,47), new Point(40, 47));

    private static final Square CHARGE_FUEL_ANIMATION = new Square(new Point(187,2), new Point(211, 2),new Point(187,17), new Point(211, 17));
    private static final Square CHARGE_FUEL = new Square(new Point(44,36), new Point(68, 36),new Point(44,49), new Point(68, 49));

    private static final Square PROGRESS_BAR_ANIMATION = new Square(new Point(180,88), new Point(206, 88),new Point(180,116), new Point(206, 116));
    private static final Square PROGRESS_BAR = new Square(new Point(96,60), new Point(122, 60),new Point(96,87), new Point(122, 87));


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

        if (menu.hasFuelStored()) {
            renderfuelAnimation(pPoseStack, x + 1, y);
        }

        if (menu.hasFuelInSlot()) {
            renderChargeFuelAnimation(pPoseStack, x, y - 1);
            renderBubblesAnimation(pPoseStack, x, y - 1);
        }

        renderProgress(pPoseStack, x, y);

        if (tick > 480) {
            tick = 0;
        }
    }

    protected void renderBubblesAnimation(PoseStack stack, int x, int y) {
        int height = BUBBLES_ANIMATION.getBottomLeft().y - BUBBLES_ANIMATION.getTopLeft().y;
        int width = BUBBLES_ANIMATION.getTopRight().x - BUBBLES_ANIMATION.getTopLeft().x;

        double percentage = menu.getAddFuelProgressPercentage();
        int sizeOfFuel = Math.min(height, (int) ((percentage / 100) * height));


        this.blit(stack, x + BUBBLES.getTopLeft().x, y + BUBBLES.getTopLeft().y + 14 + height - sizeOfFuel, BUBBLES_ANIMATION.getTopLeft().x, BUBBLES_ANIMATION.getTopLeft().y + height - sizeOfFuel, width, sizeOfFuel);

    }

    protected void renderProgress(PoseStack stack, int x, int y) {
        int height = PROGRESS_BAR_ANIMATION.getBottomLeft().y - PROGRESS_BAR_ANIMATION.getTopLeft().y;
        int width = PROGRESS_BAR_ANIMATION.getTopRight().x - PROGRESS_BAR_ANIMATION.getTopLeft().x;

        double percentageHeight = menu.getProgressPercentage();
        double percentageWidth = (menu.getProgressPercentage() - 40) * 2;

        int sizeOfFuelHeight = Math.min(height, (int) ((percentageHeight * 2 / 100) * height));
        this.blit(stack, x + PROGRESS_BAR.getTopLeft().x, y + PROGRESS_BAR.getTopLeft().y + 14, PROGRESS_BAR_ANIMATION.getTopLeft().x, PROGRESS_BAR_ANIMATION.getTopLeft().y, 4, sizeOfFuelHeight);

        if (percentageWidth > 0) {
            int sizeOfFuelWidth = Math.min(width, (int) (((percentageWidth - 0.5) / 100) * width));
            this.blit(stack, x + PROGRESS_BAR.getTopLeft().x, y + PROGRESS_BAR.getTopLeft().y + 14 + 21, PROGRESS_BAR_ANIMATION.getTopLeft().x, PROGRESS_BAR_ANIMATION.getTopLeft().y + 21, sizeOfFuelWidth, 7);
        }
    }

    protected void renderChargeFuelAnimation(PoseStack stack, int x, int y) {
        int height = CHARGE_FUEL.getBottomLeft().y - CHARGE_FUEL.getTopLeft().y;
        int width = CHARGE_FUEL.getTopRight().x - CHARGE_FUEL.getTopLeft().x;

        this.blit(stack, x + CHARGE_FUEL.getTopLeft().x, y + CHARGE_FUEL.getTopLeft().y + 14, CHARGE_FUEL_ANIMATION.getTopLeft().x, CHARGE_FUEL_ANIMATION.getTopLeft().y, width, height + 2);


    }

    protected void renderfuelAnimation(PoseStack stack, int x, int y) {
        int height = FRAME_FUEL.getBottomLeft().y - FRAME_FUEL.getTopLeft().y;
        int width = FRAME_FUEL.getTopRight().x - FRAME_FUEL.getTopLeft().x;

        double percentage = menu.getFuelQtyPercentage();
        int sizeOfFuel = Math.min(height, (int) ((percentage / 100) * height));

        if (tick >= 0 && tick < 120) {
            this.blit(stack, x + FRAME_FUEL.getTopLeft().x, y + FRAME_FUEL.getTopLeft().y + 14 + height - sizeOfFuel, FRAME_ANIMATION_FUEL.get(0).getTopLeft().x, FRAME_ANIMATION_FUEL.get(0).getTopLeft().y + height - sizeOfFuel, width, sizeOfFuel);
        } else if (tick >= 120 && tick < 240) {
            this.blit(stack, x + FRAME_FUEL.getTopLeft().x, y + FRAME_FUEL.getTopLeft().y + 14 + height - sizeOfFuel, FRAME_ANIMATION_FUEL.get(1).getTopLeft().x, FRAME_ANIMATION_FUEL.get(1).getTopLeft().y + height - sizeOfFuel, width, sizeOfFuel);
        } else if (tick >= 240 && tick < 360) {
            this.blit(stack, x + FRAME_FUEL.getTopLeft().x, y + FRAME_FUEL.getTopLeft().y + 14 + height - sizeOfFuel, FRAME_ANIMATION_FUEL.get(2).getTopLeft().x, FRAME_ANIMATION_FUEL.get(2).getTopLeft().y + height - sizeOfFuel, width, sizeOfFuel);
        } else if (tick >= 360) {
            this.blit(stack, x + FRAME_FUEL.getTopLeft().x, y + FRAME_FUEL.getTopLeft().y + 14 + height - sizeOfFuel, FRAME_ANIMATION_FUEL.get(3).getTopLeft().x, FRAME_ANIMATION_FUEL.get(3).getTopLeft().y + height - sizeOfFuel, width, sizeOfFuel);
        }
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

package net.rudahee.metallics_arts.modules.blocks.alloy_furnace;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.Texture;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.rudahee.metallics_arts.MetallicsArts;

import java.awt.*;

public class AlloyFurnaceScreen extends ContainerScreen<AlloyFurnaceContainer> {
    private AlloyFurnaceContainer alloyContainer = null;

    private final ResourceLocation GUI = new ResourceLocation(MetallicsArts.MOD_ID,
            "textures/gui/alloy_furnace.png");

    private final Texture texture = new SimpleTexture(GUI);

    private final int i = 0;

    public AlloyFurnaceScreen(AlloyFurnaceContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.alloyContainer = screenContainer;
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack,mouseX,mouseY);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
        renderBackground(matrixStack);
        this.minecraft.textureManager.register(GUI, texture);
        int i = this.getGuiLeft();
        int j = this.getGuiTop();
        super.blit(matrixStack, i, j, 0, 0, this.getXSize(), this.getYSize());

        Point activeArrowInImage = new Point(177, 15);
        Point activeArrowInGame = new Point(i + 86, j + 25);

        int progressLit = AlloyFurnaceTileEntity.getLitProgress();

        int arrowLength = 0;
        if (!AlloyFurnaceTileEntity.isCompleteCrafting()) {
            arrowLength = (Math.round((progressLit / 200.0f) * 24)-24)*-1;
        }

        super.blit(matrixStack, activeArrowInGame.x, activeArrowInGame.y, activeArrowInImage.x , activeArrowInImage.y, arrowLength, 16);

        Point LitBarInImage = new Point(176, 14);
        Point LitBarInGameLeft = new Point(i + 32, j + 69);
        Point LitBarInGameRight = new Point(i + 69, j + 69);

        int progressBar = AlloyFurnaceTileEntity.getBurnProgress();
        int barLength = 0;
        int max = AlloyFurnaceTileEntity.getMaxBurnProgress();
        if (AlloyFurnaceTileEntity.getBurnProgress() > 0) {
            barLength = (Math.round(((progressBar + 1) / Float.parseFloat(Integer.toString(max)) * 13)));
        }

        super.blit(matrixStack, LitBarInGameLeft.x, LitBarInGameLeft.y-14, LitBarInImage.x , LitBarInImage.y-14, 14, 14 - barLength);
        super.blit(matrixStack, LitBarInGameRight.x, LitBarInGameRight.y-14, LitBarInImage.x ,LitBarInImage.y-14, 14, 14 - barLength);

        super.tick();

    }

}
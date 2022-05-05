package net.rudahee.metallics_arts.modules.blocks.alloy_furnace;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.Texture;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.rudahee.metallics_arts.MetallicsArts;

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
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
        renderBackground(matrixStack);
        this.minecraft.textureManager.register(GUI, texture);
        int i = this.getGuiLeft();
        int j = this.getGuiTop();
        super.blit(matrixStack, i, j, 0, 0, this.getXSize(), this.getYSize());
        super.tick();
    }

    @Override
    protected void renderLabels(MatrixStack matrixStack, int i, int j) {
        i++;
        // Ajustar la altura del fuel
        this.font.draw(matrixStack, String.valueOf(i), (float) 128, (float) 54, 123);

    }


}
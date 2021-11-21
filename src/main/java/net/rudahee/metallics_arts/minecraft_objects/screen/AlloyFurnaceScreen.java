package net.rudahee.metallics_arts.minecraft_objects.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.minecraft_objects.containers.AlloyFurnaceContainer;

public class AlloyFurnaceScreen extends ContainerScreen<AlloyFurnaceContainer> {
    private AlloyFurnaceContainer alloyContainer = null;

    private final ResourceLocation GUI = new ResourceLocation(MetallicsArts.MOD_ID,
            "textures/gui/alloy_furnace.png");

    public AlloyFurnaceScreen(AlloyFurnaceContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.alloyContainer = screenContainer;
    }


    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
        renderBackground(matrixStack);
        this.minecraft.textureManager.register(GUI, new SimpleTexture(GUI));
        int i = this.getGuiLeft();
        int j = this.getGuiTop();
        super.blit(matrixStack, i, j, 0, 0, this.getXSize(), this.getYSize());


        // Ajustar la altura del fuel
        if (this.alloyContainer.getTileEntity().isLit()) {
            int k = this.alloyContainer.getTileEntity().getLitProgress();
            this.blit(matrixStack, i + 34, j + 36 + 32 - k, 176, 12 - k, 14, k + 1);
        }

        if (this.alloyContainer.getTileEntity().isLit()) {
            int k = this.alloyContainer.getTileEntity().getLitProgress();
            this.blit(matrixStack, i + 69, j + 36 + 32 - k, 176, 12 - k, 14, k + 1);
        }

        // Altura de la flecha
        int l = this.alloyContainer.getTileEntity().getBurnProgress();
        this.blit(matrixStack, i + 85, j + 28, 176, 14, l + 1, 16);

    }

}
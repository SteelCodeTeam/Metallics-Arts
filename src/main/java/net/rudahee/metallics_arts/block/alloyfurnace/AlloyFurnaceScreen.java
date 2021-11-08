package net.rudahee.metallics_arts.block.alloyfurnace;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.rudahee.metallics_arts.MetallicsArts;

public class AlloyFurnaceScreen extends ContainerScreen<AlloyFurnaceContainer> {

    //REVISAR RUTA
    public static final ResourceLocation TEXTURE = new ResourceLocation(MetallicsArts.MOD_ID, "texture/gui/alloyFurnace.png");

    public AlloyFurnaceScreen(AlloyFurnaceContainer container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, x, y, partialTicks);
        this.renderTooltip(matrixStack, x, y);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
        if (minecraft == null){
            return;
        }
        RenderSystem.color4f(1,1,1,1);
        minecraft.getTextureManager().bind(TEXTURE);

        int posX = (this.width - this.imageWidth)/2;
        int posY = (this.height - this.imageWidth)/2;

        blit(matrixStack,posY,posX,0,0,this.imageWidth,this.imageHeight);

        //progres Arrow
        blit(matrixStack,posX + 79, posY + 35,176, 14, menu.getProgressArrowScale() + 1, 16);


    }
}

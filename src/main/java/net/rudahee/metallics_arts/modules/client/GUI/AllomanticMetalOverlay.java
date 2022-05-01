package net.rudahee.metallics_arts.modules.client.GUI;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.Texture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import org.lwjgl.opengl.GL11;
import sun.awt.X11.XSystemTrayPeer;

import java.awt.*;

public class AllomanticMetalOverlay {
    private static final Point[] AllomanticFrames = new Point[6];

    private static final ResourceLocation meterLocation = new ResourceLocation("metallics_arts", "textures/gui/overlay/meter.png");
    private static int animationCounter = 0;
    private static int currentFrame = 0;

    static {
        int x = 9;
        int firsty = 33;
        for (int i = 0; i < 6; i++) {
            AllomanticFrames[i] = new Point(x, firsty + (6 * i));
        }
    }


    /**
     * Draws the overlay for the metals
     *
     * @param matrix
     */
    public static void drawMetalOverlay(MatrixStack matrix) {
        Minecraft mc = Minecraft.getInstance();
        ClientPlayerEntity player = mc.player;
        MainWindow res = mc.getWindow();

        if (!player.isAlive()) {
            return;
        }

        ForgeIngameGui gui = new ForgeIngameGui(mc);
        mc.getTextureManager().bind(meterLocation);
        Texture objAllomantic;
        objAllomantic = mc.getTextureManager().getTexture(meterLocation);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, objAllomantic.getId());


        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {



            /*
             * RENDERING ALLOMANTIC THINGS
             */


            //Separacion superior
            int allomanticOffsetY = 4;
            //Separacion derecha y entre recipientes
            int allomanticActualOffSetX = 0;

            int allomanticWidthVial = 5;
            int allomanticHeightVial = 16;

            int allomanticPixelOffsetXInVialBar = 3;
            int barOffSet = 7;

            int allomanticWidthBar = 3;
            int allomanticHeightBar = 13;

            int allomanticHeightAnimation = 5;
            int allomanticWidthAnimation = 5;

            for (MetalsNBTData metal: data.getAllomanticPowers()) {

                //matrix, gui, offset en la imagen en el eje X, offset en la imagen en el eje Y, offset a pintar en el juego X,
                // offser a pintar en el juego Y, Cantidad de pixeles a pintar en el eje X, cantidad de pixeles a pintar en el eje Y.

                //Operacion necesaria para separar los botes de cristal
                allomanticActualOffSetX = allomanticActualOffSetX + allomanticWidthVial + 3;

                float division = (float)data.getAllomanticAmount(metal) / (float)metal.getMaxAllomanticTicksStorage();
                int basura = Math.round(division * allomanticHeightBar);


                blit(matrix, gui, allomanticActualOffSetX,  allomanticOffsetY, 0, 0, allomanticWidthVial, allomanticHeightVial);
                blit(matrix, gui, allomanticActualOffSetX + 1, allomanticOffsetY + allomanticPixelOffsetXInVialBar + (allomanticHeightBar - basura), barOffSet, 0, allomanticWidthBar, basura);

                ITextComponent text = new StringTextComponent(Math.round(division * 100) + "%");

                //ForgeIngameGui.drawString(matrix, mc.font, text, allomanticActualOffSetX, allomanticOffsetY + allomanticHeightVial + 1, Integer.parseInt("FF0000", 16));

                // Este calculo mueve el offset 6 pixeles (3 para las barras, y 3 blancos).
                barOffSet = barOffSet + 6;

                if (data.isBurning(metal)) {
                    blit(matrix, gui, allomanticActualOffSetX, allomanticOffsetY + allomanticPixelOffsetXInVialBar, AllomanticFrames[currentFrame].x, AllomanticFrames[currentFrame].y, allomanticWidthAnimation, allomanticHeightAnimation);
                }
            }


            /*
             * RENDERING ALLOMANTIC THINGS
             */


            int offSetTop = allomanticOffsetY  + allomanticHeightVial;

            //Separacion superior
            int feruchemicOffsetY = 3;
            //Separacion derecha y entre recipientes
            int feruchemicActualOffSetX = 0;

            int feruchemicWidthVial = 5;
            int feruchemicHeightVial = 16;

            int feruchemicPixelOffsetXInVialBar = 2;

            int feruchemicWidthBar = 3;
            int feruchemicHeightBar = 13;

            int feruchemicHeightAnimation = 5;
            int feruchemicWidthAnimation = 5;

            barOffSet = 7;
            for (MetalsNBTData metal: data.getFeruchemicPowers()) {

                feruchemicActualOffSetX = feruchemicActualOffSetX + feruchemicWidthVial + 3;
                blit(matrix, gui, feruchemicActualOffSetX,  feruchemicOffsetY + offSetTop, 0, 17, feruchemicWidthVial, feruchemicHeightVial);
                blit(matrix, gui, feruchemicActualOffSetX + 1, feruchemicOffsetY + feruchemicPixelOffsetXInVialBar + offSetTop, barOffSet, 0, feruchemicWidthBar, feruchemicHeightBar);
                //if decant
                blit(matrix, gui, feruchemicActualOffSetX, feruchemicOffsetY + feruchemicPixelOffsetXInVialBar + offSetTop, AllomanticFrames[currentFrame].x, AllomanticFrames[currentFrame].y, feruchemicWidthAnimation, feruchemicHeightAnimation);
                // else if storage
                //blit(matrix, gui, feruchemicActualOffSetX, feruchemicOffsetY + feruchemicPixelOffsetXInVialBar + offSetTop, AllomanticFrames[currentFrame].x, AllomanticFrames[currentFrame].y, feruchemicWidthAnimation, feruchemicHeightAnimation);

                barOffSet = barOffSet + 6;
            }


        });



            animationCounter++;
            if (animationCounter > 300) {
                animationCounter = 0;
                currentFrame++;
                //currentFrame = ThreadLocalRandom.current().nextInt(0, 5 + 1);;
                if(currentFrame > 5) {
                    currentFrame = 0;
                }


            }
    }

    private static void blit(MatrixStack matrix, ForgeIngameGui gui, int x, int y, float uOffset, float vOffset, int uWidth, int vHeight) {
        ForgeIngameGui.blit(matrix, x, y, gui.getBlitOffset(), uOffset, vOffset, uWidth, vHeight, 128, 128);
    }
}

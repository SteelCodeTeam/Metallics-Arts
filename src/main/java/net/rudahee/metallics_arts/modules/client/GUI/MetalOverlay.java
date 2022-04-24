package net.rudahee.metallics_arts.modules.client.GUI;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.texture.Texture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class MetalOverlay {
    private static final Point[] Frames = new Point[4];
    private static final ResourceLocation meterLoc = new ResourceLocation("metallics_arts", "textures/gui/overlay/meter.png");
    private static int animationCounter = 0;
    private static int currentFrame = 0;

    static {
        int x = 0;
        int firsty = 22;
        for (int i = 0; i < 4; i++) {
            Frames[i] = new Point(x, firsty + (4 * i));
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
        mc.getTextureManager().bind(meterLoc);
        Texture obj;
        obj = mc.getTextureManager().getTexture(meterLoc);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, obj.getId());

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {



            /*
             * The rendering for the overlay
             */


            int offSetY = 5;
            int actualOffSetX = 0;
            int barOffSet = 7;
            float vHeightBar = 0;
            for (MetalsNBTData metal: data.getAllomanticPowers()) {
                vHeightBar = (data.getAllomanticAmount(metal) + 1) / metal.getMaxAllomanticTicksStorage();
                System.out.println(data.getAllomanticAmount(metal));
                System.out.println(metal.getMaxAllomanticTicksStorage());
                actualOffSetX = actualOffSetX + offSetY;
                blit(matrix, gui, actualOffSetX + (2 * metal.getIndex()),  offSetY, 0, 0, 5, 17);
                System.out.println(metal.getNameLower() + ": - vHeightBar: " + vHeightBar );
                blit(matrix, gui, actualOffSetX + (2 * metal.getIndex()) + 1, offSetY + 3, barOffSet, 0, 3, Math.round(12 * vHeightBar));
                barOffSet = barOffSet + 6;

                //if (data.isBurning(metal)) {
                blit(matrix, gui, actualOffSetX + (2 * metal.getIndex()), offSetY + 3, Frames[currentFrame].x, Frames[currentFrame].y, 5, 3);
                //}
            }

            animationCounter++;
            if (animationCounter > 10) {
                animationCounter = 0;
                currentFrame++;
                if(currentFrame > 3) {
                    currentFrame = 0;
                }

            }

        });

        // Update the animation counters
        animationCounter++;
        if (animationCounter > 6) {
            animationCounter = 0;
            currentFrame++;
            if (currentFrame > 3) {
                currentFrame = 0;
            }
        }
    }

    private static void blit(MatrixStack matrix, ForgeIngameGui gui, int x, int y, float uOffset, float vOffset, int uWidth, int vHeight) {
        ForgeIngameGui.blit(matrix, x, y, gui.getBlitOffset(), uOffset, vOffset, uWidth, vHeight, 128, 128);
    }
}

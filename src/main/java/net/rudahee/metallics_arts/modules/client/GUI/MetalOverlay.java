package net.rudahee.metallics_arts.modules.client.GUI;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.texture.Texture;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.registries.ModNetwork;
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

        int renderX = 5;
        int renderY = 10;

        ForgeIngameGui gui = new ForgeIngameGui(mc);
        mc.getTextureManager().bind(meterLoc);
        Texture obj;
        obj = mc.getTextureManager().getTexture(meterLoc);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, obj.getId());

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {



            /*
             * The rendering for the overlay
             */


            for (MetalsNBTData metal: data.getAllomanticPowers()) {
                System.out.println(metal);
                blit(matrix, gui, 0, 0 ,0, 0, 5, 17);
            }

                int metalY = 9;
                // Draw the bars first
                //blit(matrix, gui, 0, 0, 0, 0, 128, 128);
                // Draw the gauges second, so that highlights and decorations show over the bar.
                //blit(matrix, gui, 0, 0, 0, 0, 128, 128);
                // Draw the fire if it is burning


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

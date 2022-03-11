package net.rudahee.metallics_arts.modules.client.MetalSelector;

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

public class MetalSelector {
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

            if (!data.isFullInvested()) {
                return;
            }

            /*
             * The rendering for the overlay
             */
            for (MetalsNBTData mt : MetalsNBTData.values()) {
                if (data.hasAllomanticPower(mt)) {
                    int metalY = 9 - data.getAllomanticAmount(mt);
                    int i = mt.getIndex();
                    int offset = (i / 2) * 4; // Adding a gap between pairs
                    // Draw the bars first
                    blit(matrix, gui, renderX + 1 + (7 * i) + offset, renderY + 5 + metalY, 7 + (6 * i), 1 + metalY, 3, 10 - metalY);
                    // Draw the gauges second, so that highlights and decorations show over the bar.
                    blit(matrix, gui, renderX + (7 * i) + offset, renderY, 0, 0, 5, 20);
                    // Draw the fire if it is burning
                    if (data.isBurning(mt)) {
                        blit(matrix, gui, renderX + (7 * i) + offset, renderY + 4 + metalY, Frames[currentFrame].x, Frames[currentFrame].y, 5, 3);
                    }
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

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

public class FeruchemicMetalOverlay {

    private static final Point[] FeruchemicStorageFrames = new Point[6];
    private static final Point[] FeruchemicConsumeFrames = new Point[6];
    private static final ResourceLocation feruchemicMeterLoc = new ResourceLocation("metallics_arts", "textures/gui/overlay/feruchemy_meter.png");
    private static int animationCounter = 0;
    private static int currentFrame = 0;
    /*
  Feruchemic animation points
   */
    static {
        int x = 0;
        int firstY = 200;
        for (int i = 0; i < 6; i++) {
            FeruchemicStorageFrames[i] = new Point(x, firstY + (6 * i));
        }
    }

    static {
        int x = 0;
        int firstY = 200;
        for (int i = 0; i < 6; i++) {
            FeruchemicConsumeFrames[i] = new Point(x, firstY + (6 * i));
        }
    }


    public static void drawMetalOverlay(MatrixStack matrix) {
        Minecraft mc = Minecraft.getInstance();
        ClientPlayerEntity player = mc.player;
        MainWindow res = mc.getWindow();

        if (!player.isAlive()) {
            return;
        }

        ForgeIngameGui gui = new ForgeIngameGui(mc);
        mc.getTextureManager().bind(feruchemicMeterLoc);
        Texture objFeruchemic;
        objFeruchemic = mc.getTextureManager().getTexture(feruchemicMeterLoc);
        GL11.glBindTexture(GL11.GL_TEXTURE, objFeruchemic.getId());

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {
            int offSetTop = 4  + 16;

            //Separacion superior
            int feruchemicOffsetY = 4;
            //Separacion derecha y entre recipientes
            int feruchemicActualOffSetX = 0;

            int feruchemicWidthVial = 5;
            int feruchemicHeightVial = 16;

            int feruchemicPixelOffsetXInVialBar = 4;
            int feruchemicbarOffSet = 7;

            int feruchemicWidthBar = 3;
            int feruchemicHeightBar = 11;

            int feruchemicHeightAnimation = 5;
            int feruchemicWidthAnimation = 5;

            for (MetalsNBTData metal: data.getFeruchemicPowers()) {

                feruchemicActualOffSetX = feruchemicActualOffSetX + feruchemicWidthVial + 3;
                blit(matrix, gui, feruchemicActualOffSetX,  feruchemicOffsetY + offSetTop, 0, 0, feruchemicWidthVial, feruchemicHeightVial);



            }
        });
            animationCounter++;
            if (animationCounter > 100) {
                animationCounter = 0;
                currentFrame++;
                if(currentFrame > 6) {
                    currentFrame = 0;
                }

            }
    }
    private static void blit(MatrixStack matrix, ForgeIngameGui gui, int x, int y, float uOffset, float vOffset, int uWidth, int vHeight) {
        ForgeIngameGui.blit(matrix, x, y, gui.getBlitOffset(), uOffset, vOffset, uWidth, vHeight, 128, 128);
    }
}
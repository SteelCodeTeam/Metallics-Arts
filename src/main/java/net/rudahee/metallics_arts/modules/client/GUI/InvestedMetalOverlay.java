package net.rudahee.metallics_arts.modules.client.GUI;


import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.rudahee.metallics_arts.modules.client.KeyInit;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import top.theillusivec4.curios.api.CuriosApi;

import java.awt.*;


public class InvestedMetalOverlay implements IGuiOverlay {
    private static final Point[] AllomanticFrames = new Point[6];
    private static final Point[] FeruchemicDecantFrames = new Point[6];
    private static final Point[] FeruchemicStorageFrames = new Point[6];


    private static final ResourceLocation meterLocation = new ResourceLocation("metallics_arts", "textures/gui/overlay/meter.png");
    private static int animationCounter = 0;

    private static int currentFrame = 0;

    private static int animationCounterFeruchemic = 0;

    private static int currentFrameFeruchemic = 0;

    private boolean showGui = true;

    private int keyPressedDelay = 0;


    static {
        int x = 9;
        int firsty = 33;
        for (int i = 0; i < 6; i++) {
            AllomanticFrames[i] = new Point(x, firsty + (6 * i));
        }
    }

    static {
        int xStorage = 17;
        int xDecant = 9;

        int firstStorageY = 33;
        int firstDecantY = 33;

        for (int i = 0; i < 6; i++) {
            FeruchemicStorageFrames[i] = new Point(xStorage, firstStorageY + (6 * i));
        }

        for (int i = 0; i < 6; i++) {
            FeruchemicDecantFrames[i] = new Point(xDecant, firstDecantY + (6 * i));
        }
    }

    private static int actualFeruchemicReserve = -1;
    private static String actualIsBandOrRing = "";

    private static void blit(PoseStack matrix, ForgeGui gui, int x, int y, float uOffset, float vOffset, int uWidth, int vHeight) {
        ForgeGui.blit(matrix, x, y, gui.getBlitOffset(), uOffset, vOffset, uWidth, vHeight, 128, 128);
    }


    public InvestedMetalOverlay() {}


    @Override
    public void render(ForgeGui gui, PoseStack matrix, float partialTick, int screenWidth, int screenHeight) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;


        Window res = mc.getWindow();

        if (!player.isAlive()) {
            return;
        }
        if (mc.options.hideGui || !mc.isWindowActive() || !player.isAlive()) {
            return;
        }
        if (mc.screen != null && !(mc.screen instanceof ChatScreen)) {
            return;
        }



        if (KeyInit.SWITCH_OVERLAY.isDown() && keyPressedDelay == 0) {
            showGui = !showGui;
            keyPressedDelay++;
        } else if (keyPressedDelay > 0 && keyPressedDelay <= 40) {
            keyPressedDelay++;
        } else {
            keyPressedDelay = 0;
        }

        if (!showGui) {
            return;
        }


        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, meterLocation);
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

            for (MetalsNBTData metal: MetalsNBTData.values()) {

                //matrix, gui, offset en la imagen en el eje X, offset en la imagen en el eje Y, offset a pintar en el juego X,
                // offser a pintar en el juego Y, Cantidad de pixeles a pintar en el eje X, cantidad de pixeles a pintar en el eje Y.

                //Operacion necesaria para separar los botes de cristal

                allomanticActualOffSetX = allomanticActualOffSetX + allomanticWidthVial + 3;

                float division = (float)data.getAllomanticAmount(metal) / (float)metal.getMaxAllomanticTicksStorage();
                int modifierAllomantic = Math.round(division * allomanticHeightBar);

                if (data.hasAllomanticPower(metal)) {
                    int actualHeightBarXD = allomanticOffsetY + allomanticPixelOffsetXInVialBar + (allomanticHeightBar - modifierAllomantic);

                    blit(matrix, gui, allomanticActualOffSetX,  allomanticOffsetY, 0, 0, allomanticWidthVial, allomanticHeightVial);
                    int basura2xD = (modifierAllomantic == 13) ? actualHeightBarXD : (modifierAllomantic < 13 && modifierAllomantic >= 6) ? actualHeightBarXD - 1 : actualHeightBarXD - 1;
                    blit(matrix, gui, allomanticActualOffSetX+1, basura2xD, barOffSet, 0, allomanticWidthBar, modifierAllomantic);


                    //ForgeIngameGui.drawString(matrix, mc.font, text, allomanticActualOffSetX, allomanticOffsetY + allomanticHeightVial + 1, Integer.parseInt("FF0000", 16));

                    // Este calculo mueve el offset 6 pixeles (3 para las barras, y 3 blancos).

                    if (data.isBurning(metal)) {
                        blit(matrix, gui, allomanticActualOffSetX, basura2xD-1, AllomanticFrames[currentFrame].x, AllomanticFrames[currentFrame].y, allomanticWidthAnimation, (modifierAllomantic > 4) ? allomanticHeightAnimation: (modifierAllomantic == 3) ? allomanticHeightAnimation -1 : (modifierAllomantic == 2) ? allomanticHeightAnimation -3 : allomanticHeightAnimation - 4);
                    }
                } else {
                    blit(matrix, gui, allomanticActualOffSetX,  allomanticOffsetY, 102, 102, allomanticWidthVial, allomanticHeightVial);
                }
                barOffSet = barOffSet + 6;
            }


            /*
             * RENDERING FERUCHEMIC THINGS
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

            int reserve = -1;
            barOffSet = 7;
            for (MetalsNBTData metal: MetalsNBTData.values()) {

                feruchemicActualOffSetX = feruchemicActualOffSetX + feruchemicWidthVial + 3;

                CuriosApi.getCuriosHelper().getEquippedCurios(player).ifPresent(curioData -> {
                    for (int i=0; i < curioData.getSlots(); i++) {
                        if (curioData.getStackInSlot(i).getDisplayName().getString().toLowerCase().contains(metal.getNameLower())) {
                            if (curioData.getStackInSlot(i).hasTag()) {
                                actualFeruchemicReserve = curioData.getStackInSlot(i).getTag().getInt(metal.getNameLower() + "_feruchemic_reserve");
                            }
                            if (curioData.getStackInSlot(i).getDisplayName().getString().toLowerCase().contains("band")) {
                                actualIsBandOrRing = "band";
                            } else {
                                actualIsBandOrRing = "ring";
                            }
                        }
                    }
                });

                int feruchemicReserve = 0;
                boolean isBandOrRing = true; //if band = true;
                if (actualFeruchemicReserve != -1) {
                    feruchemicReserve = actualFeruchemicReserve;
                    actualFeruchemicReserve = -1;
                }

                if (actualIsBandOrRing != "") {
                    isBandOrRing = actualIsBandOrRing.equals("band");
                    actualIsBandOrRing = "";
                }

                int maxReserve = (isBandOrRing) ? metal.getMaxReserveBand() : metal.getMaxReserveRing();

                float divisionFeruchemic = (float)feruchemicReserve / (float)maxReserve;
                int modifierFeruchemic = Math.round(divisionFeruchemic * feruchemicHeightBar);

                boolean fixOffset = (feruchemicHeightBar - modifierFeruchemic) < (float) feruchemicHeightBar / 2f;
                int feruchemicCalculatedOffset = feruchemicOffsetY + feruchemicPixelOffsetXInVialBar + offSetTop + (feruchemicHeightBar - modifierFeruchemic);

                int basura3xD = (modifierFeruchemic == 13) ? feruchemicCalculatedOffset : (modifierFeruchemic < 13 && modifierFeruchemic >= 6) ? feruchemicCalculatedOffset - 1 : feruchemicCalculatedOffset - 1;

                if (data.hasFeruchemicPower(metal)) {
                    blit(matrix, gui, feruchemicActualOffSetX, feruchemicOffsetY + offSetTop, 0, 17, feruchemicWidthVial, feruchemicHeightVial);


                    if (data.getMetalMindEquiped(metal.getGroup())) {
                        blit(matrix, gui, feruchemicActualOffSetX + 1, (modifierFeruchemic == 13) ? feruchemicCalculatedOffset: feruchemicCalculatedOffset - 1, barOffSet, 0, feruchemicWidthBar, modifierFeruchemic);
                    }
                    //if decant
                    if (data.isDecanting(metal)) {
                        blit(matrix, gui, feruchemicActualOffSetX, (modifierFeruchemic == 13) ? feruchemicCalculatedOffset: feruchemicCalculatedOffset - 1, FeruchemicStorageFrames[currentFrame].x, AllomanticFrames[currentFrame].y, feruchemicWidthAnimation, ((fixOffset) ? feruchemicHeightAnimation : 2));
                    // else if storage
                    } else if (data.isStoring(metal)) {
                        blit(matrix, gui, feruchemicActualOffSetX, (modifierFeruchemic == 13) ? feruchemicCalculatedOffset: feruchemicCalculatedOffset - 1, FeruchemicDecantFrames[currentFrame].x, AllomanticFrames[currentFrame].y, feruchemicWidthAnimation,  + ((fixOffset) ? feruchemicHeightAnimation : 2));
                    }
                } else {
                    blit(matrix, gui, feruchemicActualOffSetX,  feruchemicOffsetY + offSetTop, 109, 103, feruchemicWidthVial, feruchemicHeightVial);
                }

                barOffSet = barOffSet + 6;
            }


        });

        animationCounterFeruchemic++;
        if (animationCounterFeruchemic > 100) {
            animationCounterFeruchemic = 0;
            currentFrameFeruchemic++;

            if(currentFrameFeruchemic > 5) {
                currentFrameFeruchemic = 0;
            }


        }

        animationCounter++;
        if (animationCounter > 200) {
            animationCounter = 0;
            currentFrame++;

            if(currentFrame > 5) {
                currentFrame = 0;
            }


        }
    }
}

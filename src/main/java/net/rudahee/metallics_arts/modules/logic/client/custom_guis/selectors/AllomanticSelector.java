package net.rudahee.metallics_arts.modules.logic.client.custom_guis.selectors;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.setup.registries.InvestedPlayerCapabilityRegister;
import net.rudahee.metallics_arts.setup.registries.ModKeyRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.ComparatorMetals;
import net.rudahee.metallics_arts.utils.powers_utils.ClientUtils;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;
import java.util.List;


/**
 * Class that extends Screen. This class control the selectors, that you can choose you're active allomantic power.
 * We need to redo.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @deprecated
 */
@OnlyIn(Dist.CLIENT)
@Deprecated(forRemoval = true, since = "1.5.1")
public class AllomanticSelector extends Screen {

    private static final List<MetalTagEnum> internalMetals = Arrays.stream(MetalTagEnum.values()).filter(metal -> !metal.isExternal() && !metal.isDivine()).sorted(new ComparatorMetals()).toList();
    private static final List<MetalTagEnum> externalMetals = Arrays.stream(MetalTagEnum.values()).filter(metal -> metal.isExternal() && !metal.isDivine()).sorted(new ComparatorMetals()).toList();
    private static final List<MetalTagEnum> divineMetals = Arrays.stream(MetalTagEnum.values()).filter(MetalTagEnum::isDivine).sorted(new ComparatorMetals()).toList();

    final Minecraft mc;
    int slotSelected = -1;
    int list =-1;
    int timeIn = 8;

    static int[] noPowerOdd = new int[] {125, 125, 125, 255};     // Grises = No tienes poder
    static int[] noPowerEven = new int[]{109, 109, 109, 255};

    static int[] normalEven = new int[]{84, 91, 120, 255};           //azules = Tiene poder inactivo y se expande si llevas la mente
    static int[] normalOdd = new int[]{103, 110, 140, 255};

    static int[] burningEven = new int[]{235, 190, 68, 255};
    static int[] burningOdd = new int[]{206, 160, 32, 255};

    static int[] burningEvenDivine = new int[]{235, 190, 45, 255};
    static int[] burningOddDivine = new int[]{220, 165, 40, 255};

    static int[] normalEvenDivine = new int[]{105, 105, 120, 255};
    static int[] normalOddDivine = new int[]{88, 92, 110, 255};

    static int[] noPowerEvenDivine = new int[] {140, 140, 140, 255};
    static int[] noPowerOddDivine = new int[]{123, 123, 123, 255};

    static int[] isCompoundingEven = new int[]{237, 130, 237, 255};       //Pink = desventaja/almacenar
    static int[] isCompoundingOdd = new int[]{219, 65, 219, 255};


    public AllomanticSelector() {
        super(Component.translatable("metallic_arts_allomantic_selector"));
        this.mc = Minecraft.getInstance();
    }

    private static double mouseAngle(int centroX, int centroY, int mouseX, int mouseY) {
        return (Mth.atan2(mouseY - centroY, mouseX - centroX) + Math.PI * 2) % (Math.PI * 2);
    }

    private static double mouseDistance(int centroX, int centroY, int mouseX, int mouseY) {
        return Math.sqrt(Math.pow((mouseX-centroX),2)+Math.pow((mouseY-centroY),2));

    }

    @Override
    public void render(@NotNull PoseStack matrixStack, int mx, int my, float partialTicks) {
        super.render(matrixStack, mx, my, partialTicks);

        IInvestedPlayerData data = null;

        data = CapabilityUtils.getCapability(Minecraft.getInstance().player);


        int centerX  = this.width / 2;
        int centerY  = this.height / 2;
        float internalRadio = (float) this.height / 5;
        float mediumRadio = (float) (internalRadio*1.5);
        float externalRadio = (float) (mediumRadio*1.25);

        double angle = mouseAngle(centerX,centerY,mx,my);
        double distance = mouseDistance(centerX,centerY,mx,my);

        int internalSegments = 8;
        float step = (float) Math.PI / 180;
        float degreesPerSegment  = (float) Math.PI * 2 / internalSegments;
        float degreesDivinePerSegment  = (float) Math.PI * 2 / 4;

        float degreesExternal = (float) Math.PI * 2 / divineMetals.size();

        this.slotSelected = -1;


        Tesselator tess = Tesselator.getInstance();
        BufferBuilder buf = tess.getBuilder();


        RenderSystem.disableCull();
        RenderSystem.enableBlend();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        buf.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);


        //circulo externo
        for (int actualSegment  = 0; actualSegment  < divineMetals.size(); actualSegment++) {
            MetalTagEnum metal = divineMetals.get(actualSegment);
            ;
            boolean mouseInSector = (data != null && data.hasAllomanticPower(metal)) &&
                    (degreesDivinePerSegment*actualSegment < angle && angle < degreesDivinePerSegment*(actualSegment  + 1))
                    && (mediumRadio<distance && distance<externalRadio);


            float radius = externalRadio;

            if (mouseInSector) {
                this.slotSelected = actualSegment;
                this.list=3;
                if (data.getAllomanticAmount(metal)>0) {
                    radius *= 1.025f;
                }
            }

            int[] actualColor = selectActualColor(data, centerX, centerY, buf, actualSegment, metal, noPowerEvenDivine, burningEvenDivine, normalEvenDivine, noPowerOddDivine, burningOddDivine, normalOddDivine);

            for (float v = 0; v < degreesDivinePerSegment  + step/2; v += step) {
                float rad = (v + actualSegment  * degreesDivinePerSegment) ; // (*2) DUPLICA EL TAMAÑO DE LOS ULTIMOS SELECTORES, VISUALMENTE
                float xp = centerX  + Mth.cos(rad) * radius;
                float yp = centerY  + Mth.sin(rad) * radius;

                if (v == 0) {
                    buf.vertex(xp, yp, 0).color(actualColor[0], actualColor[1], actualColor[2], actualColor[3]).endVertex();
                }
                buf.vertex(xp, yp, 0).color(actualColor[0], actualColor[1], actualColor[2], actualColor[3]).endVertex();
            }
        }

        //circulo intermedio
        for (int actualSegment  = 0; actualSegment  < internalSegments; actualSegment++) {
            MetalTagEnum metal = externalMetals.get(actualSegment);
            boolean mouseInSector = data.hasAllomanticPower(metal) &&
                    (degreesPerSegment*actualSegment<angle && angle < degreesPerSegment  * (actualSegment  + 1)) &&
                    (internalRadio<distance && distance<mediumRadio);
            float radius = mediumRadio;

            if (mouseInSector) {
                this.slotSelected = actualSegment;
                this.list=2;
                if (data.getAllomanticAmount(metal)>0) {
                    radius *= 1.025f;
                }
            }

            int[] actualColor;

            if (actualSegment % 2 != 0) {
                if (!data.hasAllomanticPower(metal)) {
                    actualColor = noPowerEven;
                } else if (data.isBurning(metal)) {
                    if (!data.isTapping(metal)) {
                        actualColor = burningEven;
                    } else {
                        actualColor = isCompoundingEven;
                    }
                } else {
                    actualColor = normalEven;
                }
            } else{
                if (!data.hasAllomanticPower(metal)) {
                    actualColor = noPowerOdd;
                } else if (data.isBurning(metal)) {
                    if (!data.isTapping(metal)) {
                        actualColor = burningOdd;
                    } else {
                        actualColor = isCompoundingOdd;
                    }
                } else {
                    actualColor = normalOdd;
                }
            }

            if (actualSegment  == 0) {
                buf.vertex(centerX,centerY,0).color(actualColor[0], actualColor[1],actualColor[2] ,actualColor[3]).endVertex();
            }

            for (float v = 0; v < degreesPerSegment  + step / 2; v += step) {
                float rad = v + actualSegment  * degreesPerSegment ;
                float xp = centerX  + Mth.cos(rad) * radius;
                float yp = centerY  + Mth.sin(rad) * radius;

                if (v == 0) {
                    buf.vertex(xp, yp, 0).color(actualColor[0], actualColor[1],actualColor[2] ,actualColor[3] ).endVertex();
                }
                buf.vertex(xp, yp, 0).color(actualColor[0], actualColor[1],actualColor[2] ,actualColor[3] ).endVertex();
            }

        }

        //circulo interno
        for (int actualSegment=0; actualSegment<internalSegments;actualSegment++) {
            MetalTagEnum metal = internalMetals.get(actualSegment);
            boolean mouseInSector = data.hasAllomanticPower(metal) && (degreesPerSegment*actualSegment < angle && angle < degreesPerSegment  * (actualSegment  + 1))  && (distance<internalRadio);
            float radius = internalRadio;

            if (mouseInSector) {
                this.slotSelected = actualSegment;
                this.list=1;
                if (data.getAllomanticAmount(metal)>0) {
                    radius *= 1.025f;
                }
            }

            int[] actualColor = selectActualColor(data, centerX, centerY, buf, actualSegment, metal, noPowerEven, burningEven, normalEven, noPowerOdd, burningOdd, normalOdd);
            for (float v = 0; v < degreesPerSegment  + step / 2; v += step) {
                float rad = v + actualSegment  * degreesPerSegment ;
                float xp = centerX  + Mth.cos(rad) * radius;
                float yp = centerY  + Mth.sin(rad) * radius;

                if (v == 0) {
                    buf.vertex(xp, yp, 0).color(actualColor[0], actualColor[1],actualColor[2] ,actualColor[3] ).endVertex();
                }
                buf.vertex(xp, yp, 0).color(actualColor[0], actualColor[1],actualColor[2] ,actualColor[3] ).endVertex();
            }
        }
        tess.end();

        //pintado interno
        for (int actualSegment  = 0; actualSegment  < internalSegments ; actualSegment ++) {
            MetalTagEnum metal = internalMetals.get(actualSegment);
            boolean mouseInSector = data.hasAllomanticPower(metal) && (degreesPerSegment*actualSegment < angle && angle < degreesPerSegment*(actualSegment  + 1)) && (distance<internalRadio);
            float radius = internalRadio;
            if (mouseInSector) {
                if (data.getAllomanticAmount(metal)>0) {
                    radius *= 1.025f;
                }
            }

            float rad = (actualSegment  + 0.5f) * degreesPerSegment;
            float xp = centerX  + Mth.cos(rad) * radius;
            float yp = centerY  + Mth.sin(rad) * radius;


            if (mouseInSector) {
                renderTooltip(matrixStack, Component.translatable("metallics_arts.metal_translate."+metal.getNameLower()),mx,my);
            }

            double mod = 0.8;
            int xdp = (int) ((xp - centerX )*mod+centerX);
            int ydp = (int) ((yp - centerY )*mod+centerY);


            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0,new ResourceLocation(MetallicsArts.MOD_ID,"textures/item/symbols/allomantic_symbols/"+metal.getNameLower()+"_symbol.png"));
            RenderSystem.setShaderColor(1, 1, 1, 1);
            blit(matrixStack, xdp - 8, ydp - 8, 0, 0, 16, 16, 16, 16);

        }

       //pintado intermedio
        for (int actualSegment  = 0; actualSegment  < internalSegments ; actualSegment ++) {
            MetalTagEnum metal = externalMetals.get(actualSegment);
            boolean mouseInSector = data.hasAllomanticPower(metal) &&
                    (degreesPerSegment* actualSegment < angle && angle < degreesPerSegment  * (actualSegment  + 1))  &&
                    (internalRadio<distance && distance<mediumRadio);

            float radius = mediumRadio;
            if (mouseInSector) {
                if (data.getAllomanticAmount(metal)>0) {
                    radius *= 1.025f;
                }
            }


            float rad = (actualSegment  + 0.5f) * degreesPerSegment;
            float xp = centerX  + Mth.cos(rad) * radius;
            float yp = centerY  + Mth.sin(rad) * radius;

            if (mouseInSector) {
                renderTooltip(matrixStack,Component.translatable("metallics_arts.metal_translate."+metal.getNameLower()),mx,my);
            }

            double mod = 0.8;
            int xdp = (int) ((xp - centerX ) * mod + centerX );
            int ydp = (int) ((yp - centerY ) * mod + centerY );

            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0,new ResourceLocation(MetallicsArts.MOD_ID,"textures/item/symbols/allomantic_symbols/"+metal.getNameLower()+"_symbol.png"));
            blit(matrixStack, xdp - 8, ydp - 8, 0, 0, 16, 16, 16, 16);

        }

        //pintado externo
        for (int actualSegment  = 0; actualSegment  < divineMetals.size() ; actualSegment ++) {
            MetalTagEnum metal = divineMetals.get(actualSegment);
            boolean mouseInSector = data.hasAllomanticPower(metal) &&
                    (degreesExternal*actualSegment < angle && angle < degreesExternal * (actualSegment  + 1))
                    && (mediumRadio<distance && distance<externalRadio);

            float radius = externalRadio;

            if (mouseInSector && data.getAllomanticAmount(metal)>0) {
                    radius *= 1.025f;
                }


            float rad = (actualSegment + 0.5f) * degreesExternal;
            float xp = centerX  + Mth.cos(rad) * radius;
            float yp = centerY  + Mth.sin(rad) * radius;

            if (mouseInSector) {
                renderTooltip(matrixStack, Component.translatable("metallics_arts.metal_translate."+metal.getNameLower()),mx,my);
            }

            double mod = 0.9;
            int xdp = (int) ((xp - centerX ) * mod + centerX );
            int ydp = (int) ((yp - centerY ) * mod + centerY );

            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0,new ResourceLocation(MetallicsArts.MOD_ID,"textures/item/symbols/allomantic_symbols/"+metal.getNameLower()+"_symbol.png"));
            blit(matrixStack, xdp - 8, ydp - 8, 0, 0, 16, 16, 16, 16);

        }

        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        RenderSystem.disableBlend();
        
    }

    private int[] selectActualColor(IInvestedPlayerData data, int centerX, int centerY, BufferBuilder buf, int actualSegment, MetalTagEnum metal, int[] noPowerEvenDivine, int[] burningEvenDivine, int[] normalEvenDivine, int[] noPowerOddDivine, int[] burningOddDivine, int[] normalOddDivine) {
        int[] actualColor;
        if (actualSegment % 2 == 0) {
            if (!data.hasAllomanticPower(metal)) {
                actualColor = noPowerEvenDivine;
            } else if (data.isBurning(metal)) {
                actualColor = burningEvenDivine;
            } else {
                actualColor = normalEvenDivine;
            }
        } else{
            if (!data.hasAllomanticPower(metal)) {
                actualColor = noPowerOddDivine;
            } else if (data.isBurning(metal)) {
                actualColor = burningOddDivine;
            } else {
                actualColor = normalOddDivine;
            }
        }

        if (actualSegment  == 0) {
            buf.vertex(centerX,centerY,0).color(actualColor[0], actualColor[1], actualColor[2], actualColor[3]).endVertex();
        }
        return actualColor;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        toggleSelected();
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void tick() { // tick
        this.timeIn++;
    }

    @Override
    public boolean keyReleased(int keysym, int scancode, int modifiers) {
        if (ModKeyRegister.ALLOMANTIC_POWER_SELECTOR.matches(keysym,scancode)) {
            this.mc.setScreen(null);
            this.mc.mouseHandler.grabMouse();
            return true;
        }
        return super.keyReleased(keysym, scancode, modifiers);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (ModKeyRegister.ALLOMANTIC_POWER_SELECTOR.matchesMouse(button)) {
            this.mc.setScreen(null);
            this.mc.mouseHandler.grabMouse();
            return true;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    /**
     * Toggles the metal the mouse is currently over
     */
    private void toggleSelected() {
        if (this.slotSelected != -1) {
            MetalTagEnum mt ;

            if(this.list==1) {
                mt = internalMetals.get(this.slotSelected);
            }else if(this.list==2) {
                mt = externalMetals.get(this.slotSelected);
            }else if(this.list==3) {
                mt = divineMetals.get(this.slotSelected);
            }else{
                mt = null;
            }

            if (this.mc.player != null) {
                this.mc.player.getCapability(InvestedPlayerCapabilityRegister.PLAYER_CAP).ifPresent(data -> {
                    ClientUtils.toggleBurn(mt, data);
                    this.mc.player.playSound(SoundEvents.UI_BUTTON_CLICK.get(), 0.1F, 2.0F);
                });
            }
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

}
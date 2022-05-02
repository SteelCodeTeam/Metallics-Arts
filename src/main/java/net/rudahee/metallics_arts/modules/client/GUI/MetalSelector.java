package net.rudahee.metallics_arts.modules.client.GUI;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.client.ClientUtils;
import net.rudahee.metallics_arts.modules.client.KeyInit;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@OnlyIn(Dist.CLIENT)
public class MetalSelector extends Screen {


    private static final List<MetalsNBTData> internalMetals = Arrays.asList(MetalsNBTData.values()).stream().filter(
                    metal -> !metal.isExternal()
                            && !metal.isDivine()).collect (Collectors.toList());



    private static final List<MetalsNBTData> externalMetals = Arrays.asList(MetalsNBTData.values()).stream().filter(metal -> metal.isExternal() && !metal.isDivine()).collect(Collectors.toList());
    private static final List<MetalsNBTData> divineMetals = Arrays.asList(MetalsNBTData.values()).stream().filter(metal -> metal.isDivine()).collect(Collectors.toList());

    final Minecraft mc;
    int slotSelected = -1;
    int list =-1;
    int timeIn = 8;

    public MetalSelector() {
        super(new StringTextComponent("metallic_arts_selector"));
        this.mc = Minecraft.getInstance();
    }

    private static double mouseAngle(int centroX, int centroY, int mouseX, int mouseY) {
        return (MathHelper.atan2(mouseY - centroY, mouseX - centroX) + Math.PI * 2) % (Math.PI * 2);
    }

    private static double mouseDistance (int centroX, int centroY, int mouseX, int mouseY) {
        return Math.sqrt(Math.pow((mouseX-centroX),2)+Math.pow((mouseY-centroY),2));
    }

    @Override
    public void render(MatrixStack matrixStack, int mx, int my, float partialTicks) {
        super.render(matrixStack, mx, my, partialTicks);
        this.mc.player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{
            int centerX  = this.width / 2;
            int centerY  = this.height / 2;
            float internalRadio = this.height/5;
            float mediumRadio = (float) (internalRadio*1.5);
            float externalRadio = (float) (mediumRadio*1.25);

            double angle = mouseAngle(centerX,centerY , mx, my);
            double distance = mouseDistance(centerX,centerY,mx,my);

            int internalSegments = 8;
            float step = (float) Math.PI / 180;
            float degreesPerSegment  = (float) Math.PI * 2 / internalSegments ;

            float degreesExternal = (float) Math.PI * 2 / divineMetals.size();

            this.slotSelected = -1;


            Tessellator tess = Tessellator.getInstance();
            BufferBuilder buf = tess.getBuilder();

            RenderSystem.disableCull();
            RenderSystem.disableTexture();
            RenderSystem.enableBlend();
            RenderSystem.shadeModel(GL11.GL_FLAT);
            buf.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);


            //circulo interno
            for (int actualSegment=0; actualSegment<internalSegments;actualSegment++) {
                MetalsNBTData metal = internalMetals.get(actualSegment);
                boolean mouseInSector = data.hasAllomanticPower(metal) &&
                        (degreesPerSegment*actualSegment < angle && angle < degreesPerSegment  * (actualSegment  + 1))  && (distance<internalRadio);
                float radius = internalRadio;

                if (mouseInSector) {
                    this.slotSelected = actualSegment;
                    this.list=1;
                    radius *= 1.025f;
                }

                int gs = 0x40;
                if (actualSegment  % 2 == 0) {
                    gs += 0x19;
                }

                gs = (!data.hasAllomanticPower(metal) || data.getAllomanticAmount(metal) == 0) ? 0 : gs;

                int r = data.isBurning(metal) ? 0xFF : gs;
                int g = gs;
                int b = gs;
                int a = 0x99;

                if (actualSegment  == 0) {
                    buf.vertex(centerX,centerY,0).color(r, g, b, a).endVertex();
                }


                for (float v = 0; v < degreesPerSegment  + step / 2; v += step) {
                    float rad = v + actualSegment  * degreesPerSegment ;
                    float xp = centerX  + MathHelper.cos(rad) * radius;
                    float yp = centerY  + MathHelper.sin(rad) * radius;

                    if (v == 0) {
                        buf.vertex(xp, yp, 0).color(r, g, b, a).endVertex();
                    }
                    buf.vertex(xp, yp, 0).color(r, g, b, a).endVertex();
                }
            }


            //circulo intermedio
            for (int actualSegment  = 0; actualSegment  < internalSegments; actualSegment++) {
                MetalsNBTData metal = externalMetals.get(actualSegment);
                boolean mouseInSector = data.hasAllomanticPower(metal) &&
                        (degreesPerSegment*actualSegment<angle && angle < degreesPerSegment  * (actualSegment  + 1)) &&
                        (internalRadio<distance && distance<mediumRadio);
                float radius = mediumRadio;

                if (mouseInSector) {
                    this.slotSelected = actualSegment;
                    this.list=2;
                    radius *= 1.025f;
                }

                int gs = 0x40;
                if (actualSegment  % 2 == 0) {
                    gs += 0x19;
                }

                gs = (!data.hasAllomanticPower(metal) || data.getAllomanticAmount(metal) == 0) ? 0 : gs;

                int r = data.isBurning(metal) ? 0xFF : gs;
                int g = gs;
                int b = gs;
                int a = 0x99;

                if (actualSegment  == 0) {
                    buf.vertex(centerX,centerY,0).color(r, g, b, a).endVertex();
                }


                for (float v = 0; v < degreesPerSegment  + step / 2; v += step) {
                    float rad = v + actualSegment  * degreesPerSegment ;
                    float xp = centerX  + MathHelper.cos(rad) * radius;
                    float yp = centerY  + MathHelper.sin(rad) * radius;

                    if (v == 0) {
                        buf.vertex(xp, yp, 0).color(r, g, b, a).endVertex();
                    }
                    buf.vertex(xp, yp, 0).color(r, g, b, a).endVertex();
                }
            }

            //circulo externo
            for (int actualSegment  = 0; actualSegment  < divineMetals.size(); actualSegment++) {
                MetalsNBTData metal = divineMetals.get(actualSegment);
                boolean mouseInSector = data.hasAllomanticPower(metal) &&
                        (degreesPerSegment*actualSegment*2 < angle && angle < degreesPerSegment* (actualSegment  + 1))
                        && (mediumRadio<distance && distance<externalRadio);

                float radius = externalRadio;

                if (mouseInSector) {
                    this.slotSelected = actualSegment;
                    this.list=3;
                    radius *= 1.025f;
                }


                //desde aqui es dibujado de parte del circulo
                int gs = 0x40;
                if (actualSegment  % 2 == 0) {
                    gs += 0x19;
                }

                gs = (!data.hasAllomanticPower(metal) || data.getAllomanticAmount(metal) == 0) ? 0 : gs;

                int r = data.isBurning(metal) ? 0xFF : gs;
                int g = gs;
                int b = gs;
                int a = 0x99;

                if (actualSegment  == 0) {
                    buf.vertex(centerX,centerY,0).color(r, g, b, a).endVertex();
                }


                for (float v = 0; v < degreesPerSegment  + step/2; v += step) {
                    float rad = (v + actualSegment  * degreesPerSegment)*2 ; // (*2) DUPLICA EL TAMAÑO DE LOS ULTIMOS SELECTORES, VISUALMENTE
                    float xp = centerX  + MathHelper.cos(rad) * radius;
                    float yp = centerY  + MathHelper.sin(rad) * radius;

                    if (v == 0) {
                        buf.vertex(xp, yp, 0).color(r, g, b, a).endVertex();
                    }
                    buf.vertex(xp, yp, 0).color(r, g, b, a).endVertex();
                }
            }

            tess.end();

            RenderSystem.shadeModel(GL11.GL_FLAT);
            RenderSystem.enableTexture();

            //pintado interno
            for (int actualSegment  = 0; actualSegment  < internalSegments ; actualSegment ++) {
                MetalsNBTData metal = internalMetals.get(actualSegment);
                boolean mouseInSector = data.hasAllomanticPower(metal)
                        && (degreesPerSegment*actualSegment < angle && angle < degreesPerSegment*(actualSegment  + 1))
                        && (distance<internalRadio);
                float radius = internalRadio;
                if (mouseInSector) {
                    radius *= 1.025f;
                }

                float rad = (actualSegment  + 0.5f) * degreesPerSegment;
                float xp = centerX  + MathHelper.cos(rad) * radius;
                float yp = centerY  + MathHelper.sin(rad) * radius;

                float xsp = xp - 4;
                float ysp = yp;

                if (mouseInSector){
                    renderTooltip(matrixStack, new StringTextComponent(metal.getNameLower()),mx,my);
                }

                double mod = 0.8;
                int xdp = (int) ((xp - centerX ) * mod + centerX );
                int ydp = (int) ((yp - centerY ) * mod + centerY );

                this.mc.getEntityRenderDispatcher().textureManager.bind( new ResourceLocation(MetallicsArts.MOD_ID,"textures/gui/allomantic_symbols/"+metal.getNameLower()+"_symbol.png"));
                RenderSystem.color4f(1, 1, 1, 1);
                blit(matrixStack, xdp - 8, ydp - 8, 0, 0, 16, 16, 16, 16);

            }

            //pintado intermedio
            for (int actualSegment  = 0; actualSegment  < internalSegments ; actualSegment ++) {
                //MetalsNBTData metal = MetalsNBTData.getMetal(toMetalIndex(actualSegment));
                MetalsNBTData metal = externalMetals.get(actualSegment);
                boolean mouseInSector = data.hasAllomanticPower(metal) &&
                        (degreesPerSegment* actualSegment < angle && angle < degreesPerSegment  * (actualSegment  + 1))  &&
                        (internalRadio<distance && distance<mediumRadio);

                float radius = mediumRadio;
                if (mouseInSector) {
                    radius *= 1.025f;
                }


                float rad = (actualSegment  + 0.5f) * degreesPerSegment;
                float xp = centerX  + MathHelper.cos(rad) * radius;
                float yp = centerY  + MathHelper.sin(rad) * radius;

                float xsp = xp - 4;
                float ysp = yp;

                if (mouseInSector){
                    renderTooltip(matrixStack, new StringTextComponent(metal.getNameLower()),mx,my);
                }

                double mod = 0.8;
                int xdp = (int) ((xp - centerX ) * mod + centerX );
                int ydp = (int) ((yp - centerY ) * mod + centerY );



                this.mc.getEntityRenderDispatcher().textureManager.bind( new ResourceLocation(MetallicsArts.MOD_ID,"textures/gui/allomantic_symbols/"+metal.getNameLower()+"_symbol.png"));
                RenderSystem.color4f(1, 1, 1, 1);
                blit(matrixStack, xdp - 8, ydp - 8, 0, 0, 16, 16, 16, 16);

            }

            //pintado externo
            for (int actualSegment  = 0; actualSegment  < divineMetals.size() ; actualSegment ++) {
                MetalsNBTData metal = divineMetals.get(actualSegment);
                boolean mouseInSector = data.hasAllomanticPower(metal) &&
                        (degreesExternal*actualSegment < angle && angle < degreesExternal * (actualSegment  + 1))
                        && (mediumRadio<distance && distance<externalRadio);

                float radius = externalRadio;

                if (mouseInSector) {
                    radius *= 1.025f;
                }

                float rad = (actualSegment + 0.5f) * degreesExternal;
                float xp = centerX  + MathHelper.cos(rad) * radius;
                float yp = centerY  + MathHelper.sin(rad) * radius;

                if (mouseInSector){
                    renderTooltip(matrixStack, new StringTextComponent(metal.getNameLower()),mx,my);
                }

                double mod = 0.9;
                int xdp = (int) ((xp - centerX ) * mod + centerX );
                int ydp = (int) ((yp - centerY ) * mod + centerY );

                this.mc.getEntityRenderDispatcher().textureManager.bind( new ResourceLocation(MetallicsArts.MOD_ID,"textures/gui/allomantic_symbols/"+metal.getNameLower()+"_symbol.png"));
                RenderSystem.color4f(1, 1, 1, 1);
                blit(matrixStack, xdp - 8, ydp - 8, 0, 0, 16, 16, 16, 16);

            }

            RenderSystem.enableRescaleNormal();
            RenderSystem.enableBlend();
            RenderSystem.blendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
            RenderHelper.turnBackOn();

            RenderHelper.turnOff();
            RenderSystem.disableBlend();
            RenderSystem.disableRescaleNormal();


        });

    }

    /*
          int actualColor[] = new int[4];
          actualColor = new int[]{200, 200, 200, 200};
                if (actualSegment % 2 == 0) {
                    actualColor = new int[]{225, 225, 225, 220};
                }

                if (!cap.hasAllomanticPower(mt) || cap.getAllomanticPowerCount() <= 0) {
                    actualColor = new int[]{100, 100, 100, 100};
                }

                if (cap.isBurning(mt)) {
                    actualColor = new int[]{200, 150, 150, 200};
                }
                int gs = 0x40;
                if (actualSegment % 2 == 0) {
                    gs += 0x19;
                }

    }*/


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

        if (KeyInit.allomancy.matches(keysym,scancode)){
            this.mc.setScreen(null);
            this.mc.mouseHandler.grabMouse();
            return true;
        }
        return super.keyReleased(keysym, scancode, modifiers);
    }


    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (KeyInit.allomancy.matchesMouse(button)) {
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
            MetalsNBTData mt ;

            if(this.list==1){
                mt = internalMetals.get(this.slotSelected);
            }else if(this.list==2){
                mt = externalMetals.get(this.slotSelected);
            }else if(this.list==3){
                mt = divineMetals.get(this.slotSelected);
            }else{
                mt = null;
            }

            this.mc.player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {
                ClientUtils.toggleBurn(mt, data);
                this.mc.player.playSound(SoundEvents.UI_BUTTON_CLICK, 0.1F, 2.0F);
            });
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

}
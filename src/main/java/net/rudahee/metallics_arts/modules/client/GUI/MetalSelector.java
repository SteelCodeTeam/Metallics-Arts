package net.rudahee.metallics_arts.modules.client.GUI;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.rudahee.metallics_arts.modules.client.ClientUtils;
import net.rudahee.metallics_arts.modules.client.KeyInit;
import net.rudahee.metallics_arts.modules.data_player.DefaultInvestedPlayerData;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.enums.metals.Metal;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;

@OnlyIn(Dist.CLIENT)
public class MetalSelector extends Screen {

    final Minecraft mc;
    int slotSelected = -1;
    int timeIn = 0;

    public MetalSelector() {
        super(new StringTextComponent("allomancy_gui"));
        this.mc = Minecraft.getInstance();
    }

    @Override
    public void render(MatrixStack matrixStack, int mx, int my, float partialTicks) {
        super.render(matrixStack, mx, my, partialTicks);

        this.mc.player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{
            int x = this.width / 2;
            int y = this.height / 2;
            int maxRadius = 50;

            double angle = mouseAngle(x, y, mx, my);

            int segments = 8;
            float step = (float) Math.PI / 180;
            float degPer = (float) Math.PI * 2 / segments;

            this.slotSelected = -1;

            Tessellator tess = Tessellator.getInstance();
            BufferBuilder buf = tess.getBuilder();


            RenderSystem.disableCull();
            RenderSystem.disableTexture();
            RenderSystem.enableBlend();
            RenderSystem.shadeModel(GL11.GL_FLAT);
            buf.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);

            //circulo interior
            for (int seg=0;seg<segments;seg++){
                MetalsNBTData metal = MetalsNBTData.getMetal(toMetalIndex(seg));
                boolean mouseInSector = data.hasAllomanticPower(metal) && (degPer * seg < angle && angle < degPer * (seg+1));
                float radius = Math.max(0F, Math.min((this.timeIn + partialTicks - seg * 6F /segments) * 40F,maxRadius));
                if (mouseInSector){
                    this.slotSelected = seg;
                    radius*=1.025f;
                }

                int gs = 0x40;
                if (seg % 2 == 0) {
                    gs += 0x19;
                }

                gs = (!data.hasAllomanticPower(metal)|| data.getAllomanticAmount(metal)==0) ? 0 : gs;


                int r = data.isBurning(metal) ? 0xFF : gs;
                int g = gs;
                int b = gs;
                int a = 0x99;


                if (seg == 0) {
                    buf.vertex(x, y, 0).color(r, g, b, a).endVertex();
                }

                for (float v = 0; v < degPer + step / 2; v += step) {
                    float rad = v + seg * degPer;
                    float xp = x + MathHelper.cos(rad) * radius;
                    float yp = y + MathHelper.sin(rad) * radius;

                    if (v == 0) {
                        buf.vertex(xp, yp, 0).color(r, g, b, a).endVertex();
                    }
                    buf.vertex(xp, yp, 0).color(r, g, b, a).endVertex();
                }
            }
            //circulo intermedio
            for (int seg=0;seg<segments;seg++){
                MetalsNBTData metal = MetalsNBTData.getMetal(toMetalIndex(seg));
                boolean mouseInSector = data.hasAllomanticPower(metal) && (degPer * seg < angle && angle < degPer * (seg+1));
                float radius = Math.max(Math.min((this.timeIn + partialTicks - seg * 6F /segments) * 40F,maxRadius),Math.min((this.timeIn + partialTicks - seg * 6F /segments) * 40F,80) );
                if (mouseInSector){
                    this.slotSelected = seg;
                    radius*=1.025f;
                }

                int gs = 0x40;
                if (seg % 2 == 0) {
                    gs += 0x19;
                }

                gs = (!data.hasAllomanticPower(metal)|| data.getAllomanticAmount(metal)==0) ? 0 : gs;


                int r = data.isBurning(metal) ? 0xFF : gs;
                int g = gs;
                int b = gs;
                int a = 0x99;


                if (seg == 0) {
                    buf.vertex(x, y, 0).color(r, g, b, a).endVertex();
                }

                for (float v = 0; v < degPer + step / 2; v += step) {
                    float rad = v + seg * degPer;
                    float xp = x + MathHelper.cos(rad) * radius;
                    float yp = y + MathHelper.sin(rad) * radius;

                    if (v == 0) {
                        buf.vertex(xp, yp, 0).color(r, g, b, a).endVertex();
                    }
                    buf.vertex(xp, yp, 0).color(r, g, b, a).endVertex();
                }
            }

            //circulo externo
            for (int seg=0;seg<segments;seg++){
                MetalsNBTData metal = MetalsNBTData.getMetal(toMetalIndex(seg));
                boolean mouseInSector = data.hasAllomanticPower(metal) && (degPer * seg < angle && angle < degPer * (seg+1));
                float radius = Math.max(Math.min((this.timeIn + partialTicks - seg * 6F /segments) * 40F,80),Math.min((this.timeIn + partialTicks - seg * 6F /segments) * 40F,110) );
                if (mouseInSector){
                    this.slotSelected = seg;
                    radius*=1.025f;
                }

                int gs = 0x40;
                if (seg % 2 == 0) {
                    gs += 0x19;
                }

                gs = (!data.hasAllomanticPower(metal)|| data.getAllomanticAmount(metal)==0) ? 0 : gs;


                int r = data.isBurning(metal) ? 0xFF : gs;
                int g = gs;
                int b = gs;
                int a = 0x99;


                if (seg == 0) {
                    buf.vertex(x, y, 0).color(r, g, b, a).endVertex();
                }

                for (float v = 0; v < degPer + step / 2; v += step) {
                    float rad = v + seg * degPer;
                    float xp = x + MathHelper.cos(rad) * radius;
                    float yp = y + MathHelper.sin(rad) * radius;

                    if (v == 0) {
                        buf.vertex(xp, yp, 0).color(r, g, b, a).endVertex();
                    }
                    buf.vertex(xp, yp, 0).color(r, g, b, a).endVertex();
                }
            }





            tess.end();

            RenderSystem.shadeModel(GL11.GL_FLAT);
            RenderSystem.enableTexture();


            for (int seg = 0; seg < segments; seg++) {
                MetalsNBTData metal = MetalsNBTData.getMetal(toMetalIndex(seg));
                boolean mouseInSector = data.hasAllomanticPower(metal) && (degPer * seg < angle && angle < degPer * (seg + 1));
                float radius = Math.max(0F, Math.min((this.timeIn + partialTicks - seg * 6F /segments) * 40F,maxRadius));
                if (mouseInSector) {
                    radius *= 1.025f;
                }
                float rad = (seg + 0.5f) * degPer;
                float xp = x + MathHelper.cos(rad) * radius;
                float yp = y + MathHelper.sin(rad) * radius;

                float xsp = xp - 4;
                float ysp = yp;
                //String name = (mouseInSector ? TextFormatting.UNDERLINE : TextFormatting.RESET) + new TranslationTextComponent(METAL_LOCAL[toMetalIndex(seg)]).getString();
                //int width = this.mc.getEntityRenderDispatcher().getFont().width(name);

                if (xsp < x) {
                    xsp -= width - 8;
                }
                if (ysp < y) {
                    ysp -= 9;
                }
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


    private static double mouseAngle(int x, int y, int mx, int my) {
        return (MathHelper.atan2(my - y, mx - x) + Math.PI * 2) % (Math.PI * 2);
    }

    private static int toMetalIndex(int segment) {
        return (segment + 5) % MetalsNBTData.values().length;
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

    private void toggleSelected() {
        if (this.slotSelected != -1) {
            MetalsNBTData metalsNBTData = MetalsNBTData.getMetal(toMetalIndex(this.slotSelected));
            this.mc.player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {
                ClientUtils.toggleBurn(metalsNBTData, data);
                this.mc.player.playSound(SoundEvents.UI_BUTTON_CLICK, 0.1F, 2.0F);
            });
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }




}

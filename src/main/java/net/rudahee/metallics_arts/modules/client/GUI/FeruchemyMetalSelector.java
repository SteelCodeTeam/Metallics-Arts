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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.client.ClientUtils;
import net.rudahee.metallics_arts.modules.client.KeyInit;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.CallbackI;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@OnlyIn(Dist.CLIENT)
public class FeruchemyMetalSelector extends Screen {

    private static final java.util.List<MetalsNBTData> internalMetals = Arrays.asList(MetalsNBTData.values()).stream().filter(metal -> !metal.isExternal() && !metal.isDivine()).sorted(new ComparatorMetals()).collect(Collectors.toList());
    private static final java.util.List<MetalsNBTData> externalMetals = Arrays.asList(MetalsNBTData.values()).stream().filter(metal -> metal.isExternal() && !metal.isDivine()).sorted(new ComparatorMetals()).collect(Collectors.toList());
    private static final List<MetalsNBTData> divineMetals = Arrays.asList(MetalsNBTData.values()).stream().filter(metal -> metal.isDivine()).sorted(new ComparatorMetals()).collect(Collectors.toList());

    final Minecraft mc;
    int slotSelected = -1;
    int list =-1;
    int timeIn = 8;

    public FeruchemyMetalSelector() {
        super(new StringTextComponent("metallic_arts_feruchemic_selector"));

        this.mc = Minecraft.getInstance();
    }

    @Override
    public void render(MatrixStack matrixStack, int mx, int my, float partialTicks) {
        super.render(matrixStack, mx, my, partialTicks);
        this.mc.player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{


            Point center = new Point(this.width / 2,this.height / 2);

            Point mouse = new Point(mx,my);


            Point xPositivo = new Point((int) (center.x*1.2), center.y);
            Point xNegativo = new Point((int) (center.x-(xPositivo.x-center.x)), center.y);
            Point yPositivo = new Point(center.x, (int) (center.y-(xPositivo.x-center.x)));
            Point yNegativo = new Point(center.x,(int) (center.y+(xPositivo.x- center.x)));

            //extremos externos
            Point xPositivoExterno = new Point((int) (xPositivo.x+(xPositivo.x-center.x)), center.y);
            Point xNegativoExterno = new Point((int) (xNegativo.x)-(xPositivo.x- center.x), center.y);

            Point yNegativoExterno = new Point(center.x,(int) (yNegativo.y+(xPositivo.x- center.x)));
            Point yPositivoExterno = new Point(center.x,(int) (yPositivo.y-(xPositivo.x- center.x)));

            //diagonales internos
            Point intermedioXPosYNeg = new Point(xPositivo.x,yNegativo.y);
            Point intermedioXPosYPos = new Point(xPositivo.x,yPositivo.y);
            Point intermedioXNegYNeg = new Point(xNegativo.x,yNegativo.y);
            Point intermedioXNegYPos = new Point(xNegativo.x,yPositivo.y);

            //diagonales
            Point intermedioXPosYNegExterno = new Point(xPositivoExterno.x,yNegativoExterno.y);
            Point intermedioXPosYPosExterno = new Point(xPositivoExterno.x,yPositivoExterno.y);
            Point intermedioXNegYNegExterno = new Point(xNegativoExterno.x,yNegativoExterno.y);
            Point intermedioXNegYPosExterno = new Point(xNegativoExterno.x,yPositivoExterno.y);


            int[] rojo = new int[]{255, 0, 0, 100};
            int[] verde = new int[]{0, 255, 0, 100};
            int[] azul = new int[]{0, 0, 255, 100};

            int[] amarillo = new int[]{255, 165, 0, 100};
            int[] violeta = new int[]{120, 0, 150, 100};
            int[] rosa = new int[]{238, 130, 238, 100};


            Tessellator tess = Tessellator.getInstance();
            BufferBuilder buf = tess.getBuilder();
            BufferBuilder bufExterno = tess.getBuilder();

            RenderSystem.disableCull();
            RenderSystem.disableTexture();
            RenderSystem.enableBlend();
            RenderSystem.shadeModel(GL11.GL_FLAT);
            buf.begin(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION_COLOR);

            //selector

            pintar(buf,xPositivo,xPositivoExterno,intermedioXPosYNeg, violeta,MetalsNBTData.BRASS,mouse);
            pintar(buf,xPositivo,xPositivoExterno,intermedioXPosYPos, amarillo,MetalsNBTData.ZINC,mouse);

            pintar(buf,yPositivo,yPositivoExterno,intermedioXPosYPos, rosa,MetalsNBTData.IRON,mouse);
            pintar(buf,yPositivo,yPositivoExterno,intermedioXNegYPos, amarillo,MetalsNBTData.STEEL,mouse);

            pintar(buf,xNegativo,xNegativoExterno,intermedioXNegYPos, violeta,MetalsNBTData.CHROMIUM,mouse);
            pintar(buf,xNegativo,xNegativoExterno,intermedioXNegYNeg, rosa,MetalsNBTData.NICROSIL,mouse);

            pintar(buf,yNegativo,yNegativoExterno,intermedioXNegYNeg, rosa,MetalsNBTData.CADMIUM,mouse);
            pintar(buf,yNegativo,yNegativoExterno,intermedioXPosYNeg, amarillo,MetalsNBTData.BENDALLOY,mouse);


            pintar(buf,center,xPositivo,intermedioXPosYNeg, rojo,MetalsNBTData.BRONZE,mouse);
            pintar(buf,center,xPositivo,intermedioXPosYPos, verde,MetalsNBTData.COPPER,mouse);
            pintar(buf,center,intermedioXPosYPos,yPositivo, azul,MetalsNBTData.TIN,mouse);
            pintar(buf,center,yPositivo,intermedioXNegYPos, rojo,MetalsNBTData.PEWTER,mouse);
            pintar(buf,center,intermedioXNegYPos,xNegativo, verde,MetalsNBTData.DURALUMIN,mouse);
            pintar(buf,center,xNegativo,intermedioXNegYNeg, azul,MetalsNBTData.ALUMINUM,mouse);
            pintar(buf,center,intermedioXNegYNeg,yNegativo, rojo,MetalsNBTData.GOLD,mouse);
            pintar(buf,center,yNegativo,intermedioXPosYNeg, rosa,MetalsNBTData.ELECTRUM,mouse);

            ///



            tess.end();
            RenderSystem.shadeModel(GL11.GL_FLAT);
            RenderSystem.enableTexture();

            //pintado
            addpintado(matrixStack,xPositivo,xPositivoExterno,intermedioXPosYNeg,MetalsNBTData.BRASS,mouse);
            /*addpintado(matrixStack,xPositivo,xPositivoExterno,intermedioXPosYPos,MetalsNBTData.ZINC,mouse);

            addpintado(matrixStack,yPositivo,yPositivoExterno,intermedioXPosYPos,MetalsNBTData.IRON,mouse);
            addpintado(matrixStack,yPositivo,yPositivoExterno,intermedioXNegYPos,MetalsNBTData.STEEL,mouse);

            addpintado(matrixStack,xNegativo,xNegativoExterno,intermedioXNegYPos,MetalsNBTData.CHROMIUM,mouse);
            addpintado(matrixStack,xNegativo,xNegativoExterno,intermedioXNegYNeg,MetalsNBTData.NICROSIL,mouse);

            addpintado(matrixStack,yNegativo,yNegativoExterno,intermedioXNegYNeg,MetalsNBTData.CADMIUM,mouse);
            addpintado(matrixStack,yNegativo,yNegativoExterno,intermedioXPosYNeg,MetalsNBTData.BENDALLOY,mouse);


            addpintado(matrixStack,center,xPositivo,intermedioXPosYNeg,MetalsNBTData.BRONZE,mouse);
            addpintado(matrixStack,center,xPositivo,intermedioXPosYPos,MetalsNBTData.COPPER,mouse);
            addpintado(matrixStack,center,intermedioXPosYPos,yPositivo,MetalsNBTData.TIN,mouse);
            addpintado(matrixStack,center,yPositivo,intermedioXNegYPos,MetalsNBTData.PEWTER,mouse);
            addpintado(matrixStack,center,intermedioXNegYPos,xNegativo,MetalsNBTData.DURALUMIN,mouse);
            addpintado(matrixStack,center,xNegativo,intermedioXNegYNeg,MetalsNBTData.ALUMINUM,mouse);
            addpintado(matrixStack,center,intermedioXNegYNeg,yNegativo,MetalsNBTData.GOLD,mouse);
            addpintado(matrixStack,center,yNegativo,intermedioXPosYNeg,MetalsNBTData.ELECTRUM,mouse);*/


            RenderSystem.enableRescaleNormal();
            RenderSystem.enableBlend();
            RenderSystem.blendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
            RenderHelper.turnBackOn();



            RenderHelper.turnOff();
            RenderSystem.disableBlend();
            RenderSystem.disableRescaleNormal();

        });


    }

    public void addpintado(MatrixStack matrixStack, Point vertex1,Point vertex2,Point vertex3,MetalsNBTData metal, Point mouse){


        Point d = new Point(vertex2.x-vertex1.x,vertex2.y-vertex1.y);
        Point e = new Point(vertex3.x-vertex1.x,vertex3.y-vertex1.y);

        //Variable de ponderación a~b

        float div1 =(d.x * e.y - d.y * e.x);
        if (div1==0){
            div1=0.000001f;
        }
        float div2 = e.y;
        if (div2==0){
            div2=0.000001f;
        }
        int w1 = Math.round((e.x * (vertex1.y - mouse.y) + e.y * (mouse.x - vertex1.x)) / div1);

        //Variable de ponderación a~c
        int w2 = Math.round((mouse.y - vertex1.y - w1 * d.y) / div2);

        boolean inSelector=(w1 >= 0.0) && (w2 >= 0.0) && ((w1 + w2) <= 1.0);

        if (inSelector){
            renderTooltip(matrixStack, new StringTextComponent(metal.getNameLower()),mouse.x,mouse.y);
        }

        //this.mc.getEntityRenderDispatcher().textureManager.bind(new ResourceLocation(MetallicsArts.MOD_ID,"textures/gui/allomantic_symbols/"+metal.getNameLower()+"_symbol.png"));
        RenderSystem.color4f(1, 1, 1, 1);
        //blit(matrixStack, xdp - 8, ydp - 8, 0, 0, 16, 16, 16, 16);

    }


                                                    //a             b             c
    public void pintar (BufferBuilder buf, Point vertex1,Point vertex2,Point vertex3, int[] color,MetalsNBTData metal, Point mouse){

        Point d = new Point(vertex2.x-vertex1.x,vertex2.y-vertex1.y);
        Point e = new Point(vertex3.x-vertex1.x,vertex3.y-vertex1.y);

        //Variable de ponderación a~b

        int div1 =(d.x * e.y - d.y * e.x);
        if (div1==0){
            div1=1;
        }
        int div2 = e.y;
        if (div2==0){
            div2=1;
        }
        int w1 = (e.x * (vertex1.y - mouse.y) + e.y * (mouse.x - vertex1.x)) / div1;

        //Variable de ponderación a~c
        int w2 = (mouse.y - vertex1.y - w1 * d.y) / div2;

        boolean inSelector=(w1 >= 0.0) && (w2 >= 0.0) && ((w1 + w2) <= 1.0);

        /*if (inSelector){
            vertex1.x = (int) (vertex1.x*1.025f);
            vertex1.y = (int) (vertex1.y*1.025f);

            vertex2.x = (int) (vertex2.x*1.025f);
            vertex2.y = (int) (vertex2.y*1.025f);

            vertex3.x = (int) (vertex3.x*1.025f);
            vertex3.y = (int) (vertex3.y*1.025f);

        }*/
        buf.vertex(vertex1.x,vertex1.y,0).color(color[0],color[1],color[2],color[3]).endVertex();
        buf.vertex(vertex2.x,vertex2.y,0).color(color[0],color[1],color[2],color[3]).endVertex();
        buf.vertex(vertex3.x,vertex3.y,0).color(color[0],color[1],color[2],color[3]).endVertex();

        /*if ((w1 >= 0.0) && (w2 >= 0.0) && ((w1 + w2) <= 1.0)){
            renderTooltip(matrixStack, new StringTextComponent(metal.getNameLower()),mouse.x,mouse.y);
        }*/




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

        if (KeyInit.feruchemic.matches(keysym,scancode)){
            this.mc.setScreen(null);
            this.mc.mouseHandler.grabMouse();
            return true;
        }
        return super.keyReleased(keysym, scancode, modifiers);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (KeyInit.feruchemic.matchesMouse(button)) {
            this.mc.setScreen(null);
            this.mc.mouseHandler.grabMouse();
            return true;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }


    private void toggleSelected() {
        /*if (this.slotSelected != -1) {
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
        }*/
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }


}

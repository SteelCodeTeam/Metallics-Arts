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
import net.rudahee.metallics_arts.modules.data_player.IDefaultInvestedPlayerData;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.enums.metals.Metal;
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

    Point point1 = null;
    Point point2 = null;
    Point point3 = null;

    int tipoTemp =-1;
    boolean paridadTemp;

    MetalsNBTData metalTemp=null;


    int timeIn = 8;




    public FeruchemyMetalSelector() {
        super(new StringTextComponent("metallic_arts_feruchemic_selector"));

        this.mc = Minecraft.getInstance();
    }


    static int[] gris1 = new int[]{109, 109, 109, 100};
    static int[] gris2 = new int[]{127, 127, 127, 100};
    static int[] gris3 = new int[]{143, 143, 143, 100};

    static int[] celeste1 = new int[]{73, 180, 199, 100};
    static int[] celeste2 = new int[]{103, 195, 211, 100};
    static int[] celeste3 = new int[]{133, 207, 221, 100};

    static int[] verdiAzul1 = new int[]{108, 165, 155, 100};
    static int[] verdiAzul2 = new int[]{112, 187, 174, 100};
    static int[] verdiAzul3 = new int[]{128, 206, 196, 100};

    static int[] verde1 = new int[]{84, 142, 96, 100};
    static int[] verde2 = new int[]{101, 165, 115, 100};
    static int[] verde3 = new int[]{119, 173, 131, 100};

    @Override
    public void render(MatrixStack matrixStack, int mx, int my, float partialTicks) {
        super.render(matrixStack, mx, my, partialTicks);
        this.mc.player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{

            Point center = new Point(this.width / 2,this.height / 2);

            Point mouse = new Point(mx,my);


            Point xPositivo = new Point((int) (center.x*1.2), center.y);
            Point xNegativo = new Point(center.x-(xPositivo.x-center.x), center.y);
            Point yPositivo = new Point(center.x, center.y-(xPositivo.x-center.x));
            Point yNegativo = new Point(center.x, center.y+(xPositivo.x-center.x));

            //extremos externos
            Point xPositivoExterno = new Point(xPositivo.x+(xPositivo.x-center.x), center.y);
            Point xNegativoExterno = new Point(xNegativo.x-(xPositivo.x- center.x), center.y);

            Point yNegativoExterno = new Point(center.x,yNegativo.y+(xPositivo.x- center.x));
            Point yPositivoExterno = new Point(center.x,yPositivo.y-(xPositivo.x- center.x));

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


            int large = xPositivo.x-center.x;

            Tessellator tess = Tessellator.getInstance();
            BufferBuilder buf = tess.getBuilder();

            RenderSystem.disableCull();
            RenderSystem.disableTexture();
            RenderSystem.enableBlend();
            RenderSystem.shadeModel(GL11.GL_FLAT);
            buf.begin(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION_COLOR);

            //trazado

            pintar(buf,xPositivo,intermedioXPosYNeg,xPositivoExterno,MetalsNBTData.BRASS,mouse,2,true,data);
            pintar(buf,xPositivo,intermedioXPosYPos,xPositivoExterno,MetalsNBTData.ZINC,mouse,1,false,data);
            pintar(buf,yPositivo,yPositivoExterno,intermedioXPosYPos,MetalsNBTData.IRON,mouse,1,true,data);
            pintar(buf,yPositivo,yPositivoExterno,intermedioXNegYPos,MetalsNBTData.STEEL,mouse,0,false,data);
            pintar(buf,xNegativo,intermedioXNegYPos,xNegativoExterno,MetalsNBTData.CHROMIUM,mouse,0,true,data);
            pintar(buf,xNegativo,intermedioXNegYNeg,xNegativoExterno,MetalsNBTData.NICROSIL,mouse,3,false,data);
            pintar(buf,yNegativo,yNegativoExterno,intermedioXNegYNeg,MetalsNBTData.CADMIUM,mouse,3,true,data);
            pintar(buf,yNegativo,yNegativoExterno,intermedioXPosYNeg,MetalsNBTData.BENDALLOY,mouse,2,false,data);

            pintar(buf,xPositivo,intermedioXPosYNeg,center,MetalsNBTData.BRONZE,mouse,3,false,data);
            pintar(buf,xPositivo,intermedioXPosYPos,center,MetalsNBTData.COPPER,mouse,0,true,data);
            pintar(buf,yPositivo,center,intermedioXPosYPos,MetalsNBTData.TIN,mouse,2,false,data);
            pintar(buf,yPositivo,center,intermedioXNegYPos,MetalsNBTData.PEWTER,mouse,3,true,data);
            pintar(buf,xNegativo,intermedioXNegYPos,center,MetalsNBTData.DURALUMIN,mouse,1,false,data);
            pintar(buf,xNegativo,intermedioXNegYNeg,center,MetalsNBTData.ALUMINUM,mouse,2,true,data);
            pintar(buf,yNegativo,center,intermedioXNegYNeg,MetalsNBTData.GOLD,mouse,0,false,data);
            pintar(buf,yNegativo,center,intermedioXPosYNeg,MetalsNBTData.ELECTRUM,mouse,1,true,data);

            pintar(buf,intermedioXPosYNegExterno,new Point(intermedioXPosYNegExterno.x,intermedioXPosYNegExterno.y-large),new Point(intermedioXPosYNegExterno.x-large,intermedioXPosYNegExterno.y),MetalsNBTData.ATIUM,mouse,0,false,data);
            pintar(buf,intermedioXPosYPosExterno,new Point(intermedioXPosYPosExterno.x,intermedioXPosYPosExterno.y+large),new Point(intermedioXPosYPosExterno.x-large,intermedioXPosYPosExterno.y),MetalsNBTData.MALATIUM,mouse,3,true,data);
            pintar(buf,intermedioXNegYNegExterno,new Point(intermedioXNegYNegExterno.x,intermedioXNegYNegExterno.y-large),new Point(intermedioXNegYNegExterno.x+large,intermedioXNegYNegExterno.y),MetalsNBTData.ETTMETAL,mouse,1,false,data);
            pintar(buf,intermedioXNegYPosExterno,new Point(intermedioXNegYPosExterno.x,intermedioXNegYPosExterno.y+large),new Point(intermedioXNegYPosExterno.x+large,intermedioXNegYPosExterno.y),MetalsNBTData.LERASIUM,mouse,2,true,data);


            if(this.point1!=null&&this.point2!=null&&this.point3!=null&&this.tipoTemp!=-1){
                pintar(buf,this.point1,this.point2,this.point3,this.metalTemp,mouse,this.tipoTemp,this.paridadTemp,data);
            }

            tess.end();
            RenderSystem.shadeModel(GL11.GL_FLAT);
            RenderSystem.enableTexture();

            //pintado

            addpintado(matrixStack,xPositivo,intermedioXPosYNeg,xPositivoExterno,MetalsNBTData.BRASS,mouse);
            addpintado(matrixStack,xPositivo,intermedioXPosYPos,xPositivoExterno,MetalsNBTData.ZINC,mouse);
            addpintado(matrixStack,yPositivo,yPositivoExterno,intermedioXPosYPos,MetalsNBTData.IRON,mouse);
            addpintado(matrixStack,yPositivo,yPositivoExterno,intermedioXNegYPos,MetalsNBTData.STEEL,mouse);
            addpintado(matrixStack,xNegativo,intermedioXNegYPos,xNegativoExterno,MetalsNBTData.CHROMIUM,mouse);
            addpintado(matrixStack,xNegativo,intermedioXNegYNeg,xNegativoExterno,MetalsNBTData.NICROSIL,mouse);
            addpintado(matrixStack,yNegativo,yNegativoExterno,intermedioXNegYNeg,MetalsNBTData.CADMIUM,mouse);
            addpintado(matrixStack,yNegativo,yNegativoExterno,intermedioXPosYNeg,MetalsNBTData.BENDALLOY,mouse);

            addpintado(matrixStack,xPositivo,intermedioXPosYNeg,center,MetalsNBTData.BRONZE,mouse);
            addpintado(matrixStack,xPositivo,intermedioXPosYPos,center,MetalsNBTData.COPPER,mouse);
            addpintado(matrixStack,yPositivo,center,intermedioXPosYPos,MetalsNBTData.TIN,mouse);
            addpintado(matrixStack,yPositivo,center,intermedioXNegYPos,MetalsNBTData.PEWTER,mouse);
            addpintado(matrixStack,xNegativo,intermedioXNegYPos,center,MetalsNBTData.DURALUMIN,mouse);
            addpintado(matrixStack,xNegativo,intermedioXNegYNeg,center,MetalsNBTData.ALUMINUM,mouse);
            addpintado(matrixStack,yNegativo,center,intermedioXNegYNeg,MetalsNBTData.GOLD,mouse);
            addpintado(matrixStack,yNegativo,center,intermedioXPosYNeg,MetalsNBTData.ELECTRUM,mouse);

            addpintado(matrixStack,intermedioXPosYNegExterno,new Point(intermedioXPosYNegExterno.x,intermedioXPosYNegExterno.y-large),new Point(intermedioXPosYNegExterno.x-large,intermedioXPosYNegExterno.y),MetalsNBTData.ATIUM,mouse);
            addpintado(matrixStack,intermedioXPosYPosExterno,new Point(intermedioXPosYPosExterno.x,intermedioXPosYPosExterno.y+large),new Point(intermedioXPosYPosExterno.x-large,intermedioXPosYPosExterno.y),MetalsNBTData.MALATIUM,mouse);
            addpintado(matrixStack,intermedioXNegYNegExterno,new Point(intermedioXNegYNegExterno.x,intermedioXNegYNegExterno.y-large),new Point(intermedioXNegYNegExterno.x+large,intermedioXNegYNegExterno.y),MetalsNBTData.ETTMETAL,mouse);
            addpintado(matrixStack,intermedioXNegYPosExterno,new Point(intermedioXNegYPosExterno.x,intermedioXNegYPosExterno.y+large),new Point(intermedioXNegYPosExterno.x+large,intermedioXNegYPosExterno.y),MetalsNBTData.LERASIUM,mouse);

            if(this.point1!=null&&this.point2!=null&&this.point3!=null){
                addpintado(matrixStack,this.point1,this.point2,this.point3,this.metalTemp,mouse);
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

    public void addpintado(MatrixStack matrixStack, Point a,Point b,Point c,MetalsNBTData metal, Point mouse){

        Point vertex1 = new Point(a.x,a.y);
        Point vertex2 = new Point(b.x,b.y);
        Point vertex3 = new Point(c.x,c.y);

        boolean inSelector = pointInTriangle(mouse,vertex1,vertex2,vertex3);

        if (inSelector){
            renderTooltip(matrixStack, new StringTextComponent(metal.getNameLower()),mouse.x,mouse.y);
        }

        //this.mc.getEntityRenderDispatcher().textureManager.bind(new ResourceLocation(MetallicsArts.MOD_ID,"textures/gui/allomantic_symbols/"+metal.getNameLower()+"_symbol.png"));
        RenderSystem.color4f(1, 1, 1, 1);
        //blit(matrixStack, xdp - 8, ydp - 8, 0, 0, 16, 16, 16, 16);

    }


    public void pintar (BufferBuilder buf, Point a,Point b,Point c,MetalsNBTData metal, Point mouse,int tipo, boolean paridad, IDefaultInvestedPlayerData data){

        Point vertex1 = new Point(a.x,a.y);
        Point vertex2 = new Point(b.x,b.y);
        Point vertex3 = new Point(c.x,c.y);

        boolean inSelector = pointInTriangle(mouse,vertex1,vertex2,vertex3);

        if (inSelector) {
            this.slotSelected = metal.getIndex();
            if (tipo == 0) {
                vertex2.y = vertex2.y - 4;
                vertex3.x = vertex3.x - 4;
            } else if (tipo == 1) {
                vertex2.y = vertex2.y - 4;
                vertex3.x = vertex3.x + 4;
            } else if (tipo == 2) {
                vertex2.y = vertex2.y + 4;
                vertex3.x = vertex3.x + 4;
            } else if (tipo == 3) {
                vertex2.y = vertex2.y + 4;
                vertex3.x = vertex3.x - 4;
            }
            this.point1 = vertex1;
            this.point2 = vertex2;
            this.point3 = vertex3;
            this.tipoTemp = tipo;
            this.paridadTemp = paridad;
            this.metalTemp = metal;
        }

        int actualColor[];
        if(paridad){
            actualColor = new int[]{125, 125, 125, 255};

            if (!data.hasFeruchemicPower(metal)) { // || si no tiene equipada la mente de ese metal
                actualColor = new int[]{84, 91, 120, 255};
            }
            if(!data.getMetalMindEquiped(metal.getGroup())){
                actualColor = new int[]{220, 20, 0, 255};
            }

            if (data.isBurning(metal)) {//logica de almacenamiento y decante
                actualColor = new int[]{73, 180, 199, 255};
            }

        }else{
            actualColor = new int[]{109, 109, 109, 255};
            if (!data.hasFeruchemicPower(metal)) { // || si no tiene equipada la mente de ese metal
                actualColor = new int[]{103, 110, 140, 255};
            }
            if(!data.getMetalMindEquiped(metal.getGroup())){
                actualColor = new int[]{255, 0, 0, 255};
            }

            if (data.isBurning(metal)) {//logica de almacenamiento y decante
                actualColor = new int[]{103, 195, 211, 255};
            }
        }

        buf.vertex(a.x,a.y,0).color(actualColor[0],actualColor[1],actualColor[2],actualColor[3]).endVertex();
        buf.vertex(b.x,b.y,0).color(actualColor[0],actualColor[1],actualColor[2],actualColor[3]).endVertex();
        buf.vertex(c.x,c.y,0).color(actualColor[0],actualColor[1],actualColor[2],actualColor[3]).endVertex();


    }
    /*public void pintadoUnico (BufferBuilder buf, Point a,Point b,Point c,MetalsNBTData metal, Point mouse,int tipo, boolean paridad, IDefaultInvestedPlayerData data){

        Point vertex1 = new Point(a.x,a.y);
        Point vertex2 = new Point(b.x,b.y);
        Point vertex3 = new Point(c.x,c.y);

        boolean inSelector = pointInTriangle(mouse,vertex1,vertex2,vertex3);

        if (inSelector) {
            this.slotSelected = metal.getIndex();
        }

        int actualColor[];
        if(paridad){
            actualColor = new int[]{125, 125, 125, 255};

            if (!data.hasFeruchemicPower(metal)) { // || si no tiene equipada la mente de ese metal
                actualColor = new int[]{84, 91, 120, 255};
            }
            if(!data.getMetalMindEquiped(metal.getGroup())){
                actualColor = new int[]{220, 20, 0, 255};
            }

            if (data.isBurning(metal)) {//logica de almacenamiento y decante
                actualColor = new int[]{73, 180, 199, 255};
            }

        }else{
            actualColor = new int[]{109, 109, 109, 255};
            if (!data.hasFeruchemicPower(metal)) { // || si no tiene equipada la mente de ese metal
                actualColor = new int[]{103, 110, 140, 255};
            }
            if(!data.getMetalMindEquiped(metal.getGroup())){
                actualColor = new int[]{255, 0, 0, 255};
            }

            if (data.isBurning(metal)) {//logica de almacenamiento y decante
                actualColor = new int[]{103, 195, 211, 255};
            }
        }

        buf.vertex(a.x,a.y,0).color(actualColor[0],actualColor[1],actualColor[2],actualColor[3]).endVertex();
        buf.vertex(b.x,b.y,0).color(actualColor[0],actualColor[1],actualColor[2],actualColor[3]).endVertex();
        buf.vertex(c.x,c.y,0).color(actualColor[0],actualColor[1],actualColor[2],actualColor[3]).endVertex();
    }*/




    public float sign(Point p1, Point p2, Point p3) {
        return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
    }

    public boolean pointInTriangle(Point pt, Point v1, Point v2, Point v3) {
        boolean b1, b2, b3;
        b1 = sign(pt, v1, v2) < 0.0f;
        b2 = sign(pt, v2, v3) < 0.0f;
        b3 = sign(pt, v3, v1) < 0.0f;
        return ((b1 == b2) && (b2 == b3));
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        // 0: left click
        // 1: right click
        if(mouseButton == 1){
            toggleSelectedRight();
        }else if (mouseButton==0){
            toggleSelectedLeft();
        }

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

    private void toggleSelectedRight() {
        if (this.slotSelected != -1) {
            MetalsNBTData metal = MetalsNBTData.getMetal(this.slotSelected);
            this.mc.player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {
                ClientUtils.toggleStorage(metal, data);
                this.mc.player.playSound(SoundEvents.UI_BUTTON_CLICK, 0.1F, 2.0F);
            });
        }
    }

    private void toggleSelectedLeft() {
        if (this.slotSelected != -1) {
            MetalsNBTData metal = MetalsNBTData.getMetal(this.slotSelected);
            this.mc.player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {
                ClientUtils.toggleDecant(metal, data);
                this.mc.player.playSound(SoundEvents.UI_BUTTON_CLICK, 0.1F, 2.0F);
            });
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

}

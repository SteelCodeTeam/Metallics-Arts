package net.rudahee.metallics_arts.modules.logic.client.custom_guis.selectors;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModKeyRegister;
import net.rudahee.metallics_arts.utils.ComparatorMetals;
import net.rudahee.metallics_arts.utils.powers_utils.ClientUtils;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
public class FeruchemySelector extends Screen {

    private static final java.util.List<MetalTagEnum> internalMetals = Arrays.asList(MetalTagEnum.values()).stream().filter(metal -> !metal.isExternal() && !metal.isDivine()).sorted(new ComparatorMetals()).collect(Collectors.toList());
    private static final java.util.List<MetalTagEnum> externalMetals = Arrays.asList(MetalTagEnum.values()).stream().filter(metal -> metal.isExternal() && !metal.isDivine()).sorted(new ComparatorMetals()).collect(Collectors.toList());
    private static final List<MetalTagEnum> divineMetals = Arrays.asList(MetalTagEnum.values()).stream().filter(metal -> metal.isDivine()).sorted(new ComparatorMetals()).collect(Collectors.toList());

    private final Minecraft mc;

    private Player player;
    int slotSelected = -1;

    Point point1 = null;
    Point point2 = null;
    Point point3 = null;

    int tipoTemp =-1;
    boolean paridadTemp;

    MetalTagEnum metalTemp=null;

    int timeIn = 8;


    public FeruchemySelector() {
        super(Component.translatable("metallic_arts_feruchemic_selector"));
        this.mc = Minecraft.getInstance();
    }


    //static int[] noPowerPar = new int[] {70, 70, 80, 255};
    //static int[] noPowerImpar = new int[]{50, 50, 60, 255};

    static int[] normalPar = new int[]{84, 91, 120, 255};           //azules = Tiene poder inactivo y se expande si llevas la mente
    static int[] normalImpar = new int[]{103, 110, 140, 255};
    static int[] noMetalMIndPar = new int[] {125, 125, 125, 255};   // Grises = No tienes poder
    static int[] noMetalMIndImpar = new int[]{109, 109, 109, 255};
    static int[] isDecantingPar = new int[]{119, 173, 131, 255};    //Verde = ventaja/decantar
    static int[] isDecantingImpar = new int[]{84, 142, 96, 255};
    static int[] isStoragePar = new int[]{206, 160, 32, 255};       //Amarillo = desventaja/almacenar
    static int[] isStorageImpar = new int[]{235, 190, 68, 255};


    @Override
    public void render(PoseStack matrixStack, int mx, int my, float partialTicks) {
        super.render(matrixStack, mx, my, partialTicks);

        this.mc.player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data ->{

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


            Tesselator tess = Tesselator.getInstance();
            BufferBuilder buf = tess.getBuilder();

            RenderSystem.disableCull();
            RenderSystem.disableTexture();
            RenderSystem.enableBlend();
            RenderSystem.setShader(GameRenderer::getPositionColorShader);

            buf.begin(VertexFormat.Mode.TRIANGLES, DefaultVertexFormat.POSITION_COLOR);

            //trazado

            pintar(buf,xPositivo,intermedioXPosYNeg,xPositivoExterno, MetalTagEnum.BRASS,mouse,2,true,data);
            pintar(buf,xPositivo,intermedioXPosYPos,xPositivoExterno, MetalTagEnum.ZINC,mouse,1,false,data);
            pintar(buf,yPositivo,yPositivoExterno,intermedioXPosYPos, MetalTagEnum.IRON,mouse,1,true,data);
            pintar(buf,yPositivo,yPositivoExterno,intermedioXNegYPos, MetalTagEnum.STEEL,mouse,0,false,data);
            pintar(buf,xNegativo,intermedioXNegYPos,xNegativoExterno, MetalTagEnum.CHROMIUM,mouse,0,true,data);
            pintar(buf,xNegativo,intermedioXNegYNeg,xNegativoExterno, MetalTagEnum.NICROSIL,mouse,3,false,data);
            pintar(buf,yNegativo,yNegativoExterno,intermedioXNegYNeg, MetalTagEnum.CADMIUM,mouse,3,true,data);
            pintar(buf,yNegativo,yNegativoExterno,intermedioXPosYNeg, MetalTagEnum.BENDALLOY,mouse,2,false,data);

            pintar(buf,xPositivo,intermedioXPosYNeg,center, MetalTagEnum.BRONZE,mouse,3,false,data);
            pintar(buf,xPositivo,intermedioXPosYPos,center, MetalTagEnum.COPPER,mouse,0,true,data);
            pintar(buf,yPositivo,center,intermedioXPosYPos, MetalTagEnum.TIN,mouse,2,false,data);
            pintar(buf,yPositivo,center,intermedioXNegYPos, MetalTagEnum.PEWTER,mouse,3,true,data);
            pintar(buf,xNegativo,intermedioXNegYPos,center, MetalTagEnum.DURALUMIN,mouse,1,false,data);
            pintar(buf,xNegativo,intermedioXNegYNeg,center, MetalTagEnum.ALUMINUM,mouse,2,true,data);
            pintar(buf,yNegativo,center,intermedioXNegYNeg, MetalTagEnum.GOLD,mouse,0,false,data);
            pintar(buf,yNegativo,center,intermedioXPosYNeg, MetalTagEnum.ELECTRUM,mouse,1,true,data);

            pintar(buf,intermedioXPosYNegExterno,new Point(intermedioXPosYNegExterno.x,intermedioXPosYNegExterno.y-large),new Point(intermedioXPosYNegExterno.x-large,intermedioXPosYNegExterno.y), MetalTagEnum.ATIUM,mouse,0,false,data);
            pintar(buf,intermedioXPosYPosExterno,new Point(intermedioXPosYPosExterno.x,intermedioXPosYPosExterno.y+large),new Point(intermedioXPosYPosExterno.x-large,intermedioXPosYPosExterno.y), MetalTagEnum.MALATIUM,mouse,3,true,data);
            pintar(buf,intermedioXNegYNegExterno,new Point(intermedioXNegYNegExterno.x,intermedioXNegYNegExterno.y-large),new Point(intermedioXNegYNegExterno.x+large,intermedioXNegYNegExterno.y), MetalTagEnum.ETTMETAL,mouse,1,true,data);
            pintar(buf,intermedioXNegYPosExterno,new Point(intermedioXNegYPosExterno.x,intermedioXNegYPosExterno.y+large),new Point(intermedioXNegYPosExterno.x+large,intermedioXNegYPosExterno.y), MetalTagEnum.LERASIUM,mouse,2,false,data);


            if(this.point1!=null&&this.point2!=null&&this.point3!=null&&this.tipoTemp!=-1){
                if(pointInTriangle(mouse,this.point1,this.point2,this.point3)) {
                    if (data.getMetalMindEquiped(this.metalTemp.getGroup())) {
                        pintadoUnico(buf,this.point1,this.point2,this.point3,this.metalTemp,mouse,this.paridadTemp,data);
                    }
                }else {
                     this.point1 = null;
                     this.point2 = null;
                     this.point3 = null;
                     this.metalTemp=null;
                }
            }

            tess.end();

            RenderSystem.enableTexture();

            //pintado

            if (this.metalTemp != MetalTagEnum.BRASS) {
                addpintado(matrixStack, xPositivo, intermedioXPosYNeg, xPositivoExterno, MetalTagEnum.BRASS, mouse,data.hasFeruchemicPower(MetalTagEnum.BRASS));
            }
            if (this.metalTemp != MetalTagEnum.ZINC) {
                addpintado(matrixStack, xPositivo, intermedioXPosYPos, xPositivoExterno, MetalTagEnum.ZINC, mouse,data.hasFeruchemicPower(MetalTagEnum.ZINC));
            }
            if (this.metalTemp != MetalTagEnum.IRON) {
                addpintado(matrixStack, yPositivo, yPositivoExterno, intermedioXPosYPos, MetalTagEnum.IRON, mouse,data.hasFeruchemicPower(MetalTagEnum.IRON));
            }
            if (this.metalTemp != MetalTagEnum.STEEL) {
                addpintado(matrixStack, yPositivo, yPositivoExterno, intermedioXNegYPos, MetalTagEnum.STEEL, mouse,data.hasFeruchemicPower(MetalTagEnum.STEEL));
            }
            if (this.metalTemp != MetalTagEnum.CHROMIUM) {
                addpintado(matrixStack, xNegativo, intermedioXNegYPos, xNegativoExterno, MetalTagEnum.CHROMIUM, mouse,data.hasFeruchemicPower(MetalTagEnum.CHROMIUM));
            }
            if (this.metalTemp != MetalTagEnum.NICROSIL) {
                addpintado(matrixStack, xNegativo, intermedioXNegYNeg, xNegativoExterno, MetalTagEnum.NICROSIL, mouse,data.hasFeruchemicPower(MetalTagEnum.NICROSIL));
            }
            if (this.metalTemp != MetalTagEnum.CADMIUM) {
                addpintado(matrixStack, yNegativo, yNegativoExterno, intermedioXNegYNeg, MetalTagEnum.CADMIUM, mouse,data.hasFeruchemicPower(MetalTagEnum.CADMIUM));
            }
            if (this.metalTemp != MetalTagEnum.BENDALLOY) {
                addpintado(matrixStack, yNegativo, yNegativoExterno, intermedioXPosYNeg, MetalTagEnum.BENDALLOY, mouse,data.hasFeruchemicPower(MetalTagEnum.BENDALLOY));
            }
            if (this.metalTemp != MetalTagEnum.BRONZE) {
                addpintado(matrixStack, xPositivo, intermedioXPosYNeg, center, MetalTagEnum.BRONZE, mouse,data.hasFeruchemicPower(MetalTagEnum.BRONZE));
            }
            if (this.metalTemp != MetalTagEnum.COPPER) {
                addpintado(matrixStack, xPositivo, intermedioXPosYPos, center, MetalTagEnum.COPPER, mouse,data.hasFeruchemicPower(MetalTagEnum.COPPER));
            }
            if (this.metalTemp != MetalTagEnum.TIN) {
                addpintado(matrixStack, yPositivo, center, intermedioXPosYPos, MetalTagEnum.TIN, mouse,data.hasFeruchemicPower(MetalTagEnum.TIN));
            }
            if (this.metalTemp != MetalTagEnum.PEWTER) {
                addpintado(matrixStack, yPositivo, center, intermedioXNegYPos, MetalTagEnum.PEWTER, mouse,data.hasFeruchemicPower(MetalTagEnum.PEWTER));
            }
            if (this.metalTemp != MetalTagEnum.DURALUMIN) {
                addpintado(matrixStack, xNegativo, intermedioXNegYPos, center, MetalTagEnum.DURALUMIN, mouse,data.hasFeruchemicPower(MetalTagEnum.DURALUMIN));
            }
            if (this.metalTemp != MetalTagEnum.ALUMINUM) {
                addpintado(matrixStack, xNegativo, intermedioXNegYNeg, center, MetalTagEnum.ALUMINUM, mouse,data.hasFeruchemicPower(MetalTagEnum.ALUMINUM));
            }
            if (this.metalTemp != MetalTagEnum.GOLD) {
                addpintado(matrixStack, yNegativo, center, intermedioXNegYNeg, MetalTagEnum.GOLD, mouse,data.hasFeruchemicPower(MetalTagEnum.GOLD));
            }
            if (this.metalTemp != MetalTagEnum.ELECTRUM) {
                addpintado(matrixStack, yNegativo, center, intermedioXPosYNeg, MetalTagEnum.ELECTRUM, mouse,data.hasFeruchemicPower(MetalTagEnum.ELECTRUM));
            }
            if (this.metalTemp != MetalTagEnum.ATIUM) {
                addpintado(matrixStack, intermedioXPosYNegExterno,
                        new Point(intermedioXPosYNegExterno.x, intermedioXPosYNegExterno.y - large),
                        new Point(intermedioXPosYNegExterno.x - large, intermedioXPosYNegExterno.y), MetalTagEnum.ATIUM, mouse,data.hasFeruchemicPower(MetalTagEnum.ATIUM));
            }
            if (this.metalTemp != MetalTagEnum.MALATIUM) {
                addpintado(matrixStack, intermedioXPosYPosExterno,
                        new Point(intermedioXPosYPosExterno.x, intermedioXPosYPosExterno.y + large),
                        new Point(intermedioXPosYPosExterno.x - large, intermedioXPosYPosExterno.y), MetalTagEnum.MALATIUM, mouse,data.hasFeruchemicPower(MetalTagEnum.MALATIUM));
            }
            if (this.metalTemp != MetalTagEnum.ETTMETAL) {
                addpintado(matrixStack, intermedioXNegYNegExterno,
                        new Point(intermedioXNegYNegExterno.x, intermedioXNegYNegExterno.y - large),
                        new Point(intermedioXNegYNegExterno.x + large, intermedioXNegYNegExterno.y), MetalTagEnum.ETTMETAL, mouse,data.hasFeruchemicPower(MetalTagEnum.ETTMETAL));
            }
            if (this.metalTemp != MetalTagEnum.LERASIUM) {
                addpintado(matrixStack, intermedioXNegYPosExterno,
                        new Point(intermedioXNegYPosExterno.x, intermedioXNegYPosExterno.y + large),
                        new Point(intermedioXNegYPosExterno.x + large, intermedioXNegYPosExterno.y), MetalTagEnum.LERASIUM, mouse,data.hasFeruchemicPower(MetalTagEnum.LERASIUM));
            }
            if (this.point1 != null && this.point2 != null && this.point3 != null) {
                addpintado(matrixStack, this.point1, this.point2, this.point3, this.metalTemp, mouse,data.hasFeruchemicPower(this.metalTemp));
            }

            RenderSystem.enableBlend();
            RenderSystem.blendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
            RenderSystem.disableBlend();
        });
    }

    public void addpintado(PoseStack matrixStack, Point a, Point b, Point c, MetalTagEnum metal, Point mouse, boolean hasPower){

        Point vertex1 = new Point(a.x,a.y);
        Point vertex2 = new Point(b.x,b.y);
        Point vertex3 = new Point(c.x,c.y);

        Point baticenter = baticentro(vertex1,vertex2,vertex3);
        boolean inSelector = pointInTriangle(mouse,vertex1,vertex2,vertex3);

        if (hasPower) {
            if (inSelector){
                renderTooltip(matrixStack, Component.translatable("metallics_arts.metal_translate."+metal.getNameLower()),mouse.x,mouse.y);
            }
        }

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0,new ResourceLocation(MetallicsArts.MOD_ID,"textures/gui/feruchemic_symbols/"+metal.getNameLower()+"_symbol.png"));


        blit(matrixStack, baticenter.x-8, baticenter.y-8, 0, 0, 16, 16, 16, 16);

    }

    public Point baticentro(Point vertex1,Point vertex2,Point vertex3) {
        return new Point(((vertex1.x+vertex2.x+vertex3.x)/3),((vertex1.y+vertex2.y+vertex3.y)/3));
    }

    public void pintar(BufferBuilder buf, Point a, Point b, Point c, MetalTagEnum metal, Point mouse, int tipo, boolean paridad, IInvestedPlayerData data){

        Point vertex1 = new Point(a.x,a.y);
        Point vertex2 = new Point(b.x,b.y);
        Point vertex3 = new Point(c.x,c.y);

        boolean inSelector = pointInTriangle(mouse,vertex1,vertex2,vertex3);
        this.slotSelected = metal.getIndex();

        if (inSelector && data.getMetalMindEquiped(metal.getGroup()) && data.hasFeruchemicPower(metal)) {
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

        int[] actualColor;

        if(paridad) {
            if (!data.hasFeruchemicPower(metal)) {
                actualColor = noMetalMIndPar;
            } else if(data.isStoring(metal)){
                actualColor = isStoragePar;
            } else if (data.isTapping(metal)){
                actualColor = isDecantingPar;
            } else {
                actualColor = normalPar;    //Tiene poder, pero no lo esta usando
            }
        } else{
            if (!data.hasFeruchemicPower(metal)) {
                actualColor = noMetalMIndImpar;
            } else if(data.isStoring(metal)){
                actualColor = isStorageImpar;
            } else if (data.isTapping(metal)){
                actualColor = isDecantingImpar;
            } else {
                actualColor = normalImpar;  //Tiene poder, pero no lo esta usando
            }
        }

        buf.vertex(a.x,a.y,0).color(actualColor[0],actualColor[1],actualColor[2],actualColor[3]).endVertex();
        buf.vertex(b.x,b.y,0).color(actualColor[0],actualColor[1],actualColor[2],actualColor[3]).endVertex();
        buf.vertex(c.x,c.y,0).color(actualColor[0],actualColor[1],actualColor[2],actualColor[3]).endVertex();


    }
    public void pintadoUnico (BufferBuilder buf, Point a, Point b, Point c, MetalTagEnum metal, Point mouse, boolean paridad, IInvestedPlayerData data){

        Point vertex1 = new Point(a.x,a.y);
        Point vertex2 = new Point(b.x,b.y);
        Point vertex3 = new Point(c.x,c.y);

        boolean inSelector = pointInTriangle(mouse,vertex1,vertex2,vertex3);

        if (inSelector) {
            this.slotSelected = metal.getIndex();
        }

        int[] actualColor;
        if(paridad){
            if (!data.hasFeruchemicPower(metal)) {
                actualColor = noMetalMIndPar;
            }else if(data.isStoring(metal)){
                actualColor = isStoragePar;
            }else if (data.isTapping(metal)){
                actualColor = isDecantingPar;
            }else {
                actualColor = normalPar;
            }

        }else{
            if (!data.hasFeruchemicPower(metal)) {
                actualColor = noMetalMIndImpar;
            }else if(data.isStoring(metal)){
                actualColor = isStorageImpar;
            }else if (data.isTapping(metal)){
                actualColor = isDecantingImpar;
            }else {
                actualColor = normalImpar;
            }
        }

        buf.vertex(a.x,a.y,0).color(actualColor[0],actualColor[1],actualColor[2],actualColor[3]).endVertex();
        buf.vertex(b.x,b.y,0).color(actualColor[0],actualColor[1],actualColor[2],actualColor[3]).endVertex();
        buf.vertex(c.x,c.y,0).color(actualColor[0],actualColor[1],actualColor[2],actualColor[3]).endVertex();

        this.timeIn++;
    }


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
    public boolean keyReleased(int keysym, int scancode, int modifiers) {
        if (ModKeyRegister.FERUCHEMIC_POWER_SELECTOR.matches(keysym,scancode)){
            this.mc.setScreen(null);
            this.mc.mouseHandler.grabMouse();
            return true;
        }
        return super.keyReleased(keysym, scancode, modifiers);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (ModKeyRegister.FERUCHEMIC_POWER_SELECTOR.matchesMouse(button)) {
            this.mc.setScreen(null);
            this.mc.mouseHandler.grabMouse();
            return true;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    private void toggleSelectedRight() {
        if (this.slotSelected != -1) {
            MetalTagEnum metal = MetalTagEnum.getMetal(this.slotSelected);
            this.mc.player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data -> {
                ClientUtils.toggleStorage(metal, data, this.mc.player);
                this.mc.player.playSound(SoundEvents.UI_BUTTON_CLICK, 0.1F, 2.0F);
            });
        }
    }

    private void toggleSelectedLeft() {
        if (this.slotSelected != -1) {
            MetalTagEnum metal = MetalTagEnum.getMetal(this.slotSelected);
            this.mc.player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data -> {
                ClientUtils.toggleDecant(metal, data,this.mc.player);
                this.mc.player.playSound(SoundEvents.UI_BUTTON_CLICK, 0.1F, 2.0F);
            });
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

}

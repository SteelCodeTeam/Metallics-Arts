package net.rudahee.metallics_arts.modules.logic.client.custom_guis.selectors;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.utils.gui.Square;

import java.awt.*;

public class HemalurgySelector extends Screen {

    final Minecraft mc;
    private Player player;
    private static final ResourceLocation FRONT_OVERLAY_PNG = new ResourceLocation(MetallicsArts.MOD_ID, "textures/gui/hemalurgy_table_front.png");
    private static final Square SIZE_GUI = new Square(new Point(0,0), new Point(0, 273), new Point(191, 0), new Point(253, 243));

    public HemalurgySelector() {
        super(Component.literal("holiwi"));
        this.mc = Minecraft.getInstance();
        player = mc.player;
    }


    @Override
    public void render(PoseStack poseStack, int mx, int my, float partialTicks) {


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

        super.renderBackground(poseStack);


        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, FRONT_OVERLAY_PNG);
        int x = (width - SIZE_GUI.getBottomRight().x) / 2;
        int y = (height - SIZE_GUI.getBottomRight().y) / 2;

        blit(poseStack, x, y, SIZE_GUI.getTopLeft().x, SIZE_GUI.getTopLeft().y, SIZE_GUI.getBottomRight().x, SIZE_GUI.getBottomRight().y);

        //super.render();
    }

}

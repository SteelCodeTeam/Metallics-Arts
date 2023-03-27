package net.rudahee.metallics_arts.utils;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import org.lwjgl.opengl.GL11;

/**
 * A utility class containing methods for drawing various graphical elements
 * within the game world. This class should only be used on the client side.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
@OnlyIn(Dist.CLIENT)
public class DrawUtils {

    /**
     * Restores the rendering state after certain rendering operations.
     * This method should only be called on the client side.
     *
     * @param stack A {@link PoseStack} object to be used for restoring the rendering state.
     */
    @OnlyIn(Dist.CLIENT)
    public static void teardownPoseStack(PoseStack stack) {
        stack.popPose();
        RenderSystem.applyModelViewMatrix();

        RenderSystem.disableBlend();
        RenderSystem.enablePolygonOffset();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);
        RenderSystem.enableCull();
        RenderSystem.enableTexture();
    }

    /**
     * Configures the rendering state for specific rendering operations.
     * This method should only be called on the client side.
     *
     * @param event A {@link RenderLevelStageEvent} object containing information about the current render stage.
     * @param minecraft A {@link Minecraft} instance representing the client-side Minecraft game.
     *
     * @return A {@link PoseStack} object configured for the specified rendering operations.
     */
    @OnlyIn(Dist.CLIENT)
    public static PoseStack setupPoseStack(final RenderLevelStageEvent event, Minecraft minecraft) {
        RenderSystem.setShader(GameRenderer::getRendertypeLinesShader);
        RenderSystem.disableTexture();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.disableCull();
        RenderSystem.enableBlend();
        RenderSystem.disablePolygonOffset();
        RenderSystem.defaultBlendFunc();

        PoseStack stack = event.getPoseStack();
        stack.pushPose();
        Vec3 view = minecraft.cameraEntity.getEyePosition(event.getPartialTick());
        stack.translate(-view.x, -view.y, -view.z);
        RenderSystem.applyModelViewMatrix();
        return stack;
    }

    /**
     * Checks if the user's system has an ATI graphics card installed.
     * This method should only be called on the client side.
     *
     * @return {@code true} if the system has an ATI graphics card, {@code false} otherwise.
     */
    @OnlyIn(Dist.CLIENT)
    public static boolean haveATIGraphicCard() {
        return GL11.glGetString(GL11.GL_VENDOR).contains("ATI");
    }

    /**
     * Patches the rendering settings for systems with ATI graphics cards.
     * This method should only be called on the client side.
     *
     * This method modifies the rendering pipeline by disabling textures and depth testing,
     * setting the depth mask to false, setting the polygon mode to render lines,
     * and enabling blending with a specified blend function.
     */
    @OnlyIn(Dist.CLIENT)
    public static void patchPoseForATIGraphicCards() {
        RenderSystem.disableTexture();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.polygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.enableBlend();
    }

    /**
     * Draws a colored line between two 3D points in the world.
     *
     * @param stack  A PoseStack instance for rendering the line.
     * @param player A Vec3 representing the starting point of the line (e.g., player's position).
     * @param dest   A Vec3 representing the destination point of the line.
     * @param width  The width of the line in pixels.
     * @param r      The red component of the line color (0.0f to 1.0f).
     * @param g      The green component of the line color (0.0f to 1.0f).
     * @param b      The blue component of the line color (0.0f to 1.0f).
     */
    public static void drawMetalLine(PoseStack stack, Vec3 player, Vec3 dest, float width, float r, float g, float b) {

        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder builder = tessellator.getBuilder();

        builder.begin(VertexFormat.Mode.LINES, DefaultVertexFormat.POSITION_COLOR);
        Matrix4f matrix4f = stack.last().pose();
        builder.vertex(matrix4f, (float) player.x, (float) player.y, (float) player.z).color(r, g, b, 0.6f).endVertex();
        builder.vertex(matrix4f, (float) dest.x, (float) dest.y, (float) dest.z).color(r, g, b, 0.6f).endVertex();
        RenderSystem.lineWidth(width);

        tessellator.end();
    }


}

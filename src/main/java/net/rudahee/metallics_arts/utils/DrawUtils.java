package net.rudahee.metallics_arts.utils;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Comparator;

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

    // new methods

    public static Matrix4f setUpForDrawingQuadLines(final RenderLevelStageEvent event){
        /**
         * @param event the levelRenderEvent.
         * @return the matrix that will translate in game vertices to on-screen vertices
         */
        RenderSystem.setShader(GameRenderer::getRendertypeGlintTranslucentShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();

        Vec3 view = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
        PoseStack stack = event.getPoseStack();
        stack.pushPose();
        stack.translate(-view.x(), -view.y(), -view.z());
        Matrix4f translationMatrix = stack.last().pose().copy();
        stack.popPose();
        return translationMatrix;
    }

    private static void addVertexToBuffer(BufferBuilder buffer, Matrix4f translationMatrix, Vec3 vertex, float uv1, float uv2){
        buffer.vertex(translationMatrix, (float)vertex.x, (float)vertex.y, (float)vertex.z).uv(uv1, uv2).endVertex();
    }

    private static void drawInOrder(BufferBuilder buffer, Matrix4f translationMatrix, Vec3 v1, Vec3 v2, Vec3 v3, Vec3 v4, double frame, double spriteLength, double columnWidthFraction, String key){
        switch (key) {
            case "pH1" -> {
                addVertexToBuffer(buffer, translationMatrix, v1, (float) (columnWidthFraction * frame), 0);
                addVertexToBuffer(buffer, translationMatrix, v2, (float) ((columnWidthFraction * frame) + (columnWidthFraction * 0.5)), 0);
                addVertexToBuffer(buffer, translationMatrix, v3, (float) ((columnWidthFraction * frame) + (columnWidthFraction * 0.5)), (float) spriteLength);
                addVertexToBuffer(buffer, translationMatrix, v4, (float) (columnWidthFraction * frame), (float) spriteLength);
            }
            case "pV1" -> {
                addVertexToBuffer(buffer, translationMatrix, v1, (float) (columnWidthFraction * frame), 0);
                addVertexToBuffer(buffer, translationMatrix, v2, (float) ((columnWidthFraction * frame) + (columnWidthFraction * 0.5)), 0);
                addVertexToBuffer(buffer, translationMatrix, v3, (float) ((columnWidthFraction * frame) + (columnWidthFraction * 0.5)), (float) spriteLength);
                addVertexToBuffer(buffer, translationMatrix, v4, (float) (columnWidthFraction * frame), (float) spriteLength);
            }
            case "pV2" -> {
                addVertexToBuffer(buffer, translationMatrix, v1, (float) (columnWidthFraction * frame), 0);
                addVertexToBuffer(buffer, translationMatrix, v2, (float) ((columnWidthFraction * frame) + (columnWidthFraction * 0.5)), 0);
                addVertexToBuffer(buffer, translationMatrix, v3, (float) ((columnWidthFraction * frame) + (columnWidthFraction * 0.5)), (float) spriteLength);
                addVertexToBuffer(buffer, translationMatrix, v4, (float) (columnWidthFraction * frame), (float) spriteLength);
            }
            case "pH2" -> {
                addVertexToBuffer(buffer, translationMatrix, v1, (float) ((columnWidthFraction * frame) + columnWidthFraction), 0);
                addVertexToBuffer(buffer, translationMatrix, v2, (float) ((columnWidthFraction * frame) + (columnWidthFraction * 0.5)), 0);
                addVertexToBuffer(buffer, translationMatrix, v3, (float) ((columnWidthFraction * frame) + (columnWidthFraction * 0.5)), (float) spriteLength);
                addVertexToBuffer(buffer, translationMatrix, v4, (float) ((columnWidthFraction * frame) + columnWidthFraction), (float) spriteLength);
            }
        }
    }

    public static void drawMetalQuadLines(int tick, Vec3 viewPosition, Matrix4f translationMatrix, Vec3 target, Vec3 source, double scale, ResourceLocation texture, int numberOfFrames, int columnWidth, int columnHeight) {
        /**
         * draw a metal line from source to target with the given texture
         *
         */

        // calculate the vertices
        Vec3 sourceTargetVector = target.subtract(source);
        Vec3 sourceTargetDirection = sourceTargetVector.normalize();
        Vec3 horizontalDependent = sourceTargetDirection.cross(new Vec3(0.0,1.0,0.0)).normalize();
        if (horizontalDependent.length() == 0){
            horizontalDependent = sourceTargetVector.subtract(sourceTargetDirection.scale(0.001)).cross(new Vec3(0.0,1.0,0.0)).normalize();
        }
        Vec3 verticalDependent = sourceTargetDirection.cross(horizontalDependent).normalize();

        Vec3 pH1 = source.add(horizontalDependent.add(verticalDependent).scale(scale));
        Vec3 pH2 = source.subtract(horizontalDependent.add(verticalDependent).scale(scale));
        Vec3 pV1 = source.add(verticalDependent.subtract(horizontalDependent).scale(scale));
        Vec3 pV2 = source.subtract(verticalDependent.subtract(horizontalDependent).scale(scale));

        // set up texture and animation
        float aspectRatio = (float)columnHeight/(float)columnWidth;
        double sourceTargetDistance = sourceTargetVector.length();
        double spriteLength =  sourceTargetDistance / ((horizontalDependent.add(verticalDependent).scale(scale).length()*2)*aspectRatio);
        double columnWidthFraction = (float)1 / (float)numberOfFrames;
        double frame = tick % numberOfFrames;
        RenderSystem.setShaderTexture(0, texture);

        // set up a way to reorder the rendering of the quads depending on camera position
        ArrayList<Tuple<String, Vec3>> quadList = new ArrayList<>();
        quadList.add(new Tuple<>("pH1", pH1));
        quadList.add(new Tuple<>("pH2", pH2));
        quadList.add(new Tuple<>("pV1", pV1));
        quadList.add(new Tuple<>("pV2", pV2));

        // get teselator and draw quads
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.getBuilder();
        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

        for (Tuple<String, Vec3> tup : quadList.stream().sorted(Comparator.comparingDouble(vecTup -> -1*(vecTup.getB().subtract(viewPosition).length()))).toList()){
            drawInOrder(buffer, translationMatrix, tup.getB(), source, target, tup.getB().add(sourceTargetVector), frame, spriteLength, columnWidthFraction, tup.getA());
        }

        tesselator.end();
    }


}

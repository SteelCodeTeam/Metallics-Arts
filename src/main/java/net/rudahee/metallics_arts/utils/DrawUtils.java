package net.rudahee.metallics_arts.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import org.joml.Matrix4f;

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
     * Draws a colored line between two 3D points in the world.
     *
     * @param stack  A PoseStack instance for rendering the line.
     * @param player A Vec3 representing the starting point of the line (e.g., player's position).
     * @param dest   A Vec3 representing the destination point of the line.
     * @param width  The width of the line in pixels.
     * @param r      The red component of the line color (0.0f to 1.0f).
     * @param g      The green component of the line color (0.0f to 1.0f).
     * @param b      The blue component of the line color (0.0f to 1.0f).
     *
     * @deprecated
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

    public static Matrix4f setUpForDrawingQuadLines(final RenderLevelStageEvent event) {
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

        Matrix4f translationMatrix = stack.last().pose();
        stack.popPose();
        return translationMatrix;
    }

    private static void addVertexToBuffer(BufferBuilder buffer, Matrix4f translationMatrix, Vec3 vertex, float uv1, float uv2) {
        /**
         * add a point to the specified BufferBuilder
         */
        buffer.vertex(translationMatrix, (float)vertex.x, (float)vertex.y, (float)vertex.z).uv(uv1, uv2).endVertex();
    }

    private static void drawInOrder(BufferBuilder buffer, Matrix4f translationMatrix, Vec3 v1, Vec3 v2, Vec3 v3, Vec3 v4, double frame, double spriteLength, double columnWidthFraction, String key) {
        /**
         * @param buffer BufferBuilder for rendering
         * @param translationMatrix Matrix4f representing the transformation from world coordinates to on-screen coordinates
         * @param v1 first vertex of the quad
         * @param v2 second vertex of the quad
         * @param v3 third vertex of the quad
         * @param v4 fourth vertex of the quad
         * @param frame double controlling which frame is displayed
         * @param spriteLength the length of the line
         * @param columnWidthFraction
         * @param key determines which quad is drawn
         *
         * render the quads in order depending on player camera position
         */
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
         * @param tick integer representing the current world tick, used to calculate the current frame.
         * @param viewPosition the current position of player's camera
         * @param transformationMatrix Matrix4f representing the transformation from world to screen coordinates
         * @param target Vec3 representing a point in the world to which the line will go to
         * @param source Vec3 representing a point in the world from which line will originate
         * @param scale controlls the width of the lines
         * @param texture ResourceLocation pointing to the line texture
         * @param numberOfFrames number of frames in the texture
         * @param columnWidth width of a single frame
         * @param columnHeight height of a single frame
         */

        // calculate the vertices
        Vec3 sourceTargetVector = target.subtract(source);
        Vec3 sourceTargetDirection = sourceTargetVector.normalize();
        Vec3 horizontalDependent = sourceTargetDirection.cross(new Vec3(0.0,1.0,0.0)).normalize();
        if (horizontalDependent.length() == 0) {
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

        for (Tuple<String, Vec3> tup : quadList.stream().sorted(Comparator.comparingDouble(vecTup -> -1*(vecTup.getB().subtract(viewPosition).length()))).toList()) {
            drawInOrder(buffer, translationMatrix, tup.getB(), source, target, tup.getB().add(sourceTargetVector), frame, spriteLength, columnWidthFraction, tup.getA());
        }

        tesselator.end();
    }


}

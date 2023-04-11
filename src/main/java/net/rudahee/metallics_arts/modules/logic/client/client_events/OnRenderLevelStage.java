package net.rudahee.metallics_arts.modules.logic.client.client_events;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.LinesColorEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.error_handling.messages.ErrorTypes;
import net.rudahee.metallics_arts.utils.DrawUtils;
import net.rudahee.metallics_arts.utils.FoundNearbyMetalUtils;
import net.rudahee.metallics_arts.utils.powers_utils.MetalBlockUtils;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@OnlyIn(Dist.CLIENT)
public class OnRenderLevelStage {

    static GlobalPos respawnPos;

    @OnlyIn(Dist.CLIENT)
    public static void onRenderLevelStage(RenderLevelStageEvent event, Minecraft minecraft, @Nullable Player player, @Nullable IInvestedPlayerData capability) throws PlayerException {

        // lines are only rendered after translucent blocks
        if ((event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) || minecraft.options.getCameraType().isMirrored()) {
            return;
        }

        if (player == null) {
            throw new PlayerException(ErrorTypes.PLAYER_ERROR);
        }
        if (capability == null) {
            throw new PlayerException(ErrorTypes.PLAYER_CAPABILITY_ERROR);
        }

        if (!player.isAlive()) {
            throw new PlayerException(ErrorTypes.PLAYER_DEAD_ERROR);
        }

        if (!capability.isInvested()) {
            return;
        }

        Matrix4f transformationMatrix = DrawUtils.setUpForDrawingQuadLines(event);

        if (capability.isBurning(MetalTagEnum.IRON) || capability.isBurning(MetalTagEnum.STEEL)){
            Vec3 view = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
            Vector3f lookDirection = Minecraft.getInstance().gameRenderer.getMainCamera().getLookVector();
            Vec3 lookDir = new Vec3(lookDirection.x(), lookDirection.y(), lookDirection.z());
            // controlls where the source of the lines is
            Vec3 playerPos = Minecraft.getInstance().player.getPosition(event.getPartialTick()).add(new Vec3(0,1,0)).add(lookDir);
            // controlls the texture displayed for the line
            ResourceLocation texture = new ResourceLocation(MetallicsArts.MOD_ID,
                    "textures/veffects/the_idle4_60_transluscent.png");
            Vec3 sourceCameraVector = view.subtract(playerPos);
            metalLines(event.getRenderTick(), event.getPartialTick(), view, transformationMatrix, playerPos, 0.005, texture, 31, 8, 32,
                    FoundNearbyMetalUtils.getMetalEntities().stream().sorted(Comparator.comparingDouble(e -> -1*(playerPos.subtract(e.getPosition(event.getPartialTick())).normalize().dot(sourceCameraVector.normalize())))).toList(),
                    FoundNearbyMetalUtils.getMetalBlocks().stream().sorted(Comparator.comparingDouble(e -> -1*(playerPos.subtract(e.getCenter()).normalize().dot(sourceCameraVector.normalize())))).toList());
        }

        double rho = 1;
        float theta = (float) ((player.getViewYRot(event.getPartialTick()) + 90) * Math.PI / 180);
        float phi = Mth.clamp((float) ((player.getViewXRot(event.getPartialTick()) + 90) * Math.PI / 180), 0.0001F, 3.14F);

        Vec3 playerCameraVector = minecraft.cameraEntity
                .getEyePosition(event.getPartialTick())
                .add(rho * Mth.sin(phi) * Mth.cos(theta), rho * Mth.cos(phi) - 0.35F, rho * Mth.sin(phi) * Mth.sin(theta));


//        if (capability.isBurning(MetalTagEnum.IRON)) {
//            IronMetalLines(stack, playerCameraVector, FoundNearbyMetalUtils.getMetalEntities(), FoundNearbyMetalUtils.getMetalBlocks());
//        } else if (capability.isBurning(MetalTagEnum.STEEL)) {
//            SteelMetalLines(stack, playerCameraVector, FoundNearbyMetalUtils.getMetalEntities(), FoundNearbyMetalUtils.getMetalBlocks());
//        }

        if (capability.isBurning(MetalTagEnum.BRONZE)) {
            // /!\ REDO LOGIC /!\
        }

       /* if (capability.isBurning(MetalTagEnum.GOLD)) {
            GoldMetalLines(stack, playerCameraVector, player.getLastDeathLocation());
        }

        if (capability.isBurning(MetalTagEnum.ELECTRUM)) {
            ElectrumMetalLines(stack, playerCameraVector, getRespawnPos());
        }

        if (capability.isBurning(MetalTagEnum.MALATIUM) /*&& otherPlayerDeathPos != null) {
            //Vec3 vector = new Vec3(otherPlayerDeathPos.getX(), otherPlayerDeathPos.getY(), otherPlayerDeathPos.getZ());
            MalatiumMetalLines(stack, playerCameraVector, getRespawnPos() );
        }

        DrawUtils.teardownPoseStack(stack);
        */

    }

    private static void metalLines(int tick, float partialTick, Vec3 viewPosition, Matrix4f translationMatrix, Vec3 source, double scale, ResourceLocation texture, int numberOfFrames, int columnWidth, int columnHeight, List<Entity> entities, List<MetalBlockUtils> blocks){
        /**
         * @param tick integer representing the current world tick, used to calculate the current frame.
         * @param partialTick the current PartialTick value used for rendering.
         * @param viewPosition the current position of player's camera
         * @param transformationMatrix Matrix4f representing the transformation from world to screen coordinates
         * @param source Vec3 representing a point in the world from which lines will originate
         * @param scale controlls the width of the lines
         * @param texture ResourceLocation pointing to the line texture
         * @param numberOfFrames number of frames in the texture
         * @param columnWidth width of a single frame
         * @param columnHeight height of a single frame
         * @param entities the entities to which lines should be drawn
         * @param blocks the block blobs to which lines should be drawn
         *
         */
        for (Entity entity: entities) {
            DrawUtils.drawMetalQuadLines(tick, viewPosition, translationMatrix, entity.getPosition(partialTick), source, scale, texture, numberOfFrames, columnWidth, columnHeight);
        }
        for (MetalBlockUtils block : blocks) {
            DrawUtils.drawMetalQuadLines(tick, viewPosition, translationMatrix, block.getCenter(), source, scale + (scale * 0.1) * block.size(), texture, numberOfFrames, columnWidth, columnHeight);
        }
    }

    public static void setRespawnPos(GlobalPos pos) {
        respawnPos = pos;
    }

    public static Optional<GlobalPos> getRespawnPos() {
        return Optional.of(respawnPos);
    }

    private static void IronMetalLines(PoseStack stack, Vec3 playerCameraVector, Set<Entity> entities, Set<MetalBlockUtils> blocks) {

        for (Entity entity: entities) {
            DrawUtils.drawMetalLine(stack, playerCameraVector, entity.position(), LinesColorEnum.IRON.getSize(), LinesColorEnum.IRON.getR(), LinesColorEnum.IRON.getG(), LinesColorEnum.IRON.getB());
        }

        for (MetalBlockUtils block : blocks) {
            DrawUtils.drawMetalLine(stack, playerCameraVector, block.getCenter(), Mth.clamp(0.3F + block.size() * 0.4F, 0.5F, 7.5F), LinesColorEnum.IRON.getR(), LinesColorEnum.IRON.getG(), LinesColorEnum.IRON.getB());
        }
    }
    private static void SteelMetalLines(PoseStack stack, Vec3 playerCameraVector, Set<Entity> entities, Set<MetalBlockUtils> blocks) {

        for (Entity entity: entities) {
            DrawUtils.drawMetalLine(stack, playerCameraVector, entity.position(), LinesColorEnum.STEEL.getSize(), LinesColorEnum.STEEL.getR(), LinesColorEnum.STEEL.getG(), LinesColorEnum.STEEL.getB());
        }

        for (MetalBlockUtils block : blocks) {
            DrawUtils.drawMetalLine(stack, playerCameraVector, block.getCenter(), Mth.clamp(0.3F + block.size() * 0.4F, 0.5F, 7.5F), LinesColorEnum.STEEL.getR(), LinesColorEnum.STEEL.getG(), LinesColorEnum.STEEL.getB());
        }
    }

    private static void GoldMetalLines(PoseStack stack, Vec3 playerCameraVector, Optional<GlobalPos> globalPos) {
        if (globalPos.isPresent()) {
            BlockPos pos = globalPos.get().pos();
            DrawUtils.drawMetalLine(stack, playerCameraVector, new Vec3(pos.getX(), pos.getY(), pos.getZ()), LinesColorEnum.GOLD.getSize(), LinesColorEnum.GOLD.getR(), LinesColorEnum.GOLD.getG(), LinesColorEnum.GOLD.getB());
        }
    }

    private static void ElectrumMetalLines(PoseStack stack, Vec3 playerCameraVector, Optional<GlobalPos> globalPos) {
        if (globalPos.isPresent()) {
            BlockPos pos = globalPos.get().pos();
            DrawUtils.drawMetalLine(stack, playerCameraVector, new Vec3(pos.getX(), pos.getY(), pos.getZ()), LinesColorEnum.ELECTRUM.getSize(), LinesColorEnum.ELECTRUM.getR(), LinesColorEnum.ELECTRUM.getG(), LinesColorEnum.ELECTRUM.getB());
        }
    }

    private static void MalatiumMetalLines(PoseStack stack, Vec3 playerCameraVector, Optional<GlobalPos> globalPos) {
        if (globalPos.isPresent()) {
            BlockPos pos = globalPos.get().pos();
            DrawUtils.drawMetalLine(stack, playerCameraVector, new Vec3(pos.getX(), pos.getY(), pos.getZ()), LinesColorEnum.MALATIUM.getSize(), LinesColorEnum.MALATIUM.getR(), LinesColorEnum.MALATIUM.getG(), LinesColorEnum.MALATIUM.getB());
        }
    }


}

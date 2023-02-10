package net.rudahee.metallics_arts.modules.logic.client.client_events;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.rudahee.metallics_arts.data.enums.implementations.LinesColorEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.error_handling.messages.ErrorTypes;
import net.rudahee.metallics_arts.utils.DrawUtils;
import net.rudahee.metallics_arts.utils.FoundNearbyMetalUtils;
import net.rudahee.metallics_arts.utils.powers_utils.MetalBlockUtils;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Set;

@OnlyIn(Dist.CLIENT)
public class OnRenderLevelStage {

    static GlobalPos respawnPos;

    @OnlyIn(Dist.CLIENT)
    public static void onRenderLevelStage(RenderLevelStageEvent event, Minecraft minecraft, @Nullable Player player, @Nullable IInvestedPlayerData capability) throws PlayerException {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_PARTICLES
                                    || event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS
                                    || minecraft.options.getCameraType().isMirrored()) {
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

        PoseStack stack = DrawUtils.setupPoseStack(event, minecraft);
        stack.pushPose();

        double rho = 1;
        float theta = (float) ((player.getViewYRot(event.getPartialTick()) + 90) * Math.PI / 180);
        float phi = Mth.clamp((float) ((player.getViewXRot(event.getPartialTick()) + 90) * Math.PI / 180), 0.0001F, 3.14F);

        Vec3 playerCameraVector = minecraft.cameraEntity
                .getEyePosition(event.getPartialTick())
                .add(rho * Mth.sin(phi) * Mth.cos(theta), rho * Mth.cos(phi) - 0.35F, rho * Mth.sin(phi) * Mth.sin(theta));

        if (DrawUtils.haveATIGraphicCard()) {
            DrawUtils.patchPoseForATIGraphicCards();
        }

        if (capability.isBurning(MetalTagEnum.IRON)) {
            IronMetalLines(stack, playerCameraVector, FoundNearbyMetalUtils.getMetalEntities(), FoundNearbyMetalUtils.getMetalBlocks());
        } else if (capability.isBurning(MetalTagEnum.STEEL)) {
            SteelMetalLines(stack, playerCameraVector, FoundNearbyMetalUtils.getMetalEntities(), FoundNearbyMetalUtils.getMetalBlocks());
        }

        if (capability.isBurning(MetalTagEnum.BRONZE)) {
            // /!\ REDO LOGIC /!\
        }

        if (capability.isBurning(MetalTagEnum.GOLD)) {
            GoldMetalLines(stack, playerCameraVector, player.getLastDeathLocation());
        }

        if (capability.isBurning(MetalTagEnum.ELECTRUM)) {
            ElectrumMetalLines(stack, playerCameraVector, getRespawnPos());
        }

        if (capability.isBurning(MetalTagEnum.MALATIUM) /*&& otherPlayerDeathPos != null*/) {
            //Vec3 vector = new Vec3(otherPlayerDeathPos.getX(), otherPlayerDeathPos.getY(), otherPlayerDeathPos.getZ());
            MalatiumMetalLines(stack, playerCameraVector, getRespawnPos() /*Must be changed*/);
        }

        DrawUtils.teardownPoseStack(stack);
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

package net.rudahee.metallics_arts.modules.logic.client.client_events.on_world_tick;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals.IronAndSteelHelpers;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.network.packets.ChangeEmotionPacket;
import net.rudahee.metallics_arts.setup.network.packets.PullAndPushBlockPacket;
import net.rudahee.metallics_arts.setup.network.packets.PullAndPushEntityPacket;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.utils.powers_utils.ClientUtils;

public class PullOrAttackKey {

    public static void pullKey(IInvestedPlayerData capability, Minecraft minecraft, Player player, Level level) {
        HitResult trace = ClientUtils.getMouseOverExtended(11F * 1.5F);

        if (capability.isBurning(MetalTagEnum.IRON)) {
            if (trace != null) {
                if (trace instanceof BlockHitResult) {
                    BlockPos blockPosition = ((BlockHitResult) trace).getBlockPos();
                    if (IronAndSteelHelpers.isBlockStateMetal(minecraft.level.getBlockState(blockPosition))) {
                        ModNetwork.sendToServer(new PullAndPushBlockPacket(blockPosition,
                                Math.round(IronAndSteelHelpers.PULL * IronAndSteelHelpers.getMultiplier(player, capability.getEnhanced() || capability.getEnhanced(),
                                        capability.isBurning(MetalTagEnum.LERASIUM)))));
                    }
                }

                if (trace instanceof EntityHitResult) {
                    ModNetwork.sendToServer(
                            new PullAndPushEntityPacket(((EntityHitResult) trace).getEntity().getId(),
                                    Math.round(IronAndSteelHelpers.PULL * IronAndSteelHelpers.getMultiplier(player, capability.getEnhanced() || capability.getEnhanced(),
                                            capability.isBurning(MetalTagEnum.LERASIUM)))));
                }
            }
        }

    }

    public static void attackKey(IInvestedPlayerData capability, Minecraft minecraft, Player player, Level level) {
        HitResult trace = ClientUtils.getMouseOverExtended(11F * 1.5F);

        if (capability.isBurning(MetalTagEnum.ZINC)) {
            Entity entity;
            if ((trace != null) && (trace.getType() == HitResult.Type.ENTITY)) {
                entity = ((EntityHitResult) trace).getEntity();
                if (entity instanceof Mob) {
                    ModNetwork.sendToServer(new ChangeEmotionPacket(entity.getId(), true));
                }
            }
        }

        if (capability.isBurning(MetalTagEnum.MALATIUM)) {
            Entity entity;
            if ((trace != null) && (trace.getType() == HitResult.Type.ENTITY)) {
                entity = ((EntityHitResult) trace).getEntity();
                if (entity instanceof Player) {
                    Player otherPlayer = (Player) entity;
                    otherPlayer.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(cap -> {

                    });
                }
            }
        }
    }
}
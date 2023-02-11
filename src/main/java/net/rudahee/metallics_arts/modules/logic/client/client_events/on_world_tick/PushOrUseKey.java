package net.rudahee.metallics_arts.modules.logic.client.client_events.on_world_tick;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
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
import net.rudahee.metallics_arts.utils.powers_utils.ClientUtils;

public class PushOrUseKey {

    public static void pushKey(IInvestedPlayerData capability, Minecraft minecraft, Player player, Level level) {
        HitResult trace = ClientUtils.getMouseOverExtended(11F * 1.5F);

        if (capability.isBurning(MetalTagEnum.STEEL)) {
            if (trace != null) {
                if (trace instanceof BlockHitResult) { // IF ITS A BLOCK
                    BlockPos blockPosition = ((BlockHitResult) trace).getBlockPos();
                    if (IronAndSteelHelpers.isBlockStateMetal(minecraft.level.getBlockState(blockPosition))) {
                        ModNetwork.sendToServer(new PullAndPushBlockPacket(blockPosition,
                                Math.round(IronAndSteelHelpers.PUSH * IronAndSteelHelpers.getMultiplier(player, capability.getEnhanced() || capability.getEnhanced(),
                                        capability.isBurning(MetalTagEnum.LERASIUM)))));
                    }
                }

                if (trace instanceof EntityHitResult) {
                    ModNetwork.sendToServer(
                            new PullAndPushEntityPacket(((EntityHitResult) trace).getEntity().getId(),
                                    Math.round(IronAndSteelHelpers.PUSH * IronAndSteelHelpers.getMultiplier(player, capability.getEnhanced() || capability.getEnhanced(),
                                            capability.isBurning(MetalTagEnum.LERASIUM)))));
                }
            }
        }
    }

    public static void useKey(IInvestedPlayerData capability, Minecraft minecraft, Player player, Level level) {
        HitResult trace = ClientUtils.getMouseOverExtended(11F * 1.5F);
        
        if (capability.isBurning(MetalTagEnum.BRASS)) {
            Entity entity;
            if ((trace != null) && (trace.getType() == HitResult.Type.ENTITY)) {
                entity = ((EntityHitResult) trace).getEntity();
                if (entity instanceof Mob) {
                    ModNetwork.sendToServer(new ChangeEmotionPacket(entity.getId(), false));
                }
            }
        }
    }
}
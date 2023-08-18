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
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals.IronAndSteelHelpers;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.network.packets.ChangeEmotionPacket;
import net.rudahee.metallics_arts.setup.network.packets.PullAndPushBlockPacket;
import net.rudahee.metallics_arts.setup.network.packets.PullAndPushEntityPacket;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.utils.powers_utils.ClientUtils;

/**
 * A utility class that provides methods for handling pull and attack key actions in the game.
 * These actions are triggered by pressing specific keys, and their effects depend on the player's capabilities
 * and the type of metal they are currently burning.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class PullOrAttackKey {

    /**
     * Handles the pull key action, which has different effects based on the type of metal the player is burning.
     * For example, if the player is burning Iron, it can pull metal blocks or entities towards the player.
     *
     * @param capability the IInvestedPlayerData instance representing the player's capabilities
     * @param minecraft  the Minecraft instance
     * @param player     the Player instance
     * @param level      the Level instance
     * @see IInvestedPlayerData
     */
    public static void pullKey(IInvestedPlayerData capability, Minecraft minecraft, Player player, Level level) {
        HitResult trace = ClientUtils.getMouseOverExtended(11F * 1.5F);

        if (capability.isBurning(MetalTagEnum.IRON)) {
            if (trace != null) {
                if (trace instanceof BlockHitResult) {
                    BlockPos blockPosition = ((BlockHitResult) trace).getBlockPos();

                    /**if (IronAndSteelHelpers.isAllomanticLever(minecraft.level.getBlockState(blockPosition))) {
                        ModNetwork.sendToServer(new LeverPacket(blockPosition));
                    }*/

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

    /**
     * Handles the attack key action, which has different effects based on the type of metal the player is burning.
     * For example, if the player is burning Zinc, it can change the emotion of a Mob, or if the player is burning
     * Malatium, it can affect other players.
     *
     * @param capability the IInvestedPlayerData instance representing the player's capabilities
     * @param minecraft  the Minecraft instance
     * @param player     the Player instance
     * @param level      the Level instance
     * @see IInvestedPlayerData
     */
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
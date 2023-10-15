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
import net.rudahee.metallics_arts.setup.network.packets.RedstoneSignalsPacket;
import net.rudahee.metallics_arts.setup.network.packets.PullAndPushBlockPacket;
import net.rudahee.metallics_arts.setup.network.packets.PullAndPushEntityPacket;
import net.rudahee.metallics_arts.utils.powers_utils.ClientUtils;

/**
 * This class contains methods to handle push and use key actions based on the player's capabilities
 * and the type of metal they are currently burning.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class PushOrUseKey {

    /**
     * Handles the push key action, which has different effects based on the type of metal the player is burning.
     * For example, if the player is burning Steel, it can push metal blocks or entities away from the player.
     *
     * @param capability the IInvestedPlayerData instance representing the player's capabilities
     * @param minecraft  the Minecraft instance
     * @param player     the Player instance
     * @param level      the Level instance
     * @see IInvestedPlayerData
     */
    public static void pushKey(IInvestedPlayerData capability, Minecraft minecraft, Player player, Level level) {
        HitResult trace = ClientUtils.getMouseOverExtended(11F * 1.5F);

        if (capability.isBurning(MetalTagEnum.STEEL)) {
            if (trace != null) {
                if (trace instanceof BlockHitResult) { // IF ITS A BLOCK
                    BlockPos blockPosition = ((BlockHitResult) trace).getBlockPos();

                    //LEVER
                    if (IronAndSteelHelpers.isAllomanticLever(minecraft.level.getBlockState(blockPosition))
                            || IronAndSteelHelpers.isAllomanticPushButton(minecraft.level.getBlockState(blockPosition))) {
                        ModNetwork.sendToServer(new RedstoneSignalsPacket(blockPosition));
                    }

                    if (IronAndSteelHelpers.isBlockStateMetal(minecraft.level.getBlockState(blockPosition))) {
                        ModNetwork.sendToServer(new PullAndPushBlockPacket(blockPosition,
                                //todo Ruben es un genio
                                Math.round(IronAndSteelHelpers.PUSH * IronAndSteelHelpers.getMultiplier(capability.getEnhanced() || capability.getEnhanced(),
                                        capability.isBurning(MetalTagEnum.LERASIUM), capability.isTapping(MetalTagEnum.STEEL)))));
                    }

                }
                if (trace instanceof EntityHitResult) {
                    ModNetwork.sendToServer(
                            new PullAndPushEntityPacket(((EntityHitResult) trace).getEntity().getId(),
                                    Math.round(IronAndSteelHelpers.PUSH * IronAndSteelHelpers.getMultiplier(capability.getEnhanced(),
                                            capability.isBurning(MetalTagEnum.LERASIUM), capability.isTapping(MetalTagEnum.STEEL)))));
                }
            }
        }
    }

    /**
     * Handles the use key action, which has different effects based on the type of metal the player is burning.
     * For example, if the player is burning Brass, it can change the emotion of a Mob.
     *
     * @param capability the IInvestedPlayerData instance representing the player's capabilities
     * @param minecraft  the Minecraft instance
     * @param player     the Player instance
     * @param level      the Level instance
     * @see IInvestedPlayerData
     */
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
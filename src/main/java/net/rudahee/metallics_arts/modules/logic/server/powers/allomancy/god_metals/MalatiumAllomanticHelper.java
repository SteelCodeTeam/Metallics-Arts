package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.god_metals;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.server_events.OnDamageEvent;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.AllomaticTick;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.powers_utils.TeleportsUtils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper class that contains the methods to use the allomantic Malatium
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see AllomaticTick
 * @see OnDamageEvent
 * @see TeleportsUtils
 */

public class MalatiumAllomanticHelper {

    private static GlobalPos pos;


    private static boolean havePosRegistered = false;


    /**
     * This method is responsible for teleporting the player to the zone indicated by "pos", which is given the value of the death zone of another player. Along with nearby players if he is burning Lerasium.
     *
     * @param level minecraft world you are in.
     * @param capability capabilities (data) of the player.
     * @param player to use the power.
     * @param lerasium if the player is burning Lerasium.
     */
    public static void teleportToDeathPosFromAnotherPlayer(Level level, IInvestedPlayerData capability, ServerPlayer player, boolean lerasium) {

        if (lerasium) {
            if (havePosRegistered) {
                TeleportsUtils.multiTeleport(player, CapabilityUtils.getBubble(player,5), level, pos.dimension(), pos.pos());
            } else {
                TeleportsUtils.multiTeleport(player, CapabilityUtils.getBubble(player, 5), level, player.getRespawnDimension(), player.getRespawnPosition());
            }
            capability.drainMetals(MetalTagEnum.LERASIUM);
        } else {
            if (havePosRegistered) {
                TeleportsUtils.teleport(player, level, pos.dimension(), pos.pos());
            }
        }

        pos = null;
        havePosRegistered = false;

        capability.drainMetals(MetalTagEnum.DURALUMIN, MetalTagEnum.MALATIUM);
    }

    /**
     * Return "Pos"
     *
     * @return GlobalPos
     */

    public static GlobalPos getPos() {
        return pos;
    }

    /**
     * Modify "pos" with the dimension and block where a player died.
     *
     * @param blockPos block where a player has died.
     * @param dimension dimension where a player has died.
     */

    public static void setPos(BlockPos blockPos, ResourceKey<Level> dimension) {
        if (blockPos == null || dimension == null) {
            MalatiumAllomanticHelper.pos = null;
            setPosRegistered(false);
        } else {
            MalatiumAllomanticHelper.pos = GlobalPos.of(dimension, blockPos);
            setPosRegistered(true);
        }
    }

    /**
     * Modify "pos" with the dimension and block where a player died.
     *
     * @param globalPos where a player has died.
     */
    public static void setPos(GlobalPos globalPos) {
        MalatiumAllomanticHelper.pos = globalPos;
    }

    /**
     * Returns a boolean indicating if "pos" contains a valid position.
     *
     * @return boolean
     */
    public static boolean isPosRegistered() {
        return havePosRegistered;
    }


    public static void setPosRegistered(boolean isPosRegistered) {
        havePosRegistered = isPosRegistered;
    }

}

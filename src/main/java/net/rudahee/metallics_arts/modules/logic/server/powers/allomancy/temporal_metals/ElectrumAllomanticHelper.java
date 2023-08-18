package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.temporal_metals;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.AllomaticTick;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.powers_utils.TeleportsUtils;

/**
 * Helper class that contains the methods to use the allomantic Electrum
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see AllomaticTick
 */
public class ElectrumAllomanticHelper {

    /**
     * This method is responsible for teleporting the player to his spawn point, along with nearby players if he is burning Lerasium.
     *
     * @param level minecraft world you are in.
     * @param playerCapability capabilities (data) of the player.
     * @param player to use the power.
     * @param lerasium if the player is burning Lerasium.
     */
    public static void teleportToSpawn(Level level, IInvestedPlayerData playerCapability, ServerPlayer player, boolean lerasium) {
        if (lerasium) {
            if (player.getRespawnPosition() != null || player.getRespawnDimension() != null) {
                TeleportsUtils.multiTeleport(player, CapabilityUtils.getBubble(player, 5), level, player.getRespawnDimension(), player.getRespawnPosition());
                playerCapability.drainMetals(MetalTagEnum.LERASIUM);
            }
        } else {
            if (player.getRespawnPosition() != null || player.getRespawnDimension() != null) {
                TeleportsUtils.teleport(player, level, player.getRespawnDimension(), player.getRespawnPosition());
            }
        }
        playerCapability.drainMetals(MetalTagEnum.DURALUMIN, MetalTagEnum.ELECTRUM);
    }
}

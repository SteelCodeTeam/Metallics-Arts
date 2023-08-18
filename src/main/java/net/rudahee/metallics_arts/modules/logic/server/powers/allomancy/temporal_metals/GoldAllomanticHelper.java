package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.temporal_metals;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.AllomaticTick;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.powers_utils.TeleportsUtils;


/**
 * Helper class that contains the methods to use the allomantic Malatium
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see AllomaticTick
 * @see TeleportsUtils
 */
public class GoldAllomanticHelper {

    /**
     * This method is responsible for teleporting the player to his death point, along with nearby players if he is burning Lerasium.
     *
     * @param level minecraft world you are in.
     * @param playerCapability capabilities (data) of the player.
     * @param player to use the power.
     * @param lerasium if the player is burning Lerasium.
     */
    public static void teleportToDeathPos(Level level, IInvestedPlayerData playerCapability, Player player, boolean lerasium) {

        if (lerasium) {
            if (player.getLastDeathLocation().isPresent()) {
                TeleportsUtils.multiTeleport(player, CapabilityUtils.getBubble(player, 5), level,
                        player.getLastDeathLocation().get().dimension(), player.getLastDeathLocation().get().pos());
                playerCapability.drainMetals(MetalTagEnum.LERASIUM);
            }
        } else {
            if (player.getLastDeathLocation().isPresent()) {
                TeleportsUtils.teleport(player, level, player.getLastDeathLocation().get().dimension(), player.getLastDeathLocation().get().pos());
            }
        }
        playerCapability.drainMetals(MetalTagEnum.DURALUMIN, MetalTagEnum.GOLD);
    }
}

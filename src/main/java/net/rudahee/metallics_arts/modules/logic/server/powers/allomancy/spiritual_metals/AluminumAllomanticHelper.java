package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.spiritual_metals;


import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.messages.ErrorTypes;
import net.rudahee.metallics_arts.modules.error_handling.utils.LoggerUtils;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.AllomaticTick;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

/**
 * Helper class that contains the methods to use the allomantic Aluminum
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see AllomaticTick
 */
public class AluminumAllomanticHelper {

    /**
     * This method is responsible for eliminating all the allomantic reserves of the player, and depending on how much of the aluminum reserve, it can clean the effects of the target player.
     *
     * @param player to whom the effect will be applied.
     * @param playerCapability capabilities (data) of the player.
     *
     * @return boolean if the player's reserve its empty
     */
    public static boolean drainAndCleanEffects(Player player, IInvestedPlayerData playerCapability) {
        if ((Math.random() < ((double) (playerCapability.getAllomanticAmount(MetalTagEnum.ALUMINUM)+1)/10))
                || playerCapability.isTapping(MetalTagEnum.ALUMINUM)) { /** Compounding */
            player.removeAllEffects();
        }
        for (MetalTagEnum metalTagEnum : playerCapability.getAllomanticPowers()) {
            playerCapability.drainMetals(metalTagEnum);
        }

        try {
            ModNetwork.syncInvestedDataPacket(playerCapability, player);
            return true;
        } catch (Exception ex) {
            LoggerUtils.printLogError(
                    ErrorTypes.INDETERMINATE_ERROR.getCode(),
                    ErrorTypes.INDETERMINATE_ERROR.getMessage(),
                    ex.getStackTrace());
            return false;
        }
    }
}

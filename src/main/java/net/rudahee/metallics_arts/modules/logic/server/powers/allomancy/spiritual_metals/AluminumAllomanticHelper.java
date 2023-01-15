package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.spiritual_metals;


import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;

public class AluminumAllomanticHelper {

    /**
     * This method is responsible for eliminating all the allomantic reserves of the player, and depending on how much of the aluminum reserve, it can clean the effects of the target player
     *
     * @param player to whom the effect will be applied.
     * @param playerCapability capabilities (data) of the player.
     */
    public static void drainAndCleanEffects(Player player, IInvestedPlayerData playerCapability) {
        if (Math.random()<((double) (playerCapability.getAllomanticAmount(MetalTagEnum.ALUMINUM)+1)/10)){
            player.removeAllEffects();
        }
        for (MetalTagEnum metalTagEnum : playerCapability.getAllomanticPowers()) {
            playerCapability.drainMetals(metalTagEnum);
        }
    }
}

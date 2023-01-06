package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy;


import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;

public class AluminumAllomanticHelper {
    public static void drainAndCleanEffects(Player player, IInvestedPlayerData playerCapability) {
        if (Math.random()<((double) (playerCapability.getAllomanticAmount(MetalTagEnum.ALUMINUM)+1)/10)){
            player.removeAllEffects();
        }
        for (MetalTagEnum metalTagEnum : playerCapability.getAllomanticPowers()) {
            playerCapability.drainMetals(metalTagEnum);
        }
    }
}

package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.god_metals;


import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.data.player.InvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.AllomaticTick;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

/**
 * Helper class that contains the methods to use the allomantic Ettmetal
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see AllomaticTick
 */
public class EttmetalAllomanticHelper {

    /**
     * It is responsible for generating an explosion around the player, depending on the amount of Ettmetal reserves that have been ingested.
     *
     * @param level minecraft world you are in.
     * @param playerCapability capabilities (data) of the player.
     * @param player that performs the explosion.
     */

    public static void ettmetalExplotion(Level level,IInvestedPlayerData playerCapability, Player player) {
        if (playerCapability.getEnhanced()) {
            //max reserve = 100/20 = 5
            //min reserve = 10/20 = 0*5
            level.explode(player,player.position().x,player.position().y,player.position().z,((float) playerCapability.getAllomanticAmount(MetalTagEnum.ETTMETAL))/20, Explosion.BlockInteraction.BREAK);
            player.setHealth((player.getHealth() - ((float) playerCapability.getAllomanticAmount(MetalTagEnum.ETTMETAL)/5)));
            playerCapability.drainMetals(MetalTagEnum.DURALUMIN);
        } else {
            level.explode(player,player.position().x,player.position().y,player.position().z,((float) playerCapability.getAllomanticAmount(MetalTagEnum.ETTMETAL))/20,Explosion.BlockInteraction.NONE);
            player.setHealth((player.getHealth() - ((float) playerCapability.getAllomanticAmount(MetalTagEnum.ETTMETAL)/10)));
        }
        playerCapability.drainMetals(MetalTagEnum.ETTMETAL);

    }

}

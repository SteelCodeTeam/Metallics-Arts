package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.spiritual_metals;

import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.server_events.OnDamageEvent;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
/**
 * Helper class that contains the methods to use the allomantic Nicrosil
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see OnDamageEvent
 */
public class NicrosilAllomanticHelper {

    /**
     * This method causes the target to enter the enhanced state.
     *
     * @param target who is attacked.
     * @param targetCapability capabilities (data) of the target.
     */
    public static void changeTargetEnhancedToTrue(Player target, IInvestedPlayerData targetCapability) {
        targetCapability.setEnhanced(true);
        ModNetwork.sync(targetCapability, target);
    }
}

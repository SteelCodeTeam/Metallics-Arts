package net.rudahee.metallics_arts.modules.logic.server.server_events;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Handles events related to a player joining the world.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class OnJoinWorldEvent {

    /**
     * Assigns Allomantic and/or Feruchemical powers to a player when they join the world
     * if they do not already have any powers.
     * This method is triggered when a PlayerLoggedInEvent occurs. It checks if the player has
     * any Allomantic or Feruchemical powers and, if not, randomly assigns them one or both types
     * of powers. Finally, it syncs the player's capability data with the client.
     *
     * @param event The PlayerEvent.PlayerLoggedInEvent that triggered this method.
     */
    public static void joinWorld(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();
        try {
        IInvestedPlayerData capability = CapabilityUtils.getCapability(player);
        if ((capability.getAllomanticPowerCount() + capability.getFeruchemicPowerCount() == 0) && !capability.isInvested()) {
            List<MetalTagEnum> metals = Arrays.asList(MetalTagEnum.values());
            Collections.shuffle(metals);
            List<Integer> typeOfPower = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2); // Leras footjob

            Collections.shuffle(typeOfPower);

            if (typeOfPower.get(0) == 0) {
                Collections.shuffle(metals);
                capability.addAllomanticPower(metals.get(0));
            } else if (typeOfPower.get(0) == 1) {
                Collections.shuffle(metals);
                capability.addFeruchemicPower(metals.get(0));
            } else {
                Collections.shuffle(metals);
                capability.addAllomanticPower(metals.get(0));
                Collections.shuffle(metals);
                capability.addFeruchemicPower(metals.get(0));
            }
            capability.setInvested(true);
        }
        //Sync cap to client
        ModNetwork.syncInvestedDataPacket(player);
        } catch (PlayerException ex) {
            ex.printCompleteLog();
        }
    }
}

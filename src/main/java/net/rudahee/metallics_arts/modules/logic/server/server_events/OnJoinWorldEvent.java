package net.rudahee.metallics_arts.modules.logic.server.server_events;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
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
     * @param player The PlayerEvent.PlayerLoggedInEvent that triggered this method.
     */
    public static void joinWorld(Player player) {
        try {
            IInvestedPlayerData capability = CapabilityUtils.getCapability(player);

            if (capability.isFirstJoin()) {
                List<MetalTagEnum> metals = Arrays.asList(MetalTagEnum.values());
                Collections.shuffle(metals);
                MetalTagEnum metal = metals.get(0);
                List<Integer> typeOfPower = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2); // Leras footjob

                Collections.shuffle(typeOfPower);

                if (typeOfPower.get(0) == 0) {
                    capability.addAllomanticPower(metal);
                    player.sendSystemMessage(Component.translatable("IMPROVE NEEDED: Has empezado siendo allomante de " + metal));
                } else if (typeOfPower.get(0) == 1) {
                    player.sendSystemMessage(Component.translatable("IMPROVE NEEDED: Has empezado siendo ferrin de " + metal));
                    capability.addFeruchemicPower(metal);
                } else {
                    capability.addAllomanticPower(metal);
                    capability.addFeruchemicPower(metal);
                    player.sendSystemMessage(Component.translatable("IMPROVE NEEDED: Has empezado siendo nacidoble de " + metal));
                }

                capability.alreadyJoin();

                //Sync cap to client
                ModNetwork.syncInvestedDataPacket(capability, player);

            }
        } catch (PlayerException ex) {
            ex.printCompleteLog();
        }
    }
}

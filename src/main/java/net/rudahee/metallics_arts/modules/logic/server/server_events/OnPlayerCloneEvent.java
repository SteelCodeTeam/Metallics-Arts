package net.rudahee.metallics_arts.modules.logic.server.server_events;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

/**
 * Handles events related to player cloning, e.g., when a player is resurrected.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class OnPlayerCloneEvent {

    /**
     * Transfers Allomantic and Feruchemical powers from the original player
     * to the cloned player upon player cloning.
     * This method is triggered when a PlayerEvent.Clone event occurs. It revives
     * the original player, gets the capabilities of the original and cloned player,
     * and transfers the Allomantic and Feruchemical powers from the original to the
     * cloned player. Finally, it invalidates the old capability and syncs the new
     * capability with the client.
     *
     * @param event The PlayerEvent.Clone event that triggered this method.
     */
    public static void playerClone(PlayerEvent.Clone event) {
        event.getOriginal().revive();
        Player player = event.getEntity();

        try {
            IInvestedPlayerData capability = CapabilityUtils.getCapability(player);
            IInvestedPlayerData oldCapability = CapabilityUtils.getCapability(event.getOriginal());

            if (oldCapability.isInvested()) {
                for (MetalTagEnum mt : MetalTagEnum.values()) {
                    if (oldCapability.hasAllomanticPower(mt)) {
                        capability.addAllomanticPower(mt);
                    }
                    if (oldCapability.hasFeruchemicPower(mt)) {
                        capability.addFeruchemicPower(mt);
                    }
                }

            }

            event.getOriginal().getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).invalidate();
            ModNetwork.syncInvestedDataPacket(player);
        } catch (PlayerException ex) {
            ex.printCompleteLog();
        }
    }
}

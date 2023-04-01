package net.rudahee.metallics_arts.modules.logic.server.server_events;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.rudahee.metallics_arts.data.enums.implementations.EttmetalState;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

public class OnRespawn {
    public static void respawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer) {
            try {
                IInvestedPlayerData capability = CapabilityUtils.getCapability(event.getEntity());

                if (capability.getEttmetalState().equals(EttmetalState.KEEP_ITEMS)) {
                    event.getEntity().getInventory().replaceWith(capability.restoreInventory());
                    capability.setEttmetalState(EttmetalState.NOTHING);
                }
            } catch (PlayerException ex) {
                ex.printCompleteLog();
            }

        }
    }
}

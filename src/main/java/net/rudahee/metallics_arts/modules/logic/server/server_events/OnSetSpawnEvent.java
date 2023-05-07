package net.rudahee.metallics_arts.modules.logic.server.server_events;

import net.minecraft.core.GlobalPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

/**
 * Handles events related to setting the player's spawn point.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class OnSetSpawnEvent {

    /**
     * Syncs the player's new spawn point with the client when the player's
     * spawn point is updated.
     * This method is triggered when a PlayerSetSpawnEvent occurs. It creates a
     * GlobalPos object from the new spawn level and position and sends a sync
     * packet to the client with this data.
     *
     * @param event The PlayerSetSpawnEvent that triggered this method.
     */
    public static void setSpawn(PlayerSetSpawnEvent event) {

        if (event.getEntity() instanceof ServerPlayer player) {
            if (event.getNewSpawn() != null) {
                ModNetwork.syncRespawnPosPacket(GlobalPos.of(event.getSpawnLevel(), event.getNewSpawn()), player);
            }
        }
    }
}

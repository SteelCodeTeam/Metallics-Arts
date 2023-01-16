package net.rudahee.metallics_arts.modules.logic.server.server_events;

import net.minecraft.core.GlobalPos;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

public class OnSetSpawnEvent {
    public static void setSpawn(PlayerSetSpawnEvent event) {
        ModNetwork.syncRespawnPosPacket(GlobalPos.of(event.getSpawnLevel(),event.getNewSpawn()), event.getEntity());
    }
}

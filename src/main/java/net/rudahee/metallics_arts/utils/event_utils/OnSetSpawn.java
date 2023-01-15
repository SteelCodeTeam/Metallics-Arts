package net.rudahee.metallics_arts.utils.event_utils;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

public class OnSetSpawn {
    public static void setSpawn(PlayerSetSpawnEvent event) {
        Player player = event.getEntity();
        IInvestedPlayerData capability = CapabilityUtils.getCapability(player);
        ModNetwork.sync(capability, player);

    }
}

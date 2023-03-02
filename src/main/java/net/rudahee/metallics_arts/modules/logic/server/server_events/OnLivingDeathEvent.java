package net.rudahee.metallics_arts.modules.logic.server.server_events;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

public class OnLivingDeathEvent {
    public static void livingDeath(LivingDeathEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();
        try {
            IInvestedPlayerData capability = CapabilityUtils.getCapability(player);

            for (MetalTagEnum metal : MetalTagEnum.values()) {
                capability.setBurning(metal, false);
                capability.setTapping(metal, false);
                capability.setStoring(metal, false);
                capability.setMetalMindEquiped(metal.getGroup(), false);
            }

            ModNetwork.syncInvestedDataPacket(capability, player);
        } catch (PlayerException ex) {
            ex.printCompleteLog();
        }
    }
}

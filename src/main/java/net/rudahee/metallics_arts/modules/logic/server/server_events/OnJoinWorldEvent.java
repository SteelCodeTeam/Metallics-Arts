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

public class OnJoinWorldEvent {
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

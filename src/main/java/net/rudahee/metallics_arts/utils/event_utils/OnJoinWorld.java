package net.rudahee.metallics_arts.utils.event_utils;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OnJoinWorld {
    public static void joinWorld(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();
        IInvestedPlayerData capability = CapabilityUtils.getCapability(player);

        /*if (capability.getSpawnDimension() == null) {
            int[] pos = {player.level.getLevelData().getXSpawn(),player.level.getLevelData().getYSpawn(),player.level.getLevelData().getZSpawn()};
            String dim = player.level.dimension().location().toString();

            capability.setSpawnPos(pos);
            capability.setSpawnDimension(dim);
        }
        if (capability.getDeathDimension() == null) {
            int[] pos = {player.level.getLevelData().getXSpawn(), player.level.getLevelData().getYSpawn(),player.level.getLevelData().getZSpawn()};
            String dim = player.level.dimension().location().toString();
            capability.setDeathPos(pos);
            capability.setDeathDimension(dim);
        }*/

        //Se necesita hacer que cargue la date del player antes de ejecutar el onjoin world, o no funciona bien
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
        ModNetwork.sync(player);
    }
}

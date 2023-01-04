package net.rudahee.metallics_arts.utils.event_utils;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

public class OnLivingDeath {
    public static void livingDeath(LivingDeathEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();
        IInvestedPlayerData capability = CapabilityUtils.getCapability(player);

        int[] pos = {(int) player.position().x,(int) player.position().y, (int) player.position().z};
        String dim = player.level.dimension().location().toString();

        capability.setDeathDimension(dim);
        capability.setDeathPos(pos);
        for (MetalTagEnum metal: MetalTagEnum.values()) {
            capability.setBurning(metal,false);
            capability.setDecanting(metal,false);
            capability.setStoring(metal,false);
            capability.setMetalMindEquiped(metal.getGroup(),false);
        }

        ModNetwork.sync(capability, player);
    }
}

package net.rudahee.metallics_arts.utils.event_utils;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

public class OnPlayerClone {
    public static void playerClone(PlayerEvent.Clone event) {
        event.getOriginal().revive();
        Player player = event.getEntity();

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
            if (oldCapability.getDeathDimension() != null) {
                capability.setDeathPos(oldCapability.getDeathPos());
                capability.setDeathDimension(oldCapability.getDeathDimension());
            } else {
                capability.setDeathPos(oldCapability.getSpawnPos());
                capability.setDeathDimension(oldCapability.getSpawnDimension());
            }
            capability.setSpawnPos(oldCapability.getSpawnPos());
            capability.setSpawnDimension(oldCapability.getSpawnDimension());
        }

        event.getOriginal().getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).invalidate();
        ModNetwork.sync(player);
    }
}

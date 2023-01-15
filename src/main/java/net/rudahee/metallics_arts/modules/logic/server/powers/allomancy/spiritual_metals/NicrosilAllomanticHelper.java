package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.spiritual_metals;

import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

public class NicrosilAllomanticHelper {
    public static void changeTargetEnhancedToTrue(Player playerTarget) {
        playerTarget.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(cap -> {
            cap.setEnhanced(true);
            ModNetwork.sync(cap, playerTarget);
        });
    }
}

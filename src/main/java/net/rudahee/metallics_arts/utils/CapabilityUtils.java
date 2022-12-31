package net.rudahee.metallics_arts.utils;

import net.minecraft.server.level.ServerPlayer;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

public class CapabilityUtils {
    IInvestedPlayerData playerData;
    public IInvestedPlayerData getCapability (ServerPlayer player) {
        playerData = null;
        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(playerCapability -> {
            playerData = playerCapability;
        });
        return playerData;
    }
}

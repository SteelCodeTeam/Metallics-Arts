package net.rudahee.metallics_arts.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
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
    public AABB getBubble (Player player, int radius) {
        return new AABB(
                new BlockPos(player.position()).offset(-radius, -radius, -radius),
                new BlockPos(player.position()).offset(radius, radius, radius));
    }
}

package net.rudahee.metallics_arts.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

public class CapabilityUtils {
    private static IInvestedPlayerData playerData;
    public static IInvestedPlayerData getCapability (ServerPlayer player) {
        playerData = null;
        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(playerCapability -> playerData = playerCapability);
        return playerData;
    }
    public static IInvestedPlayerData getCapability (Player player) {
        playerData = null;
        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(playerCapability -> playerData = playerCapability);
        return playerData;
    }

    public static IInvestedPlayerData getCapability (Entity entity) {
        playerData = null;
        entity.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(playerCapability -> playerData = playerCapability);
        return playerData;
    }


    public static AABB getBubble (Player player, int radius) {
        return new AABB(
                new BlockPos(player.position()).offset(-radius, -radius, -radius),
                new BlockPos(player.position()).offset(radius, radius, radius));
    }
}

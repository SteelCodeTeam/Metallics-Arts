package net.rudahee.metallics_arts.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

/**
 * Collection of useful statics methods to manage our player capabilities.
 *
 * @author SteelCode Team
 * @since 1.6
 */
public class CapabilityUtils {

    private static IInvestedPlayerData playerData;

    /**
     * Useful method to extract capabilities (data) from target ServerPlayer passed by parameter.
     *
     * @param player ServerPlayer from whom we are going to extract the capabilities.
     *
     * @return IInvestedPlayerData data extracted from target ServerPlayer
     *
     * @see IInvestedPlayerData
     */
    public static IInvestedPlayerData getCapability(ServerPlayer player) {

        playerData = null;

        // if player is present and has capabilities, we return capabilities, else, return null.
        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(playerCapability -> playerData = playerCapability);

        return playerData;
    }

    /**
     * Useful method to extract capabilities (data) from target local Player passed by parameter.
     *
     * @param player local from whom we are going to extract the capabilities.
     *
     * @return IInvestedPlayerData data extracted from target Player.
     *
     * @see IInvestedPlayerData
     */
    public static IInvestedPlayerData getCapability (Player player) {
        playerData = null;

        // if player is present and has capabilities, we return capabilities, else, return null.
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

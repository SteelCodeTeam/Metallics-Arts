package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.god_metals;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.utils.powers_utils.TeleportsUtils;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

public class MalatiumAllomanticHelper {
    private static BlockPos block = null;
    private static ResourceKey<Level> dimension = null;

    public static void teleportToDeathPosFromAnotherPlayer(Level level, IInvestedPlayerData capability, ServerPlayer player, boolean lerasium) {
        if (lerasium) {
            TeleportsUtils.multiTeleport(player, CapabilityUtils.getBubble(player,5), level,
                    MalatiumAllomanticHelper.getDimension(), MalatiumAllomanticHelper.getBlock());
            capability.drainMetals(MetalTagEnum.LERASIUM);
        } else {
            TeleportsUtils.teleport(player, level, MalatiumAllomanticHelper.getDimension(), MalatiumAllomanticHelper.getBlock());
        }
        MalatiumAllomanticHelper.setDimension(null);
        MalatiumAllomanticHelper.setBlock(null);
        capability.drainMetals(MetalTagEnum.DURALUMIN, MetalTagEnum.MALATIUM);
    }

    public static BlockPos getBlock() {
        return block;
    }

    public static void setBlock(BlockPos block) {
        MalatiumAllomanticHelper.block = block;
    }

    public static ResourceKey<Level> getDimension() {
        return dimension;
    }

    public static void setDimension(ResourceKey<Level> dimension) {
        MalatiumAllomanticHelper.dimension = dimension;
    }
}

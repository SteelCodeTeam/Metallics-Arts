package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.god_metals;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.server_events.OnDamageEvent;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.AllomaticTick;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.powers_utils.TeleportsUtils;

/**
 * Helper class that contains the methods to use the allomantic Malatium
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see AllomaticTick
 * @see OnDamageEvent
 * @see TeleportsUtils
 */

public class MalatiumAllomanticHelper {
    private static GlobalPos pos = null;

    private static boolean havePosRegistered = false;

    public static void teleportToDeathPosFromAnotherPlayer(Level level, IInvestedPlayerData capability, ServerPlayer player, boolean lerasium) {
        if (lerasium) {
            TeleportsUtils.multiTeleport(player, CapabilityUtils.getBubble(player,5), level,
                    pos.dimension(), pos.pos());
            capability.drainMetals(MetalTagEnum.LERASIUM);
        } else {
            TeleportsUtils.teleport(player, level, pos.dimension(), pos.pos());
        }

        pos = GlobalPos.of(null, null);
        capability.drainMetals(MetalTagEnum.DURALUMIN, MetalTagEnum.MALATIUM);
    }

    public static GlobalPos getPos() {
        return pos;
    }

    public static void setPos(BlockPos blockPos, ResourceKey<Level> dimension) {
        MalatiumAllomanticHelper.pos = GlobalPos.of(dimension, blockPos);
    }
    public static void setPos(GlobalPos globalPos) {
        MalatiumAllomanticHelper.pos = globalPos;
    }

    public static boolean isPosRegistered() {
        return havePosRegistered;
    }

    public static void setPosRegistered(boolean isPosRegistered) {
        havePosRegistered = isPosRegistered;
    }

}

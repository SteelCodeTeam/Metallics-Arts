package net.rudahee.metallics_arts.utils.event_utils;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.utils.MathUtils;
import net.rudahee.metallics_arts.utils.event_utils.on_world_tick.AllomaticTick;
import net.rudahee.metallics_arts.utils.event_utils.on_world_tick.OnTickUtils;

public class OnWorldTick {

    public static int tick = 0;

    public static void onWorldTick(IInvestedPlayerData capability, ServerPlayer player, ServerLevel level) {

        tick++;

        capability.tickAllomancyBurningMetals(player);

        OnTickUtils.equipKolossBlade(player, capability);

        if (MathUtils.isDivisibleBy3(tick)) {
            if (capability.isBurningAnything()) {
                AllomaticTick.allomanticTick(capability, player, level);
            }
        }

        if (tick == 4800) {
            tick = 0;
        }
    }

    public static int getActualTick() {
        return tick;
    }
}

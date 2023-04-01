package net.rudahee.metallics_arts.modules.logic.server.server_events;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.AllomaticTick;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.OnTickUtils;
import net.rudahee.metallics_arts.utils.MathUtils;


/**
 * Handles events related to world ticks and player abilities.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class OnWorldTickEvent {
    public static int tick = 0;

    /**
     * Performs various actions based on the world tick count and the player's
     * Allomantic and Feruchemical abilities.
     * This method is called on each world tick and updates the player's
     * abilities, manages the Koloss blade, and handles the burning of metals.
     *
     * @param capability The player's Allomantic and Feruchemical abilities.
     * @param player     The player for whom the actions are being performed.
     * @param level      The server level in which the player is located.
     */
    public static void onWorldTick(IInvestedPlayerData capability, ServerPlayer player, ServerLevel level)  {

        boolean isMetalsDrains = false;

        if (capability.isBurningAnything()) {
            if (MathUtils.isDivisibleBy3(tick)) {
                OnTickUtils.equipKolossBlade(player, capability);
                AllomaticTick.each3Ticks(capability, player, level);
            }
            AllomaticTick.eachTick(capability, player);

            isMetalsDrains = AllomaticTick.eachTickWithInstantDrain(capability, player, level);

            if (!isMetalsDrains) {
                capability.tickAllomancyBurningMetals(player);
            }
        }

        tick++;

        if (tick >= 4800) {
            tick = 0;
        }
    }

    /**
     * Returns the current world tick count.
     *
     * @return The current world tick count.
     */
    public static int getActualTick() {
        return tick;
    }
}

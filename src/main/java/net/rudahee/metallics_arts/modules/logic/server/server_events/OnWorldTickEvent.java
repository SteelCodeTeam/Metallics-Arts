package net.rudahee.metallics_arts.modules.logic.server.server_events;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.AllomaticTick;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.FeruchemicTick;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.OnTickUtils;


/**
 * Handles events related to world ticks and player abilities.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class OnWorldTickEvent {


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
    public static void onWorldTick(IInvestedPlayerData capability, Player player, Level level, Integer tick) {

        /*
         * ALLOMANCY
         */
        if (capability.isBurningAnything()) {
            if (tick % 5 == 0) {
                AllomaticTick.each5Ticks(capability, player, level);
            }

            AllomaticTick.eachTick(capability, player, level);

            OnTickUtils.equipKolossBlade(player, capability);

            if (!AllomaticTick.eachTickWithInstantDrain(capability, player, level)) {
                capability.tickAllomancyBurningMetals(player);
            }
        }

        /*
         * FERUCHEMY
         */
        if (capability.isStoringAnything() || capability.isTappingAnything()) {
            if (tick % 5 == 0) {
                FeruchemicTick.each5Ticks(capability, player);
            }

            if (tick % 40 == 0) {
                FeruchemicTick.each40Tick(capability, player);
            }

            FeruchemicTick.eachTick(capability, player);
        }


    }
}

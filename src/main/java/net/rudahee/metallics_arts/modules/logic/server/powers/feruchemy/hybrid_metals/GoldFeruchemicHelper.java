package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals;

import net.minecraft.world.entity.player.Player;

/**
 * Helper class containing the methods and implementations for using feruchemical Gold.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class GoldFeruchemicHelper {
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Gold: increases the current life of the player.
     *
     * @param player to whom the effect will be applied.
     *
     */

    public static void tapPower(Player player) {
        player.heal(2.0F);
    }


    public static void tapCompoundingPower(Player player) {
        player.heal(4.0F);
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Gold: decreases the current life of the player.
     *
     * @param player to whom the effect will be applied.
     *
     */

    public static void storagePower(Player player) {
        if (!player.isCreative()) {
            player.hurt(player.damageSources().generic(), 2.0F);
        }
    }

}

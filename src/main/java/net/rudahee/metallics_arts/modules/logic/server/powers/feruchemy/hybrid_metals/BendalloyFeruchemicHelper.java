package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals;

import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.effects.ModEffects;

/**
 * Helper class containing the methods and implementations for using feruchemical Bendalloy.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class BendalloyFeruchemicHelper {

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Bendalloy: Increases the target player's amount of food.
     *
     * @param player to whom the effect will be applied.
     *
     */

    public static void tapPower(Player player) {
        if (player.getFoodData().getFoodLevel() < 20) {
            player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() + 2);
        }
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Bendalloy: Decreases the target player's amount of food.
     *
     * @param player to whom the effect will be applied.
     *
     */
    public static void storagePower(Player player) {
        if (!player.isCreative()){
            if (player.getFoodData().getFoodLevel()>0){
                player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() - 2);
            }
        }
    }

}

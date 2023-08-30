package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.physical_metals;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

/**
 * Helper class containing the methods and implementations for using feruchemical Steel.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class SteelFeruchemicHelper {

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Pewter: speed movement will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     */

    public static void tapPower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 3, false, false));
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Pewter: slowdowns movement will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     */

    public static void storagePower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 3, false, false));
    }


}

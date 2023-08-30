package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.god_metals;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

/**
 * Helper class containing the methods and implementations for using feruchemical Atium.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class AtiumFeruchemicHelper {

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Atium: Invisibility will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     */

    public static void tapPower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 20, 0, false, false));
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Atium: Glowing will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     */

    public static void storagePower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 20, 0, false, false));
    }

}

package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.physical_metals;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

/**
 * Helper class containing the methods and implementations for using feruchemical Iron.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class IronFeruchemicHelper {
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Iron: slow movement and abort jump will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     */

    public static void tapPower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 5, false, false));
        // apply jump 128 nullifies the target player's ability to jump
        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 128, false, false));
    }


    public static void tapCompoundingPower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 7, false, false));
        // apply jump 128 nullifies the target player's ability to jump
        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 128, false, false));
    }
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Iron: slow falling, jump and weakness will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     */

    public static void storagePower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 1, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 2, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 120, 1, false, false));
    }


}

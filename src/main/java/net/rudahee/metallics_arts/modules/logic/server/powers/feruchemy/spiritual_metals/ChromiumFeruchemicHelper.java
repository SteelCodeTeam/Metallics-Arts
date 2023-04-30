package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.effects.ModEffects;
import java.util.Random;

/**
 * Helper class containing the methods and implementations for using feruchemical Chromium.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class ChromiumFeruchemicHelper {

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Chromium: Luck will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     */

    public static void tapPower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.LUCK,120,15,false, false));
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Chromium: Bad luck will be applied to the target player and will be given random negative effects.
     *
     * @param player to whom the effect will be applied.
     *
     */

    public static void storagePower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.UNLUCK,120,15,false, false));
        if (Math.random() < 0.1) {
            Random a = new Random();
            switch (a.nextInt(9)) {
                case 0 -> player.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 2, false, false));
                case 1 -> player.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 2, false, false));
                case 2 -> player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 2, false, false));
                case 3 -> player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 60, 2, false, false));
                case 4 -> player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 2, false, false));
                case 5 -> player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 80, 4, false, false));
                case 6 -> player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60, 1, false, false));
                case 7 -> player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 2, false, false));
                case 8 -> player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 60, 2, false, false));
            }
        }
    }


}

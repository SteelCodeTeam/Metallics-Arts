package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.physical_metals;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.effects.ModEffects;

/**
 * Helper class containing the methods and implementations for using feruchemical Pewter.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class PewterFeruchemicHelper {

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Pewter: jump and damage bust will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     */
    public static void tapPower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 20, 1, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20, 1, false, false));
        ModEffects.giveFeruchemicalTapEffect(player, MetalTagEnum.PEWTER);
    }
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Pewter: slow movement and weakness will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     */

    public static void storagePower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 1, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20, 1, false, false));
        ModEffects.giveFeruchemicalStorageEffect(player,MetalTagEnum.PEWTER);
    }


}

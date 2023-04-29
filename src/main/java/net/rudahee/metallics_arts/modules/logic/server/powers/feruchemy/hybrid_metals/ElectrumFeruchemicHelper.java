package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.effects.ModModifiers;
import net.rudahee.metallics_arts.modules.effects.ModEffects;

/**
 * Helper class containing the methods and implementations for using feruchemical Electrum.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class ElectrumFeruchemicHelper {

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Electrum: modify the maximum life to add 5 extra hearts to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     *
     */

    public static void tapPower(Player player) {

        if (player.getAttribute(Attributes.MAX_HEALTH).hasModifier(ModModifiers.MIN_HEALTH_ELECTRUM)) {
            player.getAttribute(Attributes.MAX_HEALTH).removeModifier(ModModifiers.MIN_HEALTH_ELECTRUM);
        }

        if(!player.getAttribute(Attributes.MAX_HEALTH).hasModifier(ModModifiers.MAX_HEALTH_ELECTRUM)) {
            player.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(ModModifiers.MAX_HEALTH_ELECTRUM);
        }

        ModEffects.giveFeruchemicalStorageEffect(player, MetalTagEnum.ELECTRUM);

    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Electrum: modify the max health so that the target player only has 5 hearts.
     *
     * @param player to whom the effect will be applied.
     *
     *
     */
    public static void storagePower(Player player) {

        if (player.getAttribute(Attributes.MAX_HEALTH).hasModifier(ModModifiers.MAX_HEALTH_ELECTRUM)) {
            player.getAttribute(Attributes.MAX_HEALTH).removeModifier(ModModifiers.MAX_HEALTH_ELECTRUM);
        }

        if (!player.getAttribute(Attributes.MAX_HEALTH).hasModifier(ModModifiers.MIN_HEALTH_ELECTRUM)) {
            player.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(ModModifiers.MIN_HEALTH_ELECTRUM);
            if (player.getHealth() > 10) {
                player.hurt(DamageSource.GENERIC, player.getHealth() - 10 + player.getAbsorptionAmount());
            }

        }
        ModEffects.giveFeruchemicalStorageEffect(player,MetalTagEnum.ELECTRUM);

    }
    /**
     * This is a unique method in the helpers, which is used to restore the max health to the base value (10 hearts), when the electrum powers stop working.
     *
     * @param player to whom the effect will be applied.
     * @param playerCapability capabilities (data) to the player.
     */
    public static void restoreHearts(Player player, IInvestedPlayerData playerCapability){
        if (player.getAttribute(Attributes.MAX_HEALTH).hasModifier(ModModifiers.MAX_HEALTH_ELECTRUM)) {
            player.getAttribute(Attributes.MAX_HEALTH).removeModifier(ModModifiers.MAX_HEALTH_ELECTRUM);
        }
        if (player.getAttribute(Attributes.MAX_HEALTH).hasModifier(ModModifiers.MIN_HEALTH_ELECTRUM)) {
            player.getAttribute(Attributes.MAX_HEALTH).removeModifier(ModModifiers.MIN_HEALTH_ELECTRUM);
        }
    }


}

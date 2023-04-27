package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.server_events.OnWorldTickEvent;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.OnTickUtils;
import net.rudahee.metallics_arts.modules.effects.ModEffects;
import net.rudahee.metallics_arts.utils.MathUtils;

import java.util.function.Supplier;

/**
 * Helper class containing the methods and implementations for using feruchemical Gold.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class GoldFecuchemicHelper {
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Gold: increases the current life of the player.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#tapPower(Player)
     */

    public static void tapPower(Player player) {
        if (MathUtils.isDivisibleBy30(OnWorldTickEvent.getActualTick())) {
            player.heal(1f);
        }
        ModEffects.giveFeruchemicalTapEffect(player, MetalTagEnum.GOLD);
    }
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Gold: decreases the current life of the player.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#storagePower(Player)
     */

    public static void storagePower(Player player) {
        if (MathUtils.isDivisibleBy30(OnWorldTickEvent.getActualTick())) {
            if (!player.isCreative()){
                player.hurt(DamageSource.GENERIC, 1);
            }
        }
        ModEffects.giveFeruchemicalStorageEffect(player,MetalTagEnum.GOLD);
    }

}

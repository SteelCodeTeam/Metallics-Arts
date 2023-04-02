package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.physical_metals;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.test.ModEffects;

import java.util.function.Supplier;

/**
 * Helper class containing the methods and implementations for using feruchemical Steel.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class SteelFecuchemicHelper extends AbstractFechuchemicHelper {

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Pewter: speed movement will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#tapPower(Player)
     */
    @Override
    public void tapPower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 3, false, false));
        ModEffects.giveFeruchemicalTapEffect(player, MetalTagEnum.STEEL);
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Pewter: slowdowns movement will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#storagePower(Player)
     */
    @Override
    public void storagePower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 3, false, false));
        ModEffects.giveFeruchemicalStorageEffect(player,MetalTagEnum.STEEL);
    }

    /**
     * Returns an instance of SteelFecuchemicHelper using a factory method pattern.
     * This method allows you to create instances of SteelFecuchemicHelper with a consistent interface.
     *
     * @return a Supplier that returns a new instance of SteelFecuchemicHelper when called
     */
    public static Supplier<? extends SteelFecuchemicHelper> getInstance() {
        return SteelFecuchemicHelper::new;
    }
}

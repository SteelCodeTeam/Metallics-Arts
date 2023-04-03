package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.physical_metals;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.effects.ModEffects;

import java.util.function.Supplier;

/**
 * Helper class containing the methods and implementations for using feruchemical Tin.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class TinFeruchemicHelper extends AbstractFechuchemicHelper {

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Pewter: night vision will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#tapPower(Player)
     */
    @Override
    public void tapPower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 320, 1, false, false));
        ModEffects.giveFeruchemicalTapEffect(player, MetalTagEnum.TIN);
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Pewter: blindness will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#storagePower(Player)
     */
    @Override
    public void storagePower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 1, false, false));
        ModEffects.giveFeruchemicalStorageEffect(player,MetalTagEnum.TIN);
    }

    /**
     * Returns an instance of TinFecuchemicHelper using a factory method pattern.
     * This method allows you to create instances of TinFecuchemicHelper with a consistent interface.
     *
     * @return a Supplier that returns a new instance of TinFecuchemicHelper when called
     */
    public static Supplier<? extends TinFeruchemicHelper> getInstance() {
        return TinFeruchemicHelper::new;
    }
}

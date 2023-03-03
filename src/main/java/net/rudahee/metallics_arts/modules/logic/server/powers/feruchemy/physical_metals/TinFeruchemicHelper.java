package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.physical_metals;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;

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
     * @see AbstractFechuchemicHelper#tappingPower(Player)
     */
    @Override
    public void tappingPower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 320, 1, true, false, false));
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
        player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 1, true, false, false));
    }
    //todo
    public static Supplier<? extends TinFeruchemicHelper> getInstance() {
        return TinFeruchemicHelper::new;
    }
}

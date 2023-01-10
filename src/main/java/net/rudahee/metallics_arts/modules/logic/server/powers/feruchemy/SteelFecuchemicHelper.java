package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

import java.util.function.Supplier;

public class SteelFecuchemicHelper extends AbstractFechuchemicHelper{
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Pewter: speed movement will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#decantPower(Player)
     */
    @Override
    public void decantPower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 3, true, false));
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
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 3, true, false));
    }

    public static Supplier<? extends SteelFecuchemicHelper> getInstance() {
        return SteelFecuchemicHelper::new;
    }
}

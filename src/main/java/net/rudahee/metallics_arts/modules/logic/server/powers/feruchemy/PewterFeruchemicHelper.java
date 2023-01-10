package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

import java.util.function.Supplier;

public class PewterFeruchemicHelper extends AbstractFechuchemicHelper{

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Pewter: jump and damage bust will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#decantPower(Player)
     */
    public void decantPower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 5, 1, true, true));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 5, 1, true, true));
    }
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Pewter: slow movement and weakness will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#storagePower(Player)
     */
    @Override
    public void storagePower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 1, true, false));
        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 5, 1, true, false));
    }

    public static Supplier<? extends PewterFeruchemicHelper> getInstance() {
        return PewterFeruchemicHelper::new;
    }
}

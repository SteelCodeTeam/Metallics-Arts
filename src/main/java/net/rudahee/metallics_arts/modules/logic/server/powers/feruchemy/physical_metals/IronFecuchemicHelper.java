package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.physical_metals;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;

import java.util.function.Supplier;

public class IronFecuchemicHelper extends AbstractFechuchemicHelper {
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Iron: slow movement and abort jump will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#tappingPower(Player)
     */
    @Override
    public void tappingPower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 5, true, false));
        // apply jump 128 nullifies the target player's ability to jump
        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 20, 128, true, false));
    }
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Iron: slow falling, jump and weakness will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#storagePower(Player) 
     */
    @Override
    public void storagePower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 1, true, false));
        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 20, 2, true, false));
        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20, 1, true, false));
    }

    public static Supplier<? extends IronFecuchemicHelper> getInstance() {
        return IronFecuchemicHelper::new;
    }
}

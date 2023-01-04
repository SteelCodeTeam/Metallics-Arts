package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

import java.util.function.Supplier;

public class IronFecuchemicHelper extends AbstractFechuchemicHelper{
    @Override
    public void decantPower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 5, true, false));
        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 20, 128, true, false));
    }

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

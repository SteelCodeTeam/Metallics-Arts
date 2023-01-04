package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

import java.util.function.Supplier;

public class AtiumFecuchemicHelper extends AbstractFechuchemicHelper{

    //GIVE INVISIBILITY
    @Override
    public void decantPower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 5, 1, true, false));
    }

    //GIVE GLOWING
    @Override
    public void storagePower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 5, 1, true, true));
    }

    public static Supplier<? extends AtiumFecuchemicHelper> getInstance() {
        return AtiumFecuchemicHelper::new;
    }
}

package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.utils.event_utils.OnWorldTick;
import net.rudahee.metallics_arts.utils.event_utils.on_world_tick.OnTickUtils;

import java.util.Random;
import java.util.function.Supplier;

public class ChromiumFecuchemicHelper extends AbstractFechuchemicHelper {

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Chromium: Luck will be applied to the target player.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#tappingPower(Player)
     */
    @Override
    public void tappingPower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.LUCK,40,15,true, true));
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Chromium: Bad luck will be applied to the target player and will be given random negative effects.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#tappingPower(Player)
     */
    @Override
    public void storagePower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.UNLUCK,40,15,true, true));
        if (OnTickUtils.activationEvery90Tick(OnWorldTick.getActualTick())){
            if (Math.random()<0.5) {
                Random a = new Random();
                switch (a.nextInt(9)) {
                    case 0 -> player.addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 2, true, true));
                    case 1 -> player.addEffect(new MobEffectInstance(MobEffects.POISON, 45, 2, true, true));
                    case 2 -> player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 50, 2, true, true));
                    case 3 -> player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 50, 2, true, true));
                    case 4 -> player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 45, 2, true, true));
                    case 5 -> player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 180, 4, true, true));
                    case 6 -> player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 40, 1, true, true));
                    case 7 ->
                            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 50, 2, true, true));
                    case 8 -> player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 50, 2, true, true));
                }
            }
        }
    }

    public static Supplier<? extends ChromiumFecuchemicHelper> getInstance() {
        return ChromiumFecuchemicHelper::new;
    }
}

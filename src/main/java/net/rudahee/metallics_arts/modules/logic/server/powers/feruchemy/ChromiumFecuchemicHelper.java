package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.modules.logic.server.ServerEventHandler;
import net.rudahee.metallics_arts.utils.event_utils.OnWorldTickEvent;

import java.util.Random;
import java.util.function.Supplier;

public class ChromiumFecuchemicHelper extends AbstractFechuchemicHelper{

    //LUCK FOR CHEST
    @Override
    public void decantPower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.LUCK,40,15,true, true));
    }

    //REMOVE LUCK AND GIVE RANDOM BAD EFFECTS
    @Override
    public void storagePower(Player player) {

        if (OnWorldTickEvent.activationEvery80Tick(ServerEventHandler.getActualTick())) {
            player.addEffect(new MobEffectInstance(MobEffects.UNLUCK,40,15,true, true));
            if (OnWorldTickEvent.activationEvery80Tick(ServerEventHandler.getActualTick())){
                if (Math.random()<0.5) {
                    Random a = new Random();
                    switch (a.nextInt(9)) {
                        case 0:
                            player.addEffect(new MobEffectInstance(MobEffects.WITHER,40,2,true, true));
                            break;
                        case 1:
                            player.addEffect(new MobEffectInstance(MobEffects.POISON,45,2,true, true));
                            break;
                        case 2:
                            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,50,2,true, true));
                            break;
                        case 3:
                            player.addEffect(new MobEffectInstance(MobEffects.HUNGER,50,2,true, true));
                            break;
                        case 4:
                            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,45,2,true, true));
                            break;
                        case 5:
                            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION,180,4,true, true));
                            break;
                        case 6:
                            player.addEffect(new MobEffectInstance(MobEffects.LEVITATION,40,1,true, true));
                            break;
                        case 7:
                            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,50,2,true, true));
                            break;
                        case 8:
                            player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN,50,2,true, true));
                            break;
                    }

                    //SE PUEDE AGREGAR DAÑO INSTANTANEO
                }
            }
        }
    }

    public static Supplier<? extends ChromiumFecuchemicHelper> getInstance() {
        return ChromiumFecuchemicHelper::new;
    }
}

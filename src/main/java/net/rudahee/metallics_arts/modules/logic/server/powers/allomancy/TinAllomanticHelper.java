package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class TinAllomanticHelper {

    public static void addTinEffects(Player player) {
        if(player.tickCount % 20 == 0) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 320, 1, true, false, false));
        }
    }


    public static void addTinEffectsEnhanced(Player player) {
        if(player.tickCount % 20 == 0) {
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 1, true, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 240, 1, true, false, false));
        }
    }

}

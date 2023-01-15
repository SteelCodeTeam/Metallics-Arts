package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class TinAllomanticHelper {


    /**
     *
     * @param player to whom the effect will be applied.
     */
    public static void addTinEffects(Player player, boolean enhanced) {
        if (enhanced) {
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 2, true, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 240, 2, true, false, false));
        } else {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 320, 1, true, false, false));
        }
    }


}

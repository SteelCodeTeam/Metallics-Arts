package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class PewterAllomanticHelper {
    /**
     *
     * @param player to whom the effect will be applied.
     * @param lerasium if player is burning lerasium
     * @param duralumin if player is burning duralumin
     */
    public static void addPewterEffects(Player player, boolean lerasium, boolean duralumin) {
        int damage, resistence, digSpeed;
        if (!duralumin && !lerasium) {
            damage = 0;
            resistence = 1;
            digSpeed = 1;
        } else if(duralumin && !lerasium) {
            damage = 1;
            resistence = 2;
            digSpeed = 2;
        } else if(!duralumin) {
            damage = 1;
            resistence = 1;
            digSpeed = 2;
        } else {
            damage = 4;
            resistence = 4;
            digSpeed = 4;
        }
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 5, digSpeed, true, true));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 5, damage, true, true));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 5, resistence, true, true));
    }
}

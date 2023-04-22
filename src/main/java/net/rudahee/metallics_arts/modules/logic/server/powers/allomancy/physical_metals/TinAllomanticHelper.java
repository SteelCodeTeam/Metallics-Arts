package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.AllomaticTick;

/**
 * Helper class that contains the methods to use the allomantic Tin
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see AllomaticTick
 */
public class TinAllomanticHelper {


    /**
     * This method gives the player the effects that make up the increased tin senses.
     *
     * @param player to whom the effect will be applied.
     */
    public static void addTinEffects(Player player, boolean enhanced) {
        if (enhanced) {
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 80, 2, true, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 240, 2, true, false, false));
        } else {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 320, 1, true, false, false));
        }
    }


}

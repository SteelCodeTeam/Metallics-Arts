package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.temporal_metals;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.AllomaticTick;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

/**
 * Helper class that contains the methods to use the allomantic Cadmium
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see AllomaticTick
 */
public class CadmiumAllomanticHelper {

    /**
     * This method reduces the falling speed of the player.
     *
     * @param player to whom the effect will be applied.
     * @param enhanced if player is burning Duralumin or the player was hit with Nicrosil.
     */
    public static void CadmiumEffectSelfPlayer(Player player, boolean enhanced) {
        if (enhanced) {
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 80, 100, true, false));
        } else {
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 4, true, false));
        }
    }

    /**
     * This method reduces the speeds of nearby mobs and players.
     *
     * @param player to whom the effect will be applied.
     * @param enhanced if player is burning Duralumin or the player was hit with Nicrosil.
     * @param lerasium if the player is burning Lerasium.
     * @param level in which the player is located (world).
     * @param playerCapability capabilities (data) of the player.
     */
    public static void CadmiumMobEffectsOtherPlayers(Player player, IInvestedPlayerData playerCapability, ServerLevel level, boolean enhanced, boolean lerasium) {
        int radius = 8;
        if (enhanced && lerasium) {
            radius = 13;
        } else if (enhanced) {
            radius = 11;
        } else if (lerasium) {
            radius = 10;
        }

        level.getEntitiesOfClass(LivingEntity.class, CapabilityUtils.getBubble(player,radius)).forEach(entity -> {
            if (playerCapability.isBurning(MetalTagEnum.LERASIUM)) {
                if (playerCapability.getEnhanced()) {
                    entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 100, true, false));
                    entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 100, true, false));
                } else {
                    entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 2, true, false));
                    entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 2, true, false));
                }

            } else {
                if (playerCapability.getEnhanced()) {
                    entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 2, true, false));
                    entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 2, true, false));
                } else {
                    entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 1, true, false));
                    entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 1, true, false));
                }
            }
        });
    }

}

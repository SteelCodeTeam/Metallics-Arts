package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;

public class CadmiumAllomanticHelper {
    public static void CadmiumEffectSelfPlayer(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 10, 4, true, false));
    }

    public static void CadmiumEffectSelfPlayerEnhanced(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 100, true, false));
    }

    public static void CadmiumMobEffectsOtherPlayers(Player player, IInvestedPlayerData playerCapability) {
        if (playerCapability.isBurning(MetalTagEnum.LERASIUM)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 2, true, false));
        } else {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 10, 1, true, false));
        }
    }

    public static void CadmiumMobEffectsOtherPlayersEnhanced(Player player, IInvestedPlayerData playerCapability) {
        if (playerCapability.isBurning(MetalTagEnum.LERASIUM)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 100, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 100, true, false));
        } else {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 2, true, false));
        }
    }
}

package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.spiritual_metals;


import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.MetalMindEnum;
import net.rudahee.metallics_arts.data.enums.implementations.languages.EffectsTranslation;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.messages.ErrorTypes;
import net.rudahee.metallics_arts.modules.error_handling.utils.LoggerUtils;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.AllomaticTick;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

import java.util.*;

/**
 * Helper class that contains the methods to use the allomantic Aluminum
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see AllomaticTick
 */
public class AluminumAllomanticHelper {

    /**
     * This method clears the player's reserves if they only burn aluminum, removes all effects if they also tap aluminum, and clears negative effects if they burn aluminum along with lerasium.
     *
     * @param player to whom the effect will be applied.
     * @param playerCapability capabilities (data) of the player.
     *
     * @return boolean if the player's reserve its empty
     */
    public static boolean drainAndCleanEffects(Player player, IInvestedPlayerData playerCapability) {
        if (playerCapability.isBurning(MetalTagEnum.LERASIUM)) {
            if (player.getActiveEffectsMap() != null && !player.getActiveEffectsMap().isEmpty()) {
                List<MobEffect> toRemove = new ArrayList<>();
                for (MobEffect effect : player.getActiveEffectsMap().keySet()) {
                    if (effect != null && effect.getCategory() == MobEffectCategory.HARMFUL) {
                        toRemove.add(effect);
                    }
                }
                for (MobEffect effect : toRemove) {
                    player.removeEffect(effect);
                }
            }
        } else {
            if (playerCapability.isTapping(MetalTagEnum.ALUMINUM)) {
                player.removeAllEffects();
            }
            for (MetalTagEnum metalTagEnum : playerCapability.getAllomanticPowers()) {
                playerCapability.drainMetals(metalTagEnum);
            }
        }

        try {
            ModNetwork.syncInvestedDataPacket(playerCapability, player);
            return true;
        } catch (Exception ex) {
            LoggerUtils.printLogError(
                    ErrorTypes.INDETERMINATE_ERROR.getCode(),
                    ErrorTypes.INDETERMINATE_ERROR.getMessage(),
                    ex.getStackTrace());
            return false;
        }
    }
}

package net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.spiritual_metals.NicrosilAllomanticHelper;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;


/**
 * This class provides methods that add utility to the control of powers that work passively, tick by tick.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class OnTickUtils {

    private static int buffNicrosilDuralumin = -1;


    /**
     * This method is responsible for adding negative effects to the player if he has the koloss blade in his hand without burning pewter.
     *
     * @param player to whom the effect will be applied.
     * @param playerCapability capabilities (data) of the player.
     */
    public static void equipKolossBlade(Player player, IInvestedPlayerData playerCapability) {
        if (!(playerCapability.isBurning(MetalTagEnum.PEWTER) || playerCapability.isTapping(MetalTagEnum.PEWTER))
                && (player.getMainHandItem().getItem() == ModItemsRegister.KOLOSS_BLADE.get() || player.getOffhandItem().getItem() == ModItemsRegister.KOLOSS_BLADE.get())) {

            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 2, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 10, 2, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 10, 2, false, false));
        }
    }

    /**
     * This method is in charge of applying the improved effects of duralumin or nicrosil applied by another player.
     * For this, it uses an internal counter, which when burning duralumin with more than 88% reserves or being hit by
     * someone who burns nicrosil, is assigned the maximum time and then counts down tick by tick.
     * <p>
     * When it reaches 0, the metal reserves that were in use are emptied, and the counter is placed at -1.
     *
     * @param playerCapability capabilities (data) of the player.
     *
     * @see NicrosilAllomanticHelper
     */
    public static void duraluminAndExternalNicrosilEffect(IInvestedPlayerData playerCapability, Player player) {
        if (playerCapability.isBurning(MetalTagEnum.DURALUMIN)) {
            if ((playerCapability.getAllomanticAmount(MetalTagEnum.DURALUMIN) > (MetalTagEnum.DURALUMIN.getMaxAllomanticTicksStorage()*0.88)) || (buffNicrosilDuralumin != -1)){
                if (buffNicrosilDuralumin == -1) {
                    playerCapability.setEnhanced(true);
                    buffNicrosilDuralumin = MetalTagEnum.DURALUMIN.getMaxAllomanticTicksStorage();
                }
                for (MetalTagEnum metal : MetalTagEnum.values()) {
                    if (playerCapability.isBurning(metal) && !playerCapability.containsMetalsEnhanced(metal)){
                        playerCapability.addMetalsEnhanced(metal);
                    }
                }
            } else {
                playerCapability.setBurning(MetalTagEnum.DURALUMIN,false);
            }
        } else if (playerCapability.getEnhanced()) {
            if (buffNicrosilDuralumin == -1) {
                buffNicrosilDuralumin = MetalTagEnum.DURALUMIN.getMaxAllomanticTicksStorage();
            }
            for (MetalTagEnum metal : MetalTagEnum.values()) {
                if (playerCapability.isBurning(metal) && !playerCapability.containsMetalsEnhanced(metal)){
                    playerCapability.addMetalsEnhanced(metal);
                }
            }
        } else {
            if (!playerCapability.getMetalsEnhanced().isEmpty()) {
                for (MetalTagEnum metal: playerCapability.getMetalsEnhanced()) {
                    playerCapability.drainMetals(metal);
                }
                playerCapability.clearMetalsEnhanced();
            }
        }
        if (playerCapability.getEnhanced()){
            buffNicrosilDuralumin--;
            if (buffNicrosilDuralumin == 0) {
                playerCapability.setEnhanced(false);
                buffNicrosilDuralumin = -1;
            }
        }

        ModNetwork.syncInvestedDataPacket(playerCapability, player);
    }
}

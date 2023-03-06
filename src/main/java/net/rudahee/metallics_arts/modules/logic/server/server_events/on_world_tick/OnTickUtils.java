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
    /**
     * This method returns true when a period of 30 ticks expires.
     *
     * @param actualTick counter current value.
     *
     * @return boolean
     */
    public static boolean activationEvery30Tick(int actualTick) {
        return (actualTick % 30) == 0;

    }
    /**
     * This method returns true when a period of 90 ticks expires.
     *
     * @param actualTick counter current value.
     *
     * @return boolean
     */
    public static boolean activationEvery90Tick(int actualTick) {
        return (actualTick % 90) == 0;
    }

    /**
     * This method returns true when a period of 240 ticks expires.
     *
     * @param actualTick counter current value.
     *
     * @return boolean
     */
    public static boolean activationEvery240Tick(int actualTick) {
        return (actualTick % 240) == 0;
    }

    /**
     * This method is responsible for adding negative effects to the player if he has the koloss blade in his hand without burning pewter.
     *
     * @param player to whom the effect will be applied.
     * @param playerCapability capabilities (data) of the player.
     */
    public static void equipKolossBlade(Player player, IInvestedPlayerData playerCapability) {
        if (!(playerCapability.isBurning(MetalTagEnum.PEWTER) || playerCapability.isTapping(MetalTagEnum.PEWTER))
                && (player.getMainHandItem().getItem() == ModItemsRegister.KOLOSS_BLADE.get() || player.getOffhandItem().getItem() == ModItemsRegister.KOLOSS_BLADE.get())) {

            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 2, true, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 10, 2, true, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 10, 2, true, true, false));
        }
    }
    private static int buffNicrosilDuralumin = -1;

    /**
     * This method is responsible for calculating the player's enhanced state, having an internal time counter that,
     * when it reaches 0, empties the Allomantic reserves that the player was burning while in this state.
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

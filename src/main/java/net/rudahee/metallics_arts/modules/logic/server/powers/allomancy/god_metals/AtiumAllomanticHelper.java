package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.god_metals;

import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.server_events.OnDamageEvent;
/**
 * Helper class that contains the methods to use the allomantic Atium
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see OnDamageEvent
 */

public class AtiumAllomanticHelper {

    /**
     * Calculate the damage the target player will take if they are burning Atium, if the source of the damage is a player.
     *
     * @param targetCapability capabilities (data) of the target player.
     * @param sourceCapability capabilities (data) of the source player.
     * @return float final damage.
     */
    public static boolean getCalculateComplexDamage(IInvestedPlayerData targetCapability, IInvestedPlayerData sourceCapability, boolean hasAtiumArmor) {
        int target = 0;
        int source = 0;
        if (targetCapability.isBurning(MetalTagEnum.ATIUM))
            target = target + 1;
        if (targetCapability.isBurning(MetalTagEnum.LERASIUM))
            target =target + 2;
        if (targetCapability.getEnhanced())
            target = target + 3;

        if (sourceCapability.isBurning(MetalTagEnum.ATIUM)) {
            source = source + 1;
            if (sourceCapability.isBurning(MetalTagEnum.LERASIUM))
                source = source + 2;
            if (sourceCapability.getEnhanced())
                source = source + 3;
        }

        if (target <= source) {
            return false;
        } else { //mayor probabilidad de evitar
            if (source == 0) {
                return getCalculateSimpleDamage(targetCapability, hasAtiumArmor ? 0.1 : 0);
            } else {
                return getCalculateDoubleBurn(target, source, hasAtiumArmor ? 0.1 : 0);
            }

        }
        /*
        atium = 1                           nada
        atium + lerasium = 3                atium = 1
        atium + duralumin = 4               atium || atium + lerasium = 3
        atium + lerasium + duralumin = 6    atium + lerasium = 3 || atium + duralumin = 4
         */
    }

    /**
     * Is in charge of calculating the final damage based on the metals burned by the source of damage and the target of damage.
     *
     * @param targetC calculated value based on whether the target is burning Atium, Lerasium and Duralumin.
     * @param sourceC calculated value based on whether the source is burning Atium, Lerasium and Duralumin.
     * @return float final damage.
     */
    private static boolean getCalculateDoubleBurn(int targetC, int sourceC, double armor) {
        if (targetC == 3) {                     //target = atium + lerasium
            return Math.random() < (0.5 + armor);         //source = atium
        } else if (targetC == 4) {              //target = atium + duralumin
            if (sourceC == 1) {
                //source = atium
                return Math.random() < (0.65 + armor);
            } else {
                //source = atium + lerasium
                return Math.random() < (0.55 + armor);
            }
        } else {                                //target quema atium + lerasium + duralumin
            if (sourceC == 1) {
                //source = atium
                return Math.random() < (0.9 + armor);
            } else if (sourceC == 3) {
                //source = atium + lerasium
                return Math.random() < (0.75 + armor);
            } else {
                //source = atium + duralumin
                return Math.random() < (0.65 + armor);
            }
        }
    }

    /**
     * Is in charge of calculating the final damage based on the metals burned by the target of damage.
     *
     * @param targetCapability capabilities (data) of the target player.
     * @return float final damage.
     */

    public static boolean getCalculateSimpleDamage(IInvestedPlayerData targetCapability, double armor) {
        if (targetCapability.isBurning(MetalTagEnum.LERASIUM) && targetCapability.getEnhanced()) { //lerasium + duralumin
            return true;
        } else if (targetCapability.isBurning(MetalTagEnum.LERASIUM)) { //solo lerasium
            return Math.random() < (0.6 + armor);
        } else if (targetCapability.getEnhanced()) { //solo duralumin
            return Math.random() < (0.8 + armor);
        } else { //solo atium
            return Math.random() < (0.4 + armor);
        }
    }

}

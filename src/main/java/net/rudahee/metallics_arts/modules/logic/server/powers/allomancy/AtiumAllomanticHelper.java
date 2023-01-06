package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy;

import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;


public class AtiumAllomanticHelper {

    public static float getCalculateComplexDamage(IInvestedPlayerData targetCapability, IInvestedPlayerData sourceCapability, float damage) {

        int tar = 0;
        int sour = 0;
        if (targetCapability.isBurning(MetalTagEnum.ATIUM))
            tar =+1;
        if (targetCapability.isBurning(MetalTagEnum.LERASIUM))
            tar =+2;
        if (targetCapability.getEnhanced())
            tar = +3;

        if (sourceCapability.isBurning(MetalTagEnum.ATIUM)){
            sour =+1;
            if (sourceCapability.isBurning(MetalTagEnum.LERASIUM))
                sour =+2;
            if (sourceCapability.getEnhanced())
                sour = +3;
        }

        if (tar <= sour) {
            return damage;
        }
        else { //mayor probabilidad de evitar
            if (sour == 0) {
                return getCalculateSimpleDamage(targetCapability,damage);
            } else {
                return getCalculateDobleBurn(tar, sour, damage);
            }

        }
        /*
        atium = 1                           nada
        atium + lerasium = 3                atium = 1
        atium + duralumin = 4               atium || atium + lerasium = 3
        atium + lerasium + duralumin = 6    atium + lerasium = 3 || atium + duralumin = 4
         */
    }

    private static float getCalculateDobleBurn(int tar, int sour, float damage) {
        if (tar == 3) {                     //target = atium + lerasium
            if (Math.random()<0.5) {        //source = atium
                return 0;
            }
        } else if (tar == 4) {              //target = atium + duralumin
            if (sour == 1) {
                if (Math.random()<0.65) {   //source = atium
                    return 0;
                }
            } else {
                if (Math.random()<0.55) {    //source = atium + lerasium
                    return 0;
                }
            }
        } else {                            //target quema atium + lerasium + duralumin
            if (sour == 1) {
                if (Math.random()<0.9) {   //source = atium
                    return 0;
                }
            } else if (sour == 3) {
                if (Math.random()<0.75) {   //source = atium + lerasium
                    return 0;
                }
            } else {
                if (Math.random()<0.65) {   //source = atium + duralumin
                    return 0;
                }
            }
        }
        return damage;
    }

    public static float getCalculateSimpleDamage(IInvestedPlayerData targetCapability, float damage) {
        if (targetCapability.isBurning(MetalTagEnum.LERASIUM) && targetCapability.getEnhanced()) { //lerasium + duralumin
            return 0;
        } else if (targetCapability.isBurning(MetalTagEnum.LERASIUM)) { //solo lerasium
            if (Math.random()<0.6) {
                return 0;
            }
        } else if (targetCapability.getEnhanced()) { //solo duralumin
            if (Math.random()<0.8) {
                return 0;
            }
        } else { //solo atium
            if (Math.random()<0.4) {
                return 0;
            }
        }
        return damage;
    }

}

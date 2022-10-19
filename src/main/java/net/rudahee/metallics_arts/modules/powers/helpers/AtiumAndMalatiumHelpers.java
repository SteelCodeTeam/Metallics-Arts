package net.rudahee.metallics_arts.modules.powers.helpers;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.modules.data_player.IDefaultInvestedPlayerData;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;


public class AtiumAndMalatiumHelpers {

    private static final boolean playerIsBurning = false;
    private static final float damage = 0F;


    public static void decantAtium(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 5, 1, true, true));
    }

    public static void storageAtium(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 5, 1, true, true));
    }

    private float qty = 0f;

    public static float getDamageWhenUseAtium(Player source, Player target, float qty) {
        return new AtiumAndMalatiumHelpers().internalDamageWhenUseAtium(source, target, qty);
    }

    public static float getCalculateComplexDamage(IDefaultInvestedPlayerData targetCapability, IDefaultInvestedPlayerData sourceCapability, float damage) {

        int tar = 0;
        int sour = 0;
        if (targetCapability.isBurning(MetalsNBTData.ATIUM))
            tar =+1;
        if (targetCapability.isBurning(MetalsNBTData.LERASIUM))
            tar =+2;
        if (targetCapability.isBurning(MetalsNBTData.DURALUMIN))
            tar = +3;

        if (sourceCapability.isBurning(MetalsNBTData.ATIUM)){
            sour =+1;
            if (sourceCapability.isBurning(MetalsNBTData.LERASIUM))
                sour =+2;
            if (sourceCapability.isBurning(MetalsNBTData.DURALUMIN))
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

    public static float getCalculateSimpleDamage(IDefaultInvestedPlayerData targetCapability, float damage) {
        if (targetCapability.isBurning(MetalsNBTData.LERASIUM) && targetCapability.isBurning(MetalsNBTData.DURALUMIN)) { //lerasium + duralumin
            return 0;
        } else if (targetCapability.isBurning(MetalsNBTData.LERASIUM)) { //solo lerasium
            if (Math.random()<0.6) {
                return 0;
            }
        } else if (targetCapability.isBurning(MetalsNBTData.DURALUMIN)) { //solo duralumin
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

    private float internalDamageWhenUseAtium(Player source, Player target, float receivedQty) {
        qty = receivedQty;
        source.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(sourceCap -> {

            target.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(targetCap -> {

                int sourceState = 0;
                int targetState = 0;
                double percetage = 0d;

                if (sourceCap.isBurning(MetalsNBTData.ATIUM)) {
                    if (sourceCap.isBurning(MetalsNBTData.LERASIUM) && !sourceCap.isBurning(MetalsNBTData.DURALUMIN)) {
                        sourceState = 2;
                    } else if (sourceCap.isBurning(MetalsNBTData.DURALUMIN) && !sourceCap.isBurning(MetalsNBTData.LERASIUM)) {
                        sourceState = 3;
                    } else if (sourceCap.isBurning(MetalsNBTData.LERASIUM) && sourceCap.isBurning(MetalsNBTData.DURALUMIN)) {
                        sourceState = 4;
                    } else {
                        sourceState = 1;
                    }
                } else {
                    sourceState = 0;
                }

                if (targetCap.isBurning(MetalsNBTData.ATIUM)) {
                    if (targetCap.isBurning(MetalsNBTData.LERASIUM) && !targetCap.isBurning(MetalsNBTData.DURALUMIN)) {
                        targetState = 2;
                    } else if (targetCap.isBurning(MetalsNBTData.DURALUMIN) && !targetCap.isBurning(MetalsNBTData.LERASIUM)) {
                        targetState = 3;
                    } else if (targetCap.isBurning(MetalsNBTData.LERASIUM) && targetCap.isBurning(MetalsNBTData.DURALUMIN)) {
                        targetState = 4;
                    } else {
                        targetState = 1;
                    }
                } else {
                    targetState = 0;
                }

                if (targetState == 4 && sourceState == 4) {
                    percetage = 0.5d;
                } else if (targetState == sourceState || targetState == 0 || sourceState > targetState) {
                    percetage = 1d;
                } else {
                    if (targetState == 1) {
                        if (sourceState == 0) {
                            percetage = 0.5d;
                        }
                    } else if (targetState == 2) {
                        if (sourceState == 0) {
                            percetage = 0.7d;
                        } else if (sourceState == 1) {
                            percetage = 0.3d;
                        }
                    } else if (targetState == 3) {
                        if (sourceState == 0) {
                            percetage = 0.1d;
                        } else if (sourceState == 1) {
                            percetage = 0.4d;
                        } else if (sourceState == 2) {
                            percetage = 0.7d;
                        }
                    } else if (targetState == 4) {
                        if (sourceState == 0) {
                            percetage = 0d;
                        } else if (sourceState == 1 || sourceState == 2) {
                            percetage = 0.2d;
                        } else if (sourceState == 3) {
                            percetage = 0.3d;
                        }
                    }
                }
                if (Math.random() <= percetage) {
                    qty = 0;
                }
            });

        });

        return qty;
    }


}

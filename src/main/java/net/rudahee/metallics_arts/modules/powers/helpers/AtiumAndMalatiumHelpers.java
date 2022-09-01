package net.rudahee.metallics_arts.modules.powers.helpers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class AtiumAndMalatiumHelpers {

    private static boolean playerIsBurning = false;
    private static float damage = 0F;


    public static void decantAtium(PlayerEntity player) {
        player.addEffect(new EffectInstance(Effects.INVISIBILITY, 5, 1, true, true));
    }

    public static void storageAtium(PlayerEntity player) {
        player.addEffect(new EffectInstance(Effects.GLOWING, 5, 1, true, true));
    }

    private float qty = 0f;

    public static float getDamageWhenUseAtium(PlayerEntity source, PlayerEntity target, float qty) {
        return new AtiumAndMalatiumHelpers().internalDamageWhenUseAtium(source, target, qty);
    }

    private float internalDamageWhenUseAtium(PlayerEntity source, PlayerEntity target, float receivedQty) {

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
/*
    public static float atiumHit(ServerPlayerEntity playerEntity, PlayerEntity objetive, float amount) {

        playerEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(playerCapability -> {
            if (playerCapability.isBurning(MetalsNBTData.ATIUM)) {
                playerIsBurning = true;
            } else {
                playerIsBurning = false;
            }
        });
        objetive.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(objetiveCapability ->{
            if (objetiveCapability.isBurning(MetalsNBTData.ATIUM)){
                if (playerIsBurning) {
                    damage = amount;//normal hit

                } else {
                    if(Math.random()<0.5){
                        damage = 0F;    //hit with probability
                    }
                }
            }else{
                damage = amount;//normal hit
            }
        });
        return damage;
    }

    public static float atiumHitMobPlayer(PlayerEntity playerEntity, float amount) {
        List<Integer> typeOfPower = Arrays.asList(0, 1);
        Collections.shuffle(typeOfPower);
        playerEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(playerCapability ->{
            if (playerCapability.isBurning(MetalsNBTData.ATIUM)){
                if (typeOfPower.get(0)==0){
                    damage = 0F;
                }
                else {
                    damage = amount;
                }
            }else {
                damage = amount;
            }
        });
        return damage;
    }
*/

}

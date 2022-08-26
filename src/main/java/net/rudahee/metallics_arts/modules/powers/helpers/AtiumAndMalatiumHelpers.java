package net.rudahee.metallics_arts.modules.powers.helpers;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;


public class AtiumAndMalatiumHelpers {

    private static boolean playerIsBurning = false;
    private static float damage = 0F;


    public static void decantAtium(PlayerEntity player) {
        player.addEffect(new EffectInstance(Effects.INVISIBILITY, 5, 1, true,true));
    }

    public static void storageAtium(PlayerEntity player) {
        player.addEffect(new EffectInstance(Effects.GLOWING, 5, 1, true,true));
    }




    public static float atiumHit(ServerPlayerEntity playerEntity, PlayerEntity objetive, float amount) {

        playerEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(playerCapability ->{
            if (playerCapability.isBurning(MetalsNBTData.ATIUM)){
                playerIsBurning = true;
            } else{
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
        playerEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(playerCapability ->{
            if (playerCapability.isBurning(MetalsNBTData.ATIUM)){
                if (Math.random()<0.5){
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


}

package net.rudahee.metallics_arts.modules.powers.helpers.metal_helpers;


import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.rudahee.metallics_arts.modules.tags_player.InvestedCapability;
import net.rudahee.metallics_arts.data.enums.implementations.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

import java.util.Random;

public class ChromiumAndNicrosilHelpers {
    public static void drainMetalChromium(Player entityLiving) {
        entityLiving.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{
            for (MetalsNBTData metalsNBTData : data.getAllomanticPowers()){
                data.drainMetals(metalsNBTData);
                ModNetwork.sync(data, entityLiving);
            }
        });
    }


    public static void goodLuck(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.LUCK,40,15,true, true));
    }

    public static void badLuck(Player player, boolean tick) {
        player.addEffect(new MobEffectInstance(MobEffects.UNLUCK,40,15,true, true));

        if (tick){
            if (Math.random()<0.5) {
                Random a = new Random();
                switch (a.nextInt(9)) {
                    case 0:
                        player.addEffect(new MobEffectInstance(MobEffects.WITHER,40,2,true, true));
                        break;
                    case 1:
                        player.addEffect(new MobEffectInstance(MobEffects.POISON,45,2,true, true));
                        break;
                    case 2:
                        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,50,2,true, true));
                        break;
                    case 3:
                        player.addEffect(new MobEffectInstance(MobEffects.HUNGER,50,2,true, true));
                        break;
                    case 4:
                        player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,45,2,true, true));
                        break;
                    case 5:
                        player.addEffect(new MobEffectInstance(MobEffects.CONFUSION,180,4,true, true));
                        break;
                    case 6:
                        player.addEffect(new MobEffectInstance(MobEffects.LEVITATION,40,1,true, true));
                        break;
                    case 7:
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,50,2,true, true));
                        break;
                    case 8:
                        player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN,50,2,true, true));
                        break;
                }

                //SE PUEDE AGREGAR DAÑO INSTANTANEO
            }
        }
    }

    public static void drainMetalCloudChromium(ServerLevel world, AABB axisAlignedBB) {

        world.getEntitiesOfClass(Player.class, axisAlignedBB).forEach(entity -> {

            if (entity != null) {
                entity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(capability -> {
                    capability.drainMetals(MetalsNBTData.values());

                    ModNetwork.sync(capability, entity);
                });
            }



        });
    }

    public static void changeNBTinTargetForEnhanced(Player target) {
        target.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(cap -> {
            cap.setEnhanced(true);

            ModNetwork.sync(cap, target);
        });
    }
}



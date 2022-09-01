package net.rudahee.metallics_arts.modules.powers.helpers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

import java.util.Random;

public class ChromiumAndNicrosilHelpers {
    public static void drainMetalChromium(PlayerEntity entityLiving) {
        entityLiving.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{
            for (MetalsNBTData metalsNBTData : data.getAllomanticPowers()){
                data.drainMetals(metalsNBTData);
                ModNetwork.sync(data, entityLiving);
            }
        });
    }


    public static void goodLuck(PlayerEntity player) {

        player.addEffect(new EffectInstance(Effects.LUCK,40,15,true, true));
    }

    public static void badLuck(PlayerEntity player, boolean tick) {
        player.addEffect(new EffectInstance(Effects.UNLUCK,40,15,true, true));

        if (tick){
            if (Math.random()<0.5) {
                Random a = new Random();
                switch (a.nextInt(9)) {
                    case 0:
                        player.addEffect(new EffectInstance(Effects.WITHER,40,2,true, true));
                        break;
                    case 1:
                        player.addEffect(new EffectInstance(Effects.POISON,45,2,true, true));
                        break;
                    case 2:
                        player.addEffect(new EffectInstance(Effects.WEAKNESS,50,2,true, true));
                        break;
                    case 3:
                        player.addEffect(new EffectInstance(Effects.HUNGER,50,2,true, true));
                        break;
                    case 4:
                        player.addEffect(new EffectInstance(Effects.BLINDNESS,45,2,true, true));
                        break;
                    case 5:
                        player.addEffect(new EffectInstance(Effects.CONFUSION,180,4,true, true));
                        break;
                    case 6:
                        player.addEffect(new EffectInstance(Effects.LEVITATION,40,1,true, true));
                        break;
                    case 7:
                        player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN,50,2,true, true));
                        break;
                    case 8:
                        player.addEffect(new EffectInstance(Effects.DIG_SLOWDOWN,50,2,true, true));
                        break;
                }

                //SE PUEDE AGREGAR DAÑO INSTANTANEO
            }
        }
    }

    public static void drainMetalCloudChromium(World world, AxisAlignedBB axisAlignedBB) {
        world.getEntitiesOfClass(PlayerEntity.class, axisAlignedBB).forEach(entity -> {

            if (entity != null) {
                entity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(capability -> {
                    capability.drainMetals(MetalsNBTData.values());

                    ModNetwork.sync(capability, entity);
                });
            }



        });
    }

    public static void changeNBTinTargetForEnhanced(PlayerEntity target) {
        target.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(cap -> {
            cap.setExternalEnhanced(true);

            ModNetwork.sync(cap, target);
        });
    }
}



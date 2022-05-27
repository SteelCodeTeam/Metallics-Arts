package net.rudahee.metallics_arts.modules.powers.helpers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.biome.Biome;

public class DuraluminAndAluminumHelpers {


    public static void duraluminDecantingEffects(PlayerEntity player, Biome biome) {
        if(biome.getBiomeCategory().equals(Biome.Category.EXTREME_HILLS)) {

            player.addEffect(new EffectInstance(Effects.SLOW_FALLING, 40, 1, true, false));
            player.addEffect(new EffectInstance(Effects.JUMP, 40, 4, true, false));

        }else if(biome.getBiomeCategory().equals(Biome.Category.MESA)) {

            player.addEffect(new EffectInstance(Effects.SLOW_FALLING, 40, 1, true, false));
            player.addEffect(new EffectInstance(Effects.JUMP, 40, 4, true, false));

            //montañas salto 4
        }else if(biome.getBiomeCategory().equals(Biome.Category.JUNGLE)) {

            player.addEffect(new EffectInstance(Effects.JUMP, 40, 3, true, false));
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 40, 1, true, false));

        } else if(biome.getBiomeCategory().equals(Biome.Category.TAIGA)) {
            player.addEffect(new EffectInstance(Effects.JUMP, 40, 2, true, false));
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 40, 2, true, false));

        }
        else if(biome.getBiomeCategory().equals(Biome.Category.PLAINS)) {
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 40, 5, true, false));

            //velocidad 4
        } else if(biome.getBiomeCategory().equals(Biome.Category.SAVANNA)) {
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 40, 5, true, false));
            //velocidad 4
        } else if(biome.getBiomeCategory().equals(Biome.Category.BEACH)) {
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 40, 2, true, false));
            player.addEffect(new EffectInstance(Effects.JUMP, 40, 1, true, false));
        } else if(biome.getBiomeCategory().equals(Biome.Category.FOREST)) {
            player.addEffect(new EffectInstance(Effects.DIG_SPEED, 40, 2, true, false));
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 40, 2, true, false));
            //prisa minera poca
            //velocidad
        } else if(biome.getBiomeCategory().equals(Biome.Category.DESERT)) {
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 40, 3, true, false));
            player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 40, 2, true, false));
            //velocidad
            //fuerza
        } else if(biome.getBiomeCategory().equals(Biome.Category.MUSHROOM)) {
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 40, 3, true, false));
            player.addEffect(new EffectInstance(Effects.JUMP, 40, 1, true, false));
            //velocidad 3 salto 1
        } else if(biome.getBiomeCategory().equals(Biome.Category.ICY)) {
            player.addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 40, 3, true, false));
            player.addEffect(new EffectInstance(Effects.JUMP, 40, 1, true, false));
            //gracia del delfin//salto
        } else if(biome.getBiomeCategory().equals(Biome.Category.OCEAN)) {
            player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 40, 1, true, false));
            player.addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 40, 3, true, false));
            //apnea//gracia del delfin
        } else if(biome.getBiomeCategory().equals(Biome.Category.SWAMP)) {
            player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 40, 1, true, false));
            player.addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 40, 3, true, false));
            //gracia del delfin //velocidad
        } else if(biome.getBiomeCategory().equals(Biome.Category.RIVER)) {
            player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 40, 1, true, false));
            player.addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 40, 3, true, false));
            //gracia del delfin //velocidad
        } else if(biome.getBiomeCategory().equals(Biome.Category.NETHER)) {
            player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 40, 2, true, false));
            player.addEffect(new EffectInstance(Effects.DIG_SPEED, 40, 2, true, false));
            //fuerza 2
            //prisa 2
        }else if(biome.getBiomeCategory().equals(Biome.Category.THEEND)) {
            player.addEffect(new EffectInstance(Effects.SLOW_FALLING, 40, 1, true, false));
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 40, 3, true, false));
            //caida lenta//velocidad 3
        }
        else{
            System.out.println("no hay poderes papa");
        }
    }

    public static void duraluminStoringEffects(PlayerEntity player, Biome biome) {
        if(biome.getBiomeCategory().equals(Biome.Category.EXTREME_HILLS)) {

            player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 2, true, false));


        }else if(biome.getBiomeCategory().equals(Biome.Category.MESA)) {

            player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 1, true, false));
            //montañas salto 4
        }else if(biome.getBiomeCategory().equals(Biome.Category.JUNGLE)) {

            player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 1, true, false));

        } else if(biome.getBiomeCategory().equals(Biome.Category.TAIGA)) {
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 2, true, false));

        }
        else if(biome.getBiomeCategory().equals(Biome.Category.PLAINS)) {
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 5, true, false));

            //velocidad 4
        } else if(biome.getBiomeCategory().equals(Biome.Category.SAVANNA)) {
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 5, true, false));
            //velocidad 4
        } else if(biome.getBiomeCategory().equals(Biome.Category.BEACH)) {
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 2, true, false));
        } else if(biome.getBiomeCategory().equals(Biome.Category.FOREST)) {
            player.addEffect(new EffectInstance(Effects.DIG_SLOWDOWN, 40, 2, true, false));
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 2, true, false));
            //prisa minera poca
            //velocidad
        } else if(biome.getBiomeCategory().equals(Biome.Category.DESERT)) {
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 3, true, false));
            player.addEffect(new EffectInstance(Effects.WEAKNESS, 40, 2, true, false));
            //velocidad
            //fuerza
        } else if(biome.getBiomeCategory().equals(Biome.Category.MUSHROOM)) {
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 3, true, false));
            //velocidad 3 salto 1
        } else if(biome.getBiomeCategory().equals(Biome.Category.ICY)) {
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 3, true, false));
            //gracia del delfin//salto
        } else if(biome.getBiomeCategory().equals(Biome.Category.OCEAN)) {
            //AHOGAMIENTO //player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 40, 1, true, false));
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 3, true, false));
            //apnea//gracia del delfin
        } else if(biome.getBiomeCategory().equals(Biome.Category.SWAMP)) {
            //AHOGAMIENTO //player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 40, 1, true, false));
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 3, true, false));
            //gracia del delfin //velocidad
        } else if(biome.getBiomeCategory().equals(Biome.Category.RIVER)) {
            //AHOGAMIENTO //player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 40, 1, true, false));
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 3, true, false));
            //gracia del delfin //velocidad
        } else if(biome.getBiomeCategory().equals(Biome.Category.NETHER)) {
            player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 40, 2, true, false));
            player.addEffect(new EffectInstance(Effects.DIG_SPEED, 40, 2, true, false));
            //fuerza 2
            //prisa 2
        }else if(biome.getBiomeCategory().equals(Biome.Category.THEEND)) {
            player.addEffect(new EffectInstance(Effects.SLOW_FALLING, 40, 1, true, false));
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 3, true, false));
            //caida lenta//velocidad 3
        }
        else{
            System.out.println("no hay poderes papa");
        }
    }
}

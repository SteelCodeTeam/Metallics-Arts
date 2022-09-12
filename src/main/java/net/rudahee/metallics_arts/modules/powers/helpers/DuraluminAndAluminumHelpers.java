package net.rudahee.metallics_arts.modules.powers.helpers;


import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.Tags;

public class DuraluminAndAluminumHelpers {


    public static void duraluminDecantingMobEffects(Player player, Biomes biome) {

        //Tags.Biomes.IS_WATER
        //Biomes.SPARSE_JUNGLE


/*
        //player.level.getBiome(player.blockPosition())
        //if(biome.getBiomeCategory().equals(Biome.Category.EXTREME_HILLS)) {
        if(biome.equals(Biomes.WINDSWEPT_GRAVELLY_HILLS) || biome.equals(Biomes.WINDSWEPT_HILLS)) {
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 4, true, false));

        }else if(biome.getBiomeCategory().equals(Biome.Category.MESA)) {
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 4, true, false));

            //montañas salto 4
        }else if(biome.getBiomeCategory().equals(Biome.Category.JUNGLE)) {

            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 3, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 1, true, false));

        } else if(biome.getBiomeCategory().equals(Biome.Category.TAIGA)) {
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, true, false));

        }
        else if(biome.getBiomeCategory().equals(Biome.Category.PLAINS)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 5, true, false));

            //velocidad 4
        } else if(biome.getBiomeCategory().equals(Biome.Category.SAVANNA)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 5, true, false));
            //velocidad 4
        } else if(biome.getBiomeCategory().equals(Biome.Category.BEACH)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 1, true, false));
        } else if(biome.getBiomeCategory().equals(Biome.Category.FOREST)) {
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, true, false));
            //prisa minera poca
            //velocidad
        } else if(biome.getBiomeCategory().equals(Biome.Category.DESERT)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 3, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 2, true, false));
            //velocidad
            //fuerza
        } else if(biome.getBiomeCategory().equals(Biome.Category.MUSHROOM)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 3, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 1, true, false));
            //velocidad 3 salto 1
        } else if(biome.getBiomeCategory().equals(Biome.Category.ICY)) {
            player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 40, 3, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 1, true, false));
            //gracia del delfin//salto
        } else if(biome.getBiomeCategory().equals(Biome.Category.OCEAN)) {
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 40, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 40, 3, true, false));
            //apnea//gracia del delfin
        } else if(biome.getBiomeCategory().equals(Biome.Category.SWAMP)) {
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 40, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 40, 3, true, false));
            //gracia del delfin //velocidad
        } else if(biome.getBiomeCategory().equals(Biome.Category.RIVER)) {
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 40, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 40, 3, true, false));
            //gracia del delfin //velocidad
        } else if(biome.getBiomeCategory().equals(Biome.Category.NETHER)) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 2, true, false));
            //fuerza 2
            //prisa 2
        }else if(biome.getBiomeCategory().equals(Biome.Category.THEEND)) {
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 3, true, false));
            //caida lenta//velocidad 3
        }
        else{
            System.out.println("no hay poderes papa");
        }*/
    }

    public static void duraluminStoringMobEffects(Player player, Biome biome) {
        /*if(biome.getBiomeCategory().equals(Biome.Category.EXTREME_HILLS)) {

            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 2, true, false));


        }else if(biome.getBiomeCategory().equals(Biome.Category.MESA)) {

            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 1, true, false));
            //montañas salto 4
        }else if(biome.getBiomeCategory().equals(Biome.Category.JUNGLE)) {

            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 1, true, false));

        } else if(biome.getBiomeCategory().equals(Biome.Category.TAIGA)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 2, true, false));

        }
        else if(biome.getBiomeCategory().equals(Biome.Category.PLAINS)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 5, true, false));

            //velocidad 4
        } else if(biome.getBiomeCategory().equals(Biome.Category.SAVANNA)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 5, true, false));
            //velocidad 4
        } else if(biome.getBiomeCategory().equals(Biome.Category.BEACH)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 2, true, false));
        } else if(biome.getBiomeCategory().equals(Biome.Category.FOREST)) {
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 2, true, false));
            //prisa minera poca
            //velocidad
        } else if(biome.getBiomeCategory().equals(Biome.Category.DESERT)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 3, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40, 2, true, false));
            //velocidad
            //fuerza
        } else if(biome.getBiomeCategory().equals(Biome.Category.MUSHROOM)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 3, true, false));
            //velocidad 3 salto 1
        } else if(biome.getBiomeCategory().equals(Biome.Category.ICY)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 3, true, false));
            //gracia del delfin//salto
        } else if(biome.getBiomeCategory().equals(Biome.Category.OCEAN)) {
            //AHOGAMIENTO //player.addEffect(new MobEffectInstance()(MobEffects.WATER_BREATHING, 40, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 3, true, false));
            //apnea//gracia del delfin
        } else if(biome.getBiomeCategory().equals(Biome.Category.SWAMP)) {
            //AHOGAMIENTO //player.addEffect(new MobEffectInstance()(MobEffects.WATER_BREATHING, 40, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 3, true, false));
            //gracia del delfin //velocidad
        } else if(biome.getBiomeCategory().equals(Biome.Category.RIVER)) {
            //AHOGAMIENTO //player.addEffect(new MobEffectInstance()(MobEffects.WATER_BREATHING, 40, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 3, true, false));
            //gracia del delfin //velocidad
        } else if(biome.getBiomeCategory().equals(Biome.Category.NETHER)) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 2, true, false));
            //fuerza 2
            //prisa 2
        }else if(biome.getBiomeCategory().equals(Biome.Category.THEEND)) {
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 3, true, false));
            //caida lenta//velocidad 3
        }
        else{
            System.out.println("no hay poderes papa");
        }*/
    }
}

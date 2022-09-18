package net.rudahee.metallics_arts.modules.powers.helpers;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public class DuraluminAndAluminumHelpers {


    public static void duraluminDecantingMobEffects(Player player, Biomes biome) {

        /*if (biome.equals(Biomes.JUNGLE) || biome.equals(Biomes.BAMBOO_JUNGLE) ||biome.equals(Biomes.SPARSE_JUNGLE)) {

            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 3, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 1, true, false));

        } else if (biome.equals(Biomes.DESERT)) {

            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 3, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 2, true, false));

        } else if (biome.equals(Biomes.PLAINS) || biome.equals(Biomes.SNOWY_PLAINS) || biome.equals(Biomes.SUNFLOWER_PLAINS) ||
                biome.equals(Biomes.MEADOW) || biome.equals(Biomes.MUSHROOM_FIELDS)) {

            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 5, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 2, true, false));

        } else if (biome.equals(Biomes.SWAMP) || biome.equals(Biomes.MANGROVE_SWAMP)) {

            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 40, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 40, 3, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 2, true, false));

        } else if (biome.equals(Biomes.SAVANNA) || biome.equals(Biomes.SAVANNA_PLATEAU) || biome.equals(Biomes.WINDSWEPT_SAVANNA)) {

            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 5, true, false));

        } else if (biome.equals(Biomes.FOREST) || biome.equals(Biomes.FLOWER_FOREST) || biome.equals(Biomes.BIRCH_FOREST) || biome.equals(Biomes.DARK_FOREST) ||
                biome.equals(Biomes.OLD_GROWTH_BIRCH_FOREST) || biome.equals(Biomes.WINDSWEPT_FOREST)) {

            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, true, false));

        } else if (biome.equals(Biomes.TAIGA) || biome.equals(Biomes.SNOWY_TAIGA) || biome.equals(Biomes.OLD_GROWTH_SPRUCE_TAIGA) ||
                biome.equals(Biomes.OLD_GROWTH_PINE_TAIGA)) {

            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, true, false));

        } else if (biome.equals(Biomes.BADLANDS) || biome.equals(Biomes.ERODED_BADLANDS) || biome.equals(Biomes.WOODED_BADLANDS)) {

            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 4, true, false));

        } else if (biome.equals(Biomes.FROZEN_PEAKS) || biome.equals(Biomes.JAGGED_PEAKS) || biome.equals(Biomes.STONY_PEAKS)) {

            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 2, true, false));

        } else if (biome.equals(Biomes.RIVER) || biome.equals(Biomes.WARM_OCEAN) || biome.equals(Biomes.LUKEWARM_OCEAN) ||
                biome.equals(Biomes.DEEP_LUKEWARM_OCEAN) || biome.equals(Biomes.OCEAN) || biome.equals(Biomes.DEEP_COLD_OCEAN) ||
                biome.equals(Biomes.DEEP_OCEAN) || biome.equals(Biomes.COLD_OCEAN)) {

            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 40, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 40, 3, true, false));

        } else if (biome.equals(Biomes.FROZEN_OCEAN) || biome.equals(Biomes.DEEP_FROZEN_OCEAN) || biome.equals(Biomes.FROZEN_RIVER) ||
                biome.equals(Biomes.ICE_SPIKES)) {

            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 2, true, false));

        } else if (biome.equals(Biomes.WINDSWEPT_HILLS) || biome.equals(Biomes.WINDSWEPT_GRAVELLY_HILLS)) {

            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 4, true, false));

        } else if (biome.equals(Biomes.BEACH) || biome.equals(Biomes.STONY_SHORE) || biome.equals(Biomes.SNOWY_BEACH)) {

            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 1, true, false));

        } else if (biome.equals(Biomes.GROVE) || biome.equals(Biomes.SNOWY_SLOPES)) {

        } else if (biome.equals(Biomes.DRIPSTONE_CAVES) || biome.equals(Biomes.LUSH_CAVES) || biome.equals(Biomes.DEEP_DARK)) {

        } else if (biome.equals(Biomes.WARPED_FOREST) || biome.equals(Biomes.CRIMSON_FOREST)) {

            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 3, true, false));

        } else if (biome.equals(Biomes.NETHER_WASTES) || biome.equals(Biomes.SOUL_SAND_VALLEY) || biome.equals(Biomes.BASALT_DELTAS)) {

            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 1, true, false));

        } else if (biome.equals(Biomes.THE_END)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 3, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 1, true, false));

        } else if (biome.equals(Biomes.END_BARRENS) || biome.equals(Biomes.SMALL_END_ISLANDS)) {

            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 2, true, false));

        } else {
            System.out.println("Bioma no registrado");
        }*/



        /*
biome.equals(Biomes.END_HIGHLANDS)
biome.equals(Biomes.END_MIDLANDS)
         */


/*

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

package net.rudahee.metallics_arts.modules.powers.helpers;


import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public class DuraluminAndAluminumHelpers {
    public static void duraluminDecantingMobEffects(Player player, ResourceKey<Biome>  biome) {

        if (biome.equals(Biomes.JUNGLE) || biome.equals(Biomes.BAMBOO_JUNGLE) ||biome.equals(Biomes.SPARSE_JUNGLE)) {
            /** JUNGLAS */
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 3, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 1, true, false));

        } else if (biome.equals(Biomes.DESERT)) {
            /** DESIERTO */
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 2, true, false));

        } else if (biome.equals(Biomes.PLAINS) || biome.equals(Biomes.SNOWY_PLAINS) || biome.equals(Biomes.SUNFLOWER_PLAINS) ||
                biome.equals(Biomes.MEADOW) || biome.equals(Biomes.MUSHROOM_FIELDS)) {

            /** PLANICIES */
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 2, true, false));

        } else if (biome.equals(Biomes.SWAMP) || biome.equals(Biomes.MANGROVE_SWAMP)) {
            /** PANTANOS */
            player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 40, 3, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 2, true, false));

        } else if (biome.equals(Biomes.SAVANNA) || biome.equals(Biomes.SAVANNA_PLATEAU) || biome.equals(Biomes.WINDSWEPT_SAVANNA)) {
            /** SABANAS */
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 5, true, false));

        } else if (biome.equals(Biomes.FOREST) || biome.equals(Biomes.FLOWER_FOREST) || biome.equals(Biomes.BIRCH_FOREST) || biome.equals(Biomes.DARK_FOREST) ||
                biome.equals(Biomes.OLD_GROWTH_BIRCH_FOREST) || biome.equals(Biomes.WINDSWEPT_FOREST) || biome.equals(Biomes.GROVE)) {

            /** BOSQUES */
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 1, true, false));

        } else if (biome.equals(Biomes.TAIGA) || biome.equals(Biomes.SNOWY_TAIGA) || biome.equals(Biomes.OLD_GROWTH_SPRUCE_TAIGA) ||
                biome.equals(Biomes.OLD_GROWTH_PINE_TAIGA)) {

            /** TAIGAS */
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, true, false));

        } else if (biome.equals(Biomes.BADLANDS) || biome.equals(Biomes.ERODED_BADLANDS) || biome.equals(Biomes.WOODED_BADLANDS)) {

            /** PARAMOS */
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 2, true, false));

        } else if (biome.equals(Biomes.FROZEN_PEAKS) || biome.equals(Biomes.JAGGED_PEAKS) || biome.equals(Biomes.STONY_PEAKS)) {

            /** PICOS */
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 1, true, false));

        } else if (biome.equals(Biomes.RIVER) || biome.equals(Biomes.WARM_OCEAN) || biome.equals(Biomes.LUKEWARM_OCEAN) ||
                biome.equals(Biomes.DEEP_LUKEWARM_OCEAN) || biome.equals(Biomes.OCEAN) || biome.equals(Biomes.DEEP_COLD_OCEAN) ||
                biome.equals(Biomes.DEEP_OCEAN) || biome.equals(Biomes.COLD_OCEAN)) {

            /** MASAS DE SIN HIELO */
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 40, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 40, 2, true, false));

        } else if (biome.equals(Biomes.FROZEN_OCEAN) || biome.equals(Biomes.DEEP_FROZEN_OCEAN) || biome.equals(Biomes.FROZEN_RIVER) ||
                biome.equals(Biomes.ICE_SPIKES)) {

            /** MASAS DE AGUA CON HIELO */
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 2, true, false));

        } else if (biome.equals(Biomes.WINDSWEPT_HILLS) || biome.equals(Biomes.WINDSWEPT_GRAVELLY_HILLS) || biome.equals(Biomes.SNOWY_SLOPES)) {

            /** COLINAS / MONTAÑAS? */
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 4, true, false));

        } else if (biome.equals(Biomes.BEACH) || biome.equals(Biomes.STONY_SHORE) || biome.equals(Biomes.SNOWY_BEACH)) {

            /** PLAYAS */
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 1, true, false));

        }  else if (biome.equals(Biomes.DRIPSTONE_CAVES) || biome.equals(Biomes.LUSH_CAVES) || biome.equals(Biomes.DEEP_DARK)) {
            /** CAVERNAS */

        } else if (biome.equals(Biomes.WARPED_FOREST) || biome.equals(Biomes.CRIMSON_FOREST)) {
            /** BOSQUES DEL NETHER*/
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 3, true, false));

        } else if (biome.equals(Biomes.NETHER_WASTES) || biome.equals(Biomes.SOUL_SAND_VALLEY) || biome.equals(Biomes.BASALT_DELTAS)) {
            /** BIOMAS DEL NETHER*/
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 1, true, false));

        } else if (biome.equals(Biomes.THE_END)) {
            /** ZONA DEL DRAGON */
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 1, true, true));

        } else if (biome.equals(Biomes.END_BARRENS) || biome.equals(Biomes.SMALL_END_ISLANDS) || biome.equals(Biomes.END_HIGHLANDS) || biome.equals(Biomes.END_MIDLANDS)) {
            /** ISLAS DEL END */
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 1, true, true));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, true, true));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 1, true, true));
        } else {
            System.out.println("Bioma no registrado");
        }

        /** (Biomes.END_BARRENS) -> limite de islas*/

    }

    public static void duraluminStoringMobEffects(Player player, ResourceKey<Biome>  biome) {
        if (biome.equals(Biomes.JUNGLE) || biome.equals(Biomes.BAMBOO_JUNGLE) ||biome.equals(Biomes.SPARSE_JUNGLE)) {
            /** JUNGLAS */
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 1, true, false));

        } else if (biome.equals(Biomes.DESERT)) {
            /** DESIERTO */
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.POISON, 40, 1, true, false));

        } else if (biome.equals(Biomes.PLAINS) || biome.equals(Biomes.SNOWY_PLAINS) || biome.equals(Biomes.SUNFLOWER_PLAINS) ||
                biome.equals(Biomes.MEADOW) || biome.equals(Biomes.MUSHROOM_FIELDS)) {

            /** PLANICIES */
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40, 2, true, false));

        } else if (biome.equals(Biomes.SWAMP) || biome.equals(Biomes.MANGROVE_SWAMP)) {
            /** PANTANOS */
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 128, true, false));

        } else if (biome.equals(Biomes.SAVANNA) || biome.equals(Biomes.SAVANNA_PLATEAU) || biome.equals(Biomes.WINDSWEPT_SAVANNA)) {
            /** SABANAS */
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 3, true, false));

        } else if (biome.equals(Biomes.FOREST) || biome.equals(Biomes.FLOWER_FOREST) || biome.equals(Biomes.BIRCH_FOREST) || biome.equals(Biomes.DARK_FOREST) ||
                biome.equals(Biomes.OLD_GROWTH_BIRCH_FOREST) || biome.equals(Biomes.WINDSWEPT_FOREST) || biome.equals(Biomes.GROVE)) {

            /** BOSQUES */

            player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 1, true, false));

        } else if (biome.equals(Biomes.TAIGA) || biome.equals(Biomes.SNOWY_TAIGA) || biome.equals(Biomes.OLD_GROWTH_SPRUCE_TAIGA) ||
                biome.equals(Biomes.OLD_GROWTH_PINE_TAIGA)) {

            /** TAIGAS */
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 2, true, false));

        } else if (biome.equals(Biomes.BADLANDS) || biome.equals(Biomes.ERODED_BADLANDS) || biome.equals(Biomes.WOODED_BADLANDS)) {

            /** PARAMOS */
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 1, true, false));

        } else if (biome.equals(Biomes.FROZEN_PEAKS) || biome.equals(Biomes.JAGGED_PEAKS) || biome.equals(Biomes.STONY_PEAKS)) {

            /** PICOS */
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 128, true, false));

        } else if (biome.equals(Biomes.RIVER) || biome.equals(Biomes.WARM_OCEAN) || biome.equals(Biomes.LUKEWARM_OCEAN) ||
                biome.equals(Biomes.DEEP_LUKEWARM_OCEAN) || biome.equals(Biomes.OCEAN) || biome.equals(Biomes.DEEP_COLD_OCEAN) ||
                biome.equals(Biomes.DEEP_OCEAN) || biome.equals(Biomes.COLD_OCEAN)) {

            /** MASAS DE SIN HIELO */
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 40, 2, true, false));

        } else if (biome.equals(Biomes.FROZEN_OCEAN) || biome.equals(Biomes.DEEP_FROZEN_OCEAN) || biome.equals(Biomes.FROZEN_RIVER) ||
                biome.equals(Biomes.ICE_SPIKES)) {

            /** MASAS DE AGUA CON HIELO */
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 128, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 40, 2, true, false));

        } else if (biome.equals(Biomes.WINDSWEPT_HILLS) || biome.equals(Biomes.WINDSWEPT_GRAVELLY_HILLS) || biome.equals(Biomes.SNOWY_SLOPES)) {

            /** COLINAS / MONTAÑAS? */
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 128, true, false));

        } else if (biome.equals(Biomes.BEACH) || biome.equals(Biomes.STONY_SHORE) || biome.equals(Biomes.SNOWY_BEACH)) {

            /** PLAYAS */
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 2, true, false));

        }  else if (biome.equals(Biomes.DRIPSTONE_CAVES) || biome.equals(Biomes.LUSH_CAVES) || biome.equals(Biomes.DEEP_DARK)) {
            /** CAVERNAS */

        } else if (biome.equals(Biomes.WARPED_FOREST) || biome.equals(Biomes.CRIMSON_FOREST)) {
            /** BOSQUES DEL NETHER*/
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 40, 3, true, false));

        } else if (biome.equals(Biomes.NETHER_WASTES) || biome.equals(Biomes.SOUL_SAND_VALLEY) || biome.equals(Biomes.BASALT_DELTAS)) {
            /** BIOMAS DEL NETHER*/
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 2, true, false));

        } else if (biome.equals(Biomes.THE_END)) {
            /** ZONA DEL DRAGON */
            player.addEffect(new MobEffectInstance(MobEffects.POISON, 40, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 40, 1, true, true));

        } else if (biome.equals(Biomes.END_BARRENS) || biome.equals(Biomes.SMALL_END_ISLANDS) || biome.equals(Biomes.END_HIGHLANDS) || biome.equals(Biomes.END_MIDLANDS)) {
            /** ISLAS DEL END */
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 2, true, true));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 128, true, true));
        } else {
            System.out.println("Bioma no registrado");
        }
    }
}

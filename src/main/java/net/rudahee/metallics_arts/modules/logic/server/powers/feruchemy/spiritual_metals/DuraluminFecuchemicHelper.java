package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.server_events.OnWorldTickEvent;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.OnTickUtils;
import net.rudahee.metallics_arts.modules.effects.ModEffects;
import net.rudahee.metallics_arts.utils.MathUtils;

import java.util.function.Supplier;

/**
 * Helper class containing the methods and implementations for using feruchemical Duralumin.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class DuraluminFecuchemicHelper extends AbstractFechuchemicHelper {

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Duralumin: will effects depending on the biome the target player
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#tapPower(Player)
     */
    @Override
    public void tapPower(Player player) {
        //if (MathUtils.isDivisibleBy30(OnWorldTickEvent.getActualTick())) {

            ResourceKey<Biome> biome = player.getLevel().getBiome(player.getOnPos()).unwrapKey().get();

            // if biome is jungle
            if (biome.equals(Biomes.JUNGLE) || biome.equals(Biomes.BAMBOO_JUNGLE) ||biome.equals(Biomes.SPARSE_JUNGLE)) {
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 3, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, 1, false, false));
            // if biome is desert
            } else if (biome.equals(Biomes.DESERT)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, 2, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 120, 2, false, false));
            // if biome is plains
            } else if (biome.equals(Biomes.PLAINS) || biome.equals(Biomes.SNOWY_PLAINS) || biome.equals(Biomes.SUNFLOWER_PLAINS) ||
                    biome.equals(Biomes.MEADOW) || biome.equals(Biomes.MUSHROOM_FIELDS)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, 2, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 120, 2, false, false));
            // if biome is swamp
            } else if (biome.equals(Biomes.SWAMP) || biome.equals(Biomes.MANGROVE_SWAMP)) {
                player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 120, 3, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 2, false, false));
            // if biome is savanna
            } else if (biome.equals(Biomes.SAVANNA) || biome.equals(Biomes.SAVANNA_PLATEAU) || biome.equals(Biomes.WINDSWEPT_SAVANNA)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, 5, false, false));
            // if biome is forest
            } else if (biome.equals(Biomes.FOREST) || biome.equals(Biomes.FLOWER_FOREST) || biome.equals(Biomes.BIRCH_FOREST) || biome.equals(Biomes.DARK_FOREST) ||
                    biome.equals(Biomes.OLD_GROWTH_BIRCH_FOREST) || biome.equals(Biomes.WINDSWEPT_FOREST) || biome.equals(Biomes.GROVE)) {
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 120, 2, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 120, 1, false, false));
            // if biome is taiga
            } else if (biome.equals(Biomes.TAIGA) || biome.equals(Biomes.SNOWY_TAIGA) || biome.equals(Biomes.OLD_GROWTH_SPRUCE_TAIGA) ||
                    biome.equals(Biomes.OLD_GROWTH_PINE_TAIGA)) {
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 1, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, 2, false, false));
            // if biome is badlands
            } else if (biome.equals(Biomes.BADLANDS) || biome.equals(Biomes.ERODED_BADLANDS) || biome.equals(Biomes.WOODED_BADLANDS)) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 120, 2, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 2, false, false));
            // if biome is peaks
            } else if (biome.equals(Biomes.FROZEN_PEAKS) || biome.equals(Biomes.JAGGED_PEAKS) || biome.equals(Biomes.STONY_PEAKS)) {
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 2, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 1, false, false));
            // if biome is water without ice
            } else if (biome.equals(Biomes.RIVER) || biome.equals(Biomes.WARM_OCEAN) || biome.equals(Biomes.LUKEWARM_OCEAN) ||
                    biome.equals(Biomes.DEEP_LUKEWARM_OCEAN) || biome.equals(Biomes.OCEAN) || biome.equals(Biomes.DEEP_COLD_OCEAN) ||
                    biome.equals(Biomes.DEEP_OCEAN) || biome.equals(Biomes.COLD_OCEAN)) {
                player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 120, 1, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 120, 2, false, false));
            // if biome is water with ice
            } else if (biome.equals(Biomes.FROZEN_OCEAN) || biome.equals(Biomes.DEEP_FROZEN_OCEAN) || biome.equals(Biomes.FROZEN_RIVER) ||
                    biome.equals(Biomes.ICE_SPIKES)) {
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 2, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 120, 2, false, false));
            // if biome is hills or slopes
            } else if (biome.equals(Biomes.WINDSWEPT_HILLS) || biome.equals(Biomes.WINDSWEPT_GRAVELLY_HILLS) || biome.equals(Biomes.SNOWY_SLOPES)) {
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 1, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 4, false, false));
            // if biome is beach
            } else if (biome.equals(Biomes.BEACH) || biome.equals(Biomes.STONY_SHORE) || biome.equals(Biomes.SNOWY_BEACH)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, 2, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 1, false, false));
            // if biome is caves
            }  else if (biome.equals(Biomes.DRIPSTONE_CAVES) || biome.equals(Biomes.LUSH_CAVES) || biome.equals(Biomes.DEEP_DARK)) {
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 1, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 120, 1, false, false));
            // if biome is nether forest
            } else if (biome.equals(Biomes.WARPED_FOREST) || biome.equals(Biomes.CRIMSON_FOREST)) {
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 120, 3, false, false));
            // if biome is nether
            } else if (biome.equals(Biomes.NETHER_WASTES) || biome.equals(Biomes.SOUL_SAND_VALLEY) || biome.equals(Biomes.BASALT_DELTAS)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, 2, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 1, false, false));
            // if biome is the end (only dragon island)
            } else if (biome.equals(Biomes.THE_END)) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 120, 1, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 1, false, false));
            // if biome is the end
            } else if (biome.equals(Biomes.END_BARRENS) || biome.equals(Biomes.SMALL_END_ISLANDS) || biome.equals(Biomes.END_HIGHLANDS) || biome.equals(Biomes.END_MIDLANDS)) {
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 1, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, 2, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 1, false, false));
            // otherwise, if the biome is not registered
            } else {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, 2, false, false));
            }
       // }

        ModEffects.giveFeruchemicalTapEffect(player, MetalTagEnum.DURALUMIN);
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Duralumin: will effects depending on the biome the target player
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#storagePower(Player)
     */
    @Override
    public void storagePower(Player player) {
        //if (MathUtils.isDivisibleBy30(OnWorldTickEvent.getActualTick())) {

            ResourceKey<Biome> biome = player.getLevel().getBiome(player.getOnPos()).unwrapKey().get();

            // if biome is jungle
            if (biome.equals(Biomes.JUNGLE) || biome.equals(Biomes.BAMBOO_JUNGLE) ||biome.equals(Biomes.SPARSE_JUNGLE)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 1, false, false));
            // if biome is desert
            } else if (biome.equals(Biomes.DESERT)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 2, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 120, 2, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.POISON, 120, 1, false, false));
            // if biome is plains
            } else if (biome.equals(Biomes.PLAINS) || biome.equals(Biomes.SNOWY_PLAINS) || biome.equals(Biomes.SUNFLOWER_PLAINS) ||
                    biome.equals(Biomes.MEADOW) || biome.equals(Biomes.MUSHROOM_FIELDS)) {
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 2, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 120, 2, false, false));
            // if biome is swamp
            } else if (biome.equals(Biomes.SWAMP) || biome.equals(Biomes.MANGROVE_SWAMP)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 2, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 128, false, false));
            // if biome is savanna
            } else if (biome.equals(Biomes.SAVANNA) || biome.equals(Biomes.SAVANNA_PLATEAU) || biome.equals(Biomes.WINDSWEPT_SAVANNA)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 3, false, false));
            // if biome is forest
            } else if (biome.equals(Biomes.FOREST) || biome.equals(Biomes.FLOWER_FOREST) || biome.equals(Biomes.BIRCH_FOREST) || biome.equals(Biomes.DARK_FOREST) ||
                    biome.equals(Biomes.OLD_GROWTH_BIRCH_FOREST) || biome.equals(Biomes.WINDSWEPT_FOREST) || biome.equals(Biomes.GROVE)) {
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 120, 2, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.WITHER, 120, 1, false, false));
            // if biome is taiga
            } else if (biome.equals(Biomes.TAIGA) || biome.equals(Biomes.SNOWY_TAIGA) || biome.equals(Biomes.OLD_GROWTH_SPRUCE_TAIGA) ||
                    biome.equals(Biomes.OLD_GROWTH_PINE_TAIGA)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 2, false, false));
            // if biome is badlands
            } else if (biome.equals(Biomes.BADLANDS) || biome.equals(Biomes.ERODED_BADLANDS) || biome.equals(Biomes.WOODED_BADLANDS)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 1, false, false));
            // if biome is peaks
            } else if (biome.equals(Biomes.FROZEN_PEAKS) || biome.equals(Biomes.JAGGED_PEAKS) || biome.equals(Biomes.STONY_PEAKS)) {
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 128, false, false));
            // if biome is water without ice
            } else if (biome.equals(Biomes.RIVER) || biome.equals(Biomes.WARM_OCEAN) || biome.equals(Biomes.LUKEWARM_OCEAN) ||
                    biome.equals(Biomes.DEEP_LUKEWARM_OCEAN) || biome.equals(Biomes.OCEAN) || biome.equals(Biomes.DEEP_COLD_OCEAN) ||
                    biome.equals(Biomes.DEEP_OCEAN) || biome.equals(Biomes.COLD_OCEAN)) {
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 120, 2, false, false));
            // if biome is water with ice
            } else if (biome.equals(Biomes.FROZEN_OCEAN) || biome.equals(Biomes.DEEP_FROZEN_OCEAN) || biome.equals(Biomes.FROZEN_RIVER) ||
                    biome.equals(Biomes.ICE_SPIKES)) {
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 128, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 120, 2, false, false));
            // if biome is water hills or slopes
            } else if (biome.equals(Biomes.WINDSWEPT_HILLS) || biome.equals(Biomes.WINDSWEPT_GRAVELLY_HILLS) || biome.equals(Biomes.SNOWY_SLOPES)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 1, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 128, false, false));
            // if biome is beach
            } else if (biome.equals(Biomes.BEACH) || biome.equals(Biomes.STONY_SHORE) || biome.equals(Biomes.SNOWY_BEACH)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 2, false, false));
            // if biome is caves
            }  else if (biome.equals(Biomes.DRIPSTONE_CAVES) || biome.equals(Biomes.LUSH_CAVES) || biome.equals(Biomes.DEEP_DARK)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 1, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 120, 1, false, false));
            // if biome is nether forest
            } else if (biome.equals(Biomes.WARPED_FOREST) || biome.equals(Biomes.CRIMSON_FOREST)) {
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 120, 3, false, false));
            // if biome is nether
            } else if (biome.equals(Biomes.NETHER_WASTES) || biome.equals(Biomes.SOUL_SAND_VALLEY) || biome.equals(Biomes.BASALT_DELTAS)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 2, false, false));
            // if biome is the end (only dragon island)
            } else if (biome.equals(Biomes.THE_END)) {
                player.addEffect(new MobEffectInstance(MobEffects.POISON, 120, 1, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 120, 1, false, false));
            // if biome is the end
            } else if (biome.equals(Biomes.END_BARRENS) || biome.equals(Biomes.SMALL_END_ISLANDS) || biome.equals(Biomes.END_HIGHLANDS) || biome.equals(Biomes.END_MIDLANDS)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 2, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 128, false, false));
            // otherwise, if the biome is not registered
            } else {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 2, false, false));
            }
       // }

        ModEffects.giveFeruchemicalStorageEffect(player,MetalTagEnum.DURALUMIN);
    }

    /**
     * Returns an instance of DuraluminFecuchemicHelper using a factory method pattern.
     * This method allows you to create instances of DuraluminFecuchemicHelper with a consistent interface.
     *
     * @return a Supplier that returns a new instance of DuraluminFecuchemicHelper when called
     */
}

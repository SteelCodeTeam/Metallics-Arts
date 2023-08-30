package net.rudahee.metallics_arts.data.configs;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.rudahee.metallics_arts.setup.registries.generation_old.ModGeodeGenerationRegister;

/**
 * Class that defines how geodes will be generated in the world. Implements BiomeModifier. implements BiomeModifier
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see CustomGeodeConfig
 * @see BiomeModifier
 */
/*
public record GeodeGenerationConfig(HolderSet<Biome> biomes, Holder<PlacedFeature> feature) implements BiomeModifier {

    /**
     * This method is used to add geode in world generation. so, It's used to modify biomes generation.
     *
     * @param biomes holder with biomes to modify.
     * @param phase phase the event.
     * @param builder object used to modify biomes generation options.

    @Override
    public void modify(Holder<Biome> biomes, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        // add a feature to all specified biomes
        if (phase == Phase.ADD) {
            builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, feature);
        }
    }

    /**
     * This return geode's codec.
     *
     * @return Codec<? extends BiomeModifier>
     */
   /* @Override
    public Codec<? extends BiomeModifier> codec() {
        return ModGeodeGenerationRegister.GEODE_CODEC.get();
    }
}*/

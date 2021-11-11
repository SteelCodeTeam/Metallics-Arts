package net.rudahee.metallics_arts.world;

import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.rudahee.metallics_arts.setup.ModBlock;


public class OreGeneration {
    public static void generateOres (final BiomeLoadingEvent event){
        ModBlock.BLOCK_METAL_ORES.forEach((name,block) -> {
            OreFeatureConfig oreFeatureConfig = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, block.defaultBlockState(),10);

            ConfiguredPlacement<TopSolidRangeConfig> configuredPlacement = Placement.RANGE.configured(new TopSolidRangeConfig(10,10,30));

            ConfiguredFeature<?, ?> oreFeature = registerOreFeature(block,oreFeatureConfig);

            event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, oreFeature);
        });
    }

    private static ConfiguredFeature<?, ?> registerOreFeature (Block block, OreFeatureConfig oreFeatureConfig){
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, block.getRegistryName(),Feature.ORE.configured(oreFeatureConfig).count(5).chance(10).range(5));
    }
}


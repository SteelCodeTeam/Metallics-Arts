package net.rudahee.metallics_arts.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.rudahee.metallics_arts.setup.ModBlock;
import net.rudahee.metallics_arts.setup.enums.MetalGenerationData;

import java.util.Arrays;
import java.util.List;


public class OreGeneration {
    public static void generateOres (final BiomeLoadingEvent event){

        List<MetalGenerationData> metalGenerationList = Arrays.asList(MetalGenerationData.values());

        metalGenerationList.forEach( block -> {

            if(!(event.getCategory().equals(Biome.Category.NETHER) || event.getCategory().equals(Biome.Category.THEEND))) {
                generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                        ModBlock.BLOCK_METAL_ORES.get(block.getMetalNameLower()).defaultBlockState(),
                        block.getVeinSize(), block.getMinHeight(), block.getMaxHeigth(), block.getAmountPerChunk());
            }
        });
    }

    private static void generateOre(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state, int veinSize, int minHeight, int maxHeight, int amountPerChunk) {
        settings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.configured(new OreFeatureConfig(fillerType, state, veinSize))
                        .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(minHeight, 0, maxHeight)))
                        .squared().count(amountPerChunk));

    }
}

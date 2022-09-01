package net.rudahee.metallics_arts.modules.world;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.rudahee.metallics_arts.setup.enums.metals.MetalGenerationData;

import java.util.Arrays;
import java.util.List;

public class OreGeneration {
    /*public static void generateOres (final BiomeLoadingEvent event){

        List<MetalGenerationData> metalGenerationList = Arrays.asList(MetalGenerationData.values());

        metalGenerationList.forEach( block -> {
            if(!(event.getCategory().equals(Biome.Category.NETHER) || event.getCategory().equals(Biome.Category.THEEND))) {
                generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                        block.getBlock().defaultBlockState(),
                        block.getVeinSize(), block.getMinHeight(), block.getMaxHeigth(), block.getAmountPerChunk());
            }
        });
    }

    private static void generateOre(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state, int veinSize, int minHeight, int maxHeight, int amountPerChunk) {
        settings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.configured(new OreFeatureConfig(fillerType, state, veinSize))
                        .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(minHeight, 0, maxHeight)))
                        .squared().count(amountPerChunk));

    }*/
}

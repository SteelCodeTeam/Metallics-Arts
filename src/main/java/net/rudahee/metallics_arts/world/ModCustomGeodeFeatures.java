package net.rudahee.metallics_arts.world;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.rudahee.metallics_arts.setup.registries.ModBlock;

import java.util.List;

public class ModCustomGeodeFeatures {

    public static final Holder<ConfiguredFeature<GeodeConfiguration, ?>> ATIUM_GEODE =
            register(
                    "atium_geode",
                    Feature.GEODE,
                    new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                            BlockStateProvider.simple(Blocks.COBBLESTONE),
                            BlockStateProvider.simple(ModBlock.BUDDING_ATIUM.get()),
                            BlockStateProvider.simple(Blocks.CALCITE),
                            BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                            List.of(ModBlock.SMALL_ATIUM_BUD.get().defaultBlockState(),
                                    ModBlock.MEDIUM_ATIUM_BUD.get().defaultBlockState(),
                                    ModBlock.LARGE_ATIUM_BUD.get().defaultBlockState(),
                                    ModBlock.ATIUM_CLUSTER.get().defaultBlockState()),
                            BlockTags.FEATURES_CANNOT_REPLACE,
                            BlockTags.GEODE_INVALID_BLOCKS),
                            new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                            new GeodeCrackSettings(0.95D, 2.0D, 2),
                            0.35D, 0.083D, true, UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2),
                            -16, 16, 0.05D, 1
                    ));

    public static final Holder<ConfiguredFeature<GeodeConfiguration, ?>> LERASIUM_GEODE =
            register(
                    "lerasium_geode",
                    Feature.GEODE,
                    new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                            BlockStateProvider.simple(Blocks.COBBLESTONE),
                            BlockStateProvider.simple(ModBlock.BUDDING_LERASIUM.get()),
                            BlockStateProvider.simple(Blocks.CALCITE),
                            BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                            List.of(ModBlock.SMALL_LERASIUM_BUD.get().defaultBlockState(),
                                    ModBlock.MEDIUM_LERASIUM_BUD.get().defaultBlockState(),
                                    ModBlock.LARGE_LERASIUM_BUD.get().defaultBlockState(),
                                    ModBlock.LERASIUM_CLUSTER.get().defaultBlockState()),
                            BlockTags.FEATURES_CANNOT_REPLACE,
                            BlockTags.GEODE_INVALID_BLOCKS),
                            new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                            new GeodeCrackSettings(0.95D, 2.0D, 2),
                            0.35D, 0.083D, true, UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2),
                            -16, 16, 0.05D, 1
                    ));



    public static final Holder<ConfiguredFeature<GeodeConfiguration, ?>> ETTMETAL_GEODE =
            register(
                    "lerasium_geode",
                    Feature.GEODE,
                    new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                            BlockStateProvider.simple(Blocks.COBBLESTONE),
                            BlockStateProvider.simple(ModBlock.BUDDING_ETTMETAL.get()),
                            BlockStateProvider.simple(Blocks.CALCITE),
                            BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                            List.of(ModBlock.SMALL_ETTMETAL_BUD.get().defaultBlockState(),
                                    ModBlock.MEDIUM_ETTMETAL_BUD.get().defaultBlockState(),
                                    ModBlock.LARGE_ETTMETAL_BUD.get().defaultBlockState(),
                                    ModBlock.ETTMETAL_CLUSTER.get().defaultBlockState()),
                            BlockTags.FEATURES_CANNOT_REPLACE,
                            BlockTags.GEODE_INVALID_BLOCKS),
                            new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                            new GeodeCrackSettings(0.95D, 2.0D, 2),
                            0.35D, 0.083D, true, UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2),
                            -16, 16, 0.05D, 1
                    ));


    public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(String p_206489_, F p_206490_, FC p_206491_) {
        return BuiltinRegistries.registerExact(BuiltinRegistries.CONFIGURED_FEATURE, p_206489_, new ConfiguredFeature<>(p_206490_, p_206491_));
    }


}

package net.rudahee.metallics_arts.world;

import com.google.common.base.Suppliers;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.registries.ModBlock;

import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, MetallicsArts.MOD_ID);

    /** STONE **/
    public static final Supplier<List<OreConfiguration.TargetBlockState>> ZINC_REPLACEMENT_STONE = Suppliers.memoize(() -> List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlock.BLOCK_METAL_ORES.get("zinc").defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ZINC_ORE_GENERATION_STONE = CONFIGURED_FEATURES.register("zinc_ore_generation_stone", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ZINC_REPLACEMENT_STONE.get(), 10, 0.5f)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> TIN_REPLACEMENT_STONE = Suppliers.memoize(() -> List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlock.BLOCK_METAL_ORES.get("tin").defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> TIN_ORE_GENERATION_STONE = CONFIGURED_FEATURES.register("tin_ore_generation_stone", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(TIN_REPLACEMENT_STONE.get(), 10, 0.5f)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> ALUMINUM_REPLACEMENT_STONE = Suppliers.memoize(() -> List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlock.BLOCK_METAL_ORES.get("aluminum").defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ALUMINUM_ORE_GENERATION_STONE = CONFIGURED_FEATURES.register("aluminum_ore_generation_stone", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ALUMINUM_REPLACEMENT_STONE.get(), 10, 0.5f)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> LEAD_REPLACEMENT_STONE = Suppliers.memoize(() -> List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlock.BLOCK_METAL_ORES.get("lead").defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> LEAD_ORE_GENERATION_STONE = CONFIGURED_FEATURES.register("lead_ore_generation_stone", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(LEAD_REPLACEMENT_STONE.get(), 10, 0.5f)));


    /** DEEPSLATE **/
    public static final Supplier<List<OreConfiguration.TargetBlockState>> CADMIUM_REPLACEMENT_DEEPSLATE = Suppliers.memoize(() -> List.of(OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlock.BLOCK_METAL_DEEPSLATE_ORES.get("cadmium").defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CADMIUM_ORE_GENERATION_DEEPSLATE = CONFIGURED_FEATURES.register("cadmium_ore_generation_deepslate", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(CADMIUM_REPLACEMENT_DEEPSLATE.get(), 10, 0.5f)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> ALUMINUM_REPLACEMENT_DEEPSLATE  =
            Suppliers.memoize(
                    () -> List.of(OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES,
                            ModBlock.BLOCK_METAL_DEEPSLATE_ORES.get("aluminum").defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ALUMINUM_ORE_GENERATION_DEEPSLATE = CONFIGURED_FEATURES.register("aluminum_ore_generation_deepslate", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ALUMINUM_REPLACEMENT_DEEPSLATE.get(), 10, 0.5f)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> CHROMIUM_REPLACEMENT_DEEPSLATE  = Suppliers.memoize(() -> List.of(OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlock.BLOCK_METAL_DEEPSLATE_ORES.get("chromium").defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CHROMIUM_ORE_GENERATION_DEEPSLATE = CONFIGURED_FEATURES.register("chromium_ore_generation_deepslate", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(CHROMIUM_REPLACEMENT_DEEPSLATE.get(), 10, 0.5f)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> SILVER_REPLACEMENT_DEEPSLATE  = Suppliers.memoize(() -> List.of(OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlock.BLOCK_METAL_DEEPSLATE_ORES.get("silver").defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> SILVER_ORE_GENERATION_DEEPSLATE = CONFIGURED_FEATURES.register("silver_ore_generation_deepslate", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(SILVER_REPLACEMENT_DEEPSLATE.get(), 10, 0.5f)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> NICKEL_REPLACEMENT_DEEPSLATE = Suppliers.memoize(() -> List.of(OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlock.BLOCK_METAL_DEEPSLATE_ORES.get("nickel").defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> NICKEL_ORE_GENERATION_DEEPSLATE = CONFIGURED_FEATURES.register("nickel_ore_generation_deepslate", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NICKEL_REPLACEMENT_DEEPSLATE.get(), 10, 0.5f)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> LEAD_REPLACEMENT_DEEPSLATE  =
            Suppliers.memoize(() -> List.of(OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlock.BLOCK_METAL_DEEPSLATE_ORES.get("lead").defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> LEAD_ORE_GENERATION_DEEPSLATE =
            CONFIGURED_FEATURES.register("lead_ore_generation_deepslate",
                    () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(LEAD_REPLACEMENT_DEEPSLATE.get(), 10, 0.5f)));

    public static final Holder<ConfiguredFeature<GeodeConfiguration, ?>> ATIUM_GEODE
            = FeatureUtils.register("atium_geode", Feature.GEODE,
            new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                    BlockStateProvider.simple(Blocks.DIAMOND_BLOCK),
                    BlockStateProvider.simple(Blocks.BUDDING_AMETHYST),
                    BlockStateProvider.simple(Blocks.CALCITE),
                    BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                    List.of(Blocks.SMALL_AMETHYST_BUD.defaultBlockState(),
                            Blocks.MEDIUM_AMETHYST_BUD.defaultBlockState(),
                            Blocks.LARGE_AMETHYST_BUD.defaultBlockState(),
                            Blocks.AMETHYST_CLUSTER.defaultBlockState()),
                    BlockTags.FEATURES_CANNOT_REPLACE,
                    BlockTags.GEODE_INVALID_BLOCKS),
                    new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                    new GeodeCrackSettings(0.95D, 2.0D, 2),
                    0.35D, 0.083D, true, UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2), -16, 16, 0.05D, 1));

    public static final Holder<ConfiguredFeature<GeodeConfiguration, ?>> LERASIUM_GEODE
            = FeatureUtils.register("lerasium_geode", Feature.GEODE,
            new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                    BlockStateProvider.simple(Blocks.GOLD_BLOCK),
                    BlockStateProvider.simple(Blocks.BUDDING_AMETHYST),//acaVaElBudding
                    BlockStateProvider.simple(Blocks.CALCITE),
                    BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                    List.of(Blocks.SMALL_AMETHYST_BUD.defaultBlockState(),
                            Blocks.MEDIUM_AMETHYST_BUD.defaultBlockState(),
                            Blocks.LARGE_AMETHYST_BUD.defaultBlockState(),
                            Blocks.AMETHYST_CLUSTER.defaultBlockState()),
                    BlockTags.FEATURES_CANNOT_REPLACE,
                    BlockTags.GEODE_INVALID_BLOCKS),
                    new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                    new GeodeCrackSettings(0.95D, 2.0D, 2),
                    0.35D, 0.083D, true, UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2), -16, 16, 0.05D, 1));


    public static final Holder<ConfiguredFeature<GeodeConfiguration, ?>> ETTMETAL_GEODE
            = FeatureUtils.register("ettmetal_geode", Feature.GEODE,
            new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                    BlockStateProvider.simple(Blocks.GLOWSTONE),
                    BlockStateProvider.simple(Blocks.BUDDING_AMETHYST),//acaVaElBudding
                    BlockStateProvider.simple(Blocks.CALCITE),
                    BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                    List.of(Blocks.SMALL_AMETHYST_BUD.defaultBlockState(),
                            Blocks.MEDIUM_AMETHYST_BUD.defaultBlockState(),
                            Blocks.LARGE_AMETHYST_BUD.defaultBlockState(),
                            Blocks.AMETHYST_CLUSTER.defaultBlockState()),
                    BlockTags.FEATURES_CANNOT_REPLACE,
                    BlockTags.GEODE_INVALID_BLOCKS),
                    new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                    new GeodeCrackSettings(0.95D, 2.0D, 2),
                    0.35D, 0.083D, true, UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2), -16, 16, 0.05D, 1));


    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }

}

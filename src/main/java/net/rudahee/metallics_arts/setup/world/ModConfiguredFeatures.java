package net.rudahee.metallics_arts.setup.world;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

import java.util.List;

public class ModConfiguredFeatures {

    private ModConfiguredFeatures() {
        throw new IllegalStateException("Class can't be instantiated");
    }

    /*
     * RULE REPLACEMENT
     */
    public static final RuleTest DEEPSLATE_REPLACE_RULE = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    public static final RuleTest STONE_REPLACE_RULE = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);

    /*
     *  GEODES
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> ATIUM_GEODE_KEY = registerKey("atium_geode_key");
    public static final GeodeConfiguration ATIUM_GEODE_CONFIG = new GeodeConfiguration(
            new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                    BlockStateProvider.simple(ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.get(MetalTagEnum.ATIUM.getGemNameLower())),
                    BlockStateProvider.simple(ModBlocksRegister.BUDDING_ATIUM.get()),
                    BlockStateProvider.simple(Blocks.CALCITE),
                    BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                    List.of(ModBlocksRegister.SMALL_ATIUM_BUD.get().defaultBlockState(),
                            ModBlocksRegister.MEDIUM_ATIUM_BUD.get().defaultBlockState(),
                            ModBlocksRegister.LARGE_ATIUM_BUD.get().defaultBlockState(),
                            ModBlocksRegister.ATIUM_CLUSTER.get().defaultBlockState()),
                    BlockTags.FEATURES_CANNOT_REPLACE,
                    BlockTags.GEODE_INVALID_BLOCKS),
            new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
            new GeodeCrackSettings(0.95D, 2.0D, 2),
            0.35D, 0.083D,
            true, UniformInt.of(4, 5),
            UniformInt.of(3, 4),
            UniformInt.of(1, 2),
            -16, 16, 0.05D, 1);

    public static final ResourceKey<ConfiguredFeature<?, ?>> LERASIUM_GEODE_KEY = registerKey("lerasium_geode_key");
    public static final GeodeConfiguration LERASIUM_GEODE_CONFIG = new GeodeConfiguration(
            new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                BlockStateProvider.simple(ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.get(MetalTagEnum.LERASIUM.getGemNameLower())),
                BlockStateProvider.simple(ModBlocksRegister.BUDDING_LERASIUM.get()),
                BlockStateProvider.simple(Blocks.CALCITE),
                BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                List.of(ModBlocksRegister.SMALL_LERASIUM_BUD.get().defaultBlockState(),
                        ModBlocksRegister.MEDIUM_LERASIUM_BUD.get().defaultBlockState(),
                        ModBlocksRegister.LARGE_LERASIUM_BUD.get().defaultBlockState(),
                        ModBlocksRegister.LERASIUM_CLUSTER.get().defaultBlockState()),
                BlockTags.FEATURES_CANNOT_REPLACE,
                BlockTags.GEODE_INVALID_BLOCKS),
            new GeodeLayerSettings(1.4D, 2D, 3.2D, 4.2D),
            new GeodeCrackSettings(0.95D, 2.0D, 2),
            0.35D, 0.083D, true, UniformInt.of(4, 5),
            UniformInt.of(2, 4),
            UniformInt.of(1, 2),
            -16, 16, 0.05D, 1);

    public static final ResourceKey<ConfiguredFeature<?, ?>> ETTMETAL_GEODE_KEY = registerKey("ettmetal_geode_key");
    public static final GeodeConfiguration ETTMETAL_GEODE_CONFIG = new GeodeConfiguration(
            new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                BlockStateProvider.simple(ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.get(MetalTagEnum.ETTMETAL.getGemNameLower())),
                BlockStateProvider.simple(ModBlocksRegister.BUDDING_ETTMETAL.get()),
                BlockStateProvider.simple(Blocks.CALCITE),
                BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                List.of(ModBlocksRegister.SMALL_ETTMETAL_BUD.get().defaultBlockState(),
                        ModBlocksRegister.MEDIUM_ETTMETAL_BUD.get().defaultBlockState(),
                        ModBlocksRegister.LARGE_ETTMETAL_BUD.get().defaultBlockState(),
                        ModBlocksRegister.ETTMETAL_CLUSTER.get().defaultBlockState()),
                BlockTags.FEATURES_CANNOT_REPLACE,
                BlockTags.GEODE_INVALID_BLOCKS),
            new GeodeLayerSettings(1.2D, 1.4D, 3.2D, 4.2D),
            new GeodeCrackSettings(0.95D, 2.0D, 2),
            0.35D, 0.083D, true, UniformInt.of(2, 3), UniformInt.of(2, 2), UniformInt.of(1, 2),
            -16, 16, 0.05D, 1);

    /*
     * STONE REPLACEMENTS
     */

    public static final List<OreConfiguration.TargetBlockState> TIN_ORE_CONFIG_STONE =  List.of(OreConfiguration.target(STONE_REPLACE_RULE, ModBlocksRegister.BLOCK_METAL_ORES.get("tin").defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> TIN_ORE_STONE_KEY = registerKey("tin_ore_stone_key");

    public static final List<OreConfiguration.TargetBlockState> ZINC_ORE_CONFIG_STONE = List.of(OreConfiguration.target(STONE_REPLACE_RULE, ModBlocksRegister.BLOCK_METAL_ORES.get("zinc").defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> ZINC_ORE_STONE_KEY = registerKey("zinc_ore_stone_key");

    public static final List<OreConfiguration.TargetBlockState> ALUMINUM_ORE_CONFIG_STONE = List.of(OreConfiguration.target(STONE_REPLACE_RULE, ModBlocksRegister.BLOCK_METAL_ORES.get("aluminum").defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALUMINUM_ORE_STONE_KEY = registerKey("aluminum_ore_stone_key");

    public static final List<OreConfiguration.TargetBlockState> LEAD_ORE_CONFIG_STONE = List.of(OreConfiguration.target(STONE_REPLACE_RULE, ModBlocksRegister.BLOCK_METAL_ORES.get("lead").defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEAD_ORE_STONE_KEY = registerKey("lead_ore_stone_key");


    /*
     * DEEPSLATE REPLACEMENTS
     */

    public static final List<OreConfiguration.TargetBlockState> CADMIUM_ORE_CONFIG_DEEPSLATE = List.of(OreConfiguration.target(DEEPSLATE_REPLACE_RULE, ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get("cadmium").defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> CADMIUM_ORE_DEEPSLATE_KEY = registerKey("cadmium_ore_deepslate_key");

    public static final List<OreConfiguration.TargetBlockState> ALUMINUM_ORE_CONFIG_DEEPSLATE  = List.of(OreConfiguration.target(DEEPSLATE_REPLACE_RULE, ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get("aluminum").defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALUMINUM_ORE_DEEPSLATE_KEY = registerKey("aluminum_ore_deepslate_key");

    public static final List<OreConfiguration.TargetBlockState> CHROMIUM_ORE_CONFIG_DEEPSLATE  = List.of(OreConfiguration.target(DEEPSLATE_REPLACE_RULE, ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get("chromium").defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHROMIUM_ORE_DEEPSLATE_KEY = registerKey("chromium_ore_deepslate_key");

    public static final List<OreConfiguration.TargetBlockState> SILVER_ORE_CONFIG_DEEPSLATE  = List.of(OreConfiguration.target(DEEPSLATE_REPLACE_RULE, ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get("silver").defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_ORE_DEEPSLATE_KEY = registerKey("silver_ore_deepslate_key");

    public static final List<OreConfiguration.TargetBlockState> NICKEL_ORE_CONFIG_DEEPSLATE = List.of(OreConfiguration.target(DEEPSLATE_REPLACE_RULE, ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get("nickel").defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> NICKEL_ORE_DEEPSLATE_KEY = registerKey("nickel_ore_deepslate_key");

    public static final List<OreConfiguration.TargetBlockState> LEAD_ORE_CONFIG_DEEPSLATE  =
            List.of(OreConfiguration.target(DEEPSLATE_REPLACE_RULE, ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get("lead").defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEAD_ORE_DEEPSLATE_KEY = registerKey("lead_ore_deepslate_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MARE_FLOWER_KEY = registerKey("mare_flower_key");
    public static final ConfiguredFeature<?, ?> MARE_FLOWER_CONFIG = new ConfiguredFeature<>(Feature.FLOWER,
            new RandomPatchConfiguration(3, 16, 3,
                    PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                            BlockStateProvider.simple(ModBlocksRegister.MARE_FLOWER.get())))));

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        context.register(MARE_FLOWER_KEY, MARE_FLOWER_CONFIG);

        register(context, ATIUM_GEODE_KEY, Feature.GEODE, ATIUM_GEODE_CONFIG);
        register(context, LERASIUM_GEODE_KEY, Feature.GEODE, LERASIUM_GEODE_CONFIG);
        register(context, ETTMETAL_GEODE_KEY, Feature.GEODE, ETTMETAL_GEODE_CONFIG);

        register(context, TIN_ORE_STONE_KEY, Feature.ORE, new OreConfiguration(TIN_ORE_CONFIG_STONE, 7, 0.5f));
        register(context, ZINC_ORE_STONE_KEY, Feature.ORE, new OreConfiguration(ZINC_ORE_CONFIG_STONE, 9, 0.5f));
        register(context, ALUMINUM_ORE_STONE_KEY, Feature.ORE, new OreConfiguration(ALUMINUM_ORE_CONFIG_STONE, 7, 0.6f));
        register(context, LEAD_ORE_STONE_KEY, Feature.ORE, new OreConfiguration(LEAD_ORE_CONFIG_STONE, 7, 0.4f));

        register(context, CADMIUM_ORE_DEEPSLATE_KEY, Feature.ORE, new OreConfiguration(CADMIUM_ORE_CONFIG_DEEPSLATE, 6, 0.7f));
        register(context, ALUMINUM_ORE_DEEPSLATE_KEY, Feature.ORE, new OreConfiguration(ALUMINUM_ORE_CONFIG_DEEPSLATE, 7, 0.5f));
        register(context, CHROMIUM_ORE_DEEPSLATE_KEY, Feature.ORE, new OreConfiguration(CHROMIUM_ORE_CONFIG_DEEPSLATE, 7, 0.6f));
        register(context, SILVER_ORE_DEEPSLATE_KEY, Feature.ORE, new OreConfiguration(SILVER_ORE_CONFIG_DEEPSLATE, 7, 0.7f));
        register(context, NICKEL_ORE_DEEPSLATE_KEY, Feature.ORE, new OreConfiguration(NICKEL_ORE_CONFIG_DEEPSLATE, 8, 0.6f));
        register(context, LEAD_ORE_DEEPSLATE_KEY, Feature.ORE, new OreConfiguration(LEAD_ORE_CONFIG_DEEPSLATE, 4, 0.5f));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(MetallicsArts.MOD_ID, name));
    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, C configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}

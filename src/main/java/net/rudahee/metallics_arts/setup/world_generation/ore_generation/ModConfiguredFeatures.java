package net.rudahee.metallics_arts.setup.world_generation.ore_generation;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

import java.util.List;

public class ModConfiguredFeatures {

    /*
     * RULE REPLACEMENT
     */
    public static final RuleTest DEEPSLATE_REPLACE_RULE = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    public static final RuleTest STONE_REPLACE_RULE = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);

    /*
     * STONE REPLACEMENTS
     */

    public static final List<OreConfiguration.TargetBlockState> TIN_ORE_CONFIG_STONE =  List.of(OreConfiguration.target(STONE_REPLACE_RULE, ModBlocksRegister.BLOCK_METAL_ORES.get("tin").defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> TIN_ORE_STONE_KEY = registerKey("tin_ore_stone");

    public static final List<OreConfiguration.TargetBlockState> ZINC_ORE_CONFIG_STONE = List.of(OreConfiguration.target(STONE_REPLACE_RULE, ModBlocksRegister.BLOCK_METAL_ORES.get("zinc").defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> ZINC_ORE_STONE_KEY = registerKey("zinc_ore_stone");

    public static final List<OreConfiguration.TargetBlockState> ALUMINUM_ORE_CONFIG_STONE = List.of(OreConfiguration.target(STONE_REPLACE_RULE, ModBlocksRegister.BLOCK_METAL_ORES.get("aluminum").defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALUMINUM_ORE_STONE_KEY = registerKey("aluminum_ore_stone");

    public static final List<OreConfiguration.TargetBlockState> LEAD_ORE_CONFIG_STONE = List.of(OreConfiguration.target(STONE_REPLACE_RULE, ModBlocksRegister.BLOCK_METAL_ORES.get("lead").defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEAD_ORE_STONE_KEY = registerKey("lead_ore_stone");


    /*
     * DEEPSLATE REPLACEMENTS
     */

    public static final List<OreConfiguration.TargetBlockState> CADMIUM_ORE_CONFIG_DEEPSLATE = List.of(OreConfiguration.target(DEEPSLATE_REPLACE_RULE, ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get("cadmium").defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> CADMIUM_ORE_DEEPSLATE_KEY = registerKey("cadmium_ore_deepslate");

    public static final List<OreConfiguration.TargetBlockState> ALUMINUM_ORE_CONFIG_DEEPSLATE  = List.of(OreConfiguration.target(DEEPSLATE_REPLACE_RULE, ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get("aluminum").defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALUMINUM_ORE_DEEPSLATE_KEY = registerKey("aluminum_ore_deepslate");

    public static final List<OreConfiguration.TargetBlockState> CHROMIUM_ORE_CONFIG_DEEPSLATE  = List.of(OreConfiguration.target(DEEPSLATE_REPLACE_RULE, ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get("chromium").defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHROMIUM_ORE_DEEPSLATE_KEY = registerKey("chromium_ore_deepslate");

    public static final List<OreConfiguration.TargetBlockState> SILVER_ORE_CONFIG_DEEPSLATE  = List.of(OreConfiguration.target(DEEPSLATE_REPLACE_RULE, ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get("silver").defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_ORE_DEEPSLATE_KEY = registerKey("silver_ore_deepslate");

    public static final List<OreConfiguration.TargetBlockState> NICKEL_ORE_CONFIG_DEEPSLATE = List.of(OreConfiguration.target(DEEPSLATE_REPLACE_RULE, ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get("nickel").defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> NICKEL_ORE_DEEPSLATE_KEY = registerKey("nickel_ore_deepslate");

    public static final List<OreConfiguration.TargetBlockState> LEAD_ORE_CONFIG_DEEPSLATE  =
            List.of(OreConfiguration.target(DEEPSLATE_REPLACE_RULE, ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get("lead").defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEAD_ORE_DEEPSLATE_KEY = registerKey("lead_ore_deepslate");




    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {


        register(context, TIN_ORE_STONE_KEY, Feature.ORE, new OreConfiguration(TIN_ORE_CONFIG_STONE, 6, 0.7f));
        register(context, ZINC_ORE_STONE_KEY, Feature.ORE, new OreConfiguration(ZINC_ORE_CONFIG_STONE, 8, 0.8f));
        register(context, ALUMINUM_ORE_STONE_KEY, Feature.ORE, new OreConfiguration(ALUMINUM_ORE_CONFIG_STONE, 6, 0.9f));
        register(context, LEAD_ORE_STONE_KEY, Feature.ORE, new OreConfiguration(LEAD_ORE_CONFIG_STONE, 6, 0.8f));

        register(context, CADMIUM_ORE_DEEPSLATE_KEY, Feature.ORE, new OreConfiguration(CADMIUM_ORE_CONFIG_DEEPSLATE, 6, 0.9f));
        register(context, ALUMINUM_ORE_DEEPSLATE_KEY, Feature.ORE, new OreConfiguration(ALUMINUM_ORE_CONFIG_DEEPSLATE, 5, 0.8f));
        register(context, CHROMIUM_ORE_DEEPSLATE_KEY, Feature.ORE, new OreConfiguration(CHROMIUM_ORE_CONFIG_DEEPSLATE, 4, 0.7f));
        register(context, SILVER_ORE_DEEPSLATE_KEY, Feature.ORE, new OreConfiguration(SILVER_ORE_CONFIG_DEEPSLATE, 5, 0.8f));
        register(context, NICKEL_ORE_DEEPSLATE_KEY, Feature.ORE, new OreConfiguration(NICKEL_ORE_CONFIG_DEEPSLATE, 8, 0.9f));
        register(context, LEAD_ORE_DEEPSLATE_KEY, Feature.ORE, new OreConfiguration(LEAD_ORE_CONFIG_DEEPSLATE, 4, 0.4f));
    }




    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(MetallicsArts.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}

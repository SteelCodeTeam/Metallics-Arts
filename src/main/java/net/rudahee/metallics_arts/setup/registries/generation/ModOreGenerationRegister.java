package net.rudahee.metallics_arts.setup.registries.generation;

import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

import java.util.List;
import java.util.function.Supplier;

public class ModOreGenerationRegister {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registries.CONFIGURED_FEATURE, MetallicsArts.MOD_ID);


    /** STONE **/
    static RuleTest stoneOres = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES) ;

    public static final Supplier<List<OreConfiguration.TargetBlockState>> ZINC_REPLACEMENT_STONE = Suppliers.memoize(() -> List.of(OreConfiguration.target(stoneOres, ModBlocksRegister.BLOCK_METAL_ORES.get("zinc").defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ZINC_ORE_GENERATION_STONE =
            CONFIGURED_FEATURES.register("zinc_ore_generation_stone",
                    () -> new ConfiguredFeature<>(Feature.ORE,
                            new OreConfiguration(ZINC_REPLACEMENT_STONE.get(), 6, 0.7f)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> TIN_REPLACEMENT_STONE = Suppliers.memoize(() -> List.of(OreConfiguration.target(stoneOres, ModBlocksRegister.BLOCK_METAL_ORES.get("tin").defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> TIN_ORE_GENERATION_STONE = CONFIGURED_FEATURES.register("tin_ore_generation_stone", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(TIN_REPLACEMENT_STONE.get(), 8, 0.8f)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> ALUMINUM_REPLACEMENT_STONE = Suppliers.memoize(() -> List.of(OreConfiguration.target(stoneOres, ModBlocksRegister.BLOCK_METAL_ORES.get("aluminum").defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ALUMINUM_ORE_GENERATION_STONE = CONFIGURED_FEATURES.register("aluminum_ore_generation_stone", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ALUMINUM_REPLACEMENT_STONE.get(), 6, 0.9f)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> LEAD_REPLACEMENT_STONE = Suppliers.memoize(() -> List.of(OreConfiguration.target(stoneOres, ModBlocksRegister.BLOCK_METAL_ORES.get("lead").defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> LEAD_ORE_GENERATION_STONE = CONFIGURED_FEATURES.register("lead_ore_generation_stone", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(LEAD_REPLACEMENT_STONE.get(), 6, 0.8f)));


    /** DEEPSLATE **/
    static RuleTest deepSlateOres = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    public static final Supplier<List<OreConfiguration.TargetBlockState>> CADMIUM_REPLACEMENT_DEEPSLATE = Suppliers.memoize(() -> List.of(OreConfiguration.target(deepSlateOres, ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get("cadmium").defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CADMIUM_ORE_GENERATION_DEEPSLATE = CONFIGURED_FEATURES.register("cadmium_ore_generation_deepslate", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(CADMIUM_REPLACEMENT_DEEPSLATE.get(), 5, 0.9f)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> ALUMINUM_REPLACEMENT_DEEPSLATE  =
            Suppliers.memoize(
                    () -> List.of(OreConfiguration.target(deepSlateOres,
                            ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get("aluminum").defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ALUMINUM_ORE_GENERATION_DEEPSLATE = CONFIGURED_FEATURES.register("aluminum_ore_generation_deepslate", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ALUMINUM_REPLACEMENT_DEEPSLATE.get(), 5, 0.8f)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> CHROMIUM_REPLACEMENT_DEEPSLATE  = Suppliers.memoize(() -> List.of(OreConfiguration.target(deepSlateOres, ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get("chromium").defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CHROMIUM_ORE_GENERATION_DEEPSLATE = CONFIGURED_FEATURES.register("chromium_ore_generation_deepslate", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(CHROMIUM_REPLACEMENT_DEEPSLATE.get(), 5, 0.9f)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> SILVER_REPLACEMENT_DEEPSLATE  = Suppliers.memoize(() -> List.of(OreConfiguration.target(deepSlateOres, ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get("silver").defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> SILVER_ORE_GENERATION_DEEPSLATE = CONFIGURED_FEATURES.register("silver_ore_generation_deepslate", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(SILVER_REPLACEMENT_DEEPSLATE.get(), 5, 0.8f)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> NICKEL_REPLACEMENT_DEEPSLATE = Suppliers.memoize(() -> List.of(OreConfiguration.target(deepSlateOres, ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get("nickel").defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> NICKEL_ORE_GENERATION_DEEPSLATE = CONFIGURED_FEATURES.register("nickel_ore_generation_deepslate", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NICKEL_REPLACEMENT_DEEPSLATE.get(), 8, 0.9f)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> LEAD_REPLACEMENT_DEEPSLATE  =
            Suppliers.memoize(() -> List.of(OreConfiguration.target(deepSlateOres, ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get("lead").defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> LEAD_ORE_GENERATION_DEEPSLATE =
            CONFIGURED_FEATURES.register("lead_ore_generation_deepslate",
                    () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(LEAD_REPLACEMENT_DEEPSLATE.get(), 6, 0.4f)));

    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }
}

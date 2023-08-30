package net.rudahee.metallics_arts.setup.registries.generation;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.configs.CustomGeodeConfig;

import java.util.List;

public class ModStructureRegister {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registries.PLACED_FEATURE, MetallicsArts.MOD_ID);

    public static final RegistryObject<PlacedFeature> ZINC_ORE_PLACED_STONE = PLACED_FEATURES.register("zinc_ore_placed_stone",
            () -> new PlacedFeature(ModOreGenerationRegister.ZINC_ORE_GENERATION_STONE.getHolder().get(),
                    commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(120)))));

    public static final RegistryObject<PlacedFeature> TIN_ORE_PLACED_STONE = PLACED_FEATURES.register("tin_ore_placed_stone",
            () -> new PlacedFeature(ModOreGenerationRegister.TIN_ORE_GENERATION_STONE.getHolder().get(),
                    commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(120)))));

    public static final RegistryObject<PlacedFeature> ALUMINUM_ORE_PLACED_STONE = PLACED_FEATURES.register("aluminum_ore_placed_stone",
            () -> new PlacedFeature(ModOreGenerationRegister.ALUMINUM_ORE_GENERATION_STONE.getHolder().get(),
                    commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(120)))));

    public static final RegistryObject<PlacedFeature> LEAD_ORE_PLACED_STONE = PLACED_FEATURES.register("lead_ore_placed_stone",
            () -> new PlacedFeature(ModOreGenerationRegister.LEAD_ORE_GENERATION_STONE.getHolder().get(),
                    commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(120)))));


    public static final RegistryObject<PlacedFeature> CADMIUM_ORE_PLACED_DEEPSLATE = PLACED_FEATURES.register("cadmium_ore_placed_deepslate",
            () -> new PlacedFeature(ModOreGenerationRegister.CADMIUM_ORE_GENERATION_DEEPSLATE.getHolder().get(),
                    commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(120)))));


    public static final RegistryObject<PlacedFeature> ALUMINUM_ORE_PLACED_DEEPSLATE = PLACED_FEATURES.register("aluminum_ore_placed_deepslate",
            () -> new PlacedFeature(ModOreGenerationRegister.ALUMINUM_ORE_GENERATION_DEEPSLATE.getHolder().get(),
                    commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(120)))));

    public static final RegistryObject<PlacedFeature> CHROMIUM_ORE_PLACED_DEEPSLATE = PLACED_FEATURES.register("chromium_ore_placed_deepslate",
            () -> new PlacedFeature(ModOreGenerationRegister.CHROMIUM_ORE_GENERATION_DEEPSLATE.getHolder().get(),
                    commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(120)))));

    public static final RegistryObject<PlacedFeature> SILVER_ORE_PLACED_DEEPSLATE = PLACED_FEATURES.register("silver_ore_placed_deepslate",
            () -> new PlacedFeature(ModOreGenerationRegister.SILVER_ORE_GENERATION_DEEPSLATE.getHolder().get(),
                    commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(120)))));

    public static final RegistryObject<PlacedFeature> NICKEL_ORE_PLACED_DEEPSLATE = PLACED_FEATURES.register("nickel_ore_placed_deepslate",
            () -> new PlacedFeature(ModOreGenerationRegister.NICKEL_ORE_GENERATION_DEEPSLATE.getHolder().get(),
                    commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(120)))));

    public static final RegistryObject<PlacedFeature> LEAD_ORE_PLACED_DEEPSLATE = PLACED_FEATURES.register("lead_ore_placed_deepslate",
            () -> new PlacedFeature(ModOreGenerationRegister.LEAD_ORE_GENERATION_DEEPSLATE.getHolder().get(),
                    commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(120)))));



    /*public static final RegistryObject<PlacedFeature> ATIUM_GEODE =
            PLACED_FEATURES.register("atium_geode", () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>) (Holder<? extends ConfiguredFeature<?,?>>) CustomGeodeConfig.ATIUM_GEODE, placementModifier()));

    public static final RegistryObject<PlacedFeature> LERASIUM_GEODE =
            PLACED_FEATURES.register("lerasium_geode",
                    () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>) (Holder<? extends ConfiguredFeature<?,?>>) CustomGeodeConfig.LERASIUM_GEODE,
                            placementModifier()));

    public static final RegistryObject<PlacedFeature> ETTMETAL_GEODE =
            PLACED_FEATURES.register("ettmetal_geode",
                    () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>) (Holder<? extends ConfiguredFeature<?,?>>) CustomGeodeConfig.ETTMETAL_GEODE, placementModifier()));*/

    public static List<PlacementModifier> placementModifier() {
        return List.of(
                RarityFilter.onAverageOnceEvery(12),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(30)),
                BiomeFilter.biome()
        );

    }



    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }


    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}

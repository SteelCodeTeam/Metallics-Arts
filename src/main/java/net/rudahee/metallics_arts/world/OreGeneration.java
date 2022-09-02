package net.rudahee.metallics_arts.world;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.world.feature.ModConfiguredFeatures;

import java.util.List;

public class OreGeneration {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, MetallicsArts.MOD_ID);

    public static final RegistryObject<PlacedFeature> ZINC_ORE_PLACED_STONE = PLACED_FEATURES.register("zinc_ore_placed_stone",
            () -> new PlacedFeature(ModConfiguredFeatures.ZINC_ORE_GENERATION_STONE.getHolder().get(),
                    commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(120)))));

    public static final RegistryObject<PlacedFeature> TIN_ORE_PLACED_STONE = PLACED_FEATURES.register("tin_ore_placed_stone",
            () -> new PlacedFeature(ModConfiguredFeatures.TIN_ORE_GENERATION_STONE.getHolder().get(),
                    commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(120)))));

    public static final RegistryObject<PlacedFeature> ALUMINUM_ORE_PLACED_STONE = PLACED_FEATURES.register("aluminum_ore_placed_stone",
            () -> new PlacedFeature(ModConfiguredFeatures.ALUMINUM_ORE_GENERATION_STONE.getHolder().get(),
                    commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(120)))));

    public static final RegistryObject<PlacedFeature> LEAD_ORE_PLACED_STONE = PLACED_FEATURES.register("lead_ore_placed_stone",
            () -> new PlacedFeature(ModConfiguredFeatures.LEAD_ORE_GENERATION_STONE.getHolder().get(),
                    commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(120)))));


    public static final RegistryObject<PlacedFeature> CADMIUM_ORE_PLACED_DEEPSLATE = PLACED_FEATURES.register("cadmium_ore_placed_deepslate",
            () -> new PlacedFeature(ModConfiguredFeatures.CADMIUM_ORE_GENERATION_DEEPSLATE.getHolder().get(),
                    commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(120)))));


    public static final RegistryObject<PlacedFeature> ALUMINUM_ORE_PLACED_DEEPSLATE = PLACED_FEATURES.register("aluminum_ore_placed_deepslate",
            () -> new PlacedFeature(ModConfiguredFeatures.ALUMINUM_ORE_GENERATION_DEEPSLATE.getHolder().get(),
                    commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(120)))));

    public static final RegistryObject<PlacedFeature> CHROMIUM_ORE_PLACED_DEEPSLATE = PLACED_FEATURES.register("chromium_ore_placed_deepslate",
            () -> new PlacedFeature(ModConfiguredFeatures.CHROMIUM_ORE_GENERATION_DEEPSLATE.getHolder().get(),
                    commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(120)))));

    public static final RegistryObject<PlacedFeature> SILVER_ORE_PLACED_DEEPSLATE = PLACED_FEATURES.register("silver_ore_placed_deepslate",
            () -> new PlacedFeature(ModConfiguredFeatures.SILVER_ORE_GENERATION_DEEPSLATE.getHolder().get(),
                    commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(120)))));

    public static final RegistryObject<PlacedFeature> NICKEL_ORE_PLACED_DEEPSLATE = PLACED_FEATURES.register("nickel_ore_placed_deepslate",
            () -> new PlacedFeature(ModConfiguredFeatures.NICKEL_ORE_GENERATION_DEEPSLATE.getHolder().get(),
                    commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(120)))));

    public static final RegistryObject<PlacedFeature> LEAD_ORE_PLACED_DEEPSLATE = PLACED_FEATURES.register("lead_ore_placed_deepslate",
            () -> new PlacedFeature(ModConfiguredFeatures.LEAD_ORE_GENERATION_DEEPSLATE.getHolder().get(),
                    commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(120)))));


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

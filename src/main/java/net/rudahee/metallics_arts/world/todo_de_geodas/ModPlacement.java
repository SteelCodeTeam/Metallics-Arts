package net.rudahee.metallics_arts.world.todo_de_geodas;

import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.world.feature.ModConfiguredFeatures;


import java.util.List;

public class ModPlacement {

    public static final Holder<PlacedFeature> ATIUM_GEODE_PLACED = PlacementUtils.register("atium_geode", ModConfiguredFeatures.ATIUM_GEODE, algo());

    public static List<PlacementModifier> algo(){
        return List.of(
                RarityFilter.onAverageOnceEvery(12),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(30)),
                BiomeFilter.biome()
        );
    }




}

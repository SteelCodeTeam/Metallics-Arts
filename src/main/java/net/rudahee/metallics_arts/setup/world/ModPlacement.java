package net.rudahee.metallics_arts.setup.world;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacement {

    private ModPlacement() {
        throw new IllegalStateException("Class can't be instantiated");
    }

    public static List<PlacementModifier> orePlacement(PlacementModifier modifier, PlacementModifier modifier2) {
        return List.of(modifier, InSquarePlacement.spread(), modifier2, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier modifier) {
        return orePlacement(CountPlacement.of(count), modifier);
    }
}

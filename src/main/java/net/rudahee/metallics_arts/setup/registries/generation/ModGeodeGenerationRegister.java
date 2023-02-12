package net.rudahee.metallics_arts.setup.registries.generation;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.configs.GeodeGenerationConfig;

public class ModGeodeGenerationRegister {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MetallicsArts.MOD_ID);

    public static RegistryObject<Codec<GeodeGenerationConfig>> GEODE_CODEC = BIOME_MODIFIER.register("geode_aa", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    // declare fields
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(GeodeGenerationConfig::biomes),
                    PlacedFeature.CODEC.fieldOf("feature").forGetter(GeodeGenerationConfig::feature)
                    // declare constructor
            ).apply(builder, GeodeGenerationConfig::new)));


    public static void register(IEventBus eventBus) {
        BIOME_MODIFIER.register(eventBus);
    }
}
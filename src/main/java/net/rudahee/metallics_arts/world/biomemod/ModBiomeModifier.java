package net.rudahee.metallics_arts.world.biomemod;

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

public class ModBiomeModifier {

    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIERS =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MetallicsArts.MOD_ID);

    public static RegistryObject<Codec<ModGeodeBiomeModifier>> GEODE_MODIFIER = BIOME_MODIFIERS.register("geode", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(ModGeodeBiomeModifier::biomes),
                    PlacedFeature.CODEC.fieldOf("feature").forGetter(ModGeodeBiomeModifier::feature)
            ).apply(builder, ModGeodeBiomeModifier::new)));


    public static void register(IEventBus eventBus) {
        BIOME_MODIFIERS.register(eventBus);
    }
}

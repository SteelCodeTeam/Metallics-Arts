package net.rudahee.metallics_arts.world.feature;

import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.metals.Metal;
import net.rudahee.metallics_arts.setup.registries.ModBlock;
import org.checkerframework.checker.units.qual.C;

import javax.print.DocFlavor;
import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES
            = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, MetallicsArts.MOD_ID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> DEEPSLATE_ORES = Suppliers.memoize(() -> generateBlockDeepOreList());

    public static final Supplier<List<OreConfiguration.TargetBlockState>> ORES = Suppliers.memoize(() -> generateBlockOreList());

    public static final RegistryObject<ConfiguredFeature<?,?>> ORE = CONFIGURED_FEATURES.register("zinc",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(DEEPSLATE_ORES.get(),4)));


    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }



    /*
    List<Metal> metalList = Arrays.asList(Metal.values());

        metalList.forEach(metal -> {
            // If not alloy, create ore.
            if (!metal.isAlloy()) {
                if (metal.isStone()) {
                    registerBlock(metal.getMetalNameLower() + "_ore", () -> {
                        Block block = new Block(Block.Properties.of(Material.STONE)
                                .strength(3, 10)
                                .sound(SoundType.STONE)
                                .requiresCorrectToolForDrops());
                        MetalGenerationData.valueOf(metal.getMetalNameUpper()).setBlock(block);
                        BLOCK_METAL_ORES.put(metal.getMetalNameLower(), block);
                        return block;
                    });
                }
                if (metal.isDeepslate()) {
                    registerBlock("deepslate_" + metal.getMetalNameLower() + "_ore", () -> {
                        Block block = new Block(Block.Properties.of(Material.STONE)
                                .strength(4, 10)
                                .sound(SoundType.STONE)
                                .requiresCorrectToolForDrops());
                        MetalGenerationData.valueOf(metal.getMetalNameUpper()).setBlock(block);
                        BLOCK_METAL_DEEPSLATE_ORES.put(metal.getMetalNameLower(), block);
                        return block;
                    });
                }

            }
        });
     */



    public static List<OreConfiguration.TargetBlockState> generateBlockOreList () {
        List<OreConfiguration.TargetBlockState> list = new ArrayList<>();
        for (Block ore:ModBlock.BLOCK_METAL_ORES.values()) {
            list.add(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ore.defaultBlockState()));
        }
        return list;
    }
    public static List<OreConfiguration.TargetBlockState> generateBlockDeepOreList () {
        List<OreConfiguration.TargetBlockState> list = new ArrayList<>();
        for (Block ore:ModBlock.BLOCK_METAL_DEEPSLATE_ORES.values()) {
            list.add(OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ore.defaultBlockState()));
        }
        return list;
    }



}

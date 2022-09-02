package net.rudahee.metallics_arts.setup.registries;


import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.blocks.EttmetalBlock;
import net.rudahee.metallics_arts.modules.blocks.alloy_furnace.AlloyFurnaceBlock;
import net.rudahee.metallics_arts.setup.enums.gems.Gems;
import net.rudahee.metallics_arts.setup.enums.metals.Metal;
import net.rudahee.metallics_arts.setup.enums.metals.MetalGenerationData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.ToIntFunction;

import static net.rudahee.metallics_arts.MetallicsArts.registerBlock;


public class ModBlock {

    public static final HashMap<String, Block> BLOCK_METAL_ORES = new HashMap<String, Block>();

    public static final HashMap<String, Block> BLOCK_METAL_DEEPSLATE_ORES = new HashMap<String, Block>();
    public static final HashMap<String, Block> BLOCK_METAL_BLOCKS = new HashMap<String, Block>();

    public static final HashMap<String, Block> RAW_METAL_BLOCKS = new HashMap<String, Block>();
    public static final HashMap<String, Block> BLOCK_GEMS_BLOCKS = new HashMap<String, Block>();

    public static RegistryObject<Block> ALLOY_FURNACE_BLOCK = null;

    public static RegistryObject<Block> ETTMETAL_BLOCK = null;

    public static void register() {
        /*
            INITIALIZING METALS
         */
        List<Metal> metalList = Arrays.asList(Metal.values());
        metalList.forEach(metal -> {
            // If not alloy, create ore.
            if (!metal.isAlloy()) {
                if (metal.isStone()) {
                    registerBlock(metal.getMetalNameLower() + "_ore", () -> {
                        Block block = new Block(Block.Properties.of(Material.STONE)
                                .strength(3.0F, 3.0F)
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
                                .strength(4.5F, 3.0F)
                                .sound(SoundType.DEEPSLATE)
                                .requiresCorrectToolForDrops());
                        MetalGenerationData.valueOf(metal.getMetalNameUpper()).setBlock(block);
                        BLOCK_METAL_DEEPSLATE_ORES.put(metal.getMetalNameLower(), block);
                        return block;
                    });
                }

            }
        });
        metalList.forEach(metal -> {
            // Always create block.
            registerBlock(metal.getMetalNameLower() + "_block", () -> {
                Block block = new Block(Block.Properties.of(Material.METAL)
                        .strength(5, 15)
                        .sound(SoundType.METAL)
                        .requiresCorrectToolForDrops());

                BLOCK_METAL_BLOCKS.put(metal.getMetalNameLower(), block);
                return block;
            });
        });
        metalList.forEach(metal -> {
            // Always create block.
            if (!metal.isAlloy()) {
                registerBlock("raw_" + metal.getMetalNameLower() + "_block", () -> {
                    Block block = new Block(Block.Properties.of(Material.METAL)
                            .strength(5, 15)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

                    RAW_METAL_BLOCKS.put(metal.getMetalNameLower(), block);
                    return block;
                });
            }
        });


        List<Gems> gemsList = Arrays.asList(Gems.values());

        gemsList.forEach(gem -> {
            if (!gem.getGemNameLower().equals(Gems.ETTMETAL.getGemNameLower())) {
                MetallicsArts.registerBlock(gem.getGemNameLower() + "_block", () -> {
                    Block block = new Block(Block.Properties.of(Material.METAL)
                            .strength(10, 25)
                            .sound(SoundType.METAL)
                            .randomTicks()
                            .requiresCorrectToolForDrops());

                    BLOCK_GEMS_BLOCKS.put(gem.getGemNameLower(), block);
                    return block;
                });
            }
        });

        ModBlock.ETTMETAL_BLOCK = MetallicsArts.registerBlock(Gems.ETTMETAL.getGemNameLower() + "_block",
                () -> {
                    Block block = new EttmetalBlock(Block.Properties.of(Material.HEAVY_METAL)
                            .strength(10f, 10f)
                            .lightLevel(litBlockEmission(13))
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

                    BLOCK_GEMS_BLOCKS.put(Gems.ETTMETAL.getGemNameLower(), block);
                    return block;
                });


        ModBlock.ALLOY_FURNACE_BLOCK = MetallicsArts.registerBlock("alloy_furnace",
                () -> new AlloyFurnaceBlock(Block.Properties.of(Material.STONE)
                        .strength(3.5F)
                        .requiresCorrectToolForDrops()));


    }



    private static ToIntFunction<BlockState> litBlockEmission(int value) {
        return (funcValue) -> {
            return 12;
            //return funcValue.getValue(BlockStateProperties.LIT) ? value : 0;
        };
    }
}

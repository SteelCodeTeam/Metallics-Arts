package net.rudahee.metallics_arts.setup.registries.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.data.enums.implementations.OreGenerationEnum;
import net.rudahee.metallics_arts.modules.custom_blocks.ettmetal_block.EttmetalBlock;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

import java.util.Arrays;
import java.util.List;
import java.util.function.ToIntFunction;

import static net.rudahee.metallics_arts.MetallicsArts.registerBlock;

public class BasicBlocksRegister {

    public static void register() {


        /**
            INITIALIZING METALS
         */
        List<MetalEnum> metalEnumList = Arrays.asList(MetalEnum.values());

        metalEnumList.forEach(metal -> {
            // If not alloy, create ore.
            if (!metal.isAlloy()) {
                //ORES BLOCKS
                if (metal.isStone()) {
                    registerBlock(metal.getMetalNameLower() + "_ore", () -> {
                        Block block = new Block(Block.Properties.of(Material.STONE)
                                .strength(3.0F, 3.0F)
                                .sound(SoundType.STONE)
                                .requiresCorrectToolForDrops());
                        OreGenerationEnum.valueOf(metal.getMetalNameUpper()).setBlock(block);
                        ModBlocksRegister.BLOCK_METAL_ORES.put(metal.getMetalNameLower(), block);
                        return block;
                    });
                }
                //DEEPSLATE BLOCKS
                if (metal.isDeepslate()) {
                    registerBlock("deepslate_" + metal.getMetalNameLower() + "_ore", () -> {
                        Block block = new Block(Block.Properties.of(Material.STONE)
                                .strength(4.5F, 3.0F)
                                .sound(SoundType.DEEPSLATE)
                                .requiresCorrectToolForDrops());
                        OreGenerationEnum.valueOf(metal.getMetalNameUpper()).setBlock(block);
                        ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.put(metal.getMetalNameLower(), block);
                        return block;
                    });
                }
                //RAWS BLOCKS
                MetallicsArts.registerBlockDecoration("raw_" + metal.getMetalNameLower() + "_block", () -> {
                    Block block = new Block(Block.Properties.of(Material.METAL)
                            .strength(4, 15)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());
                    ModBlocksRegister.RAW_METAL_BLOCKS.put(metal.getMetalNameLower(), block);
                    return block;
                });
            }

            MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_block", () -> {
                Block block = new Block(Block.Properties.of(Material.METAL)
                        .strength(5, 15)
                        .sound(SoundType.METAL)
                        .requiresCorrectToolForDrops());
                ModBlocksRegister.BLOCK_METAL_BLOCKS.put(metal.getMetalNameLower(), block);
                return block;
            });
        });


        /**
            INITIALIZING DIVINE METALS
         */
        List<GemsEnum> gemsEnumList = Arrays.asList(GemsEnum.values());

        gemsEnumList.forEach(gem -> {
            if (!gem.getGemNameLower().equals(GemsEnum.ETTMETAL.getGemNameLower())) {
                MetallicsArts.registerBlockDecoration(gem.getGemNameLower() + "_block", () -> {
                    Block block = new Block(Block.Properties.of(Material.METAL)
                            .strength(10, 25)
                            .sound(SoundType.METAL)
                            .randomTicks()
                            .requiresCorrectToolForDrops());

                    ModBlocksRegister.BLOCK_GEMS_BLOCKS.put(gem.getGemNameLower(), block);
                    return block;
                });
            }
        });

        /**
         INITIALIZING SPECIAL ETTMETAL BLOCK
         */
        ModBlocksRegister.ETTMETAL_BLOCK = MetallicsArts.registerBlockDecoration(GemsEnum.ETTMETAL.getGemNameLower() + "_block",
                () -> {
                    Block block = new EttmetalBlock(Block.Properties.of(Material.HEAVY_METAL)
                            .strength(10f, 10f)
                            .lightLevel(litBlockEmission(13))
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

                    ModBlocksRegister.BLOCK_GEMS_BLOCKS.put(GemsEnum.ETTMETAL.getGemNameLower(), block);
                    return block;
                });


    }
    private static ToIntFunction<BlockState> litBlockEmission(int value) {
        return (funcValue) -> {
            return 12;
            //return funcValue.getValue(BlockStateProperties.LIT) ? value : 0;
        };
    }
}

package net.rudahee.metallics_arts.setup.registries.blocks.other_blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.OreGenerationEnum;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

public class Decoration {

    public static void register() {
        for (MetalEnum metal: MetalEnum.values()) {
            MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_stairs", ()-> {

                StairBlock block = new StairBlock(
                        ()-> ModBlocksRegister.BLOCK_METAL_BLOCKS.get(metal.getMetalNameLower()).defaultBlockState(),
                        Block.Properties.of(Material.METAL)
                                .strength(5, 15)
                                .sound(SoundType.METAL)
                                .requiresCorrectToolForDrops());

                ModBlocksRegister.BLOCK_METAL_STAIRS.put(metal.getMetalNameLower(), block);

                return block;
            });
            if (!metal.isAlloy()) {
                MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_raw_stairs", ()-> {
                    StairBlock block = new StairBlock(
                            ()-> ModBlocksRegister.RAW_METAL_BLOCKS.get(metal.getMetalNameLower()).defaultBlockState(),
                            Block.Properties.of(Material.METAL)
                                    .strength(5, 15)
                                    .sound(SoundType.METAL)
                                    .requiresCorrectToolForDrops());

                    ModBlocksRegister.BLOCK_METAL_STAIRS.put(metal.getMetalNameLower() + "_raw", block);
                    return block;
                });
            }
        }
        for (GemsEnum metal: GemsEnum.values()) {
            MetallicsArts.registerBlockDecoration(metal.getGemNameLower() + "_stairs", ()-> {
                StairBlock block = new StairBlock(
                        () -> ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(metal.getGemNameLower()).defaultBlockState(),
                        Block.Properties.of(Material.METAL)
                                .strength(5, 15)
                                .sound(SoundType.METAL)
                                .requiresCorrectToolForDrops());

                ModBlocksRegister.BLOCK_METAL_STAIRS.put(metal.getGemNameLower(), block);
                return block;
            });
        }

        MetallicsArts.registerBlockDecoration("iron_stairs", ()-> {
            StairBlock block = new StairBlock(
                    Blocks.IRON_BLOCK::defaultBlockState,
                    Block.Properties.of(Material.METAL)
                            .strength(5, 15)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_STAIRS.put("iron", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("gold_stairs", ()-> {
            StairBlock block = new StairBlock(
                    Blocks.GOLD_BLOCK::defaultBlockState,
                    Block.Properties.of(Material.METAL)
                            .strength(5, 15)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_STAIRS.put("gold", block);
            return block;
        });

    }
}

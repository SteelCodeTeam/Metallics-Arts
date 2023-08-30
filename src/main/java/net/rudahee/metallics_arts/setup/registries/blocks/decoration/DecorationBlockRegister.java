package net.rudahee.metallics_arts.setup.registries.blocks.decoration;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

public class DecorationBlockRegister {

    public static void register() {
        for (MetalEnum metal: MetalEnum.values()) {
            MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_stairs", ()-> {

                StairBlock block = new StairBlock(
                        ()-> ModBlocksRegister.BLOCK_METAL_BLOCKS.get(metal.getMetalNameLower()).defaultBlockState(),
                        BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL));

                ModBlocksRegister.BLOCK_METAL_STAIRS.put(metal.getMetalNameLower(), block);

                return block;
            });
            if (!metal.isAlloy()) {
                MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_raw_stairs", ()-> {
                    StairBlock block = new StairBlock(
                            ()-> ModBlocksRegister.RAW_METAL_BLOCKS.get(metal.getMetalNameLower()).defaultBlockState(),
                            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL));

                    ModBlocksRegister.BLOCK_METAL_STAIRS.put(metal.getMetalNameLower() + "_raw", block);
                    return block;
                });
            }
        }
        for (GemsEnum metal: GemsEnum.values()) {
            MetallicsArts.registerBlockDecoration(metal.getGemNameLower() + "_stairs", ()-> {
                StairBlock block = new StairBlock(
                        () -> ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(metal.getGemNameLower()).defaultBlockState(),
                        BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL));;

                ModBlocksRegister.BLOCK_METAL_STAIRS.put(metal.getGemNameLower(), block);
                return block;
            });
        }

        MetallicsArts.registerBlockDecoration("iron_stairs", ()-> {
            StairBlock block = new StairBlock(
                    Blocks.IRON_BLOCK::defaultBlockState,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_STAIRS.put("iron", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("gold_stairs", ()-> {
            StairBlock block = new StairBlock(
                    Blocks.RAW_GOLD_BLOCK::defaultBlockState,
                    BlockBehaviour.Properties.copy(Blocks.RAW_GOLD_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_STAIRS.put("gold", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("iron_raw_stairs", ()-> {
            StairBlock block = new StairBlock(
                    Blocks.RAW_IRON_BLOCK::defaultBlockState,
                    BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_STAIRS.put("iron_raw", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("gold_raw_stairs", ()-> {
            StairBlock block = new StairBlock(
                    Blocks.RAW_GOLD_BLOCK::defaultBlockState,
                    BlockBehaviour.Properties.copy(Blocks.RAW_GOLD_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_STAIRS.put("gold_raw", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("copper_raw_stairs", ()-> {
            StairBlock block = new StairBlock(
                    Blocks.RAW_COPPER_BLOCK::defaultBlockState,
                    BlockBehaviour.Properties.copy(Blocks.RAW_COPPER_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_STAIRS.put("copper_raw", block);
            return block;
        });


        for (MetalEnum metal: MetalEnum.values()) {
            MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_slabs", ()-> {

                SlabBlock block = new SlabBlock(
                        BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL));

                ModBlocksRegister.BLOCK_METAL_SLABS.put(metal.getMetalNameLower(), block);

                return block;
            });
            if (!metal.isAlloy()) {
                MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_raw_slabs", ()-> {
                    SlabBlock block = new SlabBlock(
                            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL));

                    ModBlocksRegister.BLOCK_METAL_SLABS.put(metal.getMetalNameLower() + "_raw", block);
                    return block;
                });
            }
        }
        for (GemsEnum metal: GemsEnum.values()) {
            MetallicsArts.registerBlockDecoration(metal.getGemNameLower() + "_slabs", ()-> {
                SlabBlock block = new SlabBlock(
                        BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL));;

                ModBlocksRegister.BLOCK_METAL_SLABS.put(metal.getGemNameLower(), block);
                return block;
            });
        }

        MetallicsArts.registerBlockDecoration("iron_slabs", ()-> {
            SlabBlock block = new SlabBlock(
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_SLABS.put("iron", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("gold_slabs", ()-> {
            SlabBlock block = new SlabBlock(
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_SLABS.put("gold", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("iron_raw_slabs", ()-> {
            SlabBlock block = new SlabBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_SLABS.put("iron_raw", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("gold_raw_slabs", ()-> {
            SlabBlock block = new SlabBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_GOLD_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_SLABS.put("gold_raw", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("copper_raw_slabs", ()-> {
            SlabBlock block = new SlabBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_COPPER_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_SLABS.put("copper_raw", block);
            return block;
        });


        for (MetalEnum metal: MetalEnum.values()) {
            MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_walls", ()-> {

                WallBlock block = new WallBlock(
                        BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL));

                ModBlocksRegister.BLOCK_METAL_WALLS.put(metal.getMetalNameLower(), block);

                return block;
            });
            if (!metal.isAlloy()) {
                MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_raw_walls", ()-> {
                    WallBlock block = new WallBlock(
                            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL));

                    ModBlocksRegister.BLOCK_METAL_WALLS.put(metal.getMetalNameLower() + "_raw", block);
                    return block;
                });
            }
        }
        for (GemsEnum metal: GemsEnum.values()) {
            MetallicsArts.registerBlockDecoration(metal.getGemNameLower() + "_walls", ()-> {
                WallBlock block = new WallBlock(
                        BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL));;

                ModBlocksRegister.BLOCK_METAL_WALLS.put(metal.getGemNameLower(), block);
                return block;
            });
        }

        MetallicsArts.registerBlockDecoration("iron_walls", ()-> {
            WallBlock block = new WallBlock(
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_WALLS.put("iron", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("gold_walls", ()-> {
            WallBlock block = new WallBlock(
                    BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_WALLS.put("gold", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("copper_walls", ()-> {
            WallBlock block = new WallBlock(
                    BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_WALLS.put("copper", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("iron_raw_walls", ()-> {
            WallBlock block = new WallBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_WALLS.put("iron_raw", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("gold_raw_walls", ()-> {
            WallBlock block = new WallBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_GOLD_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_WALLS.put("gold_raw", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("copper_raw_walls", ()-> {
            WallBlock block = new WallBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_COPPER_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_WALLS.put("copper_raw", block);
            return block;
        });



        for (MetalEnum metal: MetalEnum.values()) {
            MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_fences", ()-> {

                FenceBlock block = new FenceBlock(
                        BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL));

                ModBlocksRegister.BLOCK_METAL_FENCES.put(metal.getMetalNameLower(), block);

                return block;
            });
            if (!metal.isAlloy()) {
                MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_raw_fences", ()-> {
                    FenceBlock block = new FenceBlock(
                            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL));

                    ModBlocksRegister.BLOCK_METAL_FENCES.put(metal.getMetalNameLower() + "_raw", block);
                    return block;
                });
            }
        }
        for (GemsEnum metal: GemsEnum.values()) {
            MetallicsArts.registerBlockDecoration(metal.getGemNameLower() + "_fences", ()-> {
                FenceBlock block = new FenceBlock(
                        BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL));;

                ModBlocksRegister.BLOCK_METAL_FENCES.put(metal.getGemNameLower(), block);
                return block;
            });
        }

        MetallicsArts.registerBlockDecoration("iron_fences", ()-> {
            FenceBlock block = new FenceBlock(
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_FENCES.put("iron", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("gold_fences", ()-> {
            FenceBlock block = new FenceBlock(
                    BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_FENCES.put("gold", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("copper_fences", ()-> {
            FenceBlock block = new FenceBlock(
                    BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_FENCES.put("copper", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("iron_raw_fences", ()-> {
            FenceBlock block = new FenceBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_FENCES.put("iron_raw", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("gold_raw_fences", ()-> {
            FenceBlock block = new FenceBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_GOLD_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_FENCES.put("gold_raw", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("copper_raw_fences", ()-> {
            FenceBlock block = new FenceBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_COPPER_BLOCK).sound(SoundType.METAL));

            ModBlocksRegister.BLOCK_METAL_FENCES.put("copper_raw", block);
            return block;
        });

        for (MetalEnum metal: MetalEnum.values()) {
            MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_fence_gates", ()-> {

                FenceGateBlock block = new FenceGateBlock(
                        BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL), WoodType.ACACIA);

                ModBlocksRegister.BLOCK_METAL_FENCE_GATES.put(metal.getMetalNameLower(), block);

                return block;
            });
            if (!metal.isAlloy()) {

                MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_raw_fence_gates", ()-> {
                    FenceGateBlock block = new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL), WoodType.ACACIA);

                    ModBlocksRegister.BLOCK_METAL_FENCE_GATES.put(metal.getMetalNameLower() + "_raw", block);
                    return block;
                });
            }
        }
        for (GemsEnum metal: GemsEnum.values()) {
            MetallicsArts.registerBlockDecoration(metal.getGemNameLower() + "_fence_gates", ()-> {
                FenceGateBlock block = new FenceGateBlock(
                        BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL), WoodType.ACACIA);

                ModBlocksRegister.BLOCK_METAL_FENCE_GATES.put(metal.getGemNameLower(), block);
                return block;
            });
        }

        MetallicsArts.registerBlockDecoration("iron_fence_gates", ()-> {
            FenceGateBlock block = new FenceGateBlock(
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL), WoodType.ACACIA);

            ModBlocksRegister.BLOCK_METAL_FENCE_GATES.put("iron", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("gold_fence_gates", ()-> {
            FenceGateBlock block = new FenceGateBlock(
                    BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK).sound(SoundType.METAL), WoodType.ACACIA);

            ModBlocksRegister.BLOCK_METAL_FENCE_GATES.put("gold", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("copper_fence_gates", ()-> {
            FenceGateBlock block = new FenceGateBlock(
                    BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK).sound(SoundType.METAL), WoodType.ACACIA);

            ModBlocksRegister.BLOCK_METAL_FENCE_GATES.put("copper", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("iron_raw_fence_gates", ()-> {
            FenceGateBlock block = new FenceGateBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK).sound(SoundType.METAL), WoodType.ACACIA);

            ModBlocksRegister.BLOCK_METAL_FENCE_GATES.put("iron_raw", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("gold_raw_fence_gates", ()-> {
            FenceGateBlock block = new FenceGateBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_GOLD_BLOCK).sound(SoundType.METAL), WoodType.ACACIA);

            ModBlocksRegister.BLOCK_METAL_FENCE_GATES.put("gold_raw", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("copper_raw_fence_gates", ()-> {
            FenceGateBlock block = new FenceGateBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_COPPER_BLOCK).sound(SoundType.METAL), WoodType.ACACIA);

            ModBlocksRegister.BLOCK_METAL_FENCE_GATES.put("copper_raw", block);
            return block;
        });

    }
}

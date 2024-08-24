package net.rudahee.metallics_arts.setup.registries.blocks.decoration;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.languages.MetalNamesEnum;
import net.rudahee.metallics_arts.modules.custom_blocks.Panels;
import net.rudahee.metallics_arts.modules.custom_blocks.ettmetal_blocks.*;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

public class DecorationBlockRegister {

    public static void register() {


        MetallicsArts.registerBlockDecoration("copper_panel_variant1", ()-> {Panels block = new Panels(
                BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops()
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.METAL_PANELS.put("copper_panel_variant1", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("aluminum_panel_variant1", ()-> {Panels block = new Panels(
                BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                        .sound(SoundType.METAL)
                        .requiresCorrectToolForDrops()
                        .requiresCorrectToolForDrops());

            ModBlocksRegister.METAL_PANELS.put("aluminum_panel_variant1", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("steel_panel_variant1", ()-> {Panels block = new Panels(
                BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                        .sound(SoundType.METAL)
                        .requiresCorrectToolForDrops()
                        .requiresCorrectToolForDrops());

            ModBlocksRegister.METAL_PANELS.put("steel_panel_variant1", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("iron_panel_variant1", ()-> {Panels block = new Panels(
                BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                        .sound(SoundType.METAL)
                        .requiresCorrectToolForDrops()
                        .requiresCorrectToolForDrops());

            ModBlocksRegister.METAL_PANELS.put("iron_panel_variant1", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("nicrosil_panel_variant1", ()-> {Panels block = new Panels(
                BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                        .sound(SoundType.METAL)
                        .requiresCorrectToolForDrops()
                        .requiresCorrectToolForDrops());

            ModBlocksRegister.METAL_PANELS.put("nicrosil_panel_variant1", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("brass_panel_variant1", ()-> {Panels block = new Panels(
                BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                        .sound(SoundType.METAL)
                        .requiresCorrectToolForDrops()
                        .requiresCorrectToolForDrops());

            ModBlocksRegister.METAL_PANELS.put("brass_panel_variant1", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("bronze_panel_variant1", ()-> {Panels block = new Panels(
                BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                        .sound(SoundType.METAL)
                        .requiresCorrectToolForDrops()
                        .requiresCorrectToolForDrops());

            ModBlocksRegister.METAL_PANELS.put("bronze_panel_variant1", block);
            return block;
        });


        for (MetalNamesEnum metal: MetalNamesEnum.values()) {
            MetallicsArts.registerBlockDecoration(metal.getId() + "_panel", ()-> {
                Panels block = new Panels(
                        BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                                .sound(SoundType.METAL)
                                .requiresCorrectToolForDrops()
                                .requiresCorrectToolForDrops());

                ModBlocksRegister.METAL_PANELS.put(metal.getId()+ "_panel", block);
                return block;
            });
        }


        for (MetalEnum metal: MetalEnum.values()) {
            MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_stairs", ()-> {
                StairBlock block = new StairBlock(
                        ()-> ModBlocksRegister.BLOCK_METAL_BLOCKS.get(metal.getMetalNameLower()).defaultBlockState(),
                        BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)

                                .sound(SoundType.METAL)
                                .requiresCorrectToolForDrops()
                                .requiresCorrectToolForDrops());

                ModBlocksRegister.BLOCK_METAL_STAIRS.put(metal.getMetalNameLower(), block);

                return block;
            });
            if (!metal.isAlloy()) {
                MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_raw_stairs", ()-> {
                    StairBlock block = new StairBlock(
                            ()-> ModBlocksRegister.RAW_METAL_BLOCKS.get(metal.getMetalNameLower()).defaultBlockState(),
                            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                                    .sound(SoundType.METAL)
                                    .requiresCorrectToolForDrops());

                    ModBlocksRegister.BLOCK_METAL_STAIRS.put(metal.getMetalNameLower() + "_raw", block);
                    return block;
                });
            }
        }
        for (GemsEnum metal: GemsEnum.values()) {
            if (metal.equals(GemsEnum.ETTMETAL)) {
                MetallicsArts.registerBlockDecoration(metal.getGemNameLower() + "_stairs", () -> {
                    EttmetalStairs block = new EttmetalStairs(
                            ()-> ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(metal.getGemNameLower()).defaultBlockState(),
                            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                                    .sound(SoundType.METAL)
                                    .requiresCorrectToolForDrops()
                                    .randomTicks());

                    ModBlocksRegister.BLOCK_METAL_STAIRS.put(metal.getGemNameLower(), block);

                    return block;
                });
            } else {
                MetallicsArts.registerBlockDecoration(metal.getGemNameLower() + "_stairs", () -> {
                    StairBlock block = new StairBlock(
                            () -> ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(metal.getGemNameLower()).defaultBlockState(),
                            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                                    .sound(SoundType.METAL)
                                    .requiresCorrectToolForDrops());

                    ModBlocksRegister.BLOCK_METAL_STAIRS.put(metal.getGemNameLower(), block);
                    return block;
                });
            }
        }

        MetallicsArts.registerBlockDecoration("iron_stairs", ()-> {
            StairBlock block = new StairBlock(
                    Blocks.IRON_BLOCK::defaultBlockState,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_STAIRS.put("iron", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("gold_stairs", ()-> {
            StairBlock block = new StairBlock(
                    Blocks.RAW_GOLD_BLOCK::defaultBlockState,
                    BlockBehaviour.Properties.copy(Blocks.RAW_GOLD_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_STAIRS.put("gold", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("iron_raw_stairs", ()-> {
            StairBlock block = new StairBlock(
                    Blocks.RAW_IRON_BLOCK::defaultBlockState,
                    BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_STAIRS.put("iron_raw", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("gold_raw_stairs", ()-> {
            StairBlock block = new StairBlock(
                    Blocks.RAW_GOLD_BLOCK::defaultBlockState,
                    BlockBehaviour.Properties.copy(Blocks.RAW_GOLD_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_STAIRS.put("gold_raw", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("copper_raw_stairs", ()-> {
            StairBlock block = new StairBlock(
                    Blocks.RAW_COPPER_BLOCK::defaultBlockState,
                    BlockBehaviour.Properties.copy(Blocks.RAW_COPPER_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_STAIRS.put("copper_raw", block);
            return block;
        });


        for (MetalEnum metal: MetalEnum.values()) {
            MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_slab", ()-> {

                SlabBlock block = new SlabBlock(
                        BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                                .sound(SoundType.METAL)
                                .requiresCorrectToolForDrops());

                ModBlocksRegister.BLOCK_METAL_SLAB.put(metal.getMetalNameLower(), block);

                return block;
            });
            if (!metal.isAlloy()) {
                MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_raw_slab", ()-> {
                    SlabBlock block = new SlabBlock(
                            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                                    .sound(SoundType.METAL)
                                    .requiresCorrectToolForDrops());

                    ModBlocksRegister.BLOCK_METAL_SLAB.put(metal.getMetalNameLower() + "_raw", block);
                    return block;
                });
            }
        }
        for (GemsEnum metal: GemsEnum.values()) {
            if (metal.equals(GemsEnum.ETTMETAL)) {
                MetallicsArts.registerBlockDecoration(metal.getGemNameLower() + "_slab", () -> {
                    EttmetalSlab block = new EttmetalSlab(
                            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                                    .sound(SoundType.METAL)
                                    .requiresCorrectToolForDrops()
                                    .randomTicks());

                    ModBlocksRegister.BLOCK_METAL_SLAB.put(metal.getGemNameLower(), block);

                    return block;
                });
            } else {
                MetallicsArts.registerBlockDecoration(metal.getGemNameLower() + "_slab", () -> {
                    SlabBlock block = new SlabBlock(
                            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                                    .sound(SoundType.METAL)
                                    .requiresCorrectToolForDrops());
                    ;

                    ModBlocksRegister.BLOCK_METAL_SLAB.put(metal.getGemNameLower(), block);
                    return block;
                });
            }
        }

        MetallicsArts.registerBlockDecoration("iron_slab", ()-> {
            SlabBlock block = new SlabBlock(
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_SLAB.put("iron", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("gold_slab", ()-> {
            SlabBlock block = new SlabBlock(
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_SLAB.put("gold", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("iron_raw_slab", ()-> {
            SlabBlock block = new SlabBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_SLAB.put("iron_raw", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("gold_raw_slab", ()-> {
            SlabBlock block = new SlabBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_GOLD_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_SLAB.put("gold_raw", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("copper_raw_slab", ()-> {
            SlabBlock block = new SlabBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_COPPER_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_SLAB.put("copper_raw", block);
            return block;
        });


        for (MetalEnum metal: MetalEnum.values()) {
            MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_wall", ()-> {

                WallBlock block = new WallBlock(
                        BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                                .sound(SoundType.METAL)
                                .requiresCorrectToolForDrops());

                ModBlocksRegister.BLOCK_METAL_WALL.put(metal.getMetalNameLower(), block);

                return block;
            });
            if (!metal.isAlloy()) {
                MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_raw_wall", ()-> {
                    WallBlock block = new WallBlock(
                            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                                    .sound(SoundType.METAL)
                                    .requiresCorrectToolForDrops());

                    ModBlocksRegister.BLOCK_METAL_WALL.put(metal.getMetalNameLower() + "_raw", block);
                    return block;
                });
            }
        }
        for (GemsEnum metal: GemsEnum.values()) {
            if (metal.equals(GemsEnum.ETTMETAL)) {
                MetallicsArts.registerBlockDecoration(metal.getGemNameLower() + "_wall", () -> {
                    EttmetalWall block = new EttmetalWall(
                            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                                    .sound(SoundType.METAL)
                                    .requiresCorrectToolForDrops()
                                    .randomTicks());

                    ModBlocksRegister.BLOCK_METAL_WALL.put(metal.getGemNameLower(), block);

                    return block;
                });
            } else {

                MetallicsArts.registerBlockDecoration(metal.getGemNameLower() + "_wall", ()-> {
                    WallBlock block = new WallBlock(
                            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                                    .sound(SoundType.METAL)
                                    .requiresCorrectToolForDrops());;

                    ModBlocksRegister.BLOCK_METAL_WALL.put(metal.getGemNameLower(), block);
                    return block;
                });
            }
        }

        MetallicsArts.registerBlockDecoration("iron_wall", ()-> {
            WallBlock block = new WallBlock(
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_WALL.put("iron", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("gold_wall", ()-> {
            WallBlock block = new WallBlock(
                    BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_WALL.put("gold", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("copper_wall", ()-> {
            WallBlock block = new WallBlock(
                    BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_WALL.put("copper", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("iron_raw_wall", ()-> {
            WallBlock block = new WallBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_WALL.put("iron_raw", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("gold_raw_wall", ()-> {
            WallBlock block = new WallBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_GOLD_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_WALL.put("gold_raw", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("copper_raw_wall", ()-> {
            WallBlock block = new WallBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_COPPER_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_WALL.put("copper_raw", block);
            return block;
        });



        for (MetalEnum metal: MetalEnum.values()) {
            MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_fence", ()-> {

                FenceBlock block = new FenceBlock(
                        BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                                .sound(SoundType.METAL)
                                .requiresCorrectToolForDrops());

                ModBlocksRegister.BLOCK_METAL_FENCE.put(metal.getMetalNameLower(), block);

                return block;
            });
            if (!metal.isAlloy()) {
                MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_raw_fence", ()-> {
                    FenceBlock block = new FenceBlock(
                            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                                    .sound(SoundType.METAL)
                                    .requiresCorrectToolForDrops());

                    ModBlocksRegister.BLOCK_METAL_FENCE.put(metal.getMetalNameLower() + "_raw", block);
                    return block;
                });
            }
        }
        for (GemsEnum metal: GemsEnum.values()) {

            if (metal.equals(GemsEnum.ETTMETAL)) {
                MetallicsArts.registerBlockDecoration(metal.getGemNameLower() + "_fence", () -> {
                    EttmetalFence block = new EttmetalFence (
                            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                                    .sound(SoundType.METAL)
                                    .requiresCorrectToolForDrops()
                                    .randomTicks());

                    ModBlocksRegister.BLOCK_METAL_FENCE.put(metal.getGemNameLower(), block);

                    return block;
                });
            } else {

                MetallicsArts.registerBlockDecoration(metal.getGemNameLower() + "_fence", () -> {
                    FenceBlock block = new FenceBlock(
                            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                                    .sound(SoundType.METAL)
                                    .requiresCorrectToolForDrops());

                    ModBlocksRegister.BLOCK_METAL_FENCE.put(metal.getGemNameLower(), block);
                    return block;
                });
            }
        }

        MetallicsArts.registerBlockDecoration("iron_fence", ()-> {
            FenceBlock block = new FenceBlock(
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_FENCE.put("iron", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("gold_fence", ()-> {
            FenceBlock block = new FenceBlock(
                    BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_FENCE.put("gold", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("copper_fence", ()-> {
            FenceBlock block = new FenceBlock(
                    BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_FENCE.put("copper", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("iron_raw_fence", ()-> {
            FenceBlock block = new FenceBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_FENCE.put("iron_raw", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("gold_raw_fence", ()-> {
            FenceBlock block = new FenceBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_GOLD_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_FENCE.put("gold_raw", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("copper_raw_fence", ()-> {
            FenceBlock block = new FenceBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_COPPER_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

            ModBlocksRegister.BLOCK_METAL_FENCE.put("copper_raw", block);
            return block;
        });

        for (MetalEnum metal: MetalEnum.values()) {

            MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_fence_gate", ()-> {

                FenceGateBlock block = new FenceGateBlock(
                        BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                                .sound(SoundType.METAL)
                                .requiresCorrectToolForDrops(), WoodType.ACACIA);

                ModBlocksRegister.BLOCK_METAL_FENCE_GATE.put(metal.getMetalNameLower(), block);

                return block;
            });
            if (!metal.isAlloy()) {

                MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_raw_fence_gate", ()-> {
                    FenceGateBlock block = new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops(), WoodType.ACACIA);

                    ModBlocksRegister.BLOCK_METAL_FENCE_GATE.put(metal.getMetalNameLower() + "_raw", block);
                    return block;
                });
            }
        }
        for (GemsEnum metal: GemsEnum.values()) {

            if (metal.equals(GemsEnum.ETTMETAL)) {
                MetallicsArts.registerBlockDecoration(metal.getGemNameLower() + "_fence_gate", () -> {
                    EttmetalFenceGate block = new EttmetalFenceGate(
                            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                                    .sound(SoundType.METAL)
                                    .requiresCorrectToolForDrops()
                                    .randomTicks(), WoodType.ACACIA);

                    ModBlocksRegister.BLOCK_METAL_FENCE_GATE.put(metal.getGemNameLower(), block);

                    return block;
                });
            } else {

                MetallicsArts.registerBlockDecoration(metal.getGemNameLower() + "_fence_gate", ()-> {
                    FenceGateBlock block = new FenceGateBlock(
                            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                                    .sound(SoundType.METAL)
                                    .requiresCorrectToolForDrops(), WoodType.ACACIA);

                    ModBlocksRegister.BLOCK_METAL_FENCE_GATE.put(metal.getGemNameLower(), block);
                    return block;
                });
            }
        }

        MetallicsArts.registerBlockDecoration("iron_fence_gate", ()-> {
            FenceGateBlock block = new FenceGateBlock(
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops(), WoodType.ACACIA);

            ModBlocksRegister.BLOCK_METAL_FENCE_GATE.put("iron", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("gold_fence_gate", ()-> {
            FenceGateBlock block = new FenceGateBlock(
                    BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops(), WoodType.ACACIA);

            ModBlocksRegister.BLOCK_METAL_FENCE_GATE.put("gold", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("copper_fence_gate", ()-> {
            FenceGateBlock block = new FenceGateBlock(
                    BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops(), WoodType.ACACIA);

            ModBlocksRegister.BLOCK_METAL_FENCE_GATE.put("copper", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("iron_raw_fence_gate", ()-> {
            FenceGateBlock block = new FenceGateBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops(), WoodType.ACACIA);

            ModBlocksRegister.BLOCK_METAL_FENCE_GATE.put("iron_raw", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("gold_raw_fence_gate", ()-> {
            FenceGateBlock block = new FenceGateBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_GOLD_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops(), WoodType.ACACIA);

            ModBlocksRegister.BLOCK_METAL_FENCE_GATE.put("gold_raw", block);
            return block;
        });
        MetallicsArts.registerBlockDecoration("copper_raw_fence_gate", ()-> {
            FenceGateBlock block = new FenceGateBlock(
                    BlockBehaviour.Properties.copy(Blocks.RAW_COPPER_BLOCK)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops(), WoodType.ACACIA);

            ModBlocksRegister.BLOCK_METAL_FENCE_GATE.put("copper_raw", block);
            return block;
        });

    }
}

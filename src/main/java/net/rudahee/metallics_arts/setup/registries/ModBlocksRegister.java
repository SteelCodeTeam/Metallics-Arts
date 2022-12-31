package net.rudahee.metallics_arts.setup.registries;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalsNBTData;
import net.rudahee.metallics_arts.data.enums.interfaces.gems.Gems;
import net.rudahee.metallics_arts.data.enums.interfaces.metals.Metal;
import net.rudahee.metallics_arts.data.enums.interfaces.metals.MetalGenerationData;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.custom_blocks.broken_cristal_block.BrokenCristalBlock;
import net.rudahee.metallics_arts.modules.custom_blocks.ettmetal_block.EttmetalBlock;
import net.rudahee.metallics_arts.modules.custom_blocks.buddings.AtiumBuddingBlock;
import net.rudahee.metallics_arts.modules.custom_blocks.buddings.EttmetalBuddingBlock;
import net.rudahee.metallics_arts.modules.custom_blocks.buddings.LerasiumBuddingBlock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.ToIntFunction;

import static net.rudahee.metallics_arts.MetallicsArts.registerBlock;


public class ModBlocksRegister {

    public static final HashMap<String, Block> BLOCK_METAL_ORES = new HashMap<>();

    public static final HashMap<String, Block> BLOCK_METAL_DEEPSLATE_ORES = new HashMap<>();
    public static final HashMap<String, Block> BLOCK_METAL_BLOCKS = new HashMap<>();

    public static final HashMap<String, Block> RAW_METAL_BLOCKS = new HashMap<>();
    public static final HashMap<String, Block> BLOCK_GEMS_BLOCKS = new HashMap<>();

    //public static RegistryObject<Block> ALLOY_FURNACE_BLOCK = null;

    public static RegistryObject<Block> ETTMETAL_BLOCK = null;


    public static final HashMap<String, Block> DIVINE_CRISTAL_BLOCKS = new HashMap<String, Block>();

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
            MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_block", () -> {
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
                MetallicsArts.registerBlockDecoration("raw_" + metal.getMetalNameLower() + "_block", () -> {
                    Block block = new Block(Block.Properties.of(Material.METAL)
                            .strength(4, 15)
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
                MetallicsArts.registerBlockDecoration(gem.getGemNameLower() + "_block", () -> {
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

        ModBlocksRegister.ETTMETAL_BLOCK = MetallicsArts.registerBlockDecoration(Gems.ETTMETAL.getGemNameLower() + "_block",
                () -> {
                    Block block = new EttmetalBlock(Block.Properties.of(Material.HEAVY_METAL)
                            .strength(10f, 10f)
                            .lightLevel(litBlockEmission(13))
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());

                    BLOCK_GEMS_BLOCKS.put(Gems.ETTMETAL.getGemNameLower(), block);
                    return block;
                });


        MetallicsArts.registerBlockDecoration("atium_cristal_block",
                () -> {
                    Block block = new BrokenCristalBlock(Block.Properties.of(Material.STONE).sound(SoundType.CHAIN).lightLevel(value -> 5).requiresCorrectToolForDrops().strength(5f, 10f));
                    DIVINE_CRISTAL_BLOCKS.put(MetalsNBTData.ATIUM.getGemNameLower(),block);
                    return block;
                });
        MetallicsArts.registerBlockDecoration("lerasium_cristal_block",
                () -> {
                    Block block = new BrokenCristalBlock(Block.Properties.of(Material.STONE).sound(SoundType.CHAIN).lightLevel(value -> 5).requiresCorrectToolForDrops().strength(5f, 10f));
                    DIVINE_CRISTAL_BLOCKS.put(MetalsNBTData.LERASIUM.getGemNameLower(),block);
                    return block;
                });
        MetallicsArts.registerBlockDecoration("ettmetal_cristal_block",
                () -> {
                    Block block = new BrokenCristalBlock(Block.Properties.of(Material.STONE).sound(SoundType.CHAIN).lightLevel(value -> 5).requiresCorrectToolForDrops().strength(5f, 10f));
                    DIVINE_CRISTAL_BLOCKS.put(MetalsNBTData.ETTMETAL.getGemNameLower(),block);
                    return block;
                });

        /*ModBlock.BROKEN_CRISTAL_BLOCK = MetallicsArts.registerBlock("",
                () -> new BrokenCristalBlock(Block.Properties.of(Material.STONE).sound(SoundType.CHAIN).lightLevel(value -> 5)));*/


        //ModBlock.ALLOY_FURNACE_BLOCK = MetallicsArts.registerBlock("alloy_furnace",() -> new AlloyFurnaceBlock(Block.Properties.of(Material.STONE).strength(3.5F).requiresCorrectToolForDrops()));


    }


    public static final RegistryObject<AmethystBlock> BUDDING_ATIUM = registerBlock ("budding_atium",
            () -> new AtiumBuddingBlock(BlockBehaviour.Properties.of(Material.AMETHYST).randomTicks().strength(1.0F).sound(SoundType.AMETHYST).noOcclusion()));

    public static final RegistryObject<AmethystBlock> ATIUM_CLUSTER = registerBlock("atium_cluster",
            () -> new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.of(Material.AMETHYST).noOcclusion().strength(0.5F).randomTicks().sound(SoundType.AMETHYST_CLUSTER).noOcclusion().lightLevel((p_152632_) -> {
                return 5;
            })));

    public static final RegistryObject<AmethystBlock> LARGE_ATIUM_BUD = registerBlock("large_atium_bud",
            () -> new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).sound(SoundType.MEDIUM_AMETHYST_BUD).noOcclusion().lightLevel((p_152629_) -> {
                return 4;
            })));

    public static final RegistryObject<AmethystBlock> MEDIUM_ATIUM_BUD = registerBlock("medium_atium_bud",
            () -> new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).sound(SoundType.LARGE_AMETHYST_BUD).noOcclusion().lightLevel((p_152617_) -> {
                return 2;
            })));

    public static final RegistryObject<AmethystBlock> SMALL_ATIUM_BUD = registerBlock("small_atium_bud",
            () -> new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).sound(SoundType.SMALL_AMETHYST_BUD).noOcclusion().lightLevel((p_187409_) -> {
                return 1;
            })));

    public static final RegistryObject<AmethystBlock> BUDDING_LERASIUM = registerBlock("budding_lerasium",
            () -> new LerasiumBuddingBlock(BlockBehaviour.Properties.of(Material.AMETHYST).randomTicks().strength(1.0F).sound(SoundType.AMETHYST).noOcclusion()));

    public static final RegistryObject<AmethystBlock> LERASIUM_CLUSTER = registerBlock("lerasium_cluster",
            () -> new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.of(Material.AMETHYST).noOcclusion().strength(0.5F).randomTicks().sound(SoundType.AMETHYST_CLUSTER).noOcclusion().lightLevel((p_152632_) -> {
                return 5;
            })));

    public static final RegistryObject<AmethystBlock> LARGE_LERASIUM_BUD = registerBlock("large_lerasium_bud",
            () -> new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).sound(SoundType.MEDIUM_AMETHYST_BUD).noOcclusion().lightLevel((p_152629_) -> {
                return 4;
            })));

    public static final RegistryObject<AmethystBlock> MEDIUM_LERASIUM_BUD = registerBlock("medium_lerasium_bud",
            () -> new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).sound(SoundType.LARGE_AMETHYST_BUD).noOcclusion().lightLevel((p_152617_) -> {
                return 2;
            })));

    public static final RegistryObject<AmethystBlock> SMALL_LERASIUM_BUD = registerBlock("small_lerasium_bud",
            () -> new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).sound(SoundType.SMALL_AMETHYST_BUD).noOcclusion().lightLevel((p_187409_) -> {
                return 1;
            })));




    public static final RegistryObject<AmethystBlock> BUDDING_ETTMETAL = registerBlock("budding_ettmetal",
            () -> new EttmetalBuddingBlock(BlockBehaviour.Properties.of(Material.AMETHYST).randomTicks().strength(1.0F).sound(SoundType.AMETHYST).noOcclusion()));

    public static final RegistryObject<AmethystBlock> ETTMETAL_CLUSTER = registerBlock("ettmetal_cluster",
            () -> new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.of(Material.AMETHYST).noOcclusion().strength(0.5F).randomTicks().sound(SoundType.AMETHYST_CLUSTER).noOcclusion().lightLevel((p_152632_) -> {
                return 5;
            })));

    public static final RegistryObject<AmethystBlock> LARGE_ETTMETAL_BUD = registerBlock("large_ettmetal_bud",
            () -> new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).sound(SoundType.MEDIUM_AMETHYST_BUD).noOcclusion().lightLevel((p_152629_) -> {
                return 4;
            })));

    public static final RegistryObject<AmethystBlock> MEDIUM_ETTMETAL_BUD = registerBlock("medium_ettmetal_bud",
            () -> new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).sound(SoundType.LARGE_AMETHYST_BUD).noOcclusion().lightLevel((p_152617_) -> {
                return 2;
            })));

    public static final RegistryObject<AmethystBlock> SMALL_ETTMETAL_BUD = registerBlock("small_ettmetal_bud",
            () -> new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).sound(SoundType.SMALL_AMETHYST_BUD).noOcclusion().lightLevel((p_187409_) -> {
                return 1;
            })));


    private static ToIntFunction<BlockState> litBlockEmission(int value) {
        return (funcValue) -> {
            return 12;
            //return funcValue.getValue(BlockStateProperties.LIT) ? value : 0;
        };
    }

    public static class InvestedCapabilityRegister {

        public static final Capability<IInvestedPlayerData> PLAYER_CAP = CapabilityManager.get(new CapabilityToken<IInvestedPlayerData>(){
            @Override
            public String toString() {
                return super.toString();
            }
        });

        public static final ResourceLocation IDENTIFIER = new ResourceLocation(MetallicsArts.MOD_ID, "ma_data");

        public static void register(final RegisterCapabilitiesEvent event) {
            event.register(IInvestedPlayerData.class);
        }

    }
}

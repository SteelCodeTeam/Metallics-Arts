package net.rudahee.metallics_arts.setup.registries.blocks;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.data.enums.implementations.OreGenerationEnum;
import net.rudahee.metallics_arts.modules.custom_blocks.ettmetal_blocks.EttmetalBlock;
import net.rudahee.metallics_arts.modules.custom_blocks.sings.ModStandingSignBlock;
import net.rudahee.metallics_arts.modules.custom_blocks.sings.ModWallSignBlock;
import net.rudahee.metallics_arts.modules.custom_blocks.sings.WoodTypeMetal;
import net.rudahee.metallics_arts.modules.custom_blocks.tables.CrucibleFurnaceBlock;
import net.rudahee.metallics_arts.modules.custom_blocks.tables.DistilleryBlock;
import net.rudahee.metallics_arts.modules.custom_blocks.tables.HemalurgyAltarBlock;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

import java.util.function.ToIntFunction;

import static net.rudahee.metallics_arts.MetallicsArts.registerBlock;

public class BasicBlocksRegister {
    public static void register() {
        for (MetalEnum metal: MetalEnum.values()) {
            // If not alloy, create ore.
            if (!metal.isAlloy()) {
                //ORES BLOCKS
                if (metal.isStone()) {
                    registerBlock(metal.getMetalNameLower() + "_ore", () -> {
                        DropExperienceBlock block = new DropExperienceBlock(Block.Properties.of(Material.STONE)
                                .strength(3.0F, 3.0F)
                                .sound(SoundType.STONE)
                                .requiresCorrectToolForDrops(), UniformInt.of(1, 6));
                        OreGenerationEnum.valueOf(metal.getMetalNameUpper()).setBlock(block);
                        ModBlocksRegister.BLOCK_METAL_ORES.put(metal.getMetalNameLower(), block);
                        return block;
                    });
                }
                //DEEPSLATE BLOCKS
                if (metal.isDeepslate()) {
                    registerBlock("deepslate_" + metal.getMetalNameLower() + "_ore", () -> {
                        DropExperienceBlock block = new DropExperienceBlock(Block.Properties.of(Material.STONE)
                                .strength(4.5F, 3.0F)
                                .sound(SoundType.DEEPSLATE)
                                .requiresCorrectToolForDrops(), UniformInt.of(3, 10));
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

        }


        for (GemsEnum gem : GemsEnum.values()) {
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
        }
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

        ModBlocksRegister.CRUCIBLE_FURNACE = MetallicsArts.registerBlock("crucible_furnace", () -> {
            Block block = new CrucibleFurnaceBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(10f, 10f)
                    .lightLevel(litBlockEmission(16))
                    .sound(SoundType.BASALT)
                    .randomTicks()
                    .requiresCorrectToolForDrops()
                    .destroyTime(15f));
            return block;
        });

        ModBlocksRegister.HEMALURGY_ALTAR = MetallicsArts.registerBlock("hemalugy_altar", () -> {
            Block block = new HemalurgyAltarBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(10f, 10f)
                    .lightLevel(litBlockEmission(16))
                    .sound(SoundType.BASALT)
                    .randomTicks()
                    .requiresCorrectToolForDrops()
                    .destroyTime(15f));
            return block;
        });

        ModBlocksRegister.DESTILLERY = MetallicsArts.registerBlock("distillery", () -> {
            Block block = new DistilleryBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(10f, 10f)
                    .lightLevel(litBlockEmission(16))
                    .sound(SoundType.BASALT)
                    .randomTicks()
                    .requiresCorrectToolForDrops()
                    .destroyTime(15f));
            return block;
        });

        ModBlocksRegister.IRON_WALL_SIGN = MetallicsArts.registerBlockNoItem("iron_wall_sign", ()
                -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_WALL_SIGN), WoodTypeMetal.IRON_TYPE));

        ModBlocksRegister.IRON_STANDING_SIGN = MetallicsArts.registerBlockNoItem("iron_standing_sign", ()
                -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_SIGN), WoodTypeMetal.IRON_TYPE));
    }


    private static ToIntFunction<BlockState> litBlockEmission(int value) {
        return (funcValue) -> {
            return value;
            //return funcValue.getValue(BlockStateProperties.LIT) ? value : 0;
        };
    }
}

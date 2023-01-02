package net.rudahee.metallics_arts.setup.registries.blocks.geodes;

import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_blocks.broken_cristal_block.BrokenCristalBlock;
import net.rudahee.metallics_arts.modules.custom_blocks.buddings.LerasiumBuddingBlock;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

import static net.rudahee.metallics_arts.MetallicsArts.registerBlock;

public class LerasiumBlocksRegister {

    public static void register() {

        MetallicsArts.registerBlockDecoration("lerasium_cristal_block",
                () -> {
                    Block block = new BrokenCristalBlock(Block.Properties.of(Material.STONE).sound(SoundType.CHAIN).lightLevel(value -> 5).requiresCorrectToolForDrops().strength(5f, 10f));
                    ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.put(MetalTagEnum.LERASIUM.getGemNameLower(),block);
                    return block;
                });

        ModBlocksRegister.BUDDING_LERASIUM = registerBlock("budding_lerasium",
                () -> new LerasiumBuddingBlock(BlockBehaviour.Properties.of(Material.AMETHYST).randomTicks().strength(1.0F).sound(SoundType.AMETHYST).noOcclusion()));

        ModBlocksRegister.LERASIUM_CLUSTER = registerBlock("lerasium_cluster",
                () -> new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.of(Material.AMETHYST).noOcclusion().strength(0.5F).randomTicks().sound(SoundType.AMETHYST_CLUSTER).noOcclusion().lightLevel((p_152632_) -> 5)));

        ModBlocksRegister.LARGE_LERASIUM_BUD = registerBlock("large_lerasium_bud",
                () -> new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).sound(SoundType.MEDIUM_AMETHYST_BUD).noOcclusion().lightLevel((p_152629_) -> 4)));

        ModBlocksRegister.MEDIUM_LERASIUM_BUD = registerBlock("medium_lerasium_bud",
                () -> new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).sound(SoundType.LARGE_AMETHYST_BUD).noOcclusion().lightLevel((p_152617_) -> 2)));

        ModBlocksRegister.SMALL_LERASIUM_BUD = registerBlock("small_lerasium_bud",
                () -> new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).sound(SoundType.SMALL_AMETHYST_BUD).noOcclusion().lightLevel((p_187409_) -> 1)));
    }
}

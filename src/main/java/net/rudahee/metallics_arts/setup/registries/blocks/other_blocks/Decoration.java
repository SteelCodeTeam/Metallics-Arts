package net.rudahee.metallics_arts.setup.registries.blocks.other_blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

public class Decoration {

    public static void register(){
        for (MetalTagEnum metal:MetalTagEnum.values()){
            MetallicsArts.registerBlockDecoration(metal.getMetalNameLower() + "_stairs", ()->{

                StairBlock block = new StairBlock(()-> ModBlocksRegister.BLOCK_METAL_BLOCKS.get(metal.getNameLower()).defaultBlockState(), Block.Properties.of(Material.METAL)
                            .strength(5, 15)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops());
                ModBlocksRegister.BLOCK_METAL_STAIRS.put(metal.getNameLower(), block);

                return block;

            }
                     );
        }
    }
}

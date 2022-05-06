package net.rudahee.metallics_arts.modules.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IAllomancyUsableBlock {

    /**
     * Called when the block is steelpushed or ironpulled
     *
     * @param isPush whether or not the activation is Steel
     * @return whether or not the block was activated
     */
    boolean IAllomancyUsableBlock(BlockState state, World world, BlockPos pos, PlayerEntity playerIn, boolean isPush);
}

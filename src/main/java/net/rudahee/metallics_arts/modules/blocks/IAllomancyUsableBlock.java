package net.rudahee.metallics_arts.modules.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface IAllomancyUsableBlock {

    /**
     * Called when the block is steelpushed or ironpulled
     *
     * @param isPush whether or not the activation is Steel
     * @return whether or not the block was activated
     */
    boolean IAllomancyUsableBlock(BlockState state, Level world, BlockPos pos, Player playerIn, boolean isPush);
}

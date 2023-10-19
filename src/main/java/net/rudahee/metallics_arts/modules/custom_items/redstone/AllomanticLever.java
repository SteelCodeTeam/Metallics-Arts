package net.rudahee.metallics_arts.modules.custom_items.redstone;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class AllomanticLever extends LeverBlock {

    /**
     * Constructs an AllomanticLever with the specified block properties.
     *
     * @param properties The properties to configure the behavior of the lever block.
     */
    public AllomanticLever(BlockBehaviour.Properties properties) {
        super(properties);
    }

    /**
     * Handles the interaction with the AllomanticLever block.
     *
     * @param blockState The current state of the lever block.
     * @param level The level (world) in which the interaction occurs.
     * @param blockPos The position of the lever block.
     * @param player The player performing the interaction.
     * @param interactionHand The hand used by the player to interact.
     * @param blockHitResult The result of the block hit during the interaction.
     *
     * @return The result of the interaction, which is set to InteractionResult.FAIL.
     */
    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        return InteractionResult.FAIL;
    }
}

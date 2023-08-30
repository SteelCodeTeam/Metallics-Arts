package net.rudahee.metallics_arts.modules.custom_blocks.buddings;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import org.jetbrains.annotations.NotNull;

/**
 * This class is used to create a custom ettmetal budding block, extended default amethyst block.
 * It also defines the behavior that occurs to grow ettmetal.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see Block
 */
public class EttmetalBuddingBlock extends AmethystBlock {

    public static final int GROWTH_CHANCE = 5;
    private static final Direction[] DIRECTIONS = Direction.values();

    /**
     * Default constructor to a custom Budding Block. It is initialized with the basic properties passed by parameter.
     * This method only call to super() with properties.
     *
     * @param properties properties of the block.
     *
     * @see Block
     */
    public EttmetalBuddingBlock(Properties properties) {
        super(properties);
    }

    /**
     * Method to define the default behaviour when piston is pushing/pulling the block.
     * By default, the block will always be destroyed.
     * <p>
     * It's deprecated by Minecraft, so, we need to change in a near future.
     *
     * @param blockState blockState of the block.
     *
     * @return PushReaction
     *
     * @see Block
     * @see AmethystBlock#getPistonPushReaction
     * @see PushReaction
     */
    public @NotNull PushReaction getPistonPushReaction(@NotNull BlockState blockState) {
        return PushReaction.DESTROY;
    }

    /**
     * Method to define the events that be release each X random ticks. In this case, We're going to control de grow of the block.
     * <p>
     * It's deprecated by Minecraft, so, we need to change in a near future.
     *
     * @param blockState state of the block.
     * @param serverLevel level of the block.
     * @param blockPos position in the level of the block.
     * @param randomSource source to control the random ticks.
     *
     * @see Block
     * @see AmethystBlock#getPistonPushReaction
     */
    @Deprecated
    public void randomTick(@NotNull BlockState blockState, @NotNull ServerLevel serverLevel, @NotNull BlockPos blockPos, RandomSource randomSource) {
        int rng = randomSource.nextInt(5);

        if (rng == 0 || rng == 1 || rng == 2)   {
            Direction direction = DIRECTIONS[randomSource.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = blockPos.relative(direction);
            BlockState blockstate = serverLevel.getBlockState(blockpos);
            Block block = null;
            if (canClusterGrowAtState(blockstate)) {
                block = ModBlocksRegister.SMALL_ETTMETAL_BUD.get();
            } else if (blockstate.is(ModBlocksRegister.SMALL_ETTMETAL_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = ModBlocksRegister.MEDIUM_ETTMETAL_BUD.get();
            } else if (blockstate.is(ModBlocksRegister.MEDIUM_ETTMETAL_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = ModBlocksRegister.LARGE_ETTMETAL_BUD.get();
            } else if (blockstate.is(ModBlocksRegister.LARGE_ETTMETAL_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = ModBlocksRegister.ETTMETAL_CLUSTER.get();
            }

            if (block != null) {
                BlockState blockstate1 = block.defaultBlockState().setValue(AmethystClusterBlock.FACING, direction).setValue(AmethystClusterBlock.WATERLOGGED, Boolean.valueOf(blockstate.getFluidState().getType() == Fluids.WATER));
                serverLevel.setBlockAndUpdate(blockpos, blockstate1);
            }

        }
    }

    /**
     * Auxiliary method to control if a cluster in the budding block can growth. To do this,
     * check the near blocks to find non-solid blocks.
     *
     * @param blockState state of the block.
     *
     * @return boolean
     */
    public static boolean canClusterGrowAtState(BlockState blockState) {
        return blockState.isAir() || blockState.is(Blocks.WATER) && blockState.getFluidState().getAmount() == 8;
    }
}

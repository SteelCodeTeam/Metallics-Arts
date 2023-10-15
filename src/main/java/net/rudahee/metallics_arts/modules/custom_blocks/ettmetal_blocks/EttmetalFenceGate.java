package net.rudahee.metallics_arts.modules.custom_blocks.ettmetal_blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.AABB;
import net.rudahee.metallics_arts.utils.BlockUtils;
import org.jetbrains.annotations.NotNull;

public class EttmetalFenceGate extends FenceGateBlock {
    public EttmetalFenceGate(Properties properties, WoodType type) {
        super(properties, type);
    }

    /**
     * Check if the block is randomly doing ticks, by default, no matter state, always going to do ticks.
     *
     * @param blockState blockState of the block.
     *
     * @return boolean
     *
     * @see Block
     */
    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return true;
    }

    /**
     * Method to define the events that be release each X random ticks. In this case, We're going to check if
     * water is touching ettmetal block, to generate an explosion. Also, we call super() too.
     * <p>
     * It's deprecated by Minecraft, so, we need to change in a near future.
     *
     * @param blockState state of the block.
     * @param serverLevel level of the block.
     * @param blockPos position in the level of the block.
     * @param random source to control the random ticks.
     *
     * @see Block
     */
    @Override
    public void tick(@NotNull BlockState blockState, @NotNull ServerLevel serverLevel, BlockPos blockPos, @NotNull RandomSource random) {
        if (serverLevel.isWaterAt(blockPos)) {
    serverLevel.explode(null, (double) blockPos.getX(), (double) blockPos.getY(), (double) blockPos.getZ(), 8.0f, true, Level.ExplosionInteraction.BLOCK);
        }

        BlockPos posLeftTop = new BlockPos(blockPos.getX() + 1, blockPos.getY() + 1, blockPos.getZ() + 1);
        BlockPos posRightDown = new BlockPos(blockPos.getX() - 1, blockPos.getY() - 1, blockPos.getZ() - 1);
        AABB area  = new AABB(posLeftTop, posRightDown);

        if (BlockUtils.isTouchingWater(serverLevel, area)) {

            serverLevel.explode(null, (double) blockPos.getX(), (double) blockPos.getY(), (double) blockPos.getZ(), 8.0f, true, Level.ExplosionInteraction.BLOCK);
            serverLevel.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 1);
        }

        super.tick(blockState, serverLevel, blockPos, random);
    }
}

package net.rudahee.metallics_arts.modules.custom_blocks.ettmetal_block;


import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

/**
 * This class is used to create a custom ettmetal block, extended default block.
 * It also defines the behavior that occurs when tick with water it's in contact with these block.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see Block
 */
public class EttmetalBlock extends Block {

    /**
     * Default constructor to a custom Ettmetal Block. It is initialized with the basic properties passed by parameter.
     * This method only call to super() with properties.
     *
     * @param properties properties of the block.
     *
     * @see Block
     * @see BlockState
     */
    public EttmetalBlock(BlockBehaviour.Properties properties) {
        super(properties);
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

        BlockPos posLeftTop = new BlockPos(blockPos.getX() + 1, blockPos.getY() + 1, blockPos.getZ() + 1);
        BlockPos posRightDown = new BlockPos(blockPos.getX() - 1, blockPos.getY() - 1, blockPos.getZ() - 1);
        AABB area  = new AABB(posLeftTop, posRightDown);

        if (isTouchingWater(serverLevel, area)) {

            serverLevel.explode(null, (double) blockPos.getX(), (double) blockPos.getY(), (double) blockPos.getZ(), 8.0f, true, Level.ExplosionInteraction.BLOCK);// todo antes era destroy
            serverLevel.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 1);
        }

        super.tick(blockState, serverLevel, blockPos, random);
    }

    /**
     * Auxiliary method to check if in the area exists water in specific world.
     *
     * @param level to check.
     * @param area to check.
     *
     * @return boolean
     *
     * @see AABB
     */
    private boolean isTouchingWater(ServerLevel level, AABB area) {
        boolean isTouchingWater = false;

        //todo chekear estos casteos
        for (int x = (int) area.minX; x <= area.maxX; x++) {
            for (int y = (int) area.minY; y <= area.maxY; y++) {
                for (int z = (int) area.minZ; z <= area.maxZ; z++) {

                    if (level.getFluidState(new BlockPos(x,y,z)).is(FluidTags.WATER)) {
                        isTouchingWater = true;
                    }
                }
            }
        }
        return isTouchingWater;
    }

}

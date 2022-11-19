package net.rudahee.metallics_arts.modules.blocks;


import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;


public class EttmetalBlock extends Block {
    public EttmetalBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return true;
    }


    @Override
    public void tick(BlockState blockState, ServerLevel serverWorld, BlockPos blockPos, RandomSource random) {

        BlockPos posLeftTop = new BlockPos(blockPos.getX() + 1, blockPos.getY() + 1, blockPos.getZ() + 1);
        BlockPos posRightDown = new BlockPos(blockPos.getX() - 1, blockPos.getY() - 1, blockPos.getZ() - 1);
        AABB area  = new AABB(posLeftTop, posRightDown);

        if (isTouchingWater(serverWorld, area)){
            serverWorld.explode(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 8.0f, true, Explosion.BlockInteraction.DESTROY);
            serverWorld.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 1);
        }

        super.tick(blockState, serverWorld, blockPos, random);
    }

    private boolean isTouchingWater(ServerLevel world, AABB area) {
        boolean isTouchingWater = false;
        for (double x = area.minX; x <= area.maxX; x++) {
            for (double y = area.minY; y <= area.maxY; y++) {
                for (double z = area.minZ; z <= area.maxZ; z++) {

                    if (world.getFluidState(new BlockPos(x,y,z)).is(FluidTags.WATER)) {
                        isTouchingWater = true;
                    }
                }
            }
        }
        return isTouchingWater;
    }

}

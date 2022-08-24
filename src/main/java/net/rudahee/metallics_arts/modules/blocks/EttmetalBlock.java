package net.rudahee.metallics_arts.modules.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class EttmetalBlock extends Block {
    public EttmetalBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return true;
    }

    @Override
    public void tick(BlockState blockState, ServerWorld serverWorld, BlockPos blockPos, Random random) {

        BlockPos posLeftTop = new BlockPos(blockPos.getX() + 1, blockPos.getY() + 1, blockPos.getZ() + 1);
        BlockPos posRightDown = new BlockPos(blockPos.getX() - 1, blockPos.getY() - 1, blockPos.getZ() - 1);
        AxisAlignedBB area  = new AxisAlignedBB(posLeftTop, posRightDown);

        if (isTouchingWater(serverWorld, area)){
            serverWorld.explode(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 8.0f, true, Explosion.Mode.DESTROY);
        }

        super.tick(blockState, serverWorld, blockPos, random);
    }

    private boolean isTouchingWater(ServerWorld world, AxisAlignedBB area) {
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

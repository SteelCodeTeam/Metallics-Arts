package net.rudahee.metallics_arts.modules.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class EttmetalBlockLogic extends Block {
    public EttmetalBlockLogic(Properties propieties) {
        super(propieties);
    }

    /*
    @SuppressWarnings("deprecation")
    @Override
    public void tick(BlockState blockState, ServerWorld serverWorld, BlockPos blockPos, Random random) {

        BlockPos posLeftTop = new BlockPos(blockPos.getX() + 2, blockPos.getY() + 2, blockPos.getZ() + 2);
        BlockPos posRightDown = new BlockPos(blockPos.getX() - 2, blockPos.getY() - 2, blockPos.getZ() - 2);
        AxisAlignedBB alignedBB  = new AxisAlignedBB(posLeftTop,posRightDown);

        if (touchesLiquid(blockState)){
            System.out.println("Toco agua");
        }

        super.tick(blockState, serverWorld, blockPos, random);
    }

    private static boolean touchesLiquid(BlockState blockState) {
        boolean flag = false;
        if (canSolidify(blockState)) {
            flag = true;
        }

        return flag;
    }

    private static boolean canSolidify(BlockState blockState) {
        return blockState.getFluidState().is(FluidTags.WATER);
    }*/
}

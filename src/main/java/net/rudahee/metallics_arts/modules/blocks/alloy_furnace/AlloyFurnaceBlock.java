package net.rudahee.metallics_arts.modules.blocks.alloy_furnace;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.network.PacketBuffer;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeBlock;
import net.minecraftforge.fml.network.NetworkHooks;
import net.rudahee.metallics_arts.modules.blocks.IAllomancyUsableBlock;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class AlloyFurnaceBlock extends ContainerBlock {

    //public static final DirectionProperty FACING = HorizontalBlock.FACING;
    //public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public AlloyFurnaceBlock(Properties properties) {
        super(properties);
        //this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.valueOf(false)));
    }

    /*@Override
    public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean bool) {
        super.onRemove(state, world, pos, newState, bool);
    }*/

    @Deprecated
    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos,
                                             PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isClientSide) {
            TileEntity tileEntity = worldIn.getBlockEntity(pos);
                if(tileEntity instanceof AlloyFurnaceTileEntity) {

                    if (player instanceof ServerPlayerEntity) {
                        NetworkHooks.openGui((ServerPlayerEntity) player, this.getMenuProvider(state, worldIn, pos), tileEntity.getBlockPos());
                    } else {
                        NetworkHooks.openGui((ServerPlayerEntity) player, this.getMenuProvider(state, worldIn, pos), new Consumer<PacketBuffer>() {
                            @Override
                            public void accept(PacketBuffer buffer) {
                                buffer.writeInt(player.getId());
                            }
                        });
                        return ActionResultType.SUCCESS;

                    }
                } else {
                    throw new IllegalStateException("Our Container provider is missing!");
                }
        }
        return ActionResultType.SUCCESS;
    }


    /*public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }*/

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return newBlockEntity(world);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }


    /*@Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        return this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, ctx.getHorizontalDirection().getOpposite());
    }*/


    /*@Override
    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(BlockStateProperties.HORIZONTAL_FACING, rotation.rotate(blockState.getValue(BlockStateProperties.HORIZONTAL_FACING)));
    }

    @Override
    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(BlockStateProperties.HORIZONTAL_FACING)));
    }*/

   /* @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateContainer) {
        stateContainer.add(FACING, LIT);
    }*/


    /*
    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random rng) {
        if (state.getValue(LIT)) {
            double d0 = (double) pos.getX() + 0.5D;
            double d1 = (double) pos.getY();
            double d2 = (double) pos.getZ() + 0.5D;

            if (rng.nextDouble() < 0.1D) {
                world.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = state.getValue(FACING);
            Direction.Axis directionAxis = direction.getAxis();

            double d4 = rng.nextDouble() * 0.6D - 0.3D;
            double d5 = directionAxis == Direction.Axis.X ? (double)direction.getStepX() * 0.52D : d4;
            double d6 = rng.nextDouble() * 6.0D / 16.0D;
            double d7 = directionAxis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52D : d4;

            world.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
            world.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
        }
    }


     */

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader reader) {
        return new AlloyFurnaceTileEntity();
    }
}

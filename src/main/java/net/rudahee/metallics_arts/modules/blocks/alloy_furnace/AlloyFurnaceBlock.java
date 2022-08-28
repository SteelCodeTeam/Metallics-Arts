package net.rudahee.metallics_arts.modules.blocks.alloy_furnace;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeBlock;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Consumer;

public class AlloyFurnaceBlock extends FurnaceBlock {

    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    public static final BooleanProperty LIT = FurnaceBlock.LIT;

    public AlloyFurnaceBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.valueOf(false)));
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

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return newBlockEntity(world);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }


    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        return this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, ctx.getHorizontalDirection().getOpposite());
    }


    @Override
    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(BlockStateProperties.HORIZONTAL_FACING, rotation.rotate(blockState.getValue(BlockStateProperties.HORIZONTAL_FACING)));
    }

    public BlockState lit(BlockState blockState, Boolean isLit) {
        return blockState.setValue(BlockStateProperties.LIT, isLit);
    }

    @Override
    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(BlockStateProperties.HORIZONTAL_FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateContainer) {
        stateContainer.add(FACING, LIT);
    }



    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random rng) {
        if (state.getValue(LIT)) {
            if (rng.nextInt(10) == 0) {
                world.playLocalSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 0.5F + rng.nextFloat(), rng.nextFloat() * 0.7F + 0.6F, false);
            }
            for(int i = 0; i < rng.nextInt(1) + 1; ++i) {
                //world.addParticle(ParticleTypes.LAVA, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, (double)(rng.nextFloat() / 2.0F), 5.0E-5D, (double)(rng.nextFloat() / 2.0F));
                world.addParticle(ParticleTypes.SMOKE, (double)pos.getX() + 0.25D + rng.nextDouble() / 2.0D * (double)(rng.nextBoolean() ? 1 : -1), (double)pos.getY() + 0.4D, (double)pos.getZ() + 0.25D + rng.nextDouble() / 2.0D * (double)(rng.nextBoolean() ? 1 : -1), 0.0D, 0.005D, 0.0D);
            }
            BasicParticleType basicparticletype = ParticleTypes.CAMPFIRE_COSY_SMOKE;
            world.addAlwaysVisibleParticle(basicparticletype, true, (double)pos.getX() + 0.5D + rng.nextDouble() / 3.0D * (double)(rng.nextBoolean() ? 1 : -1), (double)pos.getY() + rng.nextDouble() + rng.nextDouble(), (double)pos.getZ() + 0.5D + rng.nextDouble() / 3.0D * (double)(rng.nextBoolean() ? 1 : -1), 0.0D, 0.07D, 0.0D);

        }

        super.animateTick(state, world, pos, rng);
    }




    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader reader) {
        return new AlloyFurnaceTileEntity();
    }
}

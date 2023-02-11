package net.rudahee.metallics_arts.modules.custom_blocks.alloy_furnace;


import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.Nullable;

public class AlloyFurnaceBlock extends AbstractFurnaceBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty LIT = FurnaceBlock.LIT;

    public AlloyFurnaceBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void openContainer(Level p_48690_, BlockPos p_48691_, Player p_48692_) {

    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return null;
    }

    /*public void onRemove(BlockState state, Level level, BlockPos blockPos, BlockState newState, boolean bool) {

        if (!state.is(newState.getBlock())) {
            BlockEntity tileentity = level.getBlockEntity(blockPos);
            if (tileentity instanceof  AlloyFurnaceTileEntity) {
                AlloyFurnaceTileEntity furnaceTE = (AlloyFurnaceTileEntity)tileentity;
                ItemStack stack;
                for (int i=0; i<=5; i++) {
                    stack = furnaceTE.itemHandler.getStackInSlot(i);
                    Containers.dropContents(level, blockPos, NonNullList.of(stack));
                }
            }
        }
        super.onRemove(state, level, blockPos, newState, bool);
    }*/

    /*



    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean bool) {

        if (!state.is(newState.getBlock())) {
            BlockEntity tileentity = world.getBlockEntity(pos);
            if (tileentity instanceof  AlloyFurnaceTileEntity) {
                AlloyFurnaceTileEntity furnaceTE = (AlloyFurnaceTileEntity)tileentity;
                ItemStack stack;
                for (int i=0; i<=5; i++) {
                    stack = furnaceTE.itemHandler.getStackInSlot(i);
                    Containers.dropContents(world, pos, NonNullList.of(stack));
                }
            }
        }
        super.onRemove(state, world, pos, newState, bool);
    }

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

    @Override
    protected void openContainer(Level world, BlockPos pos, Player player) {
        if(!world.isClientSide) {
            BlockEntity tileEntity = world.getBlockEntity(pos);
            BlockState state = world.getBlockState(pos);
            if(tileEntity instanceof AlloyFurnaceTileEntity) {

                if (player instanceof ServerPlayer) {
                    NetworkHooks.openScreen((ServerPlayer) player, this.getMenuProvider(state, world, pos), tileEntity.getBlockPos());
                } else {
                    NetworkHooks.openScreen((ServerPlayer) player, this.getMenuProvider(state, world, pos), new Consumer<FriendlyByteBuf>() {
                        @Override
                        public void accept(FriendlyByteBuf buffer) {
                            buffer.writeInt(player.getId());
                        }
                    });

                }
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
    }


    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        if(state.getValue(LIT)) {
            return true;
        } else {
            return false;
        }
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateContainer) {
        stateContainer.add(FACING, LIT);
    }



    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource rng) {
        if (state.getValue(LIT)) {
            if (rng.nextInt(10) == 0) {
                world.playLocalSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 0.5F + rng.nextFloat(), rng.nextFloat() * 0.7F + 0.6F, false);
            }
            for(int i = 0; i < rng.nextInt(1) + 1; ++i) {
                //world.addParticle(ParticleTypes.LAVA, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, (double)(rng.nextFloat() / 2.0F), 5.0E-5D, (double)(rng.nextFloat() / 2.0F));
                world.addParticle(ParticleTypes.SMOKE, (double)pos.getX() + 0.25D + rng.nextDouble() / 2.0D * (double)(rng.nextBoolean() ? 1 : -1), (double)pos.getY() + 0.4D, (double)pos.getZ() + 0.25D + rng.nextDouble() / 2.0D * (double)(rng.nextBoolean() ? 1 : -1), 0.0D, 0.005D, 0.0D);
            }
            SimpleParticleType basicparticletype = ParticleTypes.CAMPFIRE_COSY_SMOKE;
            world.addAlwaysVisibleParticle(basicparticletype, true, (double)pos.getX() + 0.5D + rng.nextDouble() / 3.0D * (double)(rng.nextBoolean() ? 1 : -1), (double)pos.getY() + rng.nextDouble() + rng.nextDouble(), (double)pos.getZ() + 0.5D + rng.nextDouble() / 3.0D * (double)(rng.nextBoolean() ? 1 : -1), 0.0D, 0.07D, 0.0D);

        }

        super.animateTick(state, world, pos, rng);
    }




    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AlloyFurnaceTileEntity();
    }
*/
}

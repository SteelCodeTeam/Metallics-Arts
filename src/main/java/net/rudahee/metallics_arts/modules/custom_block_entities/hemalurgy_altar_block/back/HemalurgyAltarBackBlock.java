package net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.back;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import net.rudahee.metallics_arts.setup.registries.ModBlockEntitiesRegister;
import org.jetbrains.annotations.Nullable;

public class HemalurgyAltarBackBlock extends BaseEntityBlock {


    public HemalurgyAltarBackBlock(Properties properties) {
        super(properties);
    }

    /*
            BED STUFF
     */

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float modifier) {
        super.fallOn(level, state, pos, entity, modifier);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return super.getShape(state, blockGetter, pos, context);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    /*
        HORIZONTAL FACING
    */



    /*
            BLOCK ENTITY
     */

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new HemalurgyAltarBackBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                                  BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntitiesRegister.HEMALURGY_ALTAR_BACK_ENTITY.get(),
                HemalurgyAltarBackBlockEntity::tick);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);
            if(entity instanceof HemalurgyAltarBackBlockEntity) {
                NetworkHooks.openScreen(((ServerPlayer)player), (HemalurgyAltarBackBlockEntity)entity, pos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }
}

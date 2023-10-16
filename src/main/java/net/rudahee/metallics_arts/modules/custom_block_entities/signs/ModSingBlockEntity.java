package net.rudahee.metallics_arts.modules.custom_block_entities.signs;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rudahee.metallics_arts.setup.registries.ModBlockEntitiesRegister;

public class ModSingBlockEntity extends SignBlockEntity {
    public ModSingBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(blockPos, blockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntitiesRegister.BLOCK_ENTITY.get();
    }
}

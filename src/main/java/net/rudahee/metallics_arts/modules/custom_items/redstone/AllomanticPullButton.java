package net.rudahee.metallics_arts.modules.custom_items.redstone;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.BlockHitResult;

public class AllomanticPullButton  extends ButtonBlock {

    public AllomanticPullButton(BlockBehaviour.Properties p_273290_, BlockSetType p_273462_, int p_273212_) {
        super(p_273290_, p_273462_, p_273212_, false);
    }

    @Override
    public InteractionResult use(BlockState p_51088_, Level p_51089_, BlockPos p_51090_, Player p_51091_, InteractionHand p_51092_, BlockHitResult p_51093_) {
        return InteractionResult.FAIL;
    }
}

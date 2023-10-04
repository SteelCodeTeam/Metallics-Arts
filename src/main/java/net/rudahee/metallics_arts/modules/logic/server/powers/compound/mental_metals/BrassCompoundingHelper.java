package net.rudahee.metallics_arts.modules.logic.server.powers.compound.mental_metals;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class BrassCompoundingHelper {

    public static void compoundingBrass(Player player, BlockPos blockPos) {

        Level level = player.getLevel();
        BlockState blockstate = level.getBlockState(blockPos);
        if (!CampfireBlock.canLight(blockstate) && !CandleBlock.canLight(blockstate) && !CandleCakeBlock.canLight(blockstate)) {
            //player.getClickedFace()

            BlockPos blockpos1 = blockPos.relative(player.getDirection().getOpposite());
            //player.getHorizontalDirection()
            if (BaseFireBlock.canBePlacedAt(level, blockpos1, player.getDirection().getOpposite())) {
                level.playSound(player, blockpos1, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                BlockState blockstate1 = BaseFireBlock.getState(level, blockpos1);
                level.setBlock(blockpos1, blockstate1, 11);
                level.gameEvent(player, GameEvent.BLOCK_PLACE, blockPos);

            }
        }
    }
}

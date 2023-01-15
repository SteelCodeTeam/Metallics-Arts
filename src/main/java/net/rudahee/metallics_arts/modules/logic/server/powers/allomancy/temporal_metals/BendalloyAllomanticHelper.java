package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.temporal_metals;


import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;


public class BendalloyAllomanticHelper {

    /**
     *
     * @param player to whom the effect will be applied.
     * @param level in which the player is located (world)
     * @param enhanced if player is burning Duralumin or the player was hit with nicrosil
     */
    public static void BendalloyMobEffects(Player player, ServerLevel level, boolean enhanced, boolean lerasium) {

        int radius = 8;
        if (enhanced && lerasium) {
            radius = 13;
        } else if (enhanced) {
            radius = 11;
        } else if (lerasium) {
            radius = 10;
        }

        BlockPos negative = new BlockPos(player.position()).offset(- radius, - radius, - radius);
        BlockPos positive = new BlockPos(player.position()).offset(radius, radius , radius);


        level.getEntitiesOfClass(LivingEntity.class, CapabilityUtils.getBubble(player, radius)).forEach(entity -> {
            if (!(entity instanceof Player)) {
                entity.aiStep();
                if (enhanced) {
                    entity.aiStep();
                    entity.aiStep();
                }
            }
        });

        BlockPos.betweenClosedStream(negative, positive).forEach(blockPos -> {

            BlockState block = level.getBlockState(blockPos);
            BlockEntity tileEntity = level.getBlockEntity(blockPos);

            if (block.is(ModBlocksRegister.BUDDING_ATIUM.get()) || block.is(ModBlocksRegister.BUDDING_LERASIUM.get()) || block.is(ModBlocksRegister.BUDDING_ETTMETAL.get()) ) {
                return;
            }

            if (Math.random() > 0.5) {
                if (tileEntity == null && block.isRandomlyTicking()) {
                    block.randomTick(level, blockPos, level.random);
                }

                else if (tileEntity instanceof TickingBlockEntity) {
                    BlockEntityTicker ticker = block.getTicker(level, tileEntity.getType());
                    ticker.tick(level, blockPos, block, tileEntity);

                }
            }
        });
    }

    /**
     *
     * @param player to whom the effect will be applied.
     */

    public static void AddAiSteeps(Player player, boolean enhanced) {

        if (enhanced) {
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 20, 3, true, false));
            player.aiStep();
            player.aiStep();
            player.aiStep();
        } else {
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 20, 1, true, false));
            player.aiStep();
        }
    }

}

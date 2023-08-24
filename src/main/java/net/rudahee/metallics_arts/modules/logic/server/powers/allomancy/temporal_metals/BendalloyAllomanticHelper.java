package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.temporal_metals;


import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.AllomaticTick;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

/**
 * Helper class that contains the methods to use the allomantic Bendalloy
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see AllomaticTick
 */
public class BendalloyAllomanticHelper {

    /**
     * This method applies more ticks on the mobs (making them move faster or attack more often).
     *
     * @param player to whom the effect will be applied.
     * @param level in which the player is located (world).
     * @param enhanced if player is burning Duralumin or the player was hit with Nicrosil.
     * @param lerasium if the player is burning Lerasium.
     */
    public static void BendalloyMobEffects(Player player, Level level, boolean enhanced, boolean lerasium) {

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
                    block.randomTick((ServerLevel) level, blockPos, level.random);
                }

                else if (tileEntity instanceof TickingBlockEntity) {
                    BlockEntityTicker ticker = block.getTicker(level, tileEntity.getType());
                    ticker.tick(level, blockPos, block, tileEntity);

                }
            }
        });
    }

    /**
     * Adds a digging speed effect to the player and performs AI steps based on the enhanced flag.
     * If enhanced is true, performs 3 AI steps; otherwise, performs 1 AI step.
     *
     * @param player the Player to whom the effect and AI steps are applied
     * @param enhanced the boolean flag representing whether the AI steps should be enhanced
     */
    public static void AddAiSteeps(Player player, boolean enhanced) {

        if (enhanced) {
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 3, true, false));
            player.aiStep();
            player.aiStep();
            player.aiStep();
        } else {
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 1, false, false));
            player.aiStep();
        }
    }

}

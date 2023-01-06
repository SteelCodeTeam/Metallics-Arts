package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy;


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
import net.minecraft.world.phys.AABB;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;


public class BendalloyAllomanticHelper {

    public static void BendalloyMobEffects(Player player, Level world, AABB axisAlignedBB, int radius, boolean enhanced) {

        BlockPos negative = new BlockPos(player.position()).offset(- radius, - radius, - radius);
        BlockPos positive = new BlockPos(player.position()).offset(radius, radius , radius);


        if (world instanceof ServerLevel) {
            world.getEntitiesOfClass(LivingEntity.class, axisAlignedBB).forEach(entity -> {
                if (!(entity instanceof Player)) {
                    entity.aiStep();
                    if (enhanced) {
                        entity.aiStep();
                        entity.aiStep();
                    }
                }
            });
        }

        BlockPos.betweenClosedStream(negative, positive).forEach(blockPos -> {

            BlockState block = world.getBlockState(blockPos);
            BlockEntity tileEntity = world.getBlockEntity(blockPos);

            if (block.is(ModBlocksRegister.BUDDING_ATIUM.get()) || block.is(ModBlocksRegister.BUDDING_LERASIUM.get()) || block.is(ModBlocksRegister.BUDDING_ETTMETAL.get()) ) {
                return;
            }

            if (Math.random() > 0.5) {
                if (tileEntity == null && block.isRandomlyTicking()) {
                    block.randomTick((ServerLevel) world, blockPos, ((ServerLevel) world).random);
                }

                else if (tileEntity instanceof TickingBlockEntity) {
                    BlockEntityTicker ticker = block.getTicker(world, tileEntity.getType());
                    ticker.tick(world, blockPos, block, tileEntity);

                }
            }
        });
    }



    public static void AddAiSteeps(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 2, 0, true, false));
        player.aiStep();
        player.aiStep();
    }

    public static void AddAiSteepsEnhanced(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 2, 0, true, false));
        player.aiStep();
        player.aiStep();
    }

}

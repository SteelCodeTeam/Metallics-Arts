package net.rudahee.metallics_arts.modules.powers.helpers;


import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.rudahee.metallics_arts.setup.registries.ModBlock;


public class BendalloyAndCadmiunHelpers {

    public static void BendalloyMobEffects(Player player, Level world, AABB axisAlignedBB, BlockPos negative, BlockPos positive) {
        world.getEntitiesOfClass(LivingEntity.class, axisAlignedBB).forEach(entity -> {
            entity.aiStep();
        });

        BlockPos.betweenClosedStream(negative, positive).forEach(blockPos -> {

            BlockState block = world.getBlockState(blockPos);
            BlockEntity tileEntity = world.getBlockEntity(blockPos);

            for (int i = 0; i < 12 * 4 / (tileEntity == null ? 10 : 1); i++) {
                //if (!block.is(ModBlock.BUDDING_ATIUM.get()) && !block.is(ModBlock.BUDDING_LERASIUM.get()) && !block.is(ModBlock.BUDDING_ETTMETAL.get()) ){
                    if (tileEntity instanceof TickingBlockEntity) {
                        if (Math.random() > 0.70) {
                            ((TickingBlockEntity) tileEntity).tick();
                        }
                    } else if (block.isRandomlyTicking()) {
                        if (Math.random() > 0.70) {
                            block.randomTick((ServerLevel) world, blockPos, world.random);
                        }
                    }
                //}
            }
        });
    }

    public static void BendalloyMobEffectsEnhanced(Player player, Level world, AABB axisAlignedBB, BlockPos negative, BlockPos positive) {
        world.getEntitiesOfClass(LivingEntity.class, axisAlignedBB).forEach(entity -> {
            entity.aiStep();
            entity.aiStep();
            entity.aiStep();
            entity.aiStep();
        });

        BlockPos.betweenClosedStream(negative, positive).forEach(blockPos -> {
            BlockState block = world.getBlockState(blockPos);
            BlockEntity tileEntity = world.getBlockEntity(blockPos);

            for (int i = 0; i < 12 * 4 / (tileEntity == null ? 10 : 1); i++) {
                if (tileEntity instanceof TickingBlockEntity) {
                    if (Math.random() > 0.20) {
                        ((TickingBlockEntity) tileEntity).tick();
                        ((TickingBlockEntity) tileEntity).tick();
                        ((TickingBlockEntity) tileEntity).tick();
                    }
                } else if (block.isRandomlyTicking()) {
                    if (Math.random() > 0.20) {
                        block.randomTick((ServerLevel) world, blockPos, world.random);
                        block.randomTick((ServerLevel) world, blockPos, world.random);
                        block.randomTick((ServerLevel) world, blockPos, world.random);
                    }
                }
            }
        });
    }

    public static void CadmiumEffectSelfPlayer(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 10, 4, true, false));
    }

    public static void CadmiumEffectSelfPlayerEnhanced(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 100, true, false));
    }

    public static void CadmiumMobEffectsOtherPlayers(LivingEntity player, int duration, int amplifier) {

        if (player instanceof Player) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, amplifier, true, false));
        }
        player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, duration, amplifier, true, false));
    }

    public static void CadmiumMobEffectsOtherPlayersEnhanced(LivingEntity player, int duration, int amplifier) {

        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, amplifier, true, false));
        player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, duration, amplifier, true, false));
    }

    public static void AddAiSteeps(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 3, 2, true, false));
        player.aiStep();
        player.aiStep();
    }

    public static void AddAiSteepsEnhanced(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 10, 10, true, false));
        player.aiStep();
        player.aiStep();
        player.aiStep();
        player.aiStep();
        player.aiStep();
    }


    public static void addFoodLevel(Player player, int qty){
        if (player.getFoodData().getFoodLevel()<20){
            player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel()+qty);
        }
    }

    public static void removeFoodLevel(Player player, int qty){

        if (!player.isCreative()){
            if (player.getFoodData().getFoodLevel()>0){
                player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel()-qty);
            }
        }
    }

    public static void drowningEffect(Player player,int actualtick) {
        if (!player.isCreative()){
            if (!player.isEyeInFluid(FluidTags.WATER)) {
                if (player.getAirSupply()<=-10) {
                    player.setAirSupply(-10);
                    if (actualtick % 10 == 0) {
                        player.hurt(DamageSource.DROWN,1);
                    }
                } else {
                    player.setAirSupply(player.getAirSupply()-6);
                }
            } else {
                if (player.getAirSupply() <= 0) {
                    player.setAirSupply(0);
                    player.hurt(DamageSource.DROWN,2);
                }else {
                    player.setAirSupply(player.getAirSupply()-1);
                }
            }
        }
    }

    public static void throwBreathEffect(Player player, int effectLevel) {

        /*if (!player.isEyeInFluid(FluidTags.WATER) && !player.isEyeInFluid(FluidTags.LAVA)) {
            player.hurt(DamageSource.DROWN, 2);
        }*/

        if (player.isEyeInFluid(FluidTags.WATER)) {
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 40, effectLevel, true, false));
        }
    }
}

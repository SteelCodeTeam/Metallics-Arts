package net.rudahee.metallics_arts.modules.logic.server.powers;


import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;


public class BendalloyAndCadmiunHelpers {

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

                /*for (int i = 0; i < 12 * 4 / (tileEntity == null ? 10 : 1); i++) {
                    if (tileEntity instanceof TickingBlockEntity) {
                        if (Math.random() > 0.70) {
                            ((TickingBlockEntity) tileEntity).tick();
                        }
                    } else if (block.isRandomlyTicking()) {
                        if (Math.random() > 0.70) {
                            block.randomTick((ServerLevel) world, blockPos, world.random);
                        }
                    }
                }
            }*/
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

    public static void CadmiumMobEffectsOtherPlayers(Player player, IInvestedPlayerData playerCapability) {
        if (playerCapability.isBurning(MetalTagEnum.LERASIUM)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 2, true, false));
        } else {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 10, 1, true, false));
        }
    }

    public static void CadmiumMobEffectsOtherPlayersEnhanced(Player player, IInvestedPlayerData playerCapability) {
        if (playerCapability.isBurning(MetalTagEnum.LERASIUM)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 100, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 100, true, false));
        } else {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 2, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 2, true, false));
        }
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
        System.out.println("nivel de oxigeno: "+player.getAirSupply()+"\n");
        if (!player.isCreative()){
            if (!player.isEyeInFluid(FluidTags.WATER)) {
                player.setAirSupply(player.getAirSupply()-5);
                if (player.getAirSupply()<=-29) {
                    player.hurt(DamageSource.DROWN,1);
                    player.setAirSupply(-9);
                }
            } else {
                player.setAirSupply(player.getAirSupply()-1);
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

package net.rudahee.metallics_arts.modules.powers.helpers;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class BendalloyAndCadmiunHelpers {

    public static void BendalloyEffects(PlayerEntity player, World world, AxisAlignedBB axisAlignedBB, BlockPos negative, BlockPos positive) {
        world.getEntitiesOfClass(LivingEntity.class, axisAlignedBB).forEach(entity -> {
            entity.aiStep();
        });

        BlockPos.betweenClosedStream(negative, positive).forEach(blockPos -> {
            BlockState block = world.getBlockState(blockPos);
            TileEntity tileEntity = world.getBlockEntity(blockPos);

            for (int i = 0; i < 12 * 4 / (tileEntity == null ? 10 : 1); i++) {
                if (tileEntity instanceof ITickableTileEntity) {
                    if (Math.random() > 0.70) {
                        ((ITickableTileEntity) tileEntity).tick();
                    }
                } else if (block.isRandomlyTicking()) {
                    if (Math.random() > 0.70) {
                        block.randomTick((ServerWorld) world, blockPos, world.random);

                    }
                }
            }
        });
    }

    public static void BendalloyEffectsEnhanced(PlayerEntity player, World world, AxisAlignedBB axisAlignedBB, BlockPos negative, BlockPos positive) {
        world.getEntitiesOfClass(LivingEntity.class, axisAlignedBB).forEach(entity -> {
            entity.aiStep();
            entity.aiStep();
            entity.aiStep();
            entity.aiStep();
        });

        BlockPos.betweenClosedStream(negative, positive).forEach(blockPos -> {
            BlockState block = world.getBlockState(blockPos);
            TileEntity tileEntity = world.getBlockEntity(blockPos);

            for (int i = 0; i < 12 * 4 / (tileEntity == null ? 10 : 1); i++) {
                if (tileEntity instanceof ITickableTileEntity) {
                    if (Math.random() > 0.20) {
                        ((ITickableTileEntity) tileEntity).tick();
                        ((ITickableTileEntity) tileEntity).tick();
                        ((ITickableTileEntity) tileEntity).tick();
                    }
                } else if (block.isRandomlyTicking()) {
                    if (Math.random() > 0.20) {
                        block.randomTick((ServerWorld) world, blockPos, world.random);
                        block.randomTick((ServerWorld) world, blockPos, world.random);
                        block.randomTick((ServerWorld) world, blockPos, world.random);
                    }
                }
            }
        });
    }



    public static void CadmiumEffectSelfPlayer(PlayerEntity player) {
        player.addEffect(new EffectInstance(Effects.SLOW_FALLING, 10, 4, true, false));
    }

    public static void CadmiumEffectSelfPlayerEnhanced(PlayerEntity player) {
        player.addEffect(new EffectInstance(Effects.SLOW_FALLING, 20, 100, true, false));
    }

    public static void CadmiumEffectsOtherPlayers(LivingEntity player, int duration, int amplifier) {

        if (player instanceof PlayerEntity) {
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, duration, amplifier, true, false));
        }
        player.addEffect(new EffectInstance(Effects.SLOW_FALLING, duration, amplifier, true, false));
    }

    public static void CadmiumEffectsOtherPlayersEnhanced(LivingEntity player, int duration, int amplifier) {

        player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, duration, amplifier, true, false));
        player.addEffect(new EffectInstance(Effects.SLOW_FALLING, duration, amplifier, true, false));
    }

    public static void AddAiSteeps(PlayerEntity player) {
        player.addEffect(new EffectInstance(Effects.DIG_SPEED, 3, 2, true, false));
        player.aiStep();
        player.aiStep();
    }

    public static void AddAiSteepsEnhanced(PlayerEntity player) {
        player.addEffect(new EffectInstance(Effects.DIG_SPEED, 10, 10, true, false));
        player.aiStep();
        player.aiStep();
        player.aiStep();
        player.aiStep();
        player.aiStep();
    }


    public static void drowningEffect(PlayerEntity player, int speed){

        if (!player.isEyeInFluid(FluidTags.WATER)){
            //no lo hace fuera del agua, buscar como agregar burbujas

            player.setAirSupply(player.getAirSupply()-speed);
        }else if(player.isEyeInFluid(FluidTags.WATER)){
            player.setAirSupply(player.getAirSupply()-(speed*2));
        }

        /*if(player.getAirSupply() < player.getMaxAirSupply() && player.isEyeInFluid(FluidTags.WATER)){
            if(Math.random()>0.66f){
                player.setAirSupply();


                player.setAir(player.getAirSupply()+ MetalMind.CD);
            }
        }*/

    }
}

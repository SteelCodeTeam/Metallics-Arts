package net.rudahee.metallics_arts.modules.powers;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.modules.powers.helpers.PewterHelper;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber
public class PowersEventHandler {

    @SubscribeEvent
    public static void onJoinWorld(final PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.getPlayer().level.isClientSide) {
            if (event.getPlayer() instanceof ServerPlayerEntity) {
                ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();

                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {
                    //Handle random misting case
                    if (data.getAllomanticPowerCount() + data.getFeruchemicPowerCount() == 0) {
                        List<MetalsNBTData> metals = Arrays.asList(MetalsNBTData.values());
                        MetalsNBTData initialMetal = metals.get(((int) Math.random())*metals.size());

                        if (Math.random() > 0.49) {
                            data.addAllomanticPower(initialMetal);
                        } else {
                            data.addFeruchemicPower(initialMetal);
                        }
                        data.setInvested(true);
                    }
                });

                //Sync cap to client
                ModNetwork.sync(event.getPlayer());
            }
        }
    }

    @SubscribeEvent
    public static void onRespawn(final PlayerEvent.PlayerRespawnEvent event) {
        if (!event.getPlayer().getCommandSenderWorld().isClientSide()) {
            ModNetwork.sync(event.getPlayer());
        }
    }

    @SubscribeEvent
    public static void onChangeDimension(final PlayerEvent.PlayerChangedDimensionEvent event) {
        if (!event.getPlayer().getCommandSenderWorld().isClientSide()) {
            ModNetwork.sync(event.getPlayer());
        }
    }

    @SubscribeEvent
    public static void onStartTracking(final net.minecraftforge.event.entity.player.PlayerEvent.StartTracking event) {
        if (!event.getTarget().level.isClientSide) {
            if (event.getTarget() instanceof ServerPlayerEntity) {
                ServerPlayerEntity player = (ServerPlayerEntity) event.getTarget();
                ModNetwork.sync(player);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(final PlayerEvent.Clone event) {
        if (!event.getPlayer().level.isClientSide()) {

            PlayerEntity player = event.getPlayer();
            player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {

                event.getOriginal().getCapability(InvestedCapability.PLAYER_CAP).ifPresent(oldData -> {
                    if (oldData.isInvested()) { // make sure the new player has the same power status
                        for (MetalsNBTData mt : MetalsNBTData.values()) {
                            if (oldData.hasAllomanticPower(mt)) {
                                data.addAllomanticPower(mt);
                            }

                            if (oldData.hasFeruchemicPower(mt)) {
                                data.addFeruchemicPower(mt);
                            }
                        }
                    }
                });
            });

            ModNetwork.sync(player);
        }
    }

    @SubscribeEvent
    public static void onDamageEvent(final LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof ServerPlayerEntity) {
            ServerPlayerEntity playerEntity = (ServerPlayerEntity) event.getSource().getEntity();

            playerEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(
                playerCapability -> {

                    /*

                     DAMAGE WITH - PEWTER -

                     */
                    if (playerCapability.isBurning(MetalsNBTData.PEWTER)) {
                        float amountDamage = event.getAmount();
                        amountDamage =  amountDamage + PewterHelper.getExtraDamageWithItemInHand(playerEntity.getMainHandItem().getItem());

                        /* PEWTER + DURALUMIN */

                        if (playerCapability.isBurning(MetalsNBTData.DURALUMIN)) {
                            amountDamage = PewterHelper.getDamageWithMultiplier(amountDamage);
                        } else {
                            amountDamage = PewterHelper.getDamageWithIncrement(amountDamage);
                        }
                        event.setAmount(amountDamage);
                    }
                });

        }
    }

    public static int ticks = 0;

    public static int x = 8;
    public static int y = 8;
    public static int z = 8;

    @SubscribeEvent
    public static void onWorldTickEvent(final TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {

            World world = event.world;
            List<? extends PlayerEntity> playerList = world.players();
            ticks++;
            for (int playerIndex = playerList.size() - 1; playerIndex >= 0; playerIndex--) {

                PlayerEntity player = playerList.get(playerIndex);

                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(
                    playerCapability -> {
                        if (playerCapability.isInvested()) {
                            if (player instanceof ServerPlayerEntity) {
                                playerCapability.tickAllomancyBurningMetals((ServerPlayerEntity) player);
                            }

                            /************************
                                BENDALLOY POWERS
                             ************************/
                            if (playerCapability.hasAllomanticPower(MetalsNBTData.BENDALLOY) && playerCapability.isBurning(MetalsNBTData.BENDALLOY)) {
                                if (!playerCapability.isBurning(MetalsNBTData.CADMIUM)) {

                                    player.addEffect(new EffectInstance(Effects.DIG_SPEED, 3, 2, true, false));
                                    player.aiStep();
                                    player.aiStep();

                                    if (event.world instanceof ServerWorld) {
                                        BlockPos negative = new BlockPos(player.position()).offset(-x, -y, -z);
                                        BlockPos positive = new BlockPos(player.position()).offset(x, y, z);

                                        // Ticks extra in random blocks, tile entities and entities.
                                        event.world.getEntitiesOfClass(LivingEntity.class, new AxisAlignedBB(negative, positive)).forEach(entity -> {
                                            entity.aiStep();
                                        });

                                        BlockPos.betweenClosedStream(negative, positive).forEach(blockPos -> {
                                            BlockState block = event.world.getBlockState(blockPos);
                                            TileEntity tileEntity = event.world.getBlockEntity(blockPos);

                                            for (int i = 0; i < x * 4 / (tileEntity == null ? 10 : 1); i++) {
                                                if (tileEntity instanceof ITickableTileEntity) {
                                                    if (Math.random() > 0.50) {
                                                        ((ITickableTileEntity) tileEntity).tick();
                                                    }
                                                } else if (block.isRandomlyTicking()) {
                                                    if (Math.random() > 0.50) {
                                                        block.randomTick((ServerWorld) event.world, blockPos, event.world.random);

                                                    }
                                                }
                                            }
                                        });

                                    }

                                }
                            }
                            /************************
                             CADMIUM POWERS
                             ************************/
                            if (playerCapability.hasAllomanticPower(MetalsNBTData.CADMIUM) && playerCapability.isBurning(MetalsNBTData.CADMIUM)) {
                                if (!playerCapability.isBurning(MetalsNBTData.BENDALLOY)) {

                                    BlockPos negative = new BlockPos(player.position()).offset(-x, -y, -z);
                                    BlockPos positive = new BlockPos(player.position()).offset(x, y, z);
                                    int slowness_amplifier = 2;
                                    player.addEffect(new EffectInstance(Effects.SLOW_FALLING, 10, 4, true, false));

                                    if (event.world instanceof ServerWorld) {

                                        event.world.getEntitiesOfClass(LivingEntity.class, new AxisAlignedBB(negative, positive)).forEach(entity -> {
                                            if (entity != player) {
                                                entity.addEffect(new EffectInstance(Effects.SLOW_FALLING, 10, 2, true, false));
                                                entity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 10, slowness_amplifier, true, false));
                                            }
                                        });
                                    }

                                }
                            }
                        }
                        /************************
                         PEWTER POWERS
                         ************************/
                        if (playerCapability.hasAllomanticPower(MetalsNBTData.PEWTER) && playerCapability.isBurning(MetalsNBTData.PEWTER)) {
                            player.addEffect(new EffectInstance(Effects.JUMP, 3, 2, true, false));
                            player.addEffect(new EffectInstance(Effects.DIG_SPEED, 3, 1, true, false));
                            player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 3, 1, true, false));
                        }

                        /************************
                         COPPER POWERS
                         ************************/

                        if (playerCapability.hasAllomanticPower(MetalsNBTData.COPPER) && playerCapability.isBurning(MetalsNBTData.COPPER)) {

                            if (event.world instanceof ServerWorld) {

                                BlockPos negative = new BlockPos(player.position()).offset(-x, -y, -z);
                                BlockPos positive = new BlockPos(player.position()).offset(x, y, z);

                                event.world.getEntitiesOfClass(MobEntity.class, new AxisAlignedBB(negative, positive)).forEach(entity -> {
                                    entity.goalSelector.removeGoal(entity.goalSelector.getRunningGoals().findFirst().orElse(null));
                                    entity.getLookControl().setLookAt(player.position().x, player.position().y, player.position().z);
                                    entity.getMoveControl().setWantedPosition(player.position().x, player.position().y, player.position().z, 1.3f);
                                });
                            }
                        }
                        /************************
                        COPPER POWERS
                        ************************/
                        if (playerCapability.hasAllomanticPower(MetalsNBTData.BRONZE) && playerCapability.isBurning(MetalsNBTData.BRONZE)) {

                        }
                    ModNetwork.sync(player);


                });
            }
        }
    }
}

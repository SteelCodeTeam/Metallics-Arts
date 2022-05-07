package net.rudahee.metallics_arts.modules.powers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.modules.powers.helpers.*;
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

                    /*******************************
                     *   DAMAGE WITH - PEWTER -
                     *******************************/
                    if (playerCapability.isBurning(MetalsNBTData.PEWTER)) {
                        float amountDamage = event.getAmount();
                        amountDamage =  amountDamage + PewterAndTinHelpers.getExtraDamageWithItemInHand(playerEntity.getMainHandItem().getItem());

                        /* PEWTER + DURALUMIN */

                        if (playerCapability.isBurning(MetalsNBTData.DURALUMIN)) {
                            amountDamage = PewterAndTinHelpers.getDamageWithMultiplier(amountDamage);
                        } else {
                            amountDamage = PewterAndTinHelpers.getDamageWithIncrement(amountDamage);
                        }
                        event.setAmount(amountDamage);
                    }

                    /*******************************
                     *   DAMAGE WITH - CHROMIUM -
                     *******************************/
                    if (playerCapability.isBurning(MetalsNBTData.CHROMIUM)) {
                        if (event.getEntityLiving() instanceof PlayerEntity) {
                            ChromiumAndNicrosilHelpers.drainMetalChromium((PlayerEntity) event.getEntityLiving());
                        }
                    }

                    /*******************************
                     *   DAMAGE WITH - ZINC -
                     *******************************/
                    if (playerCapability.isBurning(MetalsNBTData.ZINC)) {
                        if (event.getEntityLiving() instanceof PlayerEntity) {
                            ZincAndBrassHelpers.drawSaturatedScreen((PlayerEntity) event.getEntityLiving()); //TODO
                        }
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
                             * BENDALLOY POWERS
                             ************************/
                            if (playerCapability.hasAllomanticPower(MetalsNBTData.BENDALLOY) && playerCapability.isBurning(MetalsNBTData.BENDALLOY)) {
                                if (!playerCapability.isBurning(MetalsNBTData.CADMIUM)) {

                                    BendalloyAndCadmiunHelpers.AddAiSteeps(player);

                                    if (event.world instanceof ServerWorld) {
                                        BlockPos negative = new BlockPos(player.position()).offset(-x - 2, -y - 2, -z - 2);
                                        BlockPos positive = new BlockPos(player.position()).offset(x + 2, y + 2 , z + 2);

                                        // Ticks extra in random blocks, tile entities and entities.
                                        BendalloyAndCadmiunHelpers.BendalloyEffects(player, event.world,new AxisAlignedBB(negative, positive), negative, positive);
                                    }
                                }
                            }


                            /************************
                             * CADMIUM POWERS
                             ************************/
                            if (playerCapability.hasAllomanticPower(MetalsNBTData.CADMIUM) && playerCapability.isBurning(MetalsNBTData.CADMIUM)) {
                                if (!playerCapability.isBurning(MetalsNBTData.BENDALLOY)) {

                                    BlockPos negative = new BlockPos(player.position()).offset(-x, -y, -z);
                                    BlockPos positive = new BlockPos(player.position()).offset(x, y, z);

                                    // Do myself my own powers
                                    BendalloyAndCadmiunHelpers.CadmiumEffectSelfPlayer(player);

                                    if (event.world instanceof ServerWorld) {

                                        event.world.getEntitiesOfClass(LivingEntity.class, new AxisAlignedBB(negative, positive)).forEach(entity -> {
                                            if (entity != player) {
                                                //Do others in de cloud my powers.
                                                BendalloyAndCadmiunHelpers.CadmiumEffectsOtherPlayers(entity, 10, 2);
                                            }
                                        });
                                    }

                                }
                            }
                        }
                        /************************
                         * PEWTER POWERS
                         ************************/
                        if (playerCapability.hasAllomanticPower(MetalsNBTData.PEWTER) && playerCapability.isBurning(MetalsNBTData.PEWTER)) {
                            // Add strength, jump and speed.
                            PewterAndTinHelpers.addPewterEffects(player);
                        }


                        /************************
                         * TIN POWERS
                         ************************/
                        if (playerCapability.hasAllomanticPower(MetalsNBTData.TIN) && playerCapability.isBurning(MetalsNBTData.TIN)) {
                            PewterAndTinHelpers.addTinEffects(player);
                        }

                        /************************
                         * BRONZE POWERS
                         ************************/
                        if (playerCapability.hasAllomanticPower(MetalsNBTData.BRONZE) && playerCapability.isBurning(MetalsNBTData.BRONZE)) {

                            if (event.world instanceof ServerWorld) {

                                BlockPos negative = new BlockPos(player.position()).offset(-x - 4, -y - 4, -z - 4);
                                BlockPos positive = new BlockPos(player.position()).offset(x + 4, y + 4, z + 4);

                                CopperAndBronzeHelpers.BronzeAiEntityManipulation(new AxisAlignedBB(negative, positive), player, event.world);

                            }
                        }

                        /************************
                         * COPPER POWERS
                         ************************/
                        if (playerCapability.hasAllomanticPower(MetalsNBTData.COPPER) && playerCapability.isBurning(MetalsNBTData.COPPER)) {
                            if (event.world instanceof ServerWorld) {
                                BlockPos negative = new BlockPos(player.position()).offset(-x - 4, -y - 4, -z - 4);
                                BlockPos positive = new BlockPos(player.position()).offset(x + 4, y + 4, z + 4);

                                CopperAndBronzeHelpers.CopperAiEntityManipulation(new AxisAlignedBB(negative, positive), player, event.world);
                            }
                        }

                        /************************
                         ALUMINUM POWER
                         ************************/
                        if (playerCapability.hasAllomanticPower(MetalsNBTData.ALUMINUM) && playerCapability.isBurning(MetalsNBTData.ALUMINUM)) {
                             for (MetalsNBTData metalsNBTData: playerCapability.getAllomanticPowers()){
                                 playerCapability.drainMetals(metalsNBTData);
                             }
                        }




                    // SYNC NETWORK
                    ModNetwork.sync(player);
                });
            }
        }
    }
}

package net.rudahee.metallics_arts.modules.powers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
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
                            if (ticks < 1000){
                                playerCapability.setBurning(MetalsNBTData.BRASS, true);
                            } else {

                                playerCapability.setBurning(MetalsNBTData.BRASS, false);
                            }


                        ModNetwork.sync(player);
                    });
            }
            if (ticks > 6000) {
                ticks = 0;
            }
        }
    }
}

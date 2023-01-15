package net.rudahee.metallics_arts.modules.logic.server;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.event_utils.*;

import java.util.List;

@Mod.EventBusSubscriber
public class ServerEventHandler {
    @SubscribeEvent
    public static void onLivingEntityDrop(final LivingDropsEvent event) {
        OnLivingEntityDrop.livingEntityDrop(event);
    }

    @SubscribeEvent
    public static void onJoinWorld(final PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.getEntity().level.isClientSide) {
            if (event.getEntity() instanceof ServerPlayer) {
                OnJoinWorld.joinWorld(event);
            }
        }
    }

    @SubscribeEvent
    public static void onSetSpawn(final PlayerSetSpawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer) {
            OnSetSpawn.setSpawn(event);
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(final LivingDeathEvent event) {
        if (event.getEntity() instanceof ServerPlayer) {
            OnLivingDeath.livingDeath(event);
        }
    }

    @SubscribeEvent
    public static void onRespawn(final PlayerEvent.PlayerRespawnEvent event) {
        if (!event.getEntity().getCommandSenderWorld().isClientSide()) {
            ModNetwork.sync(event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onChangeDimension(final PlayerEvent.PlayerChangedDimensionEvent event) {
        if (!event.getEntity().getCommandSenderWorld().isClientSide()) {
            ModNetwork.sync(event.getEntity());
            if (event.getEntity() instanceof ServerPlayer) {
                ServerPlayer entity = (ServerPlayer) event.getEntity();
            }
            ModNetwork.sync(event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(final PlayerEvent.Clone event) {
        if (!event.getEntity().level.isClientSide()) {
            OnPlayerClone.playerClone(event);
        }
    }

    @SubscribeEvent
    public static void onDamageEvent(final LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof ServerPlayer) {
            OnDamagePowers.onDamageFeruchemical(event, (ServerPlayer) event.getSource().getEntity(), (ServerPlayer) event.getEntity());
            OnDamagePowers.onDamageAllomantic(event, (ServerPlayer) event.getSource().getEntity(), (ServerPlayer) event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onWorldTickEvent(final TickEvent.LevelTickEvent event) throws Exception {

        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        List<? extends Player> playerList = event.level.players();

        for (Player player : playerList) {
            if (player instanceof ServerPlayer serverPlayer) {
                IInvestedPlayerData capabilities = CapabilityUtils.getCapability(serverPlayer);

                if (capabilities.isInvested()) {
                    OnWorldTick.onWorldTick(capabilities, serverPlayer, (ServerLevel) event.level);
                }
            }

        }
    }

/*
            // ELECTRUM POWER (ENHANCED)

            if (playerCapability.isBurning(MetalTagEnum.ELECTRUM) &&  playerCapability.getEnhanced()) {
                BlockPos block;
                String dimension;
                if (playerCapability.getSpawnPos() != null && playerCapability.getSpawnDimension() != null) {
                    block = new BlockPos(playerCapability.getSpawnPos()[0], playerCapability.getSpawnPos()[1], playerCapability.getSpawnPos()[2]);
                    dimension = playerCapability.getSpawnDimension();
                } else {
                    block = new BlockPos(world.getLevelData().getXSpawn(),world.getLevelData().getYSpawn(), world.getLevelData().getZSpawn());
                    dimension = Level.OVERWORLD.location().toString();
                }
                if (playerCapability.isBurning(MetalTagEnum.ELECTRUM) && playerCapability.isBurning(MetalTagEnum.LERASIUM) && playerCapability.isBurning(MetalTagEnum.DURALUMIN)){
                    radius = 11;
                    GoldAndElectrumHelpers.multiTeleport(player, CapabilityUtils.getBubble(player,radius), event.level, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension),block);
                    playerCapability.drainMetals(MetalTagEnum.LERASIUM);
                    playerCapability.drainMetals(MetalTagEnum.DURALUMIN);
                } else if(playerCapability.isBurning(MetalTagEnum.ELECTRUM) && playerCapability.isBurning(MetalTagEnum.DURALUMIN)){
                    GoldAndElectrumHelpers.teleport(player, event.level, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension), block);
                    playerCapability.drainMetals(MetalTagEnum.DURALUMIN);
                }else{
                    //GoldAndElectrumHelpers.teleport(player, event.level, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension), block);
                }
                playerCapability.drainMetals(MetalTagEnum.ELECTRUM);
            }
            // GOLD POWER (ENHANCED)

            if (playerCapability.isBurning(MetalTagEnum.GOLD) && playerCapability.getEnhanced()) {
                BlockPos block;
                String dimension;
                if (playerCapability.getDeathPos() != null && playerCapability.getDeathDimension() != null) {
                    block = new BlockPos(playerCapability.getDeathPos()[0], playerCapability.getDeathPos()[1], playerCapability.getDeathPos()[2]);
                    dimension = playerCapability.getDeathDimension();
                } else {
                    block = new BlockPos(world.getLevelData().getXSpawn(),world.getLevelData().getYSpawn(), world.getLevelData().getZSpawn());
                    dimension = Level.OVERWORLD.location().toString();
                }
                if (playerCapability.isBurning(MetalTagEnum.GOLD) && playerCapability.isBurning(MetalTagEnum.LERASIUM) && playerCapability.isBurning(MetalTagEnum.DURALUMIN)){
                    radius = 11;
                    GoldAndElectrumHelpers.multiTeleport(player, CapabilityUtils.getBubble(player,radius), event.level, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension),block);
                    playerCapability.drainMetals(MetalTagEnum.LERASIUM);
                    playerCapability.drainMetals(MetalTagEnum.DURALUMIN);
                }else if(playerCapability.isBurning(MetalTagEnum.GOLD) && playerCapability.isBurning(MetalTagEnum.DURALUMIN)){
                    GoldAndElectrumHelpers.teleport(player, event.level, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension), block);
                    playerCapability.drainMetals(MetalTagEnum.DURALUMIN);
                }else{
                    //.teleport(player, event.level, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension), block);
                }
                playerCapability.drainMetals(MetalTagEnum.GOLD);
            }
            // MALATIUM POWER (ENHANCED)

            if (playerCapability.isBurning(MetalTagEnum.MALATIUM) && playerCapability.getEnhanced()) {
                BlockPos block;
                String dimension;
                if (GoldAndElectrumHelpers.getBlock() != null && GoldAndElectrumHelpers.getDimension() != null) {
                    block = GoldAndElectrumHelpers.getBlock();
                    dimension = GoldAndElectrumHelpers.getDimension();
                    if (playerCapability.isBurning(MetalTagEnum.LERASIUM)) {
                        radius = 11;
                        GoldAndElectrumHelpers.multiTeleport(player,CapabilityUtils.getBubble(player,radius), event.level, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension),block);
                        playerCapability.drainMetals(MetalTagEnum.LERASIUM);
                    } else {
                        GoldAndElectrumHelpers.teleport(player, event.level, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension), block);
                    }
                    playerCapability.drainMetals(MetalTagEnum.MALATIUM);
                    GoldAndElectrumHelpers.setBlock(null);
                    GoldAndElectrumHelpers.setDimension(null);
                }
            }
            ModNetwork.sync(playerCapability, player);
        }
    }*/
}
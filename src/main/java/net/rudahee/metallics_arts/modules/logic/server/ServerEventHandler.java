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
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.logic.server.server_events.*;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

import java.util.List;

@Mod.EventBusSubscriber
public class ServerEventHandler {
    @SubscribeEvent
    public static void onLivingEntityDrop(final LivingDropsEvent event) {
        OnLivingEntityDropEvent.livingEntityDrop(event);
    }

    @SubscribeEvent
    public static void onJoinWorld(final PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.getEntity().level.isClientSide) {
            if (event.getEntity() instanceof ServerPlayer) {
                OnJoinWorldEvent.joinWorld(event);
            }
        }
    }

    @SubscribeEvent
    public static void onSetSpawn(final PlayerSetSpawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer) {
            OnSetSpawnEvent.setSpawn(event);
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(final LivingDeathEvent event) {
        if (event.getEntity() instanceof ServerPlayer) {
            OnLivingDeathEvent.livingDeath(event);
        }
    }

    @SubscribeEvent
    public static void onRespawn(final PlayerEvent.PlayerRespawnEvent event) {
        if (!event.getEntity().getCommandSenderWorld().isClientSide()) {
            ModNetwork.syncInvestedDataPacket(event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onChangeDimension(final PlayerEvent.PlayerChangedDimensionEvent event) {
        if (!event.getEntity().getCommandSenderWorld().isClientSide()) {
            ModNetwork.syncInvestedDataPacket(event.getEntity());
            if (event.getEntity() instanceof ServerPlayer) {
                ServerPlayer entity = (ServerPlayer) event.getEntity();
            }
            ModNetwork.syncInvestedDataPacket(event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(final PlayerEvent.Clone event) {
        if (!event.getEntity().level.isClientSide()) {
            OnPlayerCloneEvent.playerClone(event);
        }
    }

    @SubscribeEvent
    public static void onDamageEvent(final LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof ServerPlayer) {
            OnDamageEvent.onDamageFeruchemical(event, (ServerPlayer) event.getSource().getEntity(), (ServerPlayer) event.getEntity());
            OnDamageEvent.onDamageAllomantic(event, (ServerPlayer) event.getSource().getEntity(), (ServerPlayer) event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onWorldTickEvent(final TickEvent.LevelTickEvent event) {

        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        List<? extends Player> playerList = event.level.players();

        for (Player player : playerList) {

            ServerPlayer serverPlayer = (ServerPlayer) player;
            try {
                IInvestedPlayerData capabilities = CapabilityUtils.getCapability(serverPlayer);

                if (capabilities != null && capabilities.isInvested()) {
                    OnWorldTickEvent.onWorldTick(capabilities, serverPlayer, (ServerLevel) event.level);
                }
            } catch (PlayerException ex) {
                ex.printResumeLog();
            }
        }

    }
}
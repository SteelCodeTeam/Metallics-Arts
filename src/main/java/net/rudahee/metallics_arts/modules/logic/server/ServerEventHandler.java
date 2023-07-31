package net.rudahee.metallics_arts.modules.logic.server;

import com.sun.java.accessibility.util.SwingEventMonitor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.data.enums.implementations.GunsAccess;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.custom_items.weapons.guns.BasicGun;
import net.rudahee.metallics_arts.modules.custom_items.weapons.guns.GunUtils;
import net.rudahee.metallics_arts.modules.custom_items.weapons.mele.KolossBlade;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.logic.server.server_events.*;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import org.checkerframework.checker.units.qual.C;

import java.util.List;

/**
 * The ServerEventHandler class is responsible for handling server-side events in the mod.
 * This class subscribes to the mod's event bus and listens for specific events to execute custom actions.
 * Each method annotated with @SubscribeEvent processes a specific event and performs the desired actions.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
@Mod.EventBusSubscriber
public class ServerEventHandler {

    private static Integer tick;

    /**
     * Handles the LivingDropsEvent to modify or add custom drops when a living entity dies.
     * This method delegates the handling of the event to the OnLivingEntityDropEvent class.
     * This method is triggered by the LivingDropsEvent and only runs on the server-side.
     *
     * @param event The LivingDropsEvent instance representing the current living entity drops event.
     */
    @SubscribeEvent
    public static void onLivingEntityDrop(final LivingDropsEvent event) {
        OnLivingEntityDropEvent.livingEntityDrop(event);
    }

    /**
     * Handles the PlayerLoggedInEvent to perform custom actions when a player joins the world.
     * This method is triggered by the PlayerLoggedInEvent and only runs on the server-side.
     * The method delegates the handling of the event to the OnJoinWorldEvent class if the logged-in entity is a ServerPlayer.
     *
     * @param event The PlayerEvent.PlayerLoggedInEvent instance representing the current player logged-in event.
     */
    @SubscribeEvent
    public static void onJoinWorld(final PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.getEntity().level.isClientSide) {
            if (event.getEntity() instanceof ServerPlayer) {
                OnJoinWorldEvent.joinWorld(event);
            }
        }
    }

    /**
     * Handles the PlayerSetSpawnEvent to perform custom actions when a player sets their spawn point.
     * This method is triggered by the PlayerSetSpawnEvent and only runs on the server-side.
     * The method delegates the handling of the event to the OnSetSpawnEvent class if the player entity is a ServerPlayer.
     *
     * @param event The PlayerSetSpawnEvent instance representing the current player set spawn event.
     */
    @SubscribeEvent
    public static void onSetSpawn(final PlayerSetSpawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer) {
            OnSetSpawnEvent.setSpawn(event);
        }
    }

    /**
     * Handles the LivingDeathEvent to perform custom actions when a player dies.
     * This method is triggered by the LivingDeathEvent and only runs on the server-side.
     * The method delegates the handling of the event to the OnLivingDeathEvent class if the player entity is a ServerPlayer.
     *
     * @param event The LivingDeathEvent instance representing the current player death event.
     */
    @SubscribeEvent
    public static void onLivingDeath(final LivingDeathEvent event) {
        if (event.getEntity() instanceof ServerPlayer) {
            OnLivingDeathEvent.livingDeath(event);
        }
    }

    /**
     * Handles the PlayerChangedDimensionEvent to perform custom actions when a player changes dimensions.
     * This method is triggered by the PlayerChangedDimensionEvent and only runs on the server-side.
     * The method syncs invested data when a player changes dimensions, using the ModNetwork class.
     *
     * @param event The PlayerEvent.PlayerChangedDimensionEvent instance representing the current player dimension change event.
     */
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

    /**
     * Handles the PlayerEvent.Clone to perform custom actions when a player is cloned.
     * This method is triggered by the PlayerEvent.Clone and only runs on the server-side.
     * The method delegates the handling of the event to the OnPlayerCloneEvent class.
     *
     * @param event The PlayerEvent.Clone instance representing the current player clone event.
     */
    @SubscribeEvent
    public static void onPlayerClone(final PlayerEvent.Clone event) {
        if (!event.getEntity().level.isClientSide()) {
            OnPlayerCloneEvent.playerClone(event);
        }
    }

    /**
     * Handles the LivingHurtEvent to perform custom actions when a player is damaged.
     * This method is triggered by the LivingHurtEvent and only runs on the server-side.
     * The method delegates the handling of the event to the OnDamageEvent class if both the source and target entities are ServerPlayers.
     *
     * @param event The LivingHurtEvent instance representing the current player damage event.
     */
    @SubscribeEvent
    public static void onDamageEvent(final LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof ServerPlayer && event.getEntity() instanceof ServerPlayer) {
            OnDamageEvent.onDamageFeruchemical(event, (ServerPlayer) event.getSource().getEntity(), (ServerPlayer) event.getEntity());
            OnDamageEvent.onDamageAllomantic(event, (ServerPlayer) event.getSource().getEntity(), (ServerPlayer) event.getEntity());
        }
    }

    /**
     * Handles the TickEvent.LevelTickEvent to perform custom actions on each world tick on server-side.
     * This method is triggered by the TickEvent.LevelTickEvent and only runs during the end phase.
     * The method iterates through all players in the level to perform custom actions based on their capabilities.
     *
     * @param event The TickEvent.LevelTickEvent instance representing the current world tick event.
     */
    @SubscribeEvent
    public static void onWorldTickEvent(final TickEvent.LevelTickEvent event) {

        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        if (event.side.isClient()) {
            return;
        }

        if (tick == null || tick >= 4800) {
            tick = 0;
        }

        tick++;



        Level level = event.level;
        List<? extends Player> playerList = level.players();

        for (Player player : playerList) {
            if (player != null) {
                try {
                    IInvestedPlayerData capabilities = CapabilityUtils.getCapability(player);
                    OnWorldTickEvent.onWorldTick(capabilities, player, event.level, tick);
                } catch (PlayerException ex) {
                    ex.printResumeLog();
                }

                /*
                 * GUNS
                 */
                if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof BasicGun instance) {
                    ItemStack gun = player.getItemInHand(InteractionHand.MAIN_HAND);
                    if (!gun.hasTag()) {
                        gun.setTag(GunUtils.generateGunTags(instance.getGunType()));
                    }
                    if (gun.getTag().getString(GunsAccess.STATE.getKey()).equals(GunsAccess.RELOAD.getKey())) {
                        if ((tick % instance.getGunType().getReload_cooldown()) == 0) {
                            gun.setTag(GunUtils.reload(gun, player, instance.getGunType()));
                        }
                    }
                } else if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof KolossBlade) {
                    if (tick % KolossBlade.descanso == 0 && player.getItemInHand(InteractionHand.MAIN_HAND).getTag().getFloat("CustomModelData") == 1F) {
                        ItemStack gun = player.getItemInHand(InteractionHand.MAIN_HAND);
                        CompoundTag compoundTag = gun.getTag();
                        compoundTag.putFloat("CustomModelData", 0);
                        gun.setTag(compoundTag);
                    }
                }
            }
        }
    }

}
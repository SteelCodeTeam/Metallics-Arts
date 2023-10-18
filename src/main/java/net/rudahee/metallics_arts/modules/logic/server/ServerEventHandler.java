package net.rudahee.metallics_arts.modules.logic.server;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.data.enums.implementations.ForgeMasterTrades;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.ArmorPiecesEnum;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.custom_entities.ettmetal_allomancer_entity.EttmetalAllomancerEntity;
import net.rudahee.metallics_arts.modules.custom_items.weapons.mele.KolossBlade;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.logic.server.server_events.*;
import net.rudahee.metallics_arts.modules.logic.server.server_events.entity_events.AllomancerEvents;
import net.rudahee.metallics_arts.modules.custom_entities.villagers.ModVillager;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.utils.ArmorUtils;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

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


    /*@SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onAttackEvent(final PlayerInteractEvent event) {
        Player player = event.getEntity();
        if (!event.getEntity().isLocalPlayer()) {
            if (player.getMainHandItem().is(ModItemsRegister.REVOLVER.get())) {
                event.setCancellationResult(InteractionResult.FAIL);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onAttackEntity(final AttackEntityEvent event) {
        Player player = event.getEntity();
        if (!event.getEntity().isLocalPlayer()) {
            if (player.getMainHandItem().is(ModItemsRegister.REVOLVER.get())) {
                event.setCanceled(true);
            }
        }
    }


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onAttackBlock(final PlayerInteractEvent.LeftClickBlock event) {
        Player player = event.getEntity();
        if (!event.getEntity().isLocalPlayer()) {
            if (player.getMainHandItem().is(ModItemsRegister.REVOLVER.get())) {
                event.setCancellationResult(InteractionResult.FAIL);
                event.setCanceled(true);
            }
        }
    }*/


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
        /*if (event.getEntity() instanceof ServerPlayer) {
            OnLivingEntityDropEvent.playerDrops(event);
        }*/
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
                OnJoinWorldEvent.joinWorld(event.getEntity());
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
        /*if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            if (event.getSource().type().msgId().contains("explosion")) {// todo mirar de mejorar este if
                NonNullList<ItemStack> list = serverPlayer.getInventory().armor;
                if (list.get(0).getItem().equals(ModItemsRegister.ETTMETAL_ARMOR.get(ArmorPiecesEnum.BOOTS).get()) &&
                        list.get(1).getItem().equals(ModItemsRegister.ETTMETAL_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get()) &&
                        list.get(2).getItem().equals(ModItemsRegister.ETTMETAL_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get()) &&
                        list.get(3).getItem().equals(ModItemsRegister.ETTMETAL_ARMOR.get(ArmorPiecesEnum.HELMET).get())) {
                    event.setAmount(event.getAmount()/2);
                }
            }

        }*/
        if (event.getSource().getDirectEntity() instanceof ServerPlayer && event.getEntity() instanceof ServerPlayer) {
            OnDamageEvent.onDamageFeruchemical(event, (ServerPlayer) event.getSource().getEntity(), (ServerPlayer) event.getEntity());
            OnDamageEvent.onDamageAllomantic(event, (ServerPlayer) event.getSource().getEntity(), (ServerPlayer) event.getEntity());
            OnDamageEvent.onDamageToArmor(event, (ServerPlayer) event.getEntity()); //todo funciona en este if ?
        } else if(event.getSource().getDirectEntity() instanceof EttmetalAllomancerEntity) {
            AllomancerEvents.OnEttmetalAllomancerHit(event, (LivingEntity) event.getSource().getDirectEntity(), event.getEntity());
        }
    }


    /**
     * This method detects hitting an element with left click.
     *
     * @param event The PlayerInteractEvent.LeftClickBlock instance that represents the block hit event.
     */
    @SubscribeEvent
    public static void onAttackBlock(final PlayerInteractEvent.LeftClickBlock event) {
        OnAttackBlockEvent.hitBlock(event.getEntity(), event.getPos());
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
                /*if (player.getMainHandItem().getItem() instanceof BasicGun instance) {
                    ItemStack gun = player.getMainHandItem();
                    if (gun.getTag().getString(GunsAccess.STATE.getKey()).equals(GunsAccess.RELOAD.getKey())) {
                        if ((tick % instance.getGunType().getReload_cooldown()) == 0) {
                            gun.setTag(GunUtils.reload(gun, player, instance.getGunType()));
                        }
                    }
                } else*/ if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof KolossBlade) {
                    if (tick % KolossBlade.COOLDOWN == 0 && player.getItemInHand(InteractionHand.MAIN_HAND).getTag().getFloat("CustomModelData") != 1F) {
                        ItemStack gun = player.getItemInHand(InteractionHand.MAIN_HAND);
                        CompoundTag compoundTag = gun.getTag();
                        compoundTag.putFloat("CustomModelData", 1);
                        gun.setTag(compoundTag);
                    }
                }
            }
        }


    }
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == ModVillager.VILLAGER_CRUCIBLE_PROFESSION.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            for (ForgeMasterTrades localTrade: ForgeMasterTrades.values()) {
                if (localTrade.getOptionalSecondInput() == null) {
                    trades.get(localTrade.getLevel()).add((trader, rand)->
                            new MerchantOffer(localTrade.getInput(),localTrade.getOutput(),localTrade.getMaxqty(),localTrade.getXp(),0.09F
                    ));
                } else {
                    trades.get(localTrade.getLevel()).add((trader, rand)->
                            new MerchantOffer(localTrade.getInput(),localTrade.getOptionalSecondInput(), localTrade.getOutput(), localTrade.getMaxqty(),localTrade.getXp(),0.09F
                            ));
                }
            }
        }
    }


}
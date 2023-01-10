package net.rudahee.metallics_arts.modules.logic.server;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.GoldAndElectrumHelpers;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.*;
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

    public static int x = 8;
    public static int y = 8;
    public static int z = 8;
    private static int actualTick = 0;

    public static int getActualTick() {
        return actualTick;
    }

    public static void setActualTick(int actualTick) {
        ServerEventHandler.actualTick = actualTick;
    }

    private static Player newPlayer = null;
    public static int radius;
    @SubscribeEvent
    public static void onWorldTickEvent(final TickEvent.LevelTickEvent event) throws Exception {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        Level world = event.level;
        List<? extends Player> playerList = world.players();
        for (int playerIndex = playerList.size() - 1; playerIndex >= 0; playerIndex--) {

            if (playerList.get(playerIndex) instanceof Player || playerList.get(playerIndex) instanceof ServerPlayer) {
                newPlayer = playerList.get(playerIndex);
            } else {
                newPlayer = null;
            }
            if (newPlayer == null) {
                return;
            }

            Player player = newPlayer;
            IInvestedPlayerData playerCapability = CapabilityUtils.getCapability(player);

            if (playerCapability.isInvested()) {
                if (player instanceof ServerPlayer) {
                    playerCapability.tickAllomancyBurningMetals((ServerPlayer) player);
                }

                OnWorldTickEvent.duraluminAndExternalNicrosilEffect(playerCapability);

                // KOLLOSS BLADE EQUIP
                OnWorldTickEvent.equipKolossBlade(player,playerCapability);


                // ZINC POWERS

                if (playerCapability.isBurning(MetalTagEnum.ZINC) && playerCapability.isBurning(MetalTagEnum.LERASIUM)){
                    if (event.level instanceof ServerLevel) {
                        radius = (playerCapability.getEnhanced()) ? 14 : 12;
                        BrassAllomanticHelper.angryEntitiesWithLerasium
                                (player,event.level,CapabilityUtils.getBubble(player,radius),playerCapability.getEnhanced());
                    }
                }
                // BRASS POWERS

                if (playerCapability.isBurning(MetalTagEnum.BRASS)&&playerCapability.isBurning(MetalTagEnum.LERASIUM)){
                    if (event.level instanceof ServerLevel) {
                        radius = (playerCapability.getEnhanced()) ? 14 : 12;
                        ZincAllomanticHelper.happyEntitiesWithLerasium(player, event.level,CapabilityUtils.getBubble(player,radius),playerCapability.getEnhanced());
                    }
                }
                // BENDALLOY POWERS

                if (playerCapability.isBurning(MetalTagEnum.BENDALLOY)) {
                    if (!playerCapability.isBurning(MetalTagEnum.CADMIUM)) {
                        // ENHANCED
                        if (playerCapability.getEnhanced()) {
                            BendalloyAllomanticHelper.AddAiSteepsEnhanced(player);
                            if (event.level instanceof ServerLevel) {
                                radius = (playerCapability.isBurning(MetalTagEnum.LERASIUM)) ? 17 : 14;
                                // Ticks extra in random blocks, tile entities and entities.
                                BendalloyAllomanticHelper.BendalloyMobEffects(player, event.level, CapabilityUtils.getBubble(player,radius), radius, true);
                            }
                        }
                        // NORMAL
                        else {
                            BendalloyAllomanticHelper.AddAiSteeps(player);
                            if (event.level instanceof ServerLevel) {
                                radius = (playerCapability.isBurning(MetalTagEnum.LERASIUM)) ? 13 : 11;
                                // Ticks extra in random blocks, tile entities and entities.
                                BendalloyAllomanticHelper.BendalloyMobEffects(player, event.level, CapabilityUtils.getBubble(player,radius), radius, false);
                            }
                        }
                    }
                }
                // CHROMIUM ENHANCED

                if (playerCapability.isBurning(MetalTagEnum.CHROMIUM) && playerCapability.getEnhanced()) {
                    if (event.level instanceof ServerLevel) {
                        radius = (playerCapability.isBurning(MetalTagEnum.LERASIUM)) ? 14 : 11;
                        // Ticks extra in random blocks, tile entities and entities.
                        ChromiumAllomanticHelper.drainMetalCloudChromium((ServerLevel) event.level, CapabilityUtils.getBubble(player,radius));
                    }
                }
                // CADMIUM POWERS

                if (playerCapability.isBurning(MetalTagEnum.CADMIUM)) {
                    if (!playerCapability.isBurning(MetalTagEnum.BENDALLOY)) {
                        // ENHANCED
                        if (playerCapability.getEnhanced()) {
                            radius = (playerCapability.isBurning(MetalTagEnum.LERASIUM)) ? 16 : 13;
                            CadmiumAllomanticHelper.CadmiumEffectSelfPlayerEnhanced(player);
                            if (event.level instanceof ServerLevel) {
                                event.level.getEntitiesOfClass(LivingEntity.class, CapabilityUtils.getBubble(player,radius)).forEach(entity -> {
                                    if (entity != player) {
                                        //Do others in de cloud my powers.
                                        CadmiumAllomanticHelper.CadmiumMobEffectsOtherPlayersEnhanced(player, playerCapability);
                                    }
                                });
                            }
                        }
                        // NORMAL
                        else {
                            radius = (playerCapability.isBurning(MetalTagEnum.LERASIUM)) ? 11 : 8;
                            // Do myself my own powers
                            CadmiumAllomanticHelper.CadmiumEffectSelfPlayer(player);
                            if (event.level instanceof ServerLevel) {
                                event.level.getEntitiesOfClass(LivingEntity.class,CapabilityUtils.getBubble(player,radius)).forEach(entity -> {
                                    if (entity != player) {
                                        //Do others in de cloud my powers.
                                        CadmiumAllomanticHelper.CadmiumMobEffectsOtherPlayers(player, playerCapability);
                                    }
                                });
                            }
                        }
                    }
                }
                if (actualTick >= 240) {
                    actualTick = 0;
                } else {
                    actualTick++;
                }
            }
            // Extra delete when you are using both.
            if (playerCapability.isBurning(MetalTagEnum.CADMIUM) && playerCapability.isBurning(MetalTagEnum.BENDALLOY)) {
                if (playerCapability.getEnhanced() && playerCapability.isBurning(MetalTagEnum.LERASIUM)) {
                    playerCapability.drainMetals(MetalTagEnum.LERASIUM);
                }
            }
            // PEWTER POWERS

            if (playerCapability.isBurning(MetalTagEnum.PEWTER)) {
                PewterAllomanticHelper.addPewterEffects(player, playerCapability.isBurning(MetalTagEnum.LERASIUM), playerCapability.getEnhanced());
            }
            // TIN POWERS

            if (playerCapability.isBurning(MetalTagEnum.TIN)) {
                if (playerCapability.getEnhanced()) {
                    TinAllomanticHelper.addTinEffectsEnhanced(player);
                } else {
                    TinAllomanticHelper.addTinEffects(player);
                }
            }
            // BRONZE POWERS

            if (playerCapability.isBurning(MetalTagEnum.BRONZE) && !playerCapability.getEnhanced()) {
                if (event.level instanceof ServerLevel) {
                    radius = (playerCapability.isBurning(MetalTagEnum.LERASIUM)) ? 14 : 12;
                    BronzeAllomanticHelper.BronzeAiEntityManipulation(CapabilityUtils.getBubble(player,radius), player, event.level);
                }
                // BRONZE ENHANCED POWERS

            } else if (playerCapability.isBurning(MetalTagEnum.BRONZE) && playerCapability.getEnhanced()) {
                if (event.level instanceof ServerLevel) {
                    radius = (playerCapability.isBurning(MetalTagEnum.LERASIUM)) ? 18 : 16;
                    BronzeAllomanticHelper.BronzeEnhancedAiEntityManipulation(CapabilityUtils.getBubble(player,radius), player, event.level);
                }
            }
            // COPPER POWERS

            if (playerCapability.isBurning(MetalTagEnum.COPPER) && !playerCapability.getEnhanced()) {
                if (event.level instanceof ServerLevel) {
                    radius = (playerCapability.isBurning(MetalTagEnum.LERASIUM)) ? 14 : 12;
                    CopperAllomanticHelper.CopperAiEntityManipulation(CapabilityUtils.getBubble(player,radius), player, event.level);
                }
            // COPPER ENHANCED POWERS
            } else if (playerCapability.isBurning(MetalTagEnum.COPPER) && playerCapability.getEnhanced()) {
                if (event.level instanceof ServerLevel) {
                    radius = (playerCapability.isBurning(MetalTagEnum.LERASIUM)) ? 18 : 16;
                    CopperAllomanticHelper.CopperEnhancedAiEntityManipulation(CapabilityUtils.getBubble(player,radius), player, event.level);
                }
            }
            // ETTMETAL

            if (playerCapability.isBurning(MetalTagEnum.ETTMETAL)){
                EttmetalAllomanticHelper.ettmetalExplotion(event.level,player);
            }
            // ALUMINUM POWER

            if (playerCapability.isBurning(MetalTagEnum.ALUMINUM)) {
                AluminumAllomanticHelper.drainAndCleanEffects(player,playerCapability);
            }
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
    }
}
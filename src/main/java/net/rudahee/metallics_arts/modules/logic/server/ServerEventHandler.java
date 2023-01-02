package net.rudahee.metallics_arts.modules.logic.server;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.logic.server.powers.*;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
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
    public static int ticks = 0;
    public static int x = 8;
    public static int y = 8;
    public static int z = 8;
    public static int actualTick = 0;
    private static Player newPlayer = null;
    private static int buffNicrosilDuralumin = -1;

    @SubscribeEvent
    public static void onWorldTickEvent(final TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        Level world = event.level;
        List<? extends Player> playerList = world.players();
        ticks++;
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
            player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(
                    playerCapability -> {
                        if (playerCapability.isInvested()) {
                            if (player instanceof ServerPlayer) {
                                playerCapability.tickAllomancyBurningMetals((ServerPlayer) player);
                            }


                            if (playerCapability.isBurning(MetalTagEnum.DURALUMIN)) {
                                if ((playerCapability.getAllomanticAmount(MetalTagEnum.DURALUMIN)>(MetalTagEnum.DURALUMIN.getMaxAllomanticTicksStorage()*0.88))
                                        || (buffNicrosilDuralumin != -1)){

                                    if (buffNicrosilDuralumin == -1) {
                                        playerCapability.setEnhanced(true);
                                        buffNicrosilDuralumin = MetalTagEnum.DURALUMIN.getMaxAllomanticTicksStorage();
                                    }
                                    for (MetalTagEnum m : MetalTagEnum.values()) {
                                        if (playerCapability.isBurning(m) && !playerCapability.containsInListMetalBuff(m)){
                                            playerCapability.addListMetalBuff(m);
                                        }
                                    }
                                } else {
                                    playerCapability.setBurning(MetalTagEnum.DURALUMIN,false);
                                }
                            } else if (playerCapability.getEnhanced()) {
                                if (buffNicrosilDuralumin == -1) {
                                    buffNicrosilDuralumin = MetalTagEnum.DURALUMIN.getMaxAllomanticTicksStorage();
                                }
                                for (MetalTagEnum m : MetalTagEnum.values()) {
                                    if (playerCapability.isBurning(m) && !playerCapability.containsInListMetalBuff(m)){
                                        playerCapability.addListMetalBuff(m);
                                    }
                                }

                            } else {
                                if (!playerCapability.getListMetalBuff().isEmpty()) {
                                    for (MetalTagEnum m: playerCapability.getListMetalBuff()) {
                                        playerCapability.drainMetals(m);
                                    }
                                    playerCapability.clearListMetalBuff();
                                }
                            }

                            if ( !(playerCapability.isBurning(MetalTagEnum.PEWTER) || playerCapability.isDecanting(MetalTagEnum.PEWTER))
                                    && (player.getMainHandItem().getItem() == ModItemsRegister.KOLOSS_BLADE.get() || player.getOffhandItem().getItem() == ModItemsRegister.KOLOSS_BLADE.get())) {

                                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 2, true, true, false));
                                player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 10, 2, true, true, false));
                                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 10, 2, true, true, false));

                            }

                            /************************
                             * BRONZE FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalTagEnum.BRONZE)) {
                                //BlockPos negative = new BlockPos(player.position()).offset(-x -4, -y -4, -z -4);
                                //BlockPos positive = new BlockPos(player.position()).offset(x +4, y + 24 , z +4);

                                CopperAndBronzeHelpers.DontSpawnPhantoms(player, CapabilityUtils.getBubble(player,12), event.level);
                            } else if (playerCapability.isStoring(MetalTagEnum.BRONZE)) {
                                if (actualTick == 240) {
                                    CopperAndBronzeHelpers.SpawnPhamtonsWithFireResistance(player, world);
                                }
                            }
                            /************************
                             * BRASS FERUCHEMIC
                             ************************/
                            if (playerCapability.isStoring(MetalTagEnum.BRASS)) {
                                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 40, 1, true, false));

                                if (world.getBiome(player.getOnPos()).is(Tags.Biomes.IS_COLD) || (world.getBiome(player.getOnPos()).is(Biomes.DESERT) && world.isNight())) {
                                    ZincAndBrassHelpers.addFrozenTicks(player);
                                }

                            } else if (playerCapability.isDecanting(MetalTagEnum.BRASS)) {
                                if (world.getBiome(player.getOnPos()).is(Biomes.DESERT) && world.isDay()) {
                                    ZincAndBrassHelpers.addBurnBodyTicks(player);
                                } else if (world.getBiome(player.getOnPos()).is(Tags.Biomes.IS_HOT)) {
                                    ZincAndBrassHelpers.addBurnBodyTicks(player);
                                }
                            }
                            /************************
                             * GOLD FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalTagEnum.GOLD)) {
                                if (actualTick == 30 || actualTick == 60 || actualTick == 90 || actualTick == 120 || actualTick == 150 || actualTick == 180 || actualTick == 210 || actualTick == 240) {
                                    GoldAndElectrumHelpers.addHealth(player,1);
                                }
                            } else if (playerCapability.isStoring(MetalTagEnum.GOLD)) {
                                if (playerCapability.isStoring(MetalTagEnum.ELECTRUM)) {
                                    playerCapability.setStoring(MetalTagEnum.ELECTRUM, false);
                                }

                                if (actualTick == 30 || actualTick == 60 || actualTick == 90 || actualTick == 120 || actualTick == 150 || actualTick == 180 || actualTick == 210 || actualTick == 240) {
                                    GoldAndElectrumHelpers.removeHealth(player,1);
                                }
                            }
                            /************************
                             * ELECTRUM FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalTagEnum.ELECTRUM)) {
                                GoldAndElectrumHelpers.addHearts(player,30);
                            } else if (playerCapability.isStoring(MetalTagEnum.ELECTRUM)) {
                                if (playerCapability.isStoring(MetalTagEnum.GOLD)) {
                                    playerCapability.setStoring(MetalTagEnum.GOLD, false);
                                }
                                GoldAndElectrumHelpers.removeHearts(player,10);
                            }
                            /************************
                             * TIN FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalTagEnum.TIN)) {
                                PewterAndTinHelpers.addFecruchemicVision(player);
                            } else if (playerCapability.isStoring(MetalTagEnum.TIN)) {
                                PewterAndTinHelpers.removeFeruchemicVision(player);
                            }
                            /************************
                             * PEWTER FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalTagEnum.PEWTER)) {
                                PewterAndTinHelpers.decantPewterEffectsFeruchemic(player);
                            } else if (playerCapability.isStoring(MetalTagEnum.PEWTER)) {
                                PewterAndTinHelpers.storePewterEffectsFeruchemic(player);
                            }
                            /************************
                             * STEEL FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalTagEnum.STEEL)) {
                                IronAndSteelHelpers.addSpeed(player,3);
                            } else if (playerCapability.isStoring(MetalTagEnum.STEEL)) {
                                IronAndSteelHelpers.removeSpeed(player,3);
                            }
                            /************************
                             * IRON FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalTagEnum.IRON)) {
                                IronAndSteelHelpers.increaseWeight(player);
                            } else if (playerCapability.isStoring(MetalTagEnum.IRON)) {
                                IronAndSteelHelpers.reduceWeight(player);
                            }
                            /************************
                             * CADMIUM FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalTagEnum.CADMIUM)) {
                                if (actualTick == 30 || actualTick == 60 || actualTick == 90 || actualTick == 120 || actualTick == 150 || actualTick == 180 || actualTick == 210 || actualTick == 240) {
                                    BendalloyAndCadmiunHelpers.throwBreathEffect(player, 10);
                                }
                            } else if (playerCapability.isStoring(MetalTagEnum.CADMIUM)) {
                                BendalloyAndCadmiunHelpers.drowningEffect(player,actualTick);
                            }
                            /************************
                             * BENDALLOY FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalTagEnum.BENDALLOY)) {
                                if (actualTick == 30 || actualTick == 60 || actualTick == 90 || actualTick == 120 || actualTick == 150 || actualTick == 180 || actualTick == 210 || actualTick == 240) {
                                    BendalloyAndCadmiunHelpers.addFoodLevel(player,1);
                                }
                            } else if (playerCapability.isStoring(MetalTagEnum.BENDALLOY)) {
                                if (actualTick == 30 || actualTick == 60 || actualTick == 90 || actualTick == 120 || actualTick == 150 || actualTick == 180 || actualTick == 210 || actualTick == 240) {
                                    BendalloyAndCadmiunHelpers.removeFoodLevel(player,1);
                                }
                            }
                            /************************
                             * DURALUMIN FERUCHEMIC
                             ************************/
                            ResourceKey<Biome> biome = world.getBiome(player.getOnPos()).unwrapKey().get();

                            if (playerCapability.isDecanting(MetalTagEnum.DURALUMIN)) {
                                if (actualTick == 30 || actualTick == 60 || actualTick == 90 || actualTick == 120 || actualTick == 150 || actualTick == 180 || actualTick == 210 || actualTick == 240) {
                                    DuraluminAndAluminumHelpers.duraluminDecantingMobEffects(player,biome);
                                }
                            } else if (playerCapability.isStoring(MetalTagEnum.DURALUMIN)) {
                                if (actualTick == 30 || actualTick == 60 || actualTick == 90 || actualTick == 120 || actualTick == 150 || actualTick == 180 || actualTick == 210 || actualTick == 240) {
                                    DuraluminAndAluminumHelpers.duraluminStoringMobEffects(player, biome);

                                }
                            }
                            /************************
                             * CHROMIUM FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalTagEnum.CHROMIUM)){
                                ChromiumAndNicrosilHelpers.goodLuck(player);
                            } else if (playerCapability.isStoring(MetalTagEnum.CHROMIUM)){
                                ChromiumAndNicrosilHelpers.badLuck(player, actualTick == 80 || actualTick == 160 || actualTick == 240);
                            }
                            /************************
                             * ATIUM FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalTagEnum.ATIUM)){
                                AtiumAndMalatiumHelpers.decantAtium(player);
                            } else if (playerCapability.isStoring(MetalTagEnum.ATIUM)){
                                AtiumAndMalatiumHelpers.storageAtium(player);
                            }
                            if (actualTick >= 240) {
                                actualTick = 0;
                            } else {
                                actualTick++;
                            }
                            /************************
                             * ZINC POWERS
                             ************************/
                            if (playerCapability.isBurning(MetalTagEnum.ZINC) && playerCapability.isBurning(MetalTagEnum.LERASIUM)){
                                int radius;
                                if (event.level instanceof ServerLevel) {
                                    if (playerCapability.getEnhanced()){
                                        radius = 14;
                                    } else {
                                        radius = 12;
                                    }
                                    ZincAndBrassHelpers.angryEntitiesWithLerasium
                                            (player,event.level,CapabilityUtils.getBubble(player,radius),playerCapability.getEnhanced());
                                }
                            }
                            /************************
                             * ZINC POWERS
                             ************************/
                            if (playerCapability.isBurning(MetalTagEnum.BRASS)&&playerCapability.isBurning(MetalTagEnum.LERASIUM)){
                                int radius;
                                if (event.level instanceof ServerLevel) {
                                    if (playerCapability.getEnhanced()){
                                        radius = 14;
                                    } else {
                                        radius = 12;
                                    }

                                    ZincAndBrassHelpers.happyEntitiesWithLerasium(player,event.level,CapabilityUtils.getBubble(player,radius),playerCapability.getEnhanced());
                                }
                            }
                            /************************
                             * BENDALLOY POWERS
                             ************************/
                            if (playerCapability.isBurning(MetalTagEnum.BENDALLOY)) {
                                if (!playerCapability.isBurning(MetalTagEnum.CADMIUM)) {
                                    BlockPos negative;
                                    BlockPos positive;
                                    int radius;
                                    /** ENHANCED */
                                    if (playerCapability.getEnhanced()) {
                                        BendalloyAndCadmiunHelpers.AddAiSteepsEnhanced(player);
                                        if (event.level instanceof ServerLevel) {
                                            if (playerCapability.isBurning(MetalTagEnum.LERASIUM)) {
                                                radius = 17;
                                                negative = new BlockPos(player.position()).offset(-x - 9, -y - 9, -z - 9);
                                                positive = new BlockPos(player.position()).offset(x + 9, y + 9 , z + 9);
                                            } else {
                                                negative = new BlockPos(player.position()).offset(-x - 6, -y - 6, -z - 6);
                                                positive = new BlockPos(player.position()).offset(x + 6, y + 6 , z + 6);
                                                radius = 14;
                                            }
                                            // Ticks extra in random blocks, tile entities and entities.
                                            BendalloyAndCadmiunHelpers.BendalloyMobEffects(player, event.level, CapabilityUtils.getBubble(player,radius), negative, positive, true);
                                        }
                                    }
                                    /** NORMAL */
                                    else {
                                        BendalloyAndCadmiunHelpers.AddAiSteeps(player);
                                        if (event.level instanceof ServerLevel) {
                                            if (playerCapability.isBurning(MetalTagEnum.LERASIUM)) {
                                                negative = new BlockPos(player.position()).offset(-x - 5, -y - 5, -z - 5);
                                                positive = new BlockPos(player.position()).offset(x + 5, y + 5 , z + 5);
                                                radius = 13;
                                            } else {
                                                negative = new BlockPos(player.position()).offset(-x - 3, -y - 3, -z - 3);
                                                positive = new BlockPos(player.position()).offset(x + 3, y + 3 , z + 3);
                                                radius = 11;
                                            }
                                            // Ticks extra in random blocks, tile entities and entities.
                                            BendalloyAndCadmiunHelpers.BendalloyMobEffects(player, event.level, CapabilityUtils.getBubble(player,radius), negative, positive, false);
                                        }
                                    }
                                }
                            }
                            /************************
                             * CHROMIUM ENHANCED
                             ************************/
                            if (playerCapability.isBurning(MetalTagEnum.CHROMIUM) && playerCapability.getEnhanced()) {
                                if (event.level instanceof ServerLevel) {
                                    int radius;
                                    if (playerCapability.isBurning(MetalTagEnum.LERASIUM)) {
                                        radius = 14;
                                    } else {
                                        radius = 11;
                                    }
                                    // Ticks extra in random blocks, tile entities and entities.
                                    ChromiumAndNicrosilHelpers.drainMetalCloudChromium((ServerLevel) event.level, CapabilityUtils.getBubble(player,radius));
                                }
                            }
                            /************************
                             * CADMIUM POWERS
                             ************************/
                            if (playerCapability.isBurning(MetalTagEnum.CADMIUM)) {
                                if (!playerCapability.isBurning(MetalTagEnum.BENDALLOY)) {
                                    BlockPos negative;
                                    BlockPos positive;
                                    int amplifier;
                                    int time;
                                    /** ENHANCED */
                                    if (playerCapability.getEnhanced()) {
                                        if (playerCapability.isBurning(MetalTagEnum.LERASIUM)) {
                                            negative = new BlockPos(player.position()).offset(-x -8, -y -8, -z -8);
                                            positive = new BlockPos(player.position()).offset(x+8 , y+8, z+8);
                                            amplifier = 100;
                                            time = 60;
                                        } else  {
                                            negative = new BlockPos(player.position()).offset(-x -5, -y -5, -z -5);
                                            positive = new BlockPos(player.position()).offset(x+5 , y+5, z+5);
                                            amplifier = 2;
                                            time = 40;
                                        }
                                        BendalloyAndCadmiunHelpers.CadmiumEffectSelfPlayerEnhanced(player);
                                        if (event.level instanceof ServerLevel) {
                                            event.level.getEntitiesOfClass(LivingEntity.class, new AABB(negative, positive)).forEach(entity -> {
                                                if (entity != player) {
                                                    //Do others in de cloud my powers.
                                                    BendalloyAndCadmiunHelpers.CadmiumMobEffectsOtherPlayersEnhanced(entity, time, amplifier);
                                                }
                                            });
                                        }
                                    }
                                    /** NORMAL */
                                    else {
                                        if (playerCapability.isBurning(MetalTagEnum.LERASIUM)) {
                                            negative = new BlockPos(player.position()).offset(-x -3, -y -3, -z -3);
                                            positive = new BlockPos(player.position()).offset(x+3 , y+3, z+3);
                                            amplifier = 2;
                                            time = 20;
                                        } else  {
                                            negative = new BlockPos(player.position()).offset(-x, -y, -z);
                                            positive = new BlockPos(player.position()).offset(x, y, z);
                                            amplifier = 1;
                                            time = 10;
                                        }
                                        // Do myself my own powers
                                        BendalloyAndCadmiunHelpers.CadmiumEffectSelfPlayer(player);
                                        if (event.level instanceof ServerLevel) {

                                            event.level.getEntitiesOfClass(LivingEntity.class, new AABB(negative, positive)).forEach(entity -> {
                                                if (entity != player) {
                                                    //Do others in de cloud my powers.
                                                    BendalloyAndCadmiunHelpers.CadmiumMobEffectsOtherPlayers(entity, time, amplifier);
                                                }
                                            });
                                        }
                                    }
                                }
                            }
                        }
                        // Extra delete when you are using both.
                        if (playerCapability.isBurning(MetalTagEnum.CADMIUM) && playerCapability.isBurning(MetalTagEnum.BENDALLOY)) {
                            if (playerCapability.getEnhanced() && playerCapability.isBurning(MetalTagEnum.LERASIUM)) {
                                playerCapability.drainMetals(MetalTagEnum.LERASIUM);
                            }
                        }
                        /************************
                         * PEWTER POWERS
                         ************************/
                        if (playerCapability.isBurning(MetalTagEnum.PEWTER)) {
                            PewterAndTinHelpers.addPewterEffects(player,
                                    playerCapability.isBurning(MetalTagEnum.LERASIUM),
                                    playerCapability.getEnhanced());
                        }
                        /************************
                         * TIN POWERS
                         ************************/
                        if (playerCapability.isBurning(MetalTagEnum.TIN)) {
                            if (playerCapability.getEnhanced()) {
                                PewterAndTinHelpers.addTinEffectsEnhanced(player);
                            } else {
                                PewterAndTinHelpers.addTinEffects(player);
                            }
                        }
                        /************************
                         * BRONZE POWERS
                         ************************/
                        if (playerCapability.isBurning(MetalTagEnum.BRONZE) && !playerCapability.getEnhanced()) {
                            if (event.level instanceof ServerLevel) {
                                BlockPos negative;
                                BlockPos positive;
                                if (playerCapability.isBurning(MetalTagEnum.LERASIUM)) {
                                    negative = new BlockPos(player.position()).offset(-x - 6, -y - 6, -z - 6);
                                    positive = new BlockPos(player.position()).offset(x + 6, y + 6, z + 6);
                                } else {
                                    negative = new BlockPos(player.position()).offset(-x - 4, -y - 4, -z - 4);
                                    positive = new BlockPos(player.position()).offset(x + 4, y + 4, z + 4);
                                }
                                CopperAndBronzeHelpers.BronzeAiEntityManipulation(new AABB(negative, positive), player, event.level);
                            }
                            /************************
                             * BRONZE ENHANCED POWERS
                             ************************/
                        } else if (playerCapability.isBurning(MetalTagEnum.BRONZE) && playerCapability.getEnhanced()) {
                            if (event.level instanceof ServerLevel) {
                                BlockPos negative;
                                BlockPos positive;
                                if (playerCapability.isBurning(MetalTagEnum.LERASIUM)){
                                    negative = new BlockPos(player.position()).offset(-x - 10, -y - 10, -z - 10);
                                    positive = new BlockPos(player.position()).offset(x + 10, y + 10, z + 10);
                                } else {
                                    negative = new BlockPos(player.position()).offset(-x - 8, -y - 8, -z - 8);
                                    positive = new BlockPos(player.position()).offset(x + 8, y + 8, z + 8);
                                }
                                CopperAndBronzeHelpers.BronzeEnhancedAiEntityManipulation(new AABB(negative, positive), player, event.level);
                            }
                        }
                        /************************
                         * COPPER POWERS
                         ************************/
                        if (playerCapability.isBurning(MetalTagEnum.COPPER) && !playerCapability.getEnhanced()) {
                            if (event.level instanceof ServerLevel) {
                                BlockPos negative;
                                BlockPos positive;
                                if (playerCapability.isBurning(MetalTagEnum.LERASIUM)){
                                    negative = new BlockPos(player.position()).offset(-x - 6, -y - 6, -z - 6);
                                    positive = new BlockPos(player.position()).offset(x + 6, y + 6, z + 6);
                                } else {
                                    negative = new BlockPos(player.position()).offset(-x - 4, -y - 4, -z - 4);
                                    positive = new BlockPos(player.position()).offset(x + 4, y + 4, z + 4);
                                }
                                CopperAndBronzeHelpers.CopperAiEntityManipulation(new AABB(negative, positive), player, event.level);
                            }
                            /************************
                             * COPPER ENHANCED POWERS
                             ************************/
                        } else if (playerCapability.isBurning(MetalTagEnum.COPPER) && playerCapability.getEnhanced()) {
                            if (event.level instanceof ServerLevel) {
                                BlockPos negative;
                                BlockPos positive;
                                if (playerCapability.isBurning(MetalTagEnum.LERASIUM)) {
                                    negative = new BlockPos(player.position()).offset(-x - 10, -y - 10, -z - 10);
                                    positive = new BlockPos(player.position()).offset(x + 10, y + 10, z + 10);
                                } else {
                                    negative = new BlockPos(player.position()).offset(-x - 8, -y - 8, -z - 8);
                                    positive = new BlockPos(player.position()).offset(x + 8, y + 8, z + 8);
                                }
                                CopperAndBronzeHelpers.CopperEnhancedAiEntityManipulation(new AABB(negative, positive), player, event.level);
                            }
                        }
                        /************************
                         * ETTMETAL
                         ************************/
                        if (playerCapability.isBurning(MetalTagEnum.ETTMETAL)){
                            LerasiumAndEttmetalHelpers.ettmetalExplotion(event.level,player);
                        }
                        /************************
                         * ALUMINUM POWER
                         ************************/
                        if (playerCapability.isBurning(MetalTagEnum.ALUMINUM)) {
                            DuraluminAndAluminumHelpers.drainAndCleanEffects(player,playerCapability);

                        }
                        /************************
                         * ELECTRUM POWER (ENHANCED)
                         ************************/
                        if (playerCapability.isBurning(MetalTagEnum.ELECTRUM) &&  playerCapability.getEnhanced()) {
                            BlockPos block = null;
                            String dimension = null;
                            if (playerCapability.getSpawnPos() != null && playerCapability.getSpawnDimension() != null) {
                                block = new BlockPos(playerCapability.getSpawnPos()[0], playerCapability.getSpawnPos()[1], playerCapability.getSpawnPos()[2]);
                                dimension = playerCapability.getSpawnDimension();
                            } else {
                                block = new BlockPos(world.getLevelData().getXSpawn(),world.getLevelData().getYSpawn(), world.getLevelData().getZSpawn());
                                dimension = Level.OVERWORLD.location().toString();
                            }
                            if (playerCapability.isBurning(MetalTagEnum.ELECTRUM) && playerCapability.isBurning(MetalTagEnum.LERASIUM) && playerCapability.isBurning(MetalTagEnum.DURALUMIN)){

                                BlockPos negative;
                                BlockPos positive;
                                negative = new BlockPos(player.position()).offset(-x - 3, -y - 3, -z - 3);
                                positive = new BlockPos(player.position()).offset(x + 3, y + 3, z + 3);
                                GoldAndElectrumHelpers.multiTeleport(player, new AABB(negative, positive), event.level, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension),block);
                                playerCapability.drainMetals(MetalTagEnum.LERASIUM);
                                playerCapability.drainMetals(MetalTagEnum.DURALUMIN);

                            }else if(playerCapability.isBurning(MetalTagEnum.ELECTRUM) && playerCapability.isBurning(MetalTagEnum.DURALUMIN)){

                                GoldAndElectrumHelpers.teleport(player, event.level, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension), block);
                                playerCapability.drainMetals(MetalTagEnum.DURALUMIN);

                            }else{

                                //GoldAndElectrumHelpers.teleport(player, event.level, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension), block);

                            }
                            playerCapability.drainMetals(MetalTagEnum.ELECTRUM);
                        }
                        /************************
                         * GOLD POWER (ENHANCED)
                         ************************/
                        if (playerCapability.isBurning(MetalTagEnum.GOLD) && playerCapability.getEnhanced()) {
                            BlockPos block = null;
                            String dimension = null;
                            if (playerCapability.getDeathPos() != null && playerCapability.getDeathDimension() != null) {
                                block = new BlockPos(playerCapability.getDeathPos()[0], playerCapability.getDeathPos()[1], playerCapability.getDeathPos()[2]);
                                dimension = playerCapability.getDeathDimension();
                            } else {
                                block = new BlockPos(world.getLevelData().getXSpawn(),world.getLevelData().getYSpawn(), world.getLevelData().getZSpawn());
                                dimension = Level.OVERWORLD.location().toString();
                            }

                            if (playerCapability.isBurning(MetalTagEnum.GOLD) && playerCapability.isBurning(MetalTagEnum.LERASIUM) && playerCapability.isBurning(MetalTagEnum.DURALUMIN)){

                                BlockPos negative;
                                BlockPos positive;
                                negative = new BlockPos(player.position()).offset(-x - 3, -y - 3, -z - 3);
                                positive = new BlockPos(player.position()).offset(x + 3, y + 3, z + 3);
                                GoldAndElectrumHelpers.multiTeleport(player, new AABB(negative, positive), event.level, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension),block);
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
                        /************************
                         * MALATIUM POWER (ENHANCED)
                         ************************/
                        if (playerCapability.isBurning(MetalTagEnum.MALATIUM) && playerCapability.getEnhanced()) {
                            BlockPos block = null;
                            String dimension = null;
                            if (GoldAndElectrumHelpers.getBlock() != null && GoldAndElectrumHelpers.getDimension() != null) {
                                block = GoldAndElectrumHelpers.getBlock();
                                dimension = GoldAndElectrumHelpers.getDimension();
                                if (playerCapability.isBurning(MetalTagEnum.LERASIUM)) {
                                    BlockPos negative;
                                    BlockPos positive;
                                    negative = new BlockPos(player.position()).offset(-x - 3, -y - 3, -z - 3);
                                    positive = new BlockPos(player.position()).offset(x + 3, y + 3, z + 3);
                                    GoldAndElectrumHelpers.multiTeleport(player, new AABB(negative, positive), event.level, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension),block);
                                    playerCapability.drainMetals(MetalTagEnum.LERASIUM);
                                } else {
                                    GoldAndElectrumHelpers.teleport(player, event.level, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension), block);
                                }
                                playerCapability.drainMetals(MetalTagEnum.MALATIUM);
                                GoldAndElectrumHelpers.setBlock(null);
                                GoldAndElectrumHelpers.setDimension(null);



                            }
                        }

                        if (playerCapability.getEnhanced()){
                            buffNicrosilDuralumin--;
                            if (buffNicrosilDuralumin == 0) {
                                playerCapability.setEnhanced(false);
                                buffNicrosilDuralumin = -1;
                            }
                        }

                        ModNetwork.sync(playerCapability, player);
                    });
        }
    }
}
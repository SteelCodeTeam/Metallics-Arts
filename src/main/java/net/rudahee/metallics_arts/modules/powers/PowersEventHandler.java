package net.rudahee.metallics_arts.modules.powers;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.modules.powers.helpers.*;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModItems;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber
public class PowersEventHandler {


    @SubscribeEvent
    public static void onLivingEntityDrop(final LivingDropsEvent event) {

        /**
         * ZINC FERUQUIMICO
         */
        if (event.getSource().getEntity() instanceof Player && !(event.getEntity() instanceof Player)) {
            event.getSource().getEntity().getCapability(InvestedCapability.PLAYER_CAP).ifPresent(
                    capability -> {
                        if (capability.isDecanting(MetalsNBTData.ZINC)) {
                            Collection<ItemEntity> drops = event.getDrops();
                            List<ItemEntity> filteredDrops = drops.stream().filter(e -> e.getItem().getItem()!=Items.NETHER_STAR).collect(Collectors.toList());
                            event.getDrops().addAll(filteredDrops);

                        } else if (capability.isStoring(MetalsNBTData.ZINC)) {
                            event.setCanceled(true);
                        }
                    }
            );
        }
    }

    @SubscribeEvent
    public static void onJoinWorld(final PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.getEntity().level.isClientSide) {
            if (event.getEntity() instanceof ServerPlayer) {
                ServerPlayer player = (ServerPlayer) event.getEntity();

                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {
                    if (data.getSpawnDimension() == null) {
                        int[] pos = {player.level.getLevelData().getXSpawn(),player.level.getLevelData().getYSpawn(),player.level.getLevelData().getZSpawn()};
                        String dim = player.level.dimension().location().toString();

                        data.setSpawnPos(pos);
                        data.setSpawnDimension(dim);
                    }
                    if (data.getDeathDimension() == null) {
                        int[] pos = {player.level.getLevelData().getXSpawn(),player.level.getLevelData().getYSpawn(),player.level.getLevelData().getZSpawn()};
                        String dim = player.level.dimension().location().toString();
                        data.setDeathPos(pos);
                        data.setDeathDimension(dim);
                    }
                    //Se necesita hacer que cargue la date del player antes de ejecutar el onjoin world, o no funciona bien
                    if ((data.getAllomanticPowerCount() + data.getFeruchemicPowerCount() == 0) && !data.isInvested()) {
                        List<MetalsNBTData> metals = Arrays.asList(MetalsNBTData.values());
                        Collections.shuffle(metals);
                        List<Integer> typeOfPower = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2); // Leras footjob

                        Collections.shuffle(typeOfPower);

                        if (typeOfPower.get(0) == 0) {
                            Collections.shuffle(metals);
                            data.addAllomanticPower(metals.get(0));
                        } else if (typeOfPower.get(0) == 1) {
                            Collections.shuffle(metals);
                            data.addFeruchemicPower(metals.get(0));
                        } else {
                            Collections.shuffle(metals);
                            data.addAllomanticPower(metals.get(0));
                            Collections.shuffle(metals);
                            data.addFeruchemicPower(metals.get(0));
                        }
                        data.setInvested(true);
                    }

                });
                //Sync cap to client
                ModNetwork.sync(player);
            }
        }
    }

    @SubscribeEvent
    public static void onSetSpawn(final PlayerSetSpawnEvent event) {
        Player playerEntity = event.getEntity();
        if (event.getEntity() instanceof ServerPlayer) {
            playerEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(
                capabilities -> {
                    int[] pos = {(int) playerEntity.position().x,(int) playerEntity.position().y, (int) playerEntity.position().z};
                    String dim = playerEntity.level.dimension().location().toString();
                    if (capabilities.getSpawnPos() != null) {
                        capabilities.setSpawnDimension(dim);
                        capabilities.setSpawnPos(pos);
                    }
                    ModNetwork.sync(capabilities, playerEntity);
            });
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(final LivingDeathEvent event) {
        if (event.getEntity() instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer) event.getEntity();
            player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(playerCapability -> {
                int[] pos = {(int) player.position().x,(int) player.position().y, (int) player.position().z};
                String dim = player.level.dimension().location().toString();

                playerCapability.setDeathDimension(dim);
                playerCapability.setDeathPos(pos);
                for (MetalsNBTData metal:MetalsNBTData.values()) {
                    playerCapability.setBurning(metal,false);
                    playerCapability.setDecanting(metal,false);
                    playerCapability.setStoring(metal,false);
                    playerCapability.setMetalMindEquiped(metal.getGroup(),false);
                }
                ModNetwork.sync(playerCapability, player);
            });
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
                /*entity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {
                    if (data.isBurning(MetalsNBTData.ELECTRUM)) {
                        ClientUtils.toggleBurn(MetalsNBTData.ELECTRUM, data);
                    }

                    if (data.isBurning(MetalsNBTData.GOLD)) {
                        ClientUtils.toggleBurn(MetalsNBTData.GOLD, data);
                    }
                });*/
            }
            ModNetwork.sync(event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(final PlayerEvent.Clone event) {
        if (!event.getEntity().level.isClientSide()) {
            event.getOriginal().revive();
            Player player = event.getEntity();
            player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {
                event.getOriginal().getCapability(InvestedCapability.PLAYER_CAP).ifPresent(oldData -> {
                    if (oldData.isInvested()) {
                        for (MetalsNBTData mt : MetalsNBTData.values()) {
                            if (oldData.hasAllomanticPower(mt)) {
                                data.addAllomanticPower(mt);
                            }
                            if (oldData.hasFeruchemicPower(mt)) {
                                data.addFeruchemicPower(mt);
                            }
                        }
                        if (oldData.getDeathDimension() != null) {
                            data.setDeathPos(oldData.getDeathPos());
                            data.setDeathDimension(oldData.getDeathDimension());
                        } else {
                            data.setDeathPos(oldData.getSpawnPos());
                            data.setDeathDimension(oldData.getSpawnDimension());
                        }
                        data.setSpawnPos(oldData.getSpawnPos());
                        data.setSpawnDimension(oldData.getSpawnDimension());
                    }
                });
            });
            event.getOriginal().getCapability(InvestedCapability.PLAYER_CAP).invalidate();
            ModNetwork.sync(player);
        }
    }



    @SubscribeEvent
    public static void onDamageEvent(final LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof ServerPlayer) {
            ServerPlayer playerEntity = (ServerPlayer) event.getSource().getEntity();
            playerEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(
                playerCapability -> {


                    /*******************************
                     *   DAMAGE WITH - PEWTER -
                     *******************************/
                    if (playerCapability.isBurning(MetalsNBTData.PEWTER)) {
                        float amountDamage = event.getAmount();

                        ItemStack itemInHand = playerEntity.getMainHandItem();

                        if (itemInHand.getItem() == ModItems.DUELING_STAFF.get()) {

                            amountDamage = amountDamage * (((float) itemInHand.getDamageValue() / (float) itemInHand.getMaxDamage()) * 3.2f);
                        }

                        if (itemInHand.getItem() == ModItems.CRISTAL_DAGGER.get()) {
                            if (Math.random() < 0.10d) {
                                amountDamage = amountDamage * 2;
                            }
                        }

                        if (itemInHand.getItem() == ModItems.OBSIDIAN_DAGGER.get()) {
                            if (Math.random() < 0.30d) {
                                event.getEntity().addEffect(new MobEffectInstance(MobEffects.WITHER, 20, 1, true, true, false));
                            }
                        }

                        if (itemInHand.getItem() == ModItems.OBSIDIAN_AXE.get()) {
                            if (Math.random() < 0.50d) {
                                event.getEntity().addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20, 1, true, true, false));
                                event.getEntity().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 1, true, true, false));
                                event.getEntity().addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 20, 1, true, true, false));
                                event.getEntity().addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 2, true, true, false));

                            }
                        }

                        if (playerCapability.getEnhanced()) {
                            if (itemInHand.getItem() == ModItems.KOLOSS_BLADE.get()) {
                                event.getEntity().setHealth(2f);
                                amountDamage = 0;
                            }
                        }
                        event.setAmount(amountDamage);
                    }

                    /*******************************
                     *   DAMAGE WITH - CHROMIUM -
                     *******************************/
                    if (playerCapability.isBurning(MetalsNBTData.CHROMIUM)) {
                        if (event.getEntity() instanceof Player) {
                            ChromiumAndNicrosilHelpers.drainMetalChromium((Player) event.getEntity());
                        }
                    }

                    /*******************************
                     *   DAMAGE WITH - ZINC -
                     *******************************/
                    if (playerCapability.isBurning(MetalsNBTData.ZINC)) {
                        if (event.getEntity() instanceof Player) {
                            //ZincAndBrassHelpers.drawSaturatedScreen((Player) event.getEntity());
                        }
                    }

                    /*******************************
                     *   DAMAGE WITH - MALATIUM -
                     *******************************/
                    if (playerCapability.isBurning(MetalsNBTData.MALATIUM)) {
                        if (event.getEntity() instanceof Player) {
                            GoldAndElectrumHelpers.takeDeathPosToObjetive((Player) event.getEntity());
                        }
                    }

                    /*******************************
                     *   DAMAGE WITH - BRASS - FERUCHEMIC
                     *******************************/
                    if (playerCapability.isDecanting(MetalsNBTData.BRASS)) {
                        ZincAndBrassHelpers.addFireAspectToPlayer(event.getEntity(),4);
                    }

                    /*******************************
                     *   DAMAGE WITH - ZINC - FERUCHEMIC
                     *******************************/
                    //if (playerCapability.isDecanting(MetalsNBTData.ZINC)) {
                   //     ZincAndBrassHelpers.addLootToEnemy(event.getEntity(),0.6);
                   // } else if (playerCapability.isStoring(MetalsNBTData.ZINC)) {
                    //    ZincAndBrassHelpers.removeLootToEnemy(event.getEntity(),0.6);
                   // }
                    /********************************
                     * DAMAGE WITH NICROSIL
                     *******************************/
                    if (playerCapability.isBurning(MetalsNBTData.NICROSIL)) {
                        if (event.getEntity() instanceof Player) {
                            ChromiumAndNicrosilHelpers.changeNBTinTargetForEnhanced((Player) event.getEntity());
                        }
                    }
            });
        }

        Entity source = event.getSource().getEntity(); //fuente
        Entity target = event.getEntity(); //target

        if (target instanceof Player) {
            target.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(targetCapability -> {
                if (targetCapability.isBurning(MetalsNBTData.ATIUM) ){
                    if (source instanceof Player) {
                        source.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(sourceCapability -> {
                            event.setAmount(AtiumAndMalatiumHelpers.getCalculateComplexDamage(targetCapability,sourceCapability,event.getAmount()));
                        });//evasion jugador jugador
                    } else {
                        event.setAmount(AtiumAndMalatiumHelpers.getCalculateSimpleDamage(targetCapability,event.getAmount()));
                        //evasion a otras fuentes de daño
                    }
                }
            });
        }

        target.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(
                targetCapability -> {
                    if (targetCapability.isDecanting(MetalsNBTData.BRASS)) {
                        if (event.getSource().equals(DamageSource.FREEZE)) {
                            event.setCanceled(true);
                        }
                    }
                });
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
                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(
                    playerCapability -> {
                        if (playerCapability.isInvested()) {
                            if (player instanceof ServerPlayer) {
                                playerCapability.tickAllomancyBurningMetals((ServerPlayer) player);
                            }


                            if (playerCapability.isBurning(MetalsNBTData.DURALUMIN)) {
                                if ((playerCapability.getAllomanticAmount(MetalsNBTData.DURALUMIN)>(MetalsNBTData.DURALUMIN.getMaxAllomanticTicksStorage()*0.88))
                                        || (buffNicrosilDuralumin != -1)){

                                    if (buffNicrosilDuralumin == -1) {
                                        playerCapability.setEnhanced(true);
                                        buffNicrosilDuralumin = MetalsNBTData.DURALUMIN.getMaxAllomanticTicksStorage();
                                    }
                                    for (MetalsNBTData m : MetalsNBTData.values()) {
                                        if (playerCapability.isBurning(m) && !playerCapability.containsInListMetalBuff(m)){
                                            playerCapability.addListMetalBuff(m);
                                        }
                                    }
                                } else {
                                    playerCapability.setBurning(MetalsNBTData.DURALUMIN,false);
                                }
                            } else if (playerCapability.getEnhanced()) {
                                if (buffNicrosilDuralumin == -1) {
                                    buffNicrosilDuralumin = MetalsNBTData.DURALUMIN.getMaxAllomanticTicksStorage();
                                }
                                for (MetalsNBTData m : MetalsNBTData.values()) {
                                    if (playerCapability.isBurning(m) && !playerCapability.containsInListMetalBuff(m)){
                                        playerCapability.addListMetalBuff(m);
                                    }
                                }

                            } else {
                                if (!playerCapability.getListMetalBuff().isEmpty()) {
                                    for (MetalsNBTData m: playerCapability.getListMetalBuff()) {
                                        playerCapability.drainMetals(m);
                                    }
                                    playerCapability.clearListMetalBuff();
                                }
                            }

                            if ( !(playerCapability.isBurning(MetalsNBTData.PEWTER) || playerCapability.isDecanting(MetalsNBTData.PEWTER))
                                    && (player.getMainHandItem().getItem() == ModItems.KOLOSS_BLADE.get() || player.getOffhandItem().getItem() == ModItems.KOLOSS_BLADE.get())) {

                                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 2, true, true, false));
                                player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 10, 2, true, true, false));
                                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 10, 2, true, true, false));

                            }

                            /************************
                             * BRONZE FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalsNBTData.BRONZE)) {
                                BlockPos negative = new BlockPos(player.position()).offset(-x -4, -y -4, -z -4);
                                BlockPos positive = new BlockPos(player.position()).offset(x +4, y + 24 , z +4);

                                CopperAndBronzeHelpers.DontSpawnPhantoms(player, new AABB(negative, positive), event.level);
                            } else if (playerCapability.isStoring(MetalsNBTData.BRONZE)) {
                                if (actualTick == 240) {
                                    CopperAndBronzeHelpers.SpawnPhamtonsWithFireResistance(player, world);
                                }
                            }
                            /************************
                             * BRASS FERUCHEMIC
                             ************************/
                            if (playerCapability.isStoring(MetalsNBTData.BRASS)) {
                                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 40, 1, true, false));

                                if (world.getBiome(player.getOnPos()).is(Tags.Biomes.IS_COLD) || (world.getBiome(player.getOnPos()).is(Biomes.DESERT) && world.isNight())) {
                                    ZincAndBrassHelpers.addFrozenTicks(player);
                                }

                            } else if (playerCapability.isDecanting(MetalsNBTData.BRASS)) {
                                if (world.getBiome(player.getOnPos()).is(Biomes.DESERT) && world.isDay()) {
                                    ZincAndBrassHelpers.addBurnBodyTicks(player);
                                } else if (world.getBiome(player.getOnPos()).is(Tags.Biomes.IS_HOT)) {
                                    ZincAndBrassHelpers.addBurnBodyTicks(player);
                                }
                            }
                            /************************
                             * GOLD FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalsNBTData.GOLD)) {
                                if (actualTick == 30 || actualTick == 60 || actualTick == 90 || actualTick == 120 || actualTick == 150 || actualTick == 180 || actualTick == 210 || actualTick == 240) {
                                    GoldAndElectrumHelpers.addHealth(player,1);
                                }
                            } else if (playerCapability.isStoring(MetalsNBTData.GOLD)) {
                                if (playerCapability.isStoring(MetalsNBTData.ELECTRUM)) {
                                    playerCapability.setStoring(MetalsNBTData.ELECTRUM, false);
                                }

                                if (actualTick == 30 || actualTick == 60 || actualTick == 90 || actualTick == 120 || actualTick == 150 || actualTick == 180 || actualTick == 210 || actualTick == 240) {
                                    GoldAndElectrumHelpers.removeHealth(player,1);
                                }
                            }
                            /************************
                             * ELECTRUM FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalsNBTData.ELECTRUM)) {
                                GoldAndElectrumHelpers.addHearts(player,30);
                            } else if (playerCapability.isStoring(MetalsNBTData.ELECTRUM)) {
                                if (playerCapability.isStoring(MetalsNBTData.GOLD)) {
                                    playerCapability.setStoring(MetalsNBTData.GOLD, false);
                                }
                                GoldAndElectrumHelpers.removeHearts(player,10);
                            }
                            /************************
                             * TIN FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalsNBTData.TIN)) {
                                PewterAndTinHelpers.addFecruchemicVision(player);
                            } else if (playerCapability.isStoring(MetalsNBTData.TIN)) {
                                PewterAndTinHelpers.removeFeruchemicVision(player);
                            }
                            /************************
                             * PEWTER FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalsNBTData.PEWTER)) {
                                PewterAndTinHelpers.decantPewterEffectsFeruchemic(player);
                            } else if (playerCapability.isStoring(MetalsNBTData.PEWTER)) {
                                PewterAndTinHelpers.storePewterEffectsFeruchemic(player);
                            }
                            /************************
                             * STEEL FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalsNBTData.STEEL)) {
                                IronAndSteelHelpers.addSpeed(player,3);
                            } else if (playerCapability.isStoring(MetalsNBTData.STEEL)) {
                                IronAndSteelHelpers.removeSpeed(player,3);
                            }
                            /************************
                             * IRON FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalsNBTData.IRON)) {
                                IronAndSteelHelpers.increaseWeight(player);
                            } else if (playerCapability.isStoring(MetalsNBTData.IRON)) {
                                IronAndSteelHelpers.reduceWeight(player);
                            }
                            /************************
                             * CADMIUM FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalsNBTData.CADMIUM)) {
                                if (actualTick == 30 || actualTick == 60 || actualTick == 90 || actualTick == 120 || actualTick == 150 || actualTick == 180 || actualTick == 210 || actualTick == 240) {
                                    BendalloyAndCadmiunHelpers.throwBreathEffect(player, 10);
                                }
                            } else if (playerCapability.isStoring(MetalsNBTData.CADMIUM)) {
                                //if (actualTick == 30 || actualTick == 60 || actualTick == 90 || actualTick == 120 actualTick == 150 || actualTick == 180 || actualTick == 210 || actualTick == 240) {
                                    BendalloyAndCadmiunHelpers.drowningEffect(player,actualTick);
                                //}
                            }
                            /************************
                             * BENDALLOY FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalsNBTData.BENDALLOY)) {
                                if (actualTick == 30 || actualTick == 60 || actualTick == 90 || actualTick == 120 || actualTick == 150 || actualTick == 180 || actualTick == 210 || actualTick == 240) {
                                    BendalloyAndCadmiunHelpers.addFoodLevel(player,1);
                                }
                            } else if (playerCapability.isStoring(MetalsNBTData.BENDALLOY)) {
                                if (actualTick == 30 || actualTick == 60 || actualTick == 90 || actualTick == 120 || actualTick == 150 || actualTick == 180 || actualTick == 210 || actualTick == 240) {
                                    BendalloyAndCadmiunHelpers.removeFoodLevel(player,1);
                                }
                            }
                            /************************
                             * DURALUMIN FERUCHEMIC
                             ************************/
                            ResourceKey<Biome> biome = world.getBiome(player.getOnPos()).unwrapKey().get();

                            if (playerCapability.isDecanting(MetalsNBTData.DURALUMIN)) {
                                if (actualTick == 30 || actualTick == 60 || actualTick == 90 || actualTick == 120 || actualTick == 150 || actualTick == 180 || actualTick == 210 || actualTick == 240) {
                                    DuraluminAndAluminumHelpers.duraluminDecantingMobEffects(player,biome);
                                }
                            } else if (playerCapability.isStoring(MetalsNBTData.DURALUMIN)) {
                                if (actualTick == 30 || actualTick == 60 || actualTick == 90 || actualTick == 120 || actualTick == 150 || actualTick == 180 || actualTick == 210 || actualTick == 240) {
                                    DuraluminAndAluminumHelpers.duraluminStoringMobEffects(player, biome);

                                }
                            }
                            /************************
                             * CHROMIUM FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalsNBTData.CHROMIUM)){
                                ChromiumAndNicrosilHelpers.goodLuck(player);
                                //player.getLuck()
                            } else if (playerCapability.isStoring(MetalsNBTData.CHROMIUM)){
                                ChromiumAndNicrosilHelpers.badLuck(player, actualTick == 80 || actualTick == 160 || actualTick == 240);
                            }
                            /************************
                             * ATIUM FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalsNBTData.ATIUM)){
                                AtiumAndMalatiumHelpers.decantAtium(player);
                            } else if (playerCapability.isStoring(MetalsNBTData.ATIUM)){
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
                            if (playerCapability.isBurning(MetalsNBTData.ZINC) && playerCapability.isBurning(MetalsNBTData.LERASIUM)){
                                BlockPos negative;
                                BlockPos positive;
                                if (event.level instanceof ServerLevel) {
                                    if (playerCapability.getEnhanced()){
                                        negative = new BlockPos(player.position()).offset(-x - 6, -y - 6, -z - 6);
                                        positive = new BlockPos(player.position()).offset(x + 6, y + 6 , z + 6);
                                    } else {
                                        negative = new BlockPos(player.position()).offset(-x - 4, -y - 4, -z - 4);
                                        positive = new BlockPos(player.position()).offset(x + 4, y + 4 , z + 4);
                                    }
                                    ZincAndBrassHelpers.angryEntitiesWithLerasium
                                            (player,event.level,new AABB(negative, positive),playerCapability.getEnhanced());
                                }
                            }
                            /************************
                             * ZINC POWERS
                             ************************/
                            if (playerCapability.isBurning(MetalsNBTData.BRASS)&&playerCapability.isBurning(MetalsNBTData.LERASIUM)){
                                BlockPos negative;
                                BlockPos positive;
                                if (event.level instanceof ServerLevel) {
                                    if (playerCapability.getEnhanced()){
                                        negative = new BlockPos(player.position()).offset(-x - 6, -y - 6, -z - 6);
                                        positive = new BlockPos(player.position()).offset(x + 6, y + 6 , z + 6);
                                    } else {
                                        negative = new BlockPos(player.position()).offset(-x - 4, -y - 4, -z - 4);
                                        positive = new BlockPos(player.position()).offset(x + 4, y + 4 , z + 4);
                                    }

                                    ZincAndBrassHelpers.happyEntitiesWithLerasium(player,event.level,new AABB(negative, positive),playerCapability.getEnhanced());
                                }
                            }
                            /************************
                             * BENDALLOY POWERS
                             ************************/
                            if (playerCapability.isBurning(MetalsNBTData.BENDALLOY)) {
                                if (!playerCapability.isBurning(MetalsNBTData.CADMIUM)) {
                                    BlockPos negative;
                                    BlockPos positive;
                                    /** ENHANCED */
                                    if (playerCapability.getEnhanced()) {
                                        BendalloyAndCadmiunHelpers.AddAiSteepsEnhanced(player);
                                        if (event.level instanceof ServerLevel) {
                                            if (playerCapability.isBurning(MetalsNBTData.LERASIUM)) {
                                                negative = new BlockPos(player.position()).offset(-x - 9, -y - 9, -z - 9);
                                                positive = new BlockPos(player.position()).offset(x + 9, y + 9 , z + 9);
                                            } else {
                                                negative = new BlockPos(player.position()).offset(-x - 6, -y - 6, -z - 6);
                                                positive = new BlockPos(player.position()).offset(x + 6, y + 6 , z + 6);
                                            }
                                            // Ticks extra in random blocks, tile entities and entities.
                                            BendalloyAndCadmiunHelpers.BendalloyMobEffects(player, event.level, new AABB(negative, positive), negative, positive, true);
                                        }
                                    }
                                    /** NORMAL */
                                    else {
                                        BendalloyAndCadmiunHelpers.AddAiSteeps(player);
                                        if (event.level instanceof ServerLevel) {
                                            if (playerCapability.isBurning(MetalsNBTData.LERASIUM)) {
                                                negative = new BlockPos(player.position()).offset(-x - 5, -y - 5, -z - 5);
                                                positive = new BlockPos(player.position()).offset(x + 5, y + 5 , z + 5);
                                            } else {
                                                negative = new BlockPos(player.position()).offset(-x - 3, -y - 3, -z - 3);
                                                positive = new BlockPos(player.position()).offset(x + 3, y + 3 , z + 3);
                                            }
                                            // Ticks extra in random blocks, tile entities and entities.
                                            BendalloyAndCadmiunHelpers.BendalloyMobEffects(player, event.level, new AABB(negative, positive), negative, positive, false);
                                        }
                                    }
                                }
                            }
                            /************************
                             * CHROMIUM ENHANCED
                             ************************/
                            if (playerCapability.isBurning(MetalsNBTData.CHROMIUM) && playerCapability.getEnhanced()) {
                                if (event.level instanceof ServerLevel) {
                                    BlockPos negative;
                                    BlockPos positive;
                                    if (playerCapability.isBurning(MetalsNBTData.LERASIUM)) {
                                        negative = new BlockPos(player.position()).offset(-x - 6, -y - 6, -z - 6);
                                        positive = new BlockPos(player.position()).offset(x + 6, y + 6 , z + 6);
                                    } else {
                                        negative = new BlockPos(player.position()).offset(-x - 3, -y - 3, -z - 3);
                                        positive = new BlockPos(player.position()).offset(x + 3, y + 3 , z + 3);
                                    }
                                    // Ticks extra in random blocks, tile entities and entities.
                                    ChromiumAndNicrosilHelpers.drainMetalCloudChromium((ServerLevel) event.level, new AABB(negative, positive));
                                }
                            }
                            /************************
                             * CADMIUM POWERS
                             ************************/
                            if (playerCapability.isBurning(MetalsNBTData.CADMIUM)) {
                                if (!playerCapability.isBurning(MetalsNBTData.BENDALLOY)) {
                                    BlockPos negative;
                                    BlockPos positive;
                                    int amplifier;
                                    int time;
                                    /** ENHANCED */
                                    if (playerCapability.getEnhanced()) {
                                        if (playerCapability.isBurning(MetalsNBTData.LERASIUM)) {
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
                                        if (playerCapability.isBurning(MetalsNBTData.LERASIUM)) {
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
                        if (playerCapability.isBurning(MetalsNBTData.CADMIUM) && playerCapability.isBurning(MetalsNBTData.BENDALLOY)) {
                            if (playerCapability.getEnhanced() && playerCapability.isBurning(MetalsNBTData.LERASIUM)) {
                                playerCapability.drainMetals(MetalsNBTData.LERASIUM);
                             }
                        }
                        /************************
                         * PEWTER POWERS
                         ************************/
                        if (playerCapability.isBurning(MetalsNBTData.PEWTER)) {
                            PewterAndTinHelpers.addPewterEffects(player,
                                    playerCapability.isBurning(MetalsNBTData.LERASIUM),
                                    playerCapability.getEnhanced());
                        }
                        /************************
                         * TIN POWERS
                         ************************/
                        if (playerCapability.isBurning(MetalsNBTData.TIN)) {
                            if (playerCapability.getEnhanced()) {
                                PewterAndTinHelpers.addTinEffectsEnhanced(player);
                            } else {
                                PewterAndTinHelpers.addTinEffects(player);
                            }
                        }
                        /************************
                         * BRONZE POWERS
                         ************************/
                        if (playerCapability.isBurning(MetalsNBTData.BRONZE) && !playerCapability.getEnhanced()) {
                            if (event.level instanceof ServerLevel) {
                                BlockPos negative;
                                BlockPos positive;
                                if (playerCapability.isBurning(MetalsNBTData.LERASIUM)) {
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
                        } else if (playerCapability.isBurning(MetalsNBTData.BRONZE) && playerCapability.getEnhanced()) {
                            if (event.level instanceof ServerLevel) {
                                BlockPos negative;
                                BlockPos positive;
                                if (playerCapability.isBurning(MetalsNBTData.LERASIUM)){
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
                        if (playerCapability.isBurning(MetalsNBTData.COPPER) && !playerCapability.getEnhanced()) {
                            if (event.level instanceof ServerLevel) {
                                BlockPos negative;
                                BlockPos positive;
                                if (playerCapability.isBurning(MetalsNBTData.LERASIUM)){
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
                        } else if (playerCapability.isBurning(MetalsNBTData.COPPER) && playerCapability.getEnhanced()) {
                            if (event.level instanceof ServerLevel) {
                                BlockPos negative;
                                BlockPos positive;
                                if (playerCapability.isBurning(MetalsNBTData.LERASIUM)) {
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
                        if (playerCapability.isBurning(MetalsNBTData.ETTMETAL)){
                            LerasiumAndEttmetalHelpers.ettmetalExplotion(event.level,player);
                        }
                        /************************
                         * ALUMINUM POWER
                         ************************/
                        if (playerCapability.isBurning(MetalsNBTData.ALUMINUM)) {
                            DuraluminAndAluminumHelpers.drainAndCleanEffects(player,playerCapability);

                        }
                        /************************
                         * ELECTRUM POWER (ENHANCED)
                         ************************/
                        if (playerCapability.isBurning(MetalsNBTData.ELECTRUM) &&  playerCapability.getEnhanced()) {
                            BlockPos block = null;
                            String dimension = null;
                            if (playerCapability.getSpawnPos() != null && playerCapability.getSpawnDimension() != null) {
                                block = new BlockPos(playerCapability.getSpawnPos()[0], playerCapability.getSpawnPos()[1], playerCapability.getSpawnPos()[2]);
                                dimension = playerCapability.getSpawnDimension();
                            } else {
                                block = new BlockPos(world.getLevelData().getXSpawn(),world.getLevelData().getYSpawn(), world.getLevelData().getZSpawn());
                                dimension = Level.OVERWORLD.location().toString();
                            }
                            if (playerCapability.isBurning(MetalsNBTData.LERASIUM)){
                                BlockPos negative;
                                BlockPos positive;
                                negative = new BlockPos(player.position()).offset(-x - 3, -y - 3, -z - 3);
                                positive = new BlockPos(player.position()).offset(x + 3, y + 3, z + 3);
                                GoldAndElectrumHelpers.multiTeleport(player, new AABB(negative, positive), event.level, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension),block);
                                playerCapability.drainMetals(MetalsNBTData.LERASIUM);
                            } else {
                                GoldAndElectrumHelpers.teleport(player, event.level, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension), block);
                            }
                            playerCapability.drainMetals(MetalsNBTData.ELECTRUM);
                        }
                        /************************
                         * GOLD POWER (ENHANCED)
                         ************************/
                        if (playerCapability.isBurning(MetalsNBTData.GOLD) && playerCapability.getEnhanced()) {
                            BlockPos block = null;
                            String dimension = null;
                            if (playerCapability.getDeathPos() != null && playerCapability.getDeathDimension() != null) {
                                block = new BlockPos(playerCapability.getDeathPos()[0], playerCapability.getDeathPos()[1], playerCapability.getDeathPos()[2]);
                                dimension = playerCapability.getDeathDimension();
                            } else {
                                block = new BlockPos(world.getLevelData().getXSpawn(),world.getLevelData().getYSpawn(), world.getLevelData().getZSpawn());
                                dimension = Level.OVERWORLD.location().toString();
                            }
                            if (playerCapability.isBurning(MetalsNBTData.LERASIUM)){
                                BlockPos negative;
                                BlockPos positive;
                                negative = new BlockPos(player.position()).offset(-x - 3, -y - 3, -z - 3);
                                positive = new BlockPos(player.position()).offset(x + 3, y + 3, z + 3);
                                GoldAndElectrumHelpers.multiTeleport(player, new AABB(negative, positive), event.level, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension),block);
                                playerCapability.drainMetals(MetalsNBTData.LERASIUM);
                            } else {
                                GoldAndElectrumHelpers.teleport(player, event.level, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension), block);
                            }
                            playerCapability.drainMetals(MetalsNBTData.GOLD);
                        }
                        /************************
                         * MALATIUM POWER (ENHANCED)
                         ************************/
                        if (playerCapability.isBurning(MetalsNBTData.MALATIUM) && playerCapability.getEnhanced()) {
                            BlockPos block = null;
                            String dimension = null;
                            if (GoldAndElectrumHelpers.getBlock() != null && GoldAndElectrumHelpers.getDimension() != null) {
                                block = GoldAndElectrumHelpers.getBlock();
                                dimension = GoldAndElectrumHelpers.getDimension();
                                if (playerCapability.isBurning(MetalsNBTData.LERASIUM)) {
                                    BlockPos negative;
                                    BlockPos positive;
                                    negative = new BlockPos(player.position()).offset(-x - 3, -y - 3, -z - 3);
                                    positive = new BlockPos(player.position()).offset(x + 3, y + 3, z + 3);
                                    GoldAndElectrumHelpers.multiTeleport(player, new AABB(negative, positive), event.level, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension),block);
                                    playerCapability.drainMetals(MetalsNBTData.LERASIUM);
                                } else {
                                    GoldAndElectrumHelpers.teleport(player, event.level, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension), block);
                                }
                                playerCapability.drainMetals(MetalsNBTData.MALATIUM);
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

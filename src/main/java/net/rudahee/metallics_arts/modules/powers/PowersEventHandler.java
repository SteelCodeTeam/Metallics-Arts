package net.rudahee.metallics_arts.modules.powers;

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
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.modules.powers.helpers.*;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber
public class PowersEventHandler {


    @SubscribeEvent
    public static void onJoinWorld(final PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.getEntity().level.isClientSide) {
            if (event.getEntity() instanceof ServerPlayer) {
                ServerPlayer player = (ServerPlayer) event.getEntity();

                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {
                    if (data.getSpawnDimension() == null) {
                        int[] pos = {player.level.getLevelData().getXSpawn(),player.level.getLevelData().getYSpawn(),player.level.getLevelData().getZSpawn()};
                        String dim = player.level.dimension().registry().getNamespace();

                        data.setSpawnPos(pos);
                        data.setSpawnDimension(dim);
                    }
                    if (data.getDeathDimension() == null) {
                        int[] pos = {player.level.getLevelData().getXSpawn(),player.level.getLevelData().getYSpawn(),player.level.getLevelData().getZSpawn()};
                        String dim = player.level.dimension().registry().getNamespace();

                        data.setDeathPos(pos);
                        data.setDeathDimension(dim);
                    }

                    if (data.getAllomanticPowerCount() + data.getFeruchemicPowerCount() == 0) {
                        List<MetalsNBTData> metals = Arrays.asList(MetalsNBTData.values());
                        Collections.shuffle(metals);

                        List<Integer> typeOfPower = Arrays.asList(new Integer[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2}); // Leras footjob

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
                    }
                        data.setInvested(true);
                });
                //Sync cap to client
                ModNetwork.sync(event.getEntity());
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
                    String dim = playerEntity.level.dimension().registry().getNamespace();
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
                String dim = player.level.dimension().registry().getNamespace();
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

                        /* PEWTER + DURALUMIN */
                        if (playerCapability.isBurning(MetalsNBTData.DURALUMIN)) {
                            amountDamage = PewterAndTinHelpers.getDamageWithIncrement(amountDamage);
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
                        if (event.getEntity() instanceof Player) {
                            ChromiumAndNicrosilHelpers.drainMetalChromium((Player) event.getEntity());
                        }
                    }

                    /*******************************
                     *   DAMAGE WITH - ZINC -
                     *******************************/
                    if (playerCapability.isBurning(MetalsNBTData.ZINC)) {
                        if (event.getEntity() instanceof Player) {
                            ZincAndBrassHelpers.drawSaturatedScreen((Player) event.getEntity());
                        }
                    }

                    /*******************************
                     *   DAMAGE IF PLAYER IS BURN ATIUM
                     *******************************/

                    if (event.getEntity() instanceof Player) {
                        event.setAmount(AtiumAndMalatiumHelpers.getDamageWhenUseAtium(playerEntity, (Player) event.getEntity(), event.getAmount()));
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
                    if (playerCapability.isDecanting(MetalsNBTData.ZINC)) {
                        ZincAndBrassHelpers.addLootToEnemy(event.getEntity(),0.6);
                    } else if (playerCapability.isStoring(MetalsNBTData.ZINC)) {
                        ZincAndBrassHelpers.removeLootToEnemy(event.getEntity(),0.6);
                    }
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

    }

    public static int ticks = 0;

    public static int x = 8;
    public static int y = 8;
    public static int z = 8;

    public static int actualTick = 0;

    public static boolean restoreHealth = false;

    private static Player newPlayer = null;
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
                                restoreHealth = true;
                            } else if (playerCapability.isStoring(MetalsNBTData.ELECTRUM)) {
                                if (playerCapability.isStoring(MetalsNBTData.GOLD)) {
                                    playerCapability.setStoring(MetalsNBTData.GOLD, false);
                                }
                                GoldAndElectrumHelpers.removeHearts(player,10);
                                restoreHealth = true;
                            } else if (restoreHealth) {
                                GoldAndElectrumHelpers.restoreHearts(player);
                                restoreHealth = false;
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
                                if (actualTick==80 || actualTick == 160 || actualTick == 240)
                                    ChromiumAndNicrosilHelpers.badLuck(player,true);
                                else
                                    ChromiumAndNicrosilHelpers.badLuck(player,false);
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
                                    if (playerCapability.isBurning(MetalsNBTData.DURALUMIN) || playerCapability.getExternalEnhanced()){
                                        negative = new BlockPos(player.position()).offset(-x - 6, -y - 6, -z - 6);
                                        positive = new BlockPos(player.position()).offset(x + 6, y + 6 , z + 6);
                                        if (!playerCapability.getExternalEnhanced()) {
                                            playerCapability.drainMetals(MetalsNBTData.LERASIUM,MetalsNBTData.DURALUMIN,MetalsNBTData.ZINC);
                                        } else {
                                            playerCapability.drainMetals(MetalsNBTData.LERASIUM,MetalsNBTData.ZINC);
                                        }                                    } else {
                                        negative = new BlockPos(player.position()).offset(-x - 4, -y - 4, -z - 4);
                                        positive = new BlockPos(player.position()).offset(x + 4, y + 4 , z + 4);
                                    }
                                    ZincAndBrassHelpers.angryEntitiesWithLerasium
                                            (player,event.level,new AABB(negative, positive),playerCapability.isBurning(MetalsNBTData.DURALUMIN));
                                }
                            }
                            /************************
                             * ZINC POWERS
                             ************************/
                            if (playerCapability.isBurning(MetalsNBTData.BRASS)&&playerCapability.isBurning(MetalsNBTData.LERASIUM)){
                                BlockPos negative;
                                BlockPos positive;
                                if (event.level instanceof ServerLevel) {
                                    if (playerCapability.isBurning(MetalsNBTData.DURALUMIN) || playerCapability.getExternalEnhanced()){
                                        negative = new BlockPos(player.position()).offset(-x - 6, -y - 6, -z - 6);
                                        positive = new BlockPos(player.position()).offset(x + 6, y + 6 , z + 6);

                                        if (!playerCapability.getExternalEnhanced()) {
                                            playerCapability.drainMetals(MetalsNBTData.LERASIUM,MetalsNBTData.DURALUMIN,MetalsNBTData.BRASS);
                                        } else {
                                            playerCapability.drainMetals(MetalsNBTData.LERASIUM,MetalsNBTData.BRASS);
                                        }
                                    } else {
                                        negative = new BlockPos(player.position()).offset(-x - 4, -y - 4, -z - 4);
                                        positive = new BlockPos(player.position()).offset(x + 4, y + 4 , z + 4);
                                    }

                                    ZincAndBrassHelpers.happyEntitiesWithLerasium(player,event.level,new AABB(negative, positive),playerCapability.isBurning(MetalsNBTData.DURALUMIN));
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
                                    if (playerCapability.isBurning(MetalsNBTData.DURALUMIN) || playerCapability.getExternalEnhanced()) {
                                        BendalloyAndCadmiunHelpers.AddAiSteeps(player);
                                        if (event.level instanceof ServerLevel) {
                                            if (playerCapability.isBurning(MetalsNBTData.LERASIUM)) {
                                                negative = new BlockPos(player.position()).offset(-x - 9, -y - 9, -z - 9);
                                                positive = new BlockPos(player.position()).offset(x + 9, y + 9 , z + 9);
                                                playerCapability.drainMetals(MetalsNBTData.LERASIUM);
                                            } else {
                                                negative = new BlockPos(player.position()).offset(-x - 6, -y - 6, -z - 6);
                                                positive = new BlockPos(player.position()).offset(x + 6, y + 6 , z + 6);
                                            }
                                            // Ticks extra in random blocks, tile entities and entities.
                                            BendalloyAndCadmiunHelpers.BendalloyMobEffectsEnhanced(player, event.level,new AABB(negative, positive), negative, positive);
                                        }
                                        if (!playerCapability.getExternalEnhanced()) {
                                            playerCapability.drainMetals(MetalsNBTData.BENDALLOY, MetalsNBTData.DURALUMIN);
                                        } else {
                                            playerCapability.drainMetals(MetalsNBTData.BENDALLOY);
                                        }
                                    }
                                    /** NORMAL */
                                    else {
                                        BendalloyAndCadmiunHelpers.AddAiSteepsEnhanced(player);
                                        if (event.level instanceof ServerLevel) {
                                            if (playerCapability.isBurning(MetalsNBTData.LERASIUM)) {
                                                negative = new BlockPos(player.position()).offset(-x - 4, -y - 4, -z - 4);
                                                positive = new BlockPos(player.position()).offset(x + 4, y + 4 , z + 4);
                                            } else {
                                                negative = new BlockPos(player.position()).offset(-x - 2, -y - 2, -z - 2);
                                                positive = new BlockPos(player.position()).offset(x + 2, y + 2 , z + 2);
                                            }
                                            // Ticks extra in random blocks, tile entities and entities.
                                            BendalloyAndCadmiunHelpers.BendalloyMobEffects(player, event.level,new AABB(negative, positive), negative, positive);
                                        }
                                    }
                                }
                            }
                            /************************
                             * CHROMIUM ENHANCED
                             ************************/
                            if (playerCapability.isBurning(MetalsNBTData.CHROMIUM) && (playerCapability.isBurning(MetalsNBTData.DURALUMIN) || playerCapability.getExternalEnhanced())) {
                                if (event.level instanceof ServerLevel) {
                                    BlockPos negative;
                                    BlockPos positive;
                                    if (playerCapability.isBurning(MetalsNBTData.LERASIUM)) {
                                        negative = new BlockPos(player.position()).offset(-x - 6, -y - 6, -z - 6);
                                        positive = new BlockPos(player.position()).offset(x + 6, y + 6 , z + 6);
                                        playerCapability.drainMetals(MetalsNBTData.LERASIUM);
                                    } else {
                                        negative = new BlockPos(player.position()).offset(-x - 3, -y - 3, -z - 3);
                                        positive = new BlockPos(player.position()).offset(x + 3, y + 3 , z + 3);
                                    }
                                    // Ticks extra in random blocks, tile entities and entities.
                                    ChromiumAndNicrosilHelpers.drainMetalCloudChromium((ServerLevel) event.level, new AABB(negative, positive));
                                    if (!playerCapability.getExternalEnhanced()) {
                                        playerCapability.drainMetals(MetalsNBTData.CHROMIUM, MetalsNBTData.DURALUMIN);
                                    } else {
                                        playerCapability.drainMetals(MetalsNBTData.CHROMIUM);
                                    }
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
                                    if (playerCapability.isBurning(MetalsNBTData.DURALUMIN) || playerCapability.getExternalEnhanced()) {
                                        if (playerCapability.isBurning(MetalsNBTData.LERASIUM)) {
                                            negative = new BlockPos(player.position()).offset(-x -8, -y -8, -z -8);
                                            positive = new BlockPos(player.position()).offset(x+8 , y+8, z+8);
                                            amplifier = 100;
                                            time = 60;
                                            playerCapability.drainMetals(MetalsNBTData.LERASIUM);
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

                                        if (!playerCapability.getExternalEnhanced()) {
                                            playerCapability.drainMetals(MetalsNBTData.CADMIUM, MetalsNBTData.DURALUMIN);
                                        } else {
                                            playerCapability.drainMetals(MetalsNBTData.CADMIUM);
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
                            if (playerCapability.isBurning(MetalsNBTData.DURALUMIN) || playerCapability.getExternalEnhanced()) {
                                if (playerCapability.isBurning(MetalsNBTData.LERASIUM)) {
                                    playerCapability.drainMetals(MetalsNBTData.LERASIUM);
                                }

                                if (!playerCapability.getExternalEnhanced()) {
                                    playerCapability.drainMetals(MetalsNBTData.CADMIUM, MetalsNBTData.BENDALLOY, MetalsNBTData.DURALUMIN);
                                } else {
                                    playerCapability.drainMetals(MetalsNBTData.CADMIUM, MetalsNBTData.BENDALLOY);
                                }
                             }
                        }
                        /************************
                         * PEWTER POWERS
                         ************************/
                        if (playerCapability.isBurning(MetalsNBTData.PEWTER)) {
                            /***
                             * ENHANCED
                             */
                            if (playerCapability.isBurning(MetalsNBTData.LERASIUM)) {
                                PewterAndTinHelpers.addPewterEffectsEnhanced(player);
                            }

                            if (playerCapability.isBurning(MetalsNBTData.DURALUMIN) || playerCapability.getExternalEnhanced()) {
                                if (!playerCapability.getExternalEnhanced()) {
                                    playerCapability.drainMetals(MetalsNBTData.PEWTER, MetalsNBTData.DURALUMIN);
                                } else {
                                    playerCapability.drainMetals(MetalsNBTData.PEWTER);
                                }
                                if (playerCapability.isBurning(MetalsNBTData.LERASIUM)) {
                                    playerCapability.drainMetals(MetalsNBTData.LERASIUM);
                                }
                            } else {
                                PewterAndTinHelpers.addPewterEffects(player);
                            }
                        }
                        /************************
                         * TIN POWERS
                         ************************/
                        if (playerCapability.isBurning(MetalsNBTData.TIN)) {
                            /***
                             * ENHANCED
                             */
                            if (playerCapability.isBurning(MetalsNBTData.DURALUMIN) || playerCapability.getExternalEnhanced()) {
                                PewterAndTinHelpers.addTinEffectsEnhanced(player);
                                if (playerCapability.isBurning(MetalsNBTData.LERASIUM)){
                                    playerCapability.drainMetals(MetalsNBTData.LERASIUM);
                                }
                                if (!playerCapability.getExternalEnhanced()) {
                                    playerCapability.drainMetals(MetalsNBTData.TIN, MetalsNBTData.DURALUMIN);
                                } else {
                                    playerCapability.drainMetals(MetalsNBTData.TIN);
                                }
                            } else {
                                PewterAndTinHelpers.addTinEffects(player);
                            }
                        }
                        /************************
                         * BRONZE POWERS
                         ************************/
                        if (playerCapability.isBurning(MetalsNBTData.BRONZE) && !playerCapability.isBurning(MetalsNBTData.DURALUMIN)) {
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
                        } else if (playerCapability.isBurning(MetalsNBTData.BRONZE) && (playerCapability.isBurning(MetalsNBTData.DURALUMIN) || playerCapability.getExternalEnhanced())) {
                            if (event.level instanceof ServerLevel) {
                                BlockPos negative;
                                BlockPos positive;
                                if (playerCapability.isBurning(MetalsNBTData.LERASIUM)){
                                    negative = new BlockPos(player.position()).offset(-x - 10, -y - 10, -z - 10);
                                    positive = new BlockPos(player.position()).offset(x + 10, y + 10, z + 10);
                                    playerCapability.drainMetals(MetalsNBTData.LERASIUM);
                                } else {
                                    negative = new BlockPos(player.position()).offset(-x - 8, -y - 8, -z - 8);
                                    positive = new BlockPos(player.position()).offset(x + 8, y + 8, z + 8);
                                }
                                CopperAndBronzeHelpers.BronzeEnhancedAiEntityManipulation(new AABB(negative, positive), player, event.level);
                                if (!playerCapability.getExternalEnhanced()) {
                                    playerCapability.drainMetals(MetalsNBTData.BRONZE, MetalsNBTData.DURALUMIN);
                                } else {
                                    playerCapability.drainMetals(MetalsNBTData.BRONZE);
                                }
                            }
                        }
                        /************************
                         * COPPER POWERS
                         ************************/
                        if (playerCapability.isBurning(MetalsNBTData.COPPER) && !playerCapability.isBurning(MetalsNBTData.DURALUMIN)) {
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
                        } else if (playerCapability.isBurning(MetalsNBTData.COPPER) && (playerCapability.isBurning(MetalsNBTData.DURALUMIN) || playerCapability.getExternalEnhanced())) {
                            if (event.level instanceof ServerLevel) {
                                BlockPos negative;
                                BlockPos positive;
                                if (playerCapability.isBurning(MetalsNBTData.LERASIUM)) {
                                    negative = new BlockPos(player.position()).offset(-x - 10, -y - 10, -z - 10);
                                    positive = new BlockPos(player.position()).offset(x + 10, y + 10, z + 10);
                                    playerCapability.drainMetals(MetalsNBTData.LERASIUM);
                                } else {
                                    negative = new BlockPos(player.position()).offset(-x - 8, -y - 8, -z - 8);
                                    positive = new BlockPos(player.position()).offset(x + 8, y + 8, z + 8);
                                }
                                CopperAndBronzeHelpers.CopperEnhancedAiEntityManipulation(new AABB(negative, positive), player, event.level);
                                if (!playerCapability.getExternalEnhanced()) {
                                    playerCapability.drainMetals(MetalsNBTData.COPPER, MetalsNBTData.DURALUMIN);
                                } else {
                                    playerCapability.drainMetals(MetalsNBTData.COPPER);
                                }
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
                             for (MetalsNBTData metalsNBTData: playerCapability.getAllomanticPowers()) {
                                 playerCapability.drainMetals(metalsNBTData);
                             }
                             player.removeAllEffects();
                        }
                        /************************
                         * ELECTRUM POWER (ENHANCED)
                         ************************/
                        if (playerCapability.isBurning(MetalsNBTData.ELECTRUM) && (playerCapability.isBurning(MetalsNBTData.DURALUMIN) || playerCapability.getExternalEnhanced())) {
                            BlockPos block = null;
                            String dimension = null;
                            if (playerCapability.getSpawnPos() != null && playerCapability.getSpawnDimension() != null) {
                                block = new BlockPos(playerCapability.getSpawnPos()[0], playerCapability.getSpawnPos()[1], playerCapability.getSpawnPos()[2]);
                                dimension = playerCapability.getSpawnDimension();
                            } else {
                                block = new BlockPos(world.getLevelData().getXSpawn(),world.getLevelData().getYSpawn(), world.getLevelData().getZSpawn());
                                dimension = Level.OVERWORLD.registry().getNamespace();
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
                            if (!playerCapability.getExternalEnhanced()) {
                                playerCapability.drainMetals(MetalsNBTData.DURALUMIN, MetalsNBTData.ELECTRUM);
                            } else {
                                playerCapability.drainMetals(MetalsNBTData.ELECTRUM);
                            }
                        }
                        /************************
                         * GOLD POWER (ENHANCED)
                         ************************/
                        if (playerCapability.isBurning(MetalsNBTData.GOLD) && (playerCapability.isBurning(MetalsNBTData.DURALUMIN) || playerCapability.getExternalEnhanced())) {
                            BlockPos block = null;
                            String dimension = null;
                            if (playerCapability.getDeathPos() != null && playerCapability.getDeathDimension() != null) {
                                block = new BlockPos(playerCapability.getDeathPos()[0], playerCapability.getDeathPos()[1], playerCapability.getDeathPos()[2]);
                                dimension = playerCapability.getDeathDimension();
                            } else {
                                block = new BlockPos(world.getLevelData().getXSpawn(),world.getLevelData().getYSpawn(), world.getLevelData().getZSpawn());
                                dimension = Level.OVERWORLD.registry().getNamespace();
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
                            if (!playerCapability.getExternalEnhanced()) {
                                playerCapability.drainMetals(MetalsNBTData.DURALUMIN, MetalsNBTData.GOLD);
                            } else {
                                playerCapability.drainMetals(MetalsNBTData.GOLD);
                            }                        }
                        /************************
                         * MALATIUM POWER (ENHANCED)
                         ************************/
                        if (playerCapability.isBurning(MetalsNBTData.MALATIUM) && playerCapability.isBurning(MetalsNBTData.DURALUMIN)) {
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
                                GoldAndElectrumHelpers.setBlock(null);
                                GoldAndElectrumHelpers.setDimension(null);
                            }
                            playerCapability.drainMetals(MetalsNBTData.DURALUMIN, MetalsNBTData.MALATIUM);
                        }

                        if (playerCapability.getExternalEnhanced()) {
                            if (playerCapability.isBurning(MetalsNBTData.DURALUMIN)) {
                                playerCapability.drainMetals(MetalsNBTData.DURALUMIN);
                            }
                            playerCapability.setExternalEnhanced(false);
                        }

                        ModNetwork.sync(playerCapability, player);
                 });

        }
    }
}

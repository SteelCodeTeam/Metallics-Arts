package net.rudahee.metallics_arts.modules.powers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TieredItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.modules.client.ClientUtils;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.modules.data_player.InvestedDataProvider;
import net.rudahee.metallics_arts.modules.powers.helpers.*;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.enums.metals.Metal;
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
                    if (data.getSpawnDimension() == null) {
                        int[] pos = {player.level.getLevelData().getXSpawn(),player.level.getLevelData().getYSpawn(),player.level.getLevelData().getZSpawn()};
                        String dim = player.level.dimension().getRegistryName().getNamespace();

                        data.setSpawnPos(pos);
                        data.setSpawnDimension(dim);
                    }
                    if (data.getDeathDimension() == null) {
                        int[] pos = {player.level.getLevelData().getXSpawn(),player.level.getLevelData().getYSpawn(),player.level.getLevelData().getZSpawn()};
                        String dim = player.level.dimension().getRegistryName().getNamespace();

                        data.setDeathPos(pos);
                        data.setDeathDimension(dim);
                    }

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
    public static void onSetSpawn(final PlayerSetSpawnEvent event) {
        PlayerEntity playerEntity = event.getPlayer();
        if (event.getPlayer() instanceof ServerPlayerEntity) {


            playerEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(
                capabilities -> {

                    int[] pos = {(int) playerEntity.position().x,(int) playerEntity.position().y, (int) playerEntity.position().z};
                    String dim = playerEntity.level.dimension().getRegistryName().getNamespace();

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
        if (event.getEntityLiving() instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) event.getEntityLiving();
            player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(playerCapability -> {

                int[] pos = {(int) player.position().x,(int) player.position().y, (int) player.position().z};
                String dim = player.level.dimension().getRegistryName().getNamespace();
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
        if (!event.getPlayer().getCommandSenderWorld().isClientSide()) {

            ModNetwork.sync(event.getPlayer());
        }
    }

    @SubscribeEvent
    public static void onChangeDimension(final PlayerEvent.PlayerChangedDimensionEvent event) {
        if (!event.getPlayer().getCommandSenderWorld().isClientSide()) {
            ModNetwork.sync(event.getPlayer());

            if (event.getEntityLiving() instanceof ServerPlayerEntity) {
                ServerPlayerEntity entity = (ServerPlayerEntity) event.getEntityLiving();
                entity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {
                    if (data.isBurning(MetalsNBTData.ELECTRUM)) {
                        ClientUtils.toggleBurn(MetalsNBTData.ELECTRUM, data);
                    }

                    if (data.isBurning(MetalsNBTData.GOLD)) {
                        ClientUtils.toggleBurn(MetalsNBTData.GOLD, data);
                    }
                });
            }
            ModNetwork.sync(event.getPlayer());
        }
    }



    @SubscribeEvent
    public static void onPlayerClone(final PlayerEvent.Clone event) {
        if (!event.getPlayer().level.isClientSide()) {

            event.getOriginal().revive();

            PlayerEntity player = event.getPlayer();
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
                     *   DAMAGE WITH - ETTMETAL FERUCHEMIC -
                     *******************************/
                    if (playerCapability.isBurning(MetalsNBTData.ETTMETAL)) {
                        if (event.getEntityLiving() instanceof PlayerEntity) {
                            ChromiumAndNicrosilHelpers.drainMetalChromium((PlayerEntity) event.getEntityLiving());
                        }
                    }

                    /*******************************
                     *   DAMAGE IF ENEMY BURN ATIUM
                     *******************************/
                    //event.getEntityliving : recive daño
                    //playerEntity hace daño

                    if (event.getEntityLiving() instanceof PlayerEntity) {
                        event.setAmount(AtiumAndMalatiumHelpers.atiumHit(playerEntity, (PlayerEntity) event.getEntityLiving(), event.getAmount()));
                    }

                    /*******************************
                     *   DAMAGE WITH - ZINC -
                     *******************************/
                    if (playerCapability.isBurning(MetalsNBTData.ZINC)) {
                        if (event.getEntityLiving() instanceof PlayerEntity) {
                            ZincAndBrassHelpers.drawSaturatedScreen((PlayerEntity) event.getEntityLiving());
                        }
                    }

                    /*******************************
                     *   DAMAGE WITH - MALATIUM -
                     *******************************/
                    if (playerCapability.isBurning(MetalsNBTData.MALATIUM)) {
                        if (event.getEntityLiving() instanceof PlayerEntity) {
                            GoldAndElectrumHelpers.takeDeathPosToObjetive((PlayerEntity) event.getEntityLiving());
                        }
                    }

                    /*******************************
                     *   DAMAGE WITH - BRASS - FERUCHEMIC
                     *******************************/
                    if (playerCapability.isDecanting(MetalsNBTData.BRASS)) {
                        ZincAndBrassHelpers.addFireAspectToPlayer(event.getEntityLiving(),1);
                    }

                    /*******************************
                     *   DAMAGE WITH - ZINC - FERUCHEMIC
                     *******************************/
                    if (playerCapability.isDecanting(MetalsNBTData.ZINC)) {
                        ZincAndBrassHelpers.addLootToEnemy(event.getEntityLiving(),0.6);
                    } else if (playerCapability.isStoring(MetalsNBTData.ZINC)) {
                        ZincAndBrassHelpers.removeLootToEnemy(event.getEntityLiving(),0.6);
                    }

            });
        }


        /*******************************
         *   DAMAGE IF PLAYER IS BURN ATIUM
         *******************************/

        if (event.getEntityLiving() instanceof ServerPlayerEntity) {
            event.setAmount(AtiumAndMalatiumHelpers.atiumHitMobPlayer((PlayerEntity) event.getEntityLiving(), event.getAmount()));
        }



    }

    public static int ticks = 0;

    public static int x = 8;
    public static int y = 8;
    public static int z = 8;

    public static int tickOffset= 0;
    public static int actualTick = 0;

    public static boolean restoreHealth = false;

    public static boolean previusAtium = false;


    private static PlayerEntity newPlayer = null;
    @SubscribeEvent
    public static void onWorldTickEvent(final TickEvent.WorldTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        World world = event.world;
            List<? extends PlayerEntity> playerList = world.players();
            ticks++;
            for (int playerIndex = playerList.size() - 1; playerIndex >= 0; playerIndex--) {

                if (playerList.get(playerIndex) instanceof PlayerEntity || playerList.get(playerIndex) instanceof ServerPlayerEntity) {
                    newPlayer = playerList.get(playerIndex);
                } else {
                    newPlayer = null;
                }

                if (newPlayer == null) {
                    return;
                }

                PlayerEntity player = newPlayer;


                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(
                    playerCapability -> {
                        if (playerCapability.isInvested()) {
                            if (player instanceof ServerPlayerEntity) {
                                playerCapability.tickAllomancyBurningMetals((ServerPlayerEntity) player);
                            }

                            /************************
                             * BRONZE FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalsNBTData.BRONZE)) {
                                BlockPos negative = new BlockPos(player.position()).offset(-x -4, -y -4, -z -4);
                                BlockPos positive = new BlockPos(player.position()).offset(x +4, y + 24 , z +4);

                                CopperAndBronzeHelpers.DontSpawnPhantoms(player, new AxisAlignedBB(negative, positive), event.world);
                            } else if (playerCapability.isStoring(MetalsNBTData.BRONZE)) {
                                CopperAndBronzeHelpers.SpawnPhamtonsWithFireResistance(player, world); //TODO we need implement a network packet to spawn mobs in world
                            }

                            /************************
                             * COPPER FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalsNBTData.COPPER)) {
                                if (actualTick % 10 == 0) {
                                    CopperAndBronzeHelpers.generateExperience(player, event.world);
                                }
                            } else if (playerCapability.isStoring(MetalsNBTData.COPPER)) {
                                if (actualTick % 10 == 0) {
                                    CopperAndBronzeHelpers.saveExperience(player, event.world);
                                }
                            }

                            /************************
                             * BRASS FERUCHEMIC
                             ************************/
                            if (playerCapability.isStoring(MetalsNBTData.BRASS)) {
                                player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 40, 1, true, false));
                            }

                            /************************
                             * GOLD FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalsNBTData.GOLD)) {
                                if (actualTick == 30 || actualTick == 60 || actualTick == 90) {
                                    GoldAndElectrumHelpers.addHealth(player,1);
                                }
                            } else if (playerCapability.isStoring(MetalsNBTData.GOLD)) {
                                if (playerCapability.isStoring(MetalsNBTData.ELECTRUM)) {
                                    playerCapability.setStoring(MetalsNBTData.ELECTRUM, false);
                                }
                                if (actualTick == 30 || actualTick == 60 || actualTick == 90) {
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
                                if (actualTick == 30 || actualTick == 60 || actualTick == 90) {
                                    BendalloyAndCadmiunHelpers.throwBreathEffect(player, 10);
                                }
                            } else if (playerCapability.isStoring(MetalsNBTData.CADMIUM)) {
                                //if (actualTick == 30 || actualTick == 60 || actualTick == 90) {
                                    BendalloyAndCadmiunHelpers.drowningEffect(player,actualTick);
                                //}
                            }

                            /************************
                             * BENDALLOY FERUCHEMIC
                             ************************/

                            if (playerCapability.isDecanting(MetalsNBTData.BENDALLOY)) {
                                if (actualTick == 30 || actualTick == 60 || actualTick == 90) {
                                    BendalloyAndCadmiunHelpers.addFoodLevel(player,1);
                                }
                            } else if (playerCapability.isStoring(MetalsNBTData.BENDALLOY)) {
                                if (actualTick == 30 || actualTick == 60 || actualTick == 90) {
                                    BendalloyAndCadmiunHelpers.removeFoodLevel(player,1);
                                }
                            }

                            /************************
                             * DURALUMIN FERUCHEMIC
                             ************************/
                            Biome biome = world.getBiome(player.getEntity().blockPosition());

                            if (playerCapability.isDecanting(MetalsNBTData.DURALUMIN)) {
                                if (actualTick == 30 || actualTick == 60 || actualTick == 90) {
                                    DuraluminAndAluminumHelpers.duraluminDecantingEffects(player, biome);
                                }
                            } else if (playerCapability.isStoring(MetalsNBTData.DURALUMIN)) {
                                if (actualTick == 30 || actualTick == 60 || actualTick == 90) {
                                    DuraluminAndAluminumHelpers.duraluminStoringEffects(player, biome);
                                }
                            }

                            /************************
                             * CHROMIUM FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalsNBTData.CHROMIUM)){
                                ChromiumAndNicrosilHelpers.goodLuck(player);
                                //player.getLuck()
                            } else if (playerCapability.isStoring(MetalsNBTData.CHROMIUM)){
                                if (actualTick==90)
                                    ChromiumAndNicrosilHelpers.badLuck(player,true);
                                else
                                    ChromiumAndNicrosilHelpers.badLuck(player,false);
                            }


                            /************************
                             * ATIUM FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalsNBTData.ATIUM)){
                                AtiumAndMalatiumHelpers.changeExperience(player, true);
                            } else if (playerCapability.isStoring(MetalsNBTData.ATIUM)){
                                AtiumAndMalatiumHelpers.changeExperience(player, false);
                            } else if (!playerCapability.isStoring(MetalsNBTData.ATIUM) && !playerCapability.isDecanting(MetalsNBTData.ATIUM)){
                                previusAtium = false;
                            }

                            if (actualTick >= 90) {
                                actualTick = 0;
                            } else {
                                actualTick++;
                            }

                            /************************
                             * BENDALLOY POWERS
                             ************************/
                            if (playerCapability.hasAllomanticPower(MetalsNBTData.BENDALLOY) && playerCapability.isBurning(MetalsNBTData.BENDALLOY)) {
                                if (!playerCapability.isBurning(MetalsNBTData.CADMIUM)) {

                                    /** ENHANCED */
                                    if (playerCapability.isBurning(MetalsNBTData.DURALUMIN)) {
                                        BendalloyAndCadmiunHelpers.AddAiSteeps(player);

                                        if (event.world instanceof ServerWorld) {
                                            BlockPos negative = new BlockPos(player.position()).offset(-x - 6, -y - 6, -z - 6);
                                            BlockPos positive = new BlockPos(player.position()).offset(x + 6, y + 6 , z + 6);

                                            // Ticks extra in random blocks, tile entities and entities.
                                            BendalloyAndCadmiunHelpers.BendalloyEffectsEnhanced(player, event.world,new AxisAlignedBB(negative, positive), negative, positive);
                                        }
                                        if (tickOffset > 120) {
                                            playerCapability.drainMetals(MetalsNBTData.BENDALLOY, MetalsNBTData.DURALUMIN);
                                            tickOffset = 0;
                                        } else {
                                            tickOffset++;
                                        }
                                    }
                                    /** NORMAL */
                                    else {
                                        BendalloyAndCadmiunHelpers.AddAiSteepsEnhanced(player);

                                        if (event.world instanceof ServerWorld) {
                                            BlockPos negative = new BlockPos(player.position()).offset(-x - 2, -y - 2, -z - 2);
                                            BlockPos positive = new BlockPos(player.position()).offset(x + 2, y + 2 , z + 2);

                                            // Ticks extra in random blocks, tile entities and entities.
                                            BendalloyAndCadmiunHelpers.BendalloyEffects(player, event.world,new AxisAlignedBB(negative, positive), negative, positive);
                                        }
                                    }
                                }
                            }


                            /************************
                             * CADMIUM POWERS
                             ************************/
                            if (playerCapability.hasAllomanticPower(MetalsNBTData.CADMIUM) && playerCapability.isBurning(MetalsNBTData.CADMIUM)) {
                                if (!playerCapability.isBurning(MetalsNBTData.BENDALLOY)) {

                                    /** ENHANCED */
                                    if (playerCapability.isBurning(MetalsNBTData.DURALUMIN)) {

                                        BlockPos negative = new BlockPos(player.position()).offset(-x -5, -y -5, -z -5);
                                        BlockPos positive = new BlockPos(player.position()).offset(x+5 , y+5, z+5);

                                        BendalloyAndCadmiunHelpers.CadmiumEffectSelfPlayerEnhanced(player);

                                        if (event.world instanceof ServerWorld) {

                                            event.world.getEntitiesOfClass(LivingEntity.class, new AxisAlignedBB(negative, positive)).forEach(entity -> {
                                                if (entity != player) {
                                                    //Do others in de cloud my powers.
                                                    BendalloyAndCadmiunHelpers.CadmiumEffectsOtherPlayersEnhanced(entity, 20, 100);
                                                }
                                            });
                                        }

                                        if (tickOffset > 80) {
                                            playerCapability.drainMetals(MetalsNBTData.CADMIUM, MetalsNBTData.DURALUMIN);
                                            tickOffset = 0;
                                        } else {
                                            tickOffset++;
                                        }
                                    }
                                    /** NORMAL */
                                    else {

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
                        }
                        /************************
                         * PEWTER POWERS
                         ************************/
                        if (playerCapability.hasAllomanticPower(MetalsNBTData.PEWTER) && playerCapability.isBurning(MetalsNBTData.PEWTER)) {
                            // Add strength, jump and speed.
                            if (playerCapability.isBurning(MetalsNBTData.DURALUMIN)) {
                                PewterAndTinHelpers.addPewterEffectsEnhanced(player);

                                if (tickOffset > 160) {
                                    playerCapability.drainMetals(MetalsNBTData.PEWTER, MetalsNBTData.DURALUMIN);
                                    tickOffset = 0;
                                } else {
                                    tickOffset++;
                                }
                            } else {
                                PewterAndTinHelpers.addPewterEffects(player);
                            }
                        }


                        /************************
                         * TIN POWERS
                         ************************/
                        if (playerCapability.hasAllomanticPower(MetalsNBTData.TIN) && playerCapability.isBurning(MetalsNBTData.TIN)) {
                            if (playerCapability.isBurning(MetalsNBTData.DURALUMIN)) {
                                PewterAndTinHelpers.addTinEffectsEnhanced(player);

                                if (tickOffset > 320) {
                                    playerCapability.drainMetals(MetalsNBTData.TIN, MetalsNBTData.DURALUMIN);
                                    tickOffset = 0;
                                } else {
                                    tickOffset++;
                                }
                            } else {
                                PewterAndTinHelpers.addTinEffects(player);
                            }
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
                         * ETTMETAL
                         ************************/
                        if (playerCapability.isBurning(MetalsNBTData.ETTMETAL)){
                            LerasiumAndEttmetalHelpers.ettmetalExplotion(event.world,player);
                        }

                        /************************
                         * ALUMINUM POWER
                         ************************/
                        if (playerCapability.hasAllomanticPower(MetalsNBTData.ALUMINUM) && playerCapability.isBurning(MetalsNBTData.ALUMINUM)) {
                             for (MetalsNBTData metalsNBTData: playerCapability.getAllomanticPowers()) {
                                 playerCapability.drainMetals(metalsNBTData);
                             }
                        }

                        /************************
                         * ELECTRUM POWER (ENHANCED)
                         ************************/
                        if (playerCapability.isBurning(MetalsNBTData.ELECTRUM) && playerCapability.isBurning(MetalsNBTData.DURALUMIN)) {
                            BlockPos block = null;
                            String dimension = null;
                            if (playerCapability.getSpawnPos() != null && playerCapability.getSpawnDimension() != null) {
                                block = new BlockPos(playerCapability.getSpawnPos()[0], playerCapability.getSpawnPos()[1], playerCapability.getSpawnPos()[2]);
                                dimension = playerCapability.getSpawnDimension();
                            } else {
                                block = new BlockPos(world.getLevelData().getXSpawn(),world.getLevelData().getYSpawn(), world.getLevelData().getZSpawn());
                                dimension = World.OVERWORLD.getRegistryName().getNamespace();
                            }

                            GoldAndElectrumHelpers.teleport(player, event.world, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension), block);

                            playerCapability.drainMetals(MetalsNBTData.DURALUMIN, MetalsNBTData.ELECTRUM);
                        }

                        /************************
                         * GOLD POWER (ENHANCED)
                         ************************/
                        if (playerCapability.isBurning(MetalsNBTData.GOLD) && playerCapability.isBurning(MetalsNBTData.DURALUMIN)) {
                            BlockPos block = null;
                            String dimension = null;
                            if (playerCapability.getDeathPos() != null && playerCapability.getDeathDimension() != null) {
                                block = new BlockPos(playerCapability.getDeathPos()[0], playerCapability.getDeathPos()[1], playerCapability.getDeathPos()[2]);
                                dimension = playerCapability.getDeathDimension();
                            } else {
                                block = new BlockPos(world.getLevelData().getXSpawn(),world.getLevelData().getYSpawn(), world.getLevelData().getZSpawn());
                                dimension = World.OVERWORLD.getRegistryName().getNamespace();
                            }

                            GoldAndElectrumHelpers.teleport(player, event.world, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension), block);

                            playerCapability.drainMetals(MetalsNBTData.DURALUMIN, MetalsNBTData.ELECTRUM);
                        }

                        /************************
                         * MALATIUM POWER (ENHANCED)
                         ************************/
                        if (playerCapability.isBurning(MetalsNBTData.MALATIUM) && playerCapability.isBurning(MetalsNBTData.DURALUMIN)) {
                            BlockPos block = null;
                            String dimension = null;

                            if (GoldAndElectrumHelpers.getBlock() != null && GoldAndElectrumHelpers.getDimension() != null) {
                                block = GoldAndElectrumHelpers.getBlock();
                                dimension = GoldAndElectrumHelpers.getDimension();
                                GoldAndElectrumHelpers.teleport(player, event.world, GoldAndElectrumHelpers.getRegistryKeyFromString(dimension), block);
                                GoldAndElectrumHelpers.setBlock(null);
                                GoldAndElectrumHelpers.setDimension(null);
                            }

                            playerCapability.drainMetals(MetalsNBTData.DURALUMIN, MetalsNBTData.ELECTRUM);
                        }

                });
                   // ModNetwork.sync(player);


        }
    }
}

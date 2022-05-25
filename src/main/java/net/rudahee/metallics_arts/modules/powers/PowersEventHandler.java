package net.rudahee.metallics_arts.modules.powers;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.modules.client.ClientUtils;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.modules.powers.helpers.*;
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
                    if (data.getSpawnDimension() == null) {
                        int[] pos = {player.level.getLevelData().getXSpawn(),player.level.getLevelData().getYSpawn(),player.level.getLevelData().getZSpawn()};
                        String dim = player.level.dimension().getRegistryName().getNamespace();

                        data.setSpawnPos(pos);
                        data.setSpawnDimension(dim);
                    }
                    if(data.getDeathDimension() == null) {
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
            player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(capabilites -> {

                int[] pos = {(int) player.position().x,(int) player.position().y, (int) player.position().z};
                String dim = player.level.dimension().getRegistryName().getNamespace();

                capabilites.setDeathDimension(dim);
                capabilites.setDeathPos(pos);

                ModNetwork.sync(capabilites, player);
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
                        data.setDeathPos(oldData.getDeathPos());
                        data.setSpawnPos(oldData.getSpawnPos());
                        data.setDeathDimension(oldData.getDeathDimension());
                        data.setSpawnDimension(oldData.getSpawnDimension());
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
                     *   DAMAGE WITH - ZINC -
                     *******************************/
                    if (playerCapability.isBurning(MetalsNBTData.ZINC)) {
                        if (event.getEntityLiving() instanceof PlayerEntity) {
                            ZincAndBrassHelpers.drawSaturatedScreen((PlayerEntity) event.getEntityLiving()); //TODO
                        }
                    }

                    /*******************************
                     *   DAMAGE WITH - BRASS - FERUCHEMIC
                     *******************************/
                    if (playerCapability.isDecanting(MetalsNBTData.BRASS)) {
                        ZincAndBrassHelpers.addFireAspectToPlayer(event.getEntityLiving(),5);
                    }

                    /*******************************
                     *   DAMAGE WITH - ZINC - FERUCHEMIC
                     *******************************/
                    if (playerCapability.isDecanting(MetalsNBTData.ZINC)) {
                        ZincAndBrassHelpers.addLootToEnemy(event.getEntityLiving(),0.6);
                    }else if (playerCapability.isStoring(MetalsNBTData.ZINC)) {
                        ZincAndBrassHelpers.removeLootToEnemy(event.getEntityLiving(),0.6);
                    }



            });
        }
    }

    public static int ticks = 0;

    public static int x = 8;
    public static int y = 8;
    public static int z = 8;

    public static int tickOffset= 0;
    public static int actualTick = 0;


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
                        if (playerCapability.isInvested()) {
                            if (player instanceof ServerPlayerEntity) {
                                playerCapability.tickAllomancyBurningMetals((ServerPlayerEntity) player);
                            }

                            if (playerCapability.isDecanting(MetalsNBTData.DURALUMIN)) {
                                //
                            } else if (playerCapability.isStoring(MetalsNBTData.DURALUMIN)) {
                                //
                            }

                            /************************
                             * BRASS FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalsNBTData.BRASS)){
                                    //despues vemos
                            } else if (playerCapability.isStoring(MetalsNBTData.BRASS)){
                                player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 20, 1, true, false));
                            }

                            /************************
                             * ZINC FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalsNBTData.ZINC)){
                                if (actualTick == 80) {
                                    player.addEffect(new EffectInstance(Effects.LUCK,20,90,true, false));
                                }
                            } else if (playerCapability.isStoring(MetalsNBTData.ZINC)){
                                player.addEffect(new EffectInstance(Effects.UNLUCK,20,1,true, false));
                            }

                            /************************
                             * GOLD FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalsNBTData.GOLD)) {
                                if (actualTick == 80) {
                                    player.addEffect(new EffectInstance(Effects.REGENERATION, 90, 1, true, false));
                                }
                            } else if (playerCapability.isStoring(MetalsNBTData.GOLD)) {
                                if (actualTick == 80) {
                                    player.addEffect(new EffectInstance(Effects.POISON, 90, 1, true, false));
                                }
                            }
                            /************************
                             * ELECTRUM FERUCHEMIC
                             ************************/
                            if (playerCapability.isDecanting(MetalsNBTData.ELECTRUM)){
                                if (actualTick == 80) {
                                    player.addEffect(new EffectInstance(Effects.ABSORPTION, 90, 5, true, false));
                                }
                            } else if (playerCapability.isStoring(MetalsNBTData.ELECTRUM)){
                                GoldAndElectrumHelpers.removeHearts(player,5);
                            }


                            /************************
                             * STEEL FERUCHEMIC
                             ************************/
                            if(playerCapability.isDecanting(MetalsNBTData.STEEL)){
                                if (playerCapability.isBurning(MetalsNBTData.STEEL)){
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 20, 20, true, false));
                                }else{
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 20, 2, true, false));
                                }
                            }else if (playerCapability.isStoring(MetalsNBTData.STEEL)){
                                player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20, 2, true, false));
                            }

                            /************************
                             * IRON FERUCHEMIC
                             ************************/

                            if(playerCapability.isDecanting(MetalsNBTData.IRON)){
                                //player.setDeltaMovement(player.getDeltaMovement().x,(player.getDeltaMovement().y)*10,player.getDeltaMovement().z);
                            }else if (playerCapability.isStoring(MetalsNBTData.IRON)){
                                //player.setDeltaMovement(player.getDeltaMovement().x,(player.getDeltaMovement().y)/10,player.getDeltaMovement().z);
                            }

                            /************************
                             * CADMIUM FERUCHEMIC
                             ************************/
                            if(playerCapability.isDecanting(MetalsNBTData.CADMIUM)){
                                player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 20, 1, true, false));
                            }else if (playerCapability.isStoring(MetalsNBTData.CADMIUM)){
                                BendalloyAndCadmiunHelpers.drowningEffect(player,1);
                                //AHOGARSE
                            }

                            /************************
                             * BENDALLOY FERUCHEMIC
                             ************************/

                            //NO LO HACE INSTANTANEO
                            if(playerCapability.isDecanting(MetalsNBTData.BENDALLOY)){
                                player.addEffect(new EffectInstance(Effects.SATURATION, 20, 1, true, false));
                            }else if (playerCapability.isStoring(MetalsNBTData.BENDALLOY)){
                                player.addEffect(new EffectInstance(Effects.HUNGER, 20, 1, true, false));
                            }

                            /************************
                             * DURALUMIN FERUCHEMIC
                             ************************/

                            Biome biome = world.getBiome(player.getEntity().blockPosition());

                            if(playerCapability.isDecanting(MetalsNBTData.DURALUMIN)){

                                if(biome.getBiomeCategory().equals(Biome.Category.EXTREME_HILLS)) {

                                    player.addEffect(new EffectInstance(Effects.SLOW_FALLING, 20, 1, true, false));
                                    player.addEffect(new EffectInstance(Effects.JUMP, 20, 4, true, false));

                                }else if(biome.getBiomeCategory().equals(Biome.Category.MESA)) {

                                    player.addEffect(new EffectInstance(Effects.SLOW_FALLING, 20, 1, true, false));
                                    player.addEffect(new EffectInstance(Effects.JUMP, 20, 4, true, false));

                                    //montañas salto 4
                                }else if(biome.getBiomeCategory().equals(Biome.Category.JUNGLE)) {

                                    player.addEffect(new EffectInstance(Effects.JUMP, 20, 3, true, false));
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 20, 1, true, false));

                                } else if(biome.getBiomeCategory().equals(Biome.Category.TAIGA)) {
                                    player.addEffect(new EffectInstance(Effects.JUMP, 20, 2, true, false));
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 20, 2, true, false));

                                }
                                else if(biome.getBiomeCategory().equals(Biome.Category.PLAINS)) {
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 20, 5, true, false));

                                    //velocidad 4
                                } else if(biome.getBiomeCategory().equals(Biome.Category.SAVANNA)) {
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 20, 5, true, false));
                                    //velocidad 4
                                } else if(biome.getBiomeCategory().equals(Biome.Category.BEACH)) {
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 20, 2, true, false));
                                    player.addEffect(new EffectInstance(Effects.JUMP, 20, 1, true, false));
                                } else if(biome.getBiomeCategory().equals(Biome.Category.FOREST)) {
                                    player.addEffect(new EffectInstance(Effects.DIG_SPEED, 20, 2, true, false));
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 20, 2, true, false));
                                    //prisa minera poca
                                    //velocidad
                                } else if(biome.getBiomeCategory().equals(Biome.Category.DESERT)) {
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 20, 3, true, false));
                                    player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 20, 2, true, false));
                                    //velocidad
                                    //fuerza
                                } else if(biome.getBiomeCategory().equals(Biome.Category.MUSHROOM)) {
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 20, 3, true, false));
                                    player.addEffect(new EffectInstance(Effects.JUMP, 20, 1, true, false));
                                    //velocidad 3 salto 1
                                } else if(biome.getBiomeCategory().equals(Biome.Category.ICY)) {
                                    player.addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 20, 3, true, false));
                                    player.addEffect(new EffectInstance(Effects.JUMP, 20, 1, true, false));
                                    //gracia del delfin//salto
                                } else if(biome.getBiomeCategory().equals(Biome.Category.OCEAN)) {
                                    player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 20, 1, true, false));
                                    player.addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 20, 3, true, false));
                                    //apnea//gracia del delfin
                                } else if(biome.getBiomeCategory().equals(Biome.Category.SWAMP)) {
                                    player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 20, 1, true, false));
                                    player.addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 20, 3, true, false));
                                    //gracia del delfin //velocidad
                                } else if(biome.getBiomeCategory().equals(Biome.Category.RIVER)) {
                                    player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 20, 1, true, false));
                                    player.addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 20, 3, true, false));
                                    //gracia del delfin //velocidad
                                } else if(biome.getBiomeCategory().equals(Biome.Category.NETHER)) {
                                    player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 20, 2, true, false));
                                    player.addEffect(new EffectInstance(Effects.DIG_SPEED, 20, 2, true, false));
                                    //fuerza 2
                                    //prisa 2
                                }else if(biome.getBiomeCategory().equals(Biome.Category.THEEND)) {
                                    player.addEffect(new EffectInstance(Effects.SLOW_FALLING, 20, 1, true, false));
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 20, 3, true, false));
                                    //caida lenta//velocidad 3
                                }
                                else{
                                    System.out.println("no hay poderes papa");
                                }

                            }else if (playerCapability.isStoring(MetalsNBTData.DURALUMIN)){
                                if(biome.getBiomeCategory().equals(Biome.Category.EXTREME_HILLS)) {

                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20, 2, true, false));


                                }else if(biome.getBiomeCategory().equals(Biome.Category.MESA)) {

                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20, 1, true, false));
                                    //montañas salto 4
                                }else if(biome.getBiomeCategory().equals(Biome.Category.JUNGLE)) {

                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20, 1, true, false));

                                } else if(biome.getBiomeCategory().equals(Biome.Category.TAIGA)) {
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20, 2, true, false));

                                }
                                else if(biome.getBiomeCategory().equals(Biome.Category.PLAINS)) {
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20, 5, true, false));

                                    //velocidad 4
                                } else if(biome.getBiomeCategory().equals(Biome.Category.SAVANNA)) {
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20, 5, true, false));
                                    //velocidad 4
                                } else if(biome.getBiomeCategory().equals(Biome.Category.BEACH)) {
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20, 2, true, false));
                                } else if(biome.getBiomeCategory().equals(Biome.Category.FOREST)) {
                                    player.addEffect(new EffectInstance(Effects.DIG_SLOWDOWN, 20, 2, true, false));
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20, 2, true, false));
                                    //prisa minera poca
                                    //velocidad
                                } else if(biome.getBiomeCategory().equals(Biome.Category.DESERT)) {
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20, 3, true, false));
                                    player.addEffect(new EffectInstance(Effects.WEAKNESS, 20, 2, true, false));
                                    //velocidad
                                    //fuerza
                                } else if(biome.getBiomeCategory().equals(Biome.Category.MUSHROOM)) {
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20, 3, true, false));
                                    //velocidad 3 salto 1
                                } else if(biome.getBiomeCategory().equals(Biome.Category.ICY)) {
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20, 3, true, false));
                                    //gracia del delfin//salto
                                } else if(biome.getBiomeCategory().equals(Biome.Category.OCEAN)) {
                                    //AHOGAMIENTO //player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 20, 1, true, false));
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20, 3, true, false));
                                    //apnea//gracia del delfin
                                } else if(biome.getBiomeCategory().equals(Biome.Category.SWAMP)) {
                                    //AHOGAMIENTO //player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 20, 1, true, false));
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20, 3, true, false));
                                    //gracia del delfin //velocidad
                                } else if(biome.getBiomeCategory().equals(Biome.Category.RIVER)) {
                                    //AHOGAMIENTO //player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 20, 1, true, false));
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20, 3, true, false));
                                    //gracia del delfin //velocidad
                                } else if(biome.getBiomeCategory().equals(Biome.Category.NETHER)) {
                                    player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 20, 2, true, false));
                                    player.addEffect(new EffectInstance(Effects.DIG_SPEED, 20, 2, true, false));
                                    //fuerza 2
                                    //prisa 2
                                }else if(biome.getBiomeCategory().equals(Biome.Category.THEEND)) {
                                    player.addEffect(new EffectInstance(Effects.SLOW_FALLING, 20, 1, true, false));
                                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20, 3, true, false));
                                    //caida lenta//velocidad 3
                                }
                                else{
                                    System.out.println("no hay poderes papa");
                                }

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
                         * ALUMINUM POWER
                         ************************/
                        if (playerCapability.hasAllomanticPower(MetalsNBTData.ALUMINUM) && playerCapability.isBurning(MetalsNBTData.ALUMINUM)) {
                             for (MetalsNBTData metalsNBTData: playerCapability.getAllomanticPowers()){
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

                    // SYNC NETWORK
                    ModNetwork.sync(player);
                });
            }
        }
    }
}

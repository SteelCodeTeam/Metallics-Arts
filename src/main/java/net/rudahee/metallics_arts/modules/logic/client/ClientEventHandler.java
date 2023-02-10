package net.rudahee.metallics_arts.modules.logic.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.logic.client.client_events.OnKeyInputEvent;
import net.rudahee.metallics_arts.modules.logic.client.client_events.OnMouseInputEvent;
import net.rudahee.metallics_arts.modules.logic.client.client_events.OnRenderGameOverlay;
import net.rudahee.metallics_arts.modules.logic.client.client_events.OnRenderLevelStage;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals.IronAndSteelHelpers;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.network.packets.*;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModKeyRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.FoundNearbyMetalUtils;
import net.rudahee.metallics_arts.utils.powers_utils.ClientUtils;

public class ClientEventHandler {

    private final Minecraft mc = Minecraft.getInstance();


    private int tickOffset = 0;
    boolean count = true;

    BlockPos otherPlayerDeathPos = null;

    ResourceKey<Level> otherPlayerDimension = null;

    private int controlTick = 0;

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onClientTick(final TickEvent.ClientTickEvent event) {

        if (event.phase != TickEvent.Phase.END || this.mc.isPaused() || this.mc.player == null || !this.mc.player.isAlive()) {
            return;
        }

        Player player = this.mc.player;

        if (player != null) {
            player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(
                playerCapability -> {
                    if (playerCapability.isInvested()) {

                        FoundNearbyMetalUtils.redoLists(player, playerCapability);


                        if (++this.tickOffset > 9) {
                            this.tickOffset = 0;
                        }

                        if (ModKeyRegister.ALLOMANTIC_PULL.isDown()) {
                            HitResult trace = ClientUtils.getMouseOverExtended(11F * 1.5F);

                            /***********************************
                             * DO CLICK AN SOMETHING WITH  - IRON -
                             ***********************************/
                            if (playerCapability.isBurning(MetalTagEnum.IRON)) {
                                if (trace !=null){
                                    if (trace instanceof BlockHitResult) { // IF ITS A BLOCK
                                        BlockPos blockPosition = ((BlockHitResult) trace).getBlockPos();
                                        if (IronAndSteelHelpers.isBlockStateMetal(this.mc.level.getBlockState(blockPosition))) {
                                            ModNetwork.sendToServer(new PullAndPushBlockPacket(blockPosition,
                                                    Math.round(IronAndSteelHelpers.PULL * IronAndSteelHelpers.getMultiplier(player, playerCapability.getEnhanced() || playerCapability.getEnhanced(),
                                                            playerCapability.isBurning(MetalTagEnum.LERASIUM)))));
                                        }
                                    }
                                    if (trace instanceof EntityHitResult) {
                                        ModNetwork.sendToServer(
                                                new PullAndPushEntityPacket(((EntityHitResult) trace).getEntity().getId(),
                                                        Math.round(IronAndSteelHelpers.PULL * IronAndSteelHelpers.getMultiplier(player,playerCapability.getEnhanced() || playerCapability.getEnhanced(),
                                                                playerCapability.isBurning(MetalTagEnum.LERASIUM)))));
                                    }
                                }
                            }
                        }

                        /** LEFT CLICK (ATTACK) */

                        if (this.mc.options.keyAttack.isDown()) {
                            HitResult trace = ClientUtils.getMouseOverExtended(11F * 1.5F);
                            /***********************************
                             * DO CLICK AN ENTITY WITH  - ZINC -
                             ***********************************/
                            if (playerCapability.isBurning(MetalTagEnum.ZINC)) {
                                Entity entity;
                                if ((trace != null) && (trace.getType() == HitResult.Type.ENTITY)) {
                                    entity = ((EntityHitResult) trace).getEntity();
                                    if (entity instanceof Mob) {
                                        ModNetwork.sendToServer(new ChangeEmotionPacket(entity.getId(), true));
                                    }
                                }
                            }
                            /***********************************
                             * DO CLICK AN ENTITY WITH  - MALATIUM -
                             ***********************************/
                            if (playerCapability.isBurning(MetalTagEnum.MALATIUM)) {
                                Entity entity;
                                if ((trace != null) && (trace.getType() == HitResult.Type.ENTITY)) {
                                    entity = ((EntityHitResult) trace).getEntity();
                                    if (entity instanceof Player) {
                                        Player otherPlayer = (Player) entity;
                                        otherPlayer.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(cap -> {
                                            //otherPlayerDeathPos = new BlockPos(cap.getDeathPos()[0],cap.getDeathPos()[1],cap.getDeathPos()[2]);
                                            //otherPlayerDimension = GoldAndElectrumHelpers.getRegistryKeyFromString(cap.getDeathDimension());
                                        });
                                    }
                                }
                            }
                        }

                        if (!playerCapability.isBurning(MetalTagEnum.MALATIUM) && otherPlayerDeathPos != null) {
                            otherPlayerDeathPos = null;
                            otherPlayerDimension = null;
                        }

                        /***********************************
                         * DO CLICK AN ENTITY WITH  - STEEL -
                         ***********************************/
                        if (ModKeyRegister.ALLOMANTIC_PUSH.isDown()) {

                            HitResult trace = ClientUtils.getMouseOverExtended(11F * 1.5F);
                            if (playerCapability.isBurning(MetalTagEnum.STEEL)) {
                                if (trace !=null){
                                    if (trace instanceof BlockHitResult) { // IF ITS A BLOCK
                                        BlockPos blockPosition = ((BlockHitResult) trace).getBlockPos();
                                        if (IronAndSteelHelpers.isBlockStateMetal(this.mc.level.getBlockState(blockPosition))) {
                                            ModNetwork.sendToServer(new PullAndPushBlockPacket(blockPosition,
                                                    Math.round(IronAndSteelHelpers.PUSH * IronAndSteelHelpers.getMultiplier(player,playerCapability.getEnhanced() || playerCapability.getEnhanced(),
                                                            playerCapability.isBurning(MetalTagEnum.LERASIUM)))));
                                        }
                                    }
                                    if (trace instanceof EntityHitResult) {
                                        ModNetwork.sendToServer(
                                                new PullAndPushEntityPacket(((EntityHitResult) trace).getEntity().getId(),
                                                        Math.round(IronAndSteelHelpers.PUSH * IronAndSteelHelpers.getMultiplier(player,playerCapability.getEnhanced() || playerCapability.getEnhanced(),
                                                                playerCapability.isBurning(MetalTagEnum.LERASIUM)))));
                                    }
                                }
                            }
                        }

                        /** RIGHT CLICK (USE) */

                        if (this.mc.options.keyUse.isDown()) {
                            HitResult trace = ClientUtils.getMouseOverExtended(11F * 1.5F);

                            /***********************************
                             * DO CLICK AN ENTITY WITH  - BRASS -
                             ***********************************/
                            if (playerCapability.isBurning(MetalTagEnum.BRASS)) {
                                Entity entity;
                                if ((trace != null) && (trace.getType() == HitResult.Type.ENTITY)) {
                                    entity = ((EntityHitResult) trace).getEntity();
                                    if (entity instanceof Mob) {
                                        ModNetwork.sendToServer(new ChangeEmotionPacket(entity.getId(), false));
                                    }
                                }
                            }

                        }

                        /*if (player.isFallFlying() && playerCapability.isBurning(MetalsNBTData.STEEL)) {
                            player.setPose(Pose.FALL_FLYING);
                        }
                        if (!player.level.getBlockState(new BlockPos(player.position().x, player.position().y - 1, player.position().z)).is(Blocks.AIR)) {
                            player.stopFallFlying();
                        }
                        if(!playerCapability.isBurning(MetalsNBTData.STEEL)) {
                            player.stopFallFlying();
                        }*/

                        //if (this.mc.options.keyJump.isDown() && this.mc.options.keyShift.isDown() && playerCapability.isBurning(MetalsNBTData.STEEL)) {
                        if (this.mc.options.keyJump.isDown() && ModKeyRegister.VERTICAL_JUMP.isDown() && playerCapability.isBurning(MetalTagEnum.STEEL)) {

                            double x = player.getX();
                            double y = player.getY();
                            double z = player.getZ();

                            BlockPos blockPos = new BlockPos(x,y,z);
                            Vec3 vector = player.getViewVector(1.0F);

                            double pushX;
                            double pushZ;

                            int maxAltitude = 10;
                            int range = 7;

                            if (this.mc.options.keyUp.isDown()) {
                                pushX = x - (vector.x*range);
                                pushZ = z - (vector.z*range);
                            } else if (this.mc.options.keyDown.isDown()) {
                                pushX = x + (vector.x*range);
                                pushZ = z + (vector.z*range);

                           /* } else if (this.mc.options.keyRight.isDown()) { // <--------- revisar aqui
                                pushX = vector.x() >= 0 ? x - (vector.x*range) : x + (vector.x*range);
                                pushZ = vector.z() >= 0 ? z + (vector.z*range) : z - (vector.z*range);
                            } else if (this.mc.options.keyLeft.isDown()) {
                                pushX = vector.x() >= 0 ? x + (vector.x*range) : x - (vector.x*range);
                                pushZ = vector.z() >= 0 ? z - (vector.z*range) : z + (vector.z*range);*/
                            }  else {
                                pushX = x - (vector.x*range);
                                pushZ = z - (vector.z*range);
                            }
                            blockPos = new BlockPos(pushX, y, pushZ);

                            for (int i=0;i<maxAltitude;i++){
                                if (player.level.getBlockState(blockPos).is(Blocks.AIR)){
                                    blockPos = new BlockPos(blockPos.getX(),blockPos.getY()-1,blockPos.getZ());
                                }
                            }

                            if (!player.level.getBlockState(blockPos).is(Blocks.AIR)) {
                                // IF ITS A BLOCK
                                BlockPos blockPosition = blockPos;

                                int slot = IronAndSteelHelpers.haveNuggets(player);


                                if (IronAndSteelHelpers.isBlockStateMetal(this.mc.level.getBlockState(blockPosition))) {
                                    ModNetwork.sendToServer(new PullAndPushBlockPacket(blockPosition,
                                            Math.round(IronAndSteelHelpers.PUSH * IronAndSteelHelpers.getMultiplier(player,playerCapability.getEnhanced(),
                                                    playerCapability.isBurning(MetalTagEnum.LERASIUM)))));

                                } else if (slot != -1) {
                                    //player.getInventory().removeItem(slot, 1);
                                    ModNetwork.sendToServer(new PullAndPushNuggetPacket(blockPosition,
                                            Math.round(IronAndSteelHelpers.PUSH * IronAndSteelHelpers.getMultiplier(player,playerCapability.getEnhanced(),
                                                    playerCapability.isBurning(MetalTagEnum.LERASIUM)))));

                                    if (controlTick == 0 ){
                                        ModNetwork.sendToServer(new RemoveNuggetPacket(slot, player));
                                        controlTick = 18;
                                    }
                                }
                            }
                            if (controlTick>0) {
                                controlTick--;
                            }
                        }
                    }
                });
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onKeyInput(final InputEvent.Key event) {
        try {
            OnKeyInputEvent.onKeyInputEvent(event, Minecraft.getInstance(), Minecraft.getInstance().player, CapabilityUtils.getCapability(Minecraft.getInstance().player));
        } catch (PlayerException ex) {
            ex.printResumeLog();
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onMouseInput(final InputEvent.MouseButton event) {
        try {
            OnMouseInputEvent.OnMouseInputEvent(Minecraft.getInstance().player, CapabilityUtils.getCapability(Minecraft.getInstance().player));
        } catch (PlayerException ex) {
            ex.printResumeLog();
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onRenderGameOverlay(final RegisterGuiOverlaysEvent event) {

        try {
            OnRenderGameOverlay.onRenderGameOverlay(event, Minecraft.getInstance(), Minecraft.getInstance().player, CapabilityUtils.getCapability(Minecraft.getInstance().player));
        } catch (PlayerException ex) {
            ex.printResumeLog();
        }

    }



    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onRenderLevelStage(final RenderLevelStageEvent event) {
        try {
            OnRenderLevelStage.onRenderLevelStage(event, Minecraft.getInstance(), Minecraft.getInstance().player, CapabilityUtils.getCapability(Minecraft.getInstance().player));
        } catch (PlayerException ex) {
            ex.printResumeLog();
        }
    }


    public String getDimensionById(int dimension) {
        if (dimension == 0) {
            return "minecraft:overworld";
        } else if (dimension == 1) {
            return "minecraft:the_nether";
        } else {
            return "minecraft:the_end";
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onSound(PlaySoundEvent event) {

    }
}
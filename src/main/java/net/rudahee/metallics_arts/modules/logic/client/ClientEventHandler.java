package net.rudahee.metallics_arts.modules.logic.client;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.*;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidType;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.data.player.poses.CustomPoses;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.error_handling.utils.LoggerUtils;
import net.rudahee.metallics_arts.modules.logic.client.client_events.*;
import net.rudahee.metallics_arts.modules.logic.server.server_events.OnWorldTickEvent;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.setup.registries.ModRenderRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.MistUtils;

import java.awt.*;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Handles client-side events and custom rendering.
 * This class contains various methods that subscribe to specific events
 * and perform custom rendering or other client-side tasks.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class ClientEventHandler {


    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onRenderPlayerEvent(RenderPlayerEvent event) {
        Player player = event.getEntity();
        PlayerRenderer renderer = event.getRenderer();
        if (player != null) {
            ItemStack stack = player.getMainHandItem();
            if (player.getMainHandItem().is(ModItemsRegister.REVOLVER.get()) || player.getMainHandItem().is(ModItemsRegister.VINDICATOR.get())) {
                if (stack.getTag().getFloat("CustomModelData") == 1) {
                    renderer.getModel().rightArmPose = CustomPoses.getArmPose(CustomPoses.POSE_RIGHT_AIM);
                } else {
                    renderer.getModel().rightArmPose = CustomPoses.getArmPose(CustomPoses.POSE_RIGHT_REST);
                }
            } else if (player.getMainHandItem().is(ModItemsRegister.SHOTGUN.get()) || player.getMainHandItem().is(ModItemsRegister.RIFLE.get()) || player.getMainHandItem().is(ModItemsRegister.RIFLE_WITH_SPYGLASS.get())) {
               renderer.getModel().rightArmPose = CustomPoses.getArmPose(CustomPoses.POSE_BOTH_AIM);
            } else if (player.getMainHandItem().is(ModItemsRegister.KOLOSS_BLADE.get())) {
                if (player.getMainHandItem().hasTag()) {
                    try {
                        if (player.getMainHandItem().getTag().getFloat("CustomModelData") != 1.0f) {
                           renderer.getModel().rightArmPose = CustomPoses.getArmPose(CustomPoses.POSE_RIGHT_KOLOSS);
                        }
                    } catch (NullPointerException ex) {
                        LoggerUtils.printLogInfo(ex.getMessage());
                    }
                }
            }
        }
    }


    int tickCounter = 0;
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onfogevent(ViewportEvent.RenderFog event) {

        if (Minecraft.getInstance().player == null){
            return;

        }

        Player player = Minecraft.getInstance().player;

        Stream<TagKey<Biome>> biome =player.getLevel().getBiome(player.blockPosition()).getTagKeys();
        BlockPos playerpos = new BlockPos(player.getBlockX(), player.getBlockY()+20, player.getBlockZ());

        if ( biome.anyMatch(tagKey -> tagKey.equals(Tags.Biomes.IS_CAVE)) || player.isUnderWater() || !player.level.canSeeSky(playerpos) ){
            return;
        }


        try {
            IInvestedPlayerData capabilities = CapabilityUtils.getCapability(Minecraft.getInstance().player);
            if (capabilities.isBurning(MetalTagEnum.TIN) || capabilities.isTapping(MetalTagEnum.TIN)){
                event.setNearPlaneDistance(0);
                event.setFarPlaneDistance(295);
                event.setCanceled(true);

            }else if (MistUtils.mist(player, 12000, 14000)){

                event.setNearPlaneDistance(0);
                event.setFarPlaneDistance((float)(300 -  Math.floor(((double) (player.getLevel().getLevelData().getDayTime() - 12000) /7))));
                event.setCanceled(true);

            } else if (MistUtils.mist(player, 14000, 22000)){
                event.setNearPlaneDistance(0);
                event.setFarPlaneDistance(15);
                event.setCanceled(true);
            } else if (MistUtils.mist(player, 22000, 24000)) {

                event.setNearPlaneDistance(0);
                event.setFarPlaneDistance((float)(15 +  Math.floor(((double) (player.getLevel().getLevelData().getDayTime() - 22000) /7))));
                event.setCanceled(true);
            }

        } catch (PlayerException ex) {
            ex.printResumeLog();
        }

    }
        /**
         * This method is called when a client-side tick event occurs. It performs various
         * updates and checks related to the Minecraft player and their capabilities.
         *
         * @OnlyIn(Dist.CLIENT) This annotation ensures that this method is only used on the client side.
         * @SubscribeEvent This annotation registers the method as an event listener for the specified event type.
         *
         * @param event The TickEvent.ClientTickEvent instance representing the current tick event.
         */



    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onClientTick(final TickEvent.ClientTickEvent event) {
        try {
            if (Minecraft.getInstance().player == null) {
                return;
            }

            OnClientTick.onClientTick(event, Minecraft.getInstance(), Minecraft.getInstance().player, CapabilityUtils.getCapability(Minecraft.getInstance().player));

        } catch (PlayerException ex) {
            ex.printResumeLog();
        }



    }

    /**
     * Handles key input events to trigger certain actions in the game, such as tapping or storing powers, when specific keys are pressed.
     * This method is executed only on the client-side and listens for InputEvent.Key events.
     *
     * @param event the InputEvent.Key event that contains information about the key press
     * @see OnKeyInputEvent
     * @see CapabilityUtils
     */
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onKeyInput(final InputEvent.Key event) {
        try {
            if (Minecraft.getInstance().player == null) {
                return;
            }
            OnKeyInputEvent.onKeyInputEvent(event, Minecraft.getInstance(), Minecraft.getInstance().player, CapabilityUtils.getCapability(Minecraft.getInstance().player));
        } catch (PlayerException ex) {
            ex.printResumeLog();
        }
    }


    /**
     * The method first checks if the Minecraft player instance is null, and if so, it returns immediately, ensuring
     * that no further processing is done. This check is essential to prevent potential NullPointerExceptions.
     *
     * Next, the method delegates the handling of the key input event to the OnKeyInputEvent.onKeyInputEvent method,
     * passing along the current event, the Minecraft instance, the player, and the player's capability obtained
     * from the CapabilityUtils.getCapability method.
     *
     * @OnlyIn(Dist.CLIENT) This annotation ensures that this method is only used on the client side.
     * @SubscribeEvent This annotation registers the method as an event listener for the specified event type.
     *
     * @param event The InputEvent.Key instance representing the current key input event.
     */
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onMouseInput(final InputEvent.MouseButton.Post event) {
        try {
            if (Minecraft.getInstance().player == null) {
                return;
            }

            if (event.getButton() == 0 && event.getAction() == 1) {
                //button = 0 - Left click
                //action = 1 - Press button
                //if (!Minecraft.getInstance().screen.isPauseScreen()) {
                    OnMouseInputEvent.otro(Minecraft.getInstance().player, Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND));
                //}
            }
            OnMouseInputEvent.OnMouseInputEvent(Minecraft.getInstance().player, CapabilityUtils.getCapability(Minecraft.getInstance().player));
        } catch (PlayerException ex) {
            ex.printResumeLog();
        }
    }

        /**
         * Entrypoint of the client-side rendering of custom game overlays. the method delegates the handling of the overlay render
         * event to the OnRenderGameOverlay.onRenderGameOverlay method
         *
         * @OnlyIn(Dist.CLIENT) This annotation ensures that this method is only used on the client side.
         * @SubscribeEvent This annotation registers the method as an event listener for the specified event type.
         *
         * @param event The RegisterGuiOverlaysEvent instance representing the current game overlay registration event.
         */
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onRenderGameOverlay(final RegisterGuiOverlaysEvent event) {
        try {
            if (Minecraft.getInstance().player == null) {
                return;
            }
            OnRenderGameOverlay.onRenderGameOverlay(event, Minecraft.getInstance(), Minecraft.getInstance().player, CapabilityUtils.getCapability(Minecraft.getInstance().player));

        } catch (PlayerException ex) {
            ex.printResumeLog();
        }

    }



    /**
     * Entrypoint of the client-side rendering of custom level stage elements. the method delegates the handling of the stage render
     * event to the OnRenderLevelStage.onRenderLevelStage method
     *
     * @OnlyIn(Dist.CLIENT) This annotation ensures that this method is only used on the client side.
     * @SubscribeEvent This annotation registers the method as an event listener for the specified event type.
     *
     * @param event The RenderLevelStageEvent instance representing the current level stage rendering event.
     */
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onRenderLevelStage(final RenderLevelStageEvent event) {
        try {
            if (Minecraft.getInstance().player == null) {
                return;
            }
            OnRenderLevelStage.onRenderLevelStage(event, Minecraft.getInstance(), Minecraft.getInstance().player, CapabilityUtils.getCapability(Minecraft.getInstance().player));
        } catch (PlayerException ex) {
            ex.printResumeLog();
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onEntityRender(final EntityRenderersEvent.RegisterRenderers event) {
        ModRenderRegister.register(event);
    }




}
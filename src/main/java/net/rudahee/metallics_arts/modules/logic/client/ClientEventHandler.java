package net.rudahee.metallics_arts.modules.logic.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.logic.client.client_events.*;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

/**
 * Handles client-side events and custom rendering.
 * This class contains various methods that subscribe to specific events
 * and perform custom rendering or other client-side tasks.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class ClientEventHandler {

    private final Minecraft mc = Minecraft.getInstance();

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
    public void onMouseInput(final InputEvent.MouseButton event) {
        try {
            if (Minecraft.getInstance().player == null) {
                return;
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

}
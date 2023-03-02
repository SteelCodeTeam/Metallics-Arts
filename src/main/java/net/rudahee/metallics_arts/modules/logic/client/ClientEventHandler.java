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

public class ClientEventHandler {

    private final Minecraft mc = Minecraft.getInstance();

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onClientTick(final TickEvent.ClientTickEvent event) {
        try {
            OnClientTick.onClientTick(event, Minecraft.getInstance(), Minecraft.getInstance().player, CapabilityUtils.getCapability(Minecraft.getInstance().player));
        } catch (PlayerException ex) {
            ex.printResumeLog();
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

}
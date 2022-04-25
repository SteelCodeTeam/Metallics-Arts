package net.rudahee.metallics_arts.modules.powers.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.rudahee.metallics_arts.modules.client.GUI.AllomanticMetalOverlay;
import net.rudahee.metallics_arts.modules.client.GUI.FeruchemicMetalOverlay;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.util.HashSet;
import java.util.Set;

public class ClientEventHandler {


    private final Minecraft mc = Minecraft.getInstance();

    private final Set<Entity> metal_entities = new HashSet<>();
    private final Set<PlayerEntity> nearby_allomancers = new HashSet<>();

    private int tickOffset = 0;

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onClientTick(final TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            PlayerEntity player = this.mc.player;

            if (player != null && player instanceof PlayerEntity) {
                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(
                        playerCapability -> {


                });
            }
        }

    }


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onKeyInput(final InputEvent.KeyInputEvent event) {
        if (event.getAction() == GLFW.GLFW_PRESS) {
            acceptInput();
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent event) {
        AllomanticMetalOverlay.drawMetalOverlay(event.getMatrixStack());
        FeruchemicMetalOverlay.drawMetalOverlay(event.getMatrixStack());

    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onMouseInput(final InputEvent.MouseInputEvent event) {
        if (event.getAction() == GLFW.GLFW_PRESS) {
            acceptInput();
        }
    }

    /**
     * Handles either mouse or button presses for the mod's keybinds
     */
    private void acceptInput() {

    }



    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        PlayerEntity player = this.mc.player;
        if (player == null || !player.isAlive() || this.mc.options.getCameraType().isMirrored()) {
            return;
        }

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {

            if (!data.isInvested()) {
                return;
            }


            // TODO investigate depreciation
            RenderSystem.pushMatrix();
            RenderSystem.disableTexture();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.polygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.enableBlend();

            double dist = 1;
            double yaw = ((this.mc.player.yRot + 90) * Math.PI) / 180;
            double pitch = ((this.mc.player.xRot + 90) * Math.PI) / 180;
        });
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onSound(PlaySoundEvent event) {

    }
}

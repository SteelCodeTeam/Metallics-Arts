package net.rudahee.metallics_arts.modules.powers.client;

import com.google.common.graph.Network;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.rudahee.metallics_arts.data.network.ChangeEmotionPacket;
import net.rudahee.metallics_arts.modules.client.ClientUtils;
import net.rudahee.metallics_arts.modules.client.GUI.AllomanticMetalOverlay;
import net.rudahee.metallics_arts.modules.client.GUI.FeruchemyMetalSelector;
import net.rudahee.metallics_arts.modules.client.GUI.MetalSelector;
import net.rudahee.metallics_arts.modules.client.KeyInit;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import org.lwjgl.glfw.GLFW;

import java.util.HashSet;
import java.util.Set;

public class PowersClientEventHandler {


    private final Minecraft mc = Minecraft.getInstance();

    private final Set<Entity> metal_entities = new HashSet<>();
    private final Set<PlayerEntity> nearby_allomancers = new HashSet<>();

    private final int tickOffset = 0;

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onClientTick(final TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            PlayerEntity player = this.mc.player;

            if (player != null && player instanceof PlayerEntity) {
                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(
                    playerCapability -> {
                        if (playerCapability.isInvested()) {

                            /** LEFT CLICK (ATTACK) */

                            if (this.mc.options.keyAttack.isDown()) {
                                RayTraceResult trace = ClientUtils.getMouseOverExtended(20F * 1.5F);

                                /***********************************
                                 * DO CLICK AN ENTITY WITH  - ZINC -
                                 ***********************************/
                                if (playerCapability.isBurning(MetalsNBTData.ZINC)) {
                                    Entity entity;
                                    if ((trace != null) && (trace.getType() == RayTraceResult.Type.ENTITY)) {
                                        entity = ((EntityRayTraceResult) trace).getEntity();
                                        if (entity instanceof CreatureEntity) {
                                            ModNetwork.sendToServer(new ChangeEmotionPacket(entity.getId(), true));
                                        }
                                    }
                                }
                            }

                            /** RIGHT CLICK (USE) */

                            if (this.mc.options.keyUse.isDown()) {
                                RayTraceResult trace = ClientUtils.getMouseOverExtended(20F * 1.5F);

                                /***********************************
                                 * DO CLICK AN ENTITY WITH  - BRASS -
                                 ***********************************/
                                if (playerCapability.isBurning(MetalsNBTData.BRASS)) {
                                    Entity entity;
                                    if ((trace != null) && (trace.getType() == RayTraceResult.Type.ENTITY)) {
                                        entity = ((EntityRayTraceResult) trace).getEntity();
                                        if (entity instanceof CreatureEntity) {
                                            ModNetwork.sendToServer(new ChangeEmotionPacket(entity.getId(), false));
                                        }
                                    }
                                }
                            }
                        }
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
        if (KeyInit.allomancy.isDown()){
            PlayerEntity player = this.mc.player;
            if (this.mc.screen == null){
                if (player==null || !this.mc.isWindowActive()){
                    return;
                }
                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{
                    //ClientUtils.toggleBurn(MetalsNBTData.BENDALLOY, data); Llamada a funcion de quemar cruck

                    // EL SUICIDIO ES UNA OPCION
                    int num_powers = data.getAllomanticPowerCount();
                    if (num_powers == 0){
                        return;
                    }
                    else {
                        this.mc.setScreen(new MetalSelector());
                    }
                });
            }
        }
        if (KeyInit.feruchemic.isDown()){
            PlayerEntity player = this.mc.player;
            if (this.mc.screen == null){
                if (player==null || !this.mc.isWindowActive()){
                    return;
                }
                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{
                    //ClientUtils.toggleBurn(MetalsNBTData.BENDALLOY, data); Llamada a funcion de quemar cruck

                    // EL SUICIDIO ES UNA OPCION
                    int num_powers = data.getFeruchemicPowerCount();
                    if (num_powers == 0){
                        return;
                    }
                    else {
                        this.mc.setScreen(new FeruchemyMetalSelector());
                    }
                });
            }
        }

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

        });
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onSound(PlaySoundEvent event) {

    }
}

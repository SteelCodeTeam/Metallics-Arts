package net.rudahee.metallics_arts.modules.logic.client.client_events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.error_handling.messages.ErrorTypes;
import net.rudahee.metallics_arts.modules.logic.client.custom_guis.selectors.AllomanticSelector;
import net.rudahee.metallics_arts.modules.logic.client.custom_guis.selectors.FeruchemySelector;
import net.rudahee.metallics_arts.setup.registries.ModKeyRegister;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class OnRenderGameOverlay {

    @OnlyIn(Dist.CLIENT)
    public static void onRenderGameOverlay(RegisterGuiOverlaysEvent event, Minecraft minecraft, @Nullable Player player, @Nullable IInvestedPlayerData capability) throws PlayerException {
        if ((minecraft.screen instanceof AllomanticSelector) || (minecraft.screen instanceof FeruchemySelector)) {
            return;
        }

        if (!minecraft.isWindowActive() || !minecraft.player.isAlive()) {
            return;
        }
        if (minecraft.screen != null && minecraft.screen instanceof ChatScreen) {
            return;
        }

        if (player == null) {
            throw new PlayerException(ErrorTypes.PLAYER_ERROR);
        }

        if (ModKeyRegister.ALLOMANTIC_POWER_SELECTOR.isDown()) {
            if (minecraft.screen == null) {
                if (!minecraft.isWindowActive()) {
                    return;
                }
                
                minecraft.setScreen(new AllomanticSelector());

            }
        }

        if (ModKeyRegister.FERUCHEMIC_POWER_SELECTOR.isDown()) {
            if (minecraft.screen == null) {
                if (!minecraft.isWindowActive()) {
                    return;
                }

                minecraft.setScreen(new FeruchemySelector());

            }
        }
    }
}

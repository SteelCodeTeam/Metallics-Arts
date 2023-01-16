package net.rudahee.metallics_arts.modules.logic.client.client_events;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.error_handling.messages.ErrorTypes;
import net.rudahee.metallics_arts.modules.logic.client.custom_guis.selectors.AllomanticSelector;
import net.rudahee.metallics_arts.modules.logic.client.custom_guis.selectors.FeruchemySelector;
import net.rudahee.metallics_arts.setup.registries.ModKeyRegister;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class OnKeyInputEvent {

    @OnlyIn(Dist.CLIENT)
    public static void onKeyInputEvent(InputEvent.Key event, Minecraft minecraft, @Nullable Player player, @Nullable IInvestedPlayerData capability) throws PlayerException {
        if (minecraft.screen != null || !minecraft.isWindowActive() || event.getAction() != GLFW.GLFW_PRESS) {
            return;
        }

        if (player == null) {
            throw new PlayerException(ErrorTypes.PLAYER_ERROR);
        }
        if (capability == null) {
            throw new PlayerException(ErrorTypes.PLAYER_CAPABILITY_ERROR);
        }


        if (ModKeyRegister.ALLOMANTIC_POWER_SELECTOR.isDown()) {
            if (capability.hasAnyAllomanticPower()) {
                minecraft.setScreen(new AllomanticSelector());
            }
        } else if (ModKeyRegister.FERUCHEMIC_POWER_SELECTOR.isDown()) {
            if (capability.hasAnyFeruchemicPower()) {
                minecraft.setScreen(new FeruchemySelector());
            }
        }

    }

}

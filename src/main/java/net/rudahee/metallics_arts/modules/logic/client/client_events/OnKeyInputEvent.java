package net.rudahee.metallics_arts.modules.logic.client.client_events;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.custom_items.weapons.guns.BasicGun;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.error_handling.messages.ErrorTypes;
import net.rudahee.metallics_arts.modules.logic.client.custom_guis.selectors.AllomanticSelector;
import net.rudahee.metallics_arts.modules.logic.client.custom_guis.selectors.FeruchemySelector;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.network.packets.ReloadGunPacket;
import net.rudahee.metallics_arts.setup.registries.ModKeyRegister;
import net.rudahee.metallics_arts.utils.powers_utils.ClientUtils;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;

/**
 * This class handles the custom key input events on the client side.
 * It is responsible for displaying the Allomantic or Feruchemic power selector screens
 * when the respective custom keybinds are pressed, provided that the player has the required powers.
 * This class should only be used on the client side.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
@OnlyIn(Dist.CLIENT)
public class OnKeyInputEvent {

    /**
     * Processes a KeyInputEvent and displays the respective power selector screen
     * if the custom keybinds are pressed and the player has the required powers.
     * This method should only be called on the client side.
     *<p>
     * Conditions to process the key input event:
     * - The Minecraft screen should be null.
     * - The Minecraft window should be active.
     * - The event action should be GLFW_PRESS.
     *<p>
     * The method checks for the following keybinds:
     * - ALLOMANTIC_POWER_SELECTOR: Displays the AllomanticSelector screen if the player has any Allomantic power.
     * - FERUCHEMIC_POWER_SELECTOR: Displays the FeruchemySelector screen if the player has any Feruchemic power.
     *
     * @param event        The InputEvent.Key to be processed.
     * @param minecraft    The instance of the Minecraft game.
     * @param player       The player character, nullable.
     * @param capability   The IInvestedPlayerData capability of the player, nullable.
     * @throws PlayerException If any error occurs related to the player or player capability.
     */
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
        if (ModKeyRegister.RELOAD.isDown()) {
            if (player.getMainHandItem().getItem() instanceof BasicGun) {
                ModNetwork.sendToServer(new ReloadGunPacket());
            }
        }
        for (int i = 0; i< MetalTagEnum.values().length; i++) {
            if (ModKeyRegister.powers[i].isDown()) {
                if (ModKeyRegister.FERUCHEMIC_DECANT.isDown()) {
                    ClientUtils.toggleTap(MetalTagEnum.getMetal(i), capability, player);
                } else if (ModKeyRegister.FERUCHEMIC_STORAGE.isDown()) {
                    ClientUtils.toggleStorage(MetalTagEnum.getMetal(i), capability, player);
                } else {
                    ClientUtils.toggleBurn(MetalTagEnum.getMetal(i), capability);
                }
            }
        }

    }
}

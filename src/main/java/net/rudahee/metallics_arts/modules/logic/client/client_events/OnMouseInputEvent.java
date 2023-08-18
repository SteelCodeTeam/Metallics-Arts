package net.rudahee.metallics_arts.modules.logic.client.client_events;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.rudahee.metallics_arts.data.enums.implementations.GunsAccess;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.custom_items.weapons.guns.BasicGun;
import net.rudahee.metallics_arts.modules.custom_items.weapons.guns.GunUtils;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.error_handling.messages.ErrorTypes;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.network.packets.FiringGunPacket;

/**
 * OnMouseInputEvent is a client-side class responsible for processing
 * MouseInputEvents and toggling the respective powers based on custom keybinds
 * and the player's capabilities.
 *<p>
 * The class contains a static method, OnMouseInputEvent, which takes a Player
 * instance and an IInvestedPlayerData capability as arguments. The method
 * iterates through all MetalTagEnum values and checks for the following
 * keybinds: FERUCHEMIC_DECANT, FERUCHEMIC_STORAGE, and the default power
 * keybind.
 *<p>
 * If any error occurs related to the player or player capability, a
 * PlayerException is thrown.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
@OnlyIn(Dist.CLIENT)
public class OnMouseInputEvent {

    /**
     * Processes MouseInputEvents and toggles the respective powers
     * based on custom keybinds and the player's capabilities.
     * This method should only be called on the client side.
     *<p>
     * The method iterates through all the MetalTagEnum values and checks for the following keybinds:
     * - FERUCHEMIC_DECANT: Toggles the tapping of the metal if the specific power key is down.
     * - FERUCHEMIC_STORAGE: Toggles the storage of the metal if the specific power key is down.
     * - Default: Toggles the burning of the metal if the specific power key is down.
     *
     * @param player       The player character, nullable.
     * @param capability   The IInvestedPlayerData capability of the player, nullable.
     * @throws PlayerException If any error occurs related to the player or player capability.
     */
    @OnlyIn(Dist.CLIENT)
    public static void OnMouseInputEvent(Player player, IInvestedPlayerData capability) throws PlayerException {

        if (player == null) {
            throw new PlayerException(ErrorTypes.PLAYER_ERROR);
        }
        if (capability == null) {
            throw new PlayerException(ErrorTypes.PLAYER_CAPABILITY_ERROR);
        }

        /*
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
        }*/
    }

    /*@OnlyIn(Dist.CLIENT)
    public static void otro(LocalPlayer player, ItemStack itemInHand) {
        if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof BasicGun instance) {
            if (!itemInHand.hasTag()) {
                itemInHand.setTag(GunUtils.generateGunTags(instance.getGunType()));
            }
            if (itemInHand.getTag().getInt(GunsAccess.BULLETS.getKey()) > 0) {
                ModNetwork.sendToServer(new FiringGunPacket());
            }
        }
    }*/


}

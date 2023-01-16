package net.rudahee.metallics_arts.modules.logic.client.client_events;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.error_handling.messages.ErrorTypes;
import net.rudahee.metallics_arts.setup.registries.ModKeyRegister;
import net.rudahee.metallics_arts.utils.powers_utils.ClientUtils;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class OnMouseInputEvent {

    @OnlyIn(Dist.CLIENT)
    public static void OnMouseInputEvent(@Nullable Player player, @Nullable IInvestedPlayerData capability) throws PlayerException {

        if (player == null) {
            throw new PlayerException(ErrorTypes.PLAYER_ERROR);
        }
        if (capability == null) {
            throw new PlayerException(ErrorTypes.PLAYER_CAPABILITY_ERROR);
        }


        for (int i = 0; i< MetalTagEnum.values().length; i++) {
            if (ModKeyRegister.powers[i].isDown()) {
                if (ModKeyRegister.FERUCHEMIC_DECANT.isDown()) {
                    ClientUtils.toggleDecant(MetalTagEnum.getMetal(i), capability, player);
                } else if (ModKeyRegister.FERUCHEMIC_STORAGE.isDown()) {
                    ClientUtils.toggleStorage(MetalTagEnum.getMetal(i), capability, player);
                } else {
                    ClientUtils.toggleBurn(MetalTagEnum.getMetal(i), capability);
                }
            }
        }
    }
}

package net.rudahee.metallics_arts.modules.powers.helpers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class ChromiumAndNicrosilHelpers {
    public static void drainMetalChromium(PlayerEntity entityLiving) {
        entityLiving.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{
            for (MetalsNBTData metalsNBTData : data.getAllomanticPowers()){
                data.drainMetals(metalsNBTData);
            }
        });
    }
}

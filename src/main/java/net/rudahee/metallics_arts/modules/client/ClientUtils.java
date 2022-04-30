package net.rudahee.metallics_arts.modules.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.rudahee.metallics_arts.modules.data_player.IDefaultInvestedPlayerData;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

@OnlyIn(Dist.CLIENT)
public class ClientUtils {


    public static void toggleBurn(MetalsNBTData  metal, IDefaultInvestedPlayerData capability) {
        if (!capability.hasAllomanticPower(metal)) {
            return;
        }

        //new UpdateBurnPacket(metal, !capability.isBurning(metal))
        ModNetwork.sendToServer("a");

        if (capability.getAllomanticAmount(metal) > 0) {
            capability.setBurning(metal, !capability.isBurning(metal));
        }
        // play a sound effect

        /*if (capability.isBurning(metal)) {
            player.playSound(new SoundEvent(new ResourceLocation("item.flintandsteel.use")), 1, 5);
        } else {
            player.playSound(new SoundEvent(new ResourceLocation("block.fire.extinguish")), 1, 4);
        }*/
    }
}

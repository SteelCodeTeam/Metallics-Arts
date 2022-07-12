package net.rudahee.metallics_arts.modules.items.metalminds.rings;

import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class RingChromiumNicrosil extends RingsMindAbstract{

    public RingChromiumNicrosil(Properties properties){
        super(properties, MetalsNBTData.CHROMIUM,MetalsNBTData.NICROSIL,MetalsNBTData.CHROMIUM.getMaxReserveRing(),MetalsNBTData.NICROSIL.getMaxReserveRing());
    }
}
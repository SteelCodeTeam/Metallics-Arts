package net.rudahee.metallics_arts.modules.items.metalminds.rings;

import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class RingTinPewter extends RingsMindAbstract{
    public RingTinPewter(Properties properties){
        super(properties, MetalsNBTData.TIN,MetalsNBTData.PEWTER,MetalsNBTData.TIN.getMaxReserveRing(),MetalsNBTData.PEWTER.getMaxReserveRing());
    }
}

package net.rudahee.metallics_arts.modules.items.metalminds.rings;

import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class RingAluminumDuralumin extends RingsMindAbstract implements ICurioItem{

    public RingAluminumDuralumin (Properties properties){
        super(properties, MetalsNBTData.ALUMINUM,MetalsNBTData.DURALUMIN,MetalsNBTData.ALUMINUM.getMaxReserveRing(),MetalsNBTData.DURALUMIN.getMaxReserveRing());
    }


}
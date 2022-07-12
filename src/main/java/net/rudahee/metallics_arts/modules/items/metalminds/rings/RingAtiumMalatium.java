package net.rudahee.metallics_arts.modules.items.metalminds.rings;

import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class RingAtiumMalatium extends RingsMindAbstract {
    public RingAtiumMalatium (Properties properties){
        super(properties, MetalsNBTData.ATIUM,MetalsNBTData.MALATIUM,MetalsNBTData.ATIUM.getMaxReserveRing(),MetalsNBTData.MALATIUM.getMaxReserveRing());
    }
}
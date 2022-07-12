package net.rudahee.metallics_arts.modules.items.metalminds.rings;

import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class RingCopperBronze extends RingsMindAbstract{
    public RingCopperBronze(Properties properties){
        super(properties, MetalsNBTData.COPPER,MetalsNBTData.BRONZE,MetalsNBTData.COPPER.getMaxReserveRing(),MetalsNBTData.BRONZE.getMaxReserveRing());
    }
}
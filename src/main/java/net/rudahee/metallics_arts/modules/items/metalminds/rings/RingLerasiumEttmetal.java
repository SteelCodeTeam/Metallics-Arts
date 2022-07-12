package net.rudahee.metallics_arts.modules.items.metalminds.rings;

import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class RingLerasiumEttmetal extends RingsMindAbstract{
    public RingLerasiumEttmetal (Properties properties){
        super(properties, MetalsNBTData.LERASIUM,MetalsNBTData.ETTMETAL,MetalsNBTData.LERASIUM.getMaxReserveRing(), MetalsNBTData.ETTMETAL.getMaxReserveRing());
    }
}
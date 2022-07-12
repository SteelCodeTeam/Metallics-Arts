package net.rudahee.metallics_arts.modules.items.metalminds.rings;

import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class RingSteelIron extends RingsMindAbstract{
    public RingSteelIron (Properties properties){
        super(properties, MetalsNBTData.IRON,MetalsNBTData.STEEL,MetalsNBTData.IRON.getMaxReserveRing(),MetalsNBTData.STEEL.getMaxReserveRing());
    }
}
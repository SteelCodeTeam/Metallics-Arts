package net.rudahee.metallics_arts.modules.items.metalminds.rings;

import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class RingCadmiumBendalloy extends RingsMindAbstract{
    public RingCadmiumBendalloy (Properties properties){
        super(properties, MetalsNBTData.CADMIUM,MetalsNBTData.BENDALLOY,MetalsNBTData.CADMIUM.getMaxReserveRing(),MetalsNBTData.BENDALLOY.getMaxReserveRing());
    }

}
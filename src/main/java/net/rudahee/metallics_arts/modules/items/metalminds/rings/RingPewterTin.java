package net.rudahee.metallics_arts.modules.items.metalminds.rings;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class RingPewterTin extends RingsMindAbstract{
    public RingPewterTin (Properties properties){
        super(properties, MetalsNBTData.TIN,MetalsNBTData.PEWTER,MetalsNBTData.TIN.getMaxReserveRing(),MetalsNBTData.PEWTER.getMaxReserveRing());
    }
}

package net.rudahee.metallics_arts.modules.items.metalminds.rings;


import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class RingIronSteel extends RingsMindAbstract{
    public RingIronSteel(Properties properties){
        super(properties, MetalsNBTData.IRON,MetalsNBTData.STEEL,MetalsNBTData.IRON.getMaxReserveRing(),MetalsNBTData.STEEL.getMaxReserveRing());
    }
}
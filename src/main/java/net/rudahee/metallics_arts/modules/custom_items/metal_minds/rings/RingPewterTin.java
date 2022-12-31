package net.rudahee.metallics_arts.modules.custom_items.metal_minds.rings;

import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.RingsMindAbstract;

public class RingPewterTin extends RingsMindAbstract {
    public RingPewterTin(Properties properties){
        super(properties, MetalTagEnum.TIN, MetalTagEnum.PEWTER, MetalTagEnum.TIN.getMaxReserveRing(), MetalTagEnum.PEWTER.getMaxReserveRing());
    }
}

package net.rudahee.metallics_arts.modules.custom_items.metal_minds.rings;

import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.RingsMindAbstract;

public class RingCadmiumBendalloy extends RingsMindAbstract {
    public RingCadmiumBendalloy (Properties properties){
        super(properties, MetalTagEnum.CADMIUM, MetalTagEnum.BENDALLOY, MetalTagEnum.CADMIUM.getMaxReserveRing(), MetalTagEnum.BENDALLOY.getMaxReserveRing());
    }

}
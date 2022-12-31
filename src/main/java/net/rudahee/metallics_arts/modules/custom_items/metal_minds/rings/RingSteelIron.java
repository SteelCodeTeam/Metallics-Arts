package net.rudahee.metallics_arts.modules.custom_items.metal_minds.rings;


import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.RingsMindAbstract;

public class RingSteelIron extends RingsMindAbstract {
    public RingSteelIron(Properties properties){
        super(properties, MetalTagEnum.IRON, MetalTagEnum.STEEL, MetalTagEnum.IRON.getMaxReserveRing(), MetalTagEnum.STEEL.getMaxReserveRing());
    }
}
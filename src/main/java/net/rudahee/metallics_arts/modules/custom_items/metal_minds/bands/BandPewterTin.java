package net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands;

import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.BandMindAbstract;

public class BandPewterTin extends BandMindAbstract {
    public BandPewterTin(Item.Properties properties){
        super(properties, MetalTagEnum.TIN, MetalTagEnum.PEWTER, MetalTagEnum.TIN.getMaxReserveBand(), MetalTagEnum.PEWTER.getMaxReserveBand());
    }

}

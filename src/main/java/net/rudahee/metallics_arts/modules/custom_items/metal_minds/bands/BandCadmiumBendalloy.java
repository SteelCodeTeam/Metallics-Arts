package net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands;

import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.BandMindAbstract;

public class BandCadmiumBendalloy extends BandMindAbstract {
    public BandCadmiumBendalloy (Item.Properties properties){
        super(properties, MetalTagEnum.CADMIUM, MetalTagEnum.BENDALLOY, MetalTagEnum.CADMIUM.getMaxReserveBand(), MetalTagEnum.BENDALLOY.getMaxReserveBand());
    }

}
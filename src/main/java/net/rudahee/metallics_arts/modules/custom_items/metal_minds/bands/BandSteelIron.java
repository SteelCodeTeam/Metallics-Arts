package net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands;

import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.BandMindAbstract;

public class BandSteelIron extends BandMindAbstract {
    public BandSteelIron(Item.Properties properties){
        super(properties, MetalTagEnum.IRON, MetalTagEnum.STEEL, MetalTagEnum.IRON.getMaxReserveBand(), MetalTagEnum.STEEL.getMaxReserveBand());
    }

}
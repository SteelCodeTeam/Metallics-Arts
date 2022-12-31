package net.rudahee.metallics_arts.modules.custom_items.metal_spikes;

import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;

public class IronSpike extends MetalSpikeAbstract{

    public IronSpike(Item.Properties properties) {
        super(properties, MetalTagEnum.IRON);
    }
}

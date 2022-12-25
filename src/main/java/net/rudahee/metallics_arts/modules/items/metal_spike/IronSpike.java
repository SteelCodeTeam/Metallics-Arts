package net.rudahee.metallics_arts.modules.items.metal_spike;

import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.data.enums.implementations.MetalsNBTData;

public class IronSpike extends MetalSpikeAbstract{

    public IronSpike(Item.Properties properties) {
        super(properties,MetalsNBTData.IRON);
    }
}

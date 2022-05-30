package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.item.Item;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class BandZincBrass extends BandMindAbstract {

    public BandZincBrass (Item.Properties properties){
        super(properties, MetalsNBTData.ZINC,MetalsNBTData.BRASS,MetalsNBTData.ZINC.getMaxReserveBand(),MetalsNBTData.BRASS.getMaxReserveBand());
    }
}
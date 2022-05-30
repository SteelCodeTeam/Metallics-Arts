package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.item.Item;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class BandCopperBronze extends BandMindAbstract {

    public BandCopperBronze(Item.Properties properties){
        super(properties, MetalsNBTData.COPPER,MetalsNBTData.BRONZE,MetalsNBTData.COPPER.getMaxReserveBand(),MetalsNBTData.BRONZE.getMaxReserveBand());
    }
}
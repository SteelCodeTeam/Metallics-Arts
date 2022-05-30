package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.item.Item;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class BandChromiumNicrosil extends BandMindAbstract {

    public BandChromiumNicrosil(Item.Properties properties){
        super(properties, MetalsNBTData.CHROMIUM,MetalsNBTData.NICROSIL,MetalsNBTData.CHROMIUM.getMaxReserveBand(),MetalsNBTData.NICROSIL.getMaxReserveBand());
    }

}
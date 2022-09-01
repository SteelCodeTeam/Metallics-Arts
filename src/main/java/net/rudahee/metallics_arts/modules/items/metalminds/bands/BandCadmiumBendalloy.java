package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class BandCadmiumBendalloy extends BandMindAbstract {

    public BandCadmiumBendalloy (Item.Properties properties){
        super(properties, MetalsNBTData.CADMIUM,MetalsNBTData.BENDALLOY,MetalsNBTData.CADMIUM.getMaxReserveBand(),MetalsNBTData.BENDALLOY.getMaxReserveBand());

    }

}
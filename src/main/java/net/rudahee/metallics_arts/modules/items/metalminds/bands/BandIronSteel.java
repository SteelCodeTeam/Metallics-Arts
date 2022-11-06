package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class BandIronSteel extends BandMindAbstract {
    public BandIronSteel(Item.Properties properties){
        super(properties, MetalsNBTData.IRON,MetalsNBTData.STEEL,MetalsNBTData.IRON.getMaxReserveBand(),MetalsNBTData.STEEL.getMaxReserveBand());
    }

}
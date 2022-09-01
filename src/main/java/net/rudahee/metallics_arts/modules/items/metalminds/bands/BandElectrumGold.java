package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class BandElectrumGold extends BandMindAbstract {

    public BandElectrumGold(Item.Properties properties) {
        super(properties, MetalsNBTData.GOLD, MetalsNBTData.ELECTRUM,MetalsNBTData.GOLD.getMaxReserveBand(),MetalsNBTData.ELECTRUM.getMaxReserveBand());
    }

}
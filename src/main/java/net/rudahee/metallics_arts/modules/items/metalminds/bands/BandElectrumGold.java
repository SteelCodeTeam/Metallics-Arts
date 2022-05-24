package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class BandElectrumGold extends BandMindAbstract {

    private static int MAX_GOLD = 500;
    private static int MAX_ELECTRUM = 16000;

    public BandElectrumGold(Item.Properties properties) {
        super(properties, MetalsNBTData.GOLD, MetalsNBTData.ELECTRUM,MAX_GOLD,MAX_ELECTRUM);
    }

}
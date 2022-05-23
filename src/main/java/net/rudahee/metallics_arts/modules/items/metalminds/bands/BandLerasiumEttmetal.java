package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class BandLerasiumEttmetal extends BandMindAbstract {

    public BandLerasiumEttmetal (Item.Properties properties){
        super(properties, MetalsNBTData.LERASIUM,MetalsNBTData.ETTMETAL);
    }

}
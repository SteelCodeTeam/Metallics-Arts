package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class BandZincBrass extends BandMindAbstract {

    public BandZincBrass (Item.Properties properties){
        super(properties, MetalsNBTData.ZINC,MetalsNBTData.BRASS);
    }
}
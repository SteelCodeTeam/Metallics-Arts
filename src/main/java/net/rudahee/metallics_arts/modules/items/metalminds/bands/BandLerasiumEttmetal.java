package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class BandLerasiumEttmetal extends BandMindAbstract {

    private static int MAX_LERASIUM = 100;
    private static int MAX_ETTMETAL = 16000;

    public BandLerasiumEttmetal (Item.Properties properties){
        super(properties, MetalsNBTData.LERASIUM,MetalsNBTData.ETTMETAL,MAX_LERASIUM,MAX_ETTMETAL);
    }

}
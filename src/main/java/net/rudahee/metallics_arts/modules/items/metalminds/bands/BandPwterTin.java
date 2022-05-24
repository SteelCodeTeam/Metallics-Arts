package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class BandPwterTin extends BandMindAbstract {
    private static int MAX_TIN = 1000;
    private static int MAX_PEWTER = 16000;

    public BandPwterTin (Item.Properties properties){
        super(properties, MetalsNBTData.TIN,MetalsNBTData.PEWTER,MAX_TIN,MAX_PEWTER);


    }

}

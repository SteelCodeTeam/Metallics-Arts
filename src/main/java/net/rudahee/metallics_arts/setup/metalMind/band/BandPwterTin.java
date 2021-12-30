package net.rudahee.metallics_arts.setup.metalMind.band;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;

public class BandPwterTin extends BandMindAbstract {
    CompoundNBT nbt;
    public BandPwterTin (Item.Properties properties){
        super(properties);
        nbt.putInt("tin",0);
        nbt.putInt("pewter",0);
        nbt.putInt("capacityTin",100);
        nbt.putInt("capacityPewter",100);
        setNbt(nbt);
    }
}

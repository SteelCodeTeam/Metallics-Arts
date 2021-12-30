package net.rudahee.metallics_arts.setup.metalMind.band;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;

public class BandAtiumMalatium extends BandMindAbstract {
    CompoundNBT nbt;
    public BandAtiumMalatium (Item.Properties properties){
        super(properties);
        nbt.putInt("atium",0);
        nbt.putInt("malatium",0);
        nbt.putInt("capacityAtium",100);
        nbt.putInt("capacityMalatium",100);
        setNbt(nbt);
    }
}
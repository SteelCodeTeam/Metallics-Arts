package net.rudahee.metallics_arts.setup.metalMind.band;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;

public class BandAluminumDuralumin extends BandMindAbstract {
    CompoundNBT nbt;
    public BandAluminumDuralumin (Item.Properties properties){
        super(properties);
        nbt.putInt("aluminum",0);
        nbt.putInt("duralumin",0);
        nbt.putInt("capacityAluminum",100);
        nbt.putInt("capacityDuralumin",100);
        setNbt(nbt);
    }
}
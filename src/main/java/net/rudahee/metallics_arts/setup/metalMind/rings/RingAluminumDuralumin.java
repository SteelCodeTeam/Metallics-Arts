package net.rudahee.metallics_arts.setup.metalMind.rings;

import net.minecraft.nbt.CompoundNBT;

public class RingAluminumDuralumin extends RingsMindAbstract{
    CompoundNBT nbt;
    public RingAluminumDuralumin (Properties properties){
        super(properties);
        nbt.putInt("aluminum",0);
        nbt.putInt("duralumin",0);
        nbt.putInt("capacityAluminum",100);
        nbt.putInt("capacityDuralumin",100);
        setNbt(nbt);
    }
}
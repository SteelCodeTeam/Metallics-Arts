package net.rudahee.metallics_arts.setup.metalMind.rings;

import net.minecraft.nbt.CompoundNBT;

public class RingAtiumMalatium extends RingsMindAbstract{
    CompoundNBT nbt;
    public RingAtiumMalatium (Properties properties){
        super(properties);
        nbt.putInt("atium",0);
        nbt.putInt("malatium",0);
        nbt.putInt("capacityAtium",100);
        nbt.putInt("capacityMalatium",100);
        setNbt(nbt);
    }
}
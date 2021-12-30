package net.rudahee.metallics_arts.setup.metalMind.rings;

import net.minecraft.nbt.CompoundNBT;

public class RingZincBrass extends RingsMindAbstract{
    CompoundNBT nbt;
    public RingZincBrass (Properties properties){
        super(properties);
        nbt.putInt("zinc",0);
        nbt.putInt("brass",0);
        nbt.putInt("capacityZinc",100);
        nbt.putInt("capacityBrass",100);
        setNbt(nbt);
    }
}
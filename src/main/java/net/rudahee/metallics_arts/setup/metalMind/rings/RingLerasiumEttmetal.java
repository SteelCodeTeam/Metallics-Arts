package net.rudahee.metallics_arts.setup.metalMind.rings;

import net.minecraft.nbt.CompoundNBT;

public class RingLerasiumEttmetal extends RingsMindAbstract{
    CompoundNBT nbt;
    public RingLerasiumEttmetal (Properties properties){
        super(properties);
        nbt.putInt("lerasium",0);
        nbt.putInt("ettmetal",0);
        nbt.putInt("capacityLerasium",100);
        nbt.putInt("capacityEttmetal",100);
        setNbt(nbt);
    }
}
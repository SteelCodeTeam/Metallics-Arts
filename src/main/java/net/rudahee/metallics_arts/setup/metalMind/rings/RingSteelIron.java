package net.rudahee.metallics_arts.setup.metalMind.rings;

import net.minecraft.nbt.CompoundNBT;

public class RingSteelIron extends RingsMindAbstract{
    CompoundNBT nbt;
    public RingSteelIron (Properties properties){
        super(properties);
        nbt.putInt("steel",0);
        nbt.putInt("iron",0);
        nbt.putInt("capacitySteel",100);
        nbt.putInt("capacityIron",100);
        setNbt(nbt);
    }
}
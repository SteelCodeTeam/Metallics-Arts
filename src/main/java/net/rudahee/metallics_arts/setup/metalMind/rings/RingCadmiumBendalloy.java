package net.rudahee.metallics_arts.setup.metalMind.rings;

import net.minecraft.nbt.CompoundNBT;

public class RingCadmiumBendalloy extends RingsMindAbstract{
    CompoundNBT nbt;
    public RingCadmiumBendalloy (Properties properties){
        super(properties);
        nbt.putInt("candmium",0);
        nbt.putInt("bendalloy",0);
        nbt.putInt("capacityCadmium",100);
        nbt.putInt("capacityBendalloy",100);
        setNbt(nbt);
    }
}
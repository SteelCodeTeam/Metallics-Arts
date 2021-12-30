package net.rudahee.metallics_arts.setup.metalMind.rings;

import net.minecraft.nbt.CompoundNBT;

public class RingCopperBronze extends RingsMindAbstract{
    CompoundNBT nbt;
    public RingCopperBronze(Properties properties){
        super(properties);
        nbt.putInt("copper",0);
        nbt.putInt("bronze",0);
        nbt.putInt("capacityCopper",100);
        nbt.putInt("capacityBronze",100);
        setNbt(nbt);
    }
}
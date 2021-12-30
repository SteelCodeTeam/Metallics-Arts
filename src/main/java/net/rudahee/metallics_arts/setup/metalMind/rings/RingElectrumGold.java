package net.rudahee.metallics_arts.setup.metalMind.rings;

import net.minecraft.nbt.CompoundNBT;

public class RingElectrumGold extends RingsMindAbstract{
    CompoundNBT nbt;
    public RingElectrumGold (Properties properties){
        super(properties);
        nbt.putInt("gold",0);
        nbt.putInt("electrum",0);
        nbt.putInt("capacityGold",100);
        nbt.putInt("capacityElectrum",100);
        setNbt(nbt);
    }
}
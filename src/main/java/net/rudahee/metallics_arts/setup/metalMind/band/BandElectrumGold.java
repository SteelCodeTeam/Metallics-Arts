package net.rudahee.metallics_arts.setup.metalMind.band;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;

public class BandElectrumGold extends BandMindAbstract {
    CompoundNBT nbt;
    public BandElectrumGold (Item.Properties properties){
        super(properties);
        nbt.putInt("gold",0);
        nbt.putInt("electrum",0);
        nbt.putInt("capacityGold",100);
        nbt.putInt("capacityElectrum",100);
        setNbt(nbt);
    }
}
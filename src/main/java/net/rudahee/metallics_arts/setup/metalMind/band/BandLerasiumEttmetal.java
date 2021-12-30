package net.rudahee.metallics_arts.setup.metalMind.band;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;

public class BandLerasiumEttmetal extends BandMindAbstract {
    CompoundNBT nbt;
    public BandLerasiumEttmetal (Item.Properties properties){
        super(properties);
        nbt.putInt("lerasium",0);
        nbt.putInt("ettmetal",0);
        nbt.putInt("capacityLerasium",100);
        nbt.putInt("capacityEttmetal",100);
        setNbt(nbt);
    }
}
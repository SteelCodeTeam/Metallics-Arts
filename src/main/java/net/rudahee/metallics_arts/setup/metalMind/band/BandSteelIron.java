package net.rudahee.metallics_arts.setup.metalMind.band;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;

public class BandSteelIron extends BandMindAbstract {
    CompoundNBT nbt;
    public BandSteelIron (Item.Properties properties){
        super(properties);
        nbt.putInt("steel",0);
        nbt.putInt("iron",0);
        nbt.putInt("capacitySteel",100);
        nbt.putInt("capacityIron",100);
        setNbt(nbt);
    }
}
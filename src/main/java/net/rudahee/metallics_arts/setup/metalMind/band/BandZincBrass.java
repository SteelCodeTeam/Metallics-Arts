package net.rudahee.metallics_arts.setup.metalMind.band;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;

public class BandZincBrass extends BandMindAbstract {
    CompoundNBT nbt;
    public BandZincBrass (Item.Properties properties){
        super(properties);
        nbt.putInt("zinc",0);
        nbt.putInt("brass",0);
        nbt.putInt("capacityZinc",100);
        nbt.putInt("capacityBrass",100);
        setNbt(nbt);
    }
}
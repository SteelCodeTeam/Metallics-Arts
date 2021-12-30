package net.rudahee.metallics_arts.setup.metalMind.band;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;

public class BandCopperBronze extends BandMindAbstract {
    CompoundNBT nbt;
    public BandCopperBronze(Item.Properties properties){
        super(properties);
        nbt.putInt("copper",0);
        nbt.putInt("bronze",0);
        nbt.putInt("capacityCopper",100);
        nbt.putInt("capacityBronze",100);
        setNbt(nbt);
    }
}
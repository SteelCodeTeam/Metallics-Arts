package net.rudahee.metallics_arts.setup.metalMind.band;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;

public class BandCadmiumBendalloy extends BandMindAbstract {
    CompoundNBT nbt;
    public BandCadmiumBendalloy (Item.Properties properties){
        super(properties);
        nbt.putInt("candmium",0);
        nbt.putInt("bendalloy",0);
        nbt.putInt("capacityCadmium",100);
        nbt.putInt("capacityBendalloy",100);
        setNbt(nbt);
    }
}
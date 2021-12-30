package net.rudahee.metallics_arts.setup.metalMind.band;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;

public class BandChromiumNicrosil extends BandMindAbstract {
    CompoundNBT nbt;
    public BandChromiumNicrosil(Item.Properties properties){
        super(properties);
        nbt.putInt("chromium",0);
        nbt.putInt("nicrosil",0);
        nbt.putInt("capacityChromium",100);
        nbt.putInt("capacityNicrosil",100);
        setNbt(nbt);
    }
}
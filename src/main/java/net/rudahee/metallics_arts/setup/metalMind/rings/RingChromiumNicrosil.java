package net.rudahee.metallics_arts.setup.metalMind.rings;

import net.minecraft.nbt.CompoundNBT;

public class RingChromiumNicrosil extends RingsMindAbstract{
    CompoundNBT nbt;
    public RingChromiumNicrosil(Properties properties){
        super(properties);
        nbt.putInt("chromium",0);
        nbt.putInt("nicrosil",0);
        nbt.putInt("capacityChromium",100);
        nbt.putInt("capacityNicrosil",100);
        setNbt(nbt);
    }
}
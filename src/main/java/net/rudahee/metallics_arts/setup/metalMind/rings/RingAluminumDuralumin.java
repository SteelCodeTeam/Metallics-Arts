package net.rudahee.metallics_arts.setup.metalMind.rings;

import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;

public class RingAluminumDuralumin extends RingsMindAbstract{
    CompoundNBT nbt = new CompoundNBT();
    public RingAluminumDuralumin (Properties properties){
        super(properties);
        nbt.putInt(MetallicsArts.MOD_ID+".RingAlluminumDuralumin.aluminum",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingAlluminumDuralumin.duralumin",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingAlluminumDuralumin.capacityAluminum",100);
        nbt.putInt(MetallicsArts.MOD_ID+".RingAlluminumDuralumin.capacityDuralumin",100);
        setNbt(nbt);
    }
}
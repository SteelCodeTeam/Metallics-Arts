package net.rudahee.metallics_arts.modules.items.metalminds.rings;

import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;

public class RingZincBrass extends RingsMindAbstract{
    CompoundNBT nbt = new CompoundNBT();
    public RingZincBrass (Properties properties){
        super(properties);
        nbt.putInt(MetallicsArts.MOD_ID+".RingZincBrass.zinc",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingZincBrass.brass",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingZincBrass.capacityZinc",100);
        nbt.putInt(MetallicsArts.MOD_ID+".RingZincBrass.capacityBrass",100);
        setNbt(nbt);
    }
}
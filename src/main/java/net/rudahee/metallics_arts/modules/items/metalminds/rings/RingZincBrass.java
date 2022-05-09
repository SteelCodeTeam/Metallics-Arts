package net.rudahee.metallics_arts.modules.items.metalminds.rings;

import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class RingZincBrass extends RingsMindAbstract{
    CompoundNBT nbt = new CompoundNBT();
    public RingZincBrass (Properties properties){
        super(properties, MetalsNBTData.ZINC,MetalsNBTData.BRASS);
        nbt.putInt(MetallicsArts.MOD_ID+".RingZincBrass.zinc",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingZincBrass.brass",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingZincBrass.capacityZinc",100);
        nbt.putInt(MetallicsArts.MOD_ID+".RingZincBrass.capacityBrass",100);
        setNbt(nbt);
    }
}
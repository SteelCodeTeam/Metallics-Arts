package net.rudahee.metallics_arts.modules.items.metalminds.rings;

import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;

public class RingAtiumMalatium extends RingsMindAbstract{
    CompoundNBT nbt = new CompoundNBT();
    public RingAtiumMalatium (Properties properties){
        super(properties);
        nbt.putInt(MetallicsArts.MOD_ID+".RingAtiumMalatium.atium",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingAtiumMalatium.malatium",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingAtiumMalatium.capacityAtium",100);
        nbt.putInt(MetallicsArts.MOD_ID+".RingAtiumMalatium.capacityMalatium",100);
        setNbt(nbt);
    }
}
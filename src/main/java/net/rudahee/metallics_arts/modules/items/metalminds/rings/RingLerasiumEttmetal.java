package net.rudahee.metallics_arts.modules.items.metalminds.rings;

import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class RingLerasiumEttmetal extends RingsMindAbstract{
    CompoundNBT nbt = new CompoundNBT();
    public RingLerasiumEttmetal (Properties properties){
        super(properties, MetalsNBTData.LERASIUM,MetalsNBTData.ETTMETAL);
        nbt.putInt(MetallicsArts.MOD_ID+".RingLerasiumEttmetal.lerasium",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingLerasiumEttmetal.ettmetal",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingLerasiumEttmetal.capacityLerasium",100);
        nbt.putInt(MetallicsArts.MOD_ID+".RingLerasiumEttmetal.capacityEttmetal",100);
        setNbt(nbt);
    }
}
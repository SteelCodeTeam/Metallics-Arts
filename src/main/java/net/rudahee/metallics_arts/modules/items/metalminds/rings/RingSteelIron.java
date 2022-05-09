package net.rudahee.metallics_arts.modules.items.metalminds.rings;

import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class RingSteelIron extends RingsMindAbstract{
    CompoundNBT nbt = new CompoundNBT();
    public RingSteelIron (Properties properties){
        super(properties, MetalsNBTData.IRON,MetalsNBTData.STEEL);
        nbt.putInt(MetallicsArts.MOD_ID+".RingSteelIron.steel",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingSteelIron.iron",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingSteelIron.capacitySteel",100);
        nbt.putInt(MetallicsArts.MOD_ID+".RingSteelIron.capacityIron",100);
        setNbt(nbt);
    }
}
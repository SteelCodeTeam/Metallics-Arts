package net.rudahee.metallics_arts.setup.metalMind.rings;

import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;

public class RingSteelIron extends RingsMindAbstract{
    CompoundNBT nbt = new CompoundNBT();
    public RingSteelIron (Properties properties){
        super(properties);
        nbt.putInt(MetallicsArts.MOD_ID+".RingSteelIron.steel",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingSteelIron.iron",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingSteelIron.capacitySteel",100);
        nbt.putInt(MetallicsArts.MOD_ID+".RingSteelIron.capacityIron",100);
        setNbt(nbt);
    }
}
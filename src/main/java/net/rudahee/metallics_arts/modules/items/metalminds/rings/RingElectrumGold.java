package net.rudahee.metallics_arts.modules.items.metalminds.rings;

import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;

public class RingElectrumGold extends RingsMindAbstract{
    CompoundNBT nbt = new CompoundNBT();
    public RingElectrumGold (Properties properties){
        super(properties);
        nbt.putInt(MetallicsArts.MOD_ID+".RingElectrumGold.gold",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingElectrumGold.electrum",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingElectrumGold.capacityGold",100);
        nbt.putInt(MetallicsArts.MOD_ID+".RingElectrumGold.capacityElectrum",100);
        setNbt(nbt);
    }
}
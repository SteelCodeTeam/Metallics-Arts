package net.rudahee.metallics_arts.setup.metalMind.band;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;

public class BandElectrumGold extends BandMindAbstract {
    CompoundNBT nbt = new CompoundNBT();
    public BandElectrumGold (Item.Properties properties){
        super(properties);
        nbt.putInt(MetallicsArts.MOD_ID+".BandPwterTin.gold",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandPwterTin.electrum",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandPwterTin.capacityGold",100);
        nbt.putInt(MetallicsArts.MOD_ID+".BandPwterTin.capacityElectrum",100);
        setNbt(nbt);
    }
}
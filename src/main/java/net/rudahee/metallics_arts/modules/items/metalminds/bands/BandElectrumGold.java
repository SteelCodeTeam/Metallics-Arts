package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class BandElectrumGold extends BandMindAbstract {
    CompoundNBT nbt = new CompoundNBT();
    public BandElectrumGold (Item.Properties properties){
        super(properties, MetalsNBTData.GOLD,MetalsNBTData.ELECTRUM);
        nbt.putInt(MetallicsArts.MOD_ID+".BandPwterTin.gold",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandPwterTin.electrum",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandPwterTin.capacityGold",100);
        nbt.putInt(MetallicsArts.MOD_ID+".BandPwterTin.capacityElectrum",100);
    }

    @Override
    public void storing(CompoundNBT nbt, String metal, int qty) {
        nbt.putInt(metal, nbt.getInt(metal) + qty);
    }

    @Override
    public void decanting(CompoundNBT nbt, String metal, int qty) {
        nbt.putInt(metal,nbt.getInt(metal)-qty);
    }
}
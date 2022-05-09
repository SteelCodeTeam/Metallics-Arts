package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class BandLerasiumEttmetal extends BandMindAbstract {
    CompoundNBT nbt = new CompoundNBT();
    public BandLerasiumEttmetal (Item.Properties properties){
        super(properties, MetalsNBTData.LERASIUM,MetalsNBTData.ETTMETAL);
        nbt.putInt(MetallicsArts.MOD_ID+".BandPwterTin.lerasium",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandPwterTin.ettmetal",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandPwterTin.capacityLerasium",100);
        nbt.putInt(MetallicsArts.MOD_ID+".BandPwterTin.capacityEttmetal",100);
        setNbt(nbt);
    }
}
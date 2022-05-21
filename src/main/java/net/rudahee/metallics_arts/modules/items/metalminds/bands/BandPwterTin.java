package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class BandPwterTin extends BandMindAbstract {
    CompoundNBT nbt = new CompoundNBT();
    public BandPwterTin (Item.Properties properties){
        super(properties, MetalsNBTData.TIN,MetalsNBTData.PEWTER);
        nbt.putInt(MetallicsArts.MOD_ID+".BandPwterTin.tin",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandPwterTin.pewter",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandPwterTin.capacityTin",100);
        nbt.putInt(MetallicsArts.MOD_ID+".BandPwterTin.capacityPewter",100);

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

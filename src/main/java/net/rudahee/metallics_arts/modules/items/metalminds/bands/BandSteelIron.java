package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class BandSteelIron extends BandMindAbstract {
    CompoundNBT nbt = new CompoundNBT();
    public BandSteelIron (Item.Properties properties){
        super(properties, MetalsNBTData.IRON,MetalsNBTData.STEEL);
        nbt.putInt(MetallicsArts.MOD_ID+".BandSteelIron.steel",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandSteelIron.iron",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandSteelIron.capacitySteel",100);
        nbt.putInt(MetallicsArts.MOD_ID+".BandSteelIron.capacityIron",100);
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
package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.rudahee.metallics_arts.MetallicsArts;

public class BandAluminumDuralumin extends BandMindAbstract {
    CompoundNBT nbt = new CompoundNBT();
    public BandAluminumDuralumin (Item.Properties properties){
        super(properties);
        nbt.putInt(MetallicsArts.MOD_ID+".BandAluminumDuralumin.aluminum",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandAluminumDuralumin.duralumin",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandAluminumDuralumin.capacityAluminum", 100);
        nbt.putInt(MetallicsArts.MOD_ID+".BandAluminumDuralumin.capacityDuralumin",100);
        setNbt(nbt);
    }

    @Override
    public ActionResultType useOn(ItemUseContext p_195939_1_) {
        return super.useOn(p_195939_1_);
    }
}
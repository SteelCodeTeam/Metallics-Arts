package net.rudahee.metallics_arts.modules.items.metalminds.rings;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class RingCadmiumBendalloy extends RingsMindAbstract{
    CompoundNBT nbt = new CompoundNBT();
    public RingCadmiumBendalloy (Properties properties){
        super(properties, MetalsNBTData.CADMIUM,MetalsNBTData.BENDALLOY);
        nbt.putInt(MetallicsArts.MOD_ID+".RingCadmiumBendalloy.candmium",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingCadmiumBendalloy.bendalloy",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingCadmiumBendalloy.capacityCadmium",100);
        nbt.putInt(MetallicsArts.MOD_ID+".RingCadmiumBendalloy.capacityBendalloy",100);
        setNbt(nbt);
    }

}
package net.rudahee.metallics_arts.modules.items.metalminds.rings;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.data_player.IDefaultInvestedPlayerData;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class RingAluminumDuralumin extends RingsMindAbstract implements ICurioItem{
    CompoundNBT nbt = new CompoundNBT();
    public RingAluminumDuralumin (Properties properties){
        super(properties, MetalsNBTData.ALUMINUM,MetalsNBTData.DURALUMIN);
        nbt.putInt(MetallicsArts.MOD_ID+".RingAlluminumDuralumin.aluminum",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingAlluminumDuralumin.duralumin",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingAlluminumDuralumin.capacityAluminum",100);
        nbt.putInt(MetallicsArts.MOD_ID+".RingAlluminumDuralumin.capacityDuralumin",100);
        setNbt(nbt);
    }


}
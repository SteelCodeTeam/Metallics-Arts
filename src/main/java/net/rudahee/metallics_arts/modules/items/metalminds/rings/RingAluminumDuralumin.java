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
        super(properties);
        nbt.putInt(MetallicsArts.MOD_ID+".RingAlluminumDuralumin.aluminum",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingAlluminumDuralumin.duralumin",0);
        nbt.putInt(MetallicsArts.MOD_ID+".RingAlluminumDuralumin.capacityAluminum",100);
        nbt.putInt(MetallicsArts.MOD_ID+".RingAlluminumDuralumin.capacityDuralumin",100);
        setNbt(nbt);
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        super.curioTick(identifier, index, livingEntity, stack);
        //if hasFeruchemicPower de algun metal de la banda/arillo, lo activo en el power selector
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        ICurioItem.super.onEquip(slotContext, prevStack, stack);

        PlayerEntity player = (PlayerEntity) slotContext.getWearer();

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{
            data.setMetalMindEquiped(MetalsNBTData.ALUMINUM.getGroup(),true);
            data.setMetalMindEquiped(MetalsNBTData.DURALUMIN.getGroup(),true);
            ModNetwork.sync(data,player);
        });

    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        ICurioItem.super.onUnequip(slotContext, newStack, stack);

        PlayerEntity player = (PlayerEntity) slotContext.getWearer();

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{
            data.setMetalMindEquiped(MetalsNBTData.ALUMINUM.getGroup(),false);
            data.setMetalMindEquiped(MetalsNBTData.DURALUMIN.getGroup(),false);

            ModNetwork.sync(data,player);
        });
    }

    private static IDefaultInvestedPlayerData cap = null;

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlotType armorType, Entity entity) {
        PlayerEntity player = (PlayerEntity) entity;

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{
            cap = data;
        });
        if (cap != null){
            ModNetwork.sync(cap,player);
            return (!(cap.getMetalMindEquiped(MetalsNBTData.ALUMINUM.getGroup()) && cap.getMetalMindEquiped(MetalsNBTData.DURALUMIN.getGroup())));
        }
        return false;
    }


}
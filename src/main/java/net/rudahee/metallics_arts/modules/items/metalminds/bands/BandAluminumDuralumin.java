package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.data_player.DefaultInvestedPlayerData;
import net.rudahee.metallics_arts.modules.data_player.IDefaultInvestedPlayerData;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.swing.table.DefaultTableCellRenderer;

public class BandAluminumDuralumin extends BandMindAbstract implements ICurioItem {
    CompoundNBT nbt = new CompoundNBT();
    public BandAluminumDuralumin (Item.Properties properties){
        super(properties,MetalsNBTData.ALUMINUM,MetalsNBTData.DURALUMIN);
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


    /*@Override
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
    public boolean canEquip(String identifier, LivingEntity livingEntity, ItemStack stack) {
        PlayerEntity player = (PlayerEntity) livingEntity;

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{
            cap = data;
        });
        if (cap != null){
            //ModNetwork.sync(cap,player);
            return (!(cap.getMetalMindEquiped(MetalsNBTData.ALUMINUM.getGroup()) && cap.getMetalMindEquiped(MetalsNBTData.DURALUMIN.getGroup())));
        }
        return false;
    }*/
}
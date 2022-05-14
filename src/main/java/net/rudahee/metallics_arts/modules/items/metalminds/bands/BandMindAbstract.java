package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.data_player.IDefaultInvestedPlayerData;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BandMindAbstract extends Item implements ICurioItem {

    private CompoundNBT nbt;
    private int maxCapacity;
    private MetalsNBTData[] metals = new MetalsNBTData[2];

    public String unkeyedString = "ESTA LIBRE PAPU";

    public BandMindAbstract(Properties properties,MetalsNBTData metal1, MetalsNBTData metal2) {
        super(properties);
        metals[0]=metal1;
        metals[1]=metal2;
    }

    public void storing(String metal, int qty){
        this.nbt.putInt(metal,this.nbt.getInt(metal)+qty);
    }
    public void decanting(String metal, int qty){
        this.nbt.putInt(metal,this.nbt.getInt(metal)-qty);
    }

    public CompoundNBT getNbt() {
        return nbt;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> toolTips, ITooltipFlag flagIn) {

        //if (Screen.hasControlDown()){
            //toolTips.add(new StringTextComponent(metals[0].getNameLower()+": "+stack.getTag().getInt(MetallicsArts.MOD_ID + ".feruchemic_"+metals[0].getNameLower()+"_reserve")));
            //toolTips.add(new StringTextComponent(metals[1].getNameLower()+": "+stack.getTag().getInt(MetallicsArts.MOD_ID + ".feruchemic_"+metals[1].getNameLower()+"_reserve")));
        //}
        super.appendHoverText(stack, world, toolTips, flagIn);
    }

    public void setNbt(CompoundNBT nbt) {
        this.nbt = nbt;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        ICurioItem.super.onEquip(slotContext, prevStack, stack);

        PlayerEntity player = (PlayerEntity) slotContext.getWearer();

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{
            data.setMetalMindEquiped(this.metals[0].getGroup(),true);
            data.setMetalMindEquiped(this.metals[1].getGroup(),true);
            ModNetwork.sync(data,player);
        });

    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        ICurioItem.super.onUnequip(slotContext, newStack, stack);

        PlayerEntity player = (PlayerEntity) slotContext.getWearer();

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{
            data.setMetalMindEquiped(this.metals[0].getGroup(),false);
            data.setMetalMindEquiped(this.metals[1].getGroup(),false);

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
            return (!(cap.getMetalMindEquiped(this.metals[0].getGroup()) && cap.getMetalMindEquiped(this.metals[1].getGroup())));
        }
        return false;
    }
}

package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.data_player.IDefaultInvestedPlayerData;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModItems;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public abstract class BandMindAbstract extends Item implements ICurioItem {

    CompoundNBT compoundNBT = new CompoundNBT();
    private MetalsNBTData[] metals = new MetalsNBTData[2];

    private int[] metalsMaxReserve = new int[2];

    public String unkeyedString = "Nobody";

    public BandMindAbstract(Properties properties,MetalsNBTData metal1, MetalsNBTData metal2,int maxReserve1,int maxReserve2) {
        super(properties);
        metals[0]=metal1;
        metals[1]=metal2;

        metalsMaxReserve[0]=maxReserve1;
        metalsMaxReserve[1]=maxReserve2;

        this.compoundNBT.putInt(metal1.getGemNameLower()+"_feruchemic_reserve",0);
        this.compoundNBT.putInt(metal2.getGemNameLower()+"_feruchemic_reserve",0);
        this.compoundNBT.putInt(metal1.getGemNameLower()+"_feruchemic_max_capacity",maxReserve1);
        this.compoundNBT.putInt(metal2.getGemNameLower()+"_feruchemic_max_capacity",maxReserve2);
        this.compoundNBT.putString("key",unkeyedString);
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
        if (stack.getTag().getString("key").equals("ESTA LIBRE PAPU")){
            stack.getTag().putString("key", player.getUUID().toString());
        }

    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {

        ICurioItem.super.onUnequip(slotContext, newStack, stack);
        PlayerEntity player = (PlayerEntity) slotContext.getWearer();

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{
            data.setMetalMindEquiped(this.metals[0].getGroup(),false);
            data.setMetalMindEquiped(this.metals[1].getGroup(),false);
            //DEBERIA APAGAR EL DECANTE O ALMACENAJE
            ModNetwork.sync(data,player);
        });

        //if (((stack.getTag().getInt(metals[0].getNameLower()+"_feruchemic_reserve")) == 0) && ((stack.getTag().getInt(metals[1].getNameLower()+"_feruchemic_reserve")) == 0)){
            stack.getTag().putString("key", unkeyedString);
        //}


    }


    private static IDefaultInvestedPlayerData cap = null;

    @Override
    public boolean canEquip(String identifier, LivingEntity livingEntity, ItemStack stack) {
        PlayerEntity player = (PlayerEntity) livingEntity;
        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{
            cap = data;
        });
        if (cap != null){
            return (!(cap.getMetalMindEquiped(this.metals[0].getGroup()) && cap.getMetalMindEquiped(this.metals[1].getGroup())));
        }
        return false;
    }

    @Override
    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
        ItemStack resultItem;
        switch (this.metals[0].getGroup()){
            case 0:
                resultItem = new ItemStack(ModItems.BAND_STEEL_IRON.get(),1);
                break;
            case 1:
                resultItem = new ItemStack(ModItems.BAND_PEWTER_TIN.get(),1);
                break;
            case 2:
                resultItem = new ItemStack(ModItems.BAND_COPPER_BRONZE.get(),1);
                break;
            case 3:
                resultItem = new ItemStack(ModItems.BAND_ZINC_BRASS.get(),1);
                break;
            case 4:
                resultItem = new ItemStack(ModItems.BAND_CHROMIUM_NICROSIL.get(),1);
                break;
            case 5:
                resultItem = new ItemStack(ModItems.BAND_ALUMINUM_DURALUMIN.get(),1);
                break;
            case 6:
                resultItem = new ItemStack(ModItems.BAND_CADMIUM_BENDALLOY.get(),1);
                break;
            case 7:
                resultItem = new ItemStack(ModItems.BAND_ELECTRUM_GOLD.get(),1);
                break;
            case 8:
                resultItem = new ItemStack(ModItems.BAND_ATIUM_MALATIUM.get(),1);
                break;
            case 9:
                resultItem = new ItemStack(ModItems.BAND_LERASIUM_ETTMETAL.get(),1);
                break;
            default:
                resultItem = null;
        }

        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt(metals[0].getNameLower()+"_feruchemic_reserve",0);
        nbt.putInt(metals[1].getGemNameLower()+"_feruchemic_reserve",0);
        nbt.putInt(metals[0].getNameLower()+"_feruchemic_max_capacity",metalsMaxReserve[0]);
        nbt.putInt(metals[1].getGemNameLower()+"_feruchemic_max_capacity",metalsMaxReserve[1]);
        nbt.putString("key",unkeyedString);
        resultItem.setTag(nbt);
        if (this.allowdedIn(group)) {
            items.add(resultItem);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> toolTips, ITooltipFlag flagIn) {
        super.appendHoverText(stack, world, toolTips, flagIn);
        //if (Screen.hasControlDown()) {
            if (stack.hasTag()) {
                toolTips.add(new StringTextComponent(metals[0].getNameLower().substring(0,1).toUpperCase()+metals[0].getNameLower().substring(1)+": "+ stack.getTag().getInt(metals[0].getNameLower()+"_feruchemic_reserve") / 40 + "s"));
                toolTips.add(new StringTextComponent(metals[1].getNameLower().substring(0,1).toUpperCase()+metals[1].getNameLower().substring(1)+": "+ stack.getTag().getInt(metals[1].getNameLower()+"_feruchemic_reserve") / 40 + "s"));
                toolTips.add(new StringTextComponent("Owner: "+ (stack.getTag().getString("key").equals(unkeyedString) ? "Nobody" : world.getPlayerByUUID(UUID.fromString(stack.getTag().getString("key"))).getDisplayName().toString())));
            }
        //}
    }



    private static boolean needUpdate = false;

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        CompoundNBT nbtLocal = stack.getTag();
        if (livingEntity.level instanceof ServerWorld) {
            needUpdate = false;
            if (livingEntity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) livingEntity;
                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {

                    if (data.isDecanting(metals[0])) {
                        if (stack.getTag().getInt(metals[0].getNameLower()+"_feruchemic_reserve") > 0) {
                            nbtLocal.putInt(metals[0].getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(metals[0].getNameLower()+"_feruchemic_reserve")-1));
                            stack.setTag(nbtLocal);
                        } else {
                            data.setDecanting(metals[0], false);
                        }
                        needUpdate = true;
                    } else if (data.isStoring(metals[0])) {
                        if (stack.getTag().getInt(metals[0].getNameLower()+"_feruchemic_reserve") < this.metalsMaxReserve[0]) {
                            nbtLocal.putInt(metals[0].getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(metals[0].getNameLower()+"_feruchemic_reserve")+1));
                            stack.setTag(nbtLocal);
                        } else {
                            data.setStoring(metals[0], false);
                        }
                        needUpdate = true;
                    }
                    if (data.isDecanting(metals[1])) {
                        if (stack.getTag().getInt(metals[1].getNameLower()+"_feruchemic_reserve") > 0) {
                            nbtLocal.putInt(metals[1].getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(metals[1].getNameLower()+"_feruchemic_reserve")-1));
                            stack.setTag(nbtLocal);
                        } else {
                            data.setDecanting(metals[1], false);
                        }
                        needUpdate = true;
                    } else if (data.isStoring(metals[1])) {
                        if (stack.getTag().getInt(metals[1].getNameLower()+"_feruchemic_reserve") < this.metalsMaxReserve[1]) {
                            nbtLocal.putInt(metals[1].getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(metals[1].getNameLower()+"_feruchemic_reserve")+1));
                            stack.setTag(nbtLocal);
                        } else {
                            data.setStoring(metals[1], false);
                        }
                        needUpdate = true;
                    }
                    if (needUpdate) {
                        ModNetwork.sync(data, player);
                    }
                });
            }
        }
        ICurioItem.super.curioTick(identifier, index, livingEntity, stack);
    }

}

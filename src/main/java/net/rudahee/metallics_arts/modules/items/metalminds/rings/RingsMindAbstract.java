package net.rudahee.metallics_arts.modules.items.metalminds.rings;


import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.modules.data_player.IDefaultInvestedPlayerData;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;


public abstract class RingsMindAbstract extends Item implements ICurioItem {


    private final MetalsNBTData[] metals = new MetalsNBTData[2];
    private final int[] metalsMaxReserve = new int[2];
    public String unkeyedString = "Nobody";

    public RingsMindAbstract(Properties properties,MetalsNBTData metal1, MetalsNBTData metal2,int maxReserve1,int maxReserve2) {
            super(properties);
            metals[0]=metal1;
            metals[1]=metal2;
            metalsMaxReserve[0]=maxReserve1;
            metalsMaxReserve[1]=maxReserve2;
        }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        Player player = (Player) slotContext.getWearer();

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{
            data.setMetalMindEquiped(this.metals[0].getGroup(),true);
            data.setMetalMindEquiped(this.metals[1].getGroup(),true);
            ModNetwork.sync(data,player);
        });
        ICurioItem.super.onEquip(slotContext, prevStack, stack);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        Player player = (Player) slotContext.getWearer();
        if (stack.getItem() != newStack.getItem()) {
            player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{
                data.setMetalMindEquiped(this.metals[0].getGroup(),false);
                data.setMetalMindEquiped(this.metals[1].getGroup(),false);
                data.setStoring(this.metals[0],false);
                data.setStoring(this.metals[1],false);
                data.setDecanting(this.metals[0],false);
                data.setDecanting(this.metals[1],false);
                ModNetwork.sync(data,player);
            });
        }
        ICurioItem.super.onUnequip(slotContext, newStack, stack);
    }



    private static IDefaultInvestedPlayerData cap = null;

    @Override
    public boolean canEquip(String identifier, LivingEntity livingEntity, ItemStack stack) {
        if(!stack.hasTag()) {
            stack.setTag(addRingTags());
        }
        Player player = (Player) livingEntity;
        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{
            cap = data;
        });
        boolean canEquip = false;
        if (cap != null) {
            canEquip = (!(cap.getMetalMindEquiped(this.metals[0].getGroup()) && cap.getMetalMindEquiped(this.metals[1].getGroup())));
        }

        if (canEquip){
            if (!stack.getTag().getString("key").equals(unkeyedString)
                    && !player.getStringUUID().equals(stack.getTag().getString("key"))){
                canEquip = false;
            }
        }

        return canEquip;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> toolTips, TooltipFlag flagIn) {
        if(!stack.hasTag()) {
            stack.setTag(addRingTags());
        }
        if (this instanceof RingLerasiumEttmetal || this instanceof RingAtiumMalatium || this instanceof RingZincBrass || this instanceof RingCopperBronze || this instanceof RingChromiumNicrosil){
            return;
        }


        if (stack.hasTag()) {
            if (!Screen.hasControlDown()){
                toolTips.add(Component.translatable(metals[0].getNameLower().substring(0,1).toUpperCase()+metals[0].getNameLower().substring(1)+": "+ stack.getTag().getInt(metals[0].getNameLower()+"_feruchemic_reserve") / 40 + "s"));
                toolTips.add(Component.translatable(metals[1].getNameLower().substring(0,1).toUpperCase()+metals[1].getNameLower().substring(1)+": "+ stack.getTag().getInt(metals[1].getNameLower()+"_feruchemic_reserve") / 40 + "s"));
            } else {
                toolTips.add(Component.translatable(metals[0].getNameLower().substring(0,1).toUpperCase()+metals[0].getNameLower().substring(1)+": "+ ((stack.getTag().getInt(metals[0].getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(metals[0].getNameLower()+"_feruchemic_max_capacity"))+"%"));
                toolTips.add(Component.translatable(metals[1].getNameLower().substring(0,1).toUpperCase()+metals[1].getNameLower().substring(1)+": "+ ((stack.getTag().getInt(metals[1].getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(metals[1].getNameLower()+"_feruchemic_max_capacity"))+"%"));

            }
            toolTips.add(Component.translatable("Owner: "+ (stack.getTag().getString("key"))));
        }
        super.appendHoverText(stack, world, toolTips, flagIn);
    }

    private CompoundTag addRingTags() {
        CompoundTag nbt = new CompoundTag();

        nbt.putInt(this.metals[0].getNameLower()+"_feruchemic_reserve",0);
        nbt.putInt(this.metals[1].getNameLower()+"_feruchemic_reserve",0);
        nbt.putInt(this.metals[0].getNameLower()+"_feruchemic_max_capacity",metalsMaxReserve[0]);
        nbt.putInt(this.metals[1].getNameLower()+"_feruchemic_max_capacity",metalsMaxReserve[1]);
        nbt.putString("key",this.unkeyedString);
        return nbt;
    }

    private static boolean nicConsumeMet0 = false;
    private static boolean nicConsumeMet1 = false;

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        if(!stack.hasTag()) {
            stack.setTag(addRingTags());
        }

        if (this instanceof RingZincBrass || this instanceof RingCopperBronze
                || this instanceof RingLerasiumEttmetal ||this instanceof RingAtiumMalatium
                ||this instanceof RingChromiumNicrosil || this instanceof RingAluminumDuralumin) {
            return;
        }

        CompoundTag nbtLocal = stack.getTag();

        if (livingEntity.level instanceof ServerLevel) {
            if (livingEntity instanceof Player) {
                Player player = (Player) livingEntity;
                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {

                    if (data.isDecanting(MetalsNBTData.ALUMINUM)||data.isStoring(MetalsNBTData.ALUMINUM)){
                        stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                    }

                    if (data.isDecanting(this.metals[0])) {
                        if (stack.getTag().getInt(this.metals[0].getNameLower()+"_feruchemic_reserve")>0) {
                            if (data.isDecanting(MetalsNBTData.NICROSIL)){
                                if (!nicConsumeMet0){
                                    nbtLocal.putInt(this.metals[0].getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(this.metals[0].getNameLower()+"_feruchemic_reserve")-1));
                                    stack.setTag(nbtLocal);
                                }
                                nicConsumeMet0 = !nicConsumeMet0;
                            } else {
                                //las dos lineas de abajo van sin el nicrosil
                                nbtLocal.putInt(this.metals[0].getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(this.metals[0].getNameLower()+"_feruchemic_reserve")-1));
                                stack.setTag(nbtLocal);
                            }
                        } else {
                            stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                            data.setDecanting(this.metals[0],false);
                        }
                    } else if (data.isStoring(this.metals[0])) {
                        if (stack.getTag().getInt(this.metals[0].getNameLower()+"_feruchemic_reserve") < stack.getTag().getInt(this.metals[0].getNameLower()+"_feruchemic_max_capacity")) {

                            if (data.isStoring(MetalsNBTData.NICROSIL)) {
                                if (!nicConsumeMet0){
                                    stack.getTag().putString("key",changeOwner(player,stack.getTag(),true));
                                    nbtLocal.putInt(this.metals[0].getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(this.metals[0].getNameLower()+"_feruchemic_reserve")+1));
                                    stack.setTag(nbtLocal);
                                }
                                nicConsumeMet0 = !nicConsumeMet0;

                            } else {
                                //estas 3 lineas ban sin la logica del nocrosil
                                stack.getTag().putString("key",changeOwner(player,stack.getTag(),true));
                                nbtLocal.putInt(this.metals[0].getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(this.metals[0].getNameLower()+"_feruchemic_reserve")+1));
                                stack.setTag(nbtLocal);
                            }


                        } else {
                            data.setStoring(this.metals[0],false);
                        }
                    }

                    if (data.isDecanting(this.metals[1])) {
                        if (stack.getTag().getInt(this.metals[1].getNameLower()+"_feruchemic_reserve")>0) {
                            if (data.isDecanting(MetalsNBTData.NICROSIL)){
                                if (!nicConsumeMet1){
                                    nbtLocal.putInt(this.metals[1].getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(this.metals[1].getNameLower()+"_feruchemic_reserve")-1));
                                    stack.setTag(nbtLocal);
                                }
                                nicConsumeMet1 = !nicConsumeMet1;
                            } else {
                                //las dos lineas de abajo van sin el nicrosil
                                nbtLocal.putInt(this.metals[1].getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(this.metals[1].getNameLower()+"_feruchemic_reserve")-1));
                                stack.setTag(nbtLocal);
                            }
                        } else {
                            stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                            data.setDecanting(this.metals[1],false);
                        }

                    } else if (data.isStoring(this.metals[1])) {
                        if (stack.getTag().getInt(this.metals[1].getNameLower()+"_feruchemic_reserve") < stack.getTag().getInt(this.metals[1].getNameLower()+"_feruchemic_max_capacity")) {

                            if (data.isStoring(MetalsNBTData.NICROSIL)) {
                                if (!nicConsumeMet1){
                                    stack.getTag().putString("key",changeOwner(player,stack.getTag(),true));
                                    nbtLocal.putInt(this.metals[1].getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(this.metals[1].getNameLower()+"_feruchemic_reserve")+1));
                                    stack.setTag(nbtLocal);
                                }
                                nicConsumeMet1 = !nicConsumeMet1;

                            } else {
                                stack.getTag().putString("key",changeOwner(player,stack.getTag(),true));
                                nbtLocal.putInt(this.metals[1].getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(this.metals[1].getNameLower()+"_feruchemic_reserve")+1));
                                stack.setTag(nbtLocal);
                            }
                        } else {
                            data.setStoring(this.metals[1],false);
                        }
                    }
                    ModNetwork.sync(data, player);
                });
            }
        }
        ICurioItem.super.curioTick(identifier, index, livingEntity, stack);
    }

    private static String dato;

    public String changeOwner(Player player, CompoundTag compoundNBT,boolean iStoreMetal) {

        boolean isFirstReserveZero = compoundNBT.getInt(this.metals[0].getNameLower()+"_feruchemic_reserve") == 0;
        boolean isSecondReserveZero = compoundNBT.getInt(this.metals[1].getNameLower()+"_feruchemic_reserve") == 0;

        dato = compoundNBT.getString("key");

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {
            if (isFirstReserveZero && isSecondReserveZero && !data.isStoring(MetalsNBTData.ALUMINUM) &&
                    !data.isDecanting(MetalsNBTData.ALUMINUM) && iStoreMetal){
                dato = player.getStringUUID();
            } else if (isFirstReserveZero && isSecondReserveZero && !data.isStoring(MetalsNBTData.ALUMINUM) &&
                    !data.isDecanting(MetalsNBTData.ALUMINUM) && !iStoreMetal){
                dato = unkeyedString;
            }
            else if (data.isStoring(MetalsNBTData.ALUMINUM)) {
                dato = unkeyedString;
            } else if (data.isDecanting(MetalsNBTData.ALUMINUM)){
                dato = player.getStringUUID();
            }
        });
        return dato;
    }

    public MetalsNBTData getMetals(int pos) {
        return this.metals[pos];
    }

}


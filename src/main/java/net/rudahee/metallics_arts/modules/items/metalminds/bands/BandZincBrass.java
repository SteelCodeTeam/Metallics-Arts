package net.rudahee.metallics_arts.modules.items.metalminds.bands;


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
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;


public class BandZincBrass extends BandMindAbstract implements ICurioItem {

    public BandZincBrass (Item.Properties properties){
        super(properties, MetalsNBTData.ZINC,MetalsNBTData.BRASS,MetalsNBTData.ZINC.getMaxReserveBand(),MetalsNBTData.BRASS.getMaxReserveBand());
    }

    private static boolean nicConsumeMet0 = false;
    private static boolean nicConsumeMet1 = false;

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {

        CompoundTag nbtLocal = stack.getTag();

        if (livingEntity.level instanceof ServerLevel) {
            if (livingEntity instanceof Player) {
                Player player = (Player) livingEntity;
                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {

                    if (data.isDecanting(MetalsNBTData.ALUMINUM)||data.isStoring(MetalsNBTData.ALUMINUM)){
                        stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                    }

                    if (data.isDecanting(getMetals(0))) {
                        if (stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")>0) {
                            if (data.isDecanting(MetalsNBTData.NICROSIL)){
                                if (!nicConsumeMet0){
                                    nbtLocal.putInt(getMetals(0).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")-1));
                                    stack.setTag(nbtLocal);
                                }
                                nicConsumeMet0 = !nicConsumeMet0;
                            } else {
                                //las dos lineas de abajo van sin el nicrosil
                                nbtLocal.putInt(getMetals(0).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")-1));
                                stack.setTag(nbtLocal);
                            }
                        } else {
                            stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                            data.setDecanting(getMetals(0),false);
                        }
                    } else if (data.isStoring(getMetals(0))) {
                        if (stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") < stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_max_capacity")) {
                            if (data.isStoring(MetalsNBTData.NICROSIL)) {
                                if (!nicConsumeMet0){
                                    stack.getTag().putString("key",changeOwner(player,stack.getTag(),true));
                                    nbtLocal.putInt(getMetals(0).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")+1));
                                    stack.setTag(nbtLocal);
                                }
                                nicConsumeMet0 = !nicConsumeMet0;

                            } else {
                                //estas 3 lineas ban sin la logica del nocrosil
                                stack.getTag().putString("key",changeOwner(player,stack.getTag(),true));
                                nbtLocal.putInt(getMetals(0).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")+1));
                                stack.setTag(nbtLocal);
                            }




                        } else {
                            data.setStoring(getMetals(0),false);
                        }
                    }

                    if (data.isDecanting(getMetals(1))) {
                        if (stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")>0) {
                            if (data.isDecanting(MetalsNBTData.NICROSIL)){
                                if (!nicConsumeMet1){
                                    nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")-1));
                                    stack.setTag(nbtLocal);
                                }
                                nicConsumeMet1 = !nicConsumeMet1;
                            } else {
                                //las dos lineas de abajo van sin el nicrosil
                                nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")-1));
                                stack.setTag(nbtLocal);
                            }

                        } else {
                            stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                            data.setDecanting(getMetals(1),false);
                        }

                    } else if (data.isStoring(getMetals(1))){   //PROPIO DE ESTA MENTE DE METAL <- CALOR
                        if (stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") < stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity")) {
                            if (player.isOnFire()){
                                if (data.isStoring(MetalsNBTData.NICROSIL)) {
                                    if (!nicConsumeMet1){
                                        stack.getTag().putString("key",changeOwner(player,stack.getTag(),true));
                                        nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")+1));
                                        stack.setTag(nbtLocal);
                                    }
                                    nicConsumeMet1 = !nicConsumeMet1;
                                } else {
                                    stack.getTag().putString("key",changeOwner(player,stack.getTag(),true));
                                    nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")+1));
                                    stack.setTag(nbtLocal);
                                }
                            }
                        } else {
                            data.setStoring(getMetals(1),false);
                        }
                    }
                    ModNetwork.sync(data, player);
                });
            }
        }
        super.curioTick(identifier, index, livingEntity, stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> toolTips, TooltipFlag flagIn) {
        if (stack.hasTag()) {
            if (!Screen.hasControlDown()){
                toolTips.add(Component.translatable(getMetals(0).getNameLower().substring(0,1).toUpperCase()+getMetals(0).getNameLower().substring(1)+": "+ stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") / 40 + "s"));
                toolTips.add(Component.translatable(getMetals(1).getNameLower().substring(0,1).toUpperCase()+getMetals(1).getNameLower().substring(1)+": "+ stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") / 40 + "s"));
            } else {
                toolTips.add(Component.translatable(getMetals(0).getNameLower().substring(0,1).toUpperCase()+getMetals(0).getNameLower().substring(1)+": "+ ((stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_max_capacity"))+"%"));
                toolTips.add(Component.translatable(getMetals(1).getNameLower().substring(0,1).toUpperCase()+getMetals(1).getNameLower().substring(1)+": "+ ((stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity"))+"%"));
            }
            toolTips.add(Component.translatable("Owner: "+ (stack.getTag().getString("key"))));
        }
        super.appendHoverText(stack, world, toolTips, flagIn);
    }


}
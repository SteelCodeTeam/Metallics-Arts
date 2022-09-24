package net.rudahee.metallics_arts.modules.items.metalminds.bands;


import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.modules.data_player.IDefaultInvestedPlayerData;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BandLerasiumEttmetal extends BandMindAbstract {

    public BandLerasiumEttmetal (Item.Properties properties){
        super(properties, MetalsNBTData.LERASIUM,MetalsNBTData.ETTMETAL,MetalsNBTData.LERASIUM.getMaxReserveBand(), MetalsNBTData.ETTMETAL.getMaxReserveBand());
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        CompoundTag nbtLocal = stack.getTag();

        if (livingEntity.level instanceof ServerLevel) {
            if (livingEntity instanceof Player) {
                Player player = (Player) livingEntity;
                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {

                    if (data.isDecanting(MetalsNBTData.ALUMINUM) || data.isStoring(MetalsNBTData.ALUMINUM)) {
                        stack.getTag().putString("key", changeOwner(player, stack.getTag(), false));
                    }

                    /////////////LERASIUM///////////////////
                    if (data.isDecanting(MetalsNBTData.LERASIUM)) {
                        loadAllomanticReserve(data, stack);
                        nbtLocal.putInt(getMetals(0).getNameLower() + "_feruchemic_reserve", 0);
                        nbtLocal.putString("key", changeOwner(player, nbtLocal, false));
                        data.setDecanting(getMetals(0), false);
                        stack.setTag(nbtLocal);
                    } else if (data.isStoring(MetalsNBTData.LERASIUM)) {
                        if (havePlayerAnyReserve(data)) {
                            saveAllomanticReserve(data, stack);
                            nbtLocal.putString("key", changeOwner(player, nbtLocal, true));
                            nbtLocal.putInt(getMetals(0).getNameLower() + "_feruchemic_reserve",1);
                            data.setStoring(getMetals(0), false);
                        } else {
                            data.setStoring(getMetals(0), false);
                        }

                    }

                    /////////////ETTMETAL////////////////
                    if (data.isDecanting(getMetals(1))) {
                        if (nbtLocal.getInt(getMetals(1).getNameLower() + "_feruchemic_reserve") > 0) {

                            if (nbtLocal.getInt(getMetals(1).getNameLower() + "_feruchemic_reserve") > 10) {
                                player.level.explode(player,player.position().x,player.position().y,player.position().z,(float) nbtLocal.getInt(getMetals(1).getNameLower() + "_feruchemic_reserve")/410,Explosion.BlockInteraction.NONE);
                                player.hurt(DamageSource.MAGIC,(float) nbtLocal.getInt(getMetals(1).getNameLower() + "_feruchemic_reserve")/205); // 410 hace medio corazon por carga, por lo que la 10 serian 5 corazones
                                nbtLocal.putInt(getMetals(1).getNameLower() + "_feruchemic_reserve", 0);
                                stack.setTag(nbtLocal);
                            } else {
                                nbtLocal.putInt(getMetals(1).getNameLower() + "_feruchemic_reserve", 0);
                                nbtLocal.putString("key",changeOwner(player,nbtLocal,false));
                                data.setDecanting(getMetals(1),false);
                            }

                        } else {
                            nbtLocal.putString("key",changeOwner(player,nbtLocal,false));
                            data.setDecanting(getMetals(1),false);
                        }
                    } else if (data.isStoring(getMetals(1))) {
                        if (nbtLocal.getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") < nbtLocal.getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity")) {

                            if (player.getLastDamageSource() != null){
                                if ((player.getLastDamageSource().isExplosion())){
                                    nbtLocal.putString("key",changeOwner(player,nbtLocal,true));
                                    //player.addEffect(new MobEffectInstance(MobEffects.REGENERATION,20,3,true,true));
                                    nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(nbtLocal.getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")+1));
                                }
                            }
                            stack.setTag(nbtLocal);
                        } else {
                            data.setStoring(getMetals(1), false);
                        }
                    }
                    ModNetwork.sync(data, player);
                });
            }
        }

        super.curioTick(slotContext, stack);
    }


    public boolean havePlayerAnyReserve (IDefaultInvestedPlayerData playerCapability) {
        for (MetalsNBTData metal: MetalsNBTData.values()){
            if (playerCapability.getAllomanticAmount(metal)>0) {
                return true;
            }
        }
        return false;
    }


    //ACA TENEMOS QUE ARREGLAR QUE NO SE PIERDA LAS RESERVAS ESXTA QUE VAN A LA MENTE DE LERASIUM
    public boolean saveAllomanticReserve(IDefaultInvestedPlayerData playerCapability, ItemStack stack) {
        boolean itsDone = false;
        ArrayList<MetalsNBTData> metals = playerCapability.getAllomanticPowers();
        int firstQty = 0;
        int qtyToRemove = 0;
        boolean continueSaving;

        for (MetalsNBTData metal: metals) {
            continueSaving = true;
            while (continueSaving) {
                if (firstQty == 0) {
                    firstQty = playerCapability.getAllomanticAmount(metal);
                    qtyToRemove = Math.toIntExact(Math.round(firstQty * 0.1));
                }
                continueSaving = playerCapability.substractAllomanticMetalAmount(metal, qtyToRemove);
                if (!continueSaving || firstQty == 0) {
                    firstQty = 0;
                    qtyToRemove = 0;
                    continueSaving = false;
                } else {
                    if (!stack.getTag().contains(metal.getNameLower()+"inLerasiumBand")){ //no existe el tag
                        stack.getTag().putInt(metal.getNameLower()+"inLerasiumBand",0);
                    }
                    stack.getTag().putInt(metal.getNameLower()+"inLerasiumBand", stack.getTag().getInt(metal.getNameLower()+"inLerasiumBand")+qtyToRemove);
                    itsDone = true;
                    if (stack.getTag().getInt(metal.getNameLower()+"inLerasiumBand") > metal.getMaxAllomanticTicksStorage()) {
                        stack.getTag().putInt(metal.getNameLower()+"inLerasiumBand",metal.getMaxAllomanticTicksStorage());
                        continueSaving = false;
                    }

                }
            }
        }
        return itsDone;
    }

    public boolean loadAllomanticReserve(IDefaultInvestedPlayerData playerCapability, ItemStack stack) {
        boolean itsDone = false;
        ArrayList<MetalsNBTData> metals = playerCapability.getAllomanticPowers();
        int firstQty = 0;
        int qtyToAdd = 0;
        boolean continueLoading = true;

        for (MetalsNBTData metal: metals) {
            continueLoading = true;
            while (continueLoading) {
                if (firstQty == 0) {
                    firstQty = stack.getTag().getInt(metal.getNameLower()+"inLerasiumBand");
                    qtyToAdd = Math.toIntExact(Math.round(firstQty * 0.1));
                }
                if (!continueLoading || stack.getTag().getInt(metal.getNameLower()+"inLerasiumBand") == 0) {
                    firstQty = 0;
                    qtyToAdd = 0;
                    continueLoading = false;
                } else {
                    if (!stack.getTag().contains(metal.getNameLower()+"inLerasiumBand")){ //no existe el tag
                        stack.getTag().putInt(metal.getNameLower()+"inLerasiumBand",0);
                    }
                    stack.getTag().putInt(metal.getNameLower()+"inLerasiumBand", stack.getTag().getInt(metal.getNameLower()+"inLerasiumBand")-qtyToAdd);
                    continueLoading = playerCapability.addAllomanticMetalAmount(metal, qtyToAdd);
                    itsDone = true;
                }
            }
        }
        return itsDone;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> toolTips, TooltipFlag flagIn) {
        if (stack.hasTag()) {
            if (stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")>0) {
                toolTips.add(Component.translatable(getMetals(0).getNameLower().substring(0,1).toUpperCase()+getMetals(0).getNameLower().substring(1)+": Has Reserve"));
            } else {
                toolTips.add(Component.translatable(getMetals(0).getNameLower().substring(0,1).toUpperCase()+getMetals(0).getNameLower().substring(1)+": Has not Reserve" ));
            }

            if (!Screen.hasControlDown()){
                toolTips.add(Component.translatable(getMetals(1).getNameLower().substring(0,1).toUpperCase()+getMetals(1).getNameLower().substring(1)+": "+ (stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")/41) ));
                toolTips.add(Component.translatable("Owner: "+ (stack.getTag().getString("key"))));
            } else {
                toolTips.add(Component.translatable(getMetals(1).getNameLower().substring(0,1).toUpperCase()+getMetals(1).getNameLower().substring(1)+": "+ ((stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity"))+"%"));
                toolTips.add(Component.translatable("Owner: "+ (stack.getTag().getString("key"))));
                toolTips.add(Component.translatable("-------------------"));
                for (MetalsNBTData metal : MetalsNBTData.values()){
                    if(stack.getTag().getInt(metal.getNameLower()+"inLerasiumBand")>0){
                        toolTips.add(Component.translatable("  "+metal.getNameLower()+": "+stack.getTag().getInt(metal.getNameLower()+"inLerasiumBand")));
                    }
                }
            }
        }
        super.appendHoverText(stack, world, toolTips, flagIn);
    }
}
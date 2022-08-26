package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.rudahee.metallics_arts.modules.data_player.IDefaultInvestedPlayerData;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.enums.metals.Metal;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BandLerasiumEttmetal extends BandMindAbstract {

    public BandLerasiumEttmetal (Item.Properties properties){
        super(properties, MetalsNBTData.LERASIUM,MetalsNBTData.ETTMETAL,MetalsNBTData.LERASIUM.getMaxReserveBand(), MetalsNBTData.ETTMETAL.getMaxReserveBand());
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

                    if (data.isDecanting(MetalsNBTData.ALUMINUM)||data.isStoring(MetalsNBTData.ALUMINUM)){
                        stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                    }
                    /////////////LERASIUM///////////////////

                    if (data.isDecanting(getMetals(0))) {
                        loadAllomanticReserve(data, stack);
                        nbtLocal.putInt(getMetals(0).getNameLower()+"_feruchemic_reserve",0);
                        nbtLocal.putString("key",changeOwner(player,nbtLocal,false));
                        data.setDecanting(getMetals(0),false);
                        stack.setTag(nbtLocal);
                        needUpdate = true;
                    } else if (data.isStoring(getMetals(0))) {
                        saveAllomanticReserve(data,stack);
                        if (haveAnyReserve(stack)){
                            nbtLocal.putInt(getMetals(0).getNameLower()+"_feruchemic_reserve",1);
                        } else {
                            nbtLocal.putInt(getMetals(0).getNameLower()+"_feruchemic_reserve",0);
                        }
                        nbtLocal.putString("key",changeOwner(player,nbtLocal,true));
                        data.setStoring(getMetals(0),false);
                        stack.setTag(nbtLocal);
                        needUpdate = true;
                    }
                    /////////////ETTMETAL////////////////
                    if (data.isDecanting(getMetals(1))) {
                        if (nbtLocal.getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")>0) {
                            nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(nbtLocal.getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")-1));
                            stack.setTag(nbtLocal);
                        } else {
                            nbtLocal.putString("key",changeOwner(player,nbtLocal,false));
                            data.setDecanting(getMetals(1),false);
                        }
                        needUpdate = true;
                    } else if (data.isStoring(getMetals(1))) {
                        if (nbtLocal.getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") < nbtLocal.getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity")) {
                            nbtLocal.putString("key",changeOwner(player,nbtLocal,true));
                            nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(nbtLocal.getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")+1));
                            stack.setTag(nbtLocal);
                        } else {
                            data.setStoring(getMetals(1),false);
                        }
                        needUpdate = true;
                    }
                });
            }
        }



        super.curioTick(identifier, index, livingEntity, stack);
    }

    private boolean haveAnyReserve(ItemStack stack) {

        for (MetalsNBTData metal: MetalsNBTData.values()){
            if (stack.getTag().getInt(metal.getNameLower()+"inLerasiumBand")>0){
                return true;
            }
        }

        return false;
    }

    private PlayerEntity target;
    private IDefaultInvestedPlayerData targetCapability;
    private MetalsNBTData targetMetal;




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
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> toolTips, ITooltipFlag flagIn) {
        if (stack.hasTag()) {
            if (!Screen.hasControlDown()){
                toolTips.add(new StringTextComponent(getMetals(0).getNameLower().substring(0,1).toUpperCase()+getMetals(0).getNameLower().substring(1)+": "+ stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")));
                toolTips.add(new StringTextComponent(getMetals(1).getNameLower().substring(0,1).toUpperCase()+getMetals(1).getNameLower().substring(1)+": "+ stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") / 40 + "s"));
                toolTips.add(new StringTextComponent("Owner: "+ (stack.getTag().getString("key"))));
            } else {
                toolTips.add(new StringTextComponent(getMetals(0).getNameLower().substring(0,1).toUpperCase()+getMetals(0).getNameLower().substring(1)+": "+ ((stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_max_capacity"))+"%"));
                toolTips.add(new StringTextComponent(getMetals(1).getNameLower().substring(0,1).toUpperCase()+getMetals(1).getNameLower().substring(1)+": "+ ((stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity"))+"%"));
                toolTips.add(new StringTextComponent("Owner: "+ (stack.getTag().getString("key"))));
                toolTips.add(new StringTextComponent("-------------------"));
                for (MetalsNBTData metal : MetalsNBTData.values()){
                    if(stack.getTag().getInt(metal.getNameLower()+"inLerasiumBand")>0){
                        toolTips.add(new StringTextComponent("  "+metal.getNameLower()+": "+stack.getTag().getInt(metal.getNameLower()+"inLerasiumBand")));
                    }
                }
            }
        }
        super.appendHoverText(stack, world, toolTips, flagIn);
    }
}
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
import net.minecraft.world.server.ServerWorld;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import org.spongepowered.asm.mixin.Final;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;

public class BandZincBrass extends BandMindAbstract implements ICurioItem {

    public BandZincBrass (Item.Properties properties){
        super(properties, MetalsNBTData.ZINC,MetalsNBTData.BRASS,MetalsNBTData.ZINC.getMaxReserveBand(),MetalsNBTData.BRASS.getMaxReserveBand());
    }

    private static boolean needUpdate = false;
    private static boolean nicConsumeMet0 = false;
    private static boolean nicConsumeMet1 = false;

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
                        needUpdate = true;
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
                        needUpdate = true;
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
                        needUpdate = true;

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
                        needUpdate = true;
                    }
                });
            }
        }
        super.curioTick(identifier, index, livingEntity, stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> toolTips, ITooltipFlag flagIn) {
        if (stack.hasTag()) {
            if (!Screen.hasControlDown()){
                toolTips.add(new StringTextComponent(getMetals(0).getNameLower().substring(0,1).toUpperCase()+getMetals(0).getNameLower().substring(1)+": "+ stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") / 40 + "s"));
                toolTips.add(new StringTextComponent(getMetals(1).getNameLower().substring(0,1).toUpperCase()+getMetals(1).getNameLower().substring(1)+": "+ stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") / 40 + "s"));
            } else {
                toolTips.add(new StringTextComponent(getMetals(0).getNameLower().substring(0,1).toUpperCase()+getMetals(0).getNameLower().substring(1)+": "+ ((stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_max_capacity"))+"%"));
                toolTips.add(new StringTextComponent(getMetals(1).getNameLower().substring(0,1).toUpperCase()+getMetals(1).getNameLower().substring(1)+": "+ ((stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity"))+"%"));
            }
            toolTips.add(new StringTextComponent("Owner: "+ (stack.getTag().getString("key"))));
        }
        super.appendHoverText(stack, world, toolTips, flagIn);
    }


}
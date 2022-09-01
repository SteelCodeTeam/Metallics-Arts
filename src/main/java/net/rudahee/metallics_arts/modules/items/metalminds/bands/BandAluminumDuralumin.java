package net.rudahee.metallics_arts.modules.items.metalminds.bands;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class BandAluminumDuralumin extends BandMindAbstract implements ICurioItem {

    public BandAluminumDuralumin (Item.Properties properties){
        super(properties, MetalsNBTData.ALUMINUM,MetalsNBTData.DURALUMIN,MetalsNBTData.ALUMINUM.getMaxReserveBand(),MetalsNBTData.DURALUMIN.getMaxReserveBand());
    }

    public static boolean nicConsumeMet1 = false;

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

                    nbtLocal.putInt(getMetals(0).getNameLower()+"_feruchemic_reserve",1);

                    ///////DURALUMINUM
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
                            stack.getTag().putString("key",localChangeOwner(player,stack.getTag(),false));
                            data.setDecanting(getMetals(1),false);
                        }

                    } else if (data.isStoring(getMetals(1))) {
                        if (stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") < stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity")) {

                            if (data.isStoring(MetalsNBTData.NICROSIL)) {
                                if (!nicConsumeMet1){
                                    stack.getTag().putString("key",localChangeOwner(player,stack.getTag(),true));
                                    nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")+1));
                                    stack.setTag(nbtLocal);
                                }
                                nicConsumeMet1 = !nicConsumeMet1;

                            } else {
                                stack.getTag().putString("key",localChangeOwner(player,stack.getTag(),true));
                                nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")+1));
                                stack.setTag(nbtLocal);
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



    private static String dato;

    public String localChangeOwner(Player player, CompoundTag compoundNBT,boolean iStoreMetal) {

        boolean isSecondReserveZero = compoundNBT.getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") == 0;

        dato = compoundNBT.getString("key");

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {
            if (isSecondReserveZero && !data.isStoring(MetalsNBTData.ALUMINUM) &&
                    !data.isDecanting(MetalsNBTData.ALUMINUM) && iStoreMetal){
                dato = player.getStringUUID();
            } else if (isSecondReserveZero && !data.isStoring(MetalsNBTData.ALUMINUM) &&
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


}
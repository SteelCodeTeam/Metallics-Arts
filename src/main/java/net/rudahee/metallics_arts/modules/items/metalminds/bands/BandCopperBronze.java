package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.server.ServerWorld;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class BandCopperBronze extends BandMindAbstract {

    public BandCopperBronze(Item.Properties properties){
        super(properties, MetalsNBTData.COPPER,MetalsNBTData.BRONZE,MetalsNBTData.COPPER.getMaxReserveBand(),MetalsNBTData.BRONZE.getMaxReserveBand());
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


                    //PROPIO DE ESTA MENTE DE METAL <- EXPERIENCIA
                    if (data.isDecanting(getMetals(0))) {
                        if (stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")>0) {
                            player.giveExperiencePoints(10);
                            nbtLocal.putInt(getMetals(0).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")-10));
                            stack.setTag(nbtLocal);
                        } else {
                            data.setDecanting(getMetals(0),false);
                        }
                        needUpdate = true;
                    } else if (data.isStoring(getMetals(0))){

                        if (stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") < stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_max_capacity")) {

                            if (player.experienceProgress>0||player.experienceLevel>0){
                                player.giveExperiencePoints(-10);
                                nbtLocal.putInt(getMetals(0).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")+10));
                            }

                            stack.setTag(nbtLocal);
                        } else {
                            data.setStoring(getMetals(0),false);
                        }
                        needUpdate = true;

                    }






                    if (data.isDecanting(getMetals(1))) {
                        if (stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")>0) {
                            nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")-1));
                            stack.setTag(nbtLocal);
                        } else {
                            data.setDecanting(getMetals(1),false);
                        }
                        needUpdate = true;

                    } else if (data.isStoring(getMetals(1))){   //propio de almacenar calor
                        if (stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") < stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity")) {
                            if (player.isOnFire()){
                                nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")+1));
                                stack.setTag(nbtLocal);
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


}
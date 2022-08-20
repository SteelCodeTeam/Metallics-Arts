package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.server.ServerWorld;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import org.spongepowered.asm.mixin.Final;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class BandZincBrass extends BandMindAbstract implements ICurioItem {

    public BandZincBrass (Item.Properties properties){
        super(properties, MetalsNBTData.ZINC,MetalsNBTData.BRASS,MetalsNBTData.ZINC.getMaxReserveBand(),MetalsNBTData.BRASS.getMaxReserveBand());
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

                    if (data.isDecanting(getMetals(1))) {
                        if (stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")>0) {
                            nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")-1));
                            stack.setTag(nbtLocal);
                        } else {
                            stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                            data.setDecanting(getMetals(1),false);
                        }
                        needUpdate = true;
                    } else if (data.isStoring(getMetals(1))) {
                        if (stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") < stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity")) {
                            stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                            nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")+1));
                            stack.setTag(nbtLocal);
                        } else {
                            data.setStoring(getMetals(1),false);
                        }
                        needUpdate = true;
                    }

                    if (data.isDecanting(getMetals(1))) {
                        if (stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")>0) {
                            nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")-1));
                            stack.setTag(nbtLocal);
                        } else {
                            stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                            data.setDecanting(getMetals(1),false);
                        }
                        needUpdate = true;

                    } else if (data.isStoring(getMetals(1))){   //PROPIO DE ESTA MENTE DE METAL <- CALOR
                        if (stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") < stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity")) {
                            if (player.isOnFire()){
                                stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
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
package net.rudahee.metallics_arts.modules.items.metal_spike;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.enums.metals.Metal;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModItems;
import org.lwjgl.system.CallbackI;

public class AluminumSpike extends MetalSpikeAbstract{
    CompoundNBT nbt = new CompoundNBT();
    private ItemStack final_result = ItemStack.EMPTY;

    public AluminumSpike(Properties properties) {
        super(properties);
        nbt.putBoolean("feruchemy", false);
        nbt.putBoolean("allomancy", false);
        setNbt(nbt);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof ServerPlayerEntity || target instanceof PlayerEntity){
            if (attacker instanceof ServerPlayerEntity || attacker instanceof PlayerEntity){
                target.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{

                    boolean alo = data.hasAllomanticPower(MetalsNBTData.ALUMINUM);
                    boolean feru = data.hasFeruchemicPower(MetalsNBTData.ALUMINUM);
                    if (nbt.getBoolean("feruchemy")||nbt.getBoolean("allomancy")){
                        if(nbt.getBoolean("feruchemy")){
                            data.addFeruchemicPower(MetalsNBTData.ALUMINUM);
                        }else if(nbt.getBoolean("allomancy")){
                            data.addAllomanticPower(MetalsNBTData.ALUMINUM);
                        }
                    }else{
                        if (alo && feru){
                            if (Math.random()>0.49){
                                if (Math.random()>0.90){
                                    this.final_result = new ItemStack(ModItems.);
                                    if (Math.random()>0.49){
                                        data.removeAllomanticPower(MetalsNBTData.ALUMINUM);
                                    }
                                }
                            }else {
                                if (Math.random()>0.90){
                                    //dar item
                                    if (Math.random()>0.49){
                                        data.removeFeruchemicPower(MetalsNBTData.ALUMINUM);
                                    }
                                }
                            }
                        }else if(alo){
                            if (Math.random()>0.90){
                                //dar item
                                if (Math.random()>0.49){
                                    data.removeAllomanticPower(MetalsNBTData.ALUMINUM);
                                }
                            }
                        }else if (feru){
                            if (Math.random()>0.90){
                                //dar item
                                if (Math.random()>0.49){
                                    data.removeFeruchemicPower(MetalsNBTData.ALUMINUM);
                                }
                            }
                        }
                    }
                    ModNetwork.sync(data,(PlayerEntity) target);
                });
            }
        }


        return super.hurtEnemy(stack, target, attacker);
    }
}



/*
                       if (data.hasAllomanticPower(MetalsNBTData.ALUMINUM)){
                        if (Math.random()>0.90){
                            nbt.putBoolean("allomancy",true);
                            if (Math.random()>0.49){
                                data.removeAllomanticPower(MetalsNBTData.ALUMINUM);
                            }
                        }
 */
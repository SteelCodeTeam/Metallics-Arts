package net.rudahee.metallics_arts.modules.items.metal_spike;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModItems;

public class EttmetalSpike extends MetalSpikeAbstract{

    public EttmetalSpike(Properties properties) {
        super(properties);
    }
    private ItemStack final_result = ItemStack.EMPTY;

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if ((target instanceof ServerPlayerEntity || target instanceof PlayerEntity) && (attacker instanceof ServerPlayerEntity || attacker instanceof PlayerEntity)){

            target.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{

                boolean hasAllomanticPower = super.hasAllomanticPower(MetalsNBTData.ETTMETAL, data);
                boolean hasFeruchemicPower = super.hasFeruchemicPower(MetalsNBTData.ETTMETAL, data);

                boolean couldStealPower = Math.random()>0.90;
                boolean couldRemovePower = Math.random()>0.50;
                boolean isAllomantic = Math.random()>0.50;

                if (!super.hasPlayerBothPowers(MetalsNBTData.ETTMETAL, data)) {
                    if (super.getFeruchemicNbt()){
                        data.addFeruchemicPower(MetalsNBTData.ETTMETAL);
                    } else if (super.getAllomanticNbt()){
                        data.addAllomanticPower(MetalsNBTData.ETTMETAL);
                    }
                } else {
                    if (super.hasPlayerBothPowers(MetalsNBTData.ETTMETAL, data)) {
                        if (isAllomantic) {
                            if (couldStealPower){
                                if (couldRemovePower){
                                    data.removeAllomanticPower(MetalsNBTData.ETTMETAL);
                                }
                                this.addItemToPlayer((PlayerEntity) target);
                            }
                        } else {
                            if (couldStealPower){
                                if (couldRemovePower){
                                    data.removeFeruchemicPower(MetalsNBTData.ETTMETAL);
                                }
                                this.addItemToPlayer((PlayerEntity) target);
                            }
                        }
                    } else if (hasAllomanticPower){
                        if (Math.random()>0.90){
                            if (Math.random()>0.49){
                                data.removeAllomanticPower(MetalsNBTData.ETTMETAL);
                            }
                            this.addItemToPlayer((PlayerEntity) target);
                        }
                    } else if (hasFeruchemicPower){
                        if (Math.random()>0.90){
                            if (Math.random()>0.49){
                                data.removeFeruchemicPower(MetalsNBTData.ETTMETAL);
                            }
                            this.addItemToPlayer((PlayerEntity) target);
                        }
                    }
                }
                ModNetwork.sync(data,(PlayerEntity) target);
            });
        }
        return super.hurtEnemy(stack, target, attacker);
    }


    public void addItemToPlayer(PlayerEntity target) {
        this.final_result = new ItemStack(ModItems.ETTMETAL_SPIKE.get(), 1);
        final_result.addTagElement("ettmetal_spike", super.getAllNbt());
        target.addItem(final_result);
    }
}

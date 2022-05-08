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

public class MalatiumSpike extends MetalSpikeAbstract{

    public MalatiumSpike(Properties properties) {
        super(properties);
    }
    private ItemStack final_result = ItemStack.EMPTY;

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if ((target instanceof ServerPlayerEntity || target instanceof PlayerEntity) && (attacker instanceof ServerPlayerEntity || attacker instanceof PlayerEntity)){

            target.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{

                boolean hasAllomanticPower = super.hasAllomanticPower(MetalsNBTData.MALATIUM, data);
                boolean hasFeruchemicPower = super.hasFeruchemicPower(MetalsNBTData.MALATIUM, data);

                boolean couldStealPower = Math.random()>0.90;
                boolean couldRemovePower = Math.random()>0.50;
                boolean isAllomantic = Math.random()>0.50;

                if (!super.hasPlayerBothPowers(MetalsNBTData.MALATIUM, data)) {
                    if (super.getFeruchemicNbt()){
                        data.addFeruchemicPower(MetalsNBTData.MALATIUM);
                    } else if (super.getAllomanticNbt()){
                        data.addAllomanticPower(MetalsNBTData.MALATIUM);
                    }
                } else {
                    if (super.hasPlayerBothPowers(MetalsNBTData.MALATIUM, data)) {
                        if (isAllomantic) {
                            if (couldStealPower){
                                if (couldRemovePower){
                                    data.removeAllomanticPower(MetalsNBTData.MALATIUM);
                                }
                                this.addItemToPlayer((PlayerEntity) target);
                            }
                        } else {
                            if (couldStealPower){
                                if (couldRemovePower){
                                    data.removeFeruchemicPower(MetalsNBTData.MALATIUM);
                                }
                                this.addItemToPlayer((PlayerEntity) target);
                            }
                        }
                    } else if (hasAllomanticPower){
                        if (Math.random()>0.90){
                            if (Math.random()>0.49){
                                data.removeAllomanticPower(MetalsNBTData.MALATIUM);
                            }
                            this.addItemToPlayer((PlayerEntity) target);
                        }
                    } else if (hasFeruchemicPower){
                        if (Math.random()>0.90){
                            if (Math.random()>0.49){
                                data.removeFeruchemicPower(MetalsNBTData.MALATIUM);
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
        this.final_result = new ItemStack(ModItems.MALATIUM_SPIKE.get(), 1);
        final_result.addTagElement("malatium_spike", super.getAllNbt());
        target.addItem(final_result);
    }
}

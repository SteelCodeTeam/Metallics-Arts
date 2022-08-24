package net.rudahee.metallics_arts.modules.items.metal_spike;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModItems;

public class NicrosilSpike extends MetalSpikeAbstract{

    public NicrosilSpike(Properties properties) {
        super(properties);
    }

    private ItemStack final_result = ItemStack.EMPTY;

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if ((target instanceof ServerPlayerEntity || target instanceof PlayerEntity) && (attacker instanceof ServerPlayerEntity || attacker instanceof PlayerEntity)){

            target.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(targetCapability ->{

                boolean hasTargetAllomanticPower = super.hasPlayerAllomanticPower(MetalsNBTData.ZINC, targetCapability);
                boolean hasTargetFeruchemicPower = super.hasPlayerFeruchemicPower(MetalsNBTData.ZINC, targetCapability);

                boolean couldStealPower = Math.random()>0.60;
                boolean couldRemovePower = Math.random()>0.50;
                boolean isAllomantic = Math.random()>0.50;

                // We show the item's nbt, if we dont have nbt we steal it, otherwise we concede the power.
                if (super.getAllomanticNbt() || super.getFeruchemicNbt()) {

                    if (super.getAllomanticNbt()) {
                        if (!super.hasPlayerAllomanticPower(MetalsNBTData.ZINC, targetCapability)) {
                            targetCapability.addAllomanticPower(MetalsNBTData.ZINC);
                            target.hurt(DamageSource.MAGIC, 6);
                            new LightningBoltEntity(EntityType.LIGHTNING_BOLT, target.level).setVisualOnly(true);
                            target.level.playLocalSound(target.position().x, target.position().y, target.position().z, SoundEvents.GENERIC_EXPLODE , SoundCategory.HOSTILE, 1.0f, 2.0f, true);
                        }
                    } else {
                        if (!super.hasPlayerFeruchemicPower(MetalsNBTData.ZINC, targetCapability)) {
                            targetCapability.addFeruchemicPower(MetalsNBTData.ZINC);
                            target.hurt(DamageSource.MAGIC, 6);
                            new LightningBoltEntity(EntityType.LIGHTNING_BOLT, target.level).setVisualOnly(true);
                            target.level.playLocalSound(target.position().x, target.position().y, target.position().z, SoundEvents.GENERIC_EXPLODE ,SoundCategory.HOSTILE, 1.0f, 2.0f, true);

                        }
                    }

                } else {
                    // if target have both (Allomancy and Feruchemic)
                    if (super.hasPlayerBothPowers(MetalsNBTData.ZINC, targetCapability)) {
                        // 50% allomancy
                        if (isAllomantic) {
                            // 40% to steal
                            if (couldStealPower){
                                // 50% of 40% to remove power
                                if (couldRemovePower){
                                    targetCapability.removeAllomanticPower(MetalsNBTData.ZINC);
                                }
                                // Spike obtain the power
                                super.setAllomanticNbt(true);
                                // Give the new item to player
                                this.addItemToPlayer((PlayerEntity) attacker);
                            }
                            // 50% feruchemic
                        } else {
                            // 40% to steal
                            if (couldStealPower){
                                // 50% of 40% to remove power
                                if (couldRemovePower){
                                    targetCapability.removeFeruchemicPower(MetalsNBTData.ZINC);
                                }
                                // Spike obtain the power
                                super.setFeruchemicNbt(true);
                                // Give the new item to player
                                this.addItemToPlayer((PlayerEntity) attacker);
                            }
                        }
                        // if the target only have allomantic power
                    } else if (hasTargetAllomanticPower){
                        // if only have 1/2 powers less prob to steal and lose your power
                        if (Math.random()>0.70) {
                            if (Math.random() > 0.70) {
                                targetCapability.removeAllomanticPower(MetalsNBTData.ZINC);
                            }
                            // Spike obtain the power
                            super.setAllomanticNbt(true);
                            // Give the new item to player
                            this.addItemToPlayer((PlayerEntity) attacker);
                        }
                        // if the target only have feruchemic power
                    } else if (hasTargetFeruchemicPower){
                        // if only have 1/2 powers less prob to steal and lose your power
                        if (Math.random()>0.70){
                            if (Math.random()>0.70){
                                targetCapability.removeFeruchemicPower(MetalsNBTData.ZINC);
                            }
                            // Spike obtain the power
                            super.setFeruchemicNbt(true);
                            // Give the new item to player
                            this.addItemToPlayer((PlayerEntity) attacker);
                        }
                    }

                }

                ModNetwork.sync(targetCapability,(PlayerEntity) target);
            });
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    public void addItemToPlayer(PlayerEntity attacker) {
        this.final_result = new ItemStack(ModItems.ZINC_SPIKE.get(), 1);
        final_result.addTagElement("zinc_spike", super.getAllNbt());
        attacker.addItem(final_result);
    }
}

package net.rudahee.metallics_arts.modules.items.metal_spike;

import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.rudahee.metallics_arts.modules.data_player.IDefaultInvestedPlayerData;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModItems;

import javax.annotation.Nullable;
import java.util.List;

public abstract class MetalSpikeAbstract extends SwordItem {

    private static final int ATTACK_DAMAGE = 1;
    private static final float ATTACK_SPEED = -3f;

    private MetalsNBTData metalSpike;

    public MetalSpikeAbstract(Properties properties,MetalsNBTData metalsNBTData) {
        super(tier, ATTACK_DAMAGE, ATTACK_SPEED, properties);
        this.metalSpike = metalsNBTData;
    }


    private static final IItemTier tier = new IItemTier() {
        @Override
        public int getUses() {
            return 1;
        }

        @Override
        public float getSpeed() {
            return 0;
        }

        @Override
        public float getAttackDamageBonus() {
            return 0;
        }

        @Override
        public int getLevel() {
            return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return 0;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Blocks.OBSIDIAN);
        }
    };


    @Override
    public boolean isFoil(ItemStack stack) {
        return stack.getTag().getBoolean("feruchemic_power") || stack.getTag().getBoolean("allomantic_power") ? true : false;
    }

    public boolean hasPlayerBothPowers(MetalsNBTData metal, IDefaultInvestedPlayerData cap) {
        if (cap.hasAllomanticPower(metal) && cap.hasFeruchemicPower(metal)) {
            return true;
        }
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> toolTips, ITooltipFlag flag) {
        if  (!stack.getTag().contains("metal_spike")||!stack.getTag().contains("feruchemic_power") || !stack.getTag().contains("allomantic_power"))  {
            stack.setTag(generateTags(stack));
        }
        if (stack.hasTag()){

            if (stack.getTag().getBoolean("feruchemic_power")){
                toolTips.add(new StringTextComponent("Power: Feruchemic"));
            }
            if (stack.getTag().getBoolean("allomantic_power")){
                toolTips.add(new StringTextComponent("Power: Allomantic"));
            }
        }
        super.appendHoverText(stack, world, toolTips, flag);
    }

    public CompoundNBT generateTags(ItemStack stack){
        stack.getTag().putInt("metal_spike",this.metalSpike.getIndex());
        stack.getTag().putBoolean("feruchemic_power",false);
        stack.getTag().putBoolean("allomantic_power",false);
        return stack.getTag();
    }



    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if  (!stack.getTag().contains("metal_spike")||!stack.getTag().contains("feruchemic_power") || !stack.getTag().contains("allomantic_power"))  {
            stack.setTag(generateTags(stack));
        }

        if ((target instanceof ServerPlayerEntity || target instanceof PlayerEntity) && (attacker instanceof ServerPlayerEntity || attacker instanceof PlayerEntity)){

            target.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(targetData -> {

                boolean hasAllomanticPower = targetData.hasAllomanticPower(MetalsNBTData.getMetal(stack.getTag().getInt("metal_spike")));
                boolean hasFeruchemicPower = targetData.hasFeruchemicPower(MetalsNBTData.getMetal(stack.getTag().getInt("metal_spike")));

                boolean couldStealPower = Math.random()>0.90;
                boolean couldRemovePower = Math.random()>0.50;
                boolean isAllomantic = Math.random()>0.50;

                MetalsNBTData localMetal = MetalsNBTData.getMetal(stack.getTag().getInt("metal_spike"));

                //DAR PODER
                if (stack.getTag().getBoolean("allomantic_power")){
                    if (!targetData.hasAllomanticPower(localMetal)){
                        targetData.addAllomanticPower(localMetal);
                    }

                } else if (stack.getTag().getBoolean("feruchemic_power")){
                    if (!targetData.hasFeruchemicPower(localMetal)){
                        targetData.addFeruchemicPower(localMetal);
                    }

                //SI EL CLAVO NO TIENE PODERES -> intenta robar
                } else if (hasPlayerBothPowers(localMetal, targetData)) {
                    //SI EL OBJETIVO TIENE AMBOS PODERES
                    if (isAllomantic) {
                        if (couldStealPower){
                            if (couldRemovePower){
                                targetData.removeAllomanticPower(localMetal);
                            }
                            stack.getTag().putBoolean("allomantic_power",true);
                            addItemToPlayer((PlayerEntity) attacker, stack);
                        }
                    } else {
                        if (couldStealPower){
                            if (couldRemovePower){
                                targetData.removeFeruchemicPower(localMetal);
                            }
                            stack.getTag().putBoolean("feruchemic_power",true);
                            addItemToPlayer((PlayerEntity) attacker, stack);
                        }
                    }
                } else if (hasAllomanticPower){
                    if (Math.random()>0.90){
                        if (Math.random()>0.49){
                            targetData.removeAllomanticPower(localMetal);
                        }
                        stack.getTag().putBoolean("allomantic_power",true);
                        addItemToPlayer((PlayerEntity) attacker, stack);
                    }
                } else if (hasFeruchemicPower){
                    if (Math.random()>0.90){
                        if (Math.random()>0.49){
                            targetData.removeFeruchemicPower(localMetal);
                        }
                        stack.getTag().putBoolean("feruchemic_power",true);
                        addItemToPlayer((PlayerEntity) attacker, stack);
                    }
                }
                ModNetwork.sync(targetData,(PlayerEntity) target);
            });
        }
        return super.hurtEnemy(stack, target, attacker);
    }


    public void addItemToPlayer(PlayerEntity attacker,ItemStack stack) {
        ItemStack itemStack = stack.copy();
        attacker.addItem(itemStack);
    }
}

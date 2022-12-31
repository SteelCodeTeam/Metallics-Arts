package net.rudahee.metallics_arts.modules.items.metal_spike;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.rudahee.metallics_arts.data.enums.implementations.MetalsNBTData;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public abstract class MetalSpikeAbstract extends SwordItem {

    private static final int ATTACK_DAMAGE = 1;
    private static final float ATTACK_SPEED = -3f;

    private final MetalsNBTData metalSpike;

    public MetalSpikeAbstract(Item.Properties properties, MetalsNBTData metalsNBTData) {
        super(tier, ATTACK_DAMAGE, ATTACK_SPEED, properties);
        this.metalSpike = metalsNBTData;
    }


    private static final Tier tier = new Tier() {
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
        return stack.getTag().getBoolean("feruchemic_power") || stack.getTag().getBoolean("allomantic_power");
    }

    public boolean hasPlayerBothPowers(MetalsNBTData metal, IInvestedPlayerData cap) {
        return cap.hasAllomanticPower(metal) && cap.hasFeruchemicPower(metal);
    }



    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> toolTips, TooltipFlag flag) {
        if  (!stack.getTag().contains("me")||!stack.getTag().contains("feruchemic_power") || !stack.getTag().contains("allomantic_power"))  {
            stack.setTag(generateTags(stack));
        }
        if (stack.hasTag()){
            if (stack.getTag().getBoolean("feruchemic_power")) {
                toolTips.add(Component.translatable("metallics_arts.spike_feruchemic_power"));
            }
            if (stack.getTag().getBoolean("allomantic_power")) {
                toolTips.add(Component.translatable("metallics_arts.spike_allomantic_power"));
            }
        }
        super.appendHoverText(stack, world, toolTips, flag);
    }

    public CompoundTag generateTags(ItemStack stack){
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


        if ((target instanceof Player) && (attacker instanceof Player)){


            target.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(targetData -> {

                boolean hasAllomanticPower = targetData.hasAllomanticPower(MetalsNBTData.getMetal(stack.getTag().getInt("metal_spike")));
                boolean hasFeruchemicPower = targetData.hasFeruchemicPower(MetalsNBTData.getMetal(stack.getTag().getInt("metal_spike")));

                boolean couldStealPower = Math.random()>0.50;
                boolean couldRemovePower = Math.random()<0.75;
                boolean isAllomantic = Math.random()>0.50;

                MetalsNBTData localMetal = MetalsNBTData.getMetal(stack.getTag().getInt("metal_spike"));

                Random rng = new Random();
                Level world = target.level;
                BlockPos pos = new BlockPos(target.position());

                //DAR PODER
                if (stack.getTag().getBoolean("allomantic_power")){
                    if (!targetData.hasAllomanticPower(localMetal)){
                        targetData.addAllomanticPower(localMetal);
                        doEffects(rng, world, pos);
                    }
                } else if (stack.getTag().getBoolean("feruchemic_power")){
                    if (!targetData.hasFeruchemicPower(localMetal)){
                        targetData.addFeruchemicPower(localMetal);
                        doEffects(rng, world, pos);
                    }

                //SI EL CLAVO NO TIENE PODERES -> intenta robar
                } else if (hasPlayerBothPowers(localMetal, targetData)) {
                    //SI EL OBJETIVO TIENE AMBOS PODERES
                    if (isAllomantic) {
                        if (Math.random()>0.50){
                            if (Math.random()<0.75){
                                targetData.removeAllomanticPower(localMetal);

                                target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 40, 1, true, true, false));

                                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 1, true, true, false));

                            }
                            stack.getTag().putBoolean("allomantic_power",true);
                            addItemToPlayer((Player) attacker, stack);
                        }
                    } else {
                        if (Math.random()>0.50){
                            if (Math.random()<0.75){
                                targetData.removeFeruchemicPower(localMetal);

                                target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 40, 1, true, true, false));

                                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 1, true, true, false));

                            }
                            stack.getTag().putBoolean("feruchemic_power",true);
                            addItemToPlayer((Player) attacker, stack);
                        }
                    }
                } else if (hasAllomanticPower){
                    if (Math.random()>0.50){
                        if (Math.random()<0.75){
                            targetData.removeAllomanticPower(localMetal);

                            target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 40, 1, true, true, false));

                            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 1, true, true, false));

                        }
                        stack.getTag().putBoolean("allomantic_power",true);
                        addItemToPlayer((Player) attacker, stack);
                    }
                } else if (hasFeruchemicPower){
                    if (Math.random()>0.50){
                        if (Math.random()<0.75) {


                            targetData.removeFeruchemicPower(localMetal);

                            target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 40, 1, true, true, false));

                            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 1, true, true, false));

                        }
                        stack.getTag().putBoolean("feruchemic_power",true);
                        addItemToPlayer((Player) attacker, stack);
                    }
                }
                ModNetwork.sync(targetData,(Player) target);
            });
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    private void doEffects(Random rng, Level world, BlockPos pos) {
        LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, world);

        lightning.setVisualOnly(true);
        lightning.moveTo(new Vec3(pos.getX(), pos.getY(), pos.getZ()));

        world.addFreshEntity(lightning);
    }


    public void addItemToPlayer(Player attacker,ItemStack stack) {
        ItemStack itemStack = stack.copy();
        if (attacker.getInventory().getFreeSlot() == -1) {
            attacker.drop(itemStack, true, true);
        } else {
            attacker.addItem(itemStack);
        }
    }
}

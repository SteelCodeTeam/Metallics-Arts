package net.rudahee.metallics_arts.modules.custom_items.metal_spikes;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.TranslatableUtils;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import static net.minecraft.sounds.SoundEvents.ANVIL_FALL;

public class MetalSpike extends SwordItem {

    private static final int ATTACK_DAMAGE = 1;
    private static final float ATTACK_SPEED = -3f;
    private final MetalTagEnum metalSpike;

    public MetalSpike(Item.Properties properties, MetalTagEnum metalTagEnum) {
        super(tier, ATTACK_DAMAGE, ATTACK_SPEED, properties);
        this.metalSpike = metalTagEnum;
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

    public boolean hasPlayerBothPowers(MetalTagEnum metal, IInvestedPlayerData cap) {
        return cap.hasAllomanticPower(metal) && cap.hasFeruchemicPower(metal);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> toolTips, TooltipFlag flag) {
        if  (!stack.getTag().contains("metal_spike")||!stack.getTag().contains("feruchemic_power") || !stack.getTag().contains("allomantic_power"))  {
            stack.setTag(generateTags(stack));
        }
        if (stack.hasTag()){
            if (stack.getTag().getBoolean("feruchemic_power")) {
                toolTips.add(TranslatableUtils.generateComponent("metallics_arts.spike_feruchemic_power"));
            }
            if (stack.getTag().getBoolean("allomantic_power")) {
                toolTips.add(TranslatableUtils.generateComponent("metallics_arts.spike_allomantic_power"));
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

        if  (!stack.getTag().contains("metal_spike") || !stack.getTag().contains("feruchemic_power") || !stack.getTag().contains("allomantic_power"))  {
            stack.setTag(generateTags(stack));
        }
        if ((target instanceof Player) && (attacker instanceof Player)){

            target.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(targetData -> {

                boolean hasAllomanticPower = targetData.hasAllomanticPower(MetalTagEnum.getMetal(stack.getTag().getInt("metal_spike")));
                boolean hasFeruchemicPower = targetData.hasFeruchemicPower(MetalTagEnum.getMetal(stack.getTag().getInt("metal_spike")));

                boolean couldStealPower = Math.random()>0.50;
                boolean couldRemovePower = Math.random()<0.75;
                boolean isAllomantic = Math.random()>0.50;

                MetalTagEnum localMetal = MetalTagEnum.getMetal(stack.getTag().getInt("metal_spike"));

                Random rng = new Random();
                Level world = target.level;
                BlockPos pos = new BlockPos(target.position());

                //DAR PODER
                if (stack.getTag().getBoolean("allomantic_power")){
                    if (!targetData.hasAllomanticPower(localMetal)){
                        targetData.addAllomanticPower(localMetal);
                        doEffects( world, pos);
                    }
                } else if (stack.getTag().getBoolean("feruchemic_power")){
                    if (!targetData.hasFeruchemicPower(localMetal)){
                        targetData.addFeruchemicPower(localMetal);
                        doEffects(world, pos);
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
                        if (Math.random()>0.50) {
                            if (Math.random()<0.75) {
                                targetData.removeFeruchemicPower(localMetal);

                                target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 40, 1, true, true, false));

                                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 1, true, true, false));

                            }
                            stack.getTag().putBoolean("feruchemic_power",true);
                            addItemToPlayer((Player) attacker, stack);
                        }
                    }
                } else if (hasAllomanticPower) {
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
        } else if ((target instanceof Witch) && (attacker instanceof Player)) {
            if (!stack.getTag().getBoolean("allomantic_power") && !stack.getTag().getBoolean("feruchemic_power")) {
                if (Math.random()<0.1){
                    stack.getTag().putBoolean("allomantic_power",true);
                    addItemToPlayer((Player) attacker, stack);
                    target.kill();
                }
            }
        } else if ((target instanceof Evoker) && (attacker instanceof Player)) {
            if (!stack.getTag().getBoolean("allomantic_power") && !stack.getTag().getBoolean("feruchemic_power")) {
                if (Math.random()<0.1){
                    target.kill();
                    stack.getTag().putBoolean("feruchemic_power",true);
                    addItemToPlayer((Player) attacker, stack);
                }
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    private void doEffects(Level world, BlockPos pos) {
        LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, world);

        lightning.setVisualOnly(true);
        lightning.moveTo(new Vec3(pos.getX(), pos.getY(), pos.getZ()));

        world.addFreshEntity(lightning);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!itemStack.getTag().contains("metal_spike") || !itemStack.getTag().contains("feruchemic_power") || !itemStack.getTag().contains("allomantic_power"))  {
            return new InteractionResultHolder<>(InteractionResult.FAIL, itemStack);
        } else if (itemStack.getTag().getBoolean("allomantic_power") || itemStack.getTag().getBoolean("feruchemic_power")) {
            player.startUsingItem(hand);
            try {
                IInvestedPlayerData capabilities = CapabilityUtils.getCapability(player);
                if (itemStack.getTag().getBoolean("allomantic_power")) {
                    capabilities.addAllomanticPower(this.metalSpike);
                } else if (!itemStack.getTag().getBoolean("feruchemic_power")) {
                    capabilities.addFeruchemicPower(this.metalSpike);
                }
            } catch (PlayerException ex) {
                ex.printResumeLog();
            }
            doEffects(level,player.getOnPos());
            player.getInventory().removeItem(itemStack);
            return new InteractionResultHolder<>(InteractionResult.CONSUME, itemStack);
        }
        return new InteractionResultHolder<>(InteractionResult.FAIL, itemStack);
    }

    @Override
    public void releaseUsing(ItemStack p_41412_, Level p_41413_, LivingEntity p_41414_, int p_41415_) {
        super.releaseUsing(p_41412_, p_41413_, p_41414_, p_41415_);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BOW;
    }

    public void addItemToPlayer(Player attacker, ItemStack stack) {
        ItemStack itemStack = stack.copy();
        if (attacker.getInventory().getFreeSlot() == -1) {
            attacker.drop(itemStack, true, true);
        } else {
            attacker.addItem(itemStack);
        }
    }
}

package net.rudahee.metallics_arts.modules.custom_items.metal_spikes;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
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
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * Class that defines new custom SwordItem, This class implements the specific functionality of spikes,
 * as well as its own Tier, and base stats. This class is instantiated once for each spike you want to add to the game.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see SwordItem
 */
public class MetalSpike extends SwordItem {

    private static final int ATTACK_DAMAGE = 1;

    private static final float ATTACK_SPEED = -3f;

    private final MetalTagEnum metalSpike;


    // TODO: Its mandatory do it in a separate class. like a Custom Tier.
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
        public @NotNull Ingredient getRepairIngredient() {
            return Ingredient.of(Blocks.AIR);
        }
    };

    /**
     * Default constructor, metalTagEnum param going to decide what kind of spike it's going to be.
     *
     * @param properties of the items.
     * @param metalTagEnum of the spike, will be decided what metal is going to steal.
     */
    public MetalSpike(Item.Properties properties, MetalTagEnum metalTagEnum) {
        super(tier, ATTACK_DAMAGE, ATTACK_SPEED, properties);
        this.metalSpike = metalTagEnum;
    }

    /**
     * Method to define if the spike can foil or not. The spike only foil if it's charged.
     *
     * @param stack specific item to foil.
     *
     * @return boolean
     */
    @Override
    public boolean isFoil(ItemStack stack) {
        return stack.getTag().getBoolean("feruchemic_power") || stack.getTag().getBoolean("allomantic_power");
    }

    /**
     * Method to define if the player has both powers of the given metals.
     *
     * @param metal to check.
     * @param capability of the player to check.
     *
     * @return boolean
     */
    public boolean hasPlayerBothPowers(MetalTagEnum metal, IInvestedPlayerData capability) {
        return capability.hasAllomanticPower(metal) && capability.hasFeruchemicPower(metal);
    }

    /**
     * This method describes the behavior that will occur when you mouse over the item.
     * By default, it will show if it has any of the two powers or not.
     *
     * @param stack specific item to check
     * @param level for the player.
     * @param toolTips to show.
     * @param flag to show.
     */
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, @NotNull List<Component> toolTips, @NotNull TooltipFlag flag) {
        if  (!stack.getTag().contains("metal_spike") || !stack.getTag().contains("feruchemic_power") || !stack.getTag().contains("allomantic_power"))  {
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
        super.appendHoverText(stack, level, toolTips, flag);
    }

    /**
     * This method generate tags for a specific itemStack.
     *
     * @param stack specific item to check
     *
     * @return CompoundTag
     */
    public CompoundTag generateTags(ItemStack stack) {
        stack.getTag().putInt("metal_spike",this.metalSpike.getIndex());
        stack.getTag().putBoolean("feruchemic_power",false);
        stack.getTag().putBoolean("allomantic_power",false);
        return stack.getTag();
    }

    /**
     * This method is somewhat complex to understand, this method in short, what it does is check if the nail has tags or not.
     * If it has no tags, then we are charging the nail, and therefore we check the tags of the player who takes the hit to steal
     * one of the powers. If instead the nail has tags, then apply the powers to the person who receives the tag.
     *
     * @param stack specific item to check tags.
     * @param target player receive hit.
     * @param source player that do a hit to target
     *
     * @return boolean
     */
    @Override
    public boolean hurtEnemy(ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity source) {

        if  (!stack.getTag().contains("metal_spike") || !stack.getTag().contains("feruchemic_power") || !stack.getTag().contains("allomantic_power"))  {
            stack.setTag(generateTags(stack));
        }
        if ((target instanceof Player) && (source instanceof Player)){

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

                } else if (hasPlayerBothPowers(localMetal, targetData)) {
                    if (isAllomantic) {
                        if (Math.random()>0.50){
                            if (Math.random()<0.75){
                                targetData.removeAllomanticPower(localMetal);

                                target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 40, 1, true, true, false));

                                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 1, true, true, false));

                            }
                            stack.getTag().putBoolean("allomantic_power",true);
                            addItemToPlayer((Player) source, stack);
                        }
                    } else {
                        if (Math.random()>0.50) {
                            if (Math.random()<0.75) {
                                targetData.removeFeruchemicPower(localMetal);

                                target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 40, 1, true, true, false));

                                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 1, true, true, false));

                            }
                            stack.getTag().putBoolean("feruchemic_power",true);
                            addItemToPlayer((Player) source, stack);
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
                        addItemToPlayer((Player) source, stack);
                    }
                } else if (hasFeruchemicPower){
                    if (Math.random()>0.50){
                        if (Math.random()<0.75) {
                            targetData.removeFeruchemicPower(localMetal);
                            target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 40, 1, true, true, false));
                            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 1, true, true, false));
                        }
                        stack.getTag().putBoolean("feruchemic_power",true);
                        addItemToPlayer((Player) source, stack);
                    }
                }
                ModNetwork.sync(targetData,(Player) target);
            });
        } else if ((target instanceof Witch) && (source instanceof Player)) {
            if (!stack.getTag().getBoolean("allomantic_power") && !stack.getTag().getBoolean("feruchemic_power")) {
                if (Math.random()<0.1){
                    stack.getTag().putBoolean("allomantic_power",true);
                    addItemToPlayer((Player) source, stack);
                    target.kill();
                }
            }
        } else if ((target instanceof Evoker) && (source instanceof Player)) {
            if (!stack.getTag().getBoolean("allomantic_power") && !stack.getTag().getBoolean("feruchemic_power")) {
                if (Math.random()<0.1){
                    target.kill();
                    stack.getTag().putBoolean("feruchemic_power",true);
                    addItemToPlayer((Player) source, stack);
                }
            }
        }
        return super.hurtEnemy(stack, target, source);
    }

    /**
     * This auxiliary method give effects to players.
     *
     * @param level of the player.
     * @param pos of player.
     */
    private void doEffects(Level level, BlockPos pos) {
        LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, level);

        lightning.setVisualOnly(true);
        lightning.moveTo(new Vec3(pos.getX(), pos.getY(), pos.getZ()));

        level.addFreshEntity(lightning);
    }

    /**
     * This method define the behaviour when spike is used.
     *
     * @param level of the player when use it.
     * @param player that use the spike.
     * @param hand that have the spike.
     *
     * @return InteractionResultHolder<ItemStack>
     */
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
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

    /**
     * This method define the behaviour when using is finished. but we don't do anything special, only call super().
     *
     * @param stack specific item.
     * @param level of the player.
     * @param livingEntity tht use the spike.
     * @param status of the using.
     */
    @Override
    public void releaseUsing(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity livingEntity, int status) {
        super.releaseUsing(stack, level, livingEntity, status);
    }

    /**
     * This method define the animation when the player use the spike. Always be BOW animation.
     *
     * @param stack of the animation.
     *
     * @return UseAnim
     */
    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.BOW;
    }

    /**
     * Add an item to the player if the power is given.
     *
     * @param attacker that receive the item.
     * @param stack item to receive.
     */
    public void addItemToPlayer(Player attacker, ItemStack stack) {
        ItemStack itemStack = stack.copy();
        if (attacker.getInventory().getFreeSlot() == -1) {
            attacker.drop(itemStack, true, true);
        } else {
            attacker.addItem(itemStack);
        }
    }
}

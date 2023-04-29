package net.rudahee.metallics_arts.modules.custom_items.vials;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.vials.large_vial.LargeVial;
import net.rudahee.metallics_arts.modules.custom_items.vials.small_vial.SmallVial;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Abstract class that defines new custom Items, This class implements the specific functionality of vials,
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see Item
 * @see LargeVial
 * @see SmallVial
 */
public abstract class Vial extends Item {

    private final int maxNuggets;

    /**
     * Default constructor, its important default the maximum quantity of nuggets of vials,
     *
     * @param properties of the item.
     * @param maxNuggets can be contained in vial.
     */
    public Vial(Properties properties, int maxNuggets) {
        super(properties);
        this.maxNuggets = maxNuggets;

    }

    /**
     * This method describes the behavior that will occur when you mouse over the item.
     *
     * @param stack specific item to check
     * @param level for the player.
     * @param toolTips to show.
     * @param flag to show.
     */
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, @NotNull List<Component> toolTips, @NotNull TooltipFlag flag) {
        if (!stack.hasTag()) {
            stack.setTag(addVialTags());
        }
        if (Screen.hasShiftDown()) {
            for (MetalTagEnum metal : MetalTagEnum.values()) {
                if (stack.getTag().getInt(metal.getGemNameLower()) > 0) {
                    toolTips.add(
                            Component.translatable(" * ")
                                    .append(
                                            Component.translatable("metallics_arts.metal_translate."
                                                    +metal.getNameLower()))
                                    .append(": "
                                            +(stack.getTag().getInt(metal.getGemNameLower())/(metal.getMaxAllomanticTicksStorage()/10)
                                            +"/"+ this.maxNuggets)));
                }
            }
        } else {
            if (hasAnyReserve(stack)) {
                toolTips.add(Component.translatable(" "));
                toolTips.add(Component.translatable("metallics_arts.metal_mind_translate.shift_info").withStyle(ChatFormatting.BLUE));
            }
        }
        super.appendHoverText(stack, level, toolTips, flag);
    }

    /**
     * Auxiliary method for check if it has all tags of the metals.
     *
     * @param tag of the item.
     *
     * @return boolean
     */
    private static boolean hasAllTags(CompoundTag tag) {
        boolean value = true;
        for (MetalTagEnum metal: MetalTagEnum.values()) {
            if (!tag.contains(metal.getNameLower())) {
                value = false;
                break;
            }
        }
        return value;
    }

    /**
     * Auxiliary method to add tags in a vial, but without reserve.
     *
     * @return CompoundTag
     */
    private static CompoundTag addVialTags() {
        CompoundTag nbt = new CompoundTag();
        for (MetalTagEnum metal : MetalTagEnum.values()){
            nbt.putInt(metal.getNameLower(),0);
        }
        return nbt;
    }

    /**
     * Auxiliary method to add tags in a vial, but with complete reserve.
     *
     * @return CompoundTag
     */
    public static CompoundTag addFullReserveVialTags() {
        CompoundTag nbt = new CompoundTag();
        for (MetalTagEnum metal : MetalTagEnum.values()){
            nbt.putInt(metal.getNameLower(),metal.getMaxAllomanticTicksStorage());
        }
        return nbt;
    }

    /**
     * This method define the behaviour when using is finished. but we don't do anything special, only call super().
     *
     * @param stack specific item.
     * @param level of the player.
     * @param livingEntity tht use the vial.
     * @param status of the using.
     */
    @Override
    public void releaseUsing(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity livingEntity, int status) {
        super.releaseUsing(stack, level, livingEntity, status);
    }

    /**
     * Auxiliary method for check if it has any tags of the metals.
     *
     * @param stack of the item.
     *
     * @return boolean
     */
    public boolean hasAnyReserve(ItemStack stack) {
        boolean have = false;
        if (stack.hasTag()) {
            for (MetalTagEnum metal : MetalTagEnum.values()) {
                if (stack.getTag().contains(metal.getNameLower()) && stack.getTag().getInt(metal.getNameLower()) > 0) {
                    have = true;
                    break;
                }
            }
        }
        return have;
    }

    /**
     * This method define the behaviour when vial is used.
     *
     * @param level of the player when use it.
     * @param player that use the vial.
     * @param hand that have the vial.
     *
     * @return InteractionResultHolder<ItemStack>
     */
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemStackIn = player.getItemInHand(hand);
        if (!hasAnyReserve(itemStackIn)) {
            return new InteractionResultHolder<>(InteractionResult.FAIL, itemStackIn);
        }
        player.startUsingItem(hand);
        InteractionResultHolder<ItemStack> res = player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).map(data -> {
            //If all the ones being filled are full, don't allow
            if (itemStackIn.hasTag()) {
                for (MetalTagEnum metal : MetalTagEnum.values()) {
                    if (itemStackIn.getTag().contains(metal.getNameLower()) && itemStackIn.getTag().getInt(metal.getNameLower())>0) {
                        player.startUsingItem(hand);
                        return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemStackIn);
                    }
                }
            }
            return new InteractionResultHolder<>(InteractionResult.FAIL, itemStackIn);
        }).orElse(new InteractionResultHolder<>(InteractionResult.FAIL, itemStackIn));
        return res;
    }

    /**
     * This method define the behaviour when using is finished. Add metals to allomantic reserve for a player and return empty vial
     *
     * @param itemStack specific item.
     * @param level of the player.
     * @param livingEntity tht use the spike.
     *
     * @return ItemStack
     */
    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack itemStack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
        if (!itemStack.hasTag()) {
            return itemStack;
        }
        livingEntity.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data -> {
            for (MetalTagEnum metal : MetalTagEnum.values()) {
                if (itemStack.getTag().contains(metal.getNameLower()) && itemStack.getTag().getInt(metal.getNameLower())>0) {
                    data.setAllomanticMetalsAmount(metal,itemStack.getTag().getInt(metal.getNameLower()) + data.getAllomanticAmount(metal));
                }
            }
        });

        if (!((Player) (livingEntity)).getAbilities().instabuild) {
            itemStack.shrink(1);
            ItemStack item = null;
            if(this.maxNuggets==5){
                item = new ItemStack(ModItemsRegister.SMALL_VIAL.get());
            }else {
                item = new ItemStack(ModItemsRegister.LARGE_VIAL.get());
            }

            CompoundTag data = new CompoundTag();
            for (MetalTagEnum metal : MetalTagEnum.values()){
                data.putInt(metal.getNameLower(),0);
            }
            item.setTag(data);

            if (!((Player) livingEntity).getInventory().add(item)) {
                if(this.maxNuggets==5){
                    level.addFreshEntity(new ItemEntity(level, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), new ItemStack(ModItemsRegister.SMALL_VIAL.get(), 1)));
                }else {
                    level.addFreshEntity(new ItemEntity(level, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), new ItemStack(ModItemsRegister.LARGE_VIAL.get(), 1)));
                }
            }
        }
        return itemStack;
    }


    /**
     * This method return the time needed to finish the using.
     *
     * @param itemStack specific item.
     *
     * @return int
     */
    @Override
    public int getUseDuration(@NotNull ItemStack itemStack) {
        return 32;
    }

    /**
     * This method return if the item can be enchanted.
     *
     * @param itemStack specific item.
     *
     * @return boolean
     */
    @Override
    public boolean isEnchantable(@NotNull ItemStack itemStack) {
        return false;
    }

    /**
     * This method return the animation to show.
     *
     * @param itemStack specific item.
     *
     * @return UseAnim
     */
    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack itemStack) {
        return UseAnim.DRINK;
    }

    /**
     * This method specify if it's "eatable".
     *
     * @return boolean
     */
    @Override
    public boolean isEdible() {
        return true;
    }

}



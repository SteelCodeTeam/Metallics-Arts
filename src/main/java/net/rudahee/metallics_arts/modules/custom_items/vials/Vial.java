package net.rudahee.metallics_arts.modules.custom_items.vials;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.utils.TranslatableUtils;

import javax.annotation.Nullable;
import java.util.List;


public abstract class Vial extends Item {
    private final int maxNuggets;
    public Vial(Properties properties,int maxNuggets) {
        super(properties);
        this.maxNuggets = maxNuggets;

    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> toolTips, TooltipFlag flagIn) {
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
            if (haveAnyReserve(stack)) {
                toolTips.add(Component.translatable(" "));
                toolTips.add(Component.translatable("metallics_arts.mental_mind_translate.shift_info").withStyle(ChatFormatting.BLUE));
            }
        }
        super.appendHoverText(stack, world, toolTips, flagIn);
    }
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
    private static CompoundTag addVialTags() {
        CompoundTag nbt = new CompoundTag();
        for (MetalTagEnum metal : MetalTagEnum.values()){
            nbt.putInt(metal.getNameLower(),0);
        }
        return nbt;
    }

    public static CompoundTag addFullReserveVialTags() {
        CompoundTag nbt = new CompoundTag();
        for (MetalTagEnum metal : MetalTagEnum.values()){
            nbt.putInt(metal.getNameLower(),metal.getMaxAllomanticTicksStorage());
        }
        return nbt;
    }

    @Override
    public void releaseUsing(ItemStack itemStack, Level world, LivingEntity livingEntity, int number) {
        super.releaseUsing(itemStack, world, livingEntity, number);
    }

    public boolean haveAnyReserve (ItemStack itemStack) {
        boolean have = false;
        if (itemStack.hasTag()) {
            for (MetalTagEnum metal : MetalTagEnum.values()) {
                if (itemStack.getTag().contains(metal.getNameLower()) && itemStack.getTag().getInt(metal.getNameLower())>0) {
                    have = true;
                    break;
                }
            }
        }
        return have;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemStackIn = player.getItemInHand(hand);
        if (!haveAnyReserve(itemStackIn)) {
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

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level world, LivingEntity livingEntity) {
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
                    world.addFreshEntity(new ItemEntity(world, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), new ItemStack(ModItemsRegister.SMALL_VIAL.get(), 1)));
                }else {
                    world.addFreshEntity(new ItemEntity(world, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), new ItemStack(ModItemsRegister.LARGE_VIAL.get(), 1)));
                }
            }
        }
        return itemStack;
    }



    @Override
    public int getUseDuration(ItemStack itemStack) {
        return 32;
    }

    @Override
    public boolean isEnchantable(ItemStack itemStack) {
        return false;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.DRINK;
    }

    @Override
    public boolean isEdible() {
        return true;
    }


    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {

        ItemStack resultItem = null;

        if (group == MetallicsArts.MA_TAB){
            if(this.maxNuggets==5){
                resultItem = new ItemStack(ModItemsRegister.SMALL_VIAL.get(),1);
            }else if (this.maxNuggets==10) {
                resultItem = new ItemStack(ModItemsRegister.LARGE_VIAL.get(),1);
            }
            CompoundTag nbt = new CompoundTag();
            for (MetalTagEnum mt : MetalTagEnum.values()) {
                nbt.putInt(mt.getGemNameLower(), 0);
            }
            nbt.putInt("CustomModelData", 1);
            resultItem.setTag(nbt);
            items.add(resultItem);
        }
    }

}



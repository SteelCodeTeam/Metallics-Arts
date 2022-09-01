package net.rudahee.metallics_arts.modules.items.vials;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IEntityReader;
import net.minecraft.world.World;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.registries.ModItemGroup;
import net.rudahee.metallics_arts.setup.registries.ModItems;

import javax.annotation.Nullable;
import java.util.List;


public abstract class Vial extends Item {

    private int maxNuggets;

    public Vial(Properties properties,int maxNuggets) {
        super(properties);
        this.maxNuggets = maxNuggets;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> toolTips, ITooltipFlag flagIn) {

        if(!stack.hasTag()){
            stack.setTag(addVialTags());
        }
        if (Screen.hasControlDown()){
            for (MetalsNBTData metal : MetalsNBTData.values()){
                if(stack.getTag().getInt(metal.getGemNameLower())>0){
                    toolTips.add(new StringTextComponent(metal.getNameLower()+": "+stack.getTag().getInt(metal.getGemNameLower())));
                }
            }
        }
        super.appendHoverText(stack, world, toolTips, flagIn);
    }

    private static CompoundNBT addVialTags() {
        CompoundNBT nbt = new CompoundNBT();
        for (MetalsNBTData metal : MetalsNBTData.values()){
            nbt.putInt(metal.getNameLower(),0);
        }
        return nbt;
    }

    public static CompoundNBT addFullReserveVialTags() {
        CompoundNBT nbt = new CompoundNBT();
        for (MetalsNBTData metal : MetalsNBTData.values()){
            nbt.putInt(metal.getNameLower(), metal.getMaxAllomanticTicksStorage());
        }
        return nbt;
    }

    @Override
    public void releaseUsing(ItemStack itemStack, World world, LivingEntity livingEntity, int number) {
        super.releaseUsing(itemStack, world, livingEntity, number);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStackIn = player.getItemInHand(hand);
        ActionResult<ItemStack> res = player.getCapability(InvestedCapability.PLAYER_CAP).map(data -> {
            //If all the ones being filled are full, don't allow
            if (itemStackIn.hasTag()) {
                for (MetalsNBTData metal : MetalsNBTData.values()) {
                    if (itemStackIn.getTag().contains(metal.getNameLower()) && itemStackIn.getTag().getInt(metal.getNameLower())>0) {
                        player.startUsingItem(hand);
                        return new ActionResult<>(ActionResultType.SUCCESS, itemStackIn);
                    }
                }
            }
            return new ActionResult<>(ActionResultType.FAIL, itemStackIn);
        }).orElse(new ActionResult<>(ActionResultType.FAIL, itemStackIn));
        return res;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, World world, LivingEntity livingEntity) {
        if (!itemStack.hasTag()) {
            return itemStack;
        }
        livingEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {
            for (MetalsNBTData metal : MetalsNBTData.values()) {
                if (itemStack.getTag().contains(metal.getNameLower()) && itemStack.getTag().getInt(metal.getNameLower())>0) {
                    data.setAllomanticMetalsAmount(metal,itemStack.getTag().getInt(metal.getNameLower()) + data.getAllomanticAmount(metal));
                }
            }
        });

        if (!((PlayerEntity) (livingEntity)).abilities.instabuild) {
            itemStack.shrink(1);
            ItemStack item = null;
            if(this.maxNuggets==5){
                item = new ItemStack(ModItems.SMALL_VIAL.get());
            }else {
                item = new ItemStack(ModItems.LARGE_VIAL.get());
            }

            CompoundNBT data = new CompoundNBT();
            for (MetalsNBTData metal : MetalsNBTData.values()){
                data.putInt(metal.getNameLower(),0);
            }
            item.setTag(data);

            if (!((PlayerEntity) livingEntity).inventory.add(item)) {
                if(this.maxNuggets==5){
                    world.addFreshEntity(new ItemEntity(world, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), new ItemStack(ModItems.SMALL_VIAL.get(), 1)));
                }else {
                    world.addFreshEntity(new ItemEntity(world, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), new ItemStack(ModItems.LARGE_VIAL.get(), 1)));
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
    public UseAction getUseAnimation(ItemStack itemStack) {
        return UseAction.DRINK;
    }

    @Override
    public boolean isEdible() {
        return true;
    }


    @Override
    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {

        ItemStack resultItem = null;

        if (group == ModItemGroup.METALLIC_ARTS_TAG){
            if(this.maxNuggets==5){
                resultItem = new ItemStack(ModItems.SMALL_VIAL.get(),1);
            }else if (this.maxNuggets==10) {
                resultItem = new ItemStack(ModItems.LARGE_VIAL.get(),1);
            }
            CompoundNBT nbt = new CompoundNBT();
            for (MetalsNBTData mt : MetalsNBTData.values()) {
                nbt.putInt(mt.getGemNameLower(), 0);
            }
            nbt.putInt("CustomModelData", 1);
            resultItem.setTag(nbt);
            items.add(resultItem);
        }
    }

}



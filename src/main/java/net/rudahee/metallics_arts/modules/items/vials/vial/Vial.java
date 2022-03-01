package net.rudahee.metallics_arts.modules.items.vials.vial;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.registries.ModItemGroup;
import net.rudahee.metallics_arts.setup.registries.ModItems;


public class Vial extends Item {


    CompoundNBT compoundNBT = new CompoundNBT();

    public Vial(Properties properties) {
        super(properties);
        for (MetalsNBTData metal : MetalsNBTData.values()){
            this.compoundNBT.putInt(metal.getNameLower(),0);
        }
    }

    @Override
    public void releaseUsing(ItemStack p_77615_1_, World p_77615_2_, LivingEntity p_77615_3_, int p_77615_4_) {
        super.releaseUsing(p_77615_1_, p_77615_2_, p_77615_3_, p_77615_4_);
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
                    data.setAllomanticMetalsAmount(metal,itemStack.getTag().getInt(metal.getNameLower()));
                }
            }
        });

        if (!((PlayerEntity) (livingEntity)).abilities.instabuild) {
            itemStack.shrink(1);

            ItemStack item = new ItemStack(ModItems.VIAL.get());
            CompoundNBT data = new CompoundNBT();
            for (MetalsNBTData metal : MetalsNBTData.values()){
                data.putInt(metal.getNameLower(),0);
            }
            item.setTag(data);

            if (!((PlayerEntity) livingEntity).inventory.add(item)) {
                world.addFreshEntity(new ItemEntity(world, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), new ItemStack(ModItems.VIAL.get(), 1)));
            }
        }
        return itemStack;
    }

    @Override
    public int getUseDuration(ItemStack p_77626_1_) {
        return 32;
    }

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return false;
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public boolean isEdible() {
        return true;
    }

    @Override
    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
        ItemStack resultItem = new ItemStack(ModItems.VIAL.get(),1);
        CompoundNBT nbt = new CompoundNBT();
        for (MetalsNBTData mt : MetalsNBTData.values()) {
            nbt.putInt(mt.getGemNameLower(), 0);
        }
        resultItem.setTag(nbt);
        items.add(resultItem);
    }

    public CompoundNBT getCompoundNBT() {
        return compoundNBT;
    }

}



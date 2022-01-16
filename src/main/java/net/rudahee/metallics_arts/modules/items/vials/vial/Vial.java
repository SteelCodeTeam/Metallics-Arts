package net.rudahee.metallics_arts.modules.items.vials.vial;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.player.*;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.registries.ModItemGroup;
import net.rudahee.metallics_arts.setup.registries.ModItems;

import java.util.*;


public class Vial extends Item {


    CompoundNBT compoundNBT = new CompoundNBT();

    public Vial(Properties properties) {
        super(properties);
    }

    @Override
    public void releaseUsing(ItemStack p_77615_1_, World p_77615_2_, LivingEntity p_77615_3_, int p_77615_4_) {
        super.releaseUsing(p_77615_1_, p_77615_2_, p_77615_3_, p_77615_4_);
    }

    static IInvestedPlayerData finalData = null;

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        ItemStack startingVial = player.getItemInHand(hand);
        CompoundNBT vialNbts = startingVial.getTag();

        InvestedPlayerDataProvider provider = new InvestedPlayerDataProvider();

        //DefaultInvestedPlayerData capability =  provider.getCapability(InvestedPlayerCapability.PLAYER_CAP).resolve().get();



        if (!startingVial.isEmpty() && startingVial != null) {

            player.startUsingItem(hand);
            /*
            for(MetalsNBTData allomanticNbts : capability.getAllomanticPowers()) {
                if (vialNbts.getInt(allomanticNbts.getNameLower()) > 0
                        && capability.getAllomanticAmount(allomanticNbts) < allomanticNbts.getMaxAllomanticTicksStorage()) {

                    capability.setAllomanticMetalsAmount(allomanticNbts, vialNbts.getInt(allomanticNbts.getNameLower()));

                    return new ActionResult<>(ActionResultType.CONSUME, new ItemStack(ModItems.VIAL.get()));
                }
                return new ActionResult<>(ActionResultType.FAIL, startingVial);
            }

             */
            return new ActionResult<>(ActionResultType.FAIL, startingVial);
        }
        return new ActionResult<>(ActionResultType.FAIL, startingVial);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, World world, LivingEntity livingEntity) {

        if (!world.isClientSide()) {
            // :3
        }
        return super.finishUsingItem(itemStack, world, livingEntity);
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



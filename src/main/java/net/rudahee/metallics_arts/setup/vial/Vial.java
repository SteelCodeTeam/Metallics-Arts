package net.rudahee.metallics_arts.setup.vial;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.MetalsNBTData;

import java.util.*;


public class Vial extends Item {

    //Map<String, CompoundNBT> list = new HashMap<>();

    CompoundNBT compoundNBT = new CompoundNBT();

    public Vial(Properties properties) {
        super(properties);

        Arrays.asList(MetalsNBTData.values()).forEach(m->{
            compoundNBT.putInt(MetallicsArts.MOD_ID + "." + m.getNameLower()+"_reserve",0);
        });
    }

    @Override
    public void releaseUsing(ItemStack p_77615_1_, World p_77615_2_, LivingEntity p_77615_3_, int p_77615_4_) {
        super.releaseUsing(p_77615_1_, p_77615_2_, p_77615_3_, p_77615_4_);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.inventory.add(new ItemStack(Items.STONE)); // remove

        player.startUsingItem(hand);

        System.out.println(MetalsNBTData.values().length); // remove

        return new ActionResult<>(ActionResultType.CONSUME, player.getItemInHand(hand));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, World world, LivingEntity livingEntity) {
        if (!world.isClientSide()) {
            Arrays.asList(MetalsNBTData.values()).forEach(m->{
                // Do logic here!!
                int auxMetalValue = compoundNBT.getInt(MetallicsArts.MOD_ID + "." + m.getNameLower()+"_reserve");
                compoundNBT.putInt(MetallicsArts.MOD_ID + "." + m.getNameLower()+"_reserve",auxMetalValue+1);
                System.out.println(MetallicsArts.MOD_ID + "." + m.getNameLower() + " -- " + compoundNBT.getInt(MetallicsArts.MOD_ID + "." + m.getNameLower() + "_reserve"));
            });
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

}



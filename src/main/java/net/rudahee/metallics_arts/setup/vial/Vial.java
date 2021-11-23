package net.rudahee.metallics_arts.setup.vial;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.rudahee.metallics_arts.setup.enums.MetalsNBTData;

import java.util.*;


public class Vial extends Item {

    //Map<String, CompoundNBT> list = new HashMap<>();

    CompoundNBT compoundNBT = new CompoundNBT();

    public Vial(Properties properties) {
        super(properties);

        Arrays.asList(MetalsNBTData.values()).forEach(m->{
            compoundNBT.putInt(m.getNameLower()+"_reserve",0);
        });
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.inventory.add(new ItemStack(Items.STONE));

        Arrays.asList(MetalsNBTData.values()).forEach(m->{
            compoundNBT.putInt(m.getNameLower()+"_reserve",compoundNBT.getInt(m.getNameLower()+"_reserve")+1);
            System.out.println(m.getNameLower()+"_reserve: "+compoundNBT.getInt(m.getNameLower()+"_reserve"));
        });
/*        Arrays.asList(MetalsNBTData.values()).forEach(m->{
            System.out.println(m.getNameLower()+"_reserve: "+compoundNBT.getInt(m.getNameLower()+"_reserve"));
        });

  */
        return new ActionResult<>(ActionResultType.SUCCESS,player.getItemInHand(hand));
    }

    public void generatedNBT (String name){

    }

    @Override
    public int getUseDuration(ItemStack p_77626_1_) {
        return 100;
    }

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return false;
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.DRINK;
    }

}



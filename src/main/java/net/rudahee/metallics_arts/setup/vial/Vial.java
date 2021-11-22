package net.rudahee.metallics_arts.setup.vial;

import jdk.nashorn.internal.runtime.arrays.IteratorAction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.rudahee.metallics_arts.setup.enums.Metal;

import java.util.Arrays;
import java.util.List;


public class Vial extends Item {

    CompoundNBT nbt;
    public Vial(Properties p_i48487_1_) {
        super(p_i48487_1_);
        nbt = new CompoundNBT();
        nbt.contains("uses",0);
    }



        @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.inventory.add(new ItemStack(Items.STONE));
        nbt.putInt("uses", nbt.getInt("uses")+1);
        System.out.println("\n\n\nUSES:" + nbt.getInt("uses")+"\n\n\n");
        return new ActionResult<>(ActionResultType.SUCCESS,player.getItemInHand(hand));
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



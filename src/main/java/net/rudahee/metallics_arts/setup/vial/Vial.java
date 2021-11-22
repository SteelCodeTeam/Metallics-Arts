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
import net.rudahee.metallics_arts.setup.enums.Gems;
import net.rudahee.metallics_arts.setup.enums.Metal;
import net.rudahee.metallics_arts.setup.enums.MetalsNBTData;

import java.util.*;


public class Vial extends Item {

    Map<String, CompoundNBT> list = new HashMap<>();

    public Vial(Properties properties) {
        super(properties);

        Arrays.asList(MetalsNBTData.values()).forEach(m -> {
            CompoundNBT nbt = new CompoundNBT();
            nbt.contains(m.getNameLower()+"_reserve",0);
            list.put(m.getNameLower(),nbt);
        });
    }



    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.inventory.add(new ItemStack(Items.STONE));
        Arrays.asList(MetalsNBTData.values()).forEach(m ->{
            list.get(m.getNameLower()).putInt
                    (m.getNameLower()+"_reserve",
                            list.get(m.getNameLower()).getInt(m.getNameLower()+"_reserve")+1);
        });
        Arrays.asList(MetalsNBTData.values()).forEach(m -> {
            System.out.println("\n\n"+m.getNameLower()+":" + list.get(m.getNameLower()).getInt(m.getNameLower()+"_reserve"));
        });

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



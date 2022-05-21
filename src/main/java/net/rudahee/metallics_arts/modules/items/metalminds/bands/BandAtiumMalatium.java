package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

import javax.annotation.Nullable;
import java.util.List;

public class BandAtiumMalatium extends BandMindAbstract {
    private CompoundNBT nbt = new CompoundNBT();
    private static int MAX_ATIUM = 100;
    private static int MAX_MALATIUM = 16000;

    public BandAtiumMalatium (Item.Properties properties){
        super(properties, MetalsNBTData.ATIUM,MetalsNBTData.MALATIUM);

        nbt.putInt("feruchemic_atium_reserve", 0);
        nbt.putInt("feruchemic_malatium_reserve", 0);
        nbt.putInt(MetallicsArts.MOD_ID+".max_capacity_atium",this.MAX_ATIUM);
        nbt.putInt(MetallicsArts.MOD_ID+".max.capacity_malatium",this.MAX_MALATIUM);
        nbt.putString(MetallicsArts.MOD_ID + ".user_key", super.unkeyedString);

    }

    private static boolean needUpdate = false;

    /*@Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        super.curioTick(identifier, index, livingEntity, stack);

        if (livingEntity.level instanceof ServerWorld) {
            needUpdate = false;

            if (livingEntity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) livingEntity;

                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {

                    if (data.isDecanting(MetalsNBTData.ATIUM)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_atium_reserve") > 0) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_atium_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_atium_reserve") - 1);
                        } else {
                            data.setDecanting(MetalsNBTData.ATIUM, false);
                        }
                        needUpdate = true;
                    } else if (data.isStoring(MetalsNBTData.ATIUM)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_atium_reserve") <= this.MAX_ATIUM) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_atium_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_atium_reserve") + 1);
                        } else {
                            data.setStoring(MetalsNBTData.ATIUM, false);
                        }
                        needUpdate = true;
                    }

                    if (data.isDecanting(MetalsNBTData.MALATIUM)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_malatium_reserve") > 0) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_malatium_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_malatium_reserve") - 1);
                        } else {
                            data.setDecanting(MetalsNBTData.MALATIUM, false);
                        }
                        needUpdate = true;
                    } else if (data.isStoring(MetalsNBTData.MALATIUM)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_malatium_reserve") <= this.MAX_MALATIUM) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_malatium_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_malatium_reserve") + 1);
                        } else {
                            data.setDecanting(MetalsNBTData.MALATIUM, false);
                        }
                        needUpdate = true;
                    }

                    if (needUpdate) {
                        ModNetwork.sync(data, player);
                    }

                });
            }
        }
    }*/

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> toolTips, ITooltipFlag flagIn) {
        super.appendHoverText(stack, world, toolTips, flagIn);
        if (Screen.hasControlDown()) {
            //toolTips.add(new StringTextComponent("Atium: " + stack.getTagElement(MetallicsArts.MOD_ID+".feruchemic_atium_reserve")));
            toolTips.add(new StringTextComponent("Atium: " + stack.getTag().getInt("feruchemic_atium_reserve")));
            toolTips.add(new StringTextComponent("Malatium: " + stack.getTag().getInt("feruchemic_malatium_reserve")));
        }


    }

    @Override
    public void storing(CompoundNBT nbt, String metal, int qty) {
        nbt.putInt(metal, nbt.getInt(metal) + qty);
    }

    @Override
    public void decanting(CompoundNBT nbt, String metal, int qty) {
        nbt.putInt(metal,nbt.getInt(metal)-qty);
    }

    /*@Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> toolTips, ITooltipFlag flagIn) {

        if (Screen.hasControlDown()) {
            toolTips.add(new StringTextComponent(metals[0].getNameLower() + ": " + stack.getTag().getInt(MetallicsArts.MOD_ID + ".feruchemic_" + metals[0].getNameLower() + "_reserve")));
            toolTips.add(new StringTextComponent(metals[1].getNameLower() + ": " + stack.getTag().getInt(MetallicsArts.MOD_ID + ".feruchemic_" + metals[1].getNameLower() + "_reserve")));
        }
        super.appendHoverText(stack, world, toolTips, flagIn);
    } */
}
package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.data_player.DefaultInvestedPlayerData;
import net.rudahee.metallics_arts.modules.data_player.IDefaultInvestedPlayerData;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModItems;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.List;

public class BandAluminumDuralumin extends BandMindAbstract implements ICurioItem {

    CompoundNBT nbt = new CompoundNBT();
    private static int MAX_ALUMINUM = 200;
    private static int MAX_DURALUMIN = 200;

    public BandAluminumDuralumin (Item.Properties properties){
        super(properties,MetalsNBTData.ALUMINUM,MetalsNBTData.DURALUMIN);
        nbt.putInt("duralumin_feruchemic_reserve",0);
        nbt.putInt("aluminum_feruchemic_reserve",0);
        nbt.putInt("duralumin_feruchemic_max_capacity",MAX_DURALUMIN);
        nbt.putInt("aluminum_feruchemic_max_capacity",MAX_ALUMINUM);
    }

    @Override
    public ActionResultType useOn(ItemUseContext p_195939_1_) {
        return super.useOn(p_195939_1_);
    }

    private static boolean needUpdate = false;

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        CompoundNBT nbtLocal = stack.getTag();
        if (livingEntity.level instanceof ServerWorld) {
            needUpdate = false;
            if (livingEntity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) livingEntity;
                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {
                    if (data.isDecanting(MetalsNBTData.ALUMINUM)) {
                        if (stack.getTag().getInt("aluminum_feruchemic_reserve") > 0) {
                            nbtLocal.putInt("aluminum_feruchemic_reserve",(stack.getTag().getInt("aluminum_feruchemic_reserve")-1));
                            stack.setTag(nbtLocal);
                        } else {
                            data.setDecanting(MetalsNBTData.ALUMINUM, false);
                        }
                        needUpdate = true;
                    } else if (data.isStoring(MetalsNBTData.ALUMINUM)) {
                        if (stack.getTag().getInt("aluminum_feruchemic_reserve") < this.MAX_ALUMINUM) {
                            nbtLocal.putInt("aluminum_feruchemic_reserve",(stack.getTag().getInt("aluminum_feruchemic_reserve")+1));
                            stack.setTag(nbtLocal);
                        } else {
                            data.setStoring(MetalsNBTData.ALUMINUM, false);
                        }
                        needUpdate = true;
                    }
                    if (data.isDecanting(MetalsNBTData.DURALUMIN)) {
                        if (stack.getTag().getInt("duralumin_feruchemic_reserve") > 0) {
                            nbtLocal.putInt("duralumin_feruchemic_reserve",(stack.getTag().getInt("duralumin_feruchemic_reserve")-1));
                            stack.setTag(nbtLocal);
                        } else {
                            data.setDecanting(MetalsNBTData.DURALUMIN, false);
                        }
                        needUpdate = true;
                    } else if (data.isStoring(MetalsNBTData.DURALUMIN)) {
                        if (stack.getTag().getInt("duralumin_feruchemic_reserve") < this.MAX_DURALUMIN) {
                            nbtLocal.putInt("duralumin_feruchemic_reserve",(stack.getTag().getInt("duralumin_feruchemic_reserve")+1));
                            stack.setTag(nbtLocal);
                        } else {
                            data.setStoring(MetalsNBTData.DURALUMIN, false);
                        }
                        needUpdate = true;
                    }
                    if (needUpdate) {
                        ModNetwork.sync(data, player);
                    }
                });
            }
        }
        super.curioTick(identifier, index, livingEntity, stack);
    }


}
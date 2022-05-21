package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
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
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.List;

public class BandAluminumDuralumin extends BandMindAbstract implements ICurioItem {
    private CompoundNBT nbt = new CompoundNBT();
    private static int MAX_ALUMINUM = 100;
    private static int MAX_DURALUMIN = 16000;

    public BandAluminumDuralumin (Item.Properties properties){
        super(properties,MetalsNBTData.ALUMINUM,MetalsNBTData.DURALUMIN);
        this.nbt.putInt("feruchemic_aluminum_reserve",0);
        this.nbt.putInt("feruchemic_duralumin_reserve", 0);
        this.nbt.putString(MetallicsArts.MOD_ID + ".user_key", super.unkeyedString);

    }

    @Override
    public ActionResultType useOn(ItemUseContext p_195939_1_) {
        return super.useOn(p_195939_1_);
    }

    private static boolean needUpdate = false;

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        super.curioTick(identifier, index, livingEntity, stack);

        if (livingEntity.level instanceof ServerWorld) {
            needUpdate = false;

            if (livingEntity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) livingEntity;
                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {
                    if (data.isDecanting(MetalsNBTData.ALUMINUM)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_aluminum_reserve") > 0) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_aluminum_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_aluminum_reserve") - 1);
                        } else {
                            data.setDecanting(MetalsNBTData.ALUMINUM, false);
                        }
                        needUpdate = true;
                    } else if (data.isStoring(MetalsNBTData.ALUMINUM)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_aluminum_reserve") <= this.MAX_ALUMINUM) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_aluminum_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_aluminum_reserve") + 1);
                        } else {
                            data.setStoring(MetalsNBTData.ALUMINUM, false);
                        }
                        needUpdate = true;
                    }

                    if (data.isDecanting(MetalsNBTData.DURALUMIN)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_duralumin_reserve") > 0) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_duralumin_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_duralumin_reserve") - 1);
                        } else {
                            data.setDecanting(MetalsNBTData.DURALUMIN, false);
                        }
                        needUpdate = true;
                    } else if (data.isStoring(MetalsNBTData.DURALUMIN)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_duralumin_reserve") <= this.MAX_DURALUMIN) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_duralumin_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_duralumin_reserve") + 1);
                        } else {
                            data.setDecanting(MetalsNBTData.DURALUMIN, false);
                        }
                        needUpdate = true;
                    }
                    if (needUpdate) {
                        ModNetwork.sync(data, player);
                    }
                });
            }
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
}
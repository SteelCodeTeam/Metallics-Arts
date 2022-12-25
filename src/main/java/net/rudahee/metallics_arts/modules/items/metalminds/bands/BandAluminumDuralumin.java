package net.rudahee.metallics_arts.modules.items.metalminds.bands;


import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.modules.tags_player.InvestedCapability;
import net.rudahee.metallics_arts.data.enums.implementations.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class BandAluminumDuralumin extends BandMindAbstract implements ICurioItem {

    public BandAluminumDuralumin (Item.Properties properties){
        super(properties, MetalsNBTData.ALUMINUM,MetalsNBTData.DURALUMIN,MetalsNBTData.ALUMINUM.getMaxReserveBand(),MetalsNBTData.DURALUMIN.getMaxReserveBand());
    }
    public boolean nicConsumeMet1 = false;

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();

        CompoundTag nbtLocal = stack.getTag();

        if (livingEntity.level instanceof ServerLevel) {
            if (livingEntity instanceof Player) {
                Player player = (Player) livingEntity;
                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {

                    if (data.isDecanting(MetalsNBTData.ALUMINUM) || data.isStoring(MetalsNBTData.ALUMINUM)){
                        if (!this.isEquiped) {
                            this.isEquiped = true;
                        }

                        if (data.isDecanting(MetalsNBTData.ALUMINUM)) {
                            if (nbtLocal.getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") != 1) {
                                nbtLocal.putInt(getMetals(0).getNameLower()+"_feruchemic_reserve",1);
                            }
                        } else {
                            if (nbtLocal.getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") != 2) {
                                nbtLocal.putInt(getMetals(0).getNameLower()+"_feruchemic_reserve",2);
                            }

                        }
                        stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                    } else {
                        if (nbtLocal.getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") != 3) {
                            nbtLocal.putInt(getMetals(0).getNameLower()+"_feruchemic_reserve",3);
                        }

                    }

                    ///////DURALUMINUM/////
                    if (data.isDecanting(getMetals(1))) {
                        if (stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")>0) {
                            if (data.isDecanting(MetalsNBTData.NICROSIL)){
                                if (!nicConsumeMet1){
                                    nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")-1));
                                    stack.setTag(nbtLocal);
                                }
                                nicConsumeMet1 = !nicConsumeMet1;
                            } else {
                                //las dos lineas de abajo van sin el nicrosil
                                nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")-1));
                                stack.setTag(nbtLocal);
                            }
                        } else {
                            stack.getTag().putString("key",localChangeOwner(player,stack.getTag(),false));
                            data.setDecanting(getMetals(1),false);
                        }

                    } else if (data.isStoring(getMetals(1))) {
                        if (stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") < stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity")) {

                            if (data.isStoring(MetalsNBTData.NICROSIL)) {
                                if (!nicConsumeMet1){
                                    stack.getTag().putString("key",localChangeOwner(player,stack.getTag(),true));
                                    nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")+1));
                                    stack.setTag(nbtLocal);
                                }
                                nicConsumeMet1 = !nicConsumeMet1;

                            } else {
                                stack.getTag().putString("key",localChangeOwner(player,stack.getTag(),true));
                                nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")+1));
                                stack.setTag(nbtLocal);
                            }
                        } else {
                            data.setStoring(getMetals(1),false);
                        }
                    }
                    ModNetwork.sync(data, player);
                });
            }
        }
        super.curioTick(slotContext, stack);
    }

    private String dato;
    public String localChangeOwner(Player player, CompoundTag compoundNBT,boolean iStoreMetal) {

        boolean isSecondReserveZero = compoundNBT.getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") == 0;
        dato = compoundNBT.getString("key");

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {
            if (isSecondReserveZero && !data.isStoring(MetalsNBTData.ALUMINUM) &&
                    !data.isDecanting(MetalsNBTData.ALUMINUM) && iStoreMetal){
                dato = player.getStringUUID();
            } else if (isSecondReserveZero && !data.isStoring(MetalsNBTData.ALUMINUM) &&
                    !data.isDecanting(MetalsNBTData.ALUMINUM) && !iStoreMetal){
                dato = unkeyedString;
            }
            else if (data.isStoring(MetalsNBTData.ALUMINUM)) {
                dato = unkeyedString;
            } else if (data.isDecanting(MetalsNBTData.ALUMINUM)){
                dato = player.getStringUUID();
            }
        });
        return dato;
    }

    private boolean isEquiped = false;

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> toolTips, TooltipFlag flagIn) {
        if (stack.hasTag()) {
            if (isEquiped) {
                if (stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") == 2) {
                    toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": ").append(Component.translatable("metallics_arts.mental_mind_translate.store_identity")));
                } else if (stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") == 1) {
                    toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": ").append(Component.translatable("metallics_arts.spike_allomantic_power.tapping_identity")));
                } else {
                    toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": ").append(Component.translatable("metallics_arts.mental_mind_translate.off_power")));
                }
            } else {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": ").append(Component.translatable("metallics_arts.mental_mind_translate.off_power")));
            }
            if (!Screen.hasShiftDown()){
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+ stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") / 20 + "s"));
            } else {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+ ((stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity"))+"%"));
            }
            if (world != null) {
                toolTips.add(Component.translatable("metallics_arts.mental_mind.owner").append(": "+ ((stack.getTag().getString("key").equals("Nobody")) ? Component.translatable("metallics_arts.mental_mind.nobody").getString() : (world.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))) == null) ? Component.translatable("metallics_arts.mental_mind.owner_someone") : world.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))).getName().getString())));
            }
            if (!Screen.hasShiftDown()){
                toolTips.add(Component.translatable(" "));
                toolTips.add(Component.translatable("metallics_arts.mental_mind_translate.shift_info").withStyle(ChatFormatting.BLUE));
            }
        }
        super.appendHoverText(stack, world, toolTips, flagIn);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (stack.getItem() != newStack.getItem()) {
            this.isEquiped = false;
        }
        super.onUnequip(slotContext, newStack, stack);
    }
}
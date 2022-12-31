package net.rudahee.metallics_arts.modules.custom_items.metal_minds;


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
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.rings.*;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;


public abstract class RingsMindAbstract extends Item implements ICurioItem {
    private final MetalTagEnum[] metals = new MetalTagEnum[2];
    private final int[] metalsMaxReserve = new int[2];
    public String unkeyedString = "Nobody";

    public RingsMindAbstract(Properties properties, MetalTagEnum metal1, MetalTagEnum metal2, int maxReserve1, int maxReserve2) {
        super(properties);
        metals[0]=metal1;
        metals[1]=metal2;

        metalsMaxReserve[0]=maxReserve1;
        metalsMaxReserve[1]=maxReserve2;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        Player player = (Player) slotContext.getWearer();

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data ->{
            data.setMetalMindEquiped(this.metals[0].getGroup(),true);
            data.setMetalMindEquiped(this.metals[1].getGroup(),true);
            ModNetwork.sync(data, player);
        });
        ICurioItem.super.onEquip(slotContext, prevStack, stack);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (this instanceof RingElectrumGold) {
            return;
        }
        Player player = (Player) slotContext.getWearer();
        if (stack.getItem() != newStack.getItem()) {
            player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data ->{
                data.setMetalMindEquiped(this.metals[0].getGroup(),false);
                data.setMetalMindEquiped(this.metals[1].getGroup(),false);
                data.setStoring(this.metals[0],false);
                data.setStoring(this.metals[1],false);
                data.setDecanting(this.metals[0],false);
                data.setDecanting(this.metals[1],false);
                ModNetwork.sync(data, player);
            });
        }
        ICurioItem.super.onUnequip(slotContext, newStack, stack);
    }
    private IInvestedPlayerData cap = null;

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        ICurioItem.super.canEquip(slotContext, stack);
        if(!stack.hasTag()) {
            stack.setTag(addRingTags());
        }
        Player player = (Player) slotContext.entity();
        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data ->{
            cap = data;
        });
        boolean canEquip = false;

        if (cap != null) {
            canEquip = !(cap.getMetalMindEquiped(this.metals[0].getGroup()));
        }
        if (canEquip){
            if (!stack.getTag().getString("key").equals(unkeyedString)
                    && !player.getStringUUID().equals(stack.getTag().getString("key"))){
                canEquip = false;
            }
        }
        return canEquip;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> toolTips, TooltipFlag flagIn) {
        if(!stack.hasTag()) {
            stack.setTag(addRingTags());
        }
        if (this instanceof RingLerasiumEttmetal || this instanceof RingAtiumMalatium
                || this instanceof RingCopperBronze || this instanceof RingAluminumDuralumin){
            return;
        }
        if (stack.hasTag()) {
            if (!Screen.hasControlDown()){
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[0].getNameLower()).append(": "+ stack.getTag().getInt(metals[0].getNameLower()+"_feruchemic_reserve") / 20 + "s"));
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[1].getNameLower()).append(": "+ stack.getTag().getInt(metals[1].getNameLower()+"_feruchemic_reserve") / 20 + "s"));
            } else {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[0].getNameLower()).append(": "+ ((stack.getTag().getInt(metals[0].getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(metals[0].getNameLower()+"_feruchemic_max_capacity"))+"%"));
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[1].getNameLower()).append(": "+ ((stack.getTag().getInt(metals[1].getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(metals[1].getNameLower()+"_feruchemic_max_capacity"))+"%"));
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

    private CompoundTag addRingTags() {
        CompoundTag nbt = new CompoundTag();

        nbt.putInt(this.metals[0].getNameLower()+"_feruchemic_reserve",0);
        nbt.putInt(this.metals[1].getNameLower()+"_feruchemic_reserve",0);
        nbt.putInt(this.metals[0].getNameLower()+"_feruchemic_max_capacity",metalsMaxReserve[0]);
        nbt.putInt(this.metals[1].getNameLower()+"_feruchemic_max_capacity",metalsMaxReserve[1]);
        nbt.putString("key",this.unkeyedString);
        return nbt;
    }

    private boolean nicConsumeMet0 = false;
    private boolean nicConsumeMet1 = false;

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();

        if(!stack.hasTag()) {
            stack.setTag(addRingTags());
        }

        if (this instanceof RingZincBrass || this instanceof RingCopperBronze
                || this instanceof RingLerasiumEttmetal ||this instanceof RingAtiumMalatium
                ||this instanceof RingChromiumNicrosil || this instanceof RingAluminumDuralumin || this instanceof RingElectrumGold) {
            return;
        }

        CompoundTag nbtLocal = stack.getTag();

        if (livingEntity.level instanceof ServerLevel) {
            if (livingEntity instanceof Player) {
                Player player = (Player) livingEntity;
                player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data -> {

                    if (data.isDecanting(MetalTagEnum.ALUMINUM)||data.isStoring(MetalTagEnum.ALUMINUM)){
                        stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                    }

                    if (data.isDecanting(this.metals[0])) {
                        if (stack.getTag().getInt(this.metals[0].getNameLower()+"_feruchemic_reserve")>0) {
                            if (data.isDecanting(MetalTagEnum.NICROSIL)){
                                if (!nicConsumeMet0){
                                    nbtLocal.putInt(this.metals[0].getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(this.metals[0].getNameLower()+"_feruchemic_reserve")-1));
                                    stack.setTag(nbtLocal);
                                }
                                nicConsumeMet0 = !nicConsumeMet0;
                            } else {
                                //las dos lineas de abajo van sin el nicrosil
                                nbtLocal.putInt(this.metals[0].getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(this.metals[0].getNameLower()+"_feruchemic_reserve")-1));
                                stack.setTag(nbtLocal);
                            }
                        } else {
                            stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                            data.setDecanting(this.metals[0],false);
                        }
                    } else if (data.isStoring(this.metals[0])) {
                        if (stack.getTag().getInt(this.metals[0].getNameLower()+"_feruchemic_reserve") < stack.getTag().getInt(this.metals[0].getNameLower()+"_feruchemic_max_capacity")) {

                            if (data.isStoring(MetalTagEnum.NICROSIL)) {
                                if (!nicConsumeMet0){
                                    stack.getTag().putString("key",changeOwner(player,stack.getTag(),true));
                                    nbtLocal.putInt(this.metals[0].getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(this.metals[0].getNameLower()+"_feruchemic_reserve")+1));
                                    stack.setTag(nbtLocal);
                                }
                                nicConsumeMet0 = !nicConsumeMet0;

                            } else {
                                //estas 3 lineas ban sin la logica del nocrosil
                                stack.getTag().putString("key",changeOwner(player,stack.getTag(),true));
                                nbtLocal.putInt(this.metals[0].getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(this.metals[0].getNameLower()+"_feruchemic_reserve")+1));
                                stack.setTag(nbtLocal);
                            }


                        } else {
                            data.setStoring(this.metals[0],false);
                        }
                    }

                    if (data.isDecanting(this.metals[1])) {
                        if (stack.getTag().getInt(this.metals[1].getNameLower()+"_feruchemic_reserve")>0) {
                            if (data.isDecanting(MetalTagEnum.NICROSIL)){
                                if (!nicConsumeMet1){
                                    nbtLocal.putInt(this.metals[1].getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(this.metals[1].getNameLower()+"_feruchemic_reserve")-1));
                                    stack.setTag(nbtLocal);
                                }
                                nicConsumeMet1 = !nicConsumeMet1;
                            } else {
                                //las dos lineas de abajo van sin el nicrosil
                                nbtLocal.putInt(this.metals[1].getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(this.metals[1].getNameLower()+"_feruchemic_reserve")-1));
                                stack.setTag(nbtLocal);
                            }

                        } else {
                            stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                            data.setDecanting(this.metals[1],false);
                        }

                    } else if (data.isStoring(this.metals[1])) {
                        if (stack.getTag().getInt(this.metals[1].getNameLower()+"_feruchemic_reserve") < stack.getTag().getInt(this.metals[1].getNameLower()+"_feruchemic_max_capacity")) {

                            if (data.isStoring(MetalTagEnum.NICROSIL)) {
                                if (!nicConsumeMet1){
                                    stack.getTag().putString("key",changeOwner(player,stack.getTag(),true));
                                    nbtLocal.putInt(this.metals[1].getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(this.metals[1].getNameLower()+"_feruchemic_reserve")+1));
                                    stack.setTag(nbtLocal);
                                }
                                nicConsumeMet1 = !nicConsumeMet1;

                            } else {
                                stack.getTag().putString("key",changeOwner(player,stack.getTag(),true));
                                nbtLocal.putInt(this.metals[1].getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(this.metals[1].getNameLower()+"_feruchemic_reserve")+1));
                                stack.setTag(nbtLocal);
                            }
                        } else {
                            data.setStoring(this.metals[1],false);
                        }
                    }
                    ModNetwork.sync(data, player);
                });
            }
        }
        ICurioItem.super.curioTick(slotContext, stack);
    }

    private String dato;

    public String changeOwner(Player player, CompoundTag compoundNBT,boolean iStoreMetal) {

        boolean isFirstReserveZero = compoundNBT.getInt(this.metals[0].getNameLower()+"_feruchemic_reserve") == 0;
        boolean isSecondReserveZero = compoundNBT.getInt(this.metals[1].getNameLower()+"_feruchemic_reserve") == 0;

        dato = compoundNBT.getString("key");

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data -> {
            if (isFirstReserveZero && isSecondReserveZero && !data.isStoring(MetalTagEnum.ALUMINUM) &&
                    !data.isDecanting(MetalTagEnum.ALUMINUM) && iStoreMetal){
                dato = player.getStringUUID();
            } else if (isFirstReserveZero && isSecondReserveZero && !data.isStoring(MetalTagEnum.ALUMINUM) &&
                    !data.isDecanting(MetalTagEnum.ALUMINUM) && !iStoreMetal){
                dato = unkeyedString;
            }
            else if (data.isStoring(MetalTagEnum.ALUMINUM)) {
                dato = unkeyedString;
            } else if (data.isDecanting(MetalTagEnum.ALUMINUM)){
                dato = player.getStringUUID();
            }
        });
        return dato;
    }

    public MetalTagEnum getMetals(int pos) {
        return this.metals[pos];
    }

}


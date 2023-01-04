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
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;


public abstract class RingsMindAbstract <E extends AbstractFechuchemicHelper, T extends AbstractFechuchemicHelper> extends Item implements ICurioItem {
    private final MetalTagEnum[] metals = new MetalTagEnum[2];
    public String unkeyedString = "Nobody";

    private E firstSupplier;
    private T secondSupplier;

    public RingsMindAbstract(Properties properties, MetalTagEnum metal1, MetalTagEnum metal2, Supplier<? extends E> firstHelper, Supplier<? extends T> secondHelper) {
        super(properties);
        metals[0]=metal1;
        metals[1]=metal2;

        this.firstSupplier = firstHelper.get();
        this.secondSupplier = secondHelper.get();
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
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[0].getNameLower()).append(": "+ ((stack.getTag().getInt(metals[0].getNameLower()+"_feruchemic_reserve") * 100)/this.metals[0].getMaxReserveRing())+"%"));
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[1].getNameLower()).append(": "+ ((stack.getTag().getInt(metals[1].getNameLower()+"_feruchemic_reserve") * 100)/this.metals[1].getMaxReserveRing())+"%"));
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
        nbt.putString("key",this.unkeyedString);
        return nbt;
    }

    private boolean nicConsumeMet0 = false;
    private boolean nicConsumeMet1 = false;

    //public abstract int calculateDischarge(IInvestedPlayerData playerCapability, int metal0ActualReserve, boolean nicConsumeMet0);


    /*public void firstDecantingPower(Player player, IInvestedPlayerData playerCapability) {
        firstSupplier.decantPower(player);
    }

    public void secondDecantingPower(Player player, IInvestedPlayerData playerCapability) {
        secondSupplier.decantPower(player);
    }

    public void firstStoragePower(Player player, IInvestedPlayerData playerCapability) {
        firstSupplier.storagePower(player);
    }

    public void secondStoragePower(Player player, IInvestedPlayerData playerCapability) {
        secondSupplier.storagePower(player);
    }*/

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();

        if(!stack.hasTag()) {
            stack.setTag(addRingTags());
        }

        if (this instanceof RingLerasiumEttmetal ||this instanceof RingAtiumMalatium || this instanceof RingElectrumGold) {
            return;
        }

        CompoundTag nbtLocal = stack.getTag();

        if (livingEntity.level instanceof ServerLevel) {
            if (livingEntity instanceof Player) {

                Player player = (Player) livingEntity;
                IInvestedPlayerData playerCapability = CapabilityUtils.getCapability(player);

                if (playerCapability.isDecanting(MetalTagEnum.ALUMINUM) || playerCapability.isStoring(MetalTagEnum.ALUMINUM)){
                    stack.setTag(MetalMindsUtils.changeOwner(player, nbtLocal,false,this.metals[0],this.metals[1]));
                }

                String metal0Key = this.metals[0].getNameLower()+"_feruchemic_reserve";
                int metal0ActualReserve = stack.getTag().getInt(metal0Key);
                int metal0MaxReserve = this.metals[0].getMaxReserveRing();

                /**
                    DECANT
                 */

                if (playerCapability.isDecanting(this.metals[0])) {
                    if (metal0ActualReserve>0) {
                        stack.setTag(firstSupplier.calculateDischarge(nbtLocal,player,playerCapability,metal0ActualReserve,metal0Key,nicConsumeMet0));
                        if (playerCapability.isDecanting(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet0 = !nicConsumeMet0;
                        }
                    } else {
                        stack.setTag(MetalMindsUtils.changeOwner(player, nbtLocal,false,this.metals[0],this.metals[1]));
                        playerCapability.setDecanting(this.metals[0],false);
                    }
                /**
                 STORAGE
                 */
                } else if (playerCapability.isStoring(this.metals[0])) {
                    if (metal0ActualReserve < metal0MaxReserve) {
                        stack.setTag(MetalMindsUtils.changeOwner(player, nbtLocal,true,this.metals[0],this.metals[1]));
                        stack.setTag(firstSupplier.CalculateCharge(nbtLocal,player,playerCapability,metal0ActualReserve,metal0Key,nicConsumeMet0));
                        if (playerCapability.isStoring(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet0 = !nicConsumeMet0;
                        }
                    } else {
                        playerCapability.setStoring(this.metals[0],false);
                    }
                }

                String metal1Key = this.metals[1].getNameLower()+"_feruchemic_reserve";
                int metal1ActualReserve = stack.getTag().getInt(metal1Key);
                int metal1MaxReserve = this.metals[1].getMaxReserveRing();

                if (playerCapability.isDecanting(this.metals[1])) {
                    if (metal1ActualReserve>0) {

                        stack.setTag(secondSupplier.calculateDischarge(nbtLocal,player,playerCapability,metal1ActualReserve,metal1Key,nicConsumeMet1));

                        //nbtLocal.putInt(metal1Key,secondSupplier.calculateDischarge(player,playerCapability, metal1ActualReserve, nicConsumeMet1));
                        if (playerCapability.isDecanting(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet1 = !nicConsumeMet1;
                        }
                    } else {
                        stack.setTag(MetalMindsUtils.changeOwner(player, nbtLocal,false,this.metals[0],this.metals[1]));
                        playerCapability.setDecanting(this.metals[1],false);
                    }
                /**
                 STORAGE
                 */
                } else if (playerCapability.isStoring(this.metals[1])) {
                    if (metal1ActualReserve < metal1MaxReserve) {
                        stack.setTag(MetalMindsUtils.changeOwner(player, nbtLocal,true,this.metals[0],this.metals[1]));
                        stack.setTag(secondSupplier.CalculateCharge(nbtLocal,player,playerCapability,metal1ActualReserve,metal1Key,nicConsumeMet1));
                        if (playerCapability.isStoring(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet1 = !nicConsumeMet1;
                        }
                    } else {
                        playerCapability.setStoring(this.metals[1],false);
                    }
                }
                ModNetwork.sync(playerCapability, player);
            }
        }
        ICurioItem.super.curioTick(slotContext, stack);
    }

    public E getFirstSupplier() {
        return firstSupplier;
    }

    public T getSecondSupplier() {
        return secondSupplier;
    }

    public MetalTagEnum getMetals(int pos) {
        return this.metals[pos];
    }

}


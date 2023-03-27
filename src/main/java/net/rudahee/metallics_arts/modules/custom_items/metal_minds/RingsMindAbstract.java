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
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.rings.*;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.MetalMindsUtils;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;


/**
 * Abstract class that defines new custom elements. This class implements the specific functionality of feruchemical rings.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see Item
 * @see ICurioItem
 * @see AbstractFechuchemicHelper
 *
 */
public abstract class RingsMindAbstract <E extends AbstractFechuchemicHelper, T extends AbstractFechuchemicHelper> extends Item implements ICurioItem {
    private final MetalTagEnum[] metals = new MetalTagEnum[2];
    public String unkeyedString = "Nobody";
    private E firstSupplier;
    private T secondSupplier;


    /**
     * Default constructor, it is important that it receives both metals in the correct order, ore and alloy, along with their respective suppliers as a parameter,
     *
     * @param properties of the item.
     * @param metal1 first ring metal.
     * @param metal2 second ring metal.
     * @param firstHelper supplier of the first metal.
     * @param secondHelper supplier of the second metal.
     *
     */
    public RingsMindAbstract(Properties properties, MetalTagEnum metal1, MetalTagEnum metal2, Supplier<? extends E> firstHelper, Supplier<? extends T> secondHelper) {
        super(properties);
        metals[0]=metal1;
        metals[1]=metal2;

        this.firstSupplier = firstHelper.get();
        this.secondSupplier = secondHelper.get();
    }

    /**
     * This method modifies the player's internal information when a ring is equipped.
     * <p>
     * This will prevent 2 metal minds of the same type from being equipped at the same time.
     *
     * @param slotContext slot in which the item is placed.
     * @param prevStack item that is currently in the slot.
     * @param stack new item to be placed in the slot.
     *
     */
    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        Player player = (Player) slotContext.getWearer();
        IInvestedPlayerData data;
        try {
             data = CapabilityUtils.getCapability(player);
        } catch (PlayerException e) {
            e.printResumeLog();
            return;
        }

        data.setMetalMindEquiped(this.metals[0].getGroup(),true);
        data.setMetalMindEquiped(this.metals[1].getGroup(),true);

        ModNetwork.syncInvestedDataPacket(data, player);
        ICurioItem.super.onEquip(slotContext, prevStack, stack);
    }

    /**
     * This method modifies the player's internal information when a ring is unequipped.
     * <p>
     * After this, a new metal mind of this type could be equipped.
     *
     * @param slotContext slot in which the item is removed.
     * @param stack item that is currently in the slot.
     * @param newStack new item to be placed in the slot.
     */
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (this instanceof RingGoldElectrum) {
            return;
        }
        Player player = (Player) slotContext.getWearer();

        IInvestedPlayerData data;
        try {
            data = CapabilityUtils.getCapability(player);
        } catch (PlayerException e) {
            e.printResumeLog();
            return;
        }

        if (stack.getItem() != newStack.getItem()) {
            data.setMetalMindEquiped(this.metals[0].getGroup(),false);
            data.setMetalMindEquiped(this.metals[1].getGroup(),false);
            data.setStoring(this.metals[0],false);
            data.setStoring(this.metals[1],false);
            data.setTapping(this.metals[0],false);
            data.setTapping(this.metals[1],false);
            ModNetwork.syncInvestedDataPacket(data, player);
        }
        ICurioItem.super.onUnequip(slotContext, newStack, stack);
    }

    /**
     * This method checks the internal information of the player, to verify that they do not have equipped a metal mind of the type that they currently want to equip.
     *
     * @param slotContext slot in which the item tries to be placed.
     * @param stack item that is currently in the slot.
     *
     * @return boolean that indicates if the item can be equipped.
     *
     */

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        ICurioItem.super.canEquip(slotContext, stack);
        if(!stack.hasTag()) {
            stack.setTag(addRingTags());
        }
        Player player = (Player) slotContext.entity();
        IInvestedPlayerData data;
        try {
            data = CapabilityUtils.getCapability(player);
        } catch (PlayerException e) {
            e.printResumeLog();
            return false;
        }
        boolean canEquip = false;
        if (data != null) {
            canEquip = !(data.hasMetalMindEquiped(this.metals[0].getGroup()));
        }
        if (canEquip){
            if (!stack.getTag().getString("key").equals(unkeyedString)
                    && !player.getStringUUID().equals(stack.getTag().getString("key"))){
                canEquip = false;
            }
        }
        return canEquip;
    }

    /**
     * This method uses the internal information of the item to generate add the own Tooltips of the ring, for example, owner, and amount of current reservations.
     *
     * @param stack item that is being observed with the mouse over it.
     * @param level minecraft world you are in.
     * @param toolTips tooltips of the basic item, to which new information will be added.
     * @param flagIn flag that indicates if the tooltip information is normal or advanced, not used for mod item information.
     *
     */
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> toolTips, TooltipFlag flagIn) {
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
            if (level != null) {
                toolTips.add(Component.translatable("metallics_arts.mental_mind.owner").append(": "+ ((stack.getTag().getString("key").equals("Nobody")) ? Component.translatable("metallics_arts.mental_mind.nobody").getString() : (level.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))) == null) ? Component.translatable("metallics_arts.mental_mind.owner_someone") : level.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))).getName().getString())));
            }
            if (!Screen.hasShiftDown()){
                toolTips.add(Component.translatable(" "));
                toolTips.add(Component.translatable("metallics_arts.mental_mind_translate.shift_info").withStyle(ChatFormatting.BLUE));
            }
        }
        super.appendHoverText(stack, level, toolTips, flagIn);
    }

    /**
     * Auxiliary method to add tags in a metal mind, in case you didn't have them.
     *
     * @return CompoundTag
     *
     */
    private CompoundTag addRingTags() {
        CompoundTag nbt = new CompoundTag();

        nbt.putInt(this.metals[0].getNameLower()+"_feruchemic_reserve",0);
        nbt.putInt(this.metals[1].getNameLower()+"_feruchemic_reserve",0);
        nbt.putString("key",this.unkeyedString);
        return nbt;
    }

    private boolean nicConsumeMet0 = false;
    private boolean nicConsumeMet1 = false;


    /**
     * This method is in charge of loading and unloading the reserves within the mind of metal,
     * as well as granting the corresponding feruchemical powers to the player to whom the slotContext belongs,
     * all this through the metal suppliers.
     *
     * @param slotContext slot the item is in
     * @param stack item being used.
     *
     * @see AbstractFechuchemicHelper
     *
     */
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();

        if(!stack.hasTag()) {
            stack.setTag(addRingTags());
        }

        if (this instanceof RingAluminumDuralumin|| this instanceof RingGoldElectrum) {
            return;
        }

        CompoundTag compoundTag = stack.getTag();

        if (livingEntity.level instanceof ServerLevel) {
            if (livingEntity instanceof Player player) {
                IInvestedPlayerData playerCapability;
                try {
                    playerCapability = CapabilityUtils.getCapability(player);
                } catch (PlayerException ex) {
                    ex.printCompleteLog();
                    return;
                }

                if (playerCapability.isTapping(MetalTagEnum.ALUMINUM) || playerCapability.isStoring(MetalTagEnum.ALUMINUM)){
                    stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag,false,this.metals[0],this.metals[1]));
                }
                String metalKey = this.metals[0].getNameLower()+"_feruchemic_reserve";
                int actualReserve = stack.getTag().getInt(metalKey);
                int maxReserve = this.metals[0].getMaxReserveRing();
                // Tap.
                if (playerCapability.isTapping(this.metals[0])) {
                    if (actualReserve>0) {
                        stack.setTag(firstSupplier.calculateDischarge(compoundTag,player,playerCapability,actualReserve,metalKey,nicConsumeMet0));
                        if (playerCapability.isTapping(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet0 = !nicConsumeMet0;
                        }
                    } else {
                        stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag,false,this.metals[0],this.metals[1]));
                        playerCapability.setTapping(this.metals[0],false);
                    }
                    // Storage.
                } else if (playerCapability.isStoring(this.metals[0])) {
                    if (actualReserve < maxReserve) {
                        stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag,true,this.metals[0],this.metals[1]));
                        stack.setTag(firstSupplier.calculateCharge(compoundTag,player,playerCapability,actualReserve,metalKey,nicConsumeMet0));
                        if (playerCapability.isStoring(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet0 = !nicConsumeMet0;
                        }
                    } else {
                        playerCapability.setStoring(this.metals[0],false);
                    }
                }
                metalKey = this.metals[1].getNameLower()+"_feruchemic_reserve";
                actualReserve = stack.getTag().getInt(metalKey);
                maxReserve = this.metals[1].getMaxReserveRing();
                // Tap.
                if (playerCapability.isTapping(this.metals[1])) {
                    if (actualReserve>0) {
                        stack.setTag(secondSupplier.calculateDischarge(compoundTag,player,playerCapability,actualReserve,metalKey,nicConsumeMet1));
                        if (playerCapability.isTapping(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet1 = !nicConsumeMet1;
                        }
                    } else {
                        stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag,false,this.metals[0],this.metals[1]));
                        playerCapability.setTapping(this.metals[1],false);
                    }
                    // Storage.
                } else if (playerCapability.isStoring(this.metals[1])) {
                    if (actualReserve < maxReserve) {
                        stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag,true,this.metals[0],this.metals[1]));
                        stack.setTag(secondSupplier.calculateCharge(compoundTag,player,playerCapability,actualReserve,metalKey,nicConsumeMet1));
                        if (playerCapability.isStoring(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet1 = !nicConsumeMet1;
                        }
                    } else {
                        playerCapability.setStoring(this.metals[1],false);
                    }
                }
                ModNetwork.syncInvestedDataPacket(playerCapability, player);
            }
        }
        ICurioItem.super.curioTick(slotContext, stack);
    }

    //todo
    public E getFirstSupplier() {
        return firstSupplier;
    }

    //todo
    public T getSecondSupplier() {
        return secondSupplier;
    }

    //todo
    public MetalTagEnum getMetals(int pos) {
        return this.metals[pos];
    }

}


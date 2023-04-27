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
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands.*;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.server_events.OnWorldTickEvent;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.MathUtils;
import net.rudahee.metallics_arts.utils.MetalMindsUtils;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * Abstract class that defines new custom elements. This class implements the specific functionality of feruchemical bands.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see Item
 * @see ICurioItem
 * @see AbstractFechuchemicHelper
 */
public abstract class BandMindAbstract extends Item implements ICurioItem {

    private final MetalTagEnum[] metals = new MetalTagEnum[2];
    public String unkeyedString = "Nobody";

    private AbstractFechuchemicHelper firstHelper;
    private AbstractFechuchemicHelper secondHelper;

    /**
     * Default constructor, it is important that it receives both metals in the correct order, ore and alloy, along with their respective suppliers as a parameter.
     *
     * @param properties of the item.
     * @param metal1 first ring metal.
     * @param metal2 second ring metal.
     *
     */
    public BandMindAbstract(Properties properties, MetalTagEnum metal1, MetalTagEnum metal2, AbstractFechuchemicHelper firstHelper, AbstractFechuchemicHelper secondHelper) {
        super(properties);
        metals[0]=metal1;
        metals[1]=metal2;
        this.firstHelper = firstHelper;
        this.secondHelper = secondHelper;
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

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data -> {
            data.setMetalMindEquiped(this.metals[0].getGroup(),true);
            data.setMetalMindEquiped(this.metals[1].getGroup(),true);
            ModNetwork.syncInvestedDataPacket(data, player);
        });
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
     *
     */
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {

        Player player = (Player) slotContext.getWearer();
        if (stack.getItem() != newStack.getItem()) {
            player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data ->{
                data.setMetalMindEquiped(this.metals[0].getGroup(),false);
                data.setMetalMindEquiped(this.metals[1].getGroup(),false);
                data.setStoring(this.metals[0],false);
                data.setStoring(this.metals[1],false);
                data.setTapping(this.metals[0],false);
                data.setTapping(this.metals[1],false);

                ModNetwork.syncInvestedDataPacket(data, player);
            });
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
        Player player = (Player) slotContext.entity();
        IInvestedPlayerData data;
        if(!stack.hasTag()) {
            stack.setTag(addBandTags());
        }
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

        ICurioItem.super.canEquip(slotContext, stack);
        return canEquip;
    }
    /**
     * This method checks the player's internal information, to verify that they do not have a Metal Mind of the type they wish to auto-equip equipped.
     *
     * @param slotContext slot in which the item tries to be placed.
     * @param stack item that is currently in the slot.
     *
     * @return boolean that indicates if the item can auto-equip.
     *
     */
    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        ICurioItem.super.canEquipFromUse(slotContext, stack);
        Player player = (Player) slotContext.entity();
        IInvestedPlayerData data;
        if(!stack.hasTag()) {
            stack.setTag(addBandTags());
        }
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
     * This method uses the internal information of the item to generate add the own Tooltips of the band, for example, owner, and amount of current reservations.
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
            stack.setTag(addBandTags());
        }
        if (this instanceof BandLerasiumEttmetal || this instanceof BandAtiumMalatium
                || this instanceof BandCopperBronze || this instanceof BandAluminumDuralumin){
            return;
        }
        if (stack.hasTag()) {
            if (!Screen.hasShiftDown()){
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[0].getNameLower()).append(": "+ stack.getTag().getInt(metals[0].getNameLower()+"_feruchemic_reserve") / 20 + "s"));
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[1].getNameLower()).append(": "+ stack.getTag().getInt(metals[1].getNameLower()+"_feruchemic_reserve") / 20 + "s"));
            } else {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[0].getNameLower()).append(": "+ ((stack.getTag().getInt(metals[0].getNameLower()+"_feruchemic_reserve") * 100)/this.metals[0].getMaxReserveBand())+"%"));
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[1].getNameLower()).append(": "+ ((stack.getTag().getInt(metals[1].getNameLower()+"_feruchemic_reserve") * 100)/this.metals[1].getMaxReserveBand())+"%"));
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
     */
    private CompoundTag addBandTags() {
        CompoundTag nbt = new CompoundTag();

        nbt.putInt(this.metals[0].getNameLower()+"_feruchemic_reserve",0);
        nbt.putInt(this.metals[1].getNameLower()+"_feruchemic_reserve",0);
        nbt.putString("key",this.unkeyedString);
        return nbt;
    }

    /**
     * Creates a CompoundTag with Feruchemic reserve values for the specified metals.
     * If the first metal is Aluminum, its reserve value is set to 3.
     * If the first metal is Lerasium, its reserve value is set to 1 and additional values are added for all metals in a Lerasium band.
     * Otherwise, the reserve values are set to the max reserve band for each metal.
     * The key "Nobody" is also added to the CompoundTag.
     *
     * @param metal1 the first metal (MetalTagEnum)
     * @param metal2 the second metal (MetalTagEnum)
     * @return the created CompoundTag with the specified Feruchemic reserve values
     */
    public static CompoundTag addBandTagsFull(MetalTagEnum metal1, MetalTagEnum metal2) {
        CompoundTag nbt = new CompoundTag();

        if(metal1.equals(MetalTagEnum.ALUMINUM)){
            nbt.putInt(metal1.getNameLower()+"_feruchemic_reserve",3);

        }else if(metal1.equals(MetalTagEnum.LERASIUM)){
            nbt.putInt(metal1.getNameLower() + "_feruchemic_reserve",1);
            for (MetalTagEnum metal: MetalTagEnum.values()) {
                nbt.putInt(metal.getNameLower()+"inLerasiumBand",metal.getMaxAllomanticTicksStorage());
            }
        }else {
            nbt.putInt(metal1.getNameLower()+"_feruchemic_reserve",metal1.getMaxReserveBand());

        }

        nbt.putInt(metal2.getNameLower()+"_feruchemic_reserve",metal2.getMaxReserveBand());
        nbt.putString("key","Nobody");

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
     */
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();

        if(!stack.hasTag()) {
            stack.setTag(addBandTags());
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

                if (playerCapability.isTapping(MetalTagEnum.ALUMINUM) || playerCapability.isStoring(MetalTagEnum.ALUMINUM)) {
                    stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag, false, this.metals[0], this.metals[1]));
                }
                String metalKey = this.metals[0].getNameLower() + "_feruchemic_reserve";
                int actualReserve = stack.getTag().getInt(metalKey);
                int maxReserve = this.metals[0].getMaxReserveBand();
                // Tap.
                if (playerCapability.isTapping(this.metals[0])) {
                    if (actualReserve > 0) {
                        stack.setTag(firstHelper.calculateDischarge(compoundTag, player, playerCapability, actualReserve, metalKey, nicConsumeMet0));
                        if (MathUtils.isDivisibleBy10(OnWorldTickEvent.getActualTick())) {
                            firstHelper.tapPower(player);
                        }
                    } else {
                        stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag, false, this.metals[0], this.metals[1]));
                        playerCapability.setTapping(this.metals[0], false);
                    }
                    // Storage.
                } else if (playerCapability.isStoring(this.metals[0])) {
                    if (actualReserve < maxReserve) {
                        stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag, true, this.metals[0], this.metals[1]));
                        stack.setTag(firstHelper.calculateCharge(compoundTag, player, playerCapability, actualReserve, metalKey, nicConsumeMet0));
                        if (MathUtils.isDivisibleBy10(OnWorldTickEvent.getActualTick())) {
                            firstHelper.storagePower(player);
                        }
                    } else {
                        playerCapability.setStoring(this.metals[0], false);
                    }
                }
                metalKey = this.metals[1].getNameLower() + "_feruchemic_reserve";
                actualReserve = stack.getTag().getInt(metalKey);
                maxReserve = this.metals[1].getMaxReserveBand();
                // Tap.
                if (playerCapability.isTapping(this.metals[1])) {
                    if (actualReserve > 0) {
                        stack.setTag(secondHelper.calculateDischarge(compoundTag, player, playerCapability, actualReserve, metalKey, nicConsumeMet1));
                        secondHelper.tapPower(player);

                    } else {
                        stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag, false, this.metals[0], this.metals[1]));
                        playerCapability.setTapping(this.metals[1], false);
                    }
                    // Storage.
                } else if (playerCapability.isStoring(this.metals[1])) {
                    if (actualReserve < maxReserve) {
                        stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag, true, this.metals[0], this.metals[1]));
                        stack.setTag(secondHelper.calculateCharge(compoundTag, player, playerCapability, actualReserve, metalKey, nicConsumeMet1));
                        secondHelper.storagePower(player);
                    } else {
                        playerCapability.setStoring(this.metals[1], false);
                    }
                }
                if (playerCapability.isStoring(MetalTagEnum.NICROSIL) || playerCapability.isTapping(MetalTagEnum.NICROSIL)) {
                    nicConsumeMet0 = !nicConsumeMet0;
                    nicConsumeMet1 = !nicConsumeMet1;
                }
                ModNetwork.syncInvestedDataPacket(playerCapability, player);
            }
        }
        ICurioItem.super.curioTick(slotContext, stack);
    }

    /**
     * Returns the MetalTagEnum from the metals array at the specified position.
     *
     * @param pos the position of the MetalTagEnum in the metals array
     * @return the MetalTagEnum at the specified position
     */
    public MetalTagEnum getMetals(int pos) {
        return this.metals[pos];
    }

}

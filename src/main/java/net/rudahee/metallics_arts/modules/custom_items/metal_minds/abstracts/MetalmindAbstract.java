package net.rudahee.metallics_arts.modules.custom_items.metal_minds.abstracts;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.custom_tiers.CustomMaterials;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalmindType;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.effects.ModEffects;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.MetalMindsUtils;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Abstract class in which the base functionality of metal minds is defined,
 * which in specific cases are redefined by those who inherit from it, due to specific situations.
 *
 * @see Item
 * @see ICurioItem
 *
 * @author SteelCode Team
 * @since 1.6.8
 *
 */
public abstract class MetalmindAbstract extends Item implements ICurioItem {

    private final MetalTagEnum[] metals = new MetalTagEnum[2];
    public String unkeyedString = "Nobody";
    private MetalmindType type;

    public MetalmindAbstract(Item.Properties properties, MetalTagEnum metal0, MetalTagEnum metal1, MetalmindType type) {
        super(properties);
        this.metals[0] = metal0;
        this.metals[1] = metal1;
        this.type = type;
    }

    /**
     * Called when a curio item is equipped in a specified slot.
     * <p>
     * Here the capability of the player is modified with the metal mind equipped.
     *
     * @param slotContext The context of the slot where the item is being equipped.
     * @param prevStack The previous item stack in the slot, if any.
     * @param stack The new item stack being equipped.
     */
    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        ICurioItem.super.onEquip(slotContext, prevStack, stack);
        Player player = (Player) slotContext.getWearer();
            IInvestedPlayerData playerCapability = CapabilityUtils.getCapability(player);

            playerCapability.setMetalMindEquiped(this.metals[0].getGroup(),true);
            playerCapability.setMetalMindEquiped(this.metals[1].getGroup(),true);
            ModNetwork.syncInvestedDataPacket(playerCapability, player);


    }


    /**
     * Called when a curio item is unequipped from a specified slot.
     * <p>
     * Here the capability of the player is modified removing the metal mind from this.
     *
     * @param slotContext The context of the slot from which the item is being unequipped.
     * @param newStack The new item stack that will replace the curio item, if any.
     * @param stack The item stack that is being unequipped.
     */
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        ICurioItem.super.onUnequip(slotContext, newStack, stack);
        Player player = (Player) slotContext.getWearer();
        if (stack.getItem() != newStack.getItem()) {
                IInvestedPlayerData playerCapability = CapabilityUtils.getCapability(player);

                playerCapability.setMetalMindEquiped(this.metals[0].getGroup(),false);
                playerCapability.setMetalMindEquiped(this.metals[1].getGroup(),false);
                playerCapability.setStoring(this.metals[0],false);
                playerCapability.setStoring(this.metals[1],false);
                playerCapability.setTapping(this.metals[0],false);
                playerCapability.setTapping(this.metals[1],false);

                ModNetwork.syncInvestedDataPacket(playerCapability, player);


        }
    }


    /**
     * Determines whether a curio item can be equipped in the slot.
     * <p>
     * To do this, check that a mind of the same metals is not currently equipped (either band or ring).
     *
     * @param slotContext The context of the slot where the item is to be equipped.
     * @param stack The item stack being considered for equipping.
     * @return {@code true} if the item can be equipped, or {@code false} otherwise.
     */
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        ICurioItem.super.canEquip(slotContext, stack);
        Player player = (Player) slotContext.entity();

        if(!stack.hasTag()) {
            stack.setTag(addTags());
        }


            IInvestedPlayerData playerCapability = CapabilityUtils.getCapability(player);
            return !playerCapability.hasMetalMindEquiped(this.metals[0].getGroup()) && (stack.getTag().getString("key").equals(unkeyedString) || player.getStringUUID().equals(stack.getTag().getString("key")));


    }

    /**
     * Determines whether a curious item can be equipped in any slot when the item is used (use right click).
     * <p>
     * To do this, check that a mind of the same metals is not currently equipped (either band or ring).
     *
     * @param slotContext The context of the slot where the item is being equipped.
     * @param stack The item stack that is being considered for equipping.
     * <p>
     * @return {@code true} if the item can be equipped, {@code false} otherwise.
     */
    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        ICurioItem.super.canEquip(slotContext, stack);
        Player player = (Player) slotContext.entity();

        if(!stack.hasTag()) {
            stack.setTag(addTags());
        }

            IInvestedPlayerData playerCapability = CapabilityUtils.getCapability(player);
            return !playerCapability.hasMetalMindEquiped(this.metals[0].getGroup()) && (stack.getTag().getString("key").equals(unkeyedString) || player.getStringUUID().equals(stack.getTag().getString("key")));


    }

    /**
     * Appends additional hover text to an ItemStack when it is hovered over in the game.
     * <p>
     * In this case, it shows the metal and its charge in seconds, and if shift is pressed, the percentage of charge of each metal.
     *
     * @param stack The ItemStack to append hover text to.
     * @param level The game world level, or null if not in a world.
     * @param toolTips A list of text components representing the hover text to display.
     * @param flagIn A flag indicating the tooltip context.
     */
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> toolTips, TooltipFlag flagIn) {

        if(!stack.hasTag()) {
            stack.setTag(addTags());
        }

        int maxReserve0 = this.type == MetalmindType.BAND ? this.metals[0].getMaxReserveBand() : this.metals[0].getMaxReserveRing();
        int maxReserve1 = this.type == MetalmindType.BAND ? this.metals[1].getMaxReserveBand() : this.metals[1].getMaxReserveRing();

        if (stack.hasTag()) {
            if (!Screen.hasShiftDown()) {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[0].getNameLower()).append(": "+ stack.getTag().getInt(metals[0].getNameLower()+"_feruchemic_reserve") / 20 + "s"));
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[1].getNameLower()).append(": "+ stack.getTag().getInt(metals[1].getNameLower()+"_feruchemic_reserve") / 20 + "s"));
            } else {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[0].getNameLower()).append(": "+ ((stack.getTag().getInt(metals[0].getNameLower()+"_feruchemic_reserve") * 100)/maxReserve0)+"%"));
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[1].getNameLower()).append(": "+ ((stack.getTag().getInt(metals[1].getNameLower()+"_feruchemic_reserve") * 100)/maxReserve1)+"%"));
            }
            if (level != null) {
                toolTips.add(Component.translatable("metallics_arts.metal_mind.owner").append(": "+ ((stack.getTag().getString("key").equals("Nobody")) ? Component.translatable("metallics_arts.metal_mind.nobody").getString() : (level.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))) == null) ? Component.translatable("metallics_arts.metal_mind.owner_someone") : level.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))).getName().getString())));
            }
            if (!Screen.hasShiftDown()) {
                toolTips.add(Component.literal(" "));
                toolTips.add(Component.translatable("metallics_arts.metal_mind_translate.shift_info").withStyle(ChatFormatting.BLUE));
            }
        }
        super.appendHoverText(stack, level, toolTips, flagIn);
    }

    /**
     * Creates and returns a CompoundTag containing specific data to this metalmind.
     *<p>
     * @return A CompoundTag containing metadata, including feruchemic reserves for two metals and a custom key as a string.
     */
    private CompoundTag addTags() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putInt(this.metals[0].getNameLower()+"_feruchemic_reserve",0);
        compoundTag.putInt(this.metals[1].getNameLower()+"_feruchemic_reserve",0);
        compoundTag.putString("key",this.unkeyedString);
        return compoundTag;
    }

    private boolean nicConsume = false;


    /**
     * Handles the periodic tick update for a curio item,
     * <p>
     * In this case, the data of the owner of the metal mind and its reserves.
     * <p>
     * It is also responsible for synchronize the player to update the data of the powers used.
     *
     * @param slotContext The context of the slot in which the curio item is equipped.
     * @param stack The item stack represents.
     */
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();

        if(!stack.hasTag()) {
            stack.setTag(addTags());
        }

        CompoundTag compoundTag = stack.getTag();

        if (compoundTag == null) return;

        if (livingEntity.level instanceof ServerLevel) {
            if (livingEntity instanceof Player player) {
                IInvestedPlayerData playerCapability;

                    playerCapability = CapabilityUtils.getCapability(player);


                if (playerCapability.isTapping(MetalTagEnum.ALUMINUM) || playerCapability.isStoring(MetalTagEnum.ALUMINUM)) {
                    stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag, false, this.metals[0], this.metals[1]));
                }

                powerUse(compoundTag, stack, playerCapability, player, this.metals[0], true);
                powerUse(compoundTag, stack, playerCapability, player, this.metals[1], false);

                if (playerCapability.isStoring(MetalTagEnum.NICROSIL) || playerCapability.isTapping(MetalTagEnum.NICROSIL)) {
                    nicConsume = !nicConsume;
                }
                ModNetwork.syncInvestedDataPacket(playerCapability, player);
            }
        }
        ICurioItem.super.curioTick(slotContext, stack);
    }


    /**
     * Manage the use, charge and discharge of reserves to a specific metal in the curio item.
     *
     * @param compoundTag The compound tag associated with the curio item.
     * @param stack The item stack represents.
     * @param playerCapability The invested player data capability for the player.
     * @param player The player associated with the curio item.
     * @param metal The metal being used or charged.
     * @param first Specifies whether this is the first or second metal being used or charged.
     */
    private void powerUse(CompoundTag compoundTag, ItemStack stack, IInvestedPlayerData playerCapability, Player player, MetalTagEnum metal, boolean first) {
        int actualReserve = stack.getTag().getInt(metal.getNameLower() + "_feruchemic_reserve");
        int maxReserve = this.type.equals(MetalmindType.BAND) ? metal.getMaxReserveBand() : metal.getMaxReserveRing();
        //Tap
        if (playerCapability.isTapping(metal)) {
            if (actualReserve > 0) {
                if (first) {
                    stack.setTag(calculateFirstMetalDischarge(compoundTag, player, playerCapability, actualReserve, nicConsume, metal));
                } else {
                    stack.setTag(calculateSecondMetalDischarge(compoundTag, player, playerCapability, actualReserve, nicConsume, metal));
                }
            } else {
                stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag, false, this.metals[0], this.metals[1]));
                playerCapability.setTapping(metal, false);
            }
        //Storage.
        } else if (playerCapability.isStoring(metal)) {
            if (actualReserve < maxReserve) {
                stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag, true, this.metals[0], this.metals[1]));
                if (first) {
                    stack.setTag(calculateFirstMetalCharge(compoundTag, player,playerCapability, actualReserve, nicConsume, metal));
                } else {
                    stack.setTag(calculateSecondMetalCharge(compoundTag, player,playerCapability, actualReserve, nicConsume, metal));
                }
            } else {
                playerCapability.setStoring(metal, false);
            }
        }
    }

    /**
     * Retrieves the metal at the specified position in the curio item's metal array.
     *
     * @param pos The position of the metal to retrieve.
     *
     * @return The MetalTagEnum representing the metal at the specified position.
     */
    public MetalTagEnum getMetals(int pos) {
        return this.metals[pos];
    }

    /**
     * Checks if the curio item's type matches the given MetalmindType.
     *
     * @param type The MetalmindType to compare with the curio item's type.
     *
     * @return True if the curio item is of the specified type, false otherwise.
     */
    public boolean isBand(MetalmindType type) {
        return this.type.equals(type);
    }

    private int tick;


    /**
     * Calculates the charge for the first metal in the curio item and updates the compound tag accordingly.
     * <p>
     * It is divided into first and second metal, since in case one of these has a specific type of charge,
     * an override of said metal can be done in the specific metal mind.
     *
     * @param compoundTag The compound tag associated with the curio item.
     * @param player The player performing the charge.
     * @param playerCapability The invested player data capability for the player.
     * @param metalReserve The current reserve of the metal to be charged.
     * @param nicConsume Specifies whether nicrosil consumption is enabled.
     * @param metal The metal being charged.
     *
     * @return The updated compound tag with the first metal charge applied.
     */
    public CompoundTag calculateFirstMetalCharge(CompoundTag compoundTag, Player player,IInvestedPlayerData playerCapability, int metalReserve, boolean nicConsume, MetalTagEnum metal) {
        return basicCharge(compoundTag, player,playerCapability, metalReserve, nicConsume, metal);
    }

    /**
     * Calculates the charge for the second metal in the curio item and updates the compound tag accordingly.
     * <p>
     * It is divided into first and second metal, since in case one of these has a specific type of charge,
     * an override of said metal can be done in the specific metal mind.
     *
     * @param compoundTag The compound tag associated with the curio item.
     * @param player The player performing the charge.
     * @param playerCapability The invested player data capability for the player.
     * @param metalReserve The current reserve of the metal to be charged.
     * @param nicConsume Specifies whether nicrosil consumption is enabled.
     * @param metal The metal being charged.
     * @return The updated compound tag with the second metal charge applied.
     */
    public CompoundTag calculateSecondMetalCharge(CompoundTag compoundTag, Player player,IInvestedPlayerData playerCapability, int metalReserve, boolean nicConsume, MetalTagEnum metal) {
        return basicCharge(compoundTag, player,playerCapability, metalReserve, nicConsume, metal);
    }

    /**
     * Calculates the discharge for the first metal in the curio item and updates the compound tag accordingly.
     * <p>
     * It is divided into first and second metal, since in case one of these has a specific type of discharge,
     * an override of said metal can be done in the specific metal mind.
     *
     * @param compoundTag The compound tag associated with the curio item.
     * @param player The player performing the discharge.
     * @param playerCapability The invested player data capability for the player.
     * @param metalReserve The current reserve of the metal to be discharged.
     * @param nicConsume Specifies whether nicrosil consumption is enabled.
     * @param metal The metal being discharged.
     * @return The updated compound tag with the first metal discharge applied.
     */
    public CompoundTag calculateFirstMetalDischarge(CompoundTag compoundTag, Player player,IInvestedPlayerData playerCapability, int metalReserve, boolean nicConsume, MetalTagEnum metal) {
        return basicDischarge(compoundTag, player,playerCapability, metalReserve, nicConsume, metal);
    }

    /**
     * Calculates the discharge for the second metal in the curio item and updates the compound tag accordingly.
     * <p>
     * It is divided into first and second metal, since in case one of these has a specific type of discharge,
     * an override of said metal can be done in the specific metal mind.
     *
     * @param compoundTag The compound tag associated with the curio item.
     * @param player The player performing the discharge.
     * @param playerCapability The invested player data capability for the player.
     * @param metalReserve The current reserve of the metal to be discharged.
     * @param nicConsume Specifies whether nicrosil consumption is enabled.
     * @param metal The metal being discharged.
     * @return The updated compound tag with the first metal discharge applied.
     */
    public CompoundTag calculateSecondMetalDischarge(CompoundTag compoundTag, Player player,IInvestedPlayerData playerCapability, int metalReserve, boolean nicConsume, MetalTagEnum metal) {
        return basicDischarge(compoundTag, player,playerCapability, metalReserve, nicConsume, metal);
    }

    /**
     * Applies a basic charge operation to a specific metal and updates the compound tag accordingly.
     * <p>
     * This is executed in case the metal die does not have a specific type of charge.
     *
     * @param compoundTag The compound tag associated with the curio item.
     * @param player The player performing the charge.
     * @param playerCapability The invested player data capability for the player.
     * @param metalReserve The current reserve of the metal.
     * @param nicConsume Specifies whether nicrosil consumption is enabled.
     * @param metal The metal being charged.
     * @return The updated compound tag with the metal charge applied.
     */
    private CompoundTag basicCharge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, boolean nicConsume, MetalTagEnum metal) {
        String metalKey = metal.getNameLower() + "_feruchemic_reserve";

        ModEffects.giveFeruchemicalStorageEffect(player, metal);
        if (!playerCapability.isStoring(MetalTagEnum.NICROSIL) || !nicConsume) {
            compoundTag.putInt(metalKey, metalReserve + 1);
        }
        return compoundTag;
    }

    /**
     * Applies a basic discharge operation to a specific metal and updates the compound tag accordingly.
     * <p>
     * This is executed in case the metal die does not have a specific type of discharge.
     * <p>
     * Additionally, compounding specific consumption logic is added here, decreasing reserves only when compound values match.
     *
     * @param compoundTag The compound tag associated with the curio item.
     * @param player The player performing the charge.
     * @param playerCapability The invested player data capability for the player.
     * @param metalReserve The current reserve of the metal.
     * @param nicConsume Specifies whether nicrosil consumption is enabled.
     * @param metal The metal being charged.
     * @return The updated compound tag with the metal charge applied.
     */

    private CompoundTag basicDischarge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, boolean nicConsume, MetalTagEnum metal) {
        String metalKey = metal.getNameLower() + "_feruchemic_reserve";
        ModEffects.giveFeruchemicalTapEffect(player, metal);

        if (playerCapability.isBurning(metal)) {
            if (tick % metal.getFeruchemicCompoundingMultiplier() == 0) {
                if (!playerCapability.isTapping(MetalTagEnum.NICROSIL) || !nicConsume) {
                    compoundTag.putInt(metalKey, metalReserve - 1);
                }
            }
            tick++;
            if (tick>=4) {
                tick = 0;
            }
        } else {
            if (!playerCapability.isTapping(MetalTagEnum.NICROSIL) || !nicConsume) {
                compoundTag.putInt(metalKey, metalReserve - 1);
            }
        }
        return compoundTag;
    }


    /**
     * Creates and returns a compound tag containing full feruchemical reserve information for two metals in a band.
     *
     * @param metal1 The first metal for which to create the reserve information.
     * @param metal2 The second metal for which to create the reserve information.
     *
     * @return A CompoundTag containing the feruchemical reserve information for the specified metals.
     */
    public static CompoundTag addBandTagsFull(MetalTagEnum metal1, MetalTagEnum metal2) {
        CompoundTag nbt = new CompoundTag();

        if (metal1.equals(MetalTagEnum.ALUMINUM)) {
            nbt.putInt(metal1.getNameLower()+"_feruchemic_reserve",3);

        } else if(metal1.equals(MetalTagEnum.LERASIUM)) {
            nbt.putInt(metal1.getNameLower() + "_feruchemic_reserve",1);
            for (MetalTagEnum metal: MetalTagEnum.values()) {
                nbt.putInt(metal.getNameLower()+"inLerasiumBand", metal.getMaxAllomanticTicksStorage());
            }
        } else {
            nbt.putInt(metal1.getNameLower()+"_feruchemic_reserve", metal1.getMaxReserveBand());
        }
        nbt.putInt(metal2.getNameLower()+"_feruchemic_reserve", metal2.getMaxReserveBand());
        nbt.putString("key","Nobody");

        return nbt;
    }


    public static CompoundTag addRingTagsFull(MetalTagEnum metal1, MetalTagEnum metal2) {
        CompoundTag nbt = new CompoundTag();

        if (metal1.equals(MetalTagEnum.ALUMINUM)) {
            nbt.putInt(metal1.getNameLower()+"_feruchemic_reserve",3);

        } else if(metal1.equals(MetalTagEnum.LERASIUM)) {
            nbt.putInt(metal1.getNameLower() + "_feruchemic_reserve",1);
            for (MetalTagEnum metal: MetalTagEnum.values()) {
                nbt.putInt(metal.getNameLower()+"inLerasiumBand", metal.getMaxAllomanticTicksStorage());
            }
        } else {
            nbt.putInt(metal1.getNameLower()+"_feruchemic_reserve", metal1.getMaxReserveRing());
        }
        nbt.putInt(metal2.getNameLower()+"_feruchemic_reserve", metal2.getMaxReserveRing());
        nbt.putString("key","Nobody");

        return nbt;
    }
}

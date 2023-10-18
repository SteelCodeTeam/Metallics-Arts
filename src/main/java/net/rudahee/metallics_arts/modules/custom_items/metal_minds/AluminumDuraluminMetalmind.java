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
import net.rudahee.metallics_arts.data.enums.implementations.MetalmindType;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.abstracts.MetalmindAbstract;
import net.rudahee.metallics_arts.modules.effects.ModEffects;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals.AluminumFeruchemicHelper;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

/**
 * Class that specifies the aluminum and duralumin band, we pass to the abstract class (which has the behavior) the metals that compose it,
 * in the order: metal and its alloy, along with their corresponding suppliers.
 *
 * @author SteelCode Team
 * @since 1.6.8
 *
 */
public class AluminumDuraluminMetalmind extends MetalmindAbstract {

    /**
     * Default constructor, it is important to send the metals by parameter in the correct order, metal and its alloy.
     *
     * @param properties of the item.
     */
    public AluminumDuraluminMetalmind(Item.Properties properties, MetalmindType type) {
        super(properties, MetalTagEnum.ALUMINUM, MetalTagEnum.DURALUMIN, type);
    }


    /**
     * Appends hover text information to an ItemStack, providing details about its Feruchemical reserves and ownership.
     *
     * In this case it is redefined to reflect the specific states of the aluminum (powerOff, storing identity, or tapping identity).
     *
     * @param stack     The ItemStack to display hover text for.
     * @param level     The Level (game world) context, which may be null.
     * @param toolTips  A list of Components to which the hover text information is added.
     * @param flagIn    The TooltipFlag that specifies the display mode.
     */
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> toolTips, TooltipFlag flagIn) {
        if (stack.hasTag()) {
            int reserve = stack.getTag().getInt(this.getMetals(0).getNameLower()+"_feruchemic_reserve");

            if (reserve == 1) {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": ").append(Component.translatable("metallics_arts.metal_mind_translate.store_identity")));
            } else if (reserve == 2) {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": ").append(Component.translatable("metallics_arts.metal_mind_translate.tapping_identity")));
            } else {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": ").append(Component.translatable("metallics_arts.metal_mind_translate.off_power")));
            }
            if (!Screen.hasShiftDown()) {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+ stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") / 20 + "s"));
            } else {
                int maxReserve = this.isBand(MetalmindType.BAND) ? this.getMetals(1).getMaxReserveBand() : this.getMetals(1).getMaxReserveRing();
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+ ((stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") * 100)/maxReserve)+"%"));
            }
            if (level != null) {
                toolTips.add(Component.translatable("metallics_arts.metal_mind.owner").append(": "+ ((stack.getTag().getString("key").equals("Nobody")) ? Component.translatable("metallics_arts.metal_mind.nobody").getString() : (level.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))) == null) ? Component.translatable("metallics_arts.metal_mind.owner_someone") : level.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))).getName().getString())));
            }
            if (!Screen.hasShiftDown()) {
                toolTips.add(Component.literal(" "));
                toolTips.add(Component.translatable("metallics_arts.metal_mind_translate.shift_info").withStyle(ChatFormatting.BLUE));

            }
        }
    }

    /**
     * Handles the periodic tick update for a curio item,
     * <p>
     * In this specific case, the method is used so that when the player is not using the aluminum,
     * its reserve is modified to show that it is off.
     *
     * @param slotContext The context of the slot in which the curio item is equipped.
     * @param stack The item stack represents.
     */
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        CompoundTag compoundTag = stack.getTag();

        if(stack.hasTag()) {
            if (livingEntity.level instanceof ServerLevel) {
                if (livingEntity instanceof Player player) {
                    IInvestedPlayerData playerCapability;
                    try {
                        playerCapability = CapabilityUtils.getCapability(player);
                        int actualReserve = stack.getTag().getInt(this.getMetals(0).getNameLower() + "_feruchemic_reserve");
                        if (!playerCapability.isTapping(MetalTagEnum.ALUMINUM) && !playerCapability.isStoring(MetalTagEnum.ALUMINUM) && actualReserve != 3) {
                            stack.setTag(turnOffPower(compoundTag,this.getMetals(0).getNameLower() + "_feruchemic_reserve"));
                        }
                    } catch (PlayerException ex) {
                        ex.printCompleteLog();
                    }
                }
            }
        }
        super.curioTick(slotContext, stack);
    }

    /**
     * Called when a curio item is unequipped from a specified slot.
     * <p>
     * Here a special modification is made to the value of the aluminum reserve so that it reflects that the power is off.
     *
     * @param slotContext The context of the slot from which the item is being unequipped.
     * @param newStack The new item stack that will replace the curio item, if any.
     * @param stack The item stack that is being unequipped.
     */

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if(stack.hasTag()) {
            stack.setTag(turnOffPower(stack.getTag(),this.getMetals(0).getNameLower() + "_feruchemic_reserve"));
        }
        super.onUnequip(slotContext, newStack, stack);
    }


    /**
     * Redefine of the method of the MetalmindAbstract class.
     * In this specific case, because the logic of the charge is proper of the Aluminum.
     *
     * @param compoundTag The compound tag associated with the curio item.
     * @param player The player performing the charge.
     * @param playerCapability The invested player data capability for the player.
     * @param metalReserve The current reserve of the metal to be charged.
     * @param nicConsume Specifies whether nicrosil consumption is enabled.
     * @param metal The metal being charged.
     *
     * @return The updated compound tag with the first metal charge applied.
     *
     */
    @Override
    public CompoundTag calculateFirstMetalDischarge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, boolean nicConsume, MetalTagEnum metal) {
        ModEffects.giveFeruchemicalTapEffect(player, metal);
        compoundTag.putInt(metal.getNameLower() + "_feruchemic_reserve", 1);
        return compoundTag;
    }

    /**
     * Redefine of the method of the MetalmindAbstract class.
     * In this specific case, the reserve is set to 2, which is the storage value.
     *
     * @param compoundTag The compound tag associated with the curio item.
     * @param player The player performing the charge.
     * @param playerCapability The invested player data capability for the player.
     * @param metalReserve The current reserve of the metal to be charged.
     * @param nicConsume Specifies whether nicrosil consumption is enabled.
     * @param metal The metal being charged.
     *
     * @return The updated compound tag with the first metal charge applied.
     *
     */
    @Override
    public CompoundTag calculateFirstMetalCharge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, boolean nicConsume, MetalTagEnum metal) {
        ModEffects.giveFeruchemicalStorageEffect(player, metal);
        compoundTag.putInt(metal.getNameLower() + "_feruchemic_reserve", 2);
        return compoundTag;
    }

    /**
     * This is responsible for modifying the internal reserve of the metalmind, to show that the power is off.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param metalKey metal key to be modified.
     *
     * @return CompoundTag metalmind information update.
     */
    public static CompoundTag turnOffPower(CompoundTag compoundTag, String metalKey) {
        compoundTag.putInt(metalKey, 3);
        return compoundTag;
    }

}
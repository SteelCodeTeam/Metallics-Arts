package net.rudahee.metallics_arts.modules.custom_items.metal_minds;


import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Class that specifies the lerasium and ettmetal band, we pass to the abstract class (which has the behavior) the metals that compose it,
 * in this case, they do not maintain a metal alloy order, but the first known order and then the next, along with their corresponding suppliers.
 *
 * @author SteelCode Team
 * @since 1.6.8
 *
 */
public class LerasiumEttmetalMetalmind extends MetalmindAbstract {

    /**
     * Default constructor, it is important to send the metals by parameter in the correct order, metal and its alloy.
     *
     * @param properties of the item.
     */
    public LerasiumEttmetalMetalmind(Item.Properties properties, MetalmindType type) {
        super(properties, MetalTagEnum.LERASIUM, MetalTagEnum.ETTMETAL, type);
    }

    /**
     * This method uses the internal information of the item to generate add the own Tooltips of the ring, for example, owner, and amount of current reservations.
     * <p>
     * This is a specific specification of the method for this particular item, since it has certain modifications with respect to the generic of the abstract class, it differs in that:
     * <p>
     * - The Lerasium reserves store the player's Allomantic reserves, therefore this specific method displays item information that reflects the current amounts of metals it stores.
     *
     * @param stack item that is being observed with the mouse over it.
     * @param level minecraft world you are in.
     * @param toolTips tooltips of the basic item, to which new information will be added.
     * @param flagIn flag that indicates if the tooltip information is normal or advanced, not used for mod item information.
     */
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> toolTips, TooltipFlag flagIn) {
        if (stack.hasTag()) {
            if (stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")>0) {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": ").append(Component.translatable("metallics_arts.metal_mind_translate.has_reserve")));
            } else {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": ").append(Component.translatable("metallics_arts.metal_mind_translate.not_has_reserve")));
            }
            if (!Screen.hasShiftDown()) {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")/41)));
                if (level != null) {
                    toolTips.add(Component.translatable("metallics_arts.metal_mind.owner").append(": "+ ((stack.getTag().getString("key").equals("Nobody")) ? Component.translatable("metallics_arts.metal_mind.nobody").getString() : (level.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))) == null) ? Component.translatable("metallics_arts.metal_mind.owner_someone") : level.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))).getName().getString())));
                }
                toolTips.add(Component.translatable(" "));
                toolTips.add(Component.translatable("metallics_arts.metal_mind_translate.shift_info").withStyle(ChatFormatting.BLUE));

            } else {
                int maxReserve = this.isBand(MetalmindType.BAND) ? this.getMetals(1).getMaxReserveBand() : this.getMetals(1).getMaxReserveRing();
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+ ((stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") * 100)/maxReserve)+"%"));
                if (level != null) {
                    toolTips.add(Component.translatable("metallics_arts.metal_mind.owner").append(": "+ ((stack.getTag().getString("key").equals("Nobody")) ? Component.translatable("metallics_arts.metal_mind.nobody").getString() : (level.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))) == null) ? Component.translatable("metallics_arts.metal_mind.owner_someone") : level.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))).getName().getString())));
                }
                toolTips.add(Component.translatable("-------------------"));
                for (MetalTagEnum metal : MetalTagEnum.values()) {
                    if(stack.getTag().getInt(metal.getNameLower()+"inLerasiumBand")>0) {
                        toolTips.add(Component.translatable(" * ").append(Component.translatable("metallics_arts.metal_translate."+metal.getNameLower())).append(": "+stack.getTag().getInt(metal.getNameLower()+"inLerasiumBand")));
                    }
                }
            }

        }
        //super.appendHoverText(stack, level, toolTips, flagIn);
    }


    /**
     * Redefine of the method of the MetalmindAbstract class.
     * <p>
     * In this specific case, removes the basic interaction of nicrosil and the discharge logic is added that returns Allomantic reserves to the player

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
        if (allLerasiumReservesEmpty(compoundTag)) {
            compoundTag.putInt(metal.getNameLower() + "_feruchemic_reserve",0);
        } else {
            compoundTag.putInt(metal.getNameLower() + "_feruchemic_reserve",1);
        }
        return loadAllomanticReserve(playerCapability, compoundTag);
    }

    /**
     * Redefine of the method of the MetalmindAbstract class.
     * <p>
     * In this specific case, removes the basic interaction of nicrosil and the charge logic is remove Allomantic reserves to the player.
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
        if (hasAllReservesLerasiumFull(compoundTag)) {
            compoundTag.putInt(metal.getNameLower() + "_feruchemic_reserve",2);
        } else if (havePlayerAnyReserve(playerCapability)) {
            compoundTag = saveAllomanticReserve(playerCapability, compoundTag);
            compoundTag.putInt(metal.getNameLower() + "_feruchemic_reserve",1);
        }
        return compoundTag;
    }

    /**
     * Checks if all feruchemic reserves for metals in a Lerasium metalmind are at their maximum capacity.
     *
     * @param compoundTag The compound tag containing feruchemic reserve information.
     *
     * @return True if all reserves are at their maximum capacity, false otherwise.
     */
    private boolean hasAllReservesLerasiumFull(CompoundTag compoundTag) {
        for (MetalTagEnum metal : MetalTagEnum.values()) {
            if (!compoundTag.contains(metal.getNameLower()+"inLerasiumBand")) { //no existe el tag
                compoundTag.putInt(metal.getNameLower()+"inLerasiumBand",0);
            }
            if (compoundTag.getInt(metal.getNameLower()+"inLerasiumBand") < metal.getMaxAllomanticTicksStorage() * 2) {
                return false;
            }
        }
        return true;
    }
    /**
     * Checks if all feruchemic reserves for metals in a Lerasium metalmind are empty (have a value of 0).
     *
     * @param compoundTag The compound tag containing feruchemic reserve information.
     *
     * @return True if all reserves are empty, false otherwise.
     */
    private boolean allLerasiumReservesEmpty(CompoundTag compoundTag) {
        for (MetalTagEnum metal : MetalTagEnum.values()) {
            if (!compoundTag.contains(metal.getNameLower()+"inLerasiumBand")) {
                compoundTag.putInt(metal.getNameLower()+"inLerasiumBand",0);
            }
            if (compoundTag.getInt(metal.getNameLower()+"inLerasiumBand") > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns if target player has any allomantic reserves.
     *
     * @param playerCapability capabilities (data) of the player.
     * @return Boolean
     */
    private boolean havePlayerAnyReserve (IInvestedPlayerData playerCapability) {
        for (MetalTagEnum metal: MetalTagEnum.values()) {
            if (playerCapability.getAllomanticAmount(metal)>0) {
                return true;
            }
        }
        return false;
    }

    /**
     * This is a unique method in the helpers, that is in charge of storing allomantic reserves in the metalmind and eliminating them from the target player.
     *
     * @param playerCapability capabilities (data) of the player.
     * @param compoundTag the inside information of the metalmind.
     *
     * @return CompoundTag metalmind information update.
     */
    private CompoundTag saveAllomanticReserve(IInvestedPlayerData playerCapability, CompoundTag compoundTag) {
        ArrayList<MetalTagEnum> metals = playerCapability.getAllomanticPowers();

        for (MetalTagEnum metal : metals) {
            int reserveInBand = compoundTag.getInt(metal.getNameLower()+"inLerasiumBand");
            if (playerCapability.hasAllomanticAmountOf(metal) && reserveInBand < (metal.getMaxAllomanticTicksStorage() * 2)) {
                if (playerCapability.getAllomanticAmount(metal) <= ((metal.getMaxAllomanticTicksStorage() * 2) - reserveInBand)) {
                    compoundTag.putInt(metal.getNameLower() +"inLerasiumBand", reserveInBand + playerCapability.getAllomanticAmount(metal));
                    playerCapability.setAllomanticMetalsAmount(metal, 0);
                } else {
                    compoundTag.putInt(metal.getNameLower() +"inLerasiumBand", (metal.getMaxAllomanticTicksStorage() * 2));
                    playerCapability.setAllomanticMetalsAmount(metal, playerCapability.getAllomanticAmount(metal) -
                            ((metal.getMaxAllomanticTicksStorage() * 2) - reserveInBand));
                }
            }
        }
        return compoundTag;
    }

    /**
     * This is a unique method in the helpers, which is in charge of recover allomantic reserves the metalmind and return them to the target player.
     *
     * @param playerCapability capabilities (data) of the player.
     * @param compoundTag the inside information of the metalmind.
     * @return CompoundTag metalmind information update.
     */
    private CompoundTag loadAllomanticReserve(IInvestedPlayerData playerCapability, CompoundTag compoundTag) {
        //vuelve a cargar el jugador
        ArrayList<MetalTagEnum> metals = playerCapability.getAllomanticPowers();
        //todo testear que no explote
        for (MetalTagEnum metal : metals) {
            int reserveInBand = compoundTag.getInt(metal.getNameLower()+"inLerasiumBand");
            if (playerCapability.getAllomanticAmount(metal) < metal.getMaxAllomanticTicksStorage() && reserveInBand > 0) {
                if ((metal.getMaxAllomanticTicksStorage() - playerCapability.getAllomanticAmount(metal)) >= reserveInBand) {
                    playerCapability.setAllomanticMetalsAmount(metal, reserveInBand);
                    compoundTag.putInt(metal.getNameLower()+"inLerasiumBand",0);
                } else {
                    playerCapability.setAllomanticMetalsAmount(metal, metal.getMaxAllomanticTicksStorage());
                    compoundTag.putInt(metal.getNameLower()+"inLerasiumBand", reserveInBand - (metal.getMaxAllomanticTicksStorage() - playerCapability.getAllomanticAmount(metal)));
                }
            }
        }
        return compoundTag;
    }
}
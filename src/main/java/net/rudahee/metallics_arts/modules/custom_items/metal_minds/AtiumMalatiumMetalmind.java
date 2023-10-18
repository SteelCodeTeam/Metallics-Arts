package net.rudahee.metallics_arts.modules.custom_items.metal_minds;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.custom_tiers.CustomMaterials;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalmindType;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.abstracts.MetalmindAbstract;
import net.rudahee.metallics_arts.modules.effects.ModEffects;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

/**
 * Class that specifies the atium and malatium band, we pass to the abstract class (which has the behavior) the metals that compose it,
 * in the order: metal and its alloy, along with their corresponding suppliers.
 *
 * @author SteelCode Team
 * @since 1.6.8
 *
 */
public class AtiumMalatiumMetalmind extends MetalmindAbstract {

    /**
     * Default constructor, it is important to send the metals by parameter in the correct order, metal and its alloy.
     *
     * @param properties of the item.
     */
    public AtiumMalatiumMetalmind(Properties properties, MetalmindType type) {
        super(properties, MetalTagEnum.ATIUM, MetalTagEnum.MALATIUM, type);
    }

    /**
     * This method uses the internal information of the item to generate add the own Tooltips of the ring, for example, owner, and amount of current reservations.
     * <p>
     * This is a specific specification of the method for this particular item, since it has certain modifications with respect to the generic of the abstract class, it differs in that:
     * <p>
     * - The Malatium has its own reserve storage since it stores the number of item uses, not times, as the text of the generic method shows.
     *
     * @param stack item that is being observed with the mouse over it.
     * @param level minecraft world you are in.
     * @param toolTips tooltips of the basic item, to which new information will be added.
     * @param flagIn flag that indicates if the tooltip information is normal or advanced, not used for mod item information.
     *
     */
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> toolTips, TooltipFlag flagIn) {
        if (stack.hasTag()) {
            if (!stack.getTag().contains("tier_malatium_storage") || stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") == 0) {
                stack.getTag().putInt("tier_malatium_storage",-1);
            }
            if (!Screen.hasShiftDown()) {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": "+ stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") / 20 + "s"));
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+ stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")+" ").append(Component.translatable("metallics_arts.metal_mind_translate.uses")));

            } else {
                int maxReserve = this.isBand(MetalmindType.BAND) ? this.getMetals(0).getMaxReserveBand() : this.getMetals(0).getMaxReserveRing();
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": "+ ((stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") * 100)/maxReserve)+"%"));
                maxReserve = this.isBand(MetalmindType.BAND) ? this.getMetals(1).getMaxReserveBand() : this.getMetals(1).getMaxReserveRing();
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+ ((stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") * 100)/maxReserve)+"%"));
            }
            if (level != null) {
                toolTips.add(Component.translatable("metallics_arts.metal_mind.owner").append(": "+ ((stack.getTag().getString("key").equals("Nobody")) ? Component.translatable("metallics_arts.metal_mind.nobody").getString() : (level.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))) == null) ? Component.translatable("metallics_arts.metal_mind.owner_someone") : level.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))).getName().getString())));
            }
            if (stack.getTag().getInt("tier_malatium_storage")!=-1) {
                toolTips.add(Component.translatable("-------------------"));
                toolTips.add(Component.translatable("Tier: "+ convertTierToMaterial(stack.getTag().getInt("tier_malatium_storage"))));
            }
            if (!Screen.hasShiftDown()) {
                toolTips.add(Component.literal(" "));
                toolTips.add(Component.translatable("metallics_arts.metal_mind_translate.shift_info").withStyle(ChatFormatting.BLUE));
            }
        }
    }




    /**
     * Redefine of the method of the MetalmindAbstract class.
     * <p>
     * In this specific case, performs the discharge only when the item in the user's hand is of the same tier that the metal mind has in reserve.
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
    public CompoundTag calculateSecondMetalDischarge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, boolean nicConsume, MetalTagEnum metal) {
        ModEffects.giveFeruchemicalTapEffect(player, metal);
        if (isDecanting(player,compoundTag)) {
            compoundTag.putInt(metal.getNameLower() + "_feruchemic_reserve", metalReserve - 1);
        }
        if (compoundTag.getInt(metal.getNameLower() + "_feruchemic_reserve") == 0) {
            compoundTag.putInt("tier_malatium_storage",-1);
        }
        return compoundTag;
    }
    /**
     * Redefine of the method of the MetalmindAbstract class.
     * <p>
     * In this specific case, performs the charge only when the item in the user's hand is of the same tier that the metal mind has in reserve or the metal mind does not have a tier assigned.
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
    public CompoundTag calculateSecondMetalCharge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, boolean nicConsume, MetalTagEnum metal) {
        ModEffects.giveFeruchemicalStorageEffect(player, metal);
        if (isStoring(player, compoundTag)) {
            compoundTag.putInt(metal.getNameLower() + "_feruchemic_reserve", metalReserve + 1);
        }
        return compoundTag;
    }


    /**
     * Repairs the durability of weapons and armor that target player has in hand.
     *
     * @param player to whom the effect will be applied.
     * @param compoundTag metalmind information update.
     *
     * @return If the weapon or armor was repaired it returns true, otherwise false
     */
    private boolean isDecanting(Player player, CompoundTag compoundTag) {
        if (player.getMainHandItem().getItem() instanceof TieredItem tiered) {
            if (tiered.getTier().getLevel() == compoundTag.getInt("tier_malatium_storage")) {
                if (player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue() == 0) {
                    return false;
                }
                player.getItemInHand(InteractionHand.MAIN_HAND).setDamageValue(player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue()-1);
                return true;
            }
            return false; //el item no es del tier de la primer carga de la mente
        } else if (player.getMainHandItem().getItem() instanceof ArmorItem armorItem) {
            int tier = convertMaterialToTier(armorItem.getMaterial());
            if (tier == compoundTag.getInt("tier_malatium_storage")) {
                if (player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue() == 0) {
                    return false;
                }
                player.getItemInHand(InteractionHand.MAIN_HAND).setDamageValue(player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue()-1);
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Removes the durability of weapons and armor the target player has in hand.
     *
     * @param player to whom the effect will be applied.
     * @param compoundTag metalmind information update.
     * @return If durability was consumed from the weapon or armor returns true, otherwise false
     */
    private boolean isStoring (Player player, CompoundTag compoundTag) {
        if (!compoundTag.contains("tier_malatium_storage")) {
            compoundTag.putInt("tier_malatium_storage",-1);
        }
        if (compoundTag.getInt("tier_malatium_storage") == -1) {
            generateIternalReserve(player, compoundTag);
        }

        if (player.getMainHandItem().getItem() instanceof TieredItem tiered) {
            if (tiered.getTier().getLevel() == compoundTag.getInt("tier_malatium_storage")) {
                if (player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue() == player.getItemInHand(InteractionHand.MAIN_HAND).getMaxDamage()) {
                    player.setItemInHand(InteractionHand.MAIN_HAND,ItemStack.EMPTY);
                    player.level.playLocalSound(player.getX(),player.getY(),player.getZ(), SoundEvents.ITEM_BREAK, SoundSource.NEUTRAL,1.0f, 2.0f, true);
                    return false;
                }
                player.getItemInHand(InteractionHand.MAIN_HAND).setDamageValue(player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue()+1);
                return true;
            }
            return false; //el item no es del tier de la primer carga de la mente
        } else if (player.getMainHandItem().getItem() instanceof ArmorItem armorItem) {
            int tier = convertMaterialToTier(armorItem.getMaterial());
            if (tier == compoundTag.getInt("tier_malatium_storage")) {
                if (player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue() == player.getItemInHand(InteractionHand.MAIN_HAND).getMaxDamage()) {
                    player.setItemInHand(InteractionHand.MAIN_HAND,ItemStack.EMPTY);
                    return false;
                }
                player.getItemInHand(InteractionHand.MAIN_HAND).setDamageValue(player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue()+1);
                return true;
            }
        }
        return false;
    }

    /**
     * Modify the information of the metal mind to assign it the corresponding tier.
     *
     * @param player      to whom the effect will be applied.
     * @param compoundTag metalmind information update.
     */
    private void generateIternalReserve (Player player, CompoundTag compoundTag) {
        if (player.getMainHandItem().getItem() instanceof TieredItem tiered) {
            compoundTag.putInt("tier_malatium_storage",tiered.getTier().getLevel());
        }
        if (player.getMainHandItem().getItem() instanceof ArmorItem armorItem) {
            compoundTag.putInt("tier_malatium_storage",convertMaterialToTier(armorItem.getMaterial()));
        }
    }

    /**
     * Recovers the material tier.
     *
     * @param material string name
     * @return int value tier
     */
    private static int convertMaterialToTier (ArmorMaterial material) {

        if (material.equals(ArmorMaterials.GOLD) || material.equals(ArmorMaterials.LEATHER)) {
            return 0;
        } else if (material.equals(ArmorMaterials.TURTLE)) {
            return 1;
        } else if (material.equals(ArmorMaterials.IRON) || material.equals(ArmorMaterials.CHAIN)) {
            return 2;
        } else if (material.equals(ArmorMaterials.DIAMOND)) {
            return 3;
        } else if (material.equals(ArmorMaterials.NETHERITE)) {
            return 4;
        } else if (material.getName().equals(CustomMaterials.STEEL.getName()) || material.getName().equals(CustomMaterials.ALUMINUM.getName())) {
            return 6;
        }
        return -1;
    }

    /**
     * Retrieve the name of the tier.
     *
     * @param tier value of tier
     * @return String with the name of tier
     */
    private static String convertTierToMaterial (int tier) {
        if (tier == 0) {
            return Tiers.GOLD.name()+" "+ArmorMaterials.LEATHER.getName().toUpperCase();
        } else if (tier == 1) {
            return ArmorMaterials.TURTLE.getName();
        } else if (tier == 2) {
            return Tiers.IRON.name()+" "+ArmorMaterials.CHAIN.getName().toUpperCase();
        } else if (tier == 3) {
            return Tiers.DIAMOND.name();
        } else if (tier == 4) {
            return Tiers.NETHERITE.name();
        } else if (tier == 6) {
            return "post_netherite";
        }
        return "";
    }



}
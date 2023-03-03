package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.god_metals;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;

import java.util.function.Supplier;


/**
 * Helper class containing the methods and implementations for using feruchemical Malatium.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class MalatiumFecuchemicHelper extends AbstractFechuchemicHelper {

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * This method is not used, because the power logic is applied in the discharge methods of this class.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#tappingPower(Player)
     */
    @Override
    public void tappingPower(Player player) {}
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * This method is not used, because the power logic is applied in the charge methods of this class.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#storagePower(Player)
     */
    @Override
    public void storagePower(Player player) {
    }

    //todo
    public static Supplier<? extends MalatiumFecuchemicHelper> getInstance() {
        return MalatiumFecuchemicHelper::new;
    }

    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * <p>
     * In this specific case, removes interaction with nicrosil and performs the discharge only when the item in the user's hand is of the same tier that the metal mind has in reserve.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param player with the mindmetal equipped.
     * @param playerCapability capabilities (data) of the player.
     * @param metalReserve present value of the metal reserve.
     * @param metalKey metal key to be modified.
     * @param nicConsume control value of whether it is necessary to store charge or not.
     * @return CompoundTag metalmind information update.
     *
     * @see AbstractFechuchemicHelper#tappingPower(Player)
     */
    @Override
    public CompoundTag calculateDischarge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        if (isDecanting(player,compoundTag)) {
            compoundTag.putInt(metalKey, metalReserve - 1);
        }
        if (compoundTag.getInt(metalKey) == 0) {
            compoundTag.putInt("tier_malatium_storage",-1);
        }
        return compoundTag;
    }

    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * <p>
     * In this specific case, removes interaction with nicrosil and performs the charge only when the item in the user's hand is of the same tier that the metal mind has in reserve or the metal mind does not have a tier assigned.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param player with the mindmetal equipped.
     * @param playerCapability capabilities (data) of the player.
     * @param metalReserve present value of the metal reserve.
     * @param metalKey metal key to be modified.
     * @param nicConsume control value of whether it is necessary to store charge or not.
     * @return CompoundTag metalmind information update.
     *
     * @see AbstractFechuchemicHelper#tappingPower(Player)
     */
    @Override
    public CompoundTag calculateCharge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        if (isStoring(player,compoundTag)) {
            compoundTag.putInt(metalKey, metalReserve + 1);
        }
        return compoundTag;
    }

    /**
     * Repairs the durability of weapons and armor that target player has in hand.
     *
     * @param player to whom the effect will be applied.
     * @param compoundTag metalmind information update.
     * @return If the weapon or armor was repaired it returns true, otherwise false
     */
    public boolean isDecanting(Player player, CompoundTag compoundTag) {
        if (player.getMainHandItem().getItem() instanceof TieredItem tiered) {
            if (tiered.getTier().getLevel() == compoundTag.getInt("tier_malatium_storage")){
                if (player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue() == 0){
                    return false;
                }
                player.getItemInHand(InteractionHand.MAIN_HAND).setDamageValue(player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue()-1);
                return true;
            }
            return false; //el item no es del tier de la primer carga de la mente
        } else if (player.getMainHandItem().getItem() instanceof ArmorItem armorItem) {
            int tier = convertMaterialToTier(armorItem.getMaterial().getName());
            if (tier == compoundTag.getInt("tier_malatium_storage")){
                if (player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue() == 0){
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
    public boolean isStoring (Player player, CompoundTag compoundTag){
        if (!compoundTag.contains("tier_malatium_storage")){
            compoundTag.putInt("tier_malatium_storage",-1);
        }
        if (compoundTag.getInt("tier_malatium_storage") == -1){
            compoundTag = generateIternalReserve(player, compoundTag);
        }

        if (player.getMainHandItem().getItem() instanceof TieredItem tiered) {
            if (tiered.getTier().getLevel() == compoundTag.getInt("tier_malatium_storage")){
                if (player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue() == player.getItemInHand(InteractionHand.MAIN_HAND).getMaxDamage()){
                    player.setItemInHand(InteractionHand.MAIN_HAND,ItemStack.EMPTY);
                    player.level.playLocalSound(player.getX(),player.getY(),player.getZ(), SoundEvents.ITEM_BREAK, SoundSource.NEUTRAL,1.0f, 2.0f, true);
                    return false;
                }
                player.getItemInHand(InteractionHand.MAIN_HAND).setDamageValue(player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue()+1);
                return true;
            }
            return false; //el item no es del tier de la primer carga de la mente
        } else if (player.getMainHandItem().getItem() instanceof ArmorItem armorItem) {
            int tier = convertMaterialToTier(armorItem.getMaterial().getName());
            if (tier == compoundTag.getInt("tier_malatium_storage")){
                if (player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue() == player.getItemInHand(InteractionHand.MAIN_HAND).getMaxDamage()){
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
     * @param player to whom the effect will be applied.
     * @param compoundTag metalmind information update.
     * @return CompoundTag metalmind information update.
     */
    public CompoundTag generateIternalReserve (Player player, CompoundTag compoundTag){
        if (player.getMainHandItem().getItem() instanceof TieredItem tiered) {
            compoundTag.putInt("tier_malatium_storage",tiered.getTier().getLevel());
        }
        if (player.getMainHandItem().getItem() instanceof ArmorItem armorItem){
            compoundTag.putInt("tier_malatium_storage",convertMaterialToTier(armorItem.getMaterial().getName()));
        }
        return  compoundTag;
    }

    /**
     * Recovers the material tier.
     *
     * @param material string name
     * @return int value tier
     */
    public static int convertMaterialToTier (String material) {

        if (material.equals(ArmorMaterials.GOLD.getName()) || material.equals(ArmorMaterials.LEATHER.getName())) {
            return 0;
        } else if (material.equals(ArmorMaterials.TURTLE.getName())){
            return 1;
        } else if (material.equals(ArmorMaterials.IRON.getName()) || material.equals(ArmorMaterials.CHAIN.getName())) {
            return 2;
        } else if (material.equals(ArmorMaterials.DIAMOND.getName())) {
            return 3;
        } else if (material.equals(ArmorMaterials.NETHERITE.getName())) {
            return 4;
        } else if (material.equals("Obsidian")) {
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
    public static String convertTierToMaterial (int tier) {
        if (tier == 0){
            return Tiers.GOLD.name()+" "+ArmorMaterials.LEATHER.getName().toUpperCase();
        } else if (tier == 1){
            return ArmorMaterials.TURTLE.getName();
        } else if (tier == 2){
            return Tiers.IRON.name()+" "+ArmorMaterials.CHAIN.getName().toUpperCase();
        } else if (tier == 3){
            return Tiers.DIAMOND.name();
        } else if (tier == 4){
            return Tiers.NETHERITE.name();
        } else if (tier == 6) {
            return "Obsidian";
        }
        return "";
    }

}

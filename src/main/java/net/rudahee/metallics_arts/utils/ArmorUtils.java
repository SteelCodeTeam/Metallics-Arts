package net.rudahee.metallics_arts.utils;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.ArmorPiecesEnum;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

public class ArmorUtils {

    /**
     * Checks if a player is wearing a complete set of Ettmetal armor.
     *
     * @param livingEntity The livingEntity to check for Ettmetal armor.
     * @return {@code true} if the player is wearing a complete set of Ettmetal armor, {@code false} otherwise.
     */
    public static boolean hasEttmetalArmor(LivingEntity livingEntity) {

        NonNullList<ItemStack> list = (NonNullList<ItemStack>) livingEntity.getArmorSlots();
        return (list.get(0).getItem().equals(ModItemsRegister.ETTMETAL_ARMOR.get(ArmorPiecesEnum.BOOTS).get()) &&
                list.get(1).getItem().equals(ModItemsRegister.ETTMETAL_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get()) &&
                list.get(2).getItem().equals(ModItemsRegister.ETTMETAL_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get()) &&
                list.get(3).getItem().equals(ModItemsRegister.ETTMETAL_ARMOR.get(ArmorPiecesEnum.HELMET).get()));

    }
    /**
     * Checks if a player is wearing a complete set of Aluminum armor.
     *
     * @param livingEntity The livingEntity to check for Aluminum armor.
     * @return {@code true} if the player is wearing a complete set of Aluminum armor, {@code false} otherwise.
     */
    public static boolean hasAluminumArmor(LivingEntity livingEntity) {

        NonNullList<ItemStack> list = (NonNullList<ItemStack>) livingEntity.getArmorSlots();
        return (list.get(0).getItem().equals(ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.BOOTS).get()) &&
                list.get(1).getItem().equals(ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get()) &&
                list.get(2).getItem().equals(ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get()) &&
                list.get(3).getItem().equals(ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.HELMET).get()));

    }

    /**
     * Checks if a player is wearing a complete set of Atium armor.
     *
     * @param livingEntity The livingEntity to check for Atium armor.
     * @return {@code true} if the player is wearing a complete set of Atium armor, {@code false} otherwise.
     */
    public static boolean hasAtiumArmor(LivingEntity livingEntity) {
        NonNullList<ItemStack> list = (NonNullList<ItemStack>) livingEntity.getArmorSlots();
        return list.get(0).getItem().equals(ModItemsRegister.ATIUM_ARMOR.get(ArmorPiecesEnum.BOOTS).get()) &&
                list.get(1).getItem().equals(ModItemsRegister.ATIUM_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get()) &&
                list.get(2).getItem().equals(ModItemsRegister.ATIUM_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get()) &&
                list.get(3).getItem().equals(ModItemsRegister.ATIUM_ARMOR.get(ArmorPiecesEnum.HELMET).get());
    }

    public static boolean cancelHitWithAtiumArmor() {
        return Math.random() < 0.1;
    }


    /**
     * Checks if a player is wearing a complete set of Lerasium armor.
     *
     * @param player The player to check for Lerasium armor.
     *
     * @return {@code true} if the player is wearing a complete set of Lerasium armor, {@code false} otherwise.
     */
    public static boolean hasLerasiumArmor(Player player) {
        NonNullList<ItemStack> list = player.getInventory().armor;
        return list.get(0).getItem().equals(ModItemsRegister.LERASIUM_ARMOR.get(ArmorPiecesEnum.BOOTS).get()) &&
                list.get(1).getItem().equals(ModItemsRegister.LERASIUM_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get()) &&
                list.get(2).getItem().equals(ModItemsRegister.LERASIUM_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get()) &&
                list.get(3).getItem().equals(ModItemsRegister.LERASIUM_ARMOR.get(ArmorPiecesEnum.HELMET).get());
    }

    /**
     * Checks if a player is wearing a complete set of Copper armor.
     *
     * @param player The player to check for Copper armor.
     * @return {@code true} if the player is wearing a complete set of Copper armor, {@code false} otherwise.
     */
    public static boolean hasCopperArmor(Player player) {
        NonNullList<ItemStack> list = player.getInventory().armor;
        return list.get(0).getItem().equals(ModItemsRegister.COPPER_ARMOR.get(ArmorPiecesEnum.BOOTS).get()) &&
                list.get(1).getItem().equals(ModItemsRegister.COPPER_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get()) &&
                list.get(2).getItem().equals(ModItemsRegister.COPPER_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get()) &&
                list.get(3).getItem().equals(ModItemsRegister.COPPER_ARMOR.get(ArmorPiecesEnum.HELMET).get());
    }
}

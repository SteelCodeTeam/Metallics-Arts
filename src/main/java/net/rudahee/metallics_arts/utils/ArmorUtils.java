package net.rudahee.metallics_arts.utils;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.ArmorPiecesEnum;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

public class ArmorUtils {
    public static boolean hasEttmetalArmor(Player player) {
        NonNullList<ItemStack> list = player.getInventory().armor;
        return (list.get(0).getItem().equals(ModItemsRegister.ETTMETAL_ARMOR.get(ArmorPiecesEnum.BOOTS).get()) &&
                list.get(1).getItem().equals(ModItemsRegister.ETTMETAL_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get()) &&
                list.get(2).getItem().equals(ModItemsRegister.ETTMETAL_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get()) &&
                list.get(3).getItem().equals(ModItemsRegister.ETTMETAL_ARMOR.get(ArmorPiecesEnum.HELMET).get()));

    }

    public static boolean hasAtiumArmor(Player player) {
        NonNullList<ItemStack> list = player.getInventory().armor;
        return list.get(0).getItem().equals(ModItemsRegister.ATIUM_ARMOR.get(ArmorPiecesEnum.BOOTS).get()) &&
                list.get(1).getItem().equals(ModItemsRegister.ATIUM_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get()) &&
                list.get(2).getItem().equals(ModItemsRegister.ATIUM_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get()) &&
                list.get(3).getItem().equals(ModItemsRegister.ATIUM_ARMOR.get(ArmorPiecesEnum.HELMET).get());
    }

    public static boolean cancelHitWithAtiumArmor() {
        return Math.random() < 0.1;
    }

    public static boolean hasLerasiumArmor(Player player) {
        NonNullList<ItemStack> list = player.getInventory().armor;
        return list.get(0).getItem().equals(ModItemsRegister.LERASIUM_ARMOR.get(ArmorPiecesEnum.BOOTS).get()) &&
                list.get(1).getItem().equals(ModItemsRegister.LERASIUM_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get()) &&
                list.get(2).getItem().equals(ModItemsRegister.LERASIUM_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get()) &&
                list.get(3).getItem().equals(ModItemsRegister.LERASIUM_ARMOR.get(ArmorPiecesEnum.HELMET).get());
    }
}

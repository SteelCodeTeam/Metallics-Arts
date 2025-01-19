package net.rudahee.metallics_arts.setup.registries.items;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.custom_tiers.CustomMaterials;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.ArmorPiecesEnum;
import net.rudahee.metallics_arts.modules.custom_items.armors.lerasium.LerasiumArmor;
import net.rudahee.metallics_arts.modules.custom_items.armors.mistcloack.MistCloack;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

public class ArmorRegister {

    private static final Item.Properties PROPERTIES = new Item.Properties();

    public static void register() {
        RegistryObject<Item> steelHelmet = MetallicsArts.registerItem("steel_helmet",
                () -> new ArmorItem(CustomMaterials.STEEL, ArmorItem.Type.HELMET, PROPERTIES));
        ModItemsRegister.STEEL_ARMOR.put(ArmorPiecesEnum.HELMET, steelHelmet);

        RegistryObject<Item> steelChesplate = MetallicsArts.registerItem("steel_chestplate",
                () -> new ArmorItem(CustomMaterials.STEEL, ArmorItem.Type.CHESTPLATE, PROPERTIES));
        ModItemsRegister.STEEL_ARMOR.put(ArmorPiecesEnum.CHESTPLATE, steelChesplate);

        RegistryObject<Item> steelLeggings = MetallicsArts.registerItem("steel_leggings",
                () -> new ArmorItem(CustomMaterials.STEEL, ArmorItem.Type.LEGGINGS, PROPERTIES));
        ModItemsRegister.STEEL_ARMOR.put(ArmorPiecesEnum.LEGGINGS, steelLeggings);

        RegistryObject<Item> steelBoots = MetallicsArts.registerItem("steel_boots",
                () -> new ArmorItem(CustomMaterials.STEEL, ArmorItem.Type.BOOTS, PROPERTIES));
        ModItemsRegister.STEEL_ARMOR.put(ArmorPiecesEnum.BOOTS, steelBoots);

        RegistryObject<Item> aluminumHelmet = MetallicsArts.registerItem("aluminum_helmet",
                () -> new ArmorItem(CustomMaterials.ALUMINUM, ArmorItem.Type.HELMET, PROPERTIES));
        ModItemsRegister.ALUMINUM_ARMOR.put(ArmorPiecesEnum.HELMET, aluminumHelmet);

        RegistryObject<Item> aluminumChesplate = MetallicsArts.registerItem("aluminum_chestplate",
                () -> new ArmorItem(CustomMaterials.ALUMINUM, ArmorItem.Type.CHESTPLATE, PROPERTIES));
        ModItemsRegister.ALUMINUM_ARMOR.put(ArmorPiecesEnum.CHESTPLATE, aluminumChesplate);

        RegistryObject<Item> aluminumLeggings = MetallicsArts.registerItem("aluminum_leggings",
                () -> new ArmorItem(CustomMaterials.ALUMINUM, ArmorItem.Type.LEGGINGS, PROPERTIES));
        ModItemsRegister.ALUMINUM_ARMOR.put(ArmorPiecesEnum.LEGGINGS, aluminumLeggings);

        RegistryObject<Item> aluminumBoots = MetallicsArts.registerItem("aluminum_boots",
                () -> new ArmorItem(CustomMaterials.ALUMINUM, ArmorItem.Type.BOOTS, PROPERTIES));
        ModItemsRegister.ALUMINUM_ARMOR.put(ArmorPiecesEnum.BOOTS, aluminumBoots);


        RegistryObject<Item> ettmetalHelmet = MetallicsArts.registerItem("ettmetal_helmet",
                () -> new ArmorItem(CustomMaterials.ETTMETAL, ArmorItem.Type.HELMET, PROPERTIES));
        ModItemsRegister.ETTMETAL_ARMOR.put(ArmorPiecesEnum.HELMET, ettmetalHelmet);

        RegistryObject<Item> ettmetalChesplate = MetallicsArts.registerItem("ettmetal_chestplate",
                () -> new ArmorItem(CustomMaterials.ETTMETAL, ArmorItem.Type.CHESTPLATE, PROPERTIES));
        ModItemsRegister.ETTMETAL_ARMOR.put(ArmorPiecesEnum.CHESTPLATE, ettmetalChesplate);

        RegistryObject<Item> ettmetalLeggings = MetallicsArts.registerItem("ettmetal_leggings",
                () -> new ArmorItem(CustomMaterials.ETTMETAL, ArmorItem.Type.LEGGINGS, PROPERTIES));
        ModItemsRegister.ETTMETAL_ARMOR.put(ArmorPiecesEnum.LEGGINGS, ettmetalLeggings);

        RegistryObject<Item> ettmetalBoots = MetallicsArts.registerItem("ettmetal_boots",
                () -> new ArmorItem(CustomMaterials.ETTMETAL, ArmorItem.Type.BOOTS, PROPERTIES));
        ModItemsRegister.ETTMETAL_ARMOR.put(ArmorPiecesEnum.BOOTS, ettmetalBoots);


        RegistryObject<Item> lerasiumHelmet = MetallicsArts.registerItem("lerasium_helmet",
                () -> new LerasiumArmor(CustomMaterials.LERASIUM, ArmorItem.Type.HELMET, PROPERTIES));
        ModItemsRegister.LERASIUM_ARMOR.put(ArmorPiecesEnum.HELMET, lerasiumHelmet);

        RegistryObject<Item> lerasiumChesplate = MetallicsArts.registerItem("lerasium_chestplate",
                () -> new LerasiumArmor(CustomMaterials.LERASIUM, ArmorItem.Type.CHESTPLATE, PROPERTIES));
        ModItemsRegister.LERASIUM_ARMOR.put(ArmorPiecesEnum.CHESTPLATE, lerasiumChesplate);

        RegistryObject<Item> lerasiumLeggings = MetallicsArts.registerItem("lerasium_leggings",
                () -> new LerasiumArmor(CustomMaterials.LERASIUM, ArmorItem.Type.LEGGINGS, PROPERTIES));
        ModItemsRegister.LERASIUM_ARMOR.put(ArmorPiecesEnum.LEGGINGS, lerasiumLeggings);

        RegistryObject<Item> lerasiumBoots = MetallicsArts.registerItem("lerasium_boots",
                () -> new LerasiumArmor(CustomMaterials.LERASIUM, ArmorItem.Type.BOOTS, PROPERTIES));
        ModItemsRegister.LERASIUM_ARMOR.put(ArmorPiecesEnum.BOOTS, lerasiumBoots);



        RegistryObject<Item> atiumChesplate = MetallicsArts.registerItem("atium_chestplate",
                () -> new LerasiumArmor(CustomMaterials.ATIUM, ArmorItem.Type.CHESTPLATE, PROPERTIES));
        ModItemsRegister.ATIUM_ARMOR.put(ArmorPiecesEnum.CHESTPLATE, atiumChesplate);

        RegistryObject<Item> atiumLeggings = MetallicsArts.registerItem("atium_leggings",
                () -> new LerasiumArmor(CustomMaterials.ATIUM, ArmorItem.Type.LEGGINGS, PROPERTIES));
        ModItemsRegister.ATIUM_ARMOR.put(ArmorPiecesEnum.LEGGINGS, atiumLeggings);

        RegistryObject<Item> atiumBoots = MetallicsArts.registerItem("atium_boots",
                () -> new LerasiumArmor(CustomMaterials.ATIUM, ArmorItem.Type.BOOTS, PROPERTIES));
        ModItemsRegister.ATIUM_ARMOR.put(ArmorPiecesEnum.BOOTS, atiumBoots);

        RegistryObject<Item> atiumHelmet = MetallicsArts.registerItem("atium_helmet",
                () -> new LerasiumArmor(CustomMaterials.ATIUM, ArmorItem.Type.HELMET, PROPERTIES));
        ModItemsRegister.ATIUM_ARMOR.put(ArmorPiecesEnum.HELMET, atiumHelmet);




        RegistryObject<Item> copperHelmet = MetallicsArts.registerItem("copper_helmet",
                () -> new ArmorItem(CustomMaterials.COPPER, ArmorItem.Type.HELMET, PROPERTIES));
        ModItemsRegister.COPPER_ARMOR.put(ArmorPiecesEnum.HELMET, copperHelmet);

        RegistryObject<Item> copperChesplate = MetallicsArts.registerItem("copper_chestplate",
                () -> new ArmorItem(CustomMaterials.COPPER, ArmorItem.Type.CHESTPLATE, PROPERTIES));
        ModItemsRegister.COPPER_ARMOR.put(ArmorPiecesEnum.CHESTPLATE, copperChesplate);

        RegistryObject<Item> copperLeggings = MetallicsArts.registerItem("copper_leggings",
                () -> new ArmorItem(CustomMaterials.COPPER, ArmorItem.Type.LEGGINGS, PROPERTIES));
        ModItemsRegister.COPPER_ARMOR.put(ArmorPiecesEnum.LEGGINGS, copperLeggings);

        RegistryObject<Item> copperBoots = MetallicsArts.registerItem("copper_boots",
                () -> new ArmorItem(CustomMaterials.COPPER, ArmorItem.Type.BOOTS, PROPERTIES));
        ModItemsRegister.COPPER_ARMOR.put(ArmorPiecesEnum.BOOTS, copperBoots);


        ModItemsRegister.MISTCLOACK = MetallicsArts.registerItem("mistcloak",
                () -> new MistCloack(new Item.Properties().stacksTo(1)));

    }
}

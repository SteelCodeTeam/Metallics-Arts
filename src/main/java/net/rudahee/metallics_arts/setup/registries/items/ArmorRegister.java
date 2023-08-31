package net.rudahee.metallics_arts.setup.registries.items;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.custom_tiers.PostNetheriteMaterials;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.ArmorPiecesEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.Shields;
import net.rudahee.metallics_arts.modules.custom_items.armors.CustomShield;
import net.rudahee.metallics_arts.modules.custom_items.armors.MistCloak;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

public class ArmorRegister {

    private static final Item.Properties PROPERTIES = new Item.Properties();

    public static void register() {
        RegistryObject<Item> steelHelmet = MetallicsArts.registerItem("steel_helmet",
                () -> new ArmorItem(PostNetheriteMaterials.STEEL, ArmorItem.Type.HELMET, PROPERTIES));
        ModItemsRegister.STEEL_ARMOR.put(ArmorPiecesEnum.HELMET, steelHelmet);

        RegistryObject<Item> steelChesplate = MetallicsArts.registerItem("steel_chestplate",
                () -> new ArmorItem(PostNetheriteMaterials.STEEL, ArmorItem.Type.CHESTPLATE, PROPERTIES));
        ModItemsRegister.STEEL_ARMOR.put(ArmorPiecesEnum.CHESTPLATE, steelChesplate);

        RegistryObject<Item> steelLeggings = MetallicsArts.registerItem("steel_leggings",
                () -> new ArmorItem(PostNetheriteMaterials.STEEL, ArmorItem.Type.LEGGINGS, PROPERTIES));
        ModItemsRegister.STEEL_ARMOR.put(ArmorPiecesEnum.LEGGINGS, steelLeggings);

        RegistryObject<Item> steelBoots = MetallicsArts.registerItem("steel_boots",
                () -> new ArmorItem(PostNetheriteMaterials.STEEL, ArmorItem.Type.BOOTS, PROPERTIES));
        ModItemsRegister.STEEL_ARMOR.put(ArmorPiecesEnum.BOOTS, steelBoots);

        RegistryObject<Item> aluminumHelmet = MetallicsArts.registerItem("aluminum_helmet",
                () -> new ArmorItem(PostNetheriteMaterials.ALUMINUM, ArmorItem.Type.HELMET, PROPERTIES));
        ModItemsRegister.ALUMINUM_ARMOR.put(ArmorPiecesEnum.HELMET, aluminumHelmet);

        RegistryObject<Item> aluminumChesplate = MetallicsArts.registerItem("aluminum_chestplate",
                () -> new ArmorItem(PostNetheriteMaterials.ALUMINUM, ArmorItem.Type.CHESTPLATE, PROPERTIES));
        ModItemsRegister.ALUMINUM_ARMOR.put(ArmorPiecesEnum.CHESTPLATE, aluminumChesplate);

        RegistryObject<Item> aluminumLeggings = MetallicsArts.registerItem("aluminum_leggings",
                () -> new ArmorItem(PostNetheriteMaterials.ALUMINUM, ArmorItem.Type.LEGGINGS, PROPERTIES));
        ModItemsRegister.ALUMINUM_ARMOR.put(ArmorPiecesEnum.LEGGINGS, aluminumLeggings);

        RegistryObject<Item> aluminumBoots = MetallicsArts.registerItem("aluminum_boots",
                () -> new ArmorItem(PostNetheriteMaterials.ALUMINUM, ArmorItem.Type.BOOTS, PROPERTIES));
        ModItemsRegister.ALUMINUM_ARMOR.put(ArmorPiecesEnum.BOOTS, aluminumBoots);

        ModItemsRegister.MISTCLOACK = MetallicsArts.registerItem("mistcloak",
                () -> new MistCloak(new Item.Properties().stacksTo(1)));

    }
}

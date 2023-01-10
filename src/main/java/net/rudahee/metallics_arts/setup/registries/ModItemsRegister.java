package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.setup.registries.items.*;

import java.util.HashMap;


/**
 * Main registration of all items.
 *
 * @see BasicItemsRegister
 * @see IconsRegister
 * @see MetalMindsRegister
 * @see SpikesRegister
 * @see VialsRegister
 * @see WeaponsRegister
 */
public class ModItemsRegister {

    //Lists where we can store the items for a later usage
    public static final HashMap<String, Item> ITEM_METAL_INGOT = new HashMap<>();
    public static final HashMap<String, Item> ITEM_RAW_METAL = new HashMap<>();
    public static final HashMap<String, Item> ITEM_METAL_NUGGET = new HashMap<>();
    public static final HashMap<String, Item> ITEM_GEMS_BASE = new HashMap<>();
    public static final HashMap<String, Item> ITEM_GEMS_NUGGET = new HashMap<>();
    public static final HashMap<String, Item> ITEM_ICONS_ALLOMANCY = new HashMap<>();
    public static final HashMap<String, Item> ITEM_ICONS_FERUCHEMIC = new HashMap<>();
    public static final HashMap<String, Item> ITEM_ICONS_ALLOMANCY_DIVINE = new HashMap<>();
    public static final HashMap<String, Item> ITEM_ICONS_FERUCHEMIC_DIVINE = new HashMap<>();

    // Weapons
    public static RegistryObject<Item> OBSIDIAN_DAGGER;
    public static RegistryObject<Item> CRISTAL_DAGGER;
    public static RegistryObject<Item> KOLOSS_BLADE;
    public static RegistryObject<Item> DUELING_STAFF;
    public static RegistryObject<Item> OBSIDIAN_AXE;
    //Vials
    public static RegistryObject<Item> LARGE_VIAL;
    public static RegistryObject<Item> SMALL_VIAL;


    public static void register() {
        BasicItemsRegister.register();
        IconsRegister.register();
        MetalMindsRegister.register();
        SpikesRegister.register();
        WeaponsRegister.register();
        VialsRegister.register();
    }

}

package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.ArmorPiecesEnum;
import net.rudahee.metallics_arts.setup.registries.items.*;
import net.rudahee.metallics_arts.setup.registries.items.block_items.BasicBlockItemsRegister;

import java.util.HashMap;
import java.util.Map;


/**
 * Main registration of all items.
 *
 * @see BasicItemsRegister
 * @see IconsRegister
 * @see MetalMindsRegister
 * @see SpikesRegister
 * @see VialsRegister
 * @see WeaponsRegister
 *
 * @author SteelCode Team
 * @since 1.5.6
 */
public class ModItemsRegister {

    private ModItemsRegister() {
        throw new IllegalStateException("Class can't be instantiated");
    }

    //Lists where we can store the items for a later usage
    public static final Map<String, Item> ITEM_METAL_INGOT = new HashMap<>();
    public static final Map<String, Item> ITEM_RAW_METAL = new HashMap<>();
    public static final Map<String, Item> ITEM_METAL_NUGGET = new HashMap<>();
    public static final Map<String, Item> ITEM_GEMS_BASE = new HashMap<>();
    public static final Map<String, Item> ITEM_GEMS_NUGGET = new HashMap<>();
    public static final Map<String, Item> ITEM_ICONS_ALLOMANCY = new HashMap<>();
    public static final Map<String, Item> ITEM_ICONS_FERUCHEMIC = new HashMap<>();
    public static final Map<String, Item> ITEM_ICONS_ALLOMANCY_DIVINE = new HashMap<>();
    public static final Map<String, Item> ITEM_ICONS_FERUCHEMIC_DIVINE = new HashMap<>();
    public static final Map<String, Item> ITEM_CORES = new HashMap<>();
    public static final Map<String, Item> ITEM_MELE_WEAPON = new HashMap<>();
    public static final Map<String, Item> ITEM_RANGE_WEAPON = new HashMap<>();
    public static final Map<ArmorPiecesEnum, RegistryObject<Item>> STEEL_ARMOR = new HashMap<>();
    public static final Map<ArmorPiecesEnum, RegistryObject<Item>> ALUMINUM_ARMOR = new HashMap<>();
    public static final Map<ArmorPiecesEnum, RegistryObject<Item>> ETTMETAL_ARMOR = new HashMap<>();
    public static final Map<ArmorPiecesEnum, RegistryObject<Item>> LERASIUM_ARMOR = new HashMap<>();
    public static final Map<ArmorPiecesEnum, RegistryObject<Item>> ATIUM_ARMOR = new HashMap<>();
    public static final Map<ArmorPiecesEnum, RegistryObject<Item>> COPPER_ARMOR = new HashMap<>();
    public static final Map<String, RegistryObject<ForgeSpawnEggItem>> ENTITY_EGGS = new HashMap<>();


    // Weapons
    public static RegistryObject<Item> OBSIDIAN_DAGGER;
    public static RegistryObject<Item> SILVER_KNIFE;
    public static RegistryObject<Item> KOLOSS_BLADE;
    public static RegistryObject<Item> DUELING_STAFF;
    public static RegistryObject<Item> OBSIDIAN_AXE;
    // Shields
    public static RegistryObject<Item> WOOD_SHIELD;
    public static RegistryObject<Item> BRONZE_ALUMINUM_SHIELD;
    //Vials
    public static RegistryObject<Item> LARGE_VIAL;
    public static RegistryObject<Item> SMALL_VIAL;
    //book
    public static RegistryObject<Item> METALLICS_ARTS_BOOK;
    //cores
    public static RegistryObject<Item> OBSIDIAN_CORE;
    public static RegistryObject<Item> ALUMINUM_CORE;
    public static RegistryObject<Item> STEEL_CORE;
    public static RegistryObject<Item> ETTMETAL_CORE;
    public static RegistryObject<Item> LERASIUM_CORE;
    public static RegistryObject<Item> ATIUM_CORE;
    public static RegistryObject<Item> COPPER_CORE;
    //coins
    public static RegistryObject<Item> COPPER_COIN;
    public static RegistryObject<Item> BRONZE_COIN;
    //bullets
    public static RegistryObject<Item> REVOLVER_ALUMINUM_BULLET;
    public static RegistryObject<Item> REVOLVER_LEAD_BULLET;
    public static RegistryObject<Item> LEAD_BULLET_PROJECTILE;
    public static RegistryObject<Item> ALUMINUM_BULLET_PROJECTILE;
    public static RegistryObject<Item> SHOTGUN_ALUMINUM_BULLET;
    public static RegistryObject<Item> SHOTGUN_LEAD_BULLET;
    public static RegistryObject<Item> RIFLE_ALUMINUM_BULLET;
    public static RegistryObject<Item> RIFLE_LEAD_BULLET;
    //guns
    public static RegistryObject<Item> REVOLVER;
    public static RegistryObject<Item> RIFLE;
    public static RegistryObject<Item> RIFLE_WITH_SPYGLASS;
    public static RegistryObject<Item> SHOTGUN;
    public static RegistryObject<Item> VINDICATOR;
    public static RegistryObject<Item> MISTCLOACK;
    public static RegistryObject<Item> IRON_SIGN;
    public static RegistryObject<Item> GOLD_SIGN;
    public static RegistryObject<Item> COPPER_SIGN;
    public static RegistryObject<Item> ALUMINUM_SIGN;
    public static RegistryObject<Item> SAZED_DISC;

    public static RegistryObject<Item> HEMALURGY_ALTAR_BACK;
    public static RegistryObject<Item> HEMALURGY_ALTAR_FRONT;

    public static void register() {
        BasicItemsRegister.register();
        IconsRegister.register();
        MetalMindsRegister.register();
        SpikesRegister.register();
        WeaponsRegister.register();
        VialsRegister.register();
        CoreBuilderItemRegister.register();
        ArmorRegister.register();
        ModEntityEggsRegister.register();
        BasicBlockItemsRegister.register();
    }

    public static void addChargeInList() {

        ModItemsRegister.ITEM_MELE_WEAPON.put(ModItemsRegister.OBSIDIAN_DAGGER.toString(), ModItemsRegister.OBSIDIAN_DAGGER.get());
        ModItemsRegister.ITEM_MELE_WEAPON.put(ModItemsRegister.SILVER_KNIFE.toString(),ModItemsRegister.SILVER_KNIFE.get());
        ModItemsRegister.ITEM_MELE_WEAPON.put(ModItemsRegister.KOLOSS_BLADE.toString(),ModItemsRegister.KOLOSS_BLADE.get());
        ModItemsRegister.ITEM_MELE_WEAPON.put(ModItemsRegister.DUELING_STAFF.toString(),ModItemsRegister.DUELING_STAFF.get());
        ModItemsRegister.ITEM_MELE_WEAPON.put(ModItemsRegister.OBSIDIAN_AXE.toString(),ModItemsRegister.OBSIDIAN_AXE.get());
        ModItemsRegister.ITEM_MELE_WEAPON.put(ModItemsRegister.WOOD_SHIELD.toString(),ModItemsRegister.WOOD_SHIELD.get());
        ModItemsRegister.ITEM_MELE_WEAPON.put(ModItemsRegister.BRONZE_ALUMINUM_SHIELD.toString(),ModItemsRegister.BRONZE_ALUMINUM_SHIELD.get());

        ModItemsRegister.ITEM_RANGE_WEAPON.put(ModItemsRegister.COPPER_COIN.toString(),ModItemsRegister.COPPER_COIN.get());
        ModItemsRegister.ITEM_RANGE_WEAPON.put(ModItemsRegister.BRONZE_COIN.toString(),ModItemsRegister.BRONZE_COIN.get());
        ModItemsRegister.ITEM_RANGE_WEAPON.put(ModItemsRegister.REVOLVER.toString(),ModItemsRegister.REVOLVER.get());
        ModItemsRegister.ITEM_RANGE_WEAPON.put(ModItemsRegister.VINDICATOR.toString(),ModItemsRegister.VINDICATOR.get());
        ModItemsRegister.ITEM_RANGE_WEAPON.put(ModItemsRegister.SHOTGUN.toString(),ModItemsRegister.SHOTGUN.get());
        ModItemsRegister.ITEM_RANGE_WEAPON.put(ModItemsRegister.RIFLE.toString(),ModItemsRegister.RIFLE.get());
        ModItemsRegister.ITEM_RANGE_WEAPON.put(ModItemsRegister.RIFLE_WITH_SPYGLASS.toString(),ModItemsRegister.RIFLE_WITH_SPYGLASS.get());
        ModItemsRegister.ITEM_RANGE_WEAPON.put(ModItemsRegister.REVOLVER_LEAD_BULLET.toString(),ModItemsRegister.REVOLVER_LEAD_BULLET.get());
        ModItemsRegister.ITEM_RANGE_WEAPON.put(ModItemsRegister.REVOLVER_ALUMINUM_BULLET.toString(),ModItemsRegister.REVOLVER_ALUMINUM_BULLET.get());
        ModItemsRegister.ITEM_RANGE_WEAPON.put(ModItemsRegister.SHOTGUN_LEAD_BULLET.toString(),ModItemsRegister.SHOTGUN_LEAD_BULLET.get());
        ModItemsRegister.ITEM_RANGE_WEAPON.put(ModItemsRegister.SHOTGUN_ALUMINUM_BULLET.toString(),ModItemsRegister.SHOTGUN_ALUMINUM_BULLET.get());
        ModItemsRegister.ITEM_RANGE_WEAPON.put(ModItemsRegister.RIFLE_LEAD_BULLET.toString(),ModItemsRegister.RIFLE_LEAD_BULLET.get());
        ModItemsRegister.ITEM_RANGE_WEAPON.put(ModItemsRegister.RIFLE_ALUMINUM_BULLET.toString(),ModItemsRegister.RIFLE_ALUMINUM_BULLET.get());

        ModItemsRegister.ITEM_CORES.put(ModItemsRegister.OBSIDIAN_CORE.toString(),ModItemsRegister.OBSIDIAN_CORE.get());
        ModItemsRegister.ITEM_CORES.put(ModItemsRegister.ALUMINUM_CORE.toString(),ModItemsRegister.ALUMINUM_CORE.get());
        ModItemsRegister.ITEM_CORES.put(ModItemsRegister.STEEL_CORE.toString(),ModItemsRegister.STEEL_CORE.get());
        ModItemsRegister.ITEM_CORES.put(ModItemsRegister.ATIUM_CORE.toString(),ModItemsRegister.ATIUM_CORE.get());
        ModItemsRegister.ITEM_CORES.put(ModItemsRegister.LERASIUM_CORE.toString(),ModItemsRegister.LERASIUM_CORE.get());
        ModItemsRegister.ITEM_CORES.put(ModItemsRegister.ETTMETAL_CORE.toString(),ModItemsRegister.ETTMETAL_CORE.get());
        ModItemsRegister.ITEM_CORES.put(ModItemsRegister.COPPER_CORE.toString(),ModItemsRegister.COPPER_CORE.get());

    }

}

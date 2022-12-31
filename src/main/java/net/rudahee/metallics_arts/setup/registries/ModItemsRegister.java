package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.MetalMindEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.SpikeEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.alloy_furnace.AlloyRecipeEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_spikes.*;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands.*;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.rings.*;
import net.rudahee.metallics_arts.modules.custom_items.vials.large_vial.LargeVial;
import net.rudahee.metallics_arts.modules.custom_items.vials.small_vial.SmallVial;
import net.rudahee.metallics_arts.modules.custom_items.weapons.*;
import net.rudahee.metallics_arts.setup.registries.items.BasicMetalsRegister;
import net.rudahee.metallics_arts.setup.registries.items.IconsRegister;
import net.rudahee.metallics_arts.setup.registries.items.MetalMindsRegister;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


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


    public static void register() {
        BasicMetalsRegister.register();
        IconsRegister.register();
        MetalMindsRegister.register();
    }


//spikes

    private static final Item.Properties PROPERTY_SPIKE = new Item.Properties().tab(MetallicsArts.MA_TAB).stacksTo(1);

    public static RegistryObject<Item> IRON_SPIKE = MetallicsArts.registerItem("iron_spike",
            () -> {
                Item item = new IronSpike(PROPERTY_SPIKE);
                SpikeEnum.valueOf("IRON").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> STEEL_SPIKE = MetallicsArts.registerItem("steel_spike",
            () -> {
                Item item = new SteelSpike(PROPERTY_SPIKE);
                SpikeEnum.valueOf("STEEL").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> TIN_SPIKE = MetallicsArts.registerItem("tin_spike",
            () -> {
                Item item = new TinSpike(PROPERTY_SPIKE);
                SpikeEnum.valueOf("TIN").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> PEWTER_SPIKE = MetallicsArts.registerItem("pewter_spike",
            () -> {
                Item item = new PewterSpike(PROPERTY_SPIKE);
                SpikeEnum.valueOf("PEWTER").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> COPPER_SPIKE = MetallicsArts.registerItem("copper_spike",
            () -> {
                Item item = new CopperSpike(PROPERTY_SPIKE);
                SpikeEnum.valueOf("COPPER").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> BRONZE_SPIKE = MetallicsArts.registerItem("bronze_spike",
            () -> {
                Item item = new BronzeSpike(PROPERTY_SPIKE);
                SpikeEnum.valueOf("BRONZE").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> ZINC_SPIKE = MetallicsArts.registerItem("zinc_spike",
            () -> {
                Item item = new ZincSpike(PROPERTY_SPIKE);
                SpikeEnum.valueOf("ZINC").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> BRASS_SPIKE = MetallicsArts.registerItem("brass_spike",
            () -> {
                Item item = new BrassSpike(PROPERTY_SPIKE);
                SpikeEnum.valueOf("BRASS").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> CHROMIUM_SPIKE = MetallicsArts.registerItem("chromium_spike",
            () -> {
                Item item = new ChromiumSpike(PROPERTY_SPIKE);
                SpikeEnum.valueOf("CHROMIUM").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> NICROSIL_SPIKE = MetallicsArts.registerItem("nicrosil_spike",
            () -> {
                Item item = new NicrosilSpike(PROPERTY_SPIKE);
                SpikeEnum.valueOf("NICROSIL").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> ALUMINUM_SPIKE = MetallicsArts.registerItem("aluminum_spike",
            () -> {
                Item item = new AluminumSpike(PROPERTY_SPIKE);
                SpikeEnum.valueOf("ALUMINUM").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> DURALUMIN_SPIKE = MetallicsArts.registerItem("duralumin_spike",
            () -> {
                Item item = new DuraluminSpike(PROPERTY_SPIKE);
                SpikeEnum.valueOf("DURALUMIN").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> CADMIUM_SPIKE = MetallicsArts.registerItem("cadmium_spike",
            () -> {
                Item item = new CadmiumSpike(PROPERTY_SPIKE);
                SpikeEnum.valueOf("CADMIUM").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> BENDALLOY_SPIKE = MetallicsArts.registerItem("bendalloy_spike",
            () -> {
                Item item = new BendalloySpike(PROPERTY_SPIKE);
                SpikeEnum.valueOf("BENDALLOY").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> ELECTRUM_SPIKE = MetallicsArts.registerItem("electrum_spike",
            () -> {
                Item item = new ElectrumSpike(PROPERTY_SPIKE);
                SpikeEnum.valueOf("ELECTRUM").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> GOLD_SPIKE = MetallicsArts.registerItem("gold_spike",
            () -> {
                Item item = new GoldSpike(PROPERTY_SPIKE);
                SpikeEnum.valueOf("GOLD").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> ATIUM_SPIKE = MetallicsArts.registerItem("atium_spike",
            () -> {
                Item item = new AtiumSpike(PROPERTY_SPIKE);
                SpikeEnum.valueOf("ATIUM").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> MALATIUM_SPIKE = MetallicsArts.registerItem("malatium_spike",
            () -> {
                Item item = new MalatiumSpike(PROPERTY_SPIKE);
                SpikeEnum.valueOf("MALATIUM").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> LERASIUM_SPIKE = MetallicsArts.registerItem("lerasium_spike",
            () -> {
                Item item = new LerasiumSpike(PROPERTY_SPIKE);
                SpikeEnum.valueOf("LERASIUM").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> ETTMETAL_SPIKE = MetallicsArts.registerItem("ettmetal_spike",
            () -> {
                Item item = new EttmetalSpike(PROPERTY_SPIKE);
                SpikeEnum.valueOf("ETTMETAL").setSpike(item);
                return item;
            }
    );

//weapons

    public static RegistryObject<Item> OBSIDIAN_DAGGER = MetallicsArts.registerItem("obsidian_dagger",
            () -> new ObsidianDagger(new Item.Properties().tab(MetallicsArts.MA_TAB).tab(CreativeModeTab.TAB_COMBAT))
    );
    public static RegistryObject<Item> CRISTAL_DAGGER = MetallicsArts.registerItem("cristal_dagger",
            () -> new CristalDagger(new Item.Properties().tab(MetallicsArts.MA_TAB).tab(CreativeModeTab.TAB_COMBAT))
    );
    public static RegistryObject<Item> KOLOSS_BLADE = MetallicsArts.registerItem("koloss_blade",
            () -> new KolossBlade(new Item.Properties().tab(MetallicsArts.MA_TAB).tab(CreativeModeTab.TAB_COMBAT))
    );
    public static RegistryObject<Item> DUELING_STAFF = MetallicsArts.registerItem("dueling_staff",
            () -> new DuelingStaff(new Item.Properties().tab(MetallicsArts.MA_TAB).tab(CreativeModeTab.TAB_COMBAT))
    );
    public static RegistryObject<Item> OBSIDIAN_AXE = MetallicsArts.registerItem("obsidian_axe",
            () -> new ObsidianAxe(new Item.Properties().tab(MetallicsArts.MA_TAB).tab(CreativeModeTab.TAB_COMBAT))
    );




//Vials
   public static RegistryObject<Item> LARGE_VIAL= MetallicsArts.registerItem("large_vial",
            () -> new LargeVial(new Item.Properties().tab(MetallicsArts.MA_TAB).stacksTo(1).food(new FoodProperties.Builder().nutrition(0).build())));

    public static RegistryObject<Item> SMALL_VIAL = MetallicsArts.registerItem("small_vial",
            () -> new SmallVial(new Item.Properties().tab(MetallicsArts.MA_TAB).stacksTo(1).food(new FoodProperties.Builder().nutrition(0).build())));



}

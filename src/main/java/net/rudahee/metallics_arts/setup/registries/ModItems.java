package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.items.combat.*;
import net.rudahee.metallics_arts.modules.items.metal_spike.*;
import net.rudahee.metallics_arts.modules.items.metalminds.bands.*;
import net.rudahee.metallics_arts.modules.items.metalminds.rings.*;
import net.rudahee.metallics_arts.modules.items.vials.large_vial.LargeVial;
import net.rudahee.metallics_arts.modules.items.vials.small_vial.SmallVial;
import net.rudahee.metallics_arts.setup.enums.extras.MetalMindData;
import net.rudahee.metallics_arts.setup.enums.extras.MetalSpikesData;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.enums.gems.Gems;
import net.rudahee.metallics_arts.setup.enums.metals.Metal;
import net.rudahee.metallics_arts.setup.enums.metals.MetalBurningRecipeData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class ModItems {

    public static final HashMap<String, Item> ITEM_METAL_INGOT = new HashMap<String, Item>();

    public static final HashMap<String, Item> ITEM_RAW_METAL = new HashMap<String, Item>();
    public static final HashMap<String, Item> ITEM_METAL_NUGGET = new HashMap<String, Item>();
    public static final HashMap<String, Item> ITEM_GEMS_BASE = new HashMap<String, Item>();
    public static final HashMap<String, Item> ITEM_GEMS_NUGGET = new HashMap<String, Item>();

    public static final HashMap<String, Item> ITEM_ICONS_ALLOMANCY = new HashMap<String, Item>();

    public static final HashMap<String, Item> ITEM_ICONS_FERUCHEMIC = new HashMap<String, Item>();


    //ingots an nuggets
    public static void register() {

        // Generating ingots and nuggets off all metals
        List<Metal> metalList = Arrays.asList(Metal.values());

        metalList.forEach(metal -> {
            MetallicsArts.registerItem(metal.getMetalNameLower() + "_ingot", () -> {
                Item item = new Item(new Item.Properties().tab(MetallicsArts.MA_TAB).stacksTo(64));
                ITEM_METAL_INGOT.put(metal.getMetalNameLower(), item);
                if (metal.isAlloy()) {
                    MetalBurningRecipeData.valueOf(metal.getMetalNameUpper()).setItem(item);
                }
                return item;
            });

            MetallicsArts.registerItem(metal.getMetalNameLower() + "_nugget", () -> {
                Item item = new Item(new Item.Properties().tab(MetallicsArts.MA_TAB).stacksTo(64));
                ITEM_METAL_NUGGET.put(metal.getMetalNameLower(), item);
                return item;
            });

            if (!metal.isAlloy()) {
                MetallicsArts.registerItem("raw_" + metal.getMetalNameLower(), () -> {
                    Item item = new Item(new Item.Properties().tab(MetallicsArts.MA_TAB).stacksTo(64));
                    ITEM_RAW_METAL.put(metal.getMetalNameLower(), item);
                    return item;
                });
            }
        });

        MetallicsArts.registerItem("copper_nugget",() -> {
            Item item = new Item(new Item.Properties().tab(MetallicsArts.MA_TAB).stacksTo(64));
            ITEM_METAL_NUGGET.put("copper_nugget", item);
            return item;
        });


        List<Gems> gemList = Arrays.asList(Gems.values());

        gemList.forEach(gem -> {
            MetallicsArts.registerItem(gem.getGemNameLower(),
                    () -> {
                        Item item = new Item(new Item.Properties().tab(MetallicsArts.MA_TAB));
                        ITEM_GEMS_BASE.put(gem.getGemNameLower(), item);
                        if (gem.getGemNameLower() == "malatium") {
                            MetalBurningRecipeData.valueOf(gem.getGemNameUpper()).setItem(item);
                        }
                        return item;
                    });

            MetallicsArts.registerItem(gem.getGemNameLower() + "_nugget",
                    () -> {
                        Item item = new Item(new Item.Properties().tab(MetallicsArts.MA_TAB));
                        ITEM_GEMS_NUGGET.put(gem.getGemNameLower(), item);
                        return item;
                    });
        });

        for (MetalsNBTData metal: MetalsNBTData.values()) {


            MetallicsArts.registerItem(metal.getNameLower()+"_allomantic_icon",
                    ()-> {
                        Item item = new Item(new Item.Properties().tab(MetallicsArts.MA_TAB_DECORATION));
                        if (!metal.isDivine()) {
                            ITEM_ICONS_ALLOMANCY.put(metal.getNameLower(),item);
                        }
                        return item;
                    });
            MetallicsArts.registerItem(metal.getNameLower()+"_feruchemic_icon",
                    ()-> {
                        Item item = new Item(new Item.Properties().tab(MetallicsArts.MA_TAB_DECORATION));
                        if (!metal.isDivine()) {
                            ITEM_ICONS_FERUCHEMIC.put(metal.getNameLower(), item);
                        }
                        return item;
                    });
        }




    }

    //MentalMinds

    private static final Item.Properties PROPERTY_METALMINDS = new Item.Properties().tab(MetallicsArts.MA_TAB).stacksTo(1);


    public static  RegistryObject<Item> BAND_ALUMINUM_DURALUMIN = MetallicsArts.registerItem("band_aluminum_duralumin",
            () -> {
                Item item = new BandAluminumDuralumin(PROPERTY_METALMINDS);
                MetalMindData.valueOf("ALUMINUM_DURALUMIN").setBand(item);
                return item;
            });

    public static RegistryObject<Item> BAND_ATIUM_MALATIUM = MetallicsArts.registerItem("band_atium_malatium",
            () -> {
                Item item = new BandAtiumMalatium(PROPERTY_METALMINDS);
                MetalMindData.valueOf("ATIUM_MALTIUM").setBand(item);
                return item;
            }
    );
    public static RegistryObject<Item> BAND_CADMIUM_BENDALLOY = MetallicsArts.registerItem("band_cadmium_bendalloy",
            () -> {
                Item item = new BandCadmiumBendalloy(PROPERTY_METALMINDS);
                MetalMindData.valueOf("CADMIUM_BENDALLOY").setBand(item);
                return item;
            }
    );
    public static RegistryObject<Item> BAND_CHROMIUM_NICROSIL = MetallicsArts.registerItem("band_chromium_nicrosil",
            () -> {
                Item item = new BandChromiumNicrosil(PROPERTY_METALMINDS);
                MetalMindData.valueOf("CHROMIUM_NICROSIL").setBand(item);
                return item;
            }
    );
    public static RegistryObject<Item> BAND_COPPER_BRONZE = MetallicsArts.registerItem("band_copper_bronze",
            () -> {
                Item item = new BandCopperBronze(PROPERTY_METALMINDS);
                MetalMindData.valueOf("COPPER_BRONZE").setBand(item);
                return item;
            }
    );
    public static RegistryObject<Item> BAND_ELECTRUM_GOLD = MetallicsArts.registerItem("band_electrum_gold",
            () -> {
                Item item = new BandElectrumGold(PROPERTY_METALMINDS);
                MetalMindData.valueOf("ELECTRUM_GOLD").setBand(item);
                return item;
            }
    );
    public static RegistryObject<Item> BAND_LERASIUM_ETTMETAL = MetallicsArts.registerItem("band_lerasium_ettmetal",
            () -> {
                Item item = new BandLerasiumEttmetal(PROPERTY_METALMINDS);
                MetalMindData.valueOf("LERASIUM_ETTMETAL").setBand(item);
                return item;
            }
    );
    public static RegistryObject<Item> BAND_PEWTER_TIN = MetallicsArts.registerItem("band_pewter_tin",
            () -> {
                Item item = new BandPewterTin(PROPERTY_METALMINDS);
                MetalMindData.valueOf("TIN_PEWTER").setBand(item);
                return item;
            }
    );
    public static RegistryObject<Item> BAND_STEEL_IRON = MetallicsArts.registerItem("band_steel_iron",
            () -> {
                Item item = new BandSteelIron(PROPERTY_METALMINDS);
                MetalMindData.valueOf("STEEL_IRON").setBand(item);
                return item;
            }
    );
    public static RegistryObject<Item> BAND_ZINC_BRASS = MetallicsArts.registerItem("band_zinc_brass",
            () -> {
                Item item = new BandZincBrass(PROPERTY_METALMINDS);
                MetalMindData.valueOf("ZINC_BRASS").setBand(item);
                return item;
            }
    );

    public static RegistryObject<Item> RING_ALUMINUM_DURALUMIN = MetallicsArts.registerItem("ring_aluminum_duralumin",
            () -> {
                Item item = new RingAluminumDuralumin(PROPERTY_METALMINDS);
                MetalMindData.valueOf("ALUMINUM_DURALUMIN").setRing(item);
                return item;
            }
    );
    public static RegistryObject<Item> RING_ATIUM_MALATIUM = MetallicsArts.registerItem("ring_atium_malatium",
            () -> {
                Item item = new RingAtiumMalatium(PROPERTY_METALMINDS);
                MetalMindData.valueOf("ATIUM_MALTIUM").setRing(item);
                return item;
            }
    );
    public static RegistryObject<Item> RING_CADMIUM_BENDALLOY = MetallicsArts.registerItem("ring_cadmium_bendalloy",
            () -> {
                Item item = new RingCadmiumBendalloy(PROPERTY_METALMINDS);
                MetalMindData.valueOf("CADMIUM_BENDALLOY").setRing(item);
                return item;
            }
    );
    public static RegistryObject<Item> RING_CHROMIUM_NICROSIL = MetallicsArts.registerItem("ring_chromium_nicrosil",
            () -> {
                Item item = new RingChromiumNicrosil(PROPERTY_METALMINDS);
                MetalMindData.valueOf("CHROMIUM_NICROSIL").setRing(item);
                return item;
            }
    );
    public static RegistryObject<Item> RING_COPPER_BRONZE = MetallicsArts.registerItem("ring_copper_bronze",
            () -> {
                Item item = new RingCopperBronze(PROPERTY_METALMINDS);
                MetalMindData.valueOf("COPPER_BRONZE").setRing(item);
                return item;
            }
    );
    public static RegistryObject<Item> RING_ELECTRUM_GOLD = MetallicsArts.registerItem("ring_electrum_gold",
            () -> {
                Item item = new RingElectrumGold(PROPERTY_METALMINDS);
                MetalMindData.valueOf("ELECTRUM_GOLD").setRing(item);
                return item;
            }
    );
    public static RegistryObject<Item> RING_LERASIUM_ETTMETAL = MetallicsArts.registerItem("ring_lerasium_ettmetal",
            () -> {
                Item item = new RingLerasiumEttmetal(PROPERTY_METALMINDS);
                MetalMindData.valueOf("LERASIUM_ETTMETAL").setRing(item);
                return item;
            }
    );
    public static RegistryObject<Item> RING_PEWTER_TIN = MetallicsArts.registerItem("ring_pewter_tin",
            () -> {
                Item item = new RingPewterTin(PROPERTY_METALMINDS);
                MetalMindData.valueOf("TIN_PEWTER").setRing(item);
                return item;
            }
    );
    public static RegistryObject<Item> RING_STEEL_IRON = MetallicsArts.registerItem("ring_steel_iron",
            () -> {
                Item item = new RingSteelIron(PROPERTY_METALMINDS);
                MetalMindData.valueOf("STEEL_IRON").setRing(item);
                return item;
            }
    );
    public static RegistryObject<Item> RING_ZINC_BRASS = MetallicsArts.registerItem("ring_zinc_brass",
            () -> {
                Item item = new RingZincBrass(PROPERTY_METALMINDS);
                MetalMindData.valueOf("ZINC_BRASS").setRing(item);
                return item;
            }
    );


//spikes

    private static final Item.Properties PROPERTY_SPIKE = new Item.Properties().tab(MetallicsArts.MA_TAB).stacksTo(1);

    public static RegistryObject<Item> IRON_SPIKE = MetallicsArts.registerItem("iron_spike",
            () -> {
                Item item = new IronSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("IRON").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> STEEL_SPIKE = MetallicsArts.registerItem("steel_spike",
            () -> {
                Item item = new SteelSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("STEEL").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> TIN_SPIKE = MetallicsArts.registerItem("tin_spike",
            () -> {
                Item item = new TinSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("TIN").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> PEWTER_SPIKE = MetallicsArts.registerItem("pewter_spike",
            () -> {
                Item item = new PewterSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("PEWTER").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> COPPER_SPIKE = MetallicsArts.registerItem("copper_spike",
            () -> {
                Item item = new CopperSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("COPPER").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> BRONZE_SPIKE = MetallicsArts.registerItem("bronze_spike",
            () -> {
                Item item = new BronzeSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("BRONZE").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> ZINC_SPIKE = MetallicsArts.registerItem("zinc_spike",
            () -> {
                Item item = new ZincSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("ZINC").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> BRASS_SPIKE = MetallicsArts.registerItem("brass_spike",
            () -> {
                Item item = new BrassSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("BRASS").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> CHROMIUM_SPIKE = MetallicsArts.registerItem("chromium_spike",
            () -> {
                Item item = new ChromiumSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("CHROMIUM").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> NICROSIL_SPIKE = MetallicsArts.registerItem("nicrosil_spike",
            () -> {
                Item item = new NicrosilSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("NICROSIL").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> ALUMINUM_SPIKE = MetallicsArts.registerItem("aluminum_spike",
            () -> {
                Item item = new AluminumSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("ALUMINUM").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> DURALUMIN_SPIKE = MetallicsArts.registerItem("duralumin_spike",
            () -> {
                Item item = new DuraluminSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("DURALUMIN").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> CADMIUM_SPIKE = MetallicsArts.registerItem("cadmium_spike",
            () -> {
                Item item = new CadmiumSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("CADMIUM").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> BENDALLOY_SPIKE = MetallicsArts.registerItem("bendalloy_spike",
            () -> {
                Item item = new BendalloySpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("BENDALLOY").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> ELECTRUM_SPIKE = MetallicsArts.registerItem("electrum_spike",
            () -> {
                Item item = new ElectrumSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("ELECTRUM").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> GOLD_SPIKE = MetallicsArts.registerItem("gold_spike",
            () -> {
                Item item = new GoldSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("GOLD").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> ATIUM_SPIKE = MetallicsArts.registerItem("atium_spike",
            () -> {
                Item item = new AtiumSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("ATIUM").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> MALATIUM_SPIKE = MetallicsArts.registerItem("malatium_spike",
            () -> {
                Item item = new MalatiumSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("MALATIUM").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> LERASIUM_SPIKE = MetallicsArts.registerItem("lerasium_spike",
            () -> {
                Item item = new LerasiumSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("LERASIUM").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> ETTMETAL_SPIKE = MetallicsArts.registerItem("ettmetal_spike",
            () -> {
                Item item = new EttmetalSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("ETTMETAL").setSpike(item);
                return item;
            }
    );

//weapons

    public static RegistryObject<Item> OBSIDIAN_DAGGER = MetallicsArts.registerItem("obsidian_dagger",
            () -> {
                return new ObsidianDagger(new Item.Properties().tab(MetallicsArts.MA_TAB).tab(CreativeModeTab.TAB_COMBAT));
            }
    );
    public static RegistryObject<Item> CRISTAL_DAGGER = MetallicsArts.registerItem("cristal_dagger",
            () -> {
                return new CristalDagger(new Item.Properties().tab(MetallicsArts.MA_TAB).tab(CreativeModeTab.TAB_COMBAT));
            }
    );
    public static RegistryObject<Item> KOLOSS_BLADE = MetallicsArts.registerItem("koloss_blade",
            () -> {
                return new KolossBlade(new Item.Properties().tab(MetallicsArts.MA_TAB).tab(CreativeModeTab.TAB_COMBAT));
            }
    );
    public static RegistryObject<Item> DUELING_STAFF = MetallicsArts.registerItem("dueling_staff",
            () -> {
                return new DuelingStaff(new Item.Properties().tab(MetallicsArts.MA_TAB).tab(CreativeModeTab.TAB_COMBAT));
            }
    );
    public static RegistryObject<Item> OBSIDIAN_AXE = MetallicsArts.registerItem("obsidian_axe",
            () -> {
                return new ObsidianAxe(new Item.Properties().tab(MetallicsArts.MA_TAB).tab(CreativeModeTab.TAB_COMBAT));
            }
    );



   public static RegistryObject<Item> LARGE_VIAL= MetallicsArts.registerItem("large_vial",
            () -> new LargeVial(new Item.Properties().tab(MetallicsArts.MA_TAB).stacksTo(1).food(new FoodProperties.Builder().nutrition(0).build())));

    public static RegistryObject<Item> SMALL_VIAL = MetallicsArts.registerItem("small_vial",
            () -> new SmallVial(new Item.Properties().tab(MetallicsArts.MA_TAB).stacksTo(1).food(new FoodProperties.Builder().nutrition(0).build())));



}

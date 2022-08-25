package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.rudahee.metallics_arts.modules.items.combat.*;
import net.rudahee.metallics_arts.modules.items.metal_spike.*;
import net.rudahee.metallics_arts.modules.items.metalminds.bands.*;
import net.rudahee.metallics_arts.modules.items.metalminds.rings.*;
import net.rudahee.metallics_arts.modules.items.vials.large_vial.LargeVial;
import net.rudahee.metallics_arts.modules.items.vials.small_vial.SmallVial;
import net.rudahee.metallics_arts.setup.Registration;
import net.rudahee.metallics_arts.setup.enums.extras.MetalMindData;
import net.rudahee.metallics_arts.setup.enums.extras.MetalSpikesData;
import net.rudahee.metallics_arts.setup.enums.gems.Gems;
import net.rudahee.metallics_arts.setup.enums.metals.Metal;
import net.rudahee.metallics_arts.setup.enums.metals.MetalBurningRecipeData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;


public class ModItems {

    public static final HashMap<String, Item> ITEM_METAL_INGOT = new HashMap<String, Item>();
    public static final HashMap<String, Item> ITEM_METAL_NUGGET = new HashMap<String, Item>();

    public static final HashMap<String, Item> ITEM_GEMS_BASE = new HashMap<String, Item>();
    public static final HashMap<String, Item> ITEM_GEMS_NUGGET = new HashMap<String, Item>();


    //ingots an nuggets
    static {

        // Generating ingots and nuggets off all metals
        List<Metal> metalList = Arrays.asList(Metal.values());
        metalList.forEach(metal -> {
            register(metal.getMetalNameLower() + "_ingot", () -> {
                Item item = new Item(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                ITEM_METAL_INGOT.put(metal.getMetalNameLower(), item);
                if (metal.isAlloy()) {
                    MetalBurningRecipeData.valueOf(metal.getMetalNameUpper()).setItem(item);
                }
                return item;
            });

            register(metal.getMetalNameLower() + "_nugget", () -> {
                Item item = new Item(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                ITEM_METAL_NUGGET.put(metal.getMetalNameLower(), item);
                return item;
            });
        });
    }

    //gems
    static {
        List<Gems> gemList = Arrays.asList(Gems.values());
        gemList.forEach(gem -> {
            register(gem.getGemNameLower(),
                    () -> {
                        Item item = new Item(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                        ITEM_GEMS_BASE.put(gem.getGemNameLower(), item);
                        if (gem.getGemNameLower() == "malatium") {
                            MetalBurningRecipeData.valueOf(gem.getGemNameUpper()).setItem(item);
                        }
                        return item;
                    });
            register(gem.getGemNameLower() + "_nugget",
                    () -> {
                        Item item = new Item(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                        ITEM_GEMS_NUGGET.put(gem.getGemNameLower(), item);
                        return item;
                    });
        });

    }

    //MentalMinds

    private static Item.Properties PROPERTY_METALMINDS = new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG).stacksTo(1);

    public static RegistryObject<Item> BAND_ALUMINUM_DURALUMIN = register("band_aluminum_duralumin",
            () -> {
                Item item = new BandAluminumDuralumin(PROPERTY_METALMINDS);
                MetalMindData.valueOf("ALUMINUM_DURALUMIN").setBand(item);
                return item;
            }
    );
    public static RegistryObject<Item> BAND_ATIUM_MALATIUM = register("band_atium_malatium",
            () -> {
                Item item = new BandAtiumMalatium(PROPERTY_METALMINDS);
                MetalMindData.valueOf("ATIUM_MALTIUM").setBand(item);
                return item;
            }
    );
    public static RegistryObject<Item> BAND_CADMIUM_BENDALLOY = register("band_cadmium_bendalloy",
            () -> {
                Item item = new BandCadmiumBendalloy(PROPERTY_METALMINDS);
                MetalMindData.valueOf("CADMIUM_BENDALLOY").setBand(item);
                return item;
            }
    );
    public static RegistryObject<Item> BAND_CHROMIUM_NICROSIL = register("band_chromium_nicrosil",
            () -> {
                Item item = new BandChromiumNicrosil(PROPERTY_METALMINDS);
                MetalMindData.valueOf("CHROMIUM_NICROSIL").setBand(item);
                return item;
            }
    );
    public static RegistryObject<Item> BAND_COPPER_BRONZE = register("band_copper_bronze",
            () -> {
                Item item = new BandCopperBronze(PROPERTY_METALMINDS);
                MetalMindData.valueOf("COPPER_BRONZE").setBand(item);
                return item;
            }
    );
    public static RegistryObject<Item> BAND_ELECTRUM_GOLD = register("band_electrum_gold",
            () -> {
                Item item = new BandElectrumGold(PROPERTY_METALMINDS);
                MetalMindData.valueOf("ELECTRUM_GOLD").setBand(item);
                return item;
            }
    );
    public static RegistryObject<Item> BAND_LERASIUM_ETTMETAL = register("band_lerasium_ettmetal",
            () -> {
                Item item = new BandLerasiumEttmetal(PROPERTY_METALMINDS);
                MetalMindData.valueOf("LERASIUM_ETTMETAL").setBand(item);
                return item;
            }
    );
    public static RegistryObject<Item> BAND_PEWTER_TIN = register("band_pewter_tin",
            () -> {
                Item item = new BandPwterTin(PROPERTY_METALMINDS);
                MetalMindData.valueOf("TIN_PEWTER").setBand(item);
                return item;
            }
    );
    public static RegistryObject<Item> BAND_STEEL_IRON = register("band_steel_iron",
            () -> {
                Item item = new BandSteelIron(PROPERTY_METALMINDS);
                MetalMindData.valueOf("STEEL_IRON").setBand(item);
                return item;
            }
    );
    public static RegistryObject<Item> BAND_ZINC_BRASS = register("band_zinc_brass",
            () -> {
                Item item = new BandZincBrass(PROPERTY_METALMINDS);
                MetalMindData.valueOf("ZINC_BRASS").setBand(item);
                return item;
            }
    );

    public static RegistryObject<Item> RING_ALUMINUM_DURALUMIN = register("ring_aluminum_duralumin",
            () -> {
                Item item = new RingAluminumDuralumin(PROPERTY_METALMINDS);
                MetalMindData.valueOf("ALUMINUM_DURALUMIN").setRing(item);
                return item;
            }
    );
    public static RegistryObject<Item> RING_ATIUM_MALATIUM = register("ring_atium_malatium",
            () -> {
                Item item = new RingAtiumMalatium(PROPERTY_METALMINDS);
                MetalMindData.valueOf("ATIUM_MALTIUM").setRing(item);
                return item;
            }
    );
    public static RegistryObject<Item> RING_CADMIUM_BENDALLOY = register("ring_cadmium_bendalloy",
            () -> {
                Item item = new RingCadmiumBendalloy(PROPERTY_METALMINDS);
                MetalMindData.valueOf("CADMIUM_BENDALLOY").setRing(item);
                return item;
            }
    );
    public static RegistryObject<Item> RING_CHROMIUM_NICROSIL = register("ring_chromium_nicrosil",
            () -> {
                Item item = new RingChromiumNicrosil(PROPERTY_METALMINDS);
                MetalMindData.valueOf("CHROMIUM_NICROSIL").setRing(item);
                return item;
            }
    );
    public static RegistryObject<Item> RING_COPPER_BRONZE = register("ring_copper_bronze",
            () -> {
                Item item = new RingCopperBronze(PROPERTY_METALMINDS);
                MetalMindData.valueOf("COPPER_BRONZE").setRing(item);
                return item;
            }
    );
    public static RegistryObject<Item> RING_ELECTRUM_GOLD = register("ring_electrum_gold",
            () -> {
                Item item = new RingElectrumGold(PROPERTY_METALMINDS);
                MetalMindData.valueOf("ELECTRUM_GOLD").setRing(item);
                return item;
            }
    );
    public static RegistryObject<Item> RING_LERASIUM_ETTMETAL = register("ring_lerasium_ettmetal",
            () -> {
                Item item = new RingLerasiumEttmetal(PROPERTY_METALMINDS);
                MetalMindData.valueOf("LERASIUM_ETTMETAL").setRing(item);
                return item;
            }
    );
    public static RegistryObject<Item> RING_PEWTER_TIN = register("ring_pewter_tin",
            () -> {
                Item item = new RingPewterTin(PROPERTY_METALMINDS);
                MetalMindData.valueOf("TIN_PEWTER").setRing(item);
                return item;
            }
    );
    public static RegistryObject<Item> RING_STEEL_IRON = register("ring_steel_iron",
            () -> {
                Item item = new RingSteelIron(PROPERTY_METALMINDS);
                MetalMindData.valueOf("STEEL_IRON").setRing(item);
                return item;
            }
    );
    public static RegistryObject<Item> RING_ZINC_BRASS = register("ring_zinc_brass",
            () -> {
                Item item = new RingZincBrass(PROPERTY_METALMINDS);
                MetalMindData.valueOf("ZINC_BRASS").setRing(item);
                return item;
            }
    );


//spikes

    private static Item.Properties PROPERTY_SPIKE = new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG).stacksTo(1);

    public static RegistryObject<Item> IRON_SPIKE = register("iron_spike",
            () -> {
                Item item = new IronSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("IRON").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> STEEL_SPIKE = register("steel_spike",
            () -> {
                Item item = new SteelSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("STEEL").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> TIN_SPIKE = register("tin_spike",
            () -> {
                Item item = new TinSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("TIN").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> PEWTER_SPIKE = register("pewter_spike",
            () -> {
                Item item = new PewterSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("PEWTER").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> COPPER_SPIKE = register("copper_spike",
            () -> {
                Item item = new CopperSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("COPPER").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> BRONZE_SPIKE = register("bronze_spike",
            () -> {
                Item item = new BronzeSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("BRONZE").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> ZINC_SPIKE = register("zinc_spike",
            () -> {
                Item item = new ZincSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("ZINC").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> BRASS_SPIKE = register("brass_spike",
            () -> {
                Item item = new BrassSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("BRASS").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> CHROMIUM_SPIKE = register("chromium_spike",
            () -> {
                Item item = new ChromiumSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("CHROMIUM").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> NICROSIL_SPIKE = register("nicrosil_spike",
            () -> {
                Item item = new NicrosilSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("NICROSIL").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> ALUMINUM_SPIKE = register("aluminum_spike",
            () -> {
                Item item = new AluminumSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("ALUMINUM").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> DURALUMIN_SPIKE = register("duralumin_spike",
            () -> {
                Item item = new DuraluminSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("DURALUMIN").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> CADMIUM_SPIKE = register("cadmium_spike",
            () -> {
                Item item = new CadmiumSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("CADMIUM").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> BENDALLOY_SPIKE = register("bendalloy_spike",
            () -> {
                Item item = new BendalloySpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("BENDALLOY").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> ELECTRUM_SPIKE = register("electrum_spike",
            () -> {
                Item item = new ElectrumSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("ELECTRUM").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> GOLD_SPIKE = register("gold_spike",
            () -> {
                Item item = new GoldSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("GOLD").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> ATIUM_SPIKE = register("atium_spike",
            () -> {
                Item item = new AtiumSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("ATIUM").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> MALATIUM_SPIKE = register("malatium_spike",
            () -> {
                Item item = new MalatiumSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("MALATIUM").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> LERASIUM_SPIKE = register("lerasium_spike",
            () -> {
                Item item = new LerasiumSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("LERASIUM").setSpike(item);
                return item;
            }
    );
    public static RegistryObject<Item> ETTMETAL_SPIKE = register("ettmetal_spike",
            () -> {
                Item item = new EttmetalSpike(PROPERTY_SPIKE);
                MetalSpikesData.valueOf("ETTMETAL").setSpike(item);
                return item;
            }
    );

//weapons

    public static RegistryObject<Item> OBSIDIAN_DAGGER = register("obsidian_dagger",
            () -> {
                return new ObsidianDagger(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG).tab(ItemGroup.TAB_COMBAT));
            }
    );
    public static RegistryObject<Item> CRISTAL_DAGGER = register("cristal_dagger",
            () -> {
                return new CristalDagger(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG).tab(ItemGroup.TAB_COMBAT));
            }
    );
    public static RegistryObject<Item> KOLOSS_BLADE = register("koloss_blade",
            () -> {
                return new KolossBlade(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG).tab(ItemGroup.TAB_COMBAT));
            }
    );
    public static RegistryObject<Item> DUELING_STAFF = register("dueling_staff",
            () -> {
                return new DuelingStaff(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG).tab(ItemGroup.TAB_COMBAT));
            }
    );
    public static RegistryObject<Item> OBSIDIAN_AXE = register("obsidian_axe",
            () -> {
                return new ObsidianAxe(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG).tab(ItemGroup.TAB_COMBAT));
            }
    );

/*
Item item = new Item(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                ITEM_METAL_INGOT.put(metal.getMetalNameLower(), item);
 */
    public static RegistryObject<Item> LARGE_VIAL= register("big_vial",
            () -> {
                return new LargeVial(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG).stacksTo(1).food(new Food.Builder().nutrition(0).build()));
            });

    public static RegistryObject<Item> SMALL_VIAL = register("small_vial",
            () -> {
        Item item = new SmallVial(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG).stacksTo(1).food(new Food.Builder().nutrition(0).build()));
        return item;
                //return new SmallVial(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG).stacksTo(1).food(new Food.Builder().nutrition(0).build()));
            });

    public static void register() {
    }

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> itemSupplier) {
        return Registration.ITEMS.register(name, itemSupplier);
    }
}

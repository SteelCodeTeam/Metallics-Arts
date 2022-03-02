package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.rudahee.metallics_arts.modules.items.combat.*;
import net.rudahee.metallics_arts.modules.items.metal_spike.*;
import net.rudahee.metallics_arts.modules.items.metalminds.bands.*;
import net.rudahee.metallics_arts.modules.items.metalminds.rings.*;
import net.rudahee.metallics_arts.modules.items.vials.vial.BigVial;
import net.rudahee.metallics_arts.modules.items.vials.vial.SmallVial;
import net.rudahee.metallics_arts.setup.Registration;
import net.rudahee.metallics_arts.setup.enums.extras.MetalMindData;
import net.rudahee.metallics_arts.setup.enums.extras.MetalSpikesData;
import net.rudahee.metallics_arts.setup.enums.gems.Gems;
import net.rudahee.metallics_arts.setup.enums.metals.Metal;
import net.rudahee.metallics_arts.setup.enums.metals.MetalBurningRecipeData;
import net.rudahee.metallics_arts.modules.items.vials.vial.Vial;
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
    static {
        Item.Properties properties = new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG).stacksTo(1);
        RegistryObject<Item> BandAluminumDuralumin = register("band_aluminum_duralumin",
                () -> {
                    Item item = new BandAluminumDuralumin(properties);
                    MetalMindData.valueOf("ALUMINUM_DURALUMIN").setBand(item);
                    return item;
                }
        );
        RegistryObject<Item> BandAtiumMalatium = register("band_atium_malatium",
                () -> {
                    Item item = new BandAtiumMalatium(properties);
                    MetalMindData.valueOf("ATIUM_MALTIUM").setBand(item);
                    return item;
                }
        );
        RegistryObject<Item> BandCadmiumBendalloy = register("band_cadmium_bendalloy",
                () -> {
                    Item item = new BandCadmiumBendalloy(properties);
                    MetalMindData.valueOf("CADMIUM_BENDALLOY").setBand(item);
                    return item;
                }
        );
        RegistryObject<Item> BandChromiumNicrosil = register("band_chromium_nicrosil",
                () -> {
                    Item item = new BandChromiumNicrosil(properties);
                    MetalMindData.valueOf("CHROMIUM_NICROSIL").setBand(item);
                    return item;
                }
        );
        RegistryObject<Item> BandCopperBronze = register("band_copper_bronze",
                () -> {
                    Item item = new BandCopperBronze(properties);
                    MetalMindData.valueOf("COPPER_BRONZE").setBand(item);
                    return item;
                }
        );
        RegistryObject<Item> BandElectrumGold = register("band_electrum_gold",
                () -> {
                    Item item = new BandElectrumGold(properties);
                    MetalMindData.valueOf("ELECTRUM_GOLD").setBand(item);
                    return item;
                }
        );
        RegistryObject<Item> BandLerasiumEttmetal = register("band_lerasium_ettmetal",
                () -> {
                    Item item = new BandLerasiumEttmetal(properties);
                    MetalMindData.valueOf("LERASIUM_ETTMETAL").setBand(item);
                    return item;
                }
        );
        RegistryObject<Item> BandPewterTin = register("band_pewter_tin",
                () -> {
                    Item item = new BandPwterTin(properties);
                    MetalMindData.valueOf("TIN_PEWTER").setBand(item);
                    return item;
                }
        );
        RegistryObject<Item> BandSteelIron = register("band_steel_iron",
                () -> {
                    Item item = new BandSteelIron(properties);
                    MetalMindData.valueOf("STEEL_IRON").setBand(item);
                    return item;
                }
        );
        RegistryObject<Item> BandZincBrass = register("band_zinc_brass",
                () -> {
                    Item item = new RingZincBrass(properties);
                    MetalMindData.valueOf("ZINC_BRASS").setBand(item);
                    return item;
                }
        );

        RegistryObject<Item> RingAluminumDuralumin = register("ring_aluminum_duralumin",
                () -> {
                    Item item = new RingAluminumDuralumin(properties);
                    MetalMindData.valueOf("ALUMINUM_DURALUMIN").setRing(item);
                    return item;
                }
        );
        RegistryObject<Item> RingAtiumMalatium = register("ring_atium_malatium",
                () -> {
                    Item item = new RingAtiumMalatium(properties);
                    MetalMindData.valueOf("ATIUM_MALTIUM").setRing(item);
                    return item;
                }
        );
        RegistryObject<Item> RingCadmiumBendalloy = register("ring_cadmium_bendalloy",
                () -> {
                    Item item = new RingCadmiumBendalloy(properties);
                    MetalMindData.valueOf("CADMIUM_BENDALLOY").setRing(item);
                    return item;
                }
        );
        RegistryObject<Item> RingChromiumNicrosil = register("ring_chromium_nicrosil",
                () -> {
                    Item item = new RingChromiumNicrosil(properties);
                    MetalMindData.valueOf("CHROMIUM_NICROSIL").setRing(item);
                    return item;
                }
        );
        RegistryObject<Item> RingCopperBronze = register("ring_copper_bronze",
                () -> {
                    Item item = new RingCopperBronze(properties);
                    MetalMindData.valueOf("COPPER_BRONZE").setRing(item);
                    return item;
                }
        );
        RegistryObject<Item> RingElectrumGold = register("ring_electrum_gold",
                () -> {
                    Item item = new RingElectrumGold(properties);
                    MetalMindData.valueOf("ELECTRUM_GOLD").setRing(item);
                    return item;
                }
        );
        RegistryObject<Item> RingLerasiumEttmetal = register("ring_lerasium_ettmetal",
                () -> {
                    Item item = new RingLerasiumEttmetal(properties);
                    MetalMindData.valueOf("LERASIUM_ETTMETAL").setRing(item);
                    return item;
                }
        );
        RegistryObject<Item> RingPewterTin = register("ring_pewter_tin",
                () -> {
                    Item item = new RingPewterTin(properties);
                    MetalMindData.valueOf("TIN_PEWTER").setRing(item);
                    return item;
                }
        );
        RegistryObject<Item> RingSteelIron = register("ring_steel_iron",
                () -> {
                    Item item = new RingSteelIron(properties);
                    MetalMindData.valueOf("STEEL_IRON").setRing(item);
                    return item;
                }
        );
        RegistryObject<Item> RingZincBrass = register("ring_zinc_brass",
                () -> {
                    Item item = new RingZincBrass(properties);
                    MetalMindData.valueOf("ZINC_BRASS").setRing(item);
                    return item;
                }
        );
    }

    //spikes
    static {
        Item.Properties properties = new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG).stacksTo(1);
        RegistryObject<Item> IronSpike = register("iron_spike",
                () -> {
                    Item item = new IronSpike(properties);
                    MetalSpikesData.valueOf("IRON").setSpike(item);
                    return item;
                }
        );
        RegistryObject<Item> SteelSpike = register("steel_spike",
                () -> {
                    Item item = new SteelSpike(properties);
                    MetalSpikesData.valueOf("STEEL").setSpike(item);
                    return item;
                }
        );
        RegistryObject<Item> TinSpike = register("tin_spike",
                () -> {
                    Item item = new TinSpike(properties);
                    MetalSpikesData.valueOf("TIN").setSpike(item);
                    return item;
                }
        );
        RegistryObject<Item> PewterSpike = register("pewter_spike",
                () -> {
                    Item item = new PewterSpike(properties);
                    MetalSpikesData.valueOf("PEWTER").setSpike(item);
                    return item;
                }
        );
        RegistryObject<Item> CopperSpike = register("copper_spike",
                () -> {
                    Item item = new CopperSpike(properties);
                    MetalSpikesData.valueOf("COPPER").setSpike(item);
                    return item;
                }
        );
        RegistryObject<Item> BronzeSpike = register("bronze_spike",
                () -> {
                    Item item = new BronzeSpike(properties);
                    MetalSpikesData.valueOf("BRONZE").setSpike(item);
                    return item;
                }
        );
        RegistryObject<Item> ZincSpike = register("zinc_spike",
                () -> {
                    Item item = new ZincSpike(properties);
                    MetalSpikesData.valueOf("ZINC").setSpike(item);
                    return item;
                }
        );
        RegistryObject<Item> BrassSpike = register("brass_spike",
                () -> {
                    Item item = new BrassSpike(properties);
                    MetalSpikesData.valueOf("BRASS").setSpike(item);
                    return item;
                }
        );
        RegistryObject<Item> ChromiumSpike = register("chromium_spike",
                () -> {
                    Item item = new ChromiumSpike(properties);
                    MetalSpikesData.valueOf("CHROMIUM").setSpike(item);
                    return item;
                }
        );
        RegistryObject<Item> NicrosilSpike = register("nicrosil_spike",
                () -> {
                    Item item = new NicrosilSpike(properties);
                    MetalSpikesData.valueOf("NICROSIL").setSpike(item);
                    return item;
                }
        );
        RegistryObject<Item> AluminumSpike = register("aluminum_spike",
                () -> {
                    Item item = new AluminumSpike(properties);
                    MetalSpikesData.valueOf("ALUMINUM").setSpike(item);
                    return item;
                }
        );
        RegistryObject<Item> DuraluminSpike = register("duralumin_spike",
                () -> {
                    Item item = new DuraluminSpike(properties);
                    MetalSpikesData.valueOf("DURALUMIN").setSpike(item);
                    return item;
                }
        );
        RegistryObject<Item> CadmiumSpike = register("cadmium_spike",
                () -> {
                    Item item = new CadmiumSpike(properties);
                    MetalSpikesData.valueOf("CADMIUM").setSpike(item);
                    return item;
                }
        );
        RegistryObject<Item> BendalloySpike = register("bendalloy_spike",
                () -> {
                    Item item = new BendalloySpike(properties);
                    MetalSpikesData.valueOf("BENDALLOY").setSpike(item);
                    return item;
                }
        );
        RegistryObject<Item> ElectrumSpike = register("electrum_spike",
                () -> {
                    Item item = new ElectrumSpike(properties);
                    MetalSpikesData.valueOf("ELECTRUM").setSpike(item);
                    return item;
                }
        );
        RegistryObject<Item> GoldSpike = register("gold_spike",
                () -> {
                    Item item = new GoldSpike(properties);
                    MetalSpikesData.valueOf("GOLD").setSpike(item);
                    return item;
                }
        );
        RegistryObject<Item> AtiumSpike = register("atium_spike",
                () -> {
                    Item item = new AtiumSpike(properties);
                    MetalSpikesData.valueOf("ATIUM").setSpike(item);
                    return item;
                }
        );
        RegistryObject<Item> MalatiumSpike = register("malatium_spike",
                () -> {
                    Item item = new MalatiumSpike(properties);
                    MetalSpikesData.valueOf("MALATIUM").setSpike(item);
                    return item;
                }
        );
        RegistryObject<Item> LerasiumSpike = register("lerasium_spike",
                () -> {
                    Item item = new LerasiumSpike(properties);
                    MetalSpikesData.valueOf("LERASIUM").setSpike(item);
                    return item;
                }
        );
        RegistryObject<Item> EttmetalSpike = register("ettmetal_spike",
                () -> {
                    Item item = new EttmetalSpike(properties);
                    MetalSpikesData.valueOf("ETTMETAL").setSpike(item);
                    return item;
                }
        );

    }

    //weapons
    static {
        RegistryObject<Item> ObsidianDagger = register("obsidian_dagger",
                () -> {
                    return new ObsidianDagger(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG).tab(ItemGroup.TAB_COMBAT));
                }
        );
        RegistryObject<Item> CristalDagger = register("cristal_dagger",
                () -> {
                    return new CristalDagger(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG).tab(ItemGroup.TAB_COMBAT));
                }
        );
        RegistryObject<Item> KolossBlade = register("koloss_blade",
                () -> {
                    return new KolossBlade(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG).tab(ItemGroup.TAB_COMBAT));
                }
        );
        RegistryObject<Item> DuelingStaff = register("dueling_staff",
                () -> {
                    return new DuelingStaff(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG).tab(ItemGroup.TAB_COMBAT));
                }
        );
        RegistryObject<Item> ObsidianAxe = register("obsidian_axe",
                () -> {
                    return new ObsidianAxe(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG).tab(ItemGroup.TAB_COMBAT));
                }
        );
    }

    public static final RegistryObject<Item> SMALL_VIAL = register
            ("small_vial", () -> new SmallVial(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG).food(new Food.Builder().nutrition(0).build())));

    public static final RegistryObject<Item> BIG_VIAL = register
            ("big_vial", () -> new BigVial(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG).food(new Food.Builder().nutrition(0).build())));

    public static void register() {
    }

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> itemSupplier) {
        return Registration.ITEMS.register(name, itemSupplier);
    }
}

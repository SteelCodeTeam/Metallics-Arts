package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.rudahee.metallics_arts.modules.items.metalminds.bands.*;
import net.rudahee.metallics_arts.modules.items.metalminds.rings.*;
import net.rudahee.metallics_arts.setup.Registration;
import net.rudahee.metallics_arts.setup.enums.extras.MetalMindData;
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

    static {
         RegistryObject<Item> BandAluminumDuralumin = register("band_aluminum_duralumin",
                 () ->{
                     Item item = new BandAluminumDuralumin(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                     MetalMindData.valueOf("ALUMINUM_DURALUMIN").setBand(item);
                     return item;
                 }
                 );
        RegistryObject<Item> BandAtiumMalatium = register("band_atium_malatium",
                () ->{
                    Item item = new BandAtiumMalatium(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                    MetalMindData.valueOf("ATIUM_MALTIUM").setBand(item);
                    return item;
                }
        );
        RegistryObject<Item> BandCadmiumBendalloy = register("band_cadmium_bendalloy",
                () ->{
                    Item item = new BandCadmiumBendalloy(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                    MetalMindData.valueOf("CADMIUM_BENDALLOY").setBand(item);
                    return item;
                }
        );
        RegistryObject<Item> BandChromiumNicrosil = register("band_chromium_nicrosil",
                () ->{
                    Item item = new BandChromiumNicrosil(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                    MetalMindData.valueOf("CHROMIUM_NICROSIL").setBand(item);
                    return item;
                }
        );
        RegistryObject<Item> BandCopperBronze = register("band_copper_bronze",
                () ->{
                    Item item = new BandCopperBronze(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                    MetalMindData.valueOf("COPPER_BRONZE").setBand(item);
                    return item;
                }
        );
        RegistryObject<Item> BandElectrumGold = register("band_electrum_gold",
                () ->{
                    Item item = new BandElectrumGold(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                    MetalMindData.valueOf("ELECTRUM_GOLD").setBand(item);
                    return item;
                }
        );
        RegistryObject<Item> BandLerasiumEttmetal = register("band_lerasium_ettmetal",
                () ->{
                    Item item = new BandLerasiumEttmetal(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                    MetalMindData.valueOf("LERASIUM_ETTMETAL").setBand(item);
                    return item;
                }
        );
        RegistryObject<Item> BandPewterTin = register("band_pewter_tin",
                () ->{
                    Item item = new BandPwterTin(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                    MetalMindData.valueOf("TIN_PEWTER").setBand(item);
                    return item;
                }
        );
        RegistryObject<Item> BandSteelIron = register("band_steel_iron",
                () ->{
                    Item item = new BandSteelIron(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                    MetalMindData.valueOf("STEEL_IRON").setBand(item);
                    return item;
                }
        );
        RegistryObject<Item> BandZincBrass = register("band_zinc_brass",
                () ->{
                    Item item = new RingZincBrass(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                    MetalMindData.valueOf("ZINC_BRASS").setBand(item);
                    return item;
                }
        );

        RegistryObject<Item> RingAluminumDuralumin = register("ring_aluminum_duralumin",
                () ->{
                    Item item = new RingAluminumDuralumin(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                    MetalMindData.valueOf("ALUMINUM_DURALUMIN").setRing(item);
                    return item;
                }
        );
        RegistryObject<Item> RingAtiumMalatium = register("ring_atium_malatium",
                () ->{
                    Item item = new RingAtiumMalatium(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                    MetalMindData.valueOf("ATIUM_MALTIUM").setRing(item);
                    return item;
                }
        );
        RegistryObject<Item> RingCadmiumBendalloy = register("ring_cadmium_bendalloy",
                () ->{
                    Item item = new RingCadmiumBendalloy(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                    MetalMindData.valueOf("CADMIUM_BENDALLOY").setRing(item);
                    return item;
                }
        );
        RegistryObject<Item> RingChromiumNicrosil = register("ring_chromium_nicrosil",
                () ->{
                    Item item = new RingChromiumNicrosil(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                    MetalMindData.valueOf("CHROMIUM_NICROSIL").setRing(item);
                    return item;
                }
        );
        RegistryObject<Item> RingCopperBronze = register("ring_copper_bronze",
                () ->{
                    Item item = new RingCopperBronze(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                    MetalMindData.valueOf("COPPER_BRONZE").setRing(item);
                    return item;
                }
        );
        RegistryObject<Item> RingElectrumGold = register("ring_electrum_gold",
                () ->{
                    Item item = new RingElectrumGold(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                    MetalMindData.valueOf("ELECTRUM_GOLD").setRing(item);
                    return item;
                }
        );
        RegistryObject<Item> RingLerasiumEttmetal = register("ring_lerasium_ettmetal",
                () ->{
                    Item item = new RingLerasiumEttmetal(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                    MetalMindData.valueOf("LERASIUM_ETTMETAL").setRing(item);
                    return item;
                }
        );
        RegistryObject<Item> RingPewterTin = register("ring_pewter_tin",
                () ->{
                    Item item = new RingPewterTin(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                    MetalMindData.valueOf("TIN_PEWTER").setRing(item);
                    return item;
                }
        );
        RegistryObject<Item> RingSteelIron = register("ring_steel_iron",
                () ->{
                    Item item = new RingSteelIron(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                    MetalMindData.valueOf("STEEL_IRON").setRing(item);
                    return item;
                }
        );
        RegistryObject<Item> RingZincBrass = register("ring_zinc_brass",
                () ->{
                    Item item = new RingZincBrass(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG));
                    MetalMindData.valueOf("ZINC_BRASS").setRing(item);
                    return item;
                }
        );
    }

    public static final RegistryObject<Item> VIAL = register
            ("vial", () -> new Vial(new Item.Properties().food(new Food.Builder().nutrition(0).build()).tab(ModItemGroup.METALLIC_ARTS_TAG)));

    public static void register() {
    }

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> itemSupplier) {
        return Registration.ITEMS.register(name, itemSupplier);
    }
}

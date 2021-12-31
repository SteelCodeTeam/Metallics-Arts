package net.rudahee.metallics_arts.setup;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.ModItemGroup;
import net.rudahee.metallics_arts.setup.enums.Gems;
import net.rudahee.metallics_arts.setup.enums.Metal;
import net.rudahee.metallics_arts.setup.enums.MetalBurningRecipeData;
import net.rudahee.metallics_arts.setup.enums.MetalGenerationData;
import net.rudahee.metallics_arts.setup.metalMind.band.*;
import net.rudahee.metallics_arts.setup.metalMind.rings.*;
import net.rudahee.metallics_arts.setup.vial.Vial;

import java.rmi.registry.Registry;
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

    public static final RegistryObject<Item> BandAluminumDuralumin = register
            ("band_aluminum_duralumin", ()-> new BandAluminumDuralumin(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG)));
    public static final RegistryObject<Item> BandAtiumMalatium = register
            ("band_atium_malatium", ()-> new BandAtiumMalatium(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG)));
    public static final RegistryObject<Item> BandCadmiumBendalloy = register
            ("band_cadmium_endalloy", ()-> new BandCadmiumBendalloy(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG)));
    public static final RegistryObject<Item> BandChromiumNicrosil = register
            ("band_chromium_nicrosil", ()-> new BandChromiumNicrosil(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG)));
    public static final RegistryObject<Item> BandCopperBronze = register
            ("band_copper_bronze", ()-> new BandCopperBronze(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG)));
    public static final RegistryObject<Item> BandElectrumGold = register
            ("band_electrum_gold", ()-> new BandElectrumGold(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG)));
    public static final RegistryObject<Item> BandLerasiumEttmetal = register
            ("band_lerasium_ettmetal", ()-> new BandLerasiumEttmetal(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG)));
    public static final RegistryObject<Item> BandPewterTin = register
            ("band_pewter_tin", ()-> new BandPwterTin(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG)));
    public static final RegistryObject<Item> BandSteelIron = register
            ("band_steel_iron", ()-> new BandSteelIron(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG)));
    public static final RegistryObject<Item> BandZincBrass = register
            ("band_zinc_brass", ()-> new RingZincBrass(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG)));

    public static final RegistryObject<Item> RingAluminumDuralumin = register
            ("ring_aluminum_duralumin", ()-> new RingAluminumDuralumin(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG)));
    public static final RegistryObject<Item> RingAtiumMalatium = register
            ("ring_atium_malatium", ()-> new RingAtiumMalatium(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG)));
    public static final RegistryObject<Item> RingCadmiumBendalloy = register
            ("ring_cadmium_endalloy", ()-> new RingCadmiumBendalloy(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG)));
    public static final RegistryObject<Item> RingChromiumNicrosil = register
            ("ring_chromium_nicrosil", ()-> new RingChromiumNicrosil(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG)));
    public static final RegistryObject<Item> RingCopperBronze = register
            ("ring_copper_bronze", ()-> new RingCopperBronze(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG)));
    public static final RegistryObject<Item> RingElectrumGold = register
            ("ring_electrum_gold", ()-> new RingElectrumGold(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG)));
    public static final RegistryObject<Item> RingLerasiumEttmetal = register
            ("ring_lerasium_ettmetal", ()-> new RingLerasiumEttmetal(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG)));
    public static final RegistryObject<Item> RingPewterTin = register
            ("ring_pewter_tin", ()-> new RingPewterTin(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG)));
    public static final RegistryObject<Item> RingSteelIron = register
            ("ring_steel_iron", ()-> new RingSteelIron(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG)));
    public static final RegistryObject<Item> RingZincBrass = register
            ("ring_zinc_brass", ()-> new RingZincBrass(new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG)));
    public static final RegistryObject<Item> VIAL = register
            ("vial", () -> new Vial(new Item.Properties().food(new Food.Builder().nutrition(0).build()).tab(ModItemGroup.METALLIC_ARTS_TAG)));



    protected static void register() {
    }

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> itemSupplier) {
        return Registration.ITEMS.register(name, itemSupplier);
    }
}

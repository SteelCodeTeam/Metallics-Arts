package net.rudahee.metallics_arts.setup;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.rudahee.metallics_arts.ModItemGroup;
import net.rudahee.metallics_arts.setup.enums.Gems;
import net.rudahee.metallics_arts.setup.enums.Metal;

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

    protected static void register() {
    }

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> itemSupplier) {
        return Registration.ITEMS.register(name, itemSupplier);
    }
}

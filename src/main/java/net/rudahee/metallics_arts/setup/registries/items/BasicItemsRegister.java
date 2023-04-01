package net.rudahee.metallics_arts.setup.registries.items;


import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

import java.util.Arrays;
import java.util.List;


/**
 * Registration of the simple items.
 *
 * @see ModItemsRegister
 */
public class BasicItemsRegister {

    public static void register() {

        List<MetalEnum> metalList = Arrays.asList(MetalEnum.values());
        List<GemsEnum> gemList = Arrays.asList(GemsEnum.values());

       // Metals
        metalList.forEach(metal -> {
            //Ingots
            MetallicsArts.registerItem(metal.getMetalNameLower() + "_ingot", () -> {
                Item item = new Item(new Item.Properties().tab(MetallicsArts.MA_TAB).stacksTo(64));
                ModItemsRegister.ITEM_METAL_INGOT.put(metal.getMetalNameLower(), item);
                if (metal.isAlloy()) {

                    //AlloyRecipeEnum.valueOf(metal.getMetalNameUpper()).setItem(item);

             //       AlloyRecipeEnum.valueOf(metal.getMetalNameUpper()).setItem(item);

                }
                return item;
            });
            // Nuggets
            MetallicsArts.registerItem(metal.getMetalNameLower() + "_nugget", () -> {
                Item item = new Item(new Item.Properties().tab(MetallicsArts.MA_TAB).stacksTo(64));
                ModItemsRegister.ITEM_METAL_NUGGET.put(metal.getMetalNameLower(), item);
                return item;
            });
            // Raws
            if (!metal.isAlloy()) {
                MetallicsArts.registerItem("raw_" + metal.getMetalNameLower(), () -> {
                    Item item = new Item(new Item.Properties().tab(MetallicsArts.MA_TAB).stacksTo(64));
                    ModItemsRegister.ITEM_RAW_METAL.put(metal.getMetalNameLower(), item);
                    return item;
                });
            }
        });


        MetallicsArts.registerItem("copper_nugget",() -> {
            Item item = new Item(new Item.Properties().tab(MetallicsArts.MA_TAB).stacksTo(64));
            ModItemsRegister.ITEM_METAL_NUGGET.put("copper_nugget", item);
            return item;
        });
        // Gems
        gemList.forEach(gem -> {
            // Ingot
            MetallicsArts.registerItem(gem.getGemNameLower(),
                () -> {
                    Item item = new Item(new Item.Properties().tab(MetallicsArts.MA_TAB));
                    ModItemsRegister.ITEM_GEMS_BASE.put(gem.getGemNameLower(), item);
                    if (gem.getGemNameLower() == "malatium") {
                        //AlloyRecipeEnum.valueOf(gem.getGemNameUpper()).setItem(item);

                  //      AlloyRecipeEnum.valueOf(gem.getGemNameUpper()).setItem(item);

                    }
                    return item;
                });

            // Nuggets
            MetallicsArts.registerItem(gem.getGemNameLower() + "_nugget",
                () -> {
                    Item item = new Item(new Item.Properties().tab(MetallicsArts.MA_TAB));
                    ModItemsRegister.ITEM_GEMS_NUGGET.put(gem.getGemNameLower(), item);
                    return item;
                });
        });

        ModItemsRegister.DUMMY_BOOK =  MetallicsArts.registerItem("dummy_book",() -> new Item(new Item.Properties()));

    }


}

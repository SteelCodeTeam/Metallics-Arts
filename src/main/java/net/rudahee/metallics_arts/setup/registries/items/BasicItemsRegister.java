package net.rudahee.metallics_arts.setup.registries.items;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.SignItem;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.setup.registries.ModSoundsRegister;

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
                Item item = new Item(new Item.Properties().stacksTo(64));
                ModItemsRegister.ITEM_METAL_INGOT.put(metal.getMetalNameLower(), item);
                if (metal.isAlloy()) {

                    //AlloyRecipeEnum.valueOf(metal.getMetalNameUpper()).setItem(item);

             //       AlloyRecipeEnum.valueOf(metal.getMetalNameUpper()).setItem(item);

                }
                return item;
            });
            // Nuggets
            MetallicsArts.registerItem(metal.getMetalNameLower() + "_nugget", () -> {
                Item item = new Item(new Item.Properties().stacksTo(64));
                ModItemsRegister.ITEM_METAL_NUGGET.put(metal.getMetalNameLower(), item);
                return item;
            });
            // Raws
            if (!metal.isAlloy()) {
                MetallicsArts.registerItem("raw_" + metal.getMetalNameLower(), () -> {
                    Item item = new Item(new Item.Properties().stacksTo(64));
                    ModItemsRegister.ITEM_RAW_METAL.put(metal.getMetalNameLower(), item);
                    return item;
                });
            }
        });

        MetallicsArts.registerItem("copper_nugget",() -> {
            Item item = new Item(new Item.Properties().stacksTo(64));
            ModItemsRegister.ITEM_METAL_NUGGET.put("copper", item);
            return item;
        });
        // Gems
        gemList.forEach(gem -> {
            // Ingot
            MetallicsArts.registerItem(gem.getGemNameLower(),
                () -> {
                    Item item = new Item(new Item.Properties());
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
                    Item item = new Item(new Item.Properties());
                    ModItemsRegister.ITEM_GEMS_NUGGET.put(gem.getGemNameLower(), item);
                    return item;
                });
        });

        ModItemsRegister.METALLICS_ARTS_BOOK =  MetallicsArts.registerItem("metallics_arts_book",() -> new Item(new Item.Properties()));
        ModItemsRegister.IRON_SIGN =
                MetallicsArts.registerItem("iron_sign",() -> new SignItem(new Item.Properties().stacksTo(16),
                        ModBlocksRegister.IRON_STANDING_SIGN.get(), ModBlocksRegister.IRON_WALL_SIGN.get()));

        ModItemsRegister.GOLD_SIGN =
                MetallicsArts.registerItem("gold_sign",() -> new SignItem(new Item.Properties().stacksTo(16),
                        ModBlocksRegister.GOLD_STANDING_SIGN.get(), ModBlocksRegister.GOLD_WALL_SIGN.get()));
        ModItemsRegister.COPPER_SIGN =
                MetallicsArts.registerItem("copper_sign",() -> new SignItem(new Item.Properties().stacksTo(16),
                        ModBlocksRegister.COPPER_STANDING_SIGN.get(), ModBlocksRegister.COPPER_WALL_SIGN.get()));
        ModItemsRegister.ALUMINUM_SIGN =
                MetallicsArts.registerItem("aluminum_sign",() -> new SignItem(new Item.Properties().stacksTo(16),
                        ModBlocksRegister.ALUMINUM_STANDING_SIGN.get(), ModBlocksRegister.ALUMINUM_WALL_SIGN.get()));


    ModItemsRegister.SAZED_DISC =
            MetallicsArts.registerItem("sazed_disc",() -> new RecordItem(8, ModSoundsRegister.SAZED_SONG,new Item.Properties().stacksTo(1), 2180));
    }

}

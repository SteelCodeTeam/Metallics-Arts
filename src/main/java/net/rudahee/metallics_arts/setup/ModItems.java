package net.rudahee.metallics_arts.setup;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.rudahee.metallics_arts.setup.enums.Metal;

import java.util.Arrays;


import java.util.List;
import java.util.function.Supplier;


public class ModItems {

    static  {
        List<Metal> metalList = Arrays.asList(Metal.values());

        metalList.forEach(metal -> {
            register(metal.getMetalNameLower() + "_ingot",
                    ()-> (new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS))));
        });

    }

    static {
        List<Metal> metalList = Arrays.asList(Metal.values());

        metalList.forEach(metal ->  {
            register( metal.getMetalNameLower() + "_nugget",
                    ()-> (new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS))));
        });
    }

    protected static void register() {}

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> itemSupplier) {
        return Registration.ITEMS.register(name, itemSupplier);
    }
}

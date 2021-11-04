package net.rudahee.metallics_arts.setup;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.rudahee.metallics_arts.setup.enums.Gems;
import net.rudahee.metallics_arts.setup.enums.Metal;

import java.util.Arrays;


import java.util.List;
import java.util.function.Supplier;


public class ModItems {

    static {
        // Generating ingots and nuggets off all metals
        List<Metal> metalList = Arrays.asList(Metal.values());

        metalList.forEach(metal -> {
            register(metal.getMetalNameLower() + "_ingot",
                    () -> (new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS))));

            register(metal.getMetalNameLower() + "_nugget",
                    () -> (new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS))));
        });
    }

    static {
        List<Gems> gemList = Arrays.asList(Gems.values());

        gemList.forEach(gem -> {
            register(gem.getGemNameLower(),
                    () -> (new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS))));

            register(gem.getGemNameLower() + "_nugget",
                    () -> (new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS))));
        });
    }

    protected static void register() {}

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> itemSupplier) {
        return Registration.ITEMS.register(name, itemSupplier);
    }
}

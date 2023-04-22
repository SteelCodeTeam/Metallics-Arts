package net.rudahee.metallics_arts.setup.registries.items;

import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.MetallicsArts;

import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;


/**
 * Register for core items to build weapons or armors.
 *
 * @author SteelCode Team
 * @since 1.5.6
 */
public class CoreBuilderItemRegister {

    public static void register() {

        ModItemsRegister.CORE_OBSIDIAN = MetallicsArts.registerItem("core_obsidian",
                () -> new Item(new Item.Properties().tab(MetallicsArts.MA_TAB)));
        ModItemsRegister.CORE_ALUMINUM = MetallicsArts.registerItem("core_aluminum",
                () -> new Item(new Item.Properties().tab(MetallicsArts.MA_TAB)));
        ModItemsRegister.CORE_STEEL = MetallicsArts.registerItem("core_steel",
                () -> new Item(new Item.Properties().tab(MetallicsArts.MA_TAB)));

    }
}

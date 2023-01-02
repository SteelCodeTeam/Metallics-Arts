package net.rudahee.metallics_arts.setup.registries.items;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.custom_items.weapons.*;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

public class WeaponsRegister {

    private static final Item.Properties PROPERTY_WEAPONS = new Item.Properties().tab(MetallicsArts.MA_TAB).tab(CreativeModeTab.TAB_COMBAT);

    public static void register (){

        ModItemsRegister.OBSIDIAN_DAGGER = MetallicsArts.registerItem("obsidian_dagger",
                () -> new ObsidianDagger(PROPERTY_WEAPONS)
        );

        ModItemsRegister.CRISTAL_DAGGER = MetallicsArts.registerItem("cristal_dagger",
                () -> new CristalDagger(PROPERTY_WEAPONS)
        );

        ModItemsRegister.KOLOSS_BLADE = MetallicsArts.registerItem("koloss_blade",
                () -> new KolossBlade(PROPERTY_WEAPONS)
        );
        ModItemsRegister.DUELING_STAFF = MetallicsArts.registerItem("dueling_staff",
                () -> new DuelingStaff(PROPERTY_WEAPONS)
        );
        ModItemsRegister.OBSIDIAN_AXE = MetallicsArts.registerItem("obsidian_axe",
                () -> new ObsidianAxe(PROPERTY_WEAPONS)
        );
    }
}

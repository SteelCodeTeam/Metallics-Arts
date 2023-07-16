package net.rudahee.metallics_arts.setup.registries.items;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.custom_items.coins.CopperCoin;
import net.rudahee.metallics_arts.modules.custom_items.weapons.guns.PistolTest;
import net.rudahee.metallics_arts.modules.custom_items.weapons.mele.*;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

/**
 * Registration of the weapons items.
 *
 * @see ModItemsRegister
 */
public class WeaponsRegister {

    private static final Item.Properties WEAPONS_PROPERTIES = new Item.Properties().tab(MetallicsArts.MA_TAB).tab(CreativeModeTab.TAB_COMBAT);
    private static final Item.Properties COIN_PROPERTIES = new Item.Properties().tab(MetallicsArts.MA_TAB).tab(CreativeModeTab.TAB_COMBAT).stacksTo(64);
    public static void register (){
        ModItemsRegister.OBSIDIAN_DAGGER = MetallicsArts.registerItem("obsidian_dagger",
                () -> new ObsidianDagger(WEAPONS_PROPERTIES)
        );
        ModItemsRegister.SILVER_KNIFE = MetallicsArts.registerItem("silver_knife",
                () -> new CristalDagger(WEAPONS_PROPERTIES)
        );
        ModItemsRegister.KOLOSS_BLADE = MetallicsArts.registerItem("koloss_blade",
                () -> new KolossBlade(WEAPONS_PROPERTIES)
        );
        ModItemsRegister.DUELING_STAFF = MetallicsArts.registerItem("dueling_staff",
                () -> new DuelingStaff(WEAPONS_PROPERTIES)
        );
        ModItemsRegister.OBSIDIAN_AXE = MetallicsArts.registerItem("obsidian_axe",
                () -> new ObsidianAxe(WEAPONS_PROPERTIES)
        );

        ModItemsRegister.COPPER_COIN = MetallicsArts.registerItem("copper_coin",
                () -> new CopperCoin(COIN_PROPERTIES, 1, 5));

        ModItemsRegister.PISTOL_TEST = MetallicsArts.registerItem("pistol_test",
                () -> new PistolTest(new Item.Properties().stacksTo(1).tab(MetallicsArts.MA_TAB)));
    }
}

package net.rudahee.metallics_arts.setup.registries.items;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.GunType;
import net.rudahee.metallics_arts.modules.custom_items.coins.CopperCoin;
import net.rudahee.metallics_arts.modules.custom_items.weapons.guns.BasicGun;
import net.rudahee.metallics_arts.modules.custom_items.weapons.guns.Rifle;
import net.rudahee.metallics_arts.modules.custom_items.weapons.guns.ShotGun;
import net.rudahee.metallics_arts.modules.custom_items.weapons.mele.*;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

/**
 * Registration of the weapons items.
 *
 * @see ModItemsRegister
 */
public class WeaponsRegister {

    private static final Item.Properties WEAPONS_PROPERTIES = new Item.Properties().tab(MetallicsArts.MA_TAB).tab(CreativeModeTab.TAB_COMBAT).stacksTo(1);
    private static final Item.Properties COIN_PROPERTIES = new Item.Properties().tab(MetallicsArts.MA_TAB).tab(CreativeModeTab.TAB_COMBAT).stacksTo(64);
    public static void register () {
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

        ModItemsRegister.REVOLVER = MetallicsArts.registerItem(GunType.REVOLVER.getName(),
                () -> new BasicGun(WEAPONS_PROPERTIES, GunType.REVOLVER));
        ModItemsRegister.VINDICATOR = MetallicsArts.registerItem(GunType.VINDICATOR.getName(),
                () -> new BasicGun(WEAPONS_PROPERTIES, GunType.VINDICATOR));
        ModItemsRegister.SHOTGUN = MetallicsArts.registerItem(GunType.SHOTGUN.getName(),
                () -> new ShotGun(WEAPONS_PROPERTIES));

        ModItemsRegister.RIFLE = MetallicsArts.registerItem(GunType.RIFLE.getName(),
                () -> new Rifle(WEAPONS_PROPERTIES));

        ModItemsRegister.PISTOL_LEAD_BULLET = MetallicsArts.registerItem("pistol_lead_bullet",
                () -> new Item(COIN_PROPERTIES));

        ModItemsRegister.PISTOL_ALUMINUM_BULLET = MetallicsArts.registerItem("pistol_aluminum_bullet",
                () -> new Item(COIN_PROPERTIES));

        ModItemsRegister.SHOTGUN_LEAD_BULLET = MetallicsArts.registerItem("shotgun_lead_bullet",
                () -> new Item(COIN_PROPERTIES));

        ModItemsRegister.SHOTGUN_ALUMINUM_BULLET = MetallicsArts.registerItem("shotgun_aluminum_bullet",
                () -> new Item(COIN_PROPERTIES));

        ModItemsRegister.RIFLE_LEAD_BULLET = MetallicsArts.registerItem("rifle_lead_bullet",
                () -> new Item(COIN_PROPERTIES));

        ModItemsRegister.RIFLE_ALUMINUM_BULLET = MetallicsArts.registerItem("rifle_aluminum_bullet",
                () -> new Item(COIN_PROPERTIES));

    }
}

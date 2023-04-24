package net.rudahee.metallics_arts.setup.registries.items;

import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.custom_items.coins.Coin;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

public class CoinsRegister {
    private static final Item.Properties PROPERTIES = new Item.Properties().tab(MetallicsArts.MA_TAB);

    public static void register() {
        ModItemsRegister.COIN = MetallicsArts.registerItem("coin",
                () -> new Coin(PROPERTIES));
    }
}

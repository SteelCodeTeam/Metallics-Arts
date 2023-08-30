package net.rudahee.metallics_arts.setup.registries.items;

import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

/**
 * Registration of the metal icons items.
 *
 * @see ModItemsRegister
 */
public class IconsRegister {
    public static void register() {
        for (MetalTagEnum metal: MetalTagEnum.values()) {
            // Allomantic Icons
            MetallicsArts.registerItem(metal.getNameLower() + "_allomantic_icon",
                ()-> {
                    Item item = new Item(new Item.Properties());
                    if (!metal.isDivine()) {
                        ModItemsRegister.ITEM_ICONS_ALLOMANCY.put(metal.getNameLower(),item);
                    } else {
                        ModItemsRegister.ITEM_ICONS_ALLOMANCY_DIVINE.put(metal.getNameLower(),item);
                    }
                    return item;
                });
            // Feruchemic Icons
            MetallicsArts.registerItem(metal.getNameLower()+"_feruchemic_icon",
                ()-> {
                    Item item = new Item(new Item.Properties());
                    if (!metal.isDivine()) {
                        ModItemsRegister.ITEM_ICONS_FERUCHEMIC.put(metal.getNameLower(), item);
                    } else {
                        ModItemsRegister.ITEM_ICONS_FERUCHEMIC_DIVINE.put(metal.getNameLower(),item);
                    }
                    return item;
                });

        }
    }
}

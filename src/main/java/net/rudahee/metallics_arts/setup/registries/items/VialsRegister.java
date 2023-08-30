package net.rudahee.metallics_arts.setup.registries.items;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.custom_items.vials.LargeVial;
import net.rudahee.metallics_arts.modules.custom_items.vials.SmallVial;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

/**
 * Registration of the vials items.
 *
 * @see ModItemsRegister
 */
public class VialsRegister {
    private static final Item.Properties PROPERTY_VIALS =
            new Item.Properties().stacksTo(1).food(new FoodProperties.Builder().nutrition(0).build());

    public static void register () {
        ModItemsRegister.LARGE_VIAL =  MetallicsArts.registerItem("large_vial",
                () -> new LargeVial(PROPERTY_VIALS));

        ModItemsRegister.SMALL_VIAL = MetallicsArts.registerItem("small_vial",
                () -> new SmallVial(PROPERTY_VIALS));
    }
}

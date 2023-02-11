package net.rudahee.metallics_arts.setup.registries.items;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.SpikeEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_spikes.MetalSpike;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

/**
 * Registration of the metal spikes items.
 *
 * @see ModItemsRegister
 */
public class SpikesRegister {
    private static final Item.Properties PROPERTY_SPIKE = new Item.Properties().tab(MetallicsArts.MA_TAB).stacksTo(1);

    public static void register() {

        MetallicsArts.registerItem("iron_spike",
                () -> {
                    Item item = new MetalSpike(PROPERTY_SPIKE, MetalTagEnum.IRON);
                    SpikeEnum.valueOf("IRON").setSpike(item);
                    return item;
                }
        );
        MetallicsArts.registerItem("steel_spike",
                () -> {
                    Item item = new MetalSpike(PROPERTY_SPIKE, MetalTagEnum.STEEL);
                    SpikeEnum.valueOf("STEEL").setSpike(item);
                    return item;
                }
        );
        MetallicsArts.registerItem("tin_spike",
                () -> {
                    Item item = new MetalSpike(PROPERTY_SPIKE, MetalTagEnum.TIN);
                    SpikeEnum.valueOf("TIN").setSpike(item);
                    return item;
                }
        );
        MetallicsArts.registerItem("pewter_spike",
                () -> {
                    Item item = new MetalSpike(PROPERTY_SPIKE, MetalTagEnum.PEWTER);
                    SpikeEnum.valueOf("PEWTER").setSpike(item);
                    return item;
                }
        );
        MetallicsArts.registerItem("copper_spike",
                () -> {
                    Item item = new MetalSpike(PROPERTY_SPIKE, MetalTagEnum.COPPER);
                    SpikeEnum.valueOf("COPPER").setSpike(item);
                    return item;
                }
        );
        MetallicsArts.registerItem("bronze_spike",
                () -> {
                    Item item = new MetalSpike(PROPERTY_SPIKE, MetalTagEnum.BRONZE);
                    SpikeEnum.valueOf("BRONZE").setSpike(item);
                    return item;
                }
        );
        MetallicsArts.registerItem("zinc_spike",
                () -> {
                    Item item = new MetalSpike(PROPERTY_SPIKE, MetalTagEnum.ZINC);
                    SpikeEnum.valueOf("ZINC").setSpike(item);
                    return item;
                }
        );
        MetallicsArts.registerItem("brass_spike",
                () -> {
                    Item item = new MetalSpike(PROPERTY_SPIKE, MetalTagEnum.BRASS);
                    SpikeEnum.valueOf("BRASS").setSpike(item);
                    return item;
                }
        );
        MetallicsArts.registerItem("chromium_spike",
                () -> {
                    Item item = new MetalSpike(PROPERTY_SPIKE, MetalTagEnum.CHROMIUM);
                    SpikeEnum.valueOf("CHROMIUM").setSpike(item);
                    return item;
                }
        );
        MetallicsArts.registerItem("nicrosil_spike",
                () -> {
                    Item item = new MetalSpike(PROPERTY_SPIKE, MetalTagEnum.NICROSIL);
                    SpikeEnum.valueOf("NICROSIL").setSpike(item);
                    return item;
                }
        );
        MetallicsArts.registerItem("aluminum_spike",
                () -> {
                    Item item = new MetalSpike(PROPERTY_SPIKE, MetalTagEnum.ALUMINUM);
                    SpikeEnum.valueOf("ALUMINUM").setSpike(item);
                    return item;
                }
        );
        MetallicsArts.registerItem("duralumin_spike",
                () -> {
                    Item item = new MetalSpike(PROPERTY_SPIKE, MetalTagEnum.DURALUMIN);
                    SpikeEnum.valueOf("DURALUMIN").setSpike(item);
                    return item;
                }
        );
        MetallicsArts.registerItem("cadmium_spike",
                () -> {
                    Item item = new MetalSpike(PROPERTY_SPIKE, MetalTagEnum.CADMIUM);
                    SpikeEnum.valueOf("CADMIUM").setSpike(item);
                    return item;
                }
        );
        MetallicsArts.registerItem("bendalloy_spike",
                () -> {
                    Item item = new MetalSpike(PROPERTY_SPIKE, MetalTagEnum.BENDALLOY);
                    SpikeEnum.valueOf("BENDALLOY").setSpike(item);
                    return item;
                }
        );
        MetallicsArts.registerItem("electrum_spike",
                () -> {
                    Item item = new MetalSpike(PROPERTY_SPIKE, MetalTagEnum.ELECTRUM);
                    SpikeEnum.valueOf("ELECTRUM").setSpike(item);
                    return item;
                }
        );
        MetallicsArts.registerItem("gold_spike",
                () -> {
                    Item item = new MetalSpike(PROPERTY_SPIKE, MetalTagEnum.GOLD);
                    SpikeEnum.valueOf("GOLD").setSpike(item);
                    return item;
                }
        );
        MetallicsArts.registerItem("atium_spike",
                () -> {
                    Item item = new MetalSpike(PROPERTY_SPIKE, MetalTagEnum.ATIUM);
                    SpikeEnum.valueOf("ATIUM").setSpike(item);
                    return item;
                }
        );
        MetallicsArts.registerItem("malatium_spike",
                () -> {
                    Item item = new MetalSpike(PROPERTY_SPIKE, MetalTagEnum.MALATIUM);
                    SpikeEnum.valueOf("MALATIUM").setSpike(item);
                    return item;
                }
        );
        MetallicsArts.registerItem("lerasium_spike",
                () -> {
                    Item item = new MetalSpike(PROPERTY_SPIKE, MetalTagEnum.LERASIUM);
                    SpikeEnum.valueOf("LERASIUM").setSpike(item);
                    return item;
                }
        );
        MetallicsArts.registerItem("ettmetal_spike",
                () -> {
                    Item item = new MetalSpike(PROPERTY_SPIKE, MetalTagEnum.ETTMETAL);
                    SpikeEnum.valueOf("ETTMETAL").setSpike(item);
                    return item;
                }
        );
    }
}

package net.rudahee.metallics_arts.setup.registries.items;

import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalmindType;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.MetalMindEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.*;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

/**
 * Registration of the metalminds items.
 *
 * @see ModItemsRegister
 */
public class MetalMindsRegister {
    private static final Item.Properties PROPERTY_METALMINDS = new Item.Properties().stacksTo(1);

    public static void register() {
        // Bands
        MetallicsArts.registerItem("band_aluminum_duralumin",
            () -> {
                Item item = new AluminumDuraluminMetalmind(PROPERTY_METALMINDS, MetalmindType.BAND);
                MetalMindEnum.ALUMINUM_DURALUMIN.setBand(item);
                return item;
            });

        MetallicsArts.registerItem("band_atium_malatium",
            () -> {
               Item item = new AtiumMalatiumMetalmind(PROPERTY_METALMINDS, MetalmindType.BAND);
               MetalMindEnum.ATIUM_MALTIUM.setBand(item);
               return item;
            });

        MetallicsArts.registerItem("band_cadmium_bendalloy",
            () -> {
                Item item = new CadmiumBendalloyMetalmind(PROPERTY_METALMINDS, MetalmindType.BAND);
                MetalMindEnum.CADMIUM_BENDALLOY.setBand(item);
                return item;
            });

        MetallicsArts.registerItem("band_chromium_nicrosil",
            () -> {
                Item item = new ChromiumNicrosilMetalmind(PROPERTY_METALMINDS, MetalmindType.BAND);
                MetalMindEnum.CHROMIUM_NICROSIL.setBand(item);
                return item;
            });

        MetallicsArts.registerItem("band_copper_bronze",
            () -> {
                Item item = new CopperBronzeMetalmind(PROPERTY_METALMINDS, MetalmindType.BAND);
                MetalMindEnum.COPPER_BRONZE.setBand(item);
                return item;
            });

        MetallicsArts.registerItem("band_gold_electrum",
            () -> {
                Item item = new GoldElectrumMetalmind(PROPERTY_METALMINDS, MetalmindType.BAND);
                MetalMindEnum.GOLD_ELECTRUM.setBand(item);
                return item;
            });
        MetallicsArts.registerItem("band_lerasium_ettmetal",
            () -> {
                Item item = new LerasiumEttmetalMetalmind(PROPERTY_METALMINDS, MetalmindType.BAND);
                MetalMindEnum.LERASIUM_ETTMETAL.setBand(item);
                return item;
            });
        MetallicsArts.registerItem("band_tin_pewter",
            () -> {
                Item item = new TinPewterMetalmind(PROPERTY_METALMINDS, MetalmindType.BAND);
                MetalMindEnum.TIN_PEWTER.setBand(item);
                return item;
            });
        MetallicsArts.registerItem("band_iron_steel",
            () -> {
                Item item = new IronSteelMetalmind(PROPERTY_METALMINDS, MetalmindType.BAND);
                MetalMindEnum.IRON_STEEL.setBand(item);
                return item;
            });
        MetallicsArts.registerItem("band_zinc_brass",
            () -> {
                Item item = new ZincBrassMetalmind(PROPERTY_METALMINDS, MetalmindType.BAND);
                MetalMindEnum.ZINC_BRASS.setBand(item);
                return item;
            });
        // Rings
        MetallicsArts.registerItem("ring_aluminum_duralumin",
            () -> {
                Item item = new AluminumDuraluminMetalmind(PROPERTY_METALMINDS, MetalmindType.RING);
                MetalMindEnum.ALUMINUM_DURALUMIN.setRing(item);
                return item;
            });

        MetallicsArts.registerItem("ring_atium_malatium",
            () -> {
                Item item = new AtiumMalatiumMetalmind(PROPERTY_METALMINDS, MetalmindType.RING);
                MetalMindEnum.ATIUM_MALTIUM.setRing(item);
                return item;
            });

        MetallicsArts.registerItem("ring_cadmium_bendalloy",
            () -> {
                Item item = new CadmiumBendalloyMetalmind(PROPERTY_METALMINDS, MetalmindType.RING);
                MetalMindEnum.CADMIUM_BENDALLOY.setRing(item);
                return item;
            });

        MetallicsArts.registerItem("ring_chromium_nicrosil",
            () -> {
                Item item = new ChromiumNicrosilMetalmind(PROPERTY_METALMINDS, MetalmindType.RING);
                MetalMindEnum.CHROMIUM_NICROSIL.setRing(item);
                return item;
            });

        MetallicsArts.registerItem("ring_copper_bronze",
            () -> {
                Item item = new CopperBronzeMetalmind(PROPERTY_METALMINDS, MetalmindType.RING);
                MetalMindEnum.COPPER_BRONZE.setRing(item);
                return item;
            });

        MetallicsArts.registerItem("ring_gold_electrum",
            () -> {
                Item item = new GoldElectrumMetalmind(PROPERTY_METALMINDS, MetalmindType.RING);
                MetalMindEnum.GOLD_ELECTRUM.setRing(item);
                return item;
            });

        MetallicsArts.registerItem("ring_lerasium_ettmetal",
            () -> {
                Item item = new LerasiumEttmetalMetalmind(PROPERTY_METALMINDS, MetalmindType.RING);
                MetalMindEnum.LERASIUM_ETTMETAL.setRing(item);
                return item;
            });

        MetallicsArts.registerItem("ring_tin_pewter",
            () -> {
                Item item = new TinPewterMetalmind(PROPERTY_METALMINDS, MetalmindType.RING);
                MetalMindEnum.TIN_PEWTER.setRing(item);
                return item;
            });

        MetallicsArts.registerItem("ring_iron_steel",
            () -> {
                Item item = new IronSteelMetalmind(PROPERTY_METALMINDS, MetalmindType.RING);
                MetalMindEnum.IRON_STEEL.setRing(item);
                return item;
            });

        MetallicsArts.registerItem("ring_zinc_brass",
            () -> {
                Item item = new ZincBrassMetalmind(PROPERTY_METALMINDS, MetalmindType.RING);
                MetalMindEnum.ZINC_BRASS.setRing(item);
                return item;
            });

    }

}

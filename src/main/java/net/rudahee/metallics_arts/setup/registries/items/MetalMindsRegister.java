package net.rudahee.metallics_arts.setup.registries.items;

import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.MetalMindEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands.*;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.rings.*;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

/**
 * Registration of the metalminds items.
 *
 * @see ModItemsRegister
 */
public class MetalMindsRegister {
    private static final Item.Properties PROPERTY_METALMINDS = new Item.Properties().tab(MetallicsArts.MA_TAB).stacksTo(1);
    public static void register() {
        // Bands
        MetallicsArts.registerItem("band_aluminum_duralumin",
            () -> {
                Item item = new BandAluminumDuralumin(PROPERTY_METALMINDS);
                MetalMindEnum.ALUMINUM_DURALUMIN.setBand(item);
                return item;
            });

        MetallicsArts.registerItem("band_atium_malatium",
            () -> {
               Item item = new BandAtiumMalatium(PROPERTY_METALMINDS);
               MetalMindEnum.ATIUM_MALTIUM.setBand(item);
               return item;
            });

        MetallicsArts.registerItem("band_cadmium_bendalloy",
            () -> {
                Item item = new BandCadmiumBendalloy(PROPERTY_METALMINDS);
                MetalMindEnum.CADMIUM_BENDALLOY.setBand(item);
                return item;
            });

        MetallicsArts.registerItem("band_chromium_nicrosil",
            () -> {
                Item item = new BandChromiumNicrosil(PROPERTY_METALMINDS);
                MetalMindEnum.CHROMIUM_NICROSIL.setBand(item);
                return item;
            });
        MetallicsArts.registerItem("band_copper_bronze",
            () -> {
                Item item = new BandCopperBronze(PROPERTY_METALMINDS);
                MetalMindEnum.COPPER_BRONZE.setBand(item);
                return item;
            });
        MetallicsArts.registerItem("band_gold_electrum",
            () -> {
                Item item = new BandElectrumGold(PROPERTY_METALMINDS);
                MetalMindEnum.GOLD_ELECTRUM.setBand(item);
                return item;
            });
        MetallicsArts.registerItem("band_lerasium_ettmetal",
            () -> {
                Item item = new BandLerasiumEttmetal(PROPERTY_METALMINDS);
                MetalMindEnum.LERASIUM_ETTMETAL.setBand(item);
                return item;
            });
        MetallicsArts.registerItem("band_tin_pewter",
            () -> {
                Item item = new BandPewterTin(PROPERTY_METALMINDS);
                MetalMindEnum.TIN_PEWTER.setBand(item);
                return item;
            });
        MetallicsArts.registerItem("band_iron_steel",
            () -> {
                Item item = new BandSteelIron(PROPERTY_METALMINDS);
                MetalMindEnum.IRON_STEEL.setBand(item);
                return item;
            });
        MetallicsArts.registerItem("band_zinc_brass",
            () -> {
                Item item = new BandZincBrass(PROPERTY_METALMINDS);
                MetalMindEnum.ZINC_BRASS.setBand(item);
                return item;
            });
        // Rings
        MetallicsArts.registerItem("ring_aluminum_duralumin",
            () -> {
                Item item = new RingAluminumDuralumin(PROPERTY_METALMINDS);
                MetalMindEnum.GOLD_ELECTRUM.setRing(item);
                return item;
            });

        MetallicsArts.registerItem("ring_atium_malatium",
            () -> {
                Item item = new RingAtiumMalatium(PROPERTY_METALMINDS);
                MetalMindEnum.ATIUM_MALTIUM.setRing(item);
                return item;
            });

        MetallicsArts.registerItem("ring_cadmium_bendalloy",
            () -> {
                Item item = new RingCadmiumBendalloy(PROPERTY_METALMINDS);
                MetalMindEnum.CADMIUM_BENDALLOY.setRing(item);
                return item;
            });

        MetallicsArts.registerItem("ring_chromium_nicrosil",
            () -> {
                Item item = new RingChromiumNicrosil(PROPERTY_METALMINDS);
                MetalMindEnum.CHROMIUM_NICROSIL.setRing(item);
                return item;
            });

        MetallicsArts.registerItem("ring_copper_bronze",
            () -> {
                Item item = new RingCopperBronze(PROPERTY_METALMINDS);
                MetalMindEnum.COPPER_BRONZE.setRing(item);
                return item;
            });

        MetallicsArts.registerItem("ring_gold_electrum",
            () -> {
                Item item = new RingGoldElectrum(PROPERTY_METALMINDS);
                MetalMindEnum.GOLD_ELECTRUM.setRing(item);
                return item;
            });

        MetallicsArts.registerItem("ring_lerasium_ettmetal",
            () -> {
                Item item = new RingLerasiumEttmetal(PROPERTY_METALMINDS);
                MetalMindEnum.LERASIUM_ETTMETAL.setRing(item);
                return item;
            });

        MetallicsArts.registerItem("ring_tin_pewter",
            () -> {
                Item item = new RingTinPewter(PROPERTY_METALMINDS);
                MetalMindEnum.TIN_PEWTER.setRing(item);
                return item;
            });

        MetallicsArts.registerItem("ring_iron_steel",
            () -> {
                Item item = new RingIronSteel(PROPERTY_METALMINDS);
                MetalMindEnum.IRON_STEEL.setRing(item);
                return item;
            });

        MetallicsArts.registerItem("ring_zinc_brass",
            () -> {
                Item item = new RingZincBrass(PROPERTY_METALMINDS);
                MetalMindEnum.ZINC_BRASS.setRing(item);
                return item;
            });

    }

}

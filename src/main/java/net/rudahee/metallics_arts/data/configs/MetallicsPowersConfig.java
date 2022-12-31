package net.rudahee.metallics_arts.data.configs;


import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.MetalMindEnum;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MetallicsPowersConfig {

    public static final Set<String> whitelist = new HashSet<String>() {{

        for (MetalTagEnum metals : MetalTagEnum.values()) {
            if (!metals.equals(MetalTagEnum.TIN)&&!metals.equals(MetalTagEnum.ALUMINUM)){
                add(metals.getNameLower());
            }
        }

        //Things that can interact whith iron and steel in Metallics Arts

        //Metalls Arts Integrations on hand
        add("lead");
        add("nickel");
        add("tin_");

        //Minecraft Integrations on hand
        add("shield");
        add("anvil");
        add("piston");
        add("hook");
        add("cauldron");
        add("minecart");
        add("hopper");
        add("bucket");
        add("netherite");
        add("lantern");

        //Suplementaries Integration on hand
        add("_railing");
        add("cage");
        add("safe");
        add("globe");
        add("candle_holder");
        add("wind_vane");
        add("faucet");
        add("wired_");
        add("railing_");
        add("brazier");
        add("dpring_launcher");


        //add(ModItemsRegister.BAND_PEWTER_TIN.get().getDescriptionId());
        add(MetalMindEnum.TIN_PEWTER.getBand().getDescriptionId());
        add(MetalMindEnum.TIN_PEWTER.getRing().getDescriptionId());

        //add(ModItemsRegister.BAND_PEWTER_TIN.get().getDescription().toString());
        //add(ModItemsRegister.RING_PEWTER_TIN.get().getDescriptionId());
        //add(ModItemsRegister.RING_PEWTER_TIN.get().getDescription().toString());


        add(Items.CROSSBOW.getDescriptionId());
        add(Items.CROSSBOW.getDescription().toString());

        add(Items.HEAVY_WEIGHTED_PRESSURE_PLATE.getDescriptionId());
        add(Items.HEAVY_WEIGHTED_PRESSURE_PLATE.getDescription().toString());

        add(Items.LIGHT_WEIGHTED_PRESSURE_PLATE.getDescriptionId());
        add(Items.LIGHT_WEIGHTED_PRESSURE_PLATE.getDescription().toString());

        add(Items.LIGHTNING_ROD.getDescription().toString());

        add(Items.BELL.getDescriptionId());
        add(Items.BELL.getDescription().toString());

        add(Items.SMITHING_TABLE.getDescriptionId());
        add(Items.SMITHING_TABLE.getDescription().toString());

        add(Items.LODESTONE.getDescriptionId());
        add(Items.LODESTONE.getDescription().toString());
        add(Items.STONECUTTER.getDescriptionId());
        add(Items.STONECUTTER.getDescription().toString());

        //add(ModBlock.ALLOY_FURNACE_BLOCK.get().getDescriptionId());
        add(Items.STONECUTTER.getDescription().toString());

        add(Items.BLAST_FURNACE.getDescriptionId());
        add(Items.STONECUTTER.getDescription().toString());

        add(Items.RAIL.getDescription().getString());
        add(Items.DETECTOR_RAIL.getDescription().getString());
        add(Items.POWERED_RAIL.getDescription().getString());
        add(Items.ACTIVATOR_RAIL.getDescription().getString());

        add(Items.CLOCK.getDescription().toString());
        add(Items.COMPASS.getDescription().toString());
        add(Items.SHEARS.getDescription().toString());
        add(Items.CHAIN.getDescription().toString());

        //TODO insertar cosas hechas de metal a mano.
    }};


    public static ForgeConfigSpec.ConfigValue<List<? extends String>> cfg_whitelist;



    public static void refresh(final ModConfigEvent e) {
        ModConfig cfg = e.getConfig();
    }

    private static void refresh_whitelist() {
        whitelist.clear();
        whitelist.addAll(cfg_whitelist.get());
    }

    public static void load_whitelist(final ModConfigEvent e) {
        ModConfig cfg = e.getConfig();
    }

}

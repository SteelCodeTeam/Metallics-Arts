package net.rudahee.metallics_arts.modules.powers;


import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.registries.ModItems;
import net.rudahee.metallics_arts.util.MetalicsArtsConfig;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MetallicsPowersConfig {

    public static final Set<String> whitelist = new HashSet<String>() {{

        for (MetalsNBTData metals : MetalsNBTData.values()) {
            if (!metals.equals(MetalsNBTData.TIN)&&!metals.equals(MetalsNBTData.ALUMINUM)){
                add(metals.getNameLower());
            }
        }

        add("lead");
        add("nickel");
        add("shield");
        add("anvil");
        add("rail");
        add("piston");
        add("hook");
        add("cauldron");
        add("minecart");
        add("hopper");
        add("bucket");
        add("netherite");
        add("lantern");



        add(ModItems.BAND_PEWTER_TIN.get().getDescriptionId());
        add(ModItems.BAND_PEWTER_TIN.get().getDescription().toString());
        add(ModItems.RING_PEWTER_TIN.get().getDescriptionId());
        add(ModItems.RING_PEWTER_TIN.get().getDescription().toString());

        add("tin_");

        add(Items.CROSSBOW.getDescriptionId());
        add(Items.CROSSBOW.getDescription().toString());

        add(Items.HEAVY_WEIGHTED_PRESSURE_PLATE.getDescriptionId());
        add(Items.HEAVY_WEIGHTED_PRESSURE_PLATE.getDescription().toString());

        add(Items.LIGHT_WEIGHTED_PRESSURE_PLATE.getDescriptionId());
        add(Items.LIGHT_WEIGHTED_PRESSURE_PLATE.getDescription().toString());


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

        add(Items.CLOCK.getDescription().toString());
        add(Items.COMPASS.getDescription().toString());
        add(Items.SHEARS.getDescription().toString());
        add(Items.CHAIN.getDescription().toString());


        //TODO insertar cosas hechas de metal a mano.

        //Suplementaries Integration
        add("cage");
        add("safe");
        add("globe");
        add("candle_holder");
        add("wind_vane");
        add("faucet");
        add("wired_");
        add("railing_");
        add("brazier");






    }};


    public static ForgeConfigSpec.IntValue maxDistanceMetalDetection;
    public static ForgeConfigSpec.BooleanValue animalSelection;
    public static ForgeConfigSpec.BooleanValue enableMoreKeybinding;
    public static ForgeConfigSpec.BooleanValue enableOverlay;
    public static ForgeConfigSpec.BooleanValue randomMisting;
    public static ForgeConfigSpec.BooleanValue generateWhitelist;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> cfg_whitelist;



    public static void init(ForgeConfigSpec.Builder configBuilder, ForgeConfigSpec.Builder clientBuilder) {
        configBuilder.comment("Settings for the gameplay elements of the mod").push("gameplay");
        randomMisting = configBuilder.comment("Spawn players as a random Misting").define("random_misting", true);
        generateWhitelist = configBuilder.comment("Regenerate the metal whitelist").define("regenerate_whitelist", true);
        cfg_whitelist = configBuilder
                .comment("List of registry names of items and blocks that are counted as 'metal")
                .defineList("whitelist", new ArrayList<>(), o -> o instanceof String);
        configBuilder.pop();

        clientBuilder.push("graphics");
        maxDistanceMetalDetection = clientBuilder.comment("Maximum iron/steelsight distance. Can have a HUGE impact on performance").defineInRange("max_distance_metal_detection", 15, 3, 30);
        animalSelection = clientBuilder.comment("Animate the selection wheel").define("animal_election", true);
        enableOverlay = clientBuilder.comment("Enable the screen overlay").define("enable_overlay", true);
        clientBuilder.pop();

        clientBuilder.push("controls");
        enableMoreKeybinding = clientBuilder.comment("Register extra keys, one for each metal, which toggle that metal specifically").define("advanced_keybinds", false);
        clientBuilder.pop();
    }

    public static void refresh(final ModConfigEvent e) {
        ModConfig cfg = e.getConfig();
        if (cfg.getSpec() == MetalicsArtsConfig.COMMON_CONFIG) {
            refresh_whitelist();
        }
    }

    private static void refresh_whitelist() {
        whitelist.clear();
        whitelist.addAll(cfg_whitelist.get());
    }

    public static void load_whitelist(final ModConfigEvent e) {
        ModConfig cfg = e.getConfig();
        if (cfg.getSpec() == MetalicsArtsConfig.COMMON_CONFIG) {
            if (generateWhitelist.get()) {
                ArrayList<String> list = new ArrayList<>(whitelist);
                list.sort(String::compareTo);
                cfg_whitelist.set(list);
                generateWhitelist.set(false);
            }
            refresh_whitelist();
        }
    }



}

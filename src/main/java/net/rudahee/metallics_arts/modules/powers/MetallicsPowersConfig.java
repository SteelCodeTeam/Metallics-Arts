package net.rudahee.metallics_arts.modules.powers;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.powers.helpers.IronAndSteelHelpers;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.enums.metals.Metal;
import net.rudahee.metallics_arts.setup.registries.ModBlock;
import net.rudahee.metallics_arts.setup.registries.ModItems;
import net.rudahee.metallics_arts.util.MetalicsArtsConfig;

import java.util.*;

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
        add("chain");
        add("hopper");
        add("bucket");
        add("netherite");
        add("lantern");

        add(ModItems.TIN_SPIKE.get().getDescriptionId());
        add(ModItems.TIN_SPIKE.get().getRegistryName().toString());
        add(ModItems.BAND_PEWTER_TIN.get().getDescriptionId());
        add(ModItems.BAND_PEWTER_TIN.get().getRegistryName().toString());
        add(ModItems.RING_PEWTER_TIN.get().getDescriptionId());
        add(ModItems.RING_PEWTER_TIN.get().getRegistryName().toString());

        add("tin_");

        add(Items.CROSSBOW.getDescriptionId());
        add(Items.CROSSBOW.getRegistryName().toString());

        add(Items.HEAVY_WEIGHTED_PRESSURE_PLATE.getDescriptionId());
        add(Items.HEAVY_WEIGHTED_PRESSURE_PLATE.getRegistryName().toString());

        add(Items.LIGHT_WEIGHTED_PRESSURE_PLATE.getDescriptionId());
        add(Items.LIGHT_WEIGHTED_PRESSURE_PLATE.getRegistryName().toString());


        add(Items.BELL.getDescriptionId());
        add(Items.BELL.getRegistryName().toString());


        add(Items.SMITHING_TABLE.getDescriptionId());
        add(Items.SMITHING_TABLE.getRegistryName().toString());

        add(Items.LODESTONE.getDescriptionId());
        add(Items.LODESTONE.getRegistryName().toString());
        add(Items.STONECUTTER.getDescriptionId());
        add(Items.STONECUTTER.getRegistryName().toString());

        add(ModBlock.ALLOY_FURNACE_BLOCK.get().getDescriptionId());
        add(Items.STONECUTTER.getRegistryName().toString());

        add(Items.BLAST_FURNACE.getDescriptionId());
        add(Items.STONECUTTER.getRegistryName().toString());

        add(Items.CLOCK.getRegistryName().toString());
        add(Items.COMPASS.getRegistryName().toString());
        add(Items.SHEARS.getRegistryName().toString());


        //TODO insertar cosas hechas de metal a mano.


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

    public static void refresh(final ModConfig.ModConfigEvent e) {
        ModConfig cfg = e.getConfig();
        if (cfg.getSpec() == MetalicsArtsConfig.COMMON_CONFIG) {
            refresh_whitelist();
        }
    }

    private static void refresh_whitelist() {
        whitelist.clear();
        whitelist.addAll(cfg_whitelist.get());
    }

    public static void load_whitelist(final ModConfig.Loading e) {
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

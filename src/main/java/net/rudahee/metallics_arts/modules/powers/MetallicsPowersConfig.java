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
import net.rudahee.metallics_arts.setup.registries.ModBlock;
import net.rudahee.metallics_arts.util.MetalicsArtsConfig;

import java.util.*;

public class MetallicsPowersConfig {

    public static final Set<String> whitelist = new HashSet<String>() {{
        for (MetalsNBTData metals : MetalsNBTData.values()) {
            add(metals.getNameLower());
        }
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

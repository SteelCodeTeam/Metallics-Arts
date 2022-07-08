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

    private static HashSet<String> defaultList;
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
                ArrayList<String> list = new ArrayList<>(default_whitelist());
                list.sort(String::compareTo);
                cfg_whitelist.set(list);
                generateWhitelist.set(false);
            }
            refresh_whitelist();
        }
    }

    private static Set<String> default_whitelist() {
        defaultList = new HashSet<>();
        add(Items.CHAINMAIL_BOOTS);
        add(Items.BUCKET);
        add(Items.LAVA_BUCKET);
        add(Items.MILK_BUCKET);
        add(Items.COD_BUCKET);
        add(Items.PUFFERFISH_BUCKET);
        add(Items.SALMON_BUCKET);
        add(Items.TROPICAL_FISH_BUCKET);
        add(Items.WATER_BUCKET);
        add(Items.CAULDRON);
        add(Items.COMPASS);
        add(Items.CHAINMAIL_HELMET);
        add(Items.CHAINMAIL_LEGGINGS);
        add(Items.MINECART);
        add(Items.CHEST_MINECART);
        add(Items.HOPPER_MINECART);
        add(Items.FURNACE_MINECART);
        add(Items.TNT_MINECART);
        add(Items.CHAINMAIL_CHESTPLATE);
        add(Items.CLOCK);
        add(Items.SHEARS);
        add(Items.SHIELD);
        add(Items.NETHERITE_INGOT);
        add(Items.NETHERITE_SCRAP); // Op?
        add(Items.NETHERITE_HELMET);
        add(Items.NETHERITE_CHESTPLATE);
        add(Items.NETHERITE_LEGGINGS);
        add(Items.NETHERITE_BOOTS);
        add(Items.NETHERITE_HOE);
        add(Items.NETHERITE_PICKAXE);
        add(Items.NETHERITE_SHOVEL);
        add(Items.NETHERITE_SWORD);
        add(Items.NETHERITE_AXE);
        add(Items.CROSSBOW);

        add(Blocks.ANVIL);
        add(Blocks.CHIPPED_ANVIL);
        add(Blocks.DAMAGED_ANVIL);
        add(Blocks.CAULDRON);
        add(Blocks.SMITHING_TABLE);
        add(Blocks.STONECUTTER);
        add(Blocks.CHAIN);
        add(Blocks.HOPPER);
        add(Blocks.PISTON_HEAD);
        add(Blocks.MOVING_PISTON);
        add(Blocks.STICKY_PISTON);
        add(Blocks.BLAST_FURNACE);
        add(Blocks.BELL);
        add(Blocks.PISTON);
        add(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE);
        add(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE);
        add(Blocks.RAIL);
        add(Blocks.ACTIVATOR_RAIL);
        add(Blocks.DETECTOR_RAIL);
        add(Blocks.POWERED_RAIL);
        add(Blocks.LANTERN);
        add(Blocks.TRAPPED_CHEST);
        add(Blocks.TRIPWIRE_HOOK);
        add(Blocks.SOUL_LANTERN);
        add(Blocks.NETHERITE_BLOCK);
        add(Blocks.ANCIENT_DEBRIS); // OP? TODO: consider if this should not be here, alongside scrap
        add(Blocks.LODESTONE);
        add(Blocks.GILDED_BLACKSTONE);


        ForgeRegistries.ITEMS
                .getValues()
                .stream()
                .map(ForgeRegistryEntry::getRegistryName)
                .filter(Objects::nonNull)
                .filter(IronAndSteelHelpers::doesResourceContainsMetal)
                .forEach(MetallicsPowersConfig::add);

        ForgeRegistries.BLOCKS
                .getValues()
                .stream()
                .map(ForgeRegistryEntry::getRegistryName)
                .filter(Objects::nonNull)
                .filter(IronAndSteelHelpers::doesResourceContainsMetal)
                .forEach(MetallicsPowersConfig::add);
        return defaultList;
    }

    private static void add(String s) {
        MetallicsArts.LOGGER.info("Adding " + s + " to the default whitelist!");
        defaultList.add(s);
    }

    private static void add(ResourceLocation r) {
        add(r.toString());
    }

    private static void add(ForgeRegistryEntry<?> registryEntry) {
        add(registryEntry.getRegistryName());
    }



}

package net.rudahee.metallics_arts.data.network;

import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.rudahee.metallics_arts.data.AllomancyConfig_idk;

import java.util.*;

public class PowerConfiguration {

    public static final Set<String> whitelist = new HashSet<>();
    public static ForgeConfigSpec.IntValue max_metal_detection;
    public static ForgeConfigSpec.BooleanValue animate_selection;
    public static ForgeConfigSpec.BooleanValue enable_more_keybinds;
    public static ForgeConfigSpec.BooleanValue enable_overlay;
    public static ForgeConfigSpec.EnumValue<SCREEN_LOC> overlay_position;
    public static ForgeConfigSpec.BooleanValue random_mistings;
    public static ForgeConfigSpec.BooleanValue generate_whitelist;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> cfg_whitelist;
    private static HashSet<String> defaultList;

    public static void init(ForgeConfigSpec.Builder common_builder, ForgeConfigSpec.Builder client_builder) {

        overlay_position = client_builder.comment("Screen Overlay Position").defineEnum("overlay_position", SCREEN_LOC.TOP_LEFT);

    }




    public enum SCREEN_LOC {
        TOP_RIGHT,
        BOTTOM_RIGHT,
        TOP_LEFT,
        BOTTOM_LEFT
    }
}

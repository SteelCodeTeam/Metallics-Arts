package net.rudahee.metallics_arts.modules.powers;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.rudahee.metallics_arts.setup.registries.ModBlock;
import net.rudahee.metallics_arts.setup.registries.ModItems;

import java.rmi.registry.Registry;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MetallicsPowersConfig {

    public static final Set<String> whitelist = new HashSet<>();
    public static ForgeConfigSpec.IntValue maxDistanceMetalDetection;
    public static ForgeConfigSpec.BooleanValue animalSelection;
    public static ForgeConfigSpec.BooleanValue enableMoreKeybinding;
    public static ForgeConfigSpec.BooleanValue enableOverlay;
    public static ForgeConfigSpec.BooleanValue randomMisting;
    public static ForgeConfigSpec.BooleanValue generateWhitelist;

    public static void init(ForgeConfigSpec.Builder configBuilder, ForgeConfigSpec.Builder clientBuilder) {
        // TODO
    }

    public static Set<Object> generateDefaultWhitelist() {
        Set<Object> defaultWhitelist = new HashSet<Object>() {{
            add(Items.IRON_BLOCK);
            add(Items.IRON_ORE);
            for(Block block: ModBlock.BLOCK_METAL_BLOCKS.values()) {
                add(block);
                add(block.asItem());
            }
        }};

        return defaultWhitelist;
    }

}

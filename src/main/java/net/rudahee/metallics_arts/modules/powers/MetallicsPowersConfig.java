package net.rudahee.metallics_arts.modules.powers;

import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraftforge.common.ForgeConfigSpec;
import net.rudahee.metallics_arts.setup.registries.ModBlock;

import java.util.HashSet;
import java.util.Set;

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

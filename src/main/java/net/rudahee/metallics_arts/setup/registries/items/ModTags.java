package net.rudahee.metallics_arts.setup.registries.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;

import java.util.HashMap;

public class ModTags {
    public static final HashMap<String, TagKey<Item>> NUGGETS = new HashMap<>();
    public static final HashMap<String, TagKey<Item>> INGOTS = new HashMap<>();
    public static final HashMap<String, TagKey<Item>> RAWS = new HashMap<>();
    public static final HashMap<String, TagKey<Item>> METAL_BLOCKS = new HashMap<>();
    public static final HashMap<String, TagKey<Item>> RAW_BLOCKS = new HashMap<>();
    public static final HashMap<String, TagKey<Item>> GEMS = new HashMap<>();
    public static final HashMap<String, TagKey<Item>> STAIRS = new HashMap<>();
    public static final HashMap<String, TagKey<Item>> SLAB = new HashMap<>();
    public static final HashMap<String, TagKey<Item>> WALL = new HashMap<>();
    public static final HashMap<String, TagKey<Item>> FENCE = new HashMap<>();
    public static final HashMap<String, TagKey<Item>> FENCE_GATE = new HashMap<>();


    static {
        for (MetalEnum metal : MetalEnum.values()) {
            NUGGETS.put(metal.getMetalNameLower(), forgeItemTag("nuggets/" + metal.getMetalNameLower()));
            INGOTS.put(metal.getMetalNameLower(), forgeItemTag("ingots/" + metal.getMetalNameLower()));
            METAL_BLOCKS.put(metal.getMetalNameLower(), forgeItemTag("storage_blocks/" + metal.getMetalNameLower()));
            STAIRS.put(metal.getMetalNameLower(), forgeItemTag("storage_blocks/" + metal.getMetalNameLower() + "_stairs"));
            SLAB.put(metal.getMetalNameLower(), forgeItemTag("storage_blocks/" + metal.getMetalNameLower() + "_slab"));
            WALL.put(metal.getMetalNameLower(), forgeItemTag("storage_blocks/" + metal.getMetalNameLower() + "_wall"));
            FENCE.put(metal.getMetalNameLower(), forgeItemTag("storage_blocks/" + metal.getMetalNameLower() + "_fence"));
            FENCE_GATE.put(metal.getMetalNameLower(), forgeItemTag("storage_blocks/" + metal.getMetalNameLower() + "_fence_gate"));
        }

        NUGGETS.put("copper", forgeItemTag("nuggets/copper"));

        STAIRS.put("iron", forgeItemTag("storage_blocks/" + "iron_stairs"));
        STAIRS.put("gold", forgeItemTag("storage_blocks/" + "gold_stairs"));
        STAIRS.put("iron_raw", forgeItemTag("storage_blocks/" + "iron_raw_stairs"));
        STAIRS.put("gold_raw", forgeItemTag("storage_blocks/" + "gold_raw_stairs"));
        STAIRS.put("copper_raw", forgeItemTag("storage_blocks/" + "copper_raw_stairs"));

        SLAB.put("iron", forgeItemTag("storage_blocks/" + "iron_slab"));
        SLAB.put("gold", forgeItemTag("storage_blocks/" + "gold_slab"));
        SLAB.put("iron_raw", forgeItemTag("storage_blocks/" + "iron_raw_slab"));
        SLAB.put("gold_raw", forgeItemTag("storage_blocks/" + "gold_raw_slab"));
        SLAB.put("copper_raw", forgeItemTag("storage_blocks/" + "copper_raw_slab"));

        WALL.put("iron", forgeItemTag("storage_blocks/" + "iron_wall"));
        WALL.put("gold", forgeItemTag("storage_blocks/" + "gold_wall"));
        WALL.put("copper", forgeItemTag("storage_blocks/" + "copper_wall"));
        WALL.put("iron_raw", forgeItemTag("storage_blocks/" + "iron_raw_wall"));
        WALL.put("gold_raw", forgeItemTag("storage_blocks/" + "gold_raw_wall"));
        WALL.put("copper_raw", forgeItemTag("storage_blocks/" + "copper_raw_wall"));


        FENCE.put("iron", forgeItemTag("storage_blocks/" + "iron_fence"));
        FENCE.put("gold", forgeItemTag("storage_blocks/" + "gold_fence"));
        FENCE.put("copper", forgeItemTag("storage_blocks/" + "copper_fence"));
        FENCE.put("iron_raw", forgeItemTag("storage_blocks/" + "iron_raw_fence"));
        FENCE.put("gold_raw", forgeItemTag("storage_blocks/" + "gold_raw_fence"));
        FENCE.put("copper_raw", forgeItemTag("storage_blocks/" + "copper_raw_fence"));

        FENCE_GATE.put("iron", forgeItemTag("storage_blocks/" + "iron_fence_gate"));
        FENCE_GATE.put("gold", forgeItemTag("storage_blocks/" + "gold_fence_gate"));
        FENCE_GATE.put("copper", forgeItemTag("storage_blocks/" + "copper_fence_gate"));
        FENCE_GATE.put("iron_raw", forgeItemTag("storage_blocks/" + "iron_raw_fence_gate"));
        FENCE_GATE.put("gold_raw", forgeItemTag("storage_blocks/" + "gold_raw_fence_gate"));
        FENCE_GATE.put("copper_raw", forgeItemTag("storage_blocks/" + "copper_raw_fence_gate"));

        for (GemsEnum gem : GemsEnum.values()) {
            NUGGETS.put(gem.getGemNameLower(), forgeItemTag("nuggets/" + gem.getGemNameLower()));
            GEMS.put(gem.getGemNameLower(), forgeItemTag("gems/" + gem.getGemNameLower()));
            METAL_BLOCKS.put(gem.getGemNameLower(), forgeItemTag("storage_blocks/" + gem.getGemNameLower()));
            STAIRS.put(gem.getGemNameLower(), forgeItemTag("storage_blocks/" + gem.getGemNameLower() + "_stairs"));
            SLAB.put(gem.getGemNameLower(), forgeItemTag("storage_blocks/" + gem.getGemNameLower() + "_slab"));
            WALL.put(gem.getGemNameLower(), forgeItemTag("storage_blocks/" + gem.getGemNameLower() + "_wall"));
            FENCE.put(gem.getGemNameLower(), forgeItemTag("storage_blocks/" + gem.getGemNameLower() + "_fence"));
            FENCE_GATE.put(gem.getGemNameLower(), forgeItemTag("storage_blocks/" + gem.getGemNameLower() + "_fence_gate"));
        }

        for (MetalEnum metal : MetalEnum.values()) {
            if (!metal.isAlloy()) {
                RAWS.put(metal.getMetalNameLower(),forgeItemTag("raws_materials/" + metal.getMetalNameLower()));
                RAW_BLOCKS.put(metal.getMetalNameLower(),forgeItemTag("storage_blocks/raw_" + metal.getMetalNameLower()));
                STAIRS.put(metal.getMetalNameLower() + "_raw", forgeItemTag("storage_blocks/" + metal.getMetalNameLower() + "_raw_stairs"));
                SLAB.put(metal.getMetalNameLower() + "_raw", forgeItemTag("storage_blocks/" + metal.getMetalNameLower() + "_raw_slab"));
                WALL.put(metal.getMetalNameLower() + "_raw", forgeItemTag("storage_blocks/" + metal.getMetalNameLower() + "_raw_wall"));
                FENCE.put(metal.getMetalNameLower() + "_raw", forgeItemTag("storage_blocks/" + metal.getMetalNameLower() + "_raw_fence"));
                FENCE_GATE.put(metal.getMetalNameLower() + "_raw", forgeItemTag("storage_blocks/" + metal.getMetalNameLower() + "_raw_fence_gate"));
            }
        }

    }


    public static TagKey<Item> forgeItemTag(String name) {
        return ItemTags.create(new ResourceLocation("forge", name));
    }

    public static TagKey<Block> forgeBlockTag(String name) {
        return BlockTags.create(new ResourceLocation("forge", name));
    }
}

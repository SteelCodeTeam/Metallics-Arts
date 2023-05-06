package net.rudahee.metallics_arts.setup.registries.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.data.enums.implementations.languages.MetalAuxiliaryInfo;

import java.util.HashMap;

public class ModTags {

    public static final HashMap<String, TagKey<Item>> NUGGETS = new HashMap<>();
    public static final HashMap<String, TagKey<Item>> INGOTS = new HashMap<>();
    public static final HashMap<String, TagKey<Item>> RAWS = new HashMap<>();
    public static final HashMap<String, TagKey<Item>> METAL_BLOCKS = new HashMap<>();
    public static final HashMap<String, TagKey<Item>> RAW_BLOCKS = new HashMap<>();
    public static final HashMap<String, TagKey<Item>> GEMS = new HashMap<>();



    static {
        for (MetalEnum metal : MetalEnum.values()) {
            NUGGETS.put(metal.getMetalNameLower(), forgeItemTag("nuggets/" + metal.getMetalNameLower()));
            INGOTS.put(metal.getMetalNameLower(), forgeItemTag("ingots/" + metal.getMetalNameLower()));
            METAL_BLOCKS.put(metal.getMetalNameLower(), forgeItemTag("storage_blocks/" + metal.getMetalNameLower()));
        }
        NUGGETS.put("copper", forgeItemTag("nuggets/copper"));

        for (GemsEnum gem : GemsEnum.values()) {
            NUGGETS.put(gem.getGemNameLower(), forgeItemTag("nuggets/" + gem.getGemNameLower()));
            GEMS.put(gem.getGemNameLower(), forgeItemTag("gems/" + gem.getGemNameLower()));
            METAL_BLOCKS.put(gem.getGemNameLower(), forgeItemTag("storage_blocks/" + gem.getGemNameLower()));
        }

        for (MetalEnum metal : MetalEnum.values()) {
            if (!metal.isAlloy()) {
                RAWS.put(metal.getMetalNameLower(),forgeItemTag("raws_materials/" + metal.getMetalNameLower()));
                RAW_BLOCKS.put(metal.getMetalNameLower(),forgeItemTag("storage_blocks/raw_" + metal.getMetalNameLower()));
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

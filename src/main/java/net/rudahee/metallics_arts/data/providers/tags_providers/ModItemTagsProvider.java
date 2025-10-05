package net.rudahee.metallics_arts.data.providers.tags_providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.setup.registries.items.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> tagLookupCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, tagLookupCompletableFuture, MetallicsArts.MOD_ID, existingFileHelper);
    }

    //todo, todos los fors de abajo podrian hacerse con un for en la lista para evitar if y chequeos
    @Override
    protected void addTags(HolderLookup.Provider prov) {

        tag(ModTags.NUGGETS.get("copper")).add(ModItemsRegister.ITEM_METAL_NUGGET.get("copper"));
        for (GemsEnum gemsEnum : GemsEnum.values()) {
            tag(ModTags.NUGGETS.get(gemsEnum.getGemNameLower())).add(ModItemsRegister.ITEM_GEMS_NUGGET.get(gemsEnum.getGemNameLower()));
            tag(ModTags.GEMS.get(gemsEnum.getGemNameLower())).add(ModItemsRegister.ITEM_GEMS_BASE.get(gemsEnum.getGemNameLower()));
            tag(ModTags.METAL_BLOCKS.get(gemsEnum.getGemNameLower())).add(ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(gemsEnum.getGemNameLower()).asItem());

            tag(ModTags.STAIRS.get(gemsEnum.getGemNameLower())).add(ModBlocksRegister.BLOCK_METAL_STAIRS.get(gemsEnum.getGemNameLower()).asItem());
            tag(ModTags.SLAB.get(gemsEnum.getGemNameLower())).add(ModBlocksRegister.BLOCK_METAL_SLAB.get(gemsEnum.getGemNameLower()).asItem());
            tag(ModTags.WALL.get(gemsEnum.getGemNameLower())).add(ModBlocksRegister.BLOCK_METAL_WALL.get(gemsEnum.getGemNameLower()).asItem());
            tag(ModTags.FENCE.get(gemsEnum.getGemNameLower())).add(ModBlocksRegister.BLOCK_METAL_FENCE.get(gemsEnum.getGemNameLower()).asItem());
            tag(ModTags.FENCE_GATE.get(gemsEnum.getGemNameLower())).add(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(gemsEnum.getGemNameLower()).asItem());
        }
        for (MetalEnum metal : MetalEnum.values()) {
            tag(ModTags.NUGGETS.get(metal.getMetalNameLower())).add(ModItemsRegister.ITEM_METAL_NUGGET.get(metal.getMetalNameLower()));
            tag(ModTags.INGOTS.get(metal.getMetalNameLower())).add(ModItemsRegister.ITEM_METAL_INGOT.get(metal.getMetalNameLower()));
            tag(ModTags.METAL_BLOCKS.get(metal.getMetalNameLower())).add(ModBlocksRegister.BLOCK_METAL_BLOCKS.get(metal.getMetalNameLower()).asItem());
            tag(ModTags.STAIRS.get(metal.getMetalNameLower())).add(ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getMetalNameLower()).asItem());
            tag(ModTags.FENCE_GATE.get(metal.getMetalNameLower())).add(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(metal.getMetalNameLower()).asItem());
            tag(ModTags.SLAB.get(metal.getMetalNameLower())).add(ModBlocksRegister.BLOCK_METAL_SLAB.get(metal.getMetalNameLower()).asItem());
            tag(ModTags.WALL.get(metal.getMetalNameLower())).add(ModBlocksRegister.BLOCK_METAL_WALL.get(metal.getMetalNameLower()).asItem());
            tag(ModTags.FENCE.get(metal.getMetalNameLower())).add(ModBlocksRegister.BLOCK_METAL_FENCE.get(metal.getMetalNameLower()).asItem());
            if (!metal.isAlloy()) {
                tag(ModTags.RAWS.get(metal.getMetalNameLower())).add(ModItemsRegister.ITEM_RAW_METAL.get(metal.getMetalNameLower()));
                tag(ModTags.RAW_BLOCKS.get(metal.getMetalNameLower())).add(ModBlocksRegister.RAW_METAL_BLOCKS.get(metal.getMetalNameLower()).asItem());
                tag(ModTags.STAIRS.get(metal.getMetalNameLower()+ "_raw")).add(ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getMetalNameLower() + "_raw").asItem());
                tag(ModTags.FENCE_GATE.get(metal.getMetalNameLower()+ "_raw")).add(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(metal.getMetalNameLower() + "_raw").asItem());
                tag(ModTags.SLAB.get(metal.getMetalNameLower()+ "_raw")).add(ModBlocksRegister.BLOCK_METAL_SLAB.get(metal.getMetalNameLower() + "_raw").asItem());
                tag(ModTags.WALL.get(metal.getMetalNameLower()+ "_raw")).add(ModBlocksRegister.BLOCK_METAL_WALL.get(metal.getMetalNameLower() + "_raw").asItem());
                tag(ModTags.FENCE.get(metal.getMetalNameLower()+ "_raw")).add(ModBlocksRegister.BLOCK_METAL_FENCE.get(metal.getMetalNameLower() + "_raw").asItem());
            }

            if (metal.isStone() && metal.isDeepslate()) {
                tag(ModTags.forgeItemTag("ores/" + metal.getMetalNameLower()))
                        .add(ModBlocksRegister.BLOCK_METAL_ORES.get(metal.getMetalNameLower()).asItem())
                        .add(ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get(metal.getMetalNameLower()).asItem());
            } else if (metal.isDeepslate()) {
                tag(ModTags.forgeItemTag("ores/" + metal.getMetalNameLower()))
                        .add(ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get(metal.getMetalNameLower()).asItem());
            } else if (metal.isStone()) {
                tag(ModTags.forgeItemTag("ores/" + metal.getMetalNameLower()))
                        .add(ModBlocksRegister.BLOCK_METAL_ORES.get(metal.getMetalNameLower()).asItem());
            }


        }
        tag(ModTags.STAIRS.get("iron")).add(ModBlocksRegister.BLOCK_METAL_STAIRS.get("iron").asItem());
        tag(ModTags.STAIRS.get("gold")).add(ModBlocksRegister.BLOCK_METAL_STAIRS.get("gold").asItem());
        tag(ModTags.STAIRS.get("iron_raw")).add(ModBlocksRegister.BLOCK_METAL_STAIRS.get("iron_raw").asItem());
        tag(ModTags.STAIRS.get("gold_raw")).add(ModBlocksRegister.BLOCK_METAL_STAIRS.get("gold_raw").asItem());
        tag(ModTags.STAIRS.get("copper_raw")).add(ModBlocksRegister.BLOCK_METAL_STAIRS.get("copper_raw").asItem());

        tag(ModTags.SLAB.get("iron")).add(ModBlocksRegister.BLOCK_METAL_SLAB.get("iron").asItem());
        tag(ModTags.SLAB.get("gold")).add(ModBlocksRegister.BLOCK_METAL_SLAB.get("gold").asItem());
        tag(ModTags.SLAB.get("iron_raw")).add(ModBlocksRegister.BLOCK_METAL_SLAB.get("iron_raw").asItem());
        tag(ModTags.SLAB.get("gold_raw")).add(ModBlocksRegister.BLOCK_METAL_SLAB.get("gold_raw").asItem());
        tag(ModTags.SLAB.get("copper_raw")).add(ModBlocksRegister.BLOCK_METAL_SLAB.get("copper_raw").asItem());

        tag(ModTags.WALL.get("iron")).add(ModBlocksRegister.BLOCK_METAL_WALL.get("iron").asItem());
        tag(ModTags.WALL.get("gold")).add(ModBlocksRegister.BLOCK_METAL_WALL.get("gold").asItem());
        tag(ModTags.WALL.get("copper")).add(ModBlocksRegister.BLOCK_METAL_WALL.get("copper").asItem());
        tag(ModTags.WALL.get("iron_raw")).add(ModBlocksRegister.BLOCK_METAL_WALL.get("iron_raw").asItem());
        tag(ModTags.WALL.get("gold_raw")).add(ModBlocksRegister.BLOCK_METAL_WALL.get("gold_raw").asItem());
        tag(ModTags.WALL.get("copper_raw")).add(ModBlocksRegister.BLOCK_METAL_WALL.get("copper_raw").asItem());

        tag(ModTags.FENCE.get("iron")).add(ModBlocksRegister.BLOCK_METAL_FENCE.get("iron").asItem());
        tag(ModTags.FENCE.get("gold")).add(ModBlocksRegister.BLOCK_METAL_FENCE.get("gold").asItem());
        tag(ModTags.FENCE.get("copper")).add(ModBlocksRegister.BLOCK_METAL_FENCE.get("copper").asItem());
        tag(ModTags.FENCE.get("iron_raw")).add(ModBlocksRegister.BLOCK_METAL_FENCE.get("iron_raw").asItem());
        tag(ModTags.FENCE.get("gold_raw")).add(ModBlocksRegister.BLOCK_METAL_FENCE.get("gold_raw").asItem());
        tag(ModTags.FENCE.get("copper_raw")).add(ModBlocksRegister.BLOCK_METAL_FENCE.get("copper_raw").asItem());

        tag(ModTags.FENCE_GATE.get("iron")).add(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get("iron").asItem());
        tag(ModTags.FENCE_GATE.get("gold")).add(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get("gold").asItem());
        tag(ModTags.FENCE_GATE.get("copper")).add(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get("copper").asItem());
        tag(ModTags.FENCE_GATE.get("iron_raw")).add(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get("iron_raw").asItem());
        tag(ModTags.FENCE_GATE.get("gold_raw")).add(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get("gold_raw").asItem());
        tag(ModTags.FENCE_GATE.get("copper_raw")).add(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get("copper_raw").asItem());

        tag(ModTags.forgeItemTag("ores_in_ground/deepslate")).add(ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.values().stream().map(Block::asItem).toArray(Item[]::new));
        tag(ModTags.forgeItemTag("ores_in_ground/stone")).add(ModBlocksRegister.BLOCK_METAL_ORES.values().stream().map(Block::asItem).toArray(Item[]::new));
        tag(ModTags.forgeItemTag("ingots")).addTags(ModTags.INGOTS.values().toArray(TagKey[]::new));
        tag(ModTags.forgeItemTag("nuggets")).addTags(ModTags.NUGGETS.values().toArray(TagKey[]::new));
        tag(ModTags.forgeItemTag("raw_materials")).addTags(ModTags.RAWS.values().toArray(TagKey[]::new));
        tag(ModTags.forgeItemTag("gems")).addTags(ModTags.GEMS.values().toArray(TagKey[]::new));
        tag(ModTags.forgeItemTag("storage_blocks"))
                .addTags(Stream.concat(Stream.concat(Stream.concat(Arrays.stream(ModTags.RAW_BLOCKS.values().toArray(TagKey[]::new)),
                        Arrays.stream(
                                ModTags.METAL_BLOCKS.values().toArray(TagKey[]::new))),
                        Arrays.stream(
                                ModTags.STAIRS.values().toArray(TagKey[]::new))),
                                Stream.concat(
                                        Stream.concat(Arrays.stream(ModTags.FENCE.values().toArray(TagKey[]::new)),
                                                Arrays.stream(ModTags.FENCE_GATE.values().toArray(TagKey[]::new))),
                                        Stream.concat(
                                                Arrays.stream(ModTags.WALL.values().toArray(TagKey[]::new)),
                                                Arrays.stream(ModTags.SLAB.values().toArray(TagKey[]::new)))))
                        .toArray(TagKey[]::new));


    }

}

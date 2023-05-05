package net.rudahee.metallics_arts.data.providers.tags_providers;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.data.enums.implementations.languages.MetalAuxiliaryInfo;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.setup.registries.items.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.stream.Stream;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagsProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagsProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(ModTags.NUGGETS.get("copper")).add(ModItemsRegister.ITEM_METAL_NUGGET.get("copper"));
        for (GemsEnum gemsEnum : GemsEnum.values()) {
            tag(ModTags.NUGGETS.get(gemsEnum.getGemNameLower())).add(ModItemsRegister.ITEM_GEMS_BASE.get(gemsEnum.getGemNameLower()));
            tag(ModTags.GEMS.get(gemsEnum.getGemNameLower())).add(ModItemsRegister.ITEM_GEMS_NUGGET.get(gemsEnum.getGemNameLower()));
            tag(ModTags.METAL_BLOCKS.get(gemsEnum.getGemNameLower())).add(ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(gemsEnum.getGemNameLower()).asItem());
        }
        for (MetalEnum metal : MetalEnum.values()) {
            tag(ModTags.NUGGETS.get(metal.getMetalNameLower())).add(ModItemsRegister.ITEM_METAL_NUGGET.get(metal.getMetalNameLower()));
            tag(ModTags.INGOTS.get(metal.getMetalNameLower())).add(ModItemsRegister.ITEM_METAL_INGOT.get(metal.getMetalNameLower()));
            tag(ModTags.METAL_BLOCKS.get(metal.getMetalNameLower())).add(ModBlocksRegister.BLOCK_METAL_BLOCKS.get(metal.getMetalNameLower()).asItem());

            if (!metal.isAlloy()){
                tag(ModTags.RAWS.get(metal.getMetalNameLower())).add(ModItemsRegister.ITEM_RAW_METAL.get(metal.getMetalNameLower()));
                tag(ModTags.RAW_BLOCKS.get(metal.getMetalNameLower())).add(ModBlocksRegister.RAW_METAL_BLOCKS.get(metal.getMetalNameLower()).asItem());
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

        tag(ModTags.forgeItemTag("ores_in_ground/deepslate")).add(ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.values().stream().map(Block::asItem).toArray(Item[]::new));
        tag(ModTags.forgeItemTag("ores_in_ground/stone")).add(ModBlocksRegister.BLOCK_METAL_ORES.values().stream().map(Block::asItem).toArray(Item[]::new));
        tag(ModTags.forgeItemTag("ingots")).addTags(ModTags.INGOTS.values().toArray(TagKey[]::new));
        tag(ModTags.forgeItemTag("nuggets")).addTags(ModTags.NUGGETS.values().toArray(TagKey[]::new));
        tag(ModTags.forgeItemTag("raw_materials")).addTags(ModTags.RAWS.values().toArray(TagKey[]::new));
        tag(ModTags.forgeItemTag("gems")).addTags(ModTags.GEMS.values().toArray(TagKey[]::new));
        tag(ModTags.forgeItemTag("storage_blocks"))
                .addTags(Stream.concat(Arrays.stream(ModTags.RAW_BLOCKS.values().toArray(TagKey[]::new)),
                Arrays.stream(ModTags.METAL_BLOCKS.values().toArray(TagKey[]::new))).toArray(TagKey[]::new));

    }
}

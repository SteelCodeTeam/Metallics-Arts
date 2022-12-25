package net.rudahee.metallics_arts.data.providers;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalsNBTData;
import net.rudahee.metallics_arts.setup.registries.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MetallicsArts.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        /*
            I don't know why the hell I can't order the textures of the blocks,
            but I can order the textures of the items.

            so I just grab all the blocks and put the texture of the block folder in them.
         */
        ModBlocks.BLOCK_GEMS_BLOCKS.forEach((name, block) -> {
            ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/" + name + "_block"));

            // later i put texture in block and item.
            simpleBlock(block);
            simpleBlockItem(block, model);

        });

        ModBlocks.BLOCK_METAL_ORES.forEach((name, block) -> {
            // for each registry block, i get model wih format ("block/name_typeblock")
            ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/" + name + "_ore"));

            // later i put texture in block and item.
            simpleBlock(block);
            simpleBlockItem(block, model);
        });

        ModBlocks.BLOCK_METAL_DEEPSLATE_ORES.forEach((name, block) -> {
            // for each registry block, i get model wih format ("block/name_typeblock")
            ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/deepslate_" + name + "_ore"));

            // later i put texture in block and item.
            simpleBlock(block);
            simpleBlockItem(block, model);
        });

        ModBlocks.BLOCK_METAL_BLOCKS.forEach((name, block) -> {
            // for each registry block, i get model wih format ("block/name_typeblock")
            ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/" + name + "_block"));

            // later, I put texture in block and item.
            simpleBlock(block);
            simpleBlockItem(block, model);
        });

        ModBlocks.RAW_METAL_BLOCKS.forEach((name, block) -> {
            // for each registry block, i get model wih format ("block/name_typeblock")
            ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/raw_" + name + "_block"));

            // later, I put texture in block and item.
            simpleBlock(block);
            simpleBlockItem(block, model);
        });

        ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/budding_atium"));
        simpleBlock(ModBlocks.BUDDING_ATIUM.get());
        simpleBlockItem(ModBlocks.BUDDING_ATIUM.get(),model);

        model = new ModelFile.UncheckedModelFile(modLoc("block/budding_lerasium"));

        simpleBlock(ModBlocks.BUDDING_LERASIUM.get());
        simpleBlockItem(ModBlocks.BUDDING_LERASIUM.get(),model);

        model = new ModelFile.UncheckedModelFile(modLoc("block/budding_ettmetal"));

        simpleBlock(ModBlocks.BUDDING_ETTMETAL.get());
        simpleBlockItem(ModBlocks.BUDDING_ETTMETAL.get(),model);


        model = new ModelFile.UncheckedModelFile(modLoc("block/atium_cristal_block"));
        simpleBlock(ModBlocks.DIVINE_CRISTAL_BLOCKS.get(MetalsNBTData.ATIUM.getGemNameLower()));
        simpleBlockItem(ModBlocks.DIVINE_CRISTAL_BLOCKS.get(MetalsNBTData.ATIUM.getGemNameLower()),model);

        model = new ModelFile.UncheckedModelFile(modLoc("block/lerasium_cristal_block"));
        simpleBlock(ModBlocks.DIVINE_CRISTAL_BLOCKS.get(MetalsNBTData.LERASIUM.getGemNameLower()));
        simpleBlockItem(ModBlocks.DIVINE_CRISTAL_BLOCKS.get(MetalsNBTData.LERASIUM.getGemNameLower()),model);

        model = new ModelFile.UncheckedModelFile(modLoc("block/ettmetal_cristal_block"));
        simpleBlock(ModBlocks.DIVINE_CRISTAL_BLOCKS.get(MetalsNBTData.ETTMETAL.getGemNameLower()));
        simpleBlockItem(ModBlocks.DIVINE_CRISTAL_BLOCKS.get(MetalsNBTData.ETTMETAL.getGemNameLower()),model);


        //simpleBlock(ModBlock.BROKEN_CRISTAL_BLOCK.get());
        //simpleBlockItem(ModBlock.BROKEN_CRISTAL_BLOCK.get(), model);

    }
}

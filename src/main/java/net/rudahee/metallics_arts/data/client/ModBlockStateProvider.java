package net.rudahee.metallics_arts.data.client;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.ModBlock;

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
        ModBlock.BLOCK_GEMS_BLOCKS.forEach((name, block) -> {
                    // for each registry block, i get model wih format ("block/name_typeblock")
                    ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/" + block.getRegistryName().getPath()));

                    // later i put texture in block and item.
                    simpleBlock(block);
                    simpleBlockItem(block, model);
                }
        );
        ModBlock.BLOCK_METAL_ORES.forEach((name, block) -> {
                    // for each registry block, i get model wih format ("block/name_typeblock")
                    ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/" + block.getRegistryName().getPath()));

                    // later i put texture in block and item.
                    simpleBlock(block);
                    simpleBlockItem(block, model);
                }
        );
        ModBlock.BLOCK_METAL_BLOCKS.forEach((name, block) -> {
                    // for each registry block, i get model wih format ("block/name_typeblock")
                    ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/" + block.getRegistryName().getPath()));

                    // later i put texture in block and item.
                    simpleBlock(block);
                    simpleBlockItem(block, model);
                }
        );

        horizontalBlock(ModBlock.ALLOY_FURNACE_BLOCK.get(), new ModelFile.UncheckedModelFile(modLoc("block/alloy_furnace")));
        simpleBlockItem(ModBlock.ALLOY_FURNACE_BLOCK.get(), new ModelFile.UncheckedModelFile(modLoc("block/alloy_furnace")));


    }
}

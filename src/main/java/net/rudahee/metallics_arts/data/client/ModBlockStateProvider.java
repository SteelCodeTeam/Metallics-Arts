package net.rudahee.metallics_arts.data.client;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.Registration;

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
        Registration.BLOCKS.getEntries().forEach(
                block -> {
                    // for each registry block, i get model wih format ("block/name_typeblock")
                    ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/" + block.get().getRegistryName().getPath()));

                    // later i put texture in block and item.
                    simpleBlock(block.get());
                    simpleBlockItem(block.get(), model);
                }
        );

    }
}

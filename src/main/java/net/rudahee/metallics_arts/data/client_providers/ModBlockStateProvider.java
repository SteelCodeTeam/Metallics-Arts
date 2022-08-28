package net.rudahee.metallics_arts.data.client_providers;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.Property;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.blocks.alloy_furnace.AlloyFurnaceBlock;
import net.rudahee.metallics_arts.setup.registries.ModBlock;

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

        simpleBlock(ModBlock.ALLOY_FURNACE_BLOCK.get(), new ModelFile.UncheckedModelFile(modLoc("block/alloy_furnace_front")));
        simpleBlockItem(ModBlock.ALLOY_FURNACE_BLOCK.get(), new ModelFile.UncheckedModelFile(modLoc("block/alloy_furnace_front")));

        /**
        RECUERDA BORRAR LA TEXTURA ALLOY_FURNACE.PNG (deben quedar solo las front, side y top)
         */
        /*
        Alloy furnace texture. We need re-insert each time we do a "Run Data".
         */
        //models().orientable("alloy_furnace", modLoc("block/alloy_furnace_side"), modLoc("block/alloy_furnace_front"), modLoc("block/alloy_furnace_top"));

        //ConfiguredModel[] models = ConfiguredModel.allYRotations(new ModelFile.UncheckedModelFile(modLoc("block/" + ModBlock.ALLOY_FURNACE_BLOCK.get().getRegistryName().getPath())), 0, false);

       /* getVariantBuilder(ModBlock.ALLOY_FURNACE_BLOCK.get()).partialState()
                .with(AlloyFurnaceBlock.FACING, Direction.NORTH)
                .with(AlloyFurnaceBlock.LIT, false).addModels(models).modelForState();

        getVariantBuilder(ModBlock.ALLOY_FURNACE_BLOCK.get()).partialState()
                .with(AlloyFurnaceBlock.FACING, Direction.SOUTH)
                .with(AlloyFurnaceBlock.LIT, false).addModels(models).modelForState();

        getVariantBuilder(ModBlock.ALLOY_FURNACE_BLOCK.get()).partialState()
                .with(AlloyFurnaceBlock.FACING, Direction.EAST)
                .with(AlloyFurnaceBlock.LIT, false).addModels(models).modelForState();

        getVariantBuilder(ModBlock.ALLOY_FURNACE_BLOCK.get()).partialState()
                .with(AlloyFurnaceBlock.FACING, Direction.WEST)
                .with(AlloyFurnaceBlock.LIT, false).addModels(models).modelForState();

        getVariantBuilder(ModBlock.ALLOY_FURNACE_BLOCK.get()).partialState()
                .with(AlloyFurnaceBlock.FACING, Direction.NORTH)
                .with(AlloyFurnaceBlock.LIT, true).addModels(models).modelForState();

        getVariantBuilder(ModBlock.ALLOY_FURNACE_BLOCK.get()).partialState()
                .with(AlloyFurnaceBlock.FACING, Direction.SOUTH)
                .with(AlloyFurnaceBlock.LIT, true).addModels(models).modelForState();

        getVariantBuilder(ModBlock.ALLOY_FURNACE_BLOCK.get()).partialState()
                .with(AlloyFurnaceBlock.FACING, Direction.EAST)
                .with(AlloyFurnaceBlock.LIT, true).addModels(models).modelForState();

        getVariantBuilder(ModBlock.ALLOY_FURNACE_BLOCK.get()).partialState()
                .with(AlloyFurnaceBlock.FACING, Direction.WEST)
                .with(AlloyFurnaceBlock.LIT, true).addModels(models).modelForState();
        //getVariantBuilder(ModBlock.ALLOY_FURNACE_BLOCK.get()).setModels(state, models);*/

        //VariantBlockStateBuilder.forAllStates()


        //horizontalBlock(ModBlock.ALLOY_FURNACE_BLOCK.get(), new ModelFile.UncheckedModelFile(modLoc("block/alloy_furnace")));
        //simpleBlockItem(ModBlock.ALLOY_FURNACE_BLOCK.get(), new ModelFile.UncheckedModelFile(modLoc("block/alloy_furnace")));


    }
}

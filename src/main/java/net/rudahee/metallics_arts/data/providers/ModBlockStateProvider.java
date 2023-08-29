package net.rudahee.metallics_arts.data.providers;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.OreGenerationEnum;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

import java.util.HashMap;

/**
 * This provider class control the default state from blocks. We use to define a texture in each block.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see BlockStateProvider
 * @see Block
 * @see ModelFile
 */
public class ModBlockStateProvider extends BlockStateProvider {

    /**
     * Default constructor.These will be default parameters to register model textures in each block.
     * By default, always use "metallics_arts" mod id.
     *
     * @param generator class that will be used to generate the json that will contain the information
     * @param existingFileHelper class to check if path and image are valid in GatherDataEvent
     *
     * @see DataGenerator
     * @see ExistingFileHelper
     */
    public ModBlockStateProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), MetallicsArts.MOD_ID, existingFileHelper);
    }

    /**
     * In this override method we define which textures are linked to which blocks,
     * for this we use of two other auxiliary methods.

     * @see ModBlockStateProvider#simpleBlock
     * @see ModBlockStateProvider#simpleBlockItem
     */
    @Override
    protected void registerStatesAndModels() {

        /*
            I don't know why the hell I can't order the textures of the blocks,
            but I can order the textures of the items.

            so I just grab all the blocks and put the texture of the block folder in them.
         */
        ModBlocksRegister.BLOCK_GEMS_BLOCKS.forEach((name, block) -> {
            ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/" + name + "_block"));

            // later I put texture in block and item.
            simpleBlock(block);
            simpleBlockItem(block, model);

        });

        ModBlocksRegister.BLOCK_METAL_ORES.forEach((name, block) -> {
            // for each registry block, i get model wih format ("block/name_typeblock")
            ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/" + name + "_ore"));

            // later I put texture in block and item.
            simpleBlock(block);
            simpleBlockItem(block, model);
        });

        ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.forEach((name, block) -> {
            // for each registry block, i get model wih format ("block/name_typeblock")
            ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/deepslate_" + name + "_ore"));

            // later I put texture in block and item.
            simpleBlock(block);
            simpleBlockItem(block, model);
        });

        ModBlocksRegister.BLOCK_METAL_BLOCKS.forEach((name, block) -> {
            // for each registry block, i get model wih format ("block/name_typeblock")
            ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/" + name + "_block"));

            // later, I put texture in block and item.
            simpleBlock(block);
            simpleBlockItem(block, model);
        });

        ModBlocksRegister.RAW_METAL_BLOCKS.forEach((name, block) -> {
            // for each registry block, i get model wih format ("block/name_typeblock")
            ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/raw_" + name + "_block"));

            // later, I put texture in block and item.
            simpleBlock(block);
            simpleBlockItem(block, model);
        });

        ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/budding_atium"));
        simpleBlock(ModBlocksRegister.BUDDING_ATIUM.get());
        simpleBlockItem(ModBlocksRegister.BUDDING_ATIUM.get(),model);

        model = new ModelFile.UncheckedModelFile(modLoc("block/budding_lerasium"));

        simpleBlock(ModBlocksRegister.BUDDING_LERASIUM.get());
        simpleBlockItem(ModBlocksRegister.BUDDING_LERASIUM.get(),model);

        model = new ModelFile.UncheckedModelFile(modLoc("block/budding_ettmetal"));

        simpleBlock(ModBlocksRegister.BUDDING_ETTMETAL.get());
        simpleBlockItem(ModBlocksRegister.BUDDING_ETTMETAL.get(),model);


        model = new ModelFile.UncheckedModelFile(modLoc("block/atium_cristal_block"));
        simpleBlock(ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.get(MetalTagEnum.ATIUM.getGemNameLower()));
        simpleBlockItem(ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.get(MetalTagEnum.ATIUM.getGemNameLower()),model);

        model = new ModelFile.UncheckedModelFile(modLoc("block/lerasium_cristal_block"));
        simpleBlock(ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.get(MetalTagEnum.LERASIUM.getGemNameLower()));
        simpleBlockItem(ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.get(MetalTagEnum.LERASIUM.getGemNameLower()),model);

        model = new ModelFile.UncheckedModelFile(modLoc("block/ettmetal_cristal_block"));
        simpleBlock(ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.get(MetalTagEnum.ETTMETAL.getGemNameLower()));
        simpleBlockItem(ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.get(MetalTagEnum.ETTMETAL.getGemNameLower()),model);

        model = new ModelFile.UncheckedModelFile(modLoc("block/crucible_furnace"));
        horizontalBlock(ModBlocksRegister.CRUCIBLE_FURNACE.get(), model);
        //simpleBlock(ModBlocksRegister.CRUCIBLE_FURNACE.get());



        for (MetalEnum metal : MetalEnum.values()){
            stairsBlock((StairBlock) ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getMetalNameLower()), blockTexture(ModBlocksRegister.BLOCK_METAL_BLOCKS.get(metal.getMetalNameLower())));
            slabBlock((SlabBlock) ModBlocksRegister.BLOCK_METAL_SLABS.get(metal.getMetalNameLower()), blockTexture(ModBlocksRegister.BLOCK_METAL_BLOCKS.get(metal.getMetalNameLower())), blockTexture(ModBlocksRegister.BLOCK_METAL_BLOCKS.get(metal.getMetalNameLower())));
            wallBlock((WallBlock) ModBlocksRegister.BLOCK_METAL_WALLS.get(metal.getMetalNameLower()), blockTexture(ModBlocksRegister.BLOCK_METAL_BLOCKS.get(metal.getMetalNameLower())));
            fenceBlock((FenceBlock) ModBlocksRegister.BLOCK_METAL_FENCES.get(metal.getMetalNameLower()), blockTexture(ModBlocksRegister.BLOCK_METAL_BLOCKS.get(metal.getMetalNameLower())));
            fenceGateBlock((FenceGateBlock) ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get(metal.getMetalNameLower()), blockTexture(ModBlocksRegister.BLOCK_METAL_BLOCKS.get(metal.getMetalNameLower())));

            if (!metal.isAlloy()) {
                stairsBlock((StairBlock) ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getMetalNameLower() + "_raw"), blockTexture(ModBlocksRegister.RAW_METAL_BLOCKS.get(metal.getMetalNameLower())));
                slabBlock((SlabBlock) ModBlocksRegister.BLOCK_METAL_SLABS.get(metal.getMetalNameLower() + "_raw"), blockTexture(ModBlocksRegister.RAW_METAL_BLOCKS.get(metal.getMetalNameLower())), blockTexture(ModBlocksRegister.RAW_METAL_BLOCKS.get(metal.getMetalNameLower())));
                wallBlock((WallBlock) ModBlocksRegister.BLOCK_METAL_WALLS.get(metal.getMetalNameLower() + "_raw"), blockTexture(ModBlocksRegister.RAW_METAL_BLOCKS.get(metal.getMetalNameLower())));
                fenceBlock((FenceBlock) ModBlocksRegister.BLOCK_METAL_FENCES.get(metal.getMetalNameLower() + "_raw"), blockTexture(ModBlocksRegister.RAW_METAL_BLOCKS.get(metal.getMetalNameLower())));
                fenceGateBlock((FenceGateBlock) ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get(metal.getMetalNameLower() + "_raw"), blockTexture(ModBlocksRegister.RAW_METAL_BLOCKS.get(metal.getMetalNameLower())));
            }
        }
        for (GemsEnum metal : GemsEnum.values()){
            stairsBlock((StairBlock) ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getGemNameLower()), blockTexture(ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(metal.getGemNameLower())));
            slabBlock((SlabBlock) ModBlocksRegister.BLOCK_METAL_SLABS.get(metal.getGemNameLower()), blockTexture(ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(metal.getGemNameLower())), blockTexture(ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(metal.getGemNameLower())));
            wallBlock((WallBlock) ModBlocksRegister.BLOCK_METAL_WALLS.get(metal.getGemNameLower()), blockTexture(ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(metal.getGemNameLower())));
            fenceBlock((FenceBlock) ModBlocksRegister.BLOCK_METAL_FENCES.get(metal.getGemNameLower()), blockTexture(ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(metal.getGemNameLower())));
            fenceGateBlock((FenceGateBlock) ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get(metal.getGemNameLower()), blockTexture(ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(metal.getGemNameLower())));
        }


        stairsBlock((StairBlock) ModBlocksRegister.BLOCK_METAL_STAIRS.get("iron"), blockTexture(Blocks.IRON_BLOCK));
        stairsBlock((StairBlock) ModBlocksRegister.BLOCK_METAL_STAIRS.get("gold"), blockTexture(Blocks.GOLD_BLOCK));
        stairsBlock((StairBlock) ModBlocksRegister.BLOCK_METAL_STAIRS.get("iron_raw"), blockTexture(Blocks.RAW_IRON_BLOCK));
        stairsBlock((StairBlock) ModBlocksRegister.BLOCK_METAL_STAIRS.get("gold_raw"), blockTexture(Blocks.RAW_GOLD_BLOCK));
        stairsBlock((StairBlock) ModBlocksRegister.BLOCK_METAL_STAIRS.get("copper_raw"), blockTexture(Blocks.RAW_COPPER_BLOCK));

        slabBlock((SlabBlock) ModBlocksRegister.BLOCK_METAL_SLABS.get("gold"), blockTexture(Blocks.GOLD_BLOCK), blockTexture(Blocks.GOLD_BLOCK));
        slabBlock((SlabBlock) ModBlocksRegister.BLOCK_METAL_SLABS.get("iron"), blockTexture(Blocks.IRON_BLOCK), blockTexture(Blocks.IRON_BLOCK));
        slabBlock((SlabBlock) ModBlocksRegister.BLOCK_METAL_SLABS.get("gold_raw"), blockTexture(Blocks.RAW_GOLD_BLOCK), blockTexture(Blocks.RAW_GOLD_BLOCK));
        slabBlock((SlabBlock) ModBlocksRegister.BLOCK_METAL_SLABS.get("iron_raw"), blockTexture(Blocks.RAW_IRON_BLOCK), blockTexture(Blocks.RAW_IRON_BLOCK));
        slabBlock((SlabBlock) ModBlocksRegister.BLOCK_METAL_SLABS.get("copper_raw"), blockTexture(Blocks.RAW_COPPER_BLOCK), blockTexture(Blocks.RAW_COPPER_BLOCK));

        wallBlock((WallBlock) ModBlocksRegister.BLOCK_METAL_WALLS.get("iron"), blockTexture(Blocks.IRON_BLOCK));
        wallBlock((WallBlock) ModBlocksRegister.BLOCK_METAL_WALLS.get("gold"), blockTexture(Blocks.GOLD_BLOCK));
        wallBlock((WallBlock) ModBlocksRegister.BLOCK_METAL_WALLS.get("copper"), blockTexture(Blocks.COPPER_BLOCK));
        wallBlock((WallBlock) ModBlocksRegister.BLOCK_METAL_WALLS.get("iron_raw"), blockTexture(Blocks.RAW_IRON_BLOCK));
        wallBlock((WallBlock) ModBlocksRegister.BLOCK_METAL_WALLS.get("gold_raw"), blockTexture(Blocks.RAW_GOLD_BLOCK));
        wallBlock((WallBlock) ModBlocksRegister.BLOCK_METAL_WALLS.get("copper_raw"), blockTexture(Blocks.RAW_COPPER_BLOCK));

        fenceBlock((FenceBlock) ModBlocksRegister.BLOCK_METAL_FENCES.get("iron"), blockTexture(Blocks.IRON_BLOCK));
        fenceBlock((FenceBlock) ModBlocksRegister.BLOCK_METAL_FENCES.get("gold"), blockTexture(Blocks.GOLD_BLOCK));
        fenceBlock((FenceBlock) ModBlocksRegister.BLOCK_METAL_FENCES.get("copper"), blockTexture(Blocks.COPPER_BLOCK));
        fenceBlock((FenceBlock) ModBlocksRegister.BLOCK_METAL_FENCES.get("iron_raw"), blockTexture(Blocks.RAW_IRON_BLOCK));
        fenceBlock((FenceBlock) ModBlocksRegister.BLOCK_METAL_FENCES.get("gold_raw"), blockTexture(Blocks.RAW_GOLD_BLOCK));
        fenceBlock((FenceBlock) ModBlocksRegister.BLOCK_METAL_FENCES.get("copper_raw"), blockTexture(Blocks.RAW_COPPER_BLOCK));

        fenceGateBlock((FenceGateBlock) ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get("iron"), blockTexture(Blocks.IRON_BLOCK));
        fenceGateBlock((FenceGateBlock) ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get("gold"), blockTexture(Blocks.GOLD_BLOCK));
        fenceGateBlock((FenceGateBlock) ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get("copper"), blockTexture(Blocks.COPPER_BLOCK));
        fenceGateBlock((FenceGateBlock) ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get("iron_raw"), blockTexture(Blocks.RAW_IRON_BLOCK));
        fenceGateBlock((FenceGateBlock) ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get("gold_raw"), blockTexture(Blocks.RAW_GOLD_BLOCK));
        fenceGateBlock((FenceGateBlock) ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get("copper_raw"), blockTexture(Blocks.RAW_COPPER_BLOCK));
    }
}

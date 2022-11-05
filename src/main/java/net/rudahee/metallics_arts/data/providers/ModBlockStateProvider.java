package net.rudahee.metallics_arts.data.providers;

import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.registries.ModBlock;

import java.util.function.BiConsumer;

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
            ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/" + name + "_block"));

            // later i put texture in block and item.
            simpleBlock(block);
            simpleBlockItem(block, model);

        });

        ModBlock.BLOCK_METAL_ORES.forEach((name, block) -> {
            // for each registry block, i get model wih format ("block/name_typeblock")
            ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/" + name + "_ore"));

            // later i put texture in block and item.
            simpleBlock(block);
            simpleBlockItem(block, model);
        });

        ModBlock.BLOCK_METAL_DEEPSLATE_ORES.forEach((name, block) -> {
            // for each registry block, i get model wih format ("block/name_typeblock")
            ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/deepslate_" + name + "_ore"));

            // later i put texture in block and item.
            simpleBlock(block);
            simpleBlockItem(block, model);
        });

        ModBlock.BLOCK_METAL_BLOCKS.forEach((name, block) -> {
            // for each registry block, i get model wih format ("block/name_typeblock")
            ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/" + name + "_block"));

            // later, I put texture in block and item.
            simpleBlock(block);
            simpleBlockItem(block, model);
        });

        ModBlock.RAW_METAL_BLOCKS.forEach((name, block) -> {
            // for each registry block, i get model wih format ("block/name_typeblock")
            ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/raw_" + name + "_block"));

            // later, I put texture in block and item.
            simpleBlock(block);
            simpleBlockItem(block, model);
        });

        ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/budding_atium"));
        simpleBlock(ModBlock.BUDDING_ATIUM.get());
        simpleBlockItem(ModBlock.BUDDING_ATIUM.get(),model);

        model = new ModelFile.UncheckedModelFile(modLoc("block/budding_lerasium"));

        simpleBlock(ModBlock.BUDDING_LERASIUM.get());
        simpleBlockItem(ModBlock.BUDDING_LERASIUM.get(),model);

        model = new ModelFile.UncheckedModelFile(modLoc("block/budding_ettmetal"));

        simpleBlock(ModBlock.BUDDING_ETTMETAL.get());
        simpleBlockItem(ModBlock.BUDDING_ETTMETAL.get(),model);


        model = new ModelFile.UncheckedModelFile(modLoc("block/atium_cristal_block"));
        simpleBlock(ModBlock.DIVINE_CRISTAL_BLOCKS.get(MetalsNBTData.ATIUM.getGemNameLower()));
        simpleBlockItem(ModBlock.DIVINE_CRISTAL_BLOCKS.get(MetalsNBTData.ATIUM.getGemNameLower()),model);

        model = new ModelFile.UncheckedModelFile(modLoc("block/lerasium_cristal_block"));
        simpleBlock(ModBlock.DIVINE_CRISTAL_BLOCKS.get(MetalsNBTData.LERASIUM.getGemNameLower()));
        simpleBlockItem(ModBlock.DIVINE_CRISTAL_BLOCKS.get(MetalsNBTData.LERASIUM.getGemNameLower()),model);

        model = new ModelFile.UncheckedModelFile(modLoc("block/ettmetal_cristal_block"));
        simpleBlock(ModBlock.DIVINE_CRISTAL_BLOCKS.get(MetalsNBTData.ETTMETAL.getGemNameLower()));
        simpleBlockItem(ModBlock.DIVINE_CRISTAL_BLOCKS.get(MetalsNBTData.ETTMETAL.getGemNameLower()),model);


        //simpleBlock(ModBlock.BROKEN_CRISTAL_BLOCK.get());
        //simpleBlockItem(ModBlock.BROKEN_CRISTAL_BLOCK.get(), model);

//        private void createIronButtonBlock() {
//
//            ModelFile inventory = models().withExistingParent("allomancy:iron_button_inventory", mcLoc("block/button_inventory")).texture("texture", mcLoc("block/iron_block"));
//            ModelFile button = models().withExistingParent("allomancy:iron_button", mcLoc("block/button")).texture("texture", mcLoc("block/iron_block"));
//            ModelFile pressed = models().withExistingParent("allomancy:iron_button_pressed", mcLoc("block/button_pressed")).texture("texture", mcLoc("block/iron_block"));
//
//            VariantBlockStateBuilder builder = getVariantBuilder(ExtrasSetup.IRON_BUTTON.get());
//            for (Boolean powered : IronButtonBlock.POWERED.getPossibleValues()) {
//                ModelFile model = powered ? pressed : button;
//                for (AttachFace face : IronButtonBlock.FACE.getPossibleValues()) {
//                    int xangle = (face == AttachFace.CEILING) ? 180 : (face == AttachFace.WALL) ? 90 : 0;
//                    boolean uvlock = face == AttachFace.WALL;
//                    for (Direction dir : IronButtonBlock.FACING.getPossibleValues()) {
//                        int yangle = (int) dir.toYRot();
//                        yangle = face != AttachFace.CEILING ? (yangle + 180) % 360 : yangle;
//                        builder
//                                .partialState()
//                                .with(IronButtonBlock.POWERED, powered)
//                                .with(IronButtonBlock.FACE, face)
//                                .with(IronButtonBlock.FACING, dir)
//                                .modelForState()
//                                .modelFile(model)
//                                .uvLock(uvlock)
//                                .rotationY(yangle)
//                                .rotationX(xangle)
//                                .addModel();
//                    }
//                }
//            }
//        }
//
//        private void createIronLeverBlock() {
//            Allomancy.LOGGER.debug("Creating Block Data for allomancy:iron_lever");
//
//            BiConsumer<Direction, ModelBuilder<BlockModelBuilder>.ElementBuilder.FaceBuilder> base_generator = (dir, facebuilder) -> {
//                switch (dir) {
//                    case UP -> facebuilder.uvs(5, 4, 11, 12).texture("#base").end();
//                    case DOWN -> facebuilder.uvs(5, 4, 11, 12).texture("#base").cullface(Direction.DOWN).end();
//                    case NORTH, SOUTH -> facebuilder.uvs(5, 0, 11, 3).texture("#base").end();
//                    case WEST, EAST -> facebuilder.uvs(4, 0, 12, 3).texture("#base").end();
//                }
//            };
//
//            BiConsumer<Direction, ModelBuilder<BlockModelBuilder>.ElementBuilder.FaceBuilder> lever_generator = (dir, facebuilder) -> {
//                switch (dir) {
//                    case UP -> facebuilder.uvs(7, 6, 9, 8).texture("#lever").end();
//                    case NORTH, SOUTH, WEST, EAST -> facebuilder.uvs(7, 6, 9, 16).texture("#lever").end();
//                    case DOWN -> facebuilder.end();
//                }
//            };
//
//            ModelFile lever_on = models()
//                    .getBuilder("allomancy:iron_lever")
//                    .ao(false)
//                    .texture("particle", mcLoc("block/iron_block"))
//                    .texture("base", mcLoc("block/iron_block"))
//                    .texture("lever", modLoc("block/iron_lever"))
//                    .element()
//                    .from(5, 0, 4)
//                    .to(11, 3, 12)
//                    .allFaces(base_generator)
//                    .end()
//                    .element()
//                    .from(7, 1, 7)
//                    .to(9, 11, 9)
//                    .rotation()
//                    .origin(8, 1, 8)
//                    .axis(Direction.Axis.X)
//                    .angle(-45F)
//                    .end()
//                    .allFaces(lever_generator)
//                    .end();
//
//            ModelFile lever_off = models()
//                    .getBuilder("allomancy:iron_lever_off")
//                    .ao(false)
//                    .texture("particle", mcLoc("block/iron_block"))
//                    .texture("base", mcLoc("block/iron_block"))
//                    .texture("lever", modLoc("block/iron_lever"))
//                    .element()
//                    .from(5, 0, 4)
//                    .to(11, 3, 12)
//                    .allFaces(base_generator)
//                    .end()
//                    .element()
//                    .from(7, 1, 7)
//                    .to(9, 11, 9)
//                    .rotation()
//                    .origin(8, 1, 8)
//                    .axis(Direction.Axis.X)
//                    .angle(45)
//                    .end()
//                    .allFaces(lever_generator)
//                    .end();
//
//            VariantBlockStateBuilder builder = getVariantBuilder(ExtrasSetup.IRON_LEVER.get());
//            for (Boolean powered : IronLeverBlock.POWERED.getPossibleValues()) {
//                ModelFile model = powered ? lever_on : lever_off;
//                for (AttachFace face : IronLeverBlock.FACE.getPossibleValues()) {
//                    int xangle = (face == AttachFace.CEILING) ? 180 : (face == AttachFace.WALL) ? 90 : 0;
//
//                    for (Direction dir : IronLeverBlock.FACING.getPossibleValues()) {
//                        int yangle = (int) dir.toYRot();
//                        yangle = face != AttachFace.CEILING ? (yangle + 180) % 360 : yangle;
//
//                        builder
//                                .partialState()
//                                .with(IronLeverBlock.POWERED, powered)
//                                .with(IronLeverBlock.FACE, face)
//                                .with(IronLeverBlock.FACING, dir)
//                                .modelForState()
//                                .modelFile(model)
//                                .rotationY(yangle)
//                                .rotationX(xangle)
//                                .addModel();
//                    }
//                }
//            }
    }
}

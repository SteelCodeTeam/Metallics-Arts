package net.rudahee.metallics_arts.setup.registries;


import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.modules.blocks.EttmetalBlock;
import net.rudahee.metallics_arts.setup.Registration;
import net.rudahee.metallics_arts.setup.enums.gems.Gems;
import net.rudahee.metallics_arts.setup.enums.metals.Metal;
import net.rudahee.metallics_arts.setup.enums.metals.MetalGenerationData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;


public class ModBlock {

    public static final HashMap<String, Block> BLOCK_METAL_ORES = new HashMap<String, Block>();
    public static final HashMap<String, Block> BLOCK_METAL_BLOCKS = new HashMap<String, Block>();
    public static final HashMap<String, Block> BLOCK_GEMS_BLOCKS = new HashMap<String, Block>();

    static {
        /*
            INITIALIZING METALS
         */
        List<Metal> metalList = Arrays.asList(Metal.values());

        metalList.forEach(metal -> {
            // If not alloy, create ore.
            if (!metal.isAlloy()) {
                register(metal.getMetalNameLower() + "_ore", () -> {
                    Block block = new Block(Block.Properties.of(Material.METAL)
                            .strength(3, 10)
                            .sound(SoundType.STONE)
                            .requiresCorrectToolForDrops());
                    MetalGenerationData.valueOf(metal.getMetalNameUpper()).setBlock(block);
                    BLOCK_METAL_ORES.put(metal.getMetalNameLower(), block);
                    return block;
                });
            }

            // Always create block.
            register(metal.getMetalNameLower() + "_block", () -> {
                Block block = new Block(Block.Properties.of(Material.METAL)
                        .strength(5, 15)
                        .sound(SoundType.METAL)
                        .requiresCorrectToolForDrops());

                BLOCK_METAL_BLOCKS.put(metal.getMetalNameLower(), block);
                return block;
            });

        });
    }

    static {
        /*
            INITIALIZING GEMS
         */
        List<Gems> gemsList = Arrays.asList(Gems.values());

        gemsList.forEach(gem -> {
            // Always create blocks.

            if (!gem.getGemNameLower().equals(Gems.ETTMETAL.getGemNameLower())) {
                register(gem.getGemNameLower() + "_block", () -> {
                    Block block = new Block(Block.Properties.of(Material.METAL)
                            .strength(10, 25)
                            .sound(SoundType.METAL)
                            .randomTicks()
                            .requiresCorrectToolForDrops());

                    BLOCK_GEMS_BLOCKS.put(gem.getGemNameLower(), block);
                    return block;
                });
            }

        });
    }

    /*public static final RegistryObject<Block> ALLOY_FURNACE_BLOCK = register("alloy_furnace",
            () -> new AlloyFurnaceBlock(Block.Properties.of(Material.STONE)
                                                .strength(3.5F)
                                                .requiresCorrectToolForDrops()));*/

    public static final RegistryObject<Block> ETTMETAL_BLOCK = register(Gems.ETTMETAL.getGemNameLower()+"_block",
            () -> {
                Block block = new EttmetalBlock(Block.Properties.of(Material.HEAVY_METAL).strength(10f, 10f)
                        .sound(SoundType.METAL)
                        .requiresCorrectToolForDrops());

                BLOCK_GEMS_BLOCKS.put(Gems.ETTMETAL.getGemNameLower(), block);
                return block;
            });

    public static void register() {
    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> blockSupplier) {
        return Registration.BLOCKS.register(name, blockSupplier);
    }


    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier) {

        RegistryObject<T> blockRegistered = registerNoItem(name, blockSupplier);
        Registration.ITEMS.register(name, () -> (new BlockItem(blockRegistered.get(), new Item.Properties().tab(ModItemGroup.METALLIC_ARTS_TAG))));
        return blockRegistered;
    }

    private static ToIntFunction<BlockState> litBlockEmission(int value) {
        return (funcValue) -> {
            return funcValue.getValue(BlockStateProperties.LIT) ? value : 0;
        };
    }
}

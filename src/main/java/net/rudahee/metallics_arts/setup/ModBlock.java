package net.rudahee.metallics_arts.setup;


import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.rudahee.metallics_arts.block.alloyfurnace.ModAlloyFurnaceBlock;
import net.rudahee.metallics_arts.setup.enums.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

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
                            .harvestLevel(2)
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
                        .harvestLevel(3)
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
            register(gem.getGemNameLower() + "_block", () -> {
                Block block = new Block(Block.Properties.of(Material.METAL)
                        .strength(10, 25)
                        .harvestLevel(3)
                        .sound(SoundType.METAL)
                        .requiresCorrectToolForDrops());

                BLOCK_GEMS_BLOCKS.put(gem.getGemNameLower(), block);
                return block;
            });

        });
    }

    public static Block ALLOY_FURNACE_BLOCK = null;
    public static RegistryObject<?> ALLOY_FURNACE_REGISTRY = null;

    static {
        ALLOY_FURNACE_REGISTRY = register("alloy_furnace", () -> {
            ALLOY_FURNACE_BLOCK = new ModAlloyFurnaceBlock(AbstractBlock.Properties.of(Material.METAL)
                    .strength(4, 20)
                    .sound(SoundType.METAL));

            return ALLOY_FURNACE_BLOCK;
        });

    }

    protected static void register() {
    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> blockSupplier) {
        return Registration.BLOCKS.register(name, blockSupplier);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier) {

        RegistryObject<T> blockRegistered = registerNoItem(name, blockSupplier);
        Registration.ITEMS.register(name, () -> (new BlockItem(blockRegistered.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS))));

        return blockRegistered;
    }
}

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
import net.rudahee.metallics_arts.setup.enums.Gems;
import net.rudahee.metallics_arts.setup.enums.Metal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ModBlock {

    public static final ArrayList<RegistryObject> METAL_BLOCKS = new ArrayList<RegistryObject>();
    public static final ArrayList<RegistryObject> METAL_ORES = new ArrayList<RegistryObject>();
    public static final ArrayList<RegistryObject> GEMS_BLOCKS = new ArrayList<RegistryObject>();

    static {
        /*
            INITIALIZING METALS
         */
        List<Metal> metalList = Arrays.asList(Metal.values());

        metalList.forEach(metal -> {
            // If not alloy, create ore.
            if (!metal.isAlloy()) {
            METAL_ORES.add(
                register(metal.getMetalNameLower() + "_ore",
                        () -> (new Block(Block.Properties.of(Material.METAL)
                                .strength(3,10)
                                .harvestLevel(2)
                                .sound(SoundType.STONE)
                                .requiresCorrectToolForDrops()
                        ))));
            }

            // Always create block.
            METAL_BLOCKS.add(
                    register(metal.getMetalNameLower() + "_block",
                        () -> (new Block(Block.Properties.of(Material.METAL)
                            .strength(5, 15)
                            .harvestLevel(3)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops()
                        ))));

        });
    }

    static {
        /*
            INITIALIZING GEMS
         */
        List<Gems> gemsList = Arrays.asList(Gems.values());

        gemsList.forEach(gem -> {
            // Always create blocks.
            GEMS_BLOCKS.add(
                register(gem.getGemNameLower() + "_block",
                        () -> (new Block(Block.Properties.of(Material.METAL)
                                .strength(10, 25)
                                .harvestLevel(3)
                                .sound(SoundType.METAL)
                                .requiresCorrectToolForDrops()
                        ))));

            });
    }

    public static final RegistryObject<?> alloyFurnace = register("alloyFurnace",() ->
            new ModAlloyFurnaceBlock(AbstractBlock.Properties.of(Material.METAL)
                    .strength(4,20)
                    .sound(SoundType.METAL)));

    protected static void register() {}

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> blockSupplier) {
        return Registration.BLOCKS.register(name, blockSupplier);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier) {
        RegistryObject<T> blockRegistered = registerNoItem(name, blockSupplier);
        Registration.ITEMS.register(name, () -> (new BlockItem(blockRegistered.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS))));

        return blockRegistered;
    }
}

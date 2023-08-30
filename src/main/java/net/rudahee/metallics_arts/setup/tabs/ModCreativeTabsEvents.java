package net.rudahee.metallics_arts.setup.tabs;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.MetalMindEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.SpikeEnum;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ModCreativeTabsEvents {


    public static void addToCombatTab(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab().equals(CreativeModeTabs.COMBAT)) {
            event.acceptAll(ModItemsRegister.STEEL_ARMOR.values().stream().map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).collect(Collectors.toList()));
            event.acceptAll(ModItemsRegister.ALUMINUM_ARMOR.values().stream().map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).collect(Collectors.toList()));
        }
    }

    public static void addToMetallicsArtsTab(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab().equals(ModCreativeTabs.METALLICS_ARTS_TAB)) {
            event.accept(ModItemsRegister.METALLICS_ARTS_BOOK);

            event.acceptAll(ModItemsRegister.ITEM_RAW_METAL.values().stream().map(ItemStack::new).collect(Collectors.toList()));
            event.acceptAll(ModItemsRegister.ITEM_METAL_INGOT.values().stream().map(ItemStack::new).collect(Collectors.toList()));
            event.acceptAll(ModItemsRegister.ITEM_GEMS_BASE.values().stream().map(ItemStack::new).collect(Collectors.toList()));
            event.acceptAll(ModItemsRegister.ITEM_METAL_NUGGET.values().stream().map(ItemStack::new).collect(Collectors.toList()));
            event.acceptAll(ModItemsRegister.ITEM_GEMS_NUGGET.values().stream().map(ItemStack::new).collect(Collectors.toList()));

            event.acceptAll(Arrays.asList(SpikeEnum.values()).stream().map(spikeEnum -> new ItemStack(spikeEnum.getSpike())).collect(Collectors.toList()));
            event.acceptAll(Arrays.asList(MetalMindEnum.values()).stream().map(ringEnum -> new ItemStack(ringEnum.getRing())).collect(Collectors.toList()));
            event.acceptAll(Arrays.asList(MetalMindEnum.values()).stream().map(bandEnum -> new ItemStack(bandEnum.getBand())).collect(Collectors.toList()));

            event.accept(ModBlocksRegister.CRUCIBLE_FURNACE);

            event.acceptAll(ModItemsRegister.STEEL_ARMOR.values().stream().map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).collect(Collectors.toList()));
            event.acceptAll(ModItemsRegister.ALUMINUM_ARMOR.values().stream().map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).collect(Collectors.toList()));

        }
    }


    public static void addToMetallicsArtsDecorationTab(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab().equals(ModCreativeTabs.METTALLICS_ARTS_DECORATION_TAB)) {
            event.accept(ModItemsRegister.METALLICS_ARTS_BOOK);

            event.acceptAll(ModBlocksRegister.BLOCK_METAL_ORES.values().stream().map(ItemStack::new).collect(Collectors.toList()));
            event.acceptAll(ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.values().stream().map(ItemStack::new).collect(Collectors.toList()));

            event.acceptAll(ModBlocksRegister.RAW_METAL_BLOCKS.values().stream().map(ItemStack::new).collect(Collectors.toList()));
            event.acceptAll(ModBlocksRegister.BLOCK_METAL_BLOCKS.values().stream().map(ItemStack::new).collect(Collectors.toList()));
            event.acceptAll(ModBlocksRegister.BLOCK_GEMS_BLOCKS.values().stream().map(ItemStack::new).collect(Collectors.toList()));


            event.acceptAll(ModBlocksRegister.BLOCK_METAL_SLABS.values().stream().map(ItemStack::new).collect(Collectors.toList()));
            event.acceptAll(ModBlocksRegister.BLOCK_METAL_STAIRS.values().stream().map(ItemStack::new).collect(Collectors.toList()));

            event.acceptAll(ModBlocksRegister.BLOCK_METAL_WALLS.values().stream().map(ItemStack::new).collect(Collectors.toList()));
            event.acceptAll(ModBlocksRegister.BLOCK_METAL_FENCES.values().stream().map(ItemStack::new).collect(Collectors.toList()));
            event.acceptAll(ModBlocksRegister.BLOCK_METAL_FENCE_GATES.values().stream().map(ItemStack::new).collect(Collectors.toList()));

            event.acceptAll(ModItemsRegister.ITEM_ICONS_ALLOMANCY.values().stream().map(ItemStack::new).collect(Collectors.toList()));
            event.acceptAll(ModItemsRegister.ITEM_ICONS_ALLOMANCY_DIVINE.values().stream().map(ItemStack::new).collect(Collectors.toList()));
            event.acceptAll(ModItemsRegister.ITEM_ICONS_FERUCHEMIC.values().stream().map(ItemStack::new).collect(Collectors.toList()));
            event.acceptAll(ModItemsRegister.ITEM_ICONS_FERUCHEMIC_DIVINE.values().stream().map(ItemStack::new).collect(Collectors.toList()));
        }
    }

}

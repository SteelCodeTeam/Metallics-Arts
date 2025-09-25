package net.rudahee.metallics_arts.setup.tabs;

import com.klikli_dev.modonomicon.data.BookDataManager;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.fml.ModList;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.MetalMindEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.SpikeEnum;
import net.rudahee.metallics_arts.setup.registries.ModBannersRegister;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

import java.util.Arrays;

public class ModCreativeTabsEvents {

    private ModCreativeTabsEvents() {
        throw new IllegalStateException("Class can't be instantiated");
    }

    public static void addToCombatTab(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab().equals(CreativeModeTabs.COMBAT)) {
            armorsCreativeTab(event);
        }
    }

    public static void addToMetallicsArtsTab(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab().equals(ModCreativeTabs.METALLICS_ARTS_TAB)) {
            if (ModList.get().isLoaded("modonomicon")) {
                BookDataManager.get().getBooks().values().forEach(books -> {

                    if (books.getId().toString().contains("metallics_arts")) {
                        event.accept(books.getBookItem());
                    }
                });
            }

            event.accept(ModItemsRegister.SAZED_DISC);
            event.acceptAll(ModItemsRegister.ITEM_RAW_METAL.values().stream().map(ItemStack::new).toList());
            event.acceptAll(ModItemsRegister.ITEM_METAL_INGOT.values().stream().map(ItemStack::new).toList());
            event.acceptAll(ModItemsRegister.ITEM_GEMS_BASE.values().stream().map(ItemStack::new).toList());
            event.acceptAll(ModItemsRegister.ITEM_METAL_NUGGET.values().stream().map(ItemStack::new).toList());
            event.acceptAll(ModItemsRegister.ITEM_GEMS_NUGGET.values().stream().map(ItemStack::new).toList());

            event.acceptAll(Arrays.stream(SpikeEnum.values()).map(spikeEnum -> new ItemStack(spikeEnum.getSpike())).toList());
            event.acceptAll(Arrays.stream(MetalMindEnum.values()).map(ringEnum -> new ItemStack(ringEnum.getRing())).toList());
            event.acceptAll(Arrays.stream(MetalMindEnum.values()).map(bandEnum -> new ItemStack(bandEnum.getBand())).toList());

            event.accept(ModBlocksRegister.CRUCIBLE_FURNACE);
            event.accept(ModBlocksRegister.HEMALURGY_ALTAR_FRONT);
            event.accept(ModBlocksRegister.HEMALURGY_ALTAR_BACK);
            event.accept(ModBlocksRegister.DISTILLERY);

            event.accept(ModItemsRegister.IRON_SIGN);
            event.accept(ModItemsRegister.GOLD_SIGN);
            event.accept(ModItemsRegister.COPPER_SIGN);
            event.accept(ModItemsRegister.ALUMINUM_SIGN);
            event.accept(ModBlocksRegister.ALLOMANTIC_LEVER);
            event.accept(ModBlocksRegister.ALLOMANTIC_PUSH_BUTTON);
            event.accept(ModBlocksRegister.ALLOMANTIC_PULL_BUTTON);
            event.accept(ModItemsRegister.SMALL_VIAL);
            event.accept(ModItemsRegister.LARGE_VIAL);


            ModItemsRegister.addChargeInList(); //todo Mirar si se puede hacer en otro lado - lo tuve que poner aca porque no registraba a tiempo los items sino

            armorsCreativeTab(event);

            event.accept(ModItemsRegister.MISTCLOACK);

        }
    }

    private static void armorsCreativeTab(CreativeModeTabEvent.BuildContents event) {
        event.acceptAll(ModItemsRegister.ITEM_CORES.values().stream().map(ItemStack::new).toList());
        event.acceptAll(ModItemsRegister.ITEM_MELE_WEAPON.values().stream().map(ItemStack::new).toList());
        event.acceptAll(ModItemsRegister.ITEM_RANGE_WEAPON.values().stream().map(ItemStack::new).toList());

        event.acceptAll(ModItemsRegister.STEEL_ARMOR.values().stream().map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).toList());
        event.acceptAll(ModItemsRegister.ALUMINUM_ARMOR.values().stream().map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).toList());
        event.acceptAll(ModItemsRegister.COPPER_ARMOR.values().stream().map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).toList());
        event.acceptAll(ModItemsRegister.ATIUM_ARMOR.values().stream().map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).toList());
        event.acceptAll(ModItemsRegister.LERASIUM_ARMOR.values().stream().map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).toList());
        event.acceptAll(ModItemsRegister.ETTMETAL_ARMOR.values().stream().map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).toList());
    }


    public static void addToMetallicsArtsDecorationTab(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab().equals(ModCreativeTabs.METALLICS_ARTS_DECORATION_TAB)) {

            event.accept(ModBlocksRegister.MARE_FLOWER);

            event.acceptAll(ModBlocksRegister.BLOCK_METAL_ORES.values().stream().map(ItemStack::new).toList());
            event.acceptAll(ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.values().stream().map(ItemStack::new).toList());

            event.acceptAll(ModBlocksRegister.RAW_METAL_BLOCKS.values().stream().map(ItemStack::new).toList());
            event.acceptAll(ModBlocksRegister.BLOCK_METAL_BLOCKS.values().stream().map(ItemStack::new).toList());
            event.acceptAll(ModBlocksRegister.BLOCK_GEMS_BLOCKS.values().stream().map(ItemStack::new).toList());

            event.acceptAll(ModBlocksRegister.METAL_PANELS.values().stream().map(ItemStack::new).toList());

            event.accept(ModBlocksRegister.BUDDING_ETTMETAL);
            event.accept(ModBlocksRegister.ETTMETAL_CLUSTER);
            event.accept(ModBlocksRegister.LARGE_ETTMETAL_BUD);
            event.accept(ModBlocksRegister.SMALL_ETTMETAL_BUD);
            event.accept(ModBlocksRegister.MEDIUM_ETTMETAL_BUD);

            event.accept(ModBlocksRegister.BUDDING_ATIUM);
            event.accept(ModBlocksRegister.ATIUM_CLUSTER);
            event.accept(ModBlocksRegister.LARGE_ATIUM_BUD);
            event.accept(ModBlocksRegister.SMALL_ATIUM_BUD);
            event.accept(ModBlocksRegister.MEDIUM_ATIUM_BUD);

            event.accept(ModBlocksRegister.BUDDING_LERASIUM);
            event.accept(ModBlocksRegister.LERASIUM_CLUSTER);
            event.accept(ModBlocksRegister.LARGE_LERASIUM_BUD);
            event.accept(ModBlocksRegister.SMALL_LERASIUM_BUD);
            event.accept(ModBlocksRegister.MEDIUM_LERASIUM_BUD);


            event.acceptAll(ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.values().stream().map(ItemStack::new).toList());
            event.acceptAll(ModBlocksRegister.BLOCK_METAL_SLAB.values().stream().map(ItemStack::new).toList());
            event.acceptAll(ModBlocksRegister.BLOCK_METAL_STAIRS.values().stream().map(ItemStack::new).toList());

            event.acceptAll(ModBlocksRegister.BLOCK_METAL_WALL.values().stream().map(ItemStack::new).toList());
            event.acceptAll(ModBlocksRegister.BLOCK_METAL_FENCE.values().stream().map(ItemStack::new).toList());
            event.acceptAll(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.values().stream().map(ItemStack::new).toList());

            event.acceptAll(ModItemsRegister.ITEM_ICONS_ALLOMANCY.values().stream().map(ItemStack::new).toList());
            event.acceptAll(ModItemsRegister.ITEM_ICONS_ALLOMANCY_DIVINE.values().stream().map(ItemStack::new).toList());
            event.acceptAll(ModItemsRegister.ITEM_ICONS_FERUCHEMIC.values().stream().map(ItemStack::new).toList());
            event.acceptAll(ModItemsRegister.ITEM_ICONS_FERUCHEMIC_DIVINE.values().stream().map(ItemStack::new).toList());

            event.acceptAll(ModBannersRegister.PATTERN_ITEMS.values().stream().map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).toList());
        }
    }

    public static void addToMetallicsArtsEntityTab(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab().equals(CreativeModeTabs.SPAWN_EGGS)) {

           event.acceptAll(ModItemsRegister.ENTITY_EGGS.values().stream().map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).toList());
        }
    }

}

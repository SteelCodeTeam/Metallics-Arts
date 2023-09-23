package net.rudahee.metallics_arts.setup.tabs;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

@Mod.EventBusSubscriber(modid = MetallicsArts.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTabs {

    public static CreativeModeTab METALLICS_ARTS_TAB;
    public static CreativeModeTab METTALLICS_ARTS_DECORATION_TAB;
    public static CreativeModeTab METALLICS_ARTS_ENTITY_TAB;

    @SubscribeEvent
    public static void registerTabs(CreativeModeTabEvent.Register event) {
        METALLICS_ARTS_TAB = event.registerCreativeModeTab(
                new ResourceLocation(MetallicsArts.MOD_ID, "metallics_arts_tab"),
                    builder ->
                            builder.icon(
                                    () -> new ItemStack(ModItemsRegister.ITEM_ICONS_ALLOMANCY_DIVINE.get(MetalTagEnum.ETTMETAL.getMetalNameLower())))
                                    .title(Component.literal("Metallics Arts")).withSearchBar().build());

        METTALLICS_ARTS_DECORATION_TAB = event.registerCreativeModeTab(
                new ResourceLocation(MetallicsArts.MOD_ID, "metallics_arts_decoration_tab"),
                builder ->
                        builder.icon(
                                        () -> new ItemStack(ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(MetalTagEnum.ETTMETAL.getMetalNameLower())))
                                .title(Component.translatable("metallics_arts.tab.decorations")).withSearchBar().build());



        METALLICS_ARTS_ENTITY_TAB = event.registerCreativeModeTab(
                new ResourceLocation(MetallicsArts.MOD_ID, "metallics_arts_entity_tab"),
                builder ->
                        builder.icon(
                                        () -> new ItemStack(ModItemsRegister.MISTCLOACK.get()))
                                .title(Component.translatable("metallics_arts.tab.entities")).withSearchBar().build());
    }
}


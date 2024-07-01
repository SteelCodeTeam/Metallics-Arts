package net.rudahee.metallics_arts.setup.tabs;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

@Mod.EventBusSubscriber(modid = MetallicsArts.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTabs {

    private ModCreativeTabs() {
        throw new IllegalStateException("Class can't be instantiated");
    }
    protected static CreativeModeTab metallicsArtsTab;
    protected static CreativeModeTab metallicsArtsDecorationTab;
    protected static CreativeModeTab metallicsArtsEntityTab;

    @SubscribeEvent
    public static void registerTabs(CreativeModeTabEvent.Register event) {
        metallicsArtsTab = event.registerCreativeModeTab(
                new ResourceLocation(MetallicsArts.MOD_ID, "metallics_arts_tab"),
                    builder ->
                            builder.icon(
                                    () -> new ItemStack(ModItemsRegister.ITEM_ICONS_ALLOMANCY_DIVINE.get(MetalTagEnum.ETTMETAL.getMetalNameLower())))
                                    .title(Component.literal("Metallics Arts")).withSearchBar().build());

        metallicsArtsDecorationTab = event.registerCreativeModeTab(
                new ResourceLocation(MetallicsArts.MOD_ID, "metallics_arts_decoration_tab"),
                builder ->
                        builder.icon(
                                        () -> new ItemStack(ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(MetalTagEnum.ETTMETAL.getMetalNameLower())))
                                .title(Component.translatable("metallics_arts.tab.decorations")).withSearchBar().build());

        metallicsArtsEntityTab = event.registerCreativeModeTab(
                new ResourceLocation(MetallicsArts.MOD_ID, "metallics_arts_entity_tab"),
                builder ->
                        builder.icon(
                                        () -> new ItemStack(ModItemsRegister.MISTCLOACK.get()))
                                .title(Component.translatable("metallics_arts.tab.entities")).withSearchBar().build());
    }
}


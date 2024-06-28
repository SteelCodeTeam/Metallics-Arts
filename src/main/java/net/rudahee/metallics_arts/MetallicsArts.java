package net.rudahee.metallics_arts;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.MetalMindEnum;
import net.rudahee.metallics_arts.data.player.poses.CustomPoses;
import net.rudahee.metallics_arts.data.providers.ModInvestedPlayerDataProvider;
import net.rudahee.metallics_arts.data.providers.ModPaintingProvider;
import net.rudahee.metallics_arts.modules.custom_block_entities.crucible_furnace.CrucibleFurnaceScreen;
import net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.front.HemalurgyAltarFrontScreen;
import net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.back.HemalurgyAltarBackScreen;
import net.rudahee.metallics_arts.modules.custom_blocks.sings.WoodTypeMetal;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.render.CuriosLayerDefinitions;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.render.MetalMindModel;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.render.MetalMindRendered;
import net.rudahee.metallics_arts.modules.effects.ModEffects;
import net.rudahee.metallics_arts.modules.logic.client.ClientEventHandler;
import net.rudahee.metallics_arts.modules.logic.client.custom_guis.overlays.MetalsOverlay;
import net.rudahee.metallics_arts.modules.custom_entities.villagers.ModVillager;
import net.rudahee.metallics_arts.setup.Registration;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.*;

import net.rudahee.metallics_arts.setup.tabs.ModCreativeTabsEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import java.util.function.Supplier;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MetallicsArts.MOD_ID)
public class MetallicsArts {
    // Mod id
    public static final String MOD_ID = "metallics_arts";

    public static final String VERSION = "1.6.8";

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public MetallicsArts() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //In our main, we register all our objects.

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        Registration.register();

        modEventBus.addListener(this::clientInit);
        // Register the setup method for modloading
        modEventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        modEventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        modEventBus.addListener(this::processIMC);

        modEventBus.addListener(this::onGuOverlayEvent);

        ModEffects.register(modEventBus);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            modEventBus.addListener(ModKeyRegister::initKeys);
        });

        // Register the doClientStuff method for modloading
        modEventBus.addListener(InvestedPlayerCapabilityRegister::register);
        modEventBus.addListener(this::doClientStuff);

        ModEntityTypesRegister.register(modEventBus);
        ModLivingEntityRegister.register(modEventBus);
        ModBlockEntitiesRegister.register(modEventBus);
        ModMenuRegister.register(modEventBus);
        ModVillager.register(modEventBus);
        ModSoundsRegister.register(modEventBus);
        modEventBus.addListener(ModCreativeTabsEvents::addToMetallicsArtsTab);
        modEventBus.addListener(ModCreativeTabsEvents::addToMetallicsArtsDecorationTab);
        modEventBus.addListener(ModCreativeTabsEvents::addToCombatTab);
        modEventBus.addListener(ModCreativeTabsEvents::addToMetallicsArtsEntityTab);

        modEventBus.addListener(this::registerLayers);

        //Register for the paintings
        ModPaintingProvider.register(modEventBus);

        ModBannersRegister.register();


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);


    }


    @SubscribeEvent
    public void attachCapabilitiesEntity(final AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(InvestedPlayerCapabilityRegister.IDENTIFIER, new ModInvestedPlayerDataProvider());
        }


    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("Starting Metallics Arts Setup.");
        ModNetwork.registerPackets();
        ModEventsRegister.register(event);

        Sheets.addWoodType(WoodTypeMetal.IRON_TYPE);
        Sheets.addWoodType(WoodTypeMetal.GOLD_TYPE);
        Sheets.addWoodType(WoodTypeMetal.COPPER_TYPE);
        Sheets.addWoodType(WoodTypeMetal.ALUMINUM_TYPE);

        // TODO event.enqueueWork(ModVillager::registerPOIs);

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        CustomPoses.initializePoses();



        event.enqueueWork(() -> {

        });
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("metalmind_slot")
                        .priority(1)
                        .size(4)
                        .icon(new ResourceLocation("curios:slot/metal_mind_slot"))
                        .build());

        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE,
                () -> SlotTypePreset.BACK.getMessageBuilder().build());

    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call

    @SubscribeEvent
    public void onGuOverlayEvent(final RegisterGuiOverlaysEvent event) {
        event.registerBelowAll("invested_overlay", new MetalsOverlay());


        ClientEventHandler.onRenderGameOverlay(event);
    }

    @SubscribeEvent
    public void onCommandsRegister(RegisterCommandsEvent event) {
        ModCommandsRegister.register(event.getDispatcher());
    }


    public void clientInit(final FMLClientSetupEvent e) {

        //ModClientDependencyManagement.start();

        ModEventsRegister.clientInit(e);
        MenuScreens.register(ModMenuRegister.CRUCIBLE_FURNACE_MENU.get(), CrucibleFurnaceScreen::new);
        MenuScreens.register(ModMenuRegister.HEMALURGY_ALTAR_FRONT_MENU.get(), HemalurgyAltarFrontScreen::new);
        MenuScreens.register(ModMenuRegister.HEMALURGY_ALTAR_BACK_MENU.get(), HemalurgyAltarBackScreen::new);

        WoodType.register(WoodTypeMetal.IRON_TYPE);
        WoodType.register(WoodTypeMetal.GOLD_TYPE);
        WoodType.register(WoodTypeMetal.COPPER_TYPE);
        WoodType.register(WoodTypeMetal.ALUMINUM_TYPE);
        BlockEntityRenderers.register(ModBlockEntitiesRegister.BLOCK_ENTITY.get(), SignRenderer::new);

        CuriosRendererRegistry.register(MetalMindEnum.IRON_STEEL.getBand(), MetalMindRendered::new);
    }

    private void registerLayers(final EntityRenderersEvent.RegisterLayerDefinitions evt) {
        evt.registerLayerDefinition(CuriosLayerDefinitions.METALMIND, MetalMindModel::createLayer);
    }



    // ITEM & BLOCK REGISTRATION

    public static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> itemSupplier) {
        return MetallicsArts.ITEMS.register(name, itemSupplier);
    }

    public static <T extends Block> RegistryObject<T> registerBlockNoItem(String name, Supplier<T> blockSupplier) {
        return BLOCKS.register(name, blockSupplier);
    }

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> blockSupplier) {

        RegistryObject<T> blockRegistered = registerBlockNoItem(name, blockSupplier);
        ITEMS.register(name, () -> (new BlockItem(blockRegistered.get(), new Item.Properties().stacksTo(64))));
        //ITEMS.register(name, () -> (new BlockItem(blockRegistered.get(), new Item.Properties().tab(MA_TAB).stacksTo(64))));
        return blockRegistered;

    }

    public static <T extends Block> RegistryObject<T> registerBlockDecoration(String name, Supplier<T> blockSupplier) {
        RegistryObject<T> blockRegistered = registerBlockNoItem(name, blockSupplier);
        //ITEMS.register(name, () -> (new BlockItem(blockRegistered.get(), new Item.Properties().tab(MA_TAB_DECORATION).stacksTo(64))));
        ITEMS.register(name, () -> (new BlockItem(blockRegistered.get(), new Item.Properties().stacksTo(64))));
        return blockRegistered;
    }


}

package net.rudahee.metallics_arts;

import lombok.extern.log4j.Log4j2;
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
import net.rudahee.metallics_arts.modules.custom_block_entities.distillery.DistilleryScreen;
import net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.back.HemalurgyAltarBackRenderer;
import net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.back.HemalurgyAltarBackScreen;
import net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.front.HemalurgyAltarFrontRenderer;
import net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.front.HemalurgyAltarFrontScreen;
import net.rudahee.metallics_arts.modules.custom_blocks.sings.WoodTypeMetal;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.render.CuriosLayerDefinitions;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.render.MetalMindModel;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.render.MetalMindRendered;
import net.rudahee.metallics_arts.modules.effects.ModEffects;
import net.rudahee.metallics_arts.modules.logic.client.ClientEventHandler;
import net.rudahee.metallics_arts.modules.logic.client.custom_guis.overlays.MetalsOverlay;
import net.rudahee.metallics_arts.setup.Registration;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.*;
import net.rudahee.metallics_arts.setup.tabs.ModCreativeTabsEvents;
import software.bernie.geckolib.GeckoLib;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Log4j2
@Mod(MetallicsArts.MOD_ID)
public class MetallicsArts {
    // Mod id
    public static final String MOD_ID = "metallics_arts";

    public static final String VERSION = "1.6.8";

    // Directly reference a log4j logger.

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public MetallicsArts() {
        log.info("""
                
                ======================================================
                Starting Registration for Metallics Arts version {}
                ======================================================
                
                """, VERSION);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        GeckoLib.initialize();

        //In our main, we register all our objects.

        log.info("Starting Register: Blocks");
        BLOCKS.register(modEventBus);
        log.info("Completed Register: Blocks");


        log.info("Starting Register: Items");
        ITEMS.register(modEventBus);
        log.info("Completed Register: Items");


        Registration.register();

        log.info("Starting Listeners: Client, Setup, queueIMC, processIMC, GUIs");
        modEventBus.addListener(this::clientInit);
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::enqueueIMC);
        modEventBus.addListener(this::processIMC);
        modEventBus.addListener(this::onGuOverlayEvent);
        log.info("Completed Some Listeners");

        ModEffects.register(modEventBus);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            log.info("Starting Listener: ClientKeys Shortcuts");
            modEventBus.addListener(ModKeyRegister::initKeys);
            log.info("Completed Listener: ClientKeys Shortcuts");

        });

        log.info("Starting Listener: Player Data");
        // Register the doClientStuff method for modloading
        modEventBus.addListener(InvestedPlayerCapabilityRegister::register);
        modEventBus.addListener(this::doClientStuff);
        log.info("Completed Listener: Player Data");

        log.info("Starting Register: Entities");
        ModEntityTypesRegister.register(modEventBus);
        ModLivingEntityRegister.register(modEventBus);
        ModBlockEntitiesRegister.register(modEventBus);
        ModVillagersRegister.register(modEventBus);
        log.info("Completed Register: Entities");

        log.info("Starting Register: Menus");
        ModMenuRegister.register(modEventBus);
        log.info("Completed Register: Menus");

        log.info("Starting Register: Sounds");
        ModSoundsRegister.register(modEventBus);
        log.info("Completed Register: Sounds");

        log.info("Starting Listener: Creative Tabs");
        modEventBus.addListener(ModCreativeTabsEvents::addToMetallicsArtsTab);
        modEventBus.addListener(ModCreativeTabsEvents::addToMetallicsArtsDecorationTab);
        modEventBus.addListener(ModCreativeTabsEvents::addToCombatTab);
        modEventBus.addListener(ModCreativeTabsEvents::addToMetallicsArtsEntityTab);
        log.info("Completed Listener: Creative Tabs");

        modEventBus.addListener(this::registerLayers);

        log.info("Starting Register: Paintings & Banners");
        ModPaintingProvider.register(modEventBus);
        ModBannersRegister.register();
        log.info("Completed Register: Paintings & Banners");

        log.info("Adding mod to Minecraft's EventBus");
        MinecraftForge.EVENT_BUS.register(this);
        log.info("Completed addition to EventBus");

        log.info("""
                
                =========================================
                Completed Registration for Metallics Arts
                =========================================
                
                """);
    }


    @SubscribeEvent
    public void attachCapabilitiesEntity(final AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(InvestedPlayerCapabilityRegister.IDENTIFIER, new ModInvestedPlayerDataProvider());
        }
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        log.info("Creating packets");
        ModNetwork.registerPackets();
        ModEventsRegister.register(event);
        event.enqueueWork(ModVillagersRegister::registerPOI);

        log.info("Adding wood types");
        Sheets.addWoodType(WoodTypeMetal.IRON_TYPE);
        Sheets.addWoodType(WoodTypeMetal.GOLD_TYPE);
        Sheets.addWoodType(WoodTypeMetal.COPPER_TYPE);
        Sheets.addWoodType(WoodTypeMetal.ALUMINUM_TYPE);

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        CustomPoses.initializePoses();

        event.enqueueWork(() -> {

        });
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        log.info("Creating Curios Slots");
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("metalmind_slot")
                        .priority(1)
                        .size(4)
                        .icon(new ResourceLocation("curios:slot/metal_mind_slot"))
                        .build());

        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE,
                () -> SlotTypePreset.BACK.getMessageBuilder().build());

    }

    @SuppressWarnings("deprecation")
    private void processIMC(final InterModProcessEvent event)
    {
        log.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).toList());
    }

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public void onGuOverlayEvent(final RegisterGuiOverlaysEvent event) {
        event.registerBelowAll("invested_overlay", new MetalsOverlay());

        ClientEventHandler.onRenderGameOverlay(event);
    }

    @SubscribeEvent
    public void onCommandsRegister(RegisterCommandsEvent event) {
        log.info("Adding commands");
        ModCommandsRegister.register(event.getDispatcher());
    }


    public void clientInit(final FMLClientSetupEvent e) {

        log.info("[Client] Registering Client Events");
        ModEventsRegister.clientInit(e);
        MenuScreens.register(ModMenuRegister.CRUCIBLE_FURNACE_MENU.get(), CrucibleFurnaceScreen::new);
        MenuScreens.register(ModMenuRegister.HEMALURGY_ALTAR_FRONT_MENU.get(), HemalurgyAltarFrontScreen::new);
        MenuScreens.register(ModMenuRegister.HEMALURGY_ALTAR_BACK_MENU.get(), HemalurgyAltarBackScreen::new);
        MenuScreens.register(ModMenuRegister.DISTILLERY_MENU.get(), DistilleryScreen::new);
        WoodType.register(WoodTypeMetal.IRON_TYPE);
        WoodType.register(WoodTypeMetal.GOLD_TYPE);
        WoodType.register(WoodTypeMetal.COPPER_TYPE);
        WoodType.register(WoodTypeMetal.ALUMINUM_TYPE);

        BlockEntityRenderers.register(ModBlockEntitiesRegister.SIGN_BLOCK_ENTITY.get(), SignRenderer::new);
        BlockEntityRenderers.register(ModBlockEntitiesRegister.HEMALURGY_ALTAR_BACK_ENTITY.get(), HemalurgyAltarBackRenderer::new);
        BlockEntityRenderers.register(ModBlockEntitiesRegister.HEMALURGY_ALTAR_FRONT_ENTITY.get(), HemalurgyAltarFrontRenderer::new);

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
        return blockRegistered;

    }

    public static <T extends Block> RegistryObject<T> registerBlockDecoration(String name, Supplier<T> blockSupplier) {
        RegistryObject<T> blockRegistered = registerBlockNoItem(name, blockSupplier);
        ITEMS.register(name, () -> (new BlockItem(blockRegistered.get(), new Item.Properties().stacksTo(64))));
        return blockRegistered;
    }


}

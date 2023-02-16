package net.rudahee.metallics_arts;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
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
import net.rudahee.metallics_arts.data.providers.ModPaintingProvider;
import net.rudahee.metallics_arts.modules.logic.client.ClientEventHandler;
import net.rudahee.metallics_arts.modules.logic.client.custom_guis.overlays.MetalsOverlay;
import net.rudahee.metallics_arts.setup.DataGenerators;
import net.rudahee.metallics_arts.setup.Registration;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.*;
import net.rudahee.metallics_arts.setup.registries.generation.ModGeodeGenerationRegister;
import net.rudahee.metallics_arts.setup.registries.generation.ModOreGenerationRegister;
import net.rudahee.metallics_arts.setup.registries.generation.ModStructureRegister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotTypeMessage;

import java.util.function.Supplier;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MetallicsArts.MOD_ID)
public class MetallicsArts {
    // Mod id
    public static final String MOD_ID = "metallics_arts";

    public static final String VERSION = "1.5.1";

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    // creative tab
    public static CreativeModeTab MA_TAB = new CreativeModeTab(MOD_ID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(Items.GOLD_INGOT);
        }
    };

    public static CreativeModeTab MA_TAB_DECORATION = new CreativeModeTab(-1, MOD_ID + ".decorations") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(Items.GOLD_BLOCK);
        }
    };

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

        modEventBus.addListener(this::onGuOveirlayEvent);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            modEventBus.addListener(ModKeyRegister::initKeys);
        });

        // Register the doClientStuff method for modloading
        modEventBus.addListener(ModBlocksRegister.InvestedCapabilityRegister::register);
        modEventBus.addListener(this::doClientStuff);

        ModGeodeGenerationRegister.register(modEventBus);
        ModOreGenerationRegister.register(modEventBus);
        ModStructureRegister.register(modEventBus);
        //Register for the paintings
        ModPaintingProvider.register(modEventBus);

        ModBannersRegister.register();


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        //book
        modEventBus.addListener(DataGenerators::gatherData);
    }

    @SubscribeEvent
    public void attachCapabilitiesEntity(final AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(ModBlocksRegister.InvestedCapabilityRegister.IDENTIFIER, new ModPaintingProvider.ModInvestedDataProvider());
        }
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("Starting Metallics Arts Setup.");

        ModNetwork.registerPackets();
        ModEventsRegister.register(event);

    }

    private void doClientStuff(final FMLClientSetupEvent event) {

        event.enqueueWork(() -> {

        });
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                ()-> new SlotTypeMessage.Builder("metalmind_slot")
                        .priority(1).size(4).icon(new ResourceLocation("curios:slot/metal_mind_slot")).build());
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
    public void onGuOveirlayEvent(final RegisterGuiOverlaysEvent event) {
        event.registerBelowAll("invested_overlay", new MetalsOverlay());

        ClientEventHandler.onRenderGameOverlay(event);
    }


    @SubscribeEvent
    public void onCommandsRegister(RegisterCommandsEvent event){
        ModCommandsRegister.register(event.getDispatcher());
    }

    public void clientInit(final FMLClientSetupEvent e){
        ModEventsRegister.clientInit(e);
    }



    // ITEM & BLOCK REGISTRATION

    public static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> itemSupplier) {
        return MetallicsArts.ITEMS.register(name, itemSupplier);
    }

    private static <T extends Block> RegistryObject<T> registerBlockNoItem(String name, Supplier<T> blockSupplier) {
        return BLOCKS.register(name, blockSupplier);
    }

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> blockSupplier) {

        RegistryObject<T> blockRegistered = registerBlockNoItem(name, blockSupplier);
        ITEMS.register(name, () -> (new BlockItem(blockRegistered.get(), new Item.Properties().tab(MA_TAB).stacksTo(64))));
        return blockRegistered;

    }

    public static <T extends Block> RegistryObject<T> registerBlockDecoration(String name, Supplier<T> blockSupplier) {

        RegistryObject<T> blockRegistered = registerBlockNoItem(name, blockSupplier);
        ITEMS.register(name, () -> (new BlockItem(blockRegistered.get(), new Item.Properties().tab(MA_TAB_DECORATION).stacksTo(64))));
        return blockRegistered;
    }


}

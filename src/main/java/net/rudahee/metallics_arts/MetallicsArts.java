package net.rudahee.metallics_arts;

import net.minecraft.block.Blocks;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.rudahee.metallics_arts.modules.blocks.alloy_furnace.AlloyFurnaceScreen;
import net.rudahee.metallics_arts.modules.client.KeyInit;
import net.rudahee.metallics_arts.modules.data_player.*;
import net.rudahee.metallics_arts.modules.powers.MetallicsPowersSetup;
import net.rudahee.metallics_arts.setup.Registration;
import net.rudahee.metallics_arts.setup.commands.MetallicArtsCommand;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModContainers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.type.util.IIconHelper;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MetallicsArts.MOD_ID)
public class MetallicsArts
{
    // Mod id
    public static final String MOD_ID = "metallics_arts";


    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public MetallicsArts() {

        //In our main, we register all our objects.
        Registration.register();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientInit);

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void attachCapabilitiesEntity(final AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof PlayerEntity) {
            event.addCapability(InvestedCapability.IDENTIFIER, new InvestedDataProvider());
        }
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

        CapabilityManager.INSTANCE.register(IDefaultInvestedPlayerData.class, new InvestedStorage(), DefaultInvestedPlayerData::new);
        MetallicsPowersSetup.register(event);
        ModNetwork.registerPackets();


    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);

        event.enqueueWork(() -> {
            ScreenManager.register(ModContainers.ALLOY_FURNACE_CONTAINER.get(),
                    AlloyFurnaceScreen::new);
            KeyInit.register();
        });
    }


    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        IIconHelper iconHelper = CuriosApi.getIconHelper();
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                ()-> new SlotTypeMessage.Builder("metalmind_slot").priority(1).size(4).build());
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
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public void onCommandsRegister(RegisterCommandsEvent event){
        MetallicArtsCommand.register(event.getDispatcher());
    }


    public void clientInit(final FMLClientSetupEvent e){
        MetallicsPowersSetup.clientInit(e);
    }

}

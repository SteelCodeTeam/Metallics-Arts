package net.rudahee.metallics_arts.modules.powers;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;

public class MetallicsPowersSetup {

    public static void clientInit(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new PowersEventHandler());
    }

    public static void registerCommands(final RegisterCommandsEvent event) {
    }

    public static void init(final FMLCommonSetupEvent event) {

        InvestedCapability.register();
        MinecraftForge.EVENT_BUS.register(PowersEventHandler.class);
    }

    public static void register() {
        MetallicsPowersSetup.register();
    }
}

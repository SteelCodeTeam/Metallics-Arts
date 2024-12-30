package net.rudahee.metallics_arts.setup.registries;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.rudahee.metallics_arts.modules.logic.client.ClientEventHandler;
import net.rudahee.metallics_arts.modules.logic.server.ServerEventHandler;


public class ModEventsRegister {

    private ModEventsRegister() {
        throw new IllegalStateException("Class can't be instantiated");
    }

    public static void clientInit(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new ServerEventHandler());
    }


    public static void register(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
        });
    }


}

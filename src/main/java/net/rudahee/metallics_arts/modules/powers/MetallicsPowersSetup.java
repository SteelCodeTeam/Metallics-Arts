package net.rudahee.metallics_arts.modules.powers;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.rudahee.metallics_arts.modules.powers.client.PowersClientEventHandler;


public class MetallicsPowersSetup {

    public static void clientInit(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new PowersEventHandler());
    }


    public static void register(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new PowersClientEventHandler());
    }

    public static void register() {
        MetallicsPowersSetup.register();
    }
}

package net.rudahee.metallics_arts.modules.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.MetallicsArts;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = MetallicsArts.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @Mod.EventBusSubscriber(modid = MetallicsArts.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
        public static class ClientModBusEvents {

            @SubscribeEvent
            public static void onKeyRegister(RegisterKeyMappingsEvent event) {
                event.register(KeyInit.ALLOMANTIC_POWER_SELECTOR);
                event.register(KeyInit.FERUCHEMIC_POWER_SELECTOR);
            }

        }


    }
}

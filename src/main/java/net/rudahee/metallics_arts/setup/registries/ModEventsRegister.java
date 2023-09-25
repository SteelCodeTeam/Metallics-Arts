package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.rudahee.metallics_arts.modules.custom_entities.iron_allomancer_entity.IronAllomancerEntity;
import net.rudahee.metallics_arts.modules.custom_entities.steel_allomancer_entity.SteelAllomancerEntity;
import net.rudahee.metallics_arts.modules.logic.client.ClientEventHandler;
import net.rudahee.metallics_arts.modules.logic.server.ServerEventHandler;


public class ModEventsRegister {

    public static void clientInit(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new ServerEventHandler());
    }


    public static void register(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
        });
    }

    public static void register() {
        ModEventsRegister.register();
    }
}
